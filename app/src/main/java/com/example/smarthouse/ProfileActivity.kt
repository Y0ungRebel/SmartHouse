package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.Exception

class ProfileActivity : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var userMail: EditText
    private lateinit var userAddress: EditText

    val supabase = createSupabaseClient(
        supabaseUrl = "https://bykxgvzixlrojlvuswuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ5a3hndnppeGxyb2psdnVzd3V1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA3MzQ5MzgsImV4cCI6MjAxNjMxMDkzOH0.MgFymK0lL3uein5ROiyXcp9z_HAV4DMuszg82qyf1do"
    ) {
        install(GoTrue)
        install(Postgrest)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        userName = findViewById(R.id.name)
        userMail = findViewById(R.id.mail)
        userAddress = findViewById(R.id.address)

        lifecycleScope.launch {
            try {
                val user = supabase.gotrue.retrieveUserForCurrentSession(updateSession = true)

                supabase.gotrue.modifyUser {
                    userMail.setText(user.email)
                }

                val nameResponse = supabase.postgrest["Users"].select(columns = Columns.list("name")){
                    eq("id", user.id)
                }.body.toString()
                val arrayName = JSONArray(nameResponse)
                val objectName = arrayName.getJSONObject(0)
                val name = objectName.getString("name")
                userName.setText(name)


                val addressResponse = supabase.postgrest["Users"].select(columns = Columns.list("address")){
                    eq("id", user.id)
                }.body.toString()
                val arrayAddress = JSONArray(addressResponse)
                val objectAddress = arrayAddress.getJSONObject(0)
                val address = objectAddress.getString("address")
                userAddress.setText(address)
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    fun Save(view: View){
        lifecycleScope.launch {
            try {
                //обновление имени
                val user = supabase.gotrue.retrieveUserForCurrentSession(updateSession = true)
                supabase.postgrest["Users"].update(
                    {
                        set ("name", userName.text.toString())
                    }
                ) {
                    eq ("id", user.id)
                }

                //обновление адреса
                supabase.postgrest["Users"].update(
                    {
                        set ("address", userAddress.text.toString())
                    }
                ) {
                    eq ("id", user.id)
                }

            } catch (e: Exception){
                //Log.e("Данные введены неправильно", e.toString())
            }
        }
    }

    fun Exit(view: View){
        val intent = Intent(this@ProfileActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun Back(view: View){
        val intent = Intent(this@ProfileActivity, AllRoomsActivity::class.java)
        startActivity(intent)
    }

}