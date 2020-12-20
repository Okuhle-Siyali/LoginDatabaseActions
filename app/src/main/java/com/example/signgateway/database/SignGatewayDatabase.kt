package com.example.signgateway.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SignUpUser::class, SignInUser::class], version = 10)
abstract class SignGatewayDatabase : RoomDatabase() {
    abstract fun signGatewayDao() : SignGatewayDao

    companion object {
        @Volatile
        private var database: SignGatewayDatabase? = null

        fun getDatabase(context: Context): SignGatewayDatabase {
            val storeDatabase = database
            if (storeDatabase != null) return storeDatabase
            synchronized(this) {
                val databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    SignGatewayDatabase::class.java,
                    "sign_gateaway_database"
                ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
                database = databaseInstance
                return databaseInstance
            }
        }
    }
}