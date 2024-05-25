package com.devina.ekacareformassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devina.ekacareformassignment.data.database.dao.UserDao
import com.devina.ekacareformassignment.data.database.models.User

@Database(entities = [User::class], exportSchema = true, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao?

    companion object{
        private const val DATABASE_NAME = "UserDB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return (instance ?: synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                instance = newInstance

                instance
            })!!
        }
    }
}