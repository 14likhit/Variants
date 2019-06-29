package com.likhit.variants.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.likhit.variants.R;
import com.likhit.variants.base.BaseActivity;
import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.Variants;

public class HomeActivity extends BaseActivity {

    private final static String TAG = "HomeActivity";

    private VariantsViewModel variantsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel.class);

        variantsViewModel.getVariants().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(@Nullable BaseResponse variants) {
                Log.d(TAG, "DATA RECEIVED");
            }
        });

    }
}
