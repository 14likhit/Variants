package com.likhit.variants.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.likhit.variants.R
import com.likhit.variants.base.BaseActivity
import com.likhit.variants.data.models.BaseResponse
import com.likhit.variants.data.models.VariantGroup
import com.likhit.variants.data.models.Variants
import com.likhit.variants.databinding.ActivityHomeBinding
import com.likhit.variants.listeners.OnItemClickListener
import com.likhit.variants.ui.helper.ExcludedListHelper
import com.likhit.variants.utils.ActivityLauncher
import com.likhit.variants.utils.AppConstants

import java.util.ArrayList

class HomeActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<VariantGroup> {

    private var binding: ActivityHomeBinding? = null

    private var variantsViewModel: VariantsViewModel? = null

    private var variants: BaseResponse? = null
    private var variantGroupList: List<VariantGroup>? = null

    private var fromGroupId: String? = null
    private var fromVariantID: String? = null

    private var setOnRefresh: Boolean = false

    private var variantGroupAdapter: VariantGroupAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        if (intent.getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) != null) {
            variants = intent.getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) as BaseResponse
            variantGroupList = variants!!.variants!!.variantGroups
            fromGroupId = intent.getStringExtra(AppConstants.BUNDLE_KEY_FROM_GROUP_ID)
            fromVariantID = intent.getStringExtra(AppConstants.BUNDLE_KEY_FROM_VARIANT_ID)
            setupToolbar(intent.getStringExtra(AppConstants.BUNDLE_KEY_TITLE), true)
            setOnRefresh = false
            initView()
        } else {
            setupToolbar(getString(R.string.app_name), false)
            variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel::class.java!!)
            setOnRefresh = true
            getVariants()
        }
    }

    override fun onResume() {
        super.onResume()
        if (setOnRefresh) {
            getVariants()
        }
    }

    private fun getVariants() {
        binding!!.swipeRefresh.isRefreshing = true
        variantsViewModel!!.variants.observe(this, Observer { baseResponse ->
            if (baseResponse != null) {
                variants = baseResponse
                variantGroupList = variants!!.variants!!.variantGroups
                ExcludedListHelper.setExcludedList(variants!!.variants!!.excludeList!!)
                ExcludedListHelper.setExcludingMapping()
                initView()
            } else {
                showMessage(R.string.generic_error)
            }
            binding!!.swipeRefresh.isRefreshing = false
        })
    }

    private fun initView() {
        if (setOnRefresh) {
            binding!!.swipeRefresh.setOnRefreshListener(this)
        } else {
            binding!!.swipeRefresh.isEnabled = false
        }
        variantGroupAdapter = VariantGroupAdapter(this, this)
        variantGroupAdapter!!.variations = variantGroupList
        binding!!.rvVariations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.rvVariations.adapter = variantGroupAdapter
    }

    override fun onRefresh() {
        getVariants()
    }

    override fun onItemClick(item: VariantGroup, position: Int, view: View) {
        if (item.name.equals(AppConstants.KEY_COMPANY, ignoreCase = true)) {
            val updateVariationGroup = ArrayList<VariantGroup>()
            for (variantGroup in variants!!.variants!!.variantGroups!!) {
                if (!variantGroup.groupId.equals(item.groupId, ignoreCase = true)) {
                    updateVariationGroup.add(variantGroup)
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.variations = ExcludedListHelper.getUpdatedVariationsList(fromGroupId!!, fromVariantID!!, item.groupId, item.variations!!)
            }
            ActivityLauncher.launchCompanyActivity(BaseResponse(Variants(updateVariationGroup)), item, this)
        } else if (item.name.equals(AppConstants.KEY_JOB, ignoreCase = true)) {
            val updateVariationGroup = ArrayList<VariantGroup>()
            for (variantGroup in variants!!.variants!!.variantGroups!!) {
                if (!variantGroup.groupId.equals(item.groupId, ignoreCase = true)) {
                    updateVariationGroup.add(variantGroup)
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.variations = ExcludedListHelper.getUpdatedVariationsList(fromGroupId!!, fromVariantID!!, item.groupId, item.variations!!)
            }
            ActivityLauncher.launchJobActivity(BaseResponse(Variants(updateVariationGroup)), item, this)
        } else if (item.name.equals(AppConstants.KEY_LOCATION, ignoreCase = true)) {
            val updateVariationGroup = ArrayList<VariantGroup>()
            for (variantGroup in variants!!.variants!!.variantGroups!!) {
                if (!variantGroup.groupId.equals(item.groupId, ignoreCase = true)) {
                    updateVariationGroup.add(variantGroup)
                }
            }
            if (fromGroupId != null && fromVariantID != null) {
                item.variations = ExcludedListHelper.getUpdatedVariationsList(fromGroupId!!, fromVariantID!!, item.groupId, item.variations!!)
            }
            ActivityLauncher.launchLocationActivity(BaseResponse(Variants(updateVariationGroup)), item, this)
        }
    }

    companion object {

        private val TAG = "HomeActivity"
    }
}
