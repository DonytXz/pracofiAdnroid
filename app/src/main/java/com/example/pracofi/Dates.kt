package com.example.pracofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pracofi.Services.DatesAdapter
import com.example.pracofi.Services.DatesService.Companion.deleteMethod
import com.example.pracofi.Services.DatesService.Companion.getBookings
import com.example.pracofi.Services.Network

class Dates : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        var errorMsj: String
        var userName: String
        var isLoadding: String
        val context = this
        val tvIsNoData = findViewById<TextView>(R.id.tvNoData)

//        Toast.makeText(
//            this,
//            "Inicio de session exitoso!",
//            Toast.LENGTH_SHORT
//        ).show()

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {

            } else {
                errorMsj = extras.getString("Error").toString()
                userName = extras.getString("User").toString()
                isLoadding = extras.getString("LOADING").toString()

                Toast.makeText(
                    this,
                    "Bienvenido " + userName,
                    Toast.LENGTH_SHORT
                ).show()

//                Log.i("EXTRASONDATES", errorMsj.toString())
//                Log.i("EXTRASONDATES", userName.toString())
//                Log.i("EXTRASONDATES", isLoadding.toString())
//                if (userName == "null") {
//                    Toast.makeText(
//                        this,
//                        "Bienvenido " + userName,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                if(userName == "null"){
//                    Toast.makeText(
//                        this,
//                        isLoadding,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                if (userName == "null") {
//                    Toast.makeText(
//                        this,
//                        errorMsj,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
            }
        }

        //check network capability
        if (Network.networkPresent(this)) {
            val data = getBookings(this)
            Log.d("ARR_DATE", data.toString())
            val list = findViewById<ListView>(R.id.lvDates)
//            val ivBtnDelete = findViewById<ImageView>(R.id.ivBtnDelete)
//            val adapter = ArrayAdapter<Date>(this, android.R.layout.simple_list_item_1, data)


            val adapter = DatesAdapter(this, data)

//            ivBtnDelete.setOnClickListener {
//                deleteMethod()
//            }


            list.adapter = adapter


            list.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
//                Log.d("LISTENER", data.get(position).date.toString())
//                Toast.makeText(this, data.get(position).topic.toString(), Toast.LENGTH_SHORT).show()
                    val ivBtnDelete = view.findViewById<ImageView>(R.id.ivBtnModify)
                    ivBtnDelete.setOnClickListener {
                        deleteMethod(data.get(position).id.toString(), context)
                    }

                    val intent = Intent(this, Details::class.java)
                    intent.putExtra("Date", data.get(position).date.toString());
                    intent.putExtra("DateTime", data.get(position).dateTime.toString());
                    intent.putExtra("Area", data.get(position).area.toString());
                    intent.putExtra("Topic", data.get(position).topic.toString());
                    intent.putExtra("RFC", data.get(position).rfc.toString());
                    intent.putExtra("UserID", data.get(position).userId.toString());
                    intent.putExtra("ID", data.get(position).id.toString());
                    this.startActivity(intent)
                }
            if (data?.isEmpty()) {
                list.setVisibility(View.GONE);
            } else {
                tvIsNoData.setVisibility(TextView.GONE);
            }

        } else {
            Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
        }
    }
}