package com.example.trackapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.provider.DocumentsContract
import androidx.room.Room
import com.example.trackapp.db.RunningDataBase
import com.example.trackapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.trackapp.other.Constants.KEY_NAME
import com.example.trackapp.other.Constants.KEY_WEIGHT
import com.example.trackapp.other.Constants.RUNNING_DATABASE_NAME
import com.example.trackapp.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent ::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        RunningDataBase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db : RunningDataBase) = db.getRunDao()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext app: Context){
        app.getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideName(sharedPref : SharedPreferences) = sharedPref.getString(KEY_NAME,"") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref : SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref : SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true)

}