package com.example.ppcare.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.ppcare.Dialogs.articleDialog
import com.example.ppcare.Methods.sqlEpid
import com.example.ppcare.R
import kotlinx.android.synthetic.main.epid_listview.view.*

class epidInfoListAdapter(var context:Context,var activity: Activity):BaseAdapter() {
    override fun getCount(): Int {
        return sqlEpid.getCount(context, activity)
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var v=LayoutInflater.from(context).inflate(R.layout.epid_listview,null)
        var res=sqlEpid.get(context,activity,p0+1)
        var date=res[0]
        v.txt_title.text=res[1]
        var dateArray=date.split('/')
        v.txt_ym.text="${dateArray[2]}.${dateArray[0]}"
        v.txt_date.text=dateArray[1]
        v.layout_click.setOnClickListener {
            articleDialog(context,activity,p0+1).show()
        }
        return v
    }
}