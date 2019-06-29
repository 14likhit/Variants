package com.likhit.variants.data;

import com.likhit.variants.data.models.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/bins/w2km6")
    Call<BaseResponse> getVariants();
}