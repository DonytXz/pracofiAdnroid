package com.example.pracofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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

        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvDateTime = findViewById<TextView>(R.id.tvDateTime)
        val tvTopic = findViewById<TextView>(R.id.tvTopic)
        val tvArea = findViewById<TextView>(R.id.tvArea)
        val tvRfc = findViewById<TextView>(R.id.tvRfc)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                val intent = Intent(this, Dates::class.java);
//                intent.putExtra("Error", "No se encontro la cita");
                this.startActivity(intent)
            } else {
                date =   extras.getString("Date").toString()
                dateTime = extras.getString("DateTime").toString()
                area = extras.getString("Area").toString()
                topic = extras.getString("Topic").toString()
                rfc = extras.getString("RFC").toString()
                userId = extras.getString("UserID").toString()
                id = extras.getString("ID").toString()

                val outputFormat: DateFormat = SimpleDateFormat("MM/yyyy", Locale.US)
                val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US)

                val dateParse: java.util.Date? = inputFormat.parse(date)
                val outputText: String = outputFormat.format(dateParse)

                tvDate.setText("Fecha: " + outputText.toString())
                tvDateTime.setText("Hora: " + dateTime.toString())
                tvTopic.setText("Asunto: " + topic.toString())
                tvArea.setText("Area: " + area.toString())
                tvRfc.setText("Rfc: " + rfc.toString())

                Log.d("EXTRAS", date)
                Log.d("EXTRAS", dateTime)
                Log.d("EXTRAS", area)
                Log.d("EXTRAS", topic)
                Log.d("EXTRAS", rfc)
                Log.d("EXTRAS", userId)
                Log.d("EXTRAS", id)
            }
        }
    }
}