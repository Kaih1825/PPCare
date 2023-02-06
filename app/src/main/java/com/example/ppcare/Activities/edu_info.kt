package com.example.ppcare.Activities

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import coil.load
import coil.transform.BlurTransformation
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_set_per_info.*

class edu_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edu_info)
    }
}