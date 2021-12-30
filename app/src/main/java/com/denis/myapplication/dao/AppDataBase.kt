package com.denis.myapplication.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1 )
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserEntityDao(): UserEntityDao
}