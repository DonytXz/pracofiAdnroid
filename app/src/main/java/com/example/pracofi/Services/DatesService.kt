package com.example.pracofi.Services

import android.content.Context
import android.content.Intent
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pracofi.Dates
import com.example.pracofi.Details
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.Path
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class DatesService {
    companion object {
        var datesList: ArrayList<Date>? = null
        var responseData: String = ""
        var flag: Boolean = false
        fun getBookings(activity: AppCompatActivity): ArrayList<Date> {
            @Throws(IOException::class)

            fun downloadData(url: String): String {

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                var inputStream: InputStream? = null
                try {
                    val url = URL(url)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connect()
                    inputStream = connection.inputStream
                    return inputStream.bufferedReader().use {
                        it.readText()
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close()
                    }

                }
            }
//            var data = null
//            if(Network.networkPresent(activity)){
//                val arr = [{hola:"mundo"}, ]
            val arrayList = downloadData("https://pracofi.herokuapp.com/mostrar_citas")
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
//            Toast.makeText(activity, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
//            }
            return data
        }

        fun deleteMethod(id: String, view: Context) {

            // Create Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pracofi.herokuapp.com")
                .build()

            // Create Service
            val service = retrofit.create(APIService::class.java)

            CoroutineScope(Dispatchers.IO).launch {

                // Do the DELETE request and get response


                val response = service.deleteEmployee(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("REPONSE_DELETE", response.body().toString())
                        val intent = Intent(view, Dates::class.java)
//                        intent.putExtra("LOADING", "Cargando...");
                        view.startActivity(intent)
                        // Convert raw JSON to pretty JSON using GSON library
//                        val gson = GsonBuilder().setPrettyPrinting().create()
//                        val prettyJson = gson.toJson(
//                            JsonParser.parseString(
//                                response.body()
//                                    ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
//                            )
//                        )
//
//                        Log.d("Pretty Printed JSON :", prettyJson)

                    } else {

                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }

                }
            }
        }

        fun parseResponse(response: String, activity: AppCompatActivity): ArrayList<Date> {

            val mGparser = JsonParser()
            val mGson = Gson()

            var hora = ""
            var area = ""
            val rfc = ""

            val jsonArray = JSONArray(response)
            val items: ArrayList<Date> = mGson.fromJson(
                response,
                object : TypeToken<ArrayList<Date?>?>() {}.type
            )
//            var arrBookings: ArrayList<Date> = ArrayList()
            var dateObj: Date

            datesList = ArrayList()
//            Log.d("TAG1", jsonArray.toString())
            for (i in 0 until jsonArray.length()) {
                //get json objetc
                val jsonObject = jsonArray.getJSONObject(i)
                Log.d("JSON", jsonObject.toString())
                //get values from object
                val date: String = jsonObject.get("fecha_cita").toString()
                val dateTime: String = jsonObject.get("hora").toString()
                val area: String = jsonObject.get("area").toString()
                val topic: String = jsonObject.get("motivo").toString()
                val rfc: String = jsonObject.get("rfc").toString()
                val userId: String = jsonObject.get("usuario").toString()
                val id: String = jsonObject.get("_id").toString()
                //create object for date class
                dateObj = Date(date, dateTime, area, topic, rfc, userId, id)
                //push object to array
                datesList?.add(dateObj)
                Log.d("OBJ", dateObj.topic.toString())
//                arrBookings.add(dateObj)
            }
            return datesList as ArrayList<Date>
        }

    }
}

interface APIService {
    @DELETE("/citas/{id}")
//    @DELETE("/citas?id={id}")
    suspend fun deleteEmployee(
        @Path("id") id: String    // use @Path() instead of @Query()
    ): Response<ResponseBody>
}
