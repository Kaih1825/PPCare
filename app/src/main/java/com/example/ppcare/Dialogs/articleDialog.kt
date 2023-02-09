package com.example.ppcare.Dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.ppcare.Methods.sqlEpid
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_epidemic_info.*
import kotlinx.android.synthetic.main.article_dailog.*

class articleDialog(context: Context,var activity:Activity,var id:Int): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_dailog)
        window!!.apply {
            setElevation(20f)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        title1.text=sqlEpid.getTitle(context, activity,id).substring(0,20)
        title2.text=sqlEpid.getTitle(context, activity,id).substring(20,sqlEpid.getTitle(context, activity,id).length)
        article.text=sqlEpid.getArticle(context, activity,id)
        var dateArray=sqlEpid.get(context,activity,id)[0].split('/')
        date.text="發布日期：${dateArray[2]}.${dateArray[0]}.${dateArray[1]}"
        btn_confirm.setOnClickListener {
            cancel()
        }
    }

    override fun show() {
        super.show()
    }

    override fun cancel() {
        super.cancel()
    }
}