package com.likhit.variants.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.likhit.variants.R;
import com.likhit.variants.base.BaseActivity;
import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final static String TAG = "HomeActivity";

    private ActivityHomeBinding binding;

    private VariantsViewModel variantsViewModel;

    private BaseResponse variants;

    private VariantGroupAdapter variantGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setupToolbar(getString(R.string.home), false);

        variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel.class);
        getVariants();
    }

    private void getVariants() {
        binding.swipeRefresh.setRefreshing(true);
        variantsViewModel.getVariants().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(@Nullable BaseResponse baseResponse) {
                if (baseResponse != null) {
                    variants = baseResponse;
                    initView();
                } else {
                    showMessage("Unable to load. Please Check Internet Connection");
                }
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void initView() {
        binding.swipeRefresh.setOnRefreshListener(this);
        variantGroupAdapter = new VariantGroupAdapter(this);
        variantGroupAdapter.setVariations(variants.getVariants().getVariantGroups());
        binding.rvVariations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvVariations.setAdapter(variantGroupAdapter);
    }

    @Override
    public void onRefresh() {
        getVariants();
    }
}
