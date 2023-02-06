package com.example.ppcare.Methods

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

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