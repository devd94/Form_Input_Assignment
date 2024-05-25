package com.devina.ekacareformassignment.di

import android.content.Context
import com.devina.ekacareformassignment.data.database.AppDatabase
import com.devina.ekacareformassignment.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao
    {
        return appDatabase.userDao()!!
    }
}