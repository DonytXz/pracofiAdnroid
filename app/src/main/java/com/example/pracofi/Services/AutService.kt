package com.example.pracofi.Services

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.pracofi.Dates
import com.example.pracofi.MainActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class AutService {
    companion object {
        //        var mHandler =  Handler(Looper.getMainLooper());
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
                    map = Gson().fromJson(responseData, map.javaClass)
                    val flag:Boolean = map["ok"] as Boolean
                    if(flag){
                        val intent = Intent(activity, Dates::class.java)
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

//            try {
//                val response= client.newCall(request).execute()
//                var MyResult: String = response.body?.string().toString()
//                Log.i("RESULT", MyResult.toString())
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }


//            Log.i("FLAGOUT", flag.toString())
//            Log.i("FLAGOUT", responseData.toString())

//            if(flag){
//                Log.i("FLAG", "ENTRO!")
//                Toast.makeText(
//                    activity,
//                    "Inicio de session exitoso!",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Toast.makeText(
//                    activity,
//                    responseData,
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
        }

//        suspend fun authenticate(username: String, password: String): Deferred<AutService> = withContext(Dispatchers.IO) {
//            async {
//                suspendCoroutine<AutService> { continuation ->
//                    val jsonObject = JSONObject()
//                    try {
//                        jsonObject.put("email", username)
//                        jsonObject.put("password", password)
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                    val mediaType = "application/json; charset=utf-8".toMediaType()
//                    val body = jsonObject.toString().toRequestBody(mediaType)
//                    var url = "https://pracofi.herokuapp.com/login"
//
//                    // creating request
//                    var request = Request.Builder().url(url)
//                        .post(body)
//                        .build()
//                    val client = OkHttpClient()
//
//                    client.newCall(request).enqueue(object : Callback {
//                        override fun onResponse(call: Call, response: Response) {
//                            val body = response.body?.string()
//
//                            val gson = GsonBuilder().create()
//                            val res = gson.fromJson(body, AutService::class.java)
//                                                Log.i("POSTSUCCSES", res.toString())
//                            Log.i("FLAG", "ENTRO!")
//
//
//
////                            APIToken.getInstance().put(APIToken.Key.API_TOKEN, res.token)
////                            continuation.resume(LoggedInUser(UUID.randomUUID().toString(), "Jane DD","dfasdfasdf")) // return LoggedInUser
//                        }
//
//                        override fun onFailure(call: Call, e: IOException) {
//                            println("error")
//                            println(call)
//                            continuation.resumeWithException(e) // fails with exception
//                        }
//                    })
//                    continuation.resume(return@suspendCoroutine)
//                }
//            }
//        }
    }


}