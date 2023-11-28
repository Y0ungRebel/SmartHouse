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
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.Exception

class AllRoomsActivity : AppCompatActivity() {

    private lateinit var userAddress: TextView
    private lateinit var profile: ImageView

    val supabase = createSupabaseClient(
        supabaseUrl = "https://bykxgvzixlrojlvuswuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJ5a3hndnppeGxyb2psdnVzd3V1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA3MzQ5MzgsImV4cCI6MjAxNjMxMDkzOH0.MgFymK0lL3uein5ROiyXcp9z_HAV4DMuszg82qyf1do"
    ) {
        install(GoTrue)
        install(Postgrest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_rooms)
        userAddress = findViewById(R.id.userAddress)
        profile = findViewById(R.id.btn_settings)


        lifecycleScope.launch {
            try {
                val user = supabase.gotrue.retrieveUserForCurrentSession(updateSession = true)
                val addressResp = supabase.postgrest["Users"].select(columns = Columns.list("address")){
                    eq("id", user.id)
                }.body.toString()
                val array = JSONArray(addressResp)
                val obj = array.getJSONObject(0)
                val address = obj.getString("address")
                userAddress.setText(address)
            } catch (e: Exception){
                Log.e("Error", e.toString())
            }
        }
    }

    fun Profile(view: View){
        val intent = Intent(this@AllRoomsActivity, ProfileActivity::class.java)
        startActivity(intent)
    }
}