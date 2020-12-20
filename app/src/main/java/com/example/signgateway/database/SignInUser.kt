package com.example.signgateway.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sign_in_users")
class SignInUser(
    @PrimaryKey(autoGenerate = true)
    var wID: Int = 0,
    @ColumnInfo(name = "username")
    val username : String,
    @ColumnInfo(name = "password")
    val password : String
)