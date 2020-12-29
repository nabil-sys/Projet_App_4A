package com.example.android4a.injection

import android.accessibilityservice.GestureDescription
import android.content.SharedPreferences
import com.example.android4a.data.local.ExoApi

import android.content.Context;
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Singletons {
    private var gsonInstance: Gson? = null
    private var exoApiInstance: ExoApi? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }

    val exoApi: ExoApi?
        @RequiresApi(Build.VERSION_CODES.N)
        get() {
            if (exoApiInstance == null) {
                //val retrofit: Retrofit = GestureDescription.Builder()
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                exoApiInstance = retrofit.create(ExoApi::class.java)
            }
            return exoApiInstance
        }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance =
                context.getSharedPreferences("application_mobile", Context.MODE_PRIVATE)
        }
        return sharedPreferencesInstance
    }
}