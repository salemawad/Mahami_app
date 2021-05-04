package com.example.final_projects.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.final_projects.Feedback_Ativity;
import com.example.final_projects.New_Task;
import com.example.final_projects.R;

public class Home_Fragment extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        final CardView new_text = view.findViewById(R.id.new_task);
        new_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), New_Task.class);
                startActivity(intent);

            }
        });

        final CardView feedback = view.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Feedback_Ativity.class);
                startActivity(intent);

            }
        });
    return view;

    }
}
