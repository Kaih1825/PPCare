package com.example.ppcare.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.ppcare.Adapter.ViewPagerAdapter
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_epidemic_info.*

class epidemic_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epidemic_info)
        viewPager.adapter=ViewPagerAdapter(this)
        viewPager.setPageTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                page.scaleY = 1 - (0.15f * Math.abs(position))
                // position: -1  0  1
            }
        })
        //緩存頁面
        viewPager.offscreenPageLimit=3
        viewPager.setCurrentItem(1,false)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position==0){
                    viewPager.setCurrentItem(5,false)
                }
                if(position==6){
                    viewPager.setCurrentItem(1,false)
                }
            }
        })
    }
}