package com.example.ppcare.Activitys

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.example.ppcare.Activities.HomeScreen
import com.example.ppcare.Activities.RegisterScreen
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.apply {
//            decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            statusBarColor=resources.getColor(R.color.transparent)
            setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        var tmp="";var tmpPwd=""
        var isShowing=false
        var notSuccess=0
        var sp=getSharedPreferences("login", MODE_PRIVATE)
        var spe=sp.edit()
        edt_account.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tmp=edt_account.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(edt_account.text.length>30){
                    edt_account.error = "字數將超過30字"
                    edt_account.setText(tmp)
                    edt_account.setSelection(edt_account.text.length)
                }
                var regex="""[\sA-Za-z0-9._-]+@[\sA-Za-z0-9._-]+\.[\sA-Za-z]{2,4}""".toRegex()
                if(!regex.containsMatchIn(edt_account.text)){
                    edt_account.error="格式錯誤"
                }
                regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_account.text)){
                    edt_account.error="含有空格且格式錯誤"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //TODO("Not yet implemented")
            }

        })
        edt_password.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                tmpPwd=edt_password.text.toString()

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(edt_password.text.length>15){
                    edt_password.error = "字數將超過15字"
                    edt_password.setText(tmpPwd)
                    edt_password.setSelection(edt_password.text.length)
                }
                var regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_password.text)){
                    edt_password.error = "含有空白"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //TODO("Not yet implemented")
            }

        })
        edt_password.setOnTouchListener(object : View.OnTouchListener{
            var drawable=edt_password.compoundDrawables[2]
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if(p1?.action==MotionEvent.ACTION_UP){
                    if(p1.x>edt_password.width-edt_password.paddingRight-drawable.intrinsicWidth){
                        if(isShowing){
                            isShowing=!isShowing
                            edt_password.transformationMethod=PasswordTransformationMethod()
                            edt_password.setCompoundDrawablesRelativeWithIntrinsicBounds(resources.getDrawable(R.drawable.a6xxxhdpi_113),null,resources.getDrawable(R.drawable.a6xxxhdpi_110),null)
                        }else{
                            isShowing=!isShowing
                            edt_password.transformationMethod= null
                            edt_password.setCompoundDrawablesRelativeWithIntrinsicBounds(resources.getDrawable(R.drawable.a6xxxhdpi_113),null,resources.getDrawable(R.drawable.a6xxxhdpi_96),null)
                        }
                    }
                }
                return false
            }

        })
        btn_login.setOnClickListener{
            if(edt_account.text.toString()=="abc123@mail.com"&&edt_password.text.toString()=="Asdf456!"){
                var intent=Intent(this,HomeScreen::class.java)
                startActivity(intent)
                finish()
                spe.putBoolean("isLogin",true)
                spe.apply()
            }
            else{
                notSuccess++
                txt_pwdError.setText("帳號或密碼錯誤")
                edt_password.setText("")
                if(notSuccess>=3){
                    object : CountDownTimer(10000,1000){
                        override fun onTick(i: Long) {
                            txt_pwdError.text = "請稍等${i/1000+1}秒再試"
                            btn_login.setCardBackgroundColor(Color.GRAY)
                            btn_login.isEnabled=false
                        }

                        override fun onFinish() {
                            txt_pwdError.text = ""
                            btn_login.setCardBackgroundColor(resources.getColor(R.color.deepBlue))
                            btn_login.isEnabled=true
                        }
                    }.start()
                }
            }
        }
        btn_reg.setOnClickListener {
            val intent=Intent(this,RegisterScreen::class.java)
            startActivity(intent)
        }
        layoutClick.setOnClickListener {
            edt_password.clearFocus()
            edt_account.clearFocus()
        }
    }
}