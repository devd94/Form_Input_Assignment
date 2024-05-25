package com.devina.ekacareformassignment.data

import com.devina.ekacareformassignment.data.database.UserLocalSource
import com.devina.ekacareformassignment.data.database.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository @Inject constructor(private val userSource: UserLocalSource) {

    suspend fun insertUsers(user: User) : Flow<Long>
    {
        return flow { emit(userSource.insertUsers(user)) }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllUsers() : Flow<List<User>>
    {
        return flow { emit(userSource.getAllUsers()) }.flowOn(Dispatchers.IO)
    }

}