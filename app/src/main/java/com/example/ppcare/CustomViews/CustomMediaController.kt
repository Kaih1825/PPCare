package com.example.ppcare.CustomViews

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
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

//        for (index in 0 until childCount) {
//            val nextChild = getChildAt(index)
//            if (nextChild is LinearLayout) {
//                val linearLayout = nextChild as LinearLayout
//                for (i in 0 until linearLayout.childCount) {
//                    if (i == 0 && linearLayout.getChildAt(i) is LinearLayout) {
//                        val linearLayoutControlPanel = linearLayout.getChildAt(i) as LinearLayout
//                        val len = linearLayoutControlPanel.childCount
//                        if (len > 0) {
//                            // index = 0, pre button, just hide it
//                            val buttonPre = linearLayoutControlPanel.getChildAt(0)
//                            var videoView2=view as VideoView
//                            buttonPre.setOnClickListener {
//                                videoView2.seekTo(videoView2.currentPosition-10000)
//                            }
//                            // index = len - 1, next button, change icon to fullscreen
//                            val buttonNex = linearLayoutControlPanel.getChildAt(len - 1)
//                            buttonNex.setOnClickListener {
//                                videoView2.seekTo(videoView2.currentPosition+10000)
//                            }
//                        }
//                    }
//                }
//            }
//        }


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