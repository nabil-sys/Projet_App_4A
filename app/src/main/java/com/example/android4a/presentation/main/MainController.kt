package com.example.android4a.presentation.main

import android.content.SharedPreferences
import com.example.android4a.data.local.models.ExerciceImage
import com.example.android4a.data.local.models.RestExerciceImageResponse
import com.example.android4a.injection.Constants
import com.example.android4a.injection.Singletons
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type



class MainController(
    private val view: MainActivity2,
    gson: Gson,
    sharedPreferences: SharedPreferences
) {
    private val sharedPreferences: SharedPreferences
    private val gson: Gson
    fun onStart() {
        val exerciceImageList: List<ExerciceImage>? = dataFromCache as List<ExerciceImage>?
        if (exerciceImageList != null) {
            view.showList(exerciceImageList)
        } else {
            makeApiCall()
        }
    }

    private fun makeApiCall() {
        val call: Call<RestExerciceImageResponse?>? =
            Singletons.exoApi?.exerciceImageResponse
        call?.enqueue(object : Callback<RestExerciceImageResponse?> {
            override fun onResponse(
                call: Call<RestExerciceImageResponse?>?,
                response: Response<RestExerciceImageResponse?>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    val exerciceImageList: List<ExerciceImage>? =
                        response.body()!!.results
                    if (exerciceImageList != null) {
                        saveList(exerciceImageList)
                    }
                    view.showList(exerciceImageList)
                } else {
                    view.showError()
                }
            }

            override fun onFailure(
                call: Call<RestExerciceImageResponse?>?,
                t: Throwable?
            ) {
                view.showError()
            }
        })
    }

    private fun saveList(exerciceImageList: List<ExerciceImage>) {
        val jsonString: String = gson.toJson(exerciceImageList)
        sharedPreferences
            .edit()
            .putString(Constants.KEY_EXERCICEIMAGE_LIST, jsonString)
            .apply()
    }

    private val dataFromCache: List<Any>?
        private get() {
            val jsonExerciceImage =
                sharedPreferences.getString(Constants.KEY_EXERCICEIMAGE_LIST, null)
            return if (jsonExerciceImage == null) {
                null
            } else {
                val listType: Type =
                    object : TypeToken<List<ExerciceImage?>?>() {}.getType()
                gson.fromJson(jsonExerciceImage, listType)
            }
        }

    fun onItemClick(exerciceImage: ExerciceImage?) {
        view.navigateToDetails(exerciceImage)
    }

    fun onButtonAClick() {}

    init {
        this.gson = gson
        this.sharedPreferences = sharedPreferences
    }
}