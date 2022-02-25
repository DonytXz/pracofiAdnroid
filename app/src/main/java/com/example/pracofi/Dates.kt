package com.example.pracofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Dates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        Toast.makeText(
            this,
            "Inicio de session exitoso!",
            Toast.LENGTH_SHORT
        ).show()
    }
}