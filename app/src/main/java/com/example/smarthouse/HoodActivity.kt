package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hood)
    }

    fun Back (view: View){
        val intent = Intent(this@HoodActivity, DeviceActivity::class.java)
        startActivity(intent)
    }

}