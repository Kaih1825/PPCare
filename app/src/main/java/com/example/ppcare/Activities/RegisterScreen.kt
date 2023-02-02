package com.example.ppcare.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.WindowDecorActionBar
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_login.*

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        window.apply {
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
    }
}