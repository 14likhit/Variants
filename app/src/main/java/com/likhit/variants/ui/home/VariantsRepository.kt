package com.likhit.variants.ui.home

import android.arch.lifecycle.MutableLiveData
import android.util.Log

import com.likhit.variants.data.ApiClient
import com.likhit.variants.data.ApiService
import com.likhit.variants.data.models.BaseResponse
import com.likhit.variants.data.models.Variants

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VariantsRepository {

    private val variantsMutableLiveData = MutableLiveData<BaseResponse>()

    private val service = ApiClient.retrofitInstance.create(ApiService::class.java!!)

    val variants: MutableLiveData<BaseResponse>
        get() {
            val request = service.variants
            request.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        variantsMutableLiveData.setValue(response.body())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.e("API", t.message)
                    variantsMutableLiveData.setValue(null)
                }
            })
            return variantsMutableLiveData
        }
}
