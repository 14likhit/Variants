package com.likhit.variants.utils

import android.app.Activity
import android.content.Intent

import com.likhit.variants.data.models.BaseResponse
import com.likhit.variants.data.models.VariantGroup
import com.likhit.variants.ui.companies.CompanyActivity
import com.likhit.variants.ui.home.HomeActivity
import com.likhit.variants.ui.jobs.JobActivity
import com.likhit.variants.ui.locations.LocationActivity

class ActivityLauncher {
    companion object {

        fun launchHomeActivity(baseResponse: BaseResponse, fromGroupId: String, fromVariantId: String, title: String, activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse)
            intent.putExtra(AppConstants.BUNDLE_KEY_FROM_GROUP_ID, fromGroupId)
            intent.putExtra(AppConstants.BUNDLE_KEY_FROM_VARIANT_ID, fromVariantId)
            intent.putExtra(AppConstants.BUNDLE_KEY_TITLE, title)
            activity.startActivity(intent)
        }

        fun launchCompanyActivity(baseResponse: BaseResponse, variantGroup: VariantGroup, activity: Activity) {
            val intent = Intent(activity, CompanyActivity::class.java)
            intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse)
            intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup)
            activity.startActivity(intent)
        }

        fun launchJobActivity(baseResponse: BaseResponse, variantGroup: VariantGroup, activity: Activity) {
            val intent = Intent(activity, JobActivity::class.java)
            intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse)
            intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup)
            activity.startActivity(intent)
        }

        fun launchLocationActivity(baseResponse: BaseResponse, variantGroup: VariantGroup, activity: Activity) {
            val intent = Intent(activity, LocationActivity::class.java)
            intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse)
            intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup)
            activity.startActivity(intent)
        }
    }

}
