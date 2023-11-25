package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Returning
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editName: EditText


    val supabase = createSupabaseClient(
        supabaseUrl = "https://bykxgvzixlrojlvuswuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ5a3hndnppeGxyb2psdnVzd3V1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA3MzQ5MzgsImV4cCI6MjAxNjMxMDkzOH0.MgFymK0lL3uein5ROiyXcp9z_HAV4DMuszg82qyf1do"
    ) {
        install(GoTrue)
        install(Postgrest)
        //install other modules
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editEmail = findViewById(R.id.mail)
        editPassword = findViewById(R.id.password)
        editName = findViewById(R.id.name)
    }

    fun onClick (view: View){
        //val intent = Intent(this@RegistrationActivity, CreatePinActivity::class.java)
        //startActivity(intent)

        val emailValue = editEmail.getText().toString()
        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"

        //preferences = getSharedPreferences("UserInfo", 0)

        if (emailValue.matches(emailPattern.toRegex())) {
            //val editor: SharedPreferences.Editor = preferences.edit()
            //editor.putString("email", emailValue)
            //editor.apply()
            Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CreatePinActivity::class.java)
            startActivity(intent)
        } else if (!emailValue.matches(emailPattern.toRegex())) {
            Toast.makeText(this, "Почта введена неверно или не введена вовсе!", Toast.LENGTH_SHORT).show()
        }
    }

    fun Registration(view: View){
        lifecycleScope.launch {
            try {
                supabase.gotrue.signUpWith(Email) {
                    email = editEmail.getText().toString()
                    password = editPassword.getText().toString()
                }

                val user = supabase.gotrue.retrieveUserForCurrentSession(updateSession = true)
                val users = DataUser(name = editName.text.toString(), id = user.id)
                supabase.postgrest["Users"].insert(users)//, returning = Returning.MINIMAL)

                val intent = Intent(this@RegistrationActivity, CreatePinActivity::class.java)
                startActivity(intent)
            } catch (e: Exception){
                //Log.e("Данные введены неправильно", e.toString())
                Toast.makeText(this@RegistrationActivity, "Проверьте правильность введенных данных", Toast.LENGTH_SHORT).show()
            }
        }
    }


}