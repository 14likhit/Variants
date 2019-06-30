package com.likhit.variants.data.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ExcludeItem(@field:SerializedName("group_id")
                  val groupId: String, @field:SerializedName("variation_id")
                  val variationId: String) : Serializable
