package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TemperatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
    }

    fun Back (view: View){
        val intent = Intent(this@TemperatureActivity, DeviceActivity::class.java)
        startActivity(intent)
    }

}