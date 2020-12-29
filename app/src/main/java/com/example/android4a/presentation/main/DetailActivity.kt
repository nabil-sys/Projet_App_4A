package com.example.android4a.presentation.main


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android4a.R
import com.example.android4a.data.local.models.ExerciceImage
import com.example.android4a.injection.Singletons
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity() {
    private var txtDetail: TextView? = null
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       txtDetail = findViewById(R.id.detail_txt)
        val intent = intent
        val exerciceImageJson = intent.getStringExtra("exerciceImageKey")
        val exerciceImage: ExerciceImage? = Singletons.gson?.fromJson(
            exerciceImageJson,
            ExerciceImage::class.java
        )
        if (exerciceImage != null) {
            showDetail(exerciceImage)
        }
       imageView = findViewById<View>(R.id.imageView) as ImageView
        if (exerciceImage != null) {
            loadImageFromUrl(exerciceImage)
        }
    }

    private fun loadImageFromUrl(exerciceImage: ExerciceImage) {
        Picasso.with(this).load(exerciceImage.image).placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView, object : Callback {
                override fun onSuccess() {}
                override fun onError() {}
            })
    }

    private fun showDetail(exerciceImage: ExerciceImage) {
        txtDetail!!.text = exerciceImage.image
    }
}
