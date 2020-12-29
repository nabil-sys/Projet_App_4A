package com.example.android4a.data.local


import com.example.android4a.data.local.models.RestExerciceImageResponse
import retrofit2.http.GET


interface ExoApi {
    @get:GET("/api/v2/exerciseimage/")
    val exerciceImageResponse: retrofit2.Call<RestExerciceImageResponse?>?
}