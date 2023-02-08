package com.example.ppcare.Adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ppcare.R


class ViewPagerAdapter(val activity: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView:ImageView
        val blurBackground:ImageView
        init {
            imageView=view.findViewById(R.id.imageView)
            blurBackground=view.findViewById(R.id.blur_bac)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.view_pager_image,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 7
    }
    val images= arrayListOf(R.drawable.news05,R.drawable.news01,R.drawable.news02,R.drawable.news03,R.drawable.news04,R.drawable.news05,R.drawable.news01)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val v=holder as ViewHolder
        v.apply {
            imageView.setImageDrawable(activity.resources.getDrawable(images[position]))
            blurBackground.visibility=View.VISIBLE
        }
    }
}