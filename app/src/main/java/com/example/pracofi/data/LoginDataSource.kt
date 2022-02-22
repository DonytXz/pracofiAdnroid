package com.example.pracofi.data

import android.util.Log
import com.example.pracofi.data.model.LoggedInUser
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    fun runPostApi(username: String, password: String) {
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

        // add parameter
//        val formBody = FormBody.Builder().add("email", username)
//            .build()


        // creating request
        var request = Request.Builder().url(url)
            .post(body)
            .build()

        var client = OkHttpClient();
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
//                println(response.body?.string())
                Log.i("POSTSUCCSES", response.body?.string().toString())

            }

            override fun onFailure(call: Call, e: IOException) {
                println(e.message.toString())
                Log.i("POSTERROR", e.message.toString())

            }
        })}


    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            runPostApi(username, password)

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "adasda")

            return Result.Success(fakeUser)
        } catch (e: Throwable) {

            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}