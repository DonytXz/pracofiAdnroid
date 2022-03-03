package com.example.pracofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.pracofi.Services.AutService
import com.example.pracofi.Services.DatesService.Companion.getBookings
import com.example.pracofi.Services.Network

class Dates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        Toast.makeText(
            this,
            "Inicio de session exitoso!",
            Toast.LENGTH_SHORT
        ).show()

        //check network capability
        if(Network.networkPresent(this)){
                val data = getBookings(this)
            Log.d("TAG1", data.toString())
            val list = findViewById<ListView>(R.id.lvDates)
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

            list.adapter = adapter
        }else{
            Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
        }
    }
}