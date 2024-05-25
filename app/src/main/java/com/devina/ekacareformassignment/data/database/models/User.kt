package com.devina.ekacareformassignment.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")
class User {

    constructor()

    @Ignore
    constructor(name:String, age:Int, dob:String, address:String)

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    @ColumnInfo(name = "name")
    var name = ""

    @ColumnInfo(name = "age")
    var age = 0

    @ColumnInfo(name = "dob")
    var dob = ""

    @ColumnInfo(name = "address")
    var address = ""
}