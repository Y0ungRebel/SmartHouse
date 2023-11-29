package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DeviceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
    }

    fun AddDevices (view: View){
        val intent = Intent(this@DeviceActivity, AddDevicesActivity::class.java)
        startActivity(intent)
    }

    fun Back (view: View){
        val intent = Intent(this@DeviceActivity, AllRoomsActivity::class.java)
        startActivity(intent)
    }

    fun Light (view: View){
        val intent = Intent(this@DeviceActivity, LightActivity::class.java)
        startActivity(intent)
    }

    fun Temperature (view: View){
        val intent = Intent(this@DeviceActivity, TemperatureActivity::class.java)
        startActivity(intent)
    }

    fun Cond (view: View){
        val intent = Intent(this@DeviceActivity, CondActivity::class.java)
        startActivity(intent)
    }

    fun Fan (view: View){
        val intent = Intent(this@DeviceActivity, FanActivity::class.java)
        startActivity(intent)
    }

    fun Hood (view: View){
        val intent = Intent(this@DeviceActivity, HoodActivity::class.java)
        startActivity(intent)
    }
}