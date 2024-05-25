package com.devina.ekacareformassignment.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devina.ekacareformassignment.data.DataRepository
import com.devina.ekacareformassignment.data.database.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class FormViewModel @Inject constructor(
  private val dataRepository: DataRepository
) : ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val userInsertedLiveDataPrivate = MutableLiveData<Long>()
    val userInsertedLiveData : LiveData<Long> get() = userInsertedLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val allUsersLiveDataPrivate = MutableLiveData<List<User>>()
    val allUsersLiveData : LiveData<List<User>> get() = allUsersLiveDataPrivate

    fun addUser(user: User)
    {
        viewModelScope.launch {
            dataRepository.insertUsers(user).collect{
                userInsertedLiveDataPrivate.value = it
            }

        }
    }

    fun getUsers()
    {
        viewModelScope.launch {
            dataRepository.getAllUsers().collect{
                allUsersLiveDataPrivate.value = it
            }
        }
    }
}