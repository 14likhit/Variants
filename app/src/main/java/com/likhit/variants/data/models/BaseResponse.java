package com.likhit.variants.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    @SerializedName("variants")
    private Variants variants;

    public BaseResponse(Variants variants) {
        this.variants = variants;
    }

    public BaseResponse(BaseResponse response) {
        this.variants = new Variants(response.getVariants());
    }

    public Variants getVariants() {
        return variants;
    }
}
