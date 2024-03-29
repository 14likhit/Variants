package com.likhit.variants.ui.locations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.likhit.variants.R;
import com.likhit.variants.base.BaseActivity;
import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.VariantGroup;
import com.likhit.variants.data.models.Variation;
import com.likhit.variants.databinding.ActivityLocationBinding;
import com.likhit.variants.listeners.OnItemClickListener;
import com.likhit.variants.ui.helper.VariantAdapter;
import com.likhit.variants.utils.ActivityLauncher;
import com.likhit.variants.utils.AppConstants;

public class LocationActivity extends BaseActivity implements OnItemClickListener<Variation> {

    private ActivityLocationBinding binding;

    private VariantAdapter variantAdapter;

    private BaseResponse variants;
    private VariantGroup variantGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location);

        setupToolbar(getString(R.string.locations), true);

        if (getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) != null) {
            variants = (BaseResponse) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE);
            variantGroup = (VariantGroup) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP);
            initView();
        } else {
            showMessage(R.string.generic_error);
        }
    }

    private void initView() {
        variantAdapter = new VariantAdapter(this);
        variantAdapter.setVariations(variantGroup.getVariations());
        binding.rvVariations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvVariations.setAdapter(variantAdapter);
    }


    @Override
    public void onItemClick(Variation item, int position, View view) {
        if (variants.getVariants().getVariantGroups().size() > 0) {
            ActivityLauncher.launchHomeActivity(variants, variantGroup.getGroupId(), item.getId(), item.getName(), this);
        }
    }
}
