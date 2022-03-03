package com.example.pracofi.Services

import android.R
import android.os.StrictMode
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class DatesService {
    companion object {
        var responseData: String = ""
        var flag: Boolean = false
        fun getBookings(activity: AppCompatActivity):ArrayList<String> {
            @Throws(IOException::class)

            fun downloadData(url:String):String {

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                var inputStream: InputStream? = null
                try {
                    val url = URL(url)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connect()
                    inputStream = connection.inputStream
                    return inputStream.bufferedReader().use{
                        it.readText()
                    }
                }finally {
                    if(inputStream != null){
                        inputStream.close()
                    }

                }
            }
//            var data = null
//            if(Network.networkPresent(activity)){
//                val arr = [{hola:"mundo"}, ]
                val arrayList =  downloadData("https://pracofi.herokuapp.com/mostrar_citas")
                var data = parseResponse(arrayList, activity);


//                val answer = JSONObject(downloadData("https://pracofi.herokuapp.com/mostrar_citas"))
//                val gson = GsonBuilder().create()
//                val Model= gson.fromJson(arrayList,Array<BookingObject>::class.java).toList()
//                val list: List<Any> = Arrays.asList(
//                    GsonBuilder().create().fromJson(
//                        arrayList,
//                        Array<Any>::class.java
//                    )
//                )
//                val gson = GsonBuilder().create()
//                val theList = gson.fromJson<ArrayList<Objects>>(arrayList, object :TypeToken<ArrayList<Objects>>(){}.type)
//                val list:List<BookingObject> = Gson().fromJson<List<BookingObject>>(arrayList)
//                Log.d("TAG1", theList.toString())
//            }else{
                Toast.makeText(activity, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
//            }
            return data
        }
        inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)
        fun parseResponse(response: String, activity: AppCompatActivity): ArrayList<String> {

            val mGparser = JsonParser()
            val mGson = Gson()

            var hora = ""
            var area = ""
            val rfc =  ""

            val jsonArray = JSONArray(response)
            val items: ArrayList<ArrayAdapterDate> = mGson.fromJson(
                response,
                object : TypeToken<ArrayList<ArrayAdapterDate?>?>() {}.type
            )
            var arrBookings:ArrayList<String> = ArrayList()

//            Log.d("TAG1", jsonArray.toString())
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
//                Log.d("TAG1", jsonObject.toString())
                val date= jsonObject.get("area")
                arrBookings.add(date as String)
            }
            return arrBookings
//            (0..5).forEach { index ->
//                val jsonObject = jsonArray.getJSONObject(index)
//                Log.d("TAG1", jsonObject.toString())
////                if (jsonObject.has("artist") && jsonObject.has("image")) {
////                    artist = jsonObject.getString("artist")
////                    image = jsonObject.getString("image")
////                }
////                else if (jsonObject.has("video_id") && jsonObject.has("video_title")) {
////                    val newVideo = Objects(jsonObject.getString("video_id"), jsonObject.getString("video_title"))
////                    videoList.add(newVideo)
////                }
//            }
        }

    }
}