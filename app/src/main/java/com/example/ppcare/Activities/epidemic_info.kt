package com.example.ppcare.Activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.ppcare.Adapter.ViewPagerAdapter
import com.example.ppcare.Methods.sqlEpid
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_edit_vo.*
import kotlinx.android.synthetic.main.activity_epidemic_info.*
import org.w3c.dom.Text


class epidemic_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_epidemic_info)
        viewPager.adapter=ViewPagerAdapter(this)

        window.apply {
            decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }
        viewPager.setPageTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                page.scaleY = 1 - (0.15f * Math.abs(position))
                // position: -1  0  1
                val bac=page.findViewById<ImageView>(R.id.blur_bac)
                bac.visibility=View.VISIBLE
                if(position==1f||position==-1f){
                    bac.visibility=View.VISIBLE
                }
                else{
                    bac.visibility=View.GONE
                }

            }
        })
        //緩存頁面
        viewPager.offscreenPageLimit=3
        viewPager.setCurrentItem(1,false)
        var cardView= arrayOf(dot1,dot2,dot3,dot4,dot5)
        newsText.text =sqlEpid.getArticle(this@epidemic_info,this@epidemic_info,1)
        CustomEllipsize(newsText, 2, "......more>>")
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var curPos=position
                for(i in 0 until  cardView.count()){
                    cardView[i].setCardBackgroundColor(Color.GRAY)
                }
                if(position==0){
                    curPos=5
                    viewPager.setCurrentItem(5,false)
                    cardView[4].setCardBackgroundColor(Color.WHITE)
                }
                if(position==6){
                    curPos=1
                    viewPager.setCurrentItem(1,false)
                    cardView[0].setCardBackgroundColor(Color.WHITE)
                }
                if(position in 1..5){
                    cardView[position-1].setCardBackgroundColor(Color.WHITE)
                }
                try {
                    newsText.text =sqlEpid.getArticle(this@epidemic_info,this@epidemic_info,curPos)
                    CustomEllipsize(newsText, 2, "......more>>")

                }catch (ex:java.lang.Exception){}
            }
        })
        btn_back.setOnClickListener {
            finish()
        }

    }

    fun CustomEllipsize(tv: TextView, maxLine: Int, ellipsizetext: String) {
        //當觸摸模式發生改變
        tv.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                try{
                    var index=tv.layout.getLineEnd(maxLine-1)-(ellipsizetext.length/1.7).toInt()
                    var text=tv.text.substring(0,index)+ellipsizetext
                    tv.text=text
                }catch (ex:java.lang.Exception){}
                //為了避免多次觸發
                tv.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
    }
}