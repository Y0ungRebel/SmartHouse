package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cond)
    }

    fun Back(view: View){
        val intent = Intent(this@CondActivity, DeviceActivity::class.java)
        startActivity(intent)
    }

}