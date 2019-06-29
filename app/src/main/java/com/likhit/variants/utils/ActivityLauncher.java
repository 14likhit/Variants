package com.likhit.variants.utils;

import android.app.Activity;
import android.content.Intent;

import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.VariantGroup;
import com.likhit.variants.ui.companies.CompanyActivity;
import com.likhit.variants.ui.home.HomeActivity;
import com.likhit.variants.ui.jobs.JobActivity;
import com.likhit.variants.ui.locations.LocationActivity;

public class ActivityLauncher {

    public ActivityLauncher() {
    }

    public static void launchHomeActivity(BaseResponse baseResponse, String fromGroupId, String fromVariantId, Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse);
        intent.putExtra(AppConstants.BUNDLE_KEY_FROM_GROUP_ID, fromGroupId);
        intent.putExtra(AppConstants.BUNDLE_KEY_FROM_VARIANT_ID, fromVariantId);
        activity.startActivity(intent);
    }

    public static void launchCompanyActivity(BaseResponse baseResponse, VariantGroup variantGroup, Activity activity) {
        Intent intent = new Intent(activity, CompanyActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse);
        intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup);
        activity.startActivity(intent);
    }

    public static void launchJobActivity(BaseResponse baseResponse, VariantGroup variantGroup, Activity activity) {
        Intent intent = new Intent(activity, JobActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse);
        intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup);
        activity.startActivity(intent);
    }

    public static void launchLocationActivity(BaseResponse baseResponse, VariantGroup variantGroup, Activity activity) {
        Intent intent = new Intent(activity, LocationActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_BASE_RESPONSE, baseResponse);
        intent.putExtra(AppConstants.BUNDLE_KEY_VARIANT_GROUP, variantGroup);
        activity.startActivity(intent);
    }

}
