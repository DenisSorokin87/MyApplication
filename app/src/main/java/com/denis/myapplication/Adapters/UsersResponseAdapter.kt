package com.denis.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.denis.myapplication.MyApplication
import com.denis.myapplication.R
import com.denis.myapplication.dao.*
import com.denis.myapplication.data.User
import java.util.ArrayList


class UsersResponseAdapter(var context: Context, var userList: List<User?>? = emptyList()): RecyclerView.Adapter<UsersResponseAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userList = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(userList)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.firstName.text = userList!![position]?.userName
        holder.lastName.text = userList!![position]?.userLastName

        val strTaskList: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            R.layout.users,
            getUserTasks(userList!![position])
        )
        holder.tasksList.adapter = strTaskList


    }

    private fun getUserTasks(user: User?): ArrayList<String> {
        val tDb: AppTaskDataBase = MyApplication.instance.appTaskDataBase
//        val utDB: AppUserTasksDataBase = MyApplication.instance.userTasksDataBase
        val taskDao: TaskEntityDao = tDb.getTaskEntityDao()
//        val userTasksDao: UserTasksEntityDao = utDB.getUserTasksEntityDao()

//        println(userDao.getUserTasks())
        val strTaskList = ArrayList<String>()
        if(user != null){
            val userTasks: List<TaskEntity> = tDb.getTaskEntityDao().getAllUserTasks(user.id)
            userTasks.forEach {
                strTaskList.add(it!!.description +" - "+ it.date) }
            }
        return strTaskList;
        }




//        if (user != null) {
//            val userTasks: List<UserTasksEntity>? = userTasksDao.getByUserId(user.id)
//            userTasks?.forEach {
//                val task = taskDao.getById(it.taskId)
//                strTaskList.add(task!!.description +" - "+ task.date) }
//
//        }
//        return strTaskList;
//    }

    override fun getItemCount(): Int {
        return userList!!.size
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.card_view)
        var firstName: TextView = itemView.findViewById(R.id.worker_first_name)
        var lastName: TextView = itemView.findViewById(R.id.worker_last_name)
        var tasksList: ListView = itemView.findViewById(R.id.user_tasks_list)


    }
}


