package com.example.pracofi.Services

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pracofi.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DatesAdapter(var context: Context, items: ArrayList<Date>) : BaseAdapter() {

    var items: ArrayList<Date>? = null

    init {
        this.items = items
    }

    override fun getCount(): Int {
        return items?.count()!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View? = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.date_card, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        val item = getItem(position) as Date

//        val date = SimpleDateFormat("dd-MM", Locale("es", "MX")).parse(item.date)

        val outputFormat: DateFormat = SimpleDateFormat("MM/yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US)

        val date: java.util.Date? = inputFormat.parse(item.date)
        val outputText: String = outputFormat.format(date)

        holder?.date?.text = outputText.toString()
        holder?.dateTime?.text = item.dateTime
        holder?.topic?.text = item.topic
        return view!!
    }

    private class ViewHolder(view: View) {
        var date: TextView? = null
        var dateTime: TextView? = null
        var topic: TextView? = null

        init {
            //take value from params
            this.date = view.findViewById(R.id.tvDate)
            this.dateTime = view.findViewById(R.id.tvDateTime)
            this.topic = view.findViewById(R.id.tvTopic)
        }
    }
}