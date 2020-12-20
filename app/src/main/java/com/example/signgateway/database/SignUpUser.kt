package com.example.signgateway.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sign_up_users", primaryKeys = [ "email_or_number", "password" ])
data class SignUpUser (
    @ColumnInfo(name = "user_name")
    val name : String,
    @ColumnInfo(name = "user_surname")
    val surname : String,
    @ColumnInfo(name = "email_or_number") @NonNull
    val email_or_number : String,
    @ColumnInfo(name = "password") @NonNull
    val password : String,
    @ColumnInfo(name = "day_of_birth")
    val day_of_birth : Int,
    @ColumnInfo(name = "month_of_birth")
    val month_of_birth : Int,
    @ColumnInfo(name = "year_of_birth")
    val year_of_birth : Int,
    @ColumnInfo(name = "gender")
    val gender : String
)