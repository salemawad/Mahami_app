package com.example.final_projects.AdapterRe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_projects.R;
import com.example.final_projects.modle.Tasks;

import java.util.ArrayList;

public class Task_adapter extends RecyclerView.Adapter<Task_adapter.myViewHolder> {
    ArrayList<Tasks> tasks;
    Context context ;

    public Task_adapter(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.task_item,null,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.txt.setText(tasks.get(position).getName());
        holder.txtD.setText(tasks.get(position).getDescribe());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView txt,txtD;
         public myViewHolder(@NonNull View itemView) {
            super(itemView);

             txt = itemView.findViewById(R.id.txt);
            txtD = itemView.findViewById(R.id.txtD);
        }
    }
}
