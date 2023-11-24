package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editEmail = findViewById(R.id.mail)
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


}