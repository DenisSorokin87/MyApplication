package com.denis.myapplication.dao

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
    data class UserTasksEntity(

        val userId: Long,
        @PrimaryKey
        val taskId: Long
    )


