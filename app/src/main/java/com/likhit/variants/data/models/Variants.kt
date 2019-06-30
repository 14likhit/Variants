package com.likhit.variants.data.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Variants : Serializable {

    @SerializedName("variant_groups")
    var variantGroups: List<VariantGroup>? = null

    @SerializedName("exclude_list")
    var excludeList: List<List<ExcludeItem>>? = null
        private set

    constructor(variants: Variants) {
        this.variantGroups = variants.variantGroups
        this.excludeList = variants.excludeList
    }

    constructor(variantGroups: List<VariantGroup>) {
        this.variantGroups = variantGroups
    }

    constructor(variantGroups: List<VariantGroup>, excludeList: List<List<ExcludeItem>>) {
        this.variantGroups = variantGroups
        this.excludeList = excludeList
    }
}
