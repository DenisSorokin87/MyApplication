package com.denis.myapplication.dao

import androidx.room.*


@Dao
interface UserTasksEntityDao  {

//    @Query("SELECT * FROM UserTasksEntity")
//    fun getAll(): List<UserTasksEntity>

    @Query("SELECT * FROM UserTasksEntity WHERE userId = :id")
    fun getByUserId(id: Long): List<UserTasksEntity>?

    @Query("DELETE FROM UserTasksEntity")
    fun cleanSchema()

    @Insert
    fun insert(task: UserTasksEntity)

    @Update
    fun update(task: UserTasksEntity)

    @Delete
    fun delete(task: UserTasksEntity)


}