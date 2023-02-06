package com.example.ppcare.Activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import coil.load
import coil.transform.BlurTransformation
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_set_per_info.*
import org.json.JSONArray

class set_per_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_per_info)
        window.apply {
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor= Color.TRANSPARENT
            navigationBarColor= Color.TRANSPARENT
        }

        var cityAdapter=
            ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,getCityArray())
        spr_city.adapter=cityAdapter
        spr_city.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var countryArray=getCountryArray(spr_city.selectedItem.toString())
                var countryAdapter= ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,countryArray)
                spr_country.adapter=countryAdapter
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        back.setOnClickListener {
            finish()
        }

        edt_name.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_name.text)){
                    edt_name.error="請勿輸入空白字元\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        edt_tel.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var regex="^09[0-9]{8}$".toRegex()
                Log.e("s", regex.pattern )
                if(!regex.containsMatchIn(edt_tel.text.toString())){
                    edt_tel.error="格式錯誤\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        edt_id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(edt_id.length()>=10){
                    if(!checkId(edt_id.text.toString())){
                        edt_id.error="非身分證格式\n\n\n"
                    }
                }else{
                    edt_id.error="非身分證格式\n\n\n"
                }
                var regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_id.text.toString())){
                    edt_id.error="含有空格\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        edt_cardId.addTextChangedListener(object : TextWatcher {
            var del=false
            var tmp=""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tmp=edt_cardId.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                del = p3==0
                var regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_cardId.text.toString())){
                    edt_cardId.error="含有空格\n\n\n"
                }
                if(edt_cardId.text.toString().length==20){
                    edt_cardId.setText(tmp)
                    edt_cardId.setSelection(edt_cardId.text.length)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if((edt_cardId.text.length==4||edt_cardId.text.length==9||edt_cardId.text.length==14)&&!del){
                    edt_cardId.setText(edt_cardId.text.toString()+"-")
                    edt_cardId.setSelection(edt_cardId.text.length)
                }
            }

        })

        btn_con.setOnClickListener{
            setBlurBackground(this,blur_background)
            blur_background.visibility=View.VISIBLE
            reg_suc.visibility=View.VISIBLE
            Handler().postDelayed({
                reg_suc.visibility=View.GONE
                blur_background.visibility=View.GONE
                finish()
            },2000)
        }
    }
    private fun getCityArray(): Array<String?> {
        var jsonText=assets.open("taiwan_city.json").bufferedReader().use{it.readText() }
        var rootArray= JSONArray(jsonText)
        var cityArray= arrayOfNulls<String>(rootArray.length())
        for(i in 0 until rootArray.length()){
            var cityObject=rootArray.getJSONObject(i)
            var city=cityObject.getString("name")
            cityArray[i]=city
        }
        return cityArray
    }

    private fun getCountryArray(city:String):Array<String?>{
        var jsonText=assets.open("taiwan_city.json").bufferedReader().use{it.readText() }
        var rootArray= JSONArray(jsonText)
        for(i in 0 until rootArray.length()){
            var cityObject=rootArray.getJSONObject(i)
            var getCity=cityObject.getString("name")
            if(getCity==city){
                var countryJsonArray=cityObject.getJSONArray("districts")
                var countryArray= arrayOfNulls<String>(countryJsonArray.length())
                for(j in 0 until countryJsonArray.length()){
                    var countryObject=countryJsonArray.getJSONObject(j)
                    var countryName=countryObject.getString("name")
                    countryArray[j]=countryName
                }
                return countryArray
            }
        }
        return arrayOf()
    }

    private fun checkId(id:String):Boolean {
        var regex = """^[A-Z][1-9]{9}$""".toRegex()
        if (!regex.containsMatchIn(id)) {
            return false
        }
        var conver = arrayOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'J',
            'K',
            'L',
            'M',
            'N',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'X',
            'Y',
            'W',
            'Z',
            'I',
            'O',
        )
        var weight = arrayOf(1, 9, 8, 7, 6, 5, 4, 3, 2, 1,1)
        var firstCheck = (conver.indexOf(id[0]) + 10).toString()
        var checkNum= firstCheck[0].digitToInt() + firstCheck[1].digitToInt()*9
        Log.e("TAG", checkNum.toString(), )
        for(i in 1 until 10){
            checkNum+=id[i].digitToInt()*weight[i+1]
        }
        return checkNum%10==0
    }

    fun setBlurBackground(context: Context, imgView: ImageView){
        var screenShoot=window.decorView.drawToBitmap()
        var width=windowManager.defaultDisplay.width
        var height=windowManager.defaultDisplay.height
        var new= Bitmap.createBitmap(screenShoot,0,0,width,height)
        imgView.load(new){
            transformations(
                BlurTransformation(
                context,
                radius = 25f,
                sampling = 5f
            )
            )
        }
    }
}