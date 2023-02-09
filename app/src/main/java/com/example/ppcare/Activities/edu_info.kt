package com.example.ppcare.Activities

import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edu_info.*
import kotlinx.android.synthetic.main.activity_set_per_info.*


class edu_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edu_info)
        var uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video)
        videoView.setVideoURI(uri)
        var mediaController=MediaController(this)
        videoView.setMediaController(mediaController)
        videoView.setOnPreparedListener {
            mediaController.setAnchorView(videoView)
        }
        btn.setOnClickListener {
            videoView.stopPlayback()
        }
    }
}