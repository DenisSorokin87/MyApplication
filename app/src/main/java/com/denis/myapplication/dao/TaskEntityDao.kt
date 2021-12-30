package com.denis.myapplication.dao

import androidx.room.*


@Dao
interface TaskEntityDao  {

    @Query("SELECT * FROM TaskEntity")
    fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getById(id: Long): TaskEntity?

    @Query("SELECT * FROM TaskEntity WHERE userId = :useId")
    fun getAllUserTasks(useId: Long): List<TaskEntity>

    @Query("DELETE FROM TaskEntity")
    fun cleanSchema()

    @Insert
    fun insert(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)


}