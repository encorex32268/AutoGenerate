package com.example.autogenerate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.autogenerate.R
import com.example.autogenerate.uitls.ReportCreator
import com.example.autogenerate.model.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val reportCreator = ReportCreator(this)
        val name = reportCreator.getStringFromSP(Constants.SHAREDPREFERENCES_NAME_KEY)
        nameTextView.setText(name)
        saveButton.setOnClickListener {
            reportCreator.setSPStringByKey(Constants.SHAREDPREFERENCES_NAME_KEY,nameTextView.text.toString())
        }
        copyButton.setOnClickListener {
            val reportCreator = ReportCreator(this).apply { copyToClipBoard() }
            contentAreaTextView.setText(reportCreator.getReportMessage())
        }
    }



















}


