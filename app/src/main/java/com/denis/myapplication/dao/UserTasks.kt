package com.denis.myapplication.dao

import androidx.room.Embedded
import androidx.room.Relation

class UserTasks(


    val id: Long,
    val name: String,
    val lastName: String,
    @Relation(parentColumn = "id", entityColumn = "id", entity = TaskEntity::class)
    val taskList: List<TaskEntity>
)