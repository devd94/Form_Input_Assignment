package com.devina.ekacareformassignment.data.database

import com.devina.ekacareformassignment.data.database.dao.UserDao
import com.devina.ekacareformassignment.data.database.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalSource @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUsers(user: User) : Long
    {
        return userDao.insert(user)
    }

    fun getAllUsers() : List<User>
    {
        return userDao.getAll()
    }

}