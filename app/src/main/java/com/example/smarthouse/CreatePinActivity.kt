package com.example.smarthouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class CreatePinActivity : AppCompatActivity(){

    private val MY_SETTINGS = "my_settings"
    val APP_PREFERENCES_PIN = "passcode"
    var i = 0
    var value = ""

    private lateinit var ind_1st: ImageView
    private lateinit var ind_2nd: ImageView
    private lateinit var ind_3rd: ImageView
    private lateinit var ind_4th: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)

        ind_1st = findViewById(R.id.first_indicator)
        ind_2nd = findViewById(R.id.second_indicator)
        ind_3rd = findViewById(R.id.third_indicator)
        ind_4th = findViewById(R.id.fourth_indicator)
    }

    fun onClick(view: View){
        when (view.id) {
            R.id.first_btn -> value += "1"
            R.id.second_btn -> value += "2"
            R.id.third_btn -> value += "3"
            R.id.fourth_btn -> value += "4"
            R.id.fifth_btn -> value += "5"
            R.id.sixth_btn -> value += "6"
            R.id.seventh_btn -> value += "7"
            R.id.eighth_btn -> value += "8"
            R.id.ninth_btn -> value += "9"
        }
        i++
        when (i) {
            1 -> ind_1st.setImageResource(R.drawable.full_ellipse)
            2 -> ind_2nd.setImageResource(R.drawable.full_ellipse)
            3 -> ind_3rd.setImageResource(R.drawable.full_ellipse)
            4 -> {
                ind_4th.setImageResource(R.drawable.full_ellipse)
                val intent = Intent(this, AddressActivity::class.java)
                //intent.putExtra("key", value)

                val preferences = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString(APP_PREFERENCES_PIN, value)
                editor.apply()

                startActivity(intent)
            }
        }
    }
}