package com.example.ppcare.Activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout.LayoutParams
import com.example.ppcare.CustomViews.CustomMediaController
import com.example.ppcare.Methods.sqlEdu
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_video_player.*

class video_player : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        var uri= Uri.parse(intent.getStringExtra("uri"))
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        videoView.setVideoURI(uri)
        var mediaController= CustomMediaController(this,this,info)
        videoView.setMediaController(mediaController)
        videoView.setOnPreparedListener {
            mediaController.setAnchorView(videoView)
        }
        txt_title.text=intent.getStringExtra("title")
        txt_date.text=intent.getStringExtra("date")
        videoView.start()
    }
}