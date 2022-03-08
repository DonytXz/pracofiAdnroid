package com.example.pracofi.Services

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pracofi.Dates
import com.example.pracofi.MainActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class AutService {
    companion object {
        var responseData: String = ""
        var flag: Boolean = false
        fun login(activity: AppCompatActivity, username: String, password: String) {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("email", username)
                jsonObject.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)
            var url = "https://pracofi.herokuapp.com/login"

            // creating request
            var request = Request.Builder().url(url)
                .post(body)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body?.string()
                    Log.i("POSTSUCCSES", responseData.toString())
                    var map: Map<String, Any> = HashMap()
                    var mapUser: Map<String, Any> = HashMap()
                    map = Gson().fromJson(responseData, map.javaClass)
                    mapUser = map["usuario"] as Map<String, Any>
                    val flag:Boolean = map["ok"] as Boolean
//                    val user:JSONObject = map["usuario"] as JSONObject
                    val name:String = mapUser["nombre"] as String

                    Log.i("POSTSUCCSES", mapUser.toString())
                    Log.i("POSTSUCCSES", name.toString())
                    if(flag){
                        val intent = Intent(activity, Dates::class.java)
                        intent.putExtra("User", name);
                        activity.startActivity(intent)
                    }else{
                        val intent = Intent(activity, MainActivity::class.java)
                        intent.putExtra("Error_login", "Verifique sus credenciales");
                        activity.startActivity(intent)
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())
                    Log.i("POSTERROR", e.message.toString())
                }
            })
        }
    }
}