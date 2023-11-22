package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CreatePinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
    }
    fun onClick (view:View){
        val intent = Intent(this@CreatePinActivity, PinActivity::class.java)
        startActivity(intent)
    }
}