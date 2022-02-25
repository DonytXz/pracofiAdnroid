package com.example.pracofi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pracofi.Services.AutService


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //variables
        val etMail = findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        var errorString: String = ""
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                errorString = ""
            } else {
                errorString = extras.getString("Error_login").toString()
            }
        } else {
            errorString = (savedInstanceState.getSerializable("Error_login") as String?).toString()
        }
        if (errorString != "") {
            Toast.makeText(
                this,
                errorString,
                Toast.LENGTH_SHORT
            ).show()
        }

        btnLogin.setOnClickListener {
            //text from edit text
            var txtMail = etMail.text.toString()
            var txtPass = etPass.text.toString()

            //check if the EditText have values or not
            if (txtMail.trim().length > 0 && txtPass.trim().length > 0) {
                AutService.login(this, txtMail, txtPass)


            } else {
                Toast.makeText(
                    applicationContext,
                    "Ingrese sus datos antes de continuar",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }
}