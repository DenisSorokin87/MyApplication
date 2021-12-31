package com.denis.myapplication


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denis.myapplication.data.User
import com.denis.myapplication.adapters.UsersResponseAdapter
import com.denis.myapplication.dao.*
import com.denis.myapplication.data.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var userRecycler: RecyclerView? = null
    private var uResponseAdapter: UsersResponseAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener { (requireActivity() as MainActivity).showFirstFragment() }

        userRecycler = view.findViewById(R.id.users_recycler)
        userRecycler?.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        uResponseAdapter = UsersResponseAdapter(requireActivity())
        userRecycler?.adapter = uResponseAdapter

        val userService = MyApplication.getInstance().usersRetrofit
        val userCall = userService.getUsersFromJavaApp()
        userCall?.enqueue(object : Callback<ArrayList<User?>> {

            override fun onResponse(call: Call<ArrayList<User?>>, response: Response<ArrayList<User?>>) {
                if (response.body() != null && response.isSuccessful){

                    val uDb: AppDataBase = MyApplication.instance.database
                    val tDb: AppTaskDataBase = MyApplication.instance.appTaskDataBase
                    val userDao: UserEntityDao = uDb.getUserEntityDao()
                    val taskDao: TaskEntityDao = tDb.getTaskEntityDao()
                    userDao.cleanSchema()
                    taskDao.cleanSchema()

                    var user: UserEntity
                    var task: TaskEntity
                    if (response.body() != null) {
                        response.body()!!.forEach {
                            if (it != null) {
                                user = UserEntity(it.id, it.userName, it.userLastName, it.userLogin, it.userEmail)
                                it.userTasks.forEach{ userTask ->
                                    if (userTask != null){
                                        task = TaskEntity(userTask.id, userTask.taskDescription, userTask.taskDate, it.id)
                                        taskDao.insert(task)
                                    }
                                }
                                userDao.insert(user)
                            }
                        }
                    }

                    setUserRecycler(changeFromUserEntityToUSer(userDao.getAll()))

                }
                else Toast.makeText(requireActivity(), "Something went wrong...GetAllUsers Faild", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ArrayList<User?>>, t: Throwable) {
                Toast.makeText(getView()!!.context, "Something went wrong....GetAllUsers Faild!", Toast.LENGTH_LONG).show()
                println(t.message)
                //println(t.cause)
            }
        })

        userService.getSomeQuery(150, "Denis")
                ?.enqueue(object : Callback<ArrayList<String?>?> {
                    override fun onResponse(call: Call<ArrayList<String?>?>, response: Response<ArrayList<String?>?>) {
                        println(response.body())
                    }

                    override fun onFailure(call: Call<ArrayList<String?>?>, t: Throwable) {
                        Toast.makeText(getView()!!.context, "Something went wrong....getUserById", Toast.LENGTH_LONG).show()
                        println(t.message)
                        println(t.cause)
                    }
                })

        userService.sendUser(User(1,"Luke", "Anderson", "Luke12345", "LukeAnderson@Mail.com", "LukeAnderson", ArrayList<Task>()))
            ?.enqueue(object : Callback<ArrayList<String?>?> {
                override fun onResponse(call: Call<ArrayList<String?>?>, response: Response<ArrayList<String?>?>) {
                    println(response.body())
                }

                override fun onFailure(call: Call<ArrayList<String?>?>, t: Throwable) {
                    Toast.makeText(getView()?.context, "Something went wrong....getUserById", Toast.LENGTH_LONG).show()
                    println(t.message)
                    println(t.cause)
                }
            })
    }

    private fun changeFromUserEntityToUSer(dbResponse: List<UserEntity>): List<User?>? {
        var usersList: ArrayList<User?> = arrayListOf()
        dbResponse.forEach{
            usersList.add(User())
            usersList[dbResponse.indexOf(it)]!!.id = it.id
            usersList[dbResponse.indexOf(it)]!!.userName = it.name
            usersList[dbResponse.indexOf(it)]!!.userLastName = it.lastName

        }
        return usersList
    }

    private fun setUserRecycler(userslist: List<User?>?) {

        uResponseAdapter?.userList = userslist
        uResponseAdapter?.notifyDataSetChanged()

    }

}