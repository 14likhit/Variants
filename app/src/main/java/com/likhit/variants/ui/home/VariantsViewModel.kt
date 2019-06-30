package com.likhit.variants.ui.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

import com.likhit.variants.data.models.BaseResponse

class VariantsViewModel(application: Application) : AndroidViewModel(application) {

    private val variantsRepository: VariantsRepository

    internal val variants: MutableLiveData<BaseResponse>
        get() = variantsRepository.variants

    init {
        this.variantsRepository = VariantsRepository()
    }

}
