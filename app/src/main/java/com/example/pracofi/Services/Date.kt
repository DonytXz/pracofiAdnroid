package com.example.pracofi.Services

class Date (date:String, dateTime:String, area:String, topic:String, rfc:String, userId:String, id:String){
    //declare variables
    var date: String = ""
    var dateTime: String = ""
    var area: String = ""
    var topic: String = ""
    var rfc: String = ""
    var userId: String = ""
    var id: String = ""
    init {
        //take value from params
        this.date = date
        this.dateTime = dateTime
        this.area = area
        this.topic = topic
        this.rfc = rfc
        this.userId = userId
        this.id = id
    }
}