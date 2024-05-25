package com.devina.ekacareformassignment.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.devina.ekacareformassignment.data.database.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun insert(userData : User) : Long

    @Query("SELECT * FROM userdata")
    fun getAll() : List<User>

    @Delete
    fun delete(vararg userData: User)
}