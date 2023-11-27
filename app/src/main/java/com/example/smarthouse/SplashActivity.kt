package com.example.smarthouse


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGHT = 3000
    private val MY_SETTINGS = "my_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val sp = getSharedPreferences(
                MY_SETTINGS,
                MODE_PRIVATE
            )
            val hasVisited = sp.getBoolean("hasVisited", false)
            if (!hasVisited) {
                val e = sp.edit()
                e.putBoolean("hasVisited", true)
                e.apply()
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                this@SplashActivity.startActivity(i)
                this@SplashActivity.finish()
            } else {
                    val i = Intent(this@SplashActivity, MainActivity::class.java)
                    this@SplashActivity.startActivity(i)
                    this@SplashActivity.finish()
            }
        }, SPLASH_DISPLAY_LENGHT.toLong())
    }


//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//            Handler(Looper.getMainLooper()).postDelayed({
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }, 3000)
//    }
}