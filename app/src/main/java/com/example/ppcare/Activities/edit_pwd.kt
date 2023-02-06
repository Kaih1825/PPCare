package com.example.ppcare.Activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import coil.load
import coil.transform.BlurTransformation
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edit_pwd.*
import kotlinx.android.synthetic.main.activity_edit_pwd.back
import kotlinx.android.synthetic.main.activity_edit_pwd.blur_background
import kotlinx.android.synthetic.main.activity_edit_pwd.btn_con
import kotlinx.android.synthetic.main.activity_edit_pwd.reg_suc
import kotlinx.android.synthetic.main.activity_set_per_info.*

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
        btn_con.setOnClickListener {
            setBlurBackground(this,blur_background)
            blur_background.visibility=View.VISIBLE
            reg_suc.visibility=View.VISIBLE
            Handler().postDelayed({
                reg_suc.visibility=View.GONE
                blur_background.visibility=View.GONE
                finish()
            },2000)
        }
    }
    fun setBlurBackground(context: Context, imgView: ImageView){
        var screenShoot=window.decorView.drawToBitmap()
        var width=windowManager.defaultDisplay.width
        var height=windowManager.defaultDisplay.height
        var new= Bitmap.createBitmap(screenShoot,0,0,width,height)
        imgView.load(new){
            transformations(
                BlurTransformation(
                context,
                radius = 25f,
                sampling = 5f
            )
            )
        }
    }
}