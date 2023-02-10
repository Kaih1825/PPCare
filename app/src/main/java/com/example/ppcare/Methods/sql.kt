package com.example.ppcare.Methods

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.JsonReader
import android.util.Log
import com.example.ppcare.R
import org.json.JSONArray
import org.json.JSONObject

class sql {
    companion object {
        var db: SQLiteDatabase? =null
        fun init(context: Context){
            db=context.openOrCreateDatabase("db.db",MODE_PRIVATE,null)
            try {
                db!!.execSQL("CREATE TABLE voInfo(id INTEGER PRIMARY KEY,type TEXT,place TEXT,date TEXT,time TEXT)")
                db!!.execSQL("INSERT INTO voInfo(id,type,place,date,time) VALUES(1,'','','','')")
                db!!.execSQL("INSERT INTO voInfo(id,type,place,date,time) VALUES(2,'','','','')")
                db!!.execSQL("INSERT INTO voInfo(id,type,place,date,time) VALUES(3,'','','','')")
                db!!.execSQL("INSERT INTO voInfo(id,type,place,date,time) VALUES(4,'','','','')")
            }catch (ex:java.lang.Exception){}
        }

        fun update(context:Context,id:Int,type:String,place:String,date:String,time:String){
            init(context)
            db!!.execSQL("UPDATE voInfo SET type='${type}',place='${place}',date='${date}',time='${time}' WHERE id=${id}")
        }

        fun get(context: Context, id: Int): Array<String> {
            init(context)
            try{
                var cursor=db!!.rawQuery("SELECT * FROM voInfo WHERE id=${id}", null)
                cursor.moveToFirst()
                return arrayOf(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
            }catch (ex:java.lang.Exception){
                Log.e("TAG", ex.toString(), )
            }
            return arrayOf("","","","")

        }
    }

}

class sqlEpid {
    companion object {
        var db: SQLiteDatabase? =null
        fun init(context: Context,activity: Activity){
            db=context.openOrCreateDatabase("db.db",MODE_PRIVATE,null)
            try {
                db!!.execSQL("CREATE TABLE epInfo(id INTEGER PRIMARY KEY,date TEXT,title TEXT,article TEXT)")


            }catch (ex:java.lang.Exception){}

            try {
                var jsonText=activity.assets.open("epid_info.json").bufferedReader().use { it.readText() }
                var rootArray=JSONArray(jsonText)
                for(i in 0 until rootArray.length()){
                    var jsonObject=rootArray.getJSONObject(i)
                    var id=jsonObject.getInt("id")
                    var date=jsonObject.getString("date")
                    var title=jsonObject.getString("title")
                    var article=jsonObject.getString("article")
                    db!!.execSQL("INSERT INTO epInfo(id,date,title,article) VALUES(${id},'${date}','${title}','${article}')")
                }
            }catch (ex:Exception){}
        }
        fun getTitle(context: Context,activity: Activity, id: Int): String {
            init(context,activity)
            try{
                var cursor= db!!.rawQuery("SELECT * FROM epInfo WHERE id=${id}", null)
                cursor.moveToFirst()
                return cursor.getString(2)
            }catch (ex:java.lang.Exception){}
            return "ERROR"

        }

        fun getArticle(context: Context,activity: Activity, id: Int): String {
            init(context,activity)
            try{
                var cursor= db!!.rawQuery("SELECT * FROM epInfo WHERE id=${id}", null)
                cursor.moveToFirst()
                return cursor.getString(3)
            }catch (ex:java.lang.Exception){}
            return "ERROR"

        }

        fun getCount(context: Context,activity: Activity):Int{
            init(context,activity)
            var cursor=db!!.rawQuery("SELECT COUNT(*) FROM epInfo",null)
            cursor.moveToFirst()
            return cursor.getInt(0)
        }

        fun get(context: Context,activity: Activity, id: Int): Array<String> {
            init(context,activity)
            try{
                var cursor= db!!.rawQuery("SELECT * FROM epInfo WHERE id=${id}", null)
                cursor.moveToFirst()
                return arrayOf(cursor.getString(1),cursor.getString(2),cursor.getString(3))
            }catch (ex:java.lang.Exception){
                Log.e("TAG", ex.toString(), )
            }
            return arrayOf("","","","")

        }
    }

}

class sqlEdu {
    companion object {
        var db: SQLiteDatabase? =null
        fun init(context: Context){
            db=context.openOrCreateDatabase("db.db",MODE_PRIVATE,null)
            try {
                db!!.execSQL("CREATE TABLE eduInfo(id INTEGER PRIMARY KEY,date TEXT,title TEXT,video INTEGER,isStar INTEGER)")
            }catch (ex:java.lang.Exception){}
            try{
                db!!.execSQL("INSERT INTO eduInfo(id,date,title,video,isStar) VALUES(1,'2023/02/10','防疫大作戰 x 奧運羽球國手 - 王齊麟、李洋',${R.raw.video},0)")
                db!!.execSQL("INSERT INTO eduInfo(id,date,title,video,isStar) VALUES(2,'2023/02/10','防疫大作戰-Continuing preventive measures after vaccination(張厚台醫師，打完疫苗後 還是要做好防疫，英語)',${R.raw.video2},0)")
                db!!.execSQL("INSERT INTO eduInfo(id,date,title,video,isStar) VALUES(3,'2023/02/10','關於防疫，你可能比國手更厲害',${R.raw.video3},0)")
                Log.e("TAG", "init: ", )
            }catch (ex:java.lang.Exception){}
        }

        fun updateStart(context: Context,id:Int,isStar:Int){
            init(context)
            db!!.execSQL("UPDATE eduInfo SET isStar=${isStar} WHERE id=${id}")
            var a=getIsStar(context)
        }

        fun getIsStar(context: Context): ArrayList<Boolean> {
            init(context)
            var cursor=db!!.rawQuery("SELECT * FROM eduInfo",null)
            cursor.moveToFirst()
            var array= arrayListOf(false,false,false)
            for(i in 0 until cursor.count){
                var iss=cursor.getInt(4)==1
                array[i]=iss
                cursor.moveToNext()
            }
            return array
        }
    }

}