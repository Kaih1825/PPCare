package com.example.ppcare.CustomViews

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edu_info.*

class CustomMediaController(context:Context,var activity:Activity,var layoout:View):MediaController(context) {
    var isFull=false
    override fun hide() {
        super.hide()
    }

    override fun show() {
        super.show()
    }

    override fun setAnchorView(view: View?) {
        super.setAnchorView(view)
        var layout= LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
        var button=addFullButton(view)
        layout.gravity=Gravity.RIGHT or Gravity.TOP
        layout.setMargins(0,20,170,12)
        addView(button,layout)
        button=addStopButton(view)
        var layout2= LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
        layout2.gravity=Gravity.LEFT or Gravity.TOP
        layout2.setMargins(170,20,0,12)
        addView(button,layout2)
    }

    fun addFullButton(view: View?):View{
        var fullScreen=ImageButton(context)
        fullScreen.setImageResource(R.drawable.baseline_fullscreen_24)
        fullScreen.setBackgroundColor(Color.TRANSPARENT)
        fullScreen.setOnClickListener{
            if(isFull){
                Log.e("TAG", "TRUE", )
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                isFull=false
            }
            else{
                Log.e("TAG", "FALSE", )
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                isFull=true
            }
        }
        return fullScreen
    }

    fun addStopButton(view: View?):View{
        var stop=ImageButton(context)
        stop.setImageResource(R.drawable.baseline_stop_24)
        stop.setBackgroundColor(Color.TRANSPARENT)
        stop.setOnClickListener {
            if(view!=null){
                var videoView=view as VideoView
                videoView.stopPlayback()
                videoView.resume()
            }
        }
        return stop
    }
}