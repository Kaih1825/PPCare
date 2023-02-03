package com.example.ppcare.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ppcare.R
import kotlinx.android.synthetic.main.activity_register_screen.*
import org.json.JSONArray

class RegisterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        window.apply {
            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
            navigationBarColor=Color.TRANSPARENT
        }

        var cityAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,getCityArray())
        spr_city.adapter=cityAdapter
        spr_city.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var countryArray=getCountryArray(spr_city.selectedItem.toString())
                var countryAdapter=ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,countryArray)
                spr_country.adapter=countryAdapter
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        edt_name.addTextChangedListener(object:TextWatcher{
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
        edt_tel.addTextChangedListener(object:TextWatcher{
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
        edt_id.addTextChangedListener(object :TextWatcher{
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
        edt_cardId.addTextChangedListener(object : TextWatcher{
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
        edt_email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var regex="""[\sA-Za-z0-9._-]+@[\sA-Za-z0-9._-]+\.[\sA-Za-z]{2,4}""".toRegex()
                if(!regex.containsMatchIn(edt_email.text)){
                    edt_email.error="格式錯誤\n\n\n"
                }
                regex="""\s""".toRegex()
                if(regex.containsMatchIn(edt_email.text)){
                    edt_email.error="含有空格且格式錯誤\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        edt_pwd1.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var regex1="""(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,13}$""".toRegex()
                if(!regex1.containsMatchIn(edt_pwd1.text.toString())){
                    edt_pwd1.error="密碼格式有誤\n\n\n"
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        edt_pwd2.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(edt_pwd1.text.toString()!=edt_pwd2.text.toString()){
                    edt_pwd2.error="兩者密碼不同\n\n\n"
                    return true
                }
                return false
            }

        })

        btn_login.setOnClickListener {
            finish()
        }
    }

    private fun getCityArray(): Array<String?> {
        var jsonText=assets.open("taiwan_city.json").bufferedReader().use{it.readText() }
        var rootArray=JSONArray(jsonText)
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
        var rootArray=JSONArray(jsonText)
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
}