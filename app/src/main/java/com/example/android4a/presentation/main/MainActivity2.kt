package com.example.android4a.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.data.local.models.ExerciceImage
import com.example.android4a.injection.Singletons.getSharedPreferences
import com.example.android4a.injection.Singletons.gson

class MainActivity2 : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: ListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var controller: MainController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        controller = gson?.let {
            MainController(
                this,
                it,
                getSharedPreferences(applicationContext)!!
            )
        }
        controller!!.onStart()
    }

    fun showList(exerciceImageList: List<ExerciceImage?>?) {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        // define an adapter
        mAdapter = ListAdapter(exerciceImageList as List<ExerciceImage>, object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: ExerciceImage?) {
                controller!!.onItemClick(item)
            }
        })
        recyclerView!!.adapter = mAdapter
    }

    fun showError() {
        Toast.makeText(applicationContext, "API Error", Toast.LENGTH_SHORT).show()
    }

    fun navigateToDetails(exerciceImage: ExerciceImage?) {
        val myIntent = Intent(this@MainActivity2, DetailActivity::class.java)
        myIntent.putExtra("exerciceImageKey", gson?.toJson(exerciceImage))
        this@MainActivity2.startActivity(myIntent)
    }
}