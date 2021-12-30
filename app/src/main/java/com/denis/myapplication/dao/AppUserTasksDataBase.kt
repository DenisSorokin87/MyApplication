package com.denis.myapplication.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserTasksEntity::class], version = 1 )
abstract class AppUserTasksDataBase : RoomDatabase() {
    abstract fun getUserTasksEntityDao(): UserTasksEntityDao
}