package com.example.smarthouse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PinActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_pin)

        ind_1st = findViewById(R.id.first_indicator)
        ind_2nd = findViewById(R.id.second_indicator)
        ind_3rd = findViewById(R.id.third_indicator)
        ind_4th = findViewById(R.id.fourth_indicator)

        //txt = findViewById(R.id.text_house)


        //txt.setText(correctPin)
    }

    fun onClick(view: View){

        val preferences = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
        val correctPin = preferences.getString(APP_PREFERENCES_PIN, "")

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

                if (value == correctPin){
                    val intent = Intent(this@PinActivity, AddressActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this@PinActivity, "Пин-код неверный!", Toast.LENGTH_SHORT).show()
//                    value = ""
//                    ind_1st.setImageResource(R.drawable.empty_ellipse)
//                    ind_2nd.setImageResource(R.drawable.empty_ellipse)
//                    ind_3rd.setImageResource(R.drawable.empty_ellipse)
//                    ind_4th.setImageResource(R.drawable.empty_ellipse)
//                    return
                }

            }
        }
    }
}