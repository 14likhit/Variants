package com.likhit.variants.data.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Variation(@field:SerializedName("name")
                val name: String, @field:SerializedName("default")
                val defaultz: Int?, @field:SerializedName("id")
                val id: String, @field:SerializedName("logo")
                val logo: String) : Serializable
