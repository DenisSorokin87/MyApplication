package com.denis.myapplication.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
        @PrimaryKey
        val id: Long,
        val description: String,
        val date: String,
        val userId: Long
)

