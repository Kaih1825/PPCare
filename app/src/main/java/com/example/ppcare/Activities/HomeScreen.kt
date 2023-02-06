package com.example.ppcare.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.ppcare.Activitys.login
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        btn_vo.setOnClickListener{
            var intent=Intent(this,passport::class.java)
            startActivity(intent)
        }
        btn_pcr.setOnClickListener{
            var intent=Intent(this,pcr::class.java)
            startActivity(intent)
        }
        btn_epid.setOnClickListener {
            var intent=Intent(this,epidemic_info::class.java)
            startActivity(intent)
        }
        btn_edu.setOnClickListener {
            var intent=Intent(this,edu_info::class.java)
            startActivity(intent)
        }

        btn_setting.setOnClickListener {
            var intent=Intent(this,setting::class.java)
            startActivity(intent)
        }
    }
}