package com.example.ppcare.Activitys

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.ppcare.Activities.HomeScreen
import com.example.ppcare.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            val sp=getSharedPreferences("login", MODE_PRIVATE)
            if(sp.getBoolean("isLogin",false)){
                val intent=Intent(this,HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent=Intent(this,login::class.java)
                startActivity(intent)
                finish()
            }

        },2000)
    }
}