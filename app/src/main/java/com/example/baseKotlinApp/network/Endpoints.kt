package com.example.baseKotlinApp.network

import com.example.baseKotlinApp.model.MakeUpItem
import retrofit2.http.GET

interface Endpoints {

    @GET("products.json?brand=maybelline")
    suspend fun getMakeUp(): List<MakeUpItem>


}