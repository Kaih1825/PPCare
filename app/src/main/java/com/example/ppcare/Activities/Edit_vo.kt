package com.example.ppcare.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ppcare.Methods.sql
import com.example.ppcare.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import kotlinx.android.synthetic.main.activity_edit_vo.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.SimpleTimeZone

class edit_vo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vo)
        window.apply {
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
        var position=intent.getIntExtra("pos",-1)
        var datePicker=MaterialDatePicker.Builder.datePicker().build()
        btn_date.setOnClickListener {
            datePicker.show(supportFragmentManager,null)
        }
        datePicker.addOnPositiveButtonClickListener{
            txt_date.text=SimpleDateFormat("yyyy-MM-dd").format(datePicker.selection)
        }
        var timePicker=MaterialTimePicker.Builder().build()
        btn_time.setOnClickListener {
            timePicker.show(supportFragmentManager,null)
        }
        back.setOnClickListener {
            finish()
        }
        timePicker.addOnPositiveButtonClickListener {
            var hour=timePicker.hour.toString()
            var min=timePicker.minute.toString()
            if(hour.length==1){
                hour= "0$hour"
            }
            if(min.length==1){
                min= "0$min"
            }
            txt_time.text="${hour}:${min}"
        }

        btn_con.setOnClickListener {
            sql.update(this,position,spn_type.selectedItem.toString(),spn_place.selectedItem.toString(),txt_date.text.toString(),txt_time.text.toString())
            finish()
        }
    }
}