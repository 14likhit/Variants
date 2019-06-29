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
import com.likhit.variants.data.models.Variants;
import com.likhit.variants.databinding.ActivityHomeBinding;
import com.likhit.variants.listeners.OnItemClickListener;
import com.likhit.variants.ui.helper.ExcludedListHelper;
import com.likhit.variants.utils.ActivityLauncher;
import com.likhit.variants.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<VariantGroup> {

    private final static String TAG = "HomeActivity";

    private ActivityHomeBinding binding;

    private VariantsViewModel variantsViewModel;

    private BaseResponse variants;
    private List<VariantGroup> variantGroupList;

    private String fromGroupId;
    private String fromVariantID;

    private boolean setOnRefresh;

    private VariantGroupAdapter variantGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        if (getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) != null) {
            variants = (BaseResponse) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE);
            variantGroupList = variants.getVariants().getVariantGroups();
            fromGroupId = getIntent().getStringExtra(AppConstants.BUNDLE_KEY_FROM_GROUP_ID);
            fromVariantID = getIntent().getStringExtra(AppConstants.BUNDLE_KEY_FROM_VARIANT_ID);
            setupToolbar(getIntent().getStringExtra(AppConstants.BUNDLE_KEY_TITLE), true);
            setOnRefresh = false;
            initView();
        } else {
            setupToolbar(getString(R.string.app_name), false);
            variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel.class);
            setOnRefresh = true;
            getVariants();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (setOnRefresh) {
            getVariants();
        }
    }

    private void getVariants() {
        binding.swipeRefresh.setRefreshing(true);
        variantsViewModel.getVariants().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(@Nullable BaseResponse baseResponse) {
                if (baseResponse != null) {
                    variants = baseResponse;
                    variantGroupList = variants.getVariants().getVariantGroups();
                    ExcludedListHelper.setExcludedList(variants.getVariants().getExcludeList());
                    ExcludedListHelper.setExcludingMapping();
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
        variantGroupAdapter.setVariations(variantGroupList);
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
            List<VariantGroup> updateVariationGroup = new ArrayList<>();
            for (VariantGroup variantGroup : variants.getVariants().getVariantGroups()) {
                if (!variantGroup.getGroupId().equalsIgnoreCase(item.getGroupId())) {
                    updateVariationGroup.add(variantGroup);
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.setVariations(ExcludedListHelper.getUpdatedVariationsList(fromGroupId, fromVariantID, item.getGroupId(), item.getVariations()));
            }
            ActivityLauncher.launchCompanyActivity(new BaseResponse(new Variants(updateVariationGroup)), item, this);
        } else if (item.getName().equalsIgnoreCase(AppConstants.KEY_JOB)) {
            List<VariantGroup> updateVariationGroup = new ArrayList<>();
            for (VariantGroup variantGroup : variants.getVariants().getVariantGroups()) {
                if (!variantGroup.getGroupId().equalsIgnoreCase(item.getGroupId())) {
                    updateVariationGroup.add(variantGroup);
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.setVariations(ExcludedListHelper.getUpdatedVariationsList(fromGroupId, fromVariantID, item.getGroupId(), item.getVariations()));
            }
            ActivityLauncher.launchJobActivity(new BaseResponse(new Variants(updateVariationGroup)), item, this);
        } else if (item.getName().equalsIgnoreCase(AppConstants.KEY_LOCATION)) {
            List<VariantGroup> updateVariationGroup = new ArrayList<>();
            for (VariantGroup variantGroup : variants.getVariants().getVariantGroups()) {
                if (!variantGroup.getGroupId().equalsIgnoreCase(item.getGroupId())) {
                    updateVariationGroup.add(variantGroup);
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.setVariations(ExcludedListHelper.getUpdatedVariationsList(fromGroupId, fromVariantID, item.getGroupId(), item.getVariations()));
            }
            ActivityLauncher.launchLocationActivity(new BaseResponse(new Variants(updateVariationGroup)), item, this);
        }
    }
}
