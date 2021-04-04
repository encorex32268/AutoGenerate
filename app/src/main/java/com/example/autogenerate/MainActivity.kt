package com.example.autogenerate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        var name = getStringFromSP("name")
        nameTextView.setText(name)
        saveButton.setOnClickListener {
            setSPStringByKey("name",nameTextView.text.toString())
        }


        copyButton.setOnClickListener {
            val name = nameTextView.text.toString()
            val fever = arrayListOf<Double>(36.3,36.4,36.5,36.6)
            var randomer  = fever[Random.nextInt(fever.size)]
            var message = "$name 报告如下 \n" +
                    "${getDate()} \n" +
                    "1.是否咳簌，流鼻涕：否 \n" +
                    "2.是否咽喉痛：否 \n" +
                    "3.是否发热：否 \n" +
                    "4.是否有其他身体不适：否 \n" +
                    "5.目前体温：$randomer℃"

            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("MyReport",message)
            clipboardManager.setPrimaryClip(clipData)
            contentAreaTextView.setText(message)
            Toast.makeText(this@MainActivity,"Copy Done " ,Toast.LENGTH_SHORT).show()
        }






    }
    private fun setSPStringByKey(key : String, value : String) {
        getSharedPreferences("Data", Context.MODE_PRIVATE)
                .edit()
                .putString(key,value)
                .commit()
    }

    private fun getStringFromSP(key : String) : String?{
        return getSharedPreferences("Data", Context.MODE_PRIVATE)
                .getString(key,"")
    }

    private fun getDate() : String{
        val time = Date()

        val simpleDateFormatter  = SimpleDateFormat("MM月dd日")
        val result = simpleDateFormatter.format(time)
        val stringArray = result.toCharArray()
        var finalResult = ""
        for (i in stringArray.indices) {
            val temp = stringArray[i].toString()
            finalResult += if ( (i==0 || i==3 ) && temp=="0"){ "" }else{ temp }
        }
        return finalResult
    }

















}


