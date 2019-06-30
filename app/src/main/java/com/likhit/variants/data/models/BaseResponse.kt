package com.likhit.variants.data.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class BaseResponse : Serializable {

    @SerializedName("variants")
    var variants: Variants? = null
        private set

    constructor(variants: Variants) {
        this.variants = variants
    }

    constructor(response: BaseResponse) {
        this.variants = Variants(response.variants!!)
    }
}
