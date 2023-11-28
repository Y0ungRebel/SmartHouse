package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import java.lang.Exception

class AddressActivity : AppCompatActivity() {

    private lateinit var editAddress: EditText

    val supabase = createSupabaseClient(
        supabaseUrl = "https://bykxgvzixlrojlvuswuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ5a3hndnppeGxyb2psdnVzd3V1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA3MzQ5MzgsImV4cCI6MjAxNjMxMDkzOH0.MgFymK0lL3uein5ROiyXcp9z_HAV4DMuszg82qyf1do"
    ) {
        install(GoTrue)
        install(Postgrest)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        editAddress = findViewById(R.id.newAddress)

    }
    fun onClick (view: View){
        val addressValue = editAddress.getText().toString()
        val addressPattern = "[г|Г]. [А-Яа-я]+, [у|У]л. [А-Яа-я]+, [д|Д]. [0-9]+"
        if (addressValue.matches(addressPattern.toRegex()) && (!(editAddress.text.isEmpty()))) {
            lifecycleScope.launch {
                try {
                    val user = supabase.gotrue.retrieveUserForCurrentSession(updateSession = true)
                    supabase.postgrest["Users"].update(
                        {
                            set ("address", editAddress.text.toString())
                        }
                    ) {
                        eq ("id", user.id)
                    }

                    val intent = Intent(this@AddressActivity, AllRoomsActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception){
                    //Log.e("Данные введены неправильно", e.toString())
                }
            }

        } else if (!addressValue.matches(addressPattern.toRegex())) {
            Toast.makeText(this, "Адрес введен неверно!", Toast.LENGTH_SHORT).show()
        }
        else if (editAddress.text.isEmpty()) {
            Toast.makeText(this, "Введите адрес!", Toast.LENGTH_SHORT).show()
        }
    }
}