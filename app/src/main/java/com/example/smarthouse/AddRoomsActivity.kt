package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddRoomsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rooms)
    }

    fun Back(view: View){
        val intent = Intent(this@AddRoomsActivity, AllRoomsActivity::class.java)
        startActivity(intent)
    }
}