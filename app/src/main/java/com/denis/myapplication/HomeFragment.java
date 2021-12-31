package com.denis.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denis.myapplication.adapters.WorkersResponseAdapter;
import com.denis.myapplication.data.User;
import com.denis.myapplication.data.WorkersListResponse;
import com.denis.myapplication.network.GetDataService;
import com.denis.myapplication.network.GetUsersFromMyJavaApp;
import com.denis.myapplication.network.RetrofitClientInstance;
import com.denis.myapplication.network.RetrofitUsersInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView workersRecycler;
    private WorkersResponseAdapter wResponseAdapter;


    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).showSecondFragment();
            }
        });

//        ArrayList<Speciality> specialityList_1 = new ArrayList<>();
//        specialityList_1.add(new Speciality(1, "Java Developer"));
//
//        ArrayList<Speciality> specialityList_2 = new ArrayList<>();
//        specialityList_2.add(new Speciality(1, "Java Developer"));
//        specialityList_2.add(new Speciality(2, "Android Developer"));
//
//        ArrayList<Speciality> specialityList_3 = new ArrayList<>();
//        specialityList_3.add(new Speciality(1, "Java Developer"));
//        specialityList_3.add(new Speciality(1, "Android Developer"));
//        specialityList_3.add(new Speciality(1, "C++ Developer"));
//
//        ArrayList<Worker> workersList = new ArrayList<>();
//        workersList.add(new Worker("Robert", "Gray", "11.11.12", "URL", specialityList_1));
//        workersList.add(new Worker("Ben", "Willmont", "10.01.02", "URL", specialityList_3));
//        workersList.add(new Worker("Ivan", "Rogov", "15.05.87", "URL", specialityList_2));
//        workersList.add((new Worker("Vasily", "Baranov", "12.12.90","URl", specialityList_1)));
//
//        WorkersListResponse responseList = new WorkersListResponse(workersList);
//
//        setWorkersRecycler(responseList);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<WorkersListResponse> call = service.getAllWorkers();
        call.enqueue(new Callback<WorkersListResponse>() {
            @Override
            public void onResponse(Call<WorkersListResponse> call, Response<WorkersListResponse> response) {

                WorkersListResponse responseList = response.body();
                setWorkersRecycler(responseList);

            }

            @Override
            public void onFailure(Call<WorkersListResponse> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                System.out.println(t.getMessage());
                System.out.println(t.getCause());
            }
        });

        GetUsersFromMyJavaApp userService = RetrofitUsersInstance.getRetrofitInstance(getView().getContext()).create(GetUsersFromMyJavaApp.class);
        Call<ArrayList<User>> userCall = userService.getUsersFromJavaApp();
        userCall.enqueue(new Callback<ArrayList<User>>() {


            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                System.out.println(t.getMessage());
                System.out.println(t.getCause());
            }
        });
    }

    private void setWorkersRecycler(WorkersListResponse responseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false);

        workersRecycler = getView().findViewById(R.id.workers_list);
        workersRecycler.setLayoutManager(layoutManager);

        wResponseAdapter = new WorkersResponseAdapter(getView().getContext(), responseList.getResponse());

        workersRecycler.setAdapter(wResponseAdapter);

    }

}
