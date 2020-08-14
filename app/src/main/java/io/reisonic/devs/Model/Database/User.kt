package io.reisonic.devs.Model.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="user")
class User(@PrimaryKey(autoGenerate = true)var _id: Int, var _number: String, var _password:String) {
}