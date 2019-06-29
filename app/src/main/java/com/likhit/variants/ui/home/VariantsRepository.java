package com.likhit.variants.ui.home;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.likhit.variants.data.ApiClient;
import com.likhit.variants.data.ApiService;
import com.likhit.variants.data.models.BaseResponse;
import com.likhit.variants.data.models.Variants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VariantsRepository {

    private MutableLiveData<BaseResponse> variantsMutableLiveData = new MutableLiveData<>();

    private ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);

    public VariantsRepository() {
    }

    public MutableLiveData<BaseResponse> getVariants() {
        final Call<BaseResponse> request = service.getVariants();
        request.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse> call, @NonNull Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    variantsMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse> call, @NonNull Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
        return variantsMutableLiveData;
    }
}
