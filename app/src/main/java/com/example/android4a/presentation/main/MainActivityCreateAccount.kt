package com.example.android4a.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android4a.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_create_account.*
import org.koin.android.ext.android.inject


class MainActivityCreateAccount : AppCompatActivity(){
    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_create_account)

        create_button2.setOnClickListener{
            mainViewModel.onClickedCreate(login_edit2.text.toString().trim(), password_edit2.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}