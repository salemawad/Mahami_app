package com.example.final_projects.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_projects.AdapterRe.Task_adapter;
import com.example.final_projects.DBHelper.DBHelper;
import com.example.final_projects.R;
import com.example.final_projects.modle.Tasks;

import java.util.ArrayList;

public class List_New_Task extends Fragment {

     DBHelper DB;
    Task_adapter task_adapter;


    public List_New_Task(){
        //Empty Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_new_task, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.rec);

        //====================================Casting=============================================
        DB = new DBHelper(getActivity());

        //====================================Casting=============================================
        //  tasks= (ArrayList<Tasks>) DB.getData();
        ArrayList<Tasks>  arrayList = DB.getArray();
        task_adapter=new Task_adapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(task_adapter);
        return view;
    }
}
