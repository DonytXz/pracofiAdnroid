package com.example.pracofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var date: String
        var dateTime: String
        var area: String
        var topic: String
        var rfc: String
        var userId: String
        var id: String

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                //TODO HANDLE FAIL
            } else {
                date =   extras.getString("Date").toString()
                dateTime = extras.getString("DateTime").toString()
                area = extras.getString("Area").toString()
                topic = extras.getString("Topic").toString()
                rfc = extras.getString("RFC").toString()
                userId = extras.getString("UserID").toString()
                id = extras.getString("ID").toString()
                Log.d("EXTRAS", date)
                Log.d("EXTRAS", dateTime)
                Log.d("EXTRAS", area)
                Log.d("EXTRAS", topic)
                Log.d("EXTRAS", rfc)
                Log.d("EXTRAS", userId)
                Log.d("EXTRAS", id)
            }
        } else {
            //TODO HANDLE ELSE
        }
    }
}