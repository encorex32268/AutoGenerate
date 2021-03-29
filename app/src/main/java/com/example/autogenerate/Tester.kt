package com.example.autogenerate

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.logging.SimpleFormatter
import kotlin.random.Random

fun main(args: Array<String>) {


    val fever = arrayListOf<Double>(36.3,36.4,36.5,36.6,36.7)
    for (i in 0..10){
        var temp  = Random.nextInt(fever.size)
        println(fever[temp])
    }

}

private fun getDate(): String {
    val time = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        Date()
    }
    val simpleDateFormatter = SimpleDateFormat("MM月dd日")
    val result = simpleDateFormatter.format(time)
    val stringArray = result.toCharArray()
    var finalResult = ""
    for (i in stringArray.indices) {
        val temp = stringArray[i].toString()
        finalResult += if (i == 0 && temp == "0") {
            ""
        } else {
            temp
        }
    }
    return finalResult
}