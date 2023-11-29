package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddDevicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_devices)
    }

    fun Back (view: View){
        val intent = Intent(this@AddDevicesActivity, DeviceActivity::class.java)
        startActivity(intent)
    }
}