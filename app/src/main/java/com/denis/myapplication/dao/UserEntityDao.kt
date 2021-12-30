package com.denis.myapplication.dao

import androidx.room.*


@Dao
interface UserEntityDao  {

    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getById(id: Long): UserEntity

    @Query("DELETE FROM UserEntity")
    fun cleanSchema()

//    @Transaction
//    @Query("SELECT id, name, lastName from UserEntity")
//    fun getUserTasks(): List<UserTasks>

    @Insert
    fun insert(employee: UserEntity)

    @Update
    fun update(employee: UserEntity)

    @Delete
    fun delete(employee: UserEntity)


}