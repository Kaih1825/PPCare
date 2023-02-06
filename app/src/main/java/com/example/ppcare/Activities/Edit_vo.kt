package com.example.ppcare.Activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import coil.load
import coil.transform.BlurTransformation
import com.example.ppcare.Methods.sql
import com.example.ppcare.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import kotlinx.android.synthetic.main.activity_edit_pwd.*
import kotlinx.android.synthetic.main.activity_edit_vo.*
import kotlinx.android.synthetic.main.activity_edit_vo.back
import kotlinx.android.synthetic.main.activity_edit_vo.blur_background
import kotlinx.android.synthetic.main.activity_edit_vo.btn_con
import kotlinx.android.synthetic.main.activity_edit_vo.reg_suc
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.SimpleTimeZone

class edit_vo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vo)
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT
        }
        var position = intent.getIntExtra("pos", -1)
        var datePicker = MaterialDatePicker.Builder.datePicker().build()
        btn_date.setOnClickListener {
            datePicker.show(supportFragmentManager, null)
        }
        datePicker.addOnPositiveButtonClickListener {
            txt_date.text = SimpleDateFormat("yyyy-MM-dd").format(datePicker.selection)
        }
        var timePicker = MaterialTimePicker.Builder().build()
        btn_time.setOnClickListener {
            timePicker.show(supportFragmentManager, null)
        }
        back.setOnClickListener {
            finish()
        }
        timePicker.addOnPositiveButtonClickListener {
            var hour = timePicker.hour.toString()
            var min = timePicker.minute.toString()
            if (hour.length == 1) {
                hour = "0$hour"
            }
            if (min.length == 1) {
                min = "0$min"
            }
            txt_time.text = "${hour}:${min}"
        }

        btn_con.setOnClickListener {
            sql.update(
                this,
                position,
                spn_type.selectedItem.toString(),
                spn_place.selectedItem.toString(),
                txt_date.text.toString(),
                txt_time.text.toString()
            )
            setBlurBackground(this, blur_background)
            blur_background.visibility = View.VISIBLE
            reg_suc.visibility = View.VISIBLE
            Handler().postDelayed({
                reg_suc.visibility = View.GONE
                blur_background.visibility = View.GONE
                finish()
            }, 2000)
        }
    }

    fun setBlurBackground(context: Context, imgView: ImageView) {
        var screenShoot = window.decorView.drawToBitmap()
        var width = windowManager.defaultDisplay.width
        var height = windowManager.defaultDisplay.height
        var new = Bitmap.createBitmap(screenShoot, 0, 0, width, height)
        imgView.load(new) {
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