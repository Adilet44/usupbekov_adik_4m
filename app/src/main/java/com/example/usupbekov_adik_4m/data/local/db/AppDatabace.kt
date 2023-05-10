package com.example.usupbekov_adik_4m.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usupbekov_adik_4m.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabace : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
