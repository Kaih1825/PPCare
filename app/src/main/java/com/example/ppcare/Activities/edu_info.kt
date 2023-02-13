package com.example.ppcare.Activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.ImageColumns
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.example.ppcare.Methods.sqlEdu
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edu_info.*


class edu_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edu_info)

        btn_back.setOnClickListener {
            finish()
        }

        window.apply {
            decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
        sqlEdu.init(this)
        var uri="android.resource://"+getPackageName()+"/"+R.raw.video
        video1.setOnClickListener{
            var i= Intent(this, video_player::class.java)
            i.putExtra("uri",uri)
            i.putExtra("title","防疫大作戰 x 奧運羽球國手 - 王齊麟、李洋")
            i.putExtra("date","2023/02/10")
            startActivity(i)
        }
        var uri2="android.resource://"+getPackageName()+"/"+R.raw.video2
        video2.setOnClickListener {
            var i= Intent(this, video_player::class.java)
            i.putExtra("uri",uri2)
            i.putExtra("title","防疫大作戰-Continuing preventive measures after vaccination(張厚台醫師，打完疫苗後 還是要做好防疫，英語)")
            i.putExtra("date","2023/02/10")
            startActivity(i)
        }
        video3.setOnClickListener {
            var uri="android.resource://"+packageName+"/"+R.raw.video3
            var i= Intent(this, video_player::class.java)
            i.putExtra("uri",uri)
            i.putExtra("title","關於防疫，你可能比國手更厲害")
            i.putExtra("date","2023/02/10")
            startActivity(i)
        }
        var starArray= arrayOf(star1,star2,star3)
        var isStarArray= sqlEdu.getIsStar(this)
        Log.e("TAG", "${isStarArray[0]},${isStarArray[1]},${isStarArray[2]}", )
        for(i in 0 until 3){
            starArray[i].setOnClickListener {
                if(isStarArray[i]){
                    isStarArray[i]=false
                    ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.WHITE))
                    sqlEdu.updateStart(this,i+1,0)
                }else{
                    isStarArray[i]=true
                    ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.YELLOW))
                    sqlEdu.updateStart(this,i+1,1)
                }
            }
            if(isStarArray[i]){
                ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.YELLOW))
            }else{
                ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.WHITE))
            }
        }

        btn_star.setOnClickListener {
            var intent=Intent(this,edu_star::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var starArray= arrayOf(star1,star2,star3)
        var isStarArray= sqlEdu.getIsStar(this)
        for(i in 0 until 3){
            if(isStarArray[i]){
                ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.YELLOW))
            }else{
                ImageViewCompat.setImageTintList(starArray[i], ColorStateList.valueOf(Color.WHITE))
            }
        }
    }
}