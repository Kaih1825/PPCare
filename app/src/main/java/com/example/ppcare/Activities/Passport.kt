package com.example.ppcare.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppcare.Methods.sql
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_passport.*

class passport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passport)
        btn_edt1.setOnClickListener {
            var intent=Intent(this, edit_vo::class.java)
            intent.putExtra("pos",1)
            startActivity(intent)
        }
        btn_edt2.setOnClickListener {
            var intent=Intent(this, edit_vo::class.java)
            intent.putExtra("pos",2)
            startActivity(intent)
        }
        btn_edt3.setOnClickListener {
            var intent=Intent(this, edit_vo::class.java)
            intent.putExtra("pos",3)
            startActivity(intent)
        }
        btn_edt4.setOnClickListener {
            var intent=Intent(this, edit_vo::class.java)
            intent.putExtra("pos",4)
            startActivity(intent)
        }
        var array= sql.get(this,1)
        txt_type1.text=array[0]
        txt_date1.text=array[2]+" "+array[3]
        txt_place1.text=array[1]
        if(array[1]==""){
            txt_status1.text="未施打"
        }
        array= sql.get(this,2)
        txt_type2.text=array[0]
        txt_date2.text=array[2]+" "+array[3]
        txt_place2.text=array[1]
        if(array[1]==""){
            txt_status2.text="未施打"
        }
        array= sql.get(this,3)
        txt_type3.text=array[0]
        txt_date3.text=array[2]+" "+array[3]
        txt_place3.text=array[1]
        if(array[1]==""){
            txt_status3.text="未施打"
        }
        array= sql.get(this,4)
        txt_type4.text=array[0]
        txt_date4.text=array[2]+" "+array[3]
        txt_place4.text=array[1]
        if(array[1]==""){
            txt_status4.text="未施打"
        }
    }

    override fun onRestart() {
        super.onRestart()
        var array= sql.get(this,1)
        txt_type1.text=array[0]
        txt_date1.text=array[2]+" "+array[3]
        txt_place1.text=array[1]
        array= sql.get(this,2)
        txt_type2.text=array[0]
        txt_date2.text=array[2]+" "+array[3]
        txt_place2.text=array[1]
        array= sql.get(this,3)
        txt_type3.text=array[0]
        txt_date3.text=array[2]+" "+array[3]
        txt_place3.text=array[1]
        array= sql.get(this,4)
        txt_type4.text=array[0]
        txt_date4.text=array[2]+" "+array[3]
        txt_place4.text=array[1]
    }
}