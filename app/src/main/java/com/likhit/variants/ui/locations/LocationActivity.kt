package com.likhit.variants.ui.locations

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.likhit.variants.R
import com.likhit.variants.base.BaseActivity
import com.likhit.variants.data.models.BaseResponse
import com.likhit.variants.data.models.VariantGroup
import com.likhit.variants.data.models.Variation
import com.likhit.variants.databinding.ActivityLocationBinding
import com.likhit.variants.listeners.OnItemClickListener
import com.likhit.variants.ui.helper.VariantAdapter
import com.likhit.variants.utils.ActivityLauncher
import com.likhit.variants.utils.AppConstants

class LocationActivity : BaseActivity(), OnItemClickListener<Variation> {

    private var binding: ActivityLocationBinding? = null

    private var variantAdapter: VariantAdapter? = null

    private var variants: BaseResponse? = null
    private var variantGroup: VariantGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        setupToolbar(getString(R.string.locations), true)

        if (intent.getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) != null) {
            variants = intent.getSerializableExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE) as BaseResponse
            variantGroup = intent.getSerializableExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP) as VariantGroup
            initView()
        } else {
            showMessage(R.string.generic_error)
        }
    }

    private fun initView() {
        variantAdapter = VariantAdapter(this)
        variantAdapter!!.variations = variantGroup!!.variations
        binding!!.rvVariations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding!!.rvVariations.adapter = variantAdapter
    }


    override fun onItemClick(item: Variation, position: Int, view: View) {
        if (variants!!.variants!!.variantGroups!!.size > 0) {
            ActivityLauncher.launchHomeActivity(variants!!, variantGroup!!.groupId, item.id, item.name, this)
        }
    }
}
