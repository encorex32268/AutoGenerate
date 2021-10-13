package com.example.autogenerate.uitls

import android.content.*
import android.util.Log
import android.widget.Toast
import com.example.autogenerate.model.Constants

class ReportCreator(
    private val context : Context
) {

     fun getReportMessage() : String {
        val name = getStringFromSP(Constants.SHAREDPREFERENCES_NAME_KEY)?:"Please save Name"
        val fevers = arrayListOf(36.3,36.2,36.4,36.5,36.6)
        val fever = fevers.random()
        val message = "$name 报告如下 \n" +
                "${TimeUtils.getDate()} \n" +
                "1.是否咳簌，流鼻涕：否 \n" +
                "2.是否咽喉痛：否 \n" +
                "3.是否发热：否 \n" +
                "4.是否有其他身体不适：否 \n" +
                "5.目前体温：$fever℃"
        Log.d("ReportCreator", "getReportMessage: $message")
        return  message
    }

    fun setSPStringByKey(key : String, value : String) {
        context.getSharedPreferences(Constants.SHAREDPREFERENCES_FILENAME, Context.MODE_PRIVATE)
            .edit()
            .putString(key,value)
            .apply()
    }

    fun getStringFromSP(key : String) : String?{
        return context.getSharedPreferences(Constants.SHAREDPREFERENCES_FILENAME, Context.MODE_PRIVATE)
            .getString(key,"")
    }

    fun copyToClipBoard() {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(Constants.CLIPBOARD_KEY,getReportMessage())
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, Constants.COPY_DONE, Toast.LENGTH_SHORT).show()
    }
    fun intentShareToWeChat(){
        val componentName = ComponentName(
            Constants.WECHAT_PACKAGE_NAME,
            Constants.WECHAT_PACKAGE_CLASS_NAME
        )
        val intent = Intent().apply {
            component = componentName
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Constants.WECHAT_INTENT_KEY,getReportMessage())
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }


}