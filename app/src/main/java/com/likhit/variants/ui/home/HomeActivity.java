package com.likhit.variants.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.likhit.variants.R;
import com.likhit.variants.base.BaseActivity;
import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.VariantGroup;
import com.likhit.variants.databinding.ActivityHomeBinding;
import com.likhit.variants.listeners.OnItemClickListener;
import com.likhit.variants.utils.ActivityLauncher;
import com.likhit.variants.utils.AppConstants;

public class HomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<VariantGroup> {

    private final static String TAG = "HomeActivity";

    private ActivityHomeBinding binding;

    private VariantsViewModel variantsViewModel;

    private BaseResponse variants;

    private boolean setOnRefresh;

    private VariantGroupAdapter variantGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        if (getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) != null) {
            variants = (BaseResponse) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE);
            setupToolbar(getString(R.string.options), true);
            setOnRefresh = false;
            initView();
        } else {
            setupToolbar(getString(R.string.home), false);
            variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel.class);
            setOnRefresh = true;
            getVariants();
        }
    }

    private void getVariants() {
        variantsViewModel.getVariants().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(@Nullable BaseResponse baseResponse) {
                if (baseResponse != null) {
                    variants = baseResponse;
                    initView();
                } else {
                    showMessage(R.string.generic_error);
                }
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void initView() {
        if (setOnRefresh) {
            binding.swipeRefresh.setOnRefreshListener(this);
        } else {
            binding.swipeRefresh.setEnabled(false);
        }
        variantGroupAdapter = new VariantGroupAdapter(this, this);
        variantGroupAdapter.setVariations(variants.getVariants().getVariantGroups());
        binding.rvVariations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvVariations.setAdapter(variantGroupAdapter);
    }

    @Override
    public void onRefresh() {
        getVariants();
    }

    @Override
    public void onItemClick(VariantGroup item, int position, View view) {
        if (item.getName().equalsIgnoreCase(AppConstants.KEY_COMPANY)) {
            ActivityLauncher.launchCompanyActivity(variants, item, this);
        } else if (item.getName().equalsIgnoreCase(AppConstants.KEY_JOB)) {
            ActivityLauncher.launchJobActivity(variants, item, this);
        } else if (item.getName().equalsIgnoreCase(AppConstants.KEY_LOACATION)) {
            ActivityLauncher.launchLocationActivity(variants, item, this);
        }
    }
}
