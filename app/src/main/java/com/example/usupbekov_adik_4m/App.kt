package com.example.usupbekov_adik_4m

import android.app.Application
import androidx.room.Room
import com.example.usupbekov_adik_4m.data.local.db.AppDatabace

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db =  Room.databaseBuilder(
            applicationContext,
            AppDatabace::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
    companion object {
        lateinit var db: AppDatabace
    }
}