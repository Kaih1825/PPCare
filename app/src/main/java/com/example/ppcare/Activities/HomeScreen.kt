package com.example.ppcare.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppcare.Activitys.login
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        btn_logout.setOnClickListener {
            var spe=getSharedPreferences("login", MODE_PRIVATE).edit()
            spe.putBoolean("isLogin",false)
            spe.apply()
            val intent= Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }
    }
}