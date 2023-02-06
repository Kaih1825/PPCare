package com.example.ppcare.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.ppcare.Activitys.login
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_setting.*
import kotlin.math.log

class setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        window.apply {
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
        btn_back.setOnClickListener {
            finish()
        }
        btn_editInfo.setOnClickListener {
            startActivity(Intent(this,set_per_info::class.java))
        }
        btn_editPwd.setOnClickListener {
            startActivity(Intent(this,edit_pwd::class.java))
        }
        btn_logout.setOnClickListener {
            val sp=getSharedPreferences("login", MODE_PRIVATE).edit()
            sp.putBoolean("isLogin",false)
            sp.apply()
            val intent=Intent(this, login::class.java)
            startActivity(intent)
            // 結束當前Activity及所有父Activity
            finishAffinity()

        }
    }
}