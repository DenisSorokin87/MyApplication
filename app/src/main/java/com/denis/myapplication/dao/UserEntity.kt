package com.denis.myapplication.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.denis.myapplication.data.Task

@Entity
data class UserEntity(
        @PrimaryKey
        val id: Long,
        val name: String,
        val lastName: String,
        val loginName: String,
        val email: String
//        @ColumnInfo(name = "task_id")
//        val userTasks: ArrayList<TaskEntity>
)

