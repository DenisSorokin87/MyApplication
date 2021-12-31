package com.denis.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.denis.myapplication.R;
import com.denis.myapplication.data.Speciality;
import com.denis.myapplication.data.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkersResponseAdapter extends RecyclerView.Adapter<WorkersResponseAdapter.WorkerViewHolder> {

    Context context;
    List<Worker> workersList;

    public WorkersResponseAdapter(Context context, List<Worker> workersList) {
        this.context = context;
        this.workersList = workersList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View workersItems = LayoutInflater.from(context).inflate(R.layout.worker_item, parent, false);
        return new WorkerViewHolder(workersItems);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        holder.firstName.setText(workersList.get(position).getFirstName());
        holder.lastName.setText(workersList.get(position).getLastName());
        holder.birthday.setText(workersList.get(position).getBirthday());

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(workersList.get(position).getAvatarURL())
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_baseline_error_outline_24)
//                .into(holder.workerImage);

        ArrayAdapter<String> strWorkersList = new ArrayAdapter<String>(context, R.layout.workers,  getSpecialitiesStringArray(workersList.get(position).getSpecialities()));
        holder.specialitiesList.setAdapter(strWorkersList);


    }

    private ArrayList<String> getSpecialitiesStringArray(List<Speciality> specialities) {

        ArrayList<String> strWorkerSpecialities= new ArrayList<>();

        for(int i =0; i < specialities.size(); i++){
            strWorkerSpecialities.add(i, specialities.get(i).getName());
        }
        return strWorkerSpecialities;
    }

    @Override
    public int getItemCount() {
        return workersList.size();
    }

    public class WorkerViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView firstName, lastName, birthday;
        ListView specialitiesList;
        ImageView workerImage;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            firstName = itemView.findViewById(R.id.worker_first_name);
            lastName = itemView.findViewById(R.id.worker_last_name);
            birthday = itemView.findViewById(R.id.worker_birthday);
            specialitiesList = itemView.findViewById(R.id.specialties_list);
            workerImage = itemView.findViewById(R.id.worker_image);



        }
    }
}
//    List<Speciality> workerSpecialities = workersList.get(position).getSpecialities();
