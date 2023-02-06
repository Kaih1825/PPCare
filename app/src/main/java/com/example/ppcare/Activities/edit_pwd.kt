package com.example.ppcare.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edit_pwd.*

class edit_pwd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pwd)
        window.apply {
            decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
        back.setOnClickListener {
            finish()
        }
        edt_pwd1.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var regex1="""(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,13}$""".toRegex()
                if(!regex1.containsMatchIn(edt_pwd1.text.toString())){
                    edt_pwd1.error="密碼格式有誤\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        edt_pwd2.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(edt_pwd1.text.toString()!=edt_pwd2.text.toString()){
                    edt_pwd2.error="兩者密碼不同\n\n\n"
                    return true
                }
                return false
            }

        })
    }
}