package com.example.usupbekov_adik_4m.data.local.db

import androidx.room.*
import com.example.usupbekov_adik_4m.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll():List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}