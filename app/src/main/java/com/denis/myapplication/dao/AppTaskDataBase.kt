package com.denis.myapplication.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1 )
abstract class AppTaskDataBase : RoomDatabase() {
    abstract fun getTaskEntityDao(): TaskEntityDao
}