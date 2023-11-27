package com.example.smarthouse

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText

    val supabase = createSupabaseClient(
        supabaseUrl = "https://bykxgvzixlrojlvuswuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ5a3hndnppeGxyb2psdnVzd3V1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA3MzQ5MzgsImV4cCI6MjAxNjMxMDkzOH0.MgFymK0lL3uein5ROiyXcp9z_HAV4DMuszg82qyf1do"
    ) {
        install(GoTrue)
        install(Postgrest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editEmail = findViewById(R.id.login)
        editPassword = findViewById(R.id.password)
    }
    fun Registration (view:View){
        val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
        startActivity(intent)
    }

    fun Auth(view:View){
        lifecycleScope.launch {
            try {
                supabase.gotrue.loginWith(Email) {
                    email = editEmail.getText().toString()
                    password = editPassword.getText().toString()
                }
                val intent = Intent(this@MainActivity, PinActivity::class.java)
                startActivity(intent)
            } catch (e:Exception){
                //Log.e("Данные введены неправильно", e.toString())
                Toast.makeText(this@MainActivity, "Проверьте правильность введенных данных", Toast.LENGTH_SHORT).show()
            }
        }
    }
}