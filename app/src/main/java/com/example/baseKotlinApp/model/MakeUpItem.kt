package com.example.baseKotlinApp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MakeUpItem(
    @SerializedName("brand")
    val brandName: String,
    @SerializedName("api_featured_image")
    val imageUrl: String,
    @SerializedName("name")
    val modelName: String,
    @SerializedName("created_at")
    val modelYear: String,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("price")
    val price: String
) : Serializable