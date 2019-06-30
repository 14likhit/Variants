package com.likhit.variants.data.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class VariantGroup(@field:SerializedName("group_id")
                   val groupId: String, @field:SerializedName("name")
                   val name: String, @field:SerializedName("variations")
                   var variations: List<Variation>?) : Serializable
