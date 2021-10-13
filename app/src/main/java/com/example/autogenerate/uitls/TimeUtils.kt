package com.example.autogenerate.uitls

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun getDate() : String{
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