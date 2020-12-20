package com.example.signgateway.database

import androidx.room.*

@Dao
interface SignGatewayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSignUpUser(signUpUser: SignUpUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSignInUser(singInUser: SignInUser)

    @Query("DELETE FROM SIGN_UP_USERS")
    fun deleteSignUpAll()

    @Query("DELETE FROM SIGN_UP_USERS")
    fun deleteSignInAll()

    @Query("SELECT * FROM SIGN_IN_USERS ORDER BY wID ASC")
    fun getAllSignInUsers(): List<SignInUser>

    @Query("SELECT * FROM SIGN_UP_USERS ORDER BY user_name ASC")
    fun getAllSignUpUsers(): List<SignUpUser>
}