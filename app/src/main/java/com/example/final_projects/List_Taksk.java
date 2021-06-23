package com.example.final_projects;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.final_projects.Fragment.List_Last_Task;
import com.example.final_projects.Fragment.List_New_Task;
import com.example.final_projects.Tab_Adapter.Adapter;
import com.google.android.material.tabs.TabLayout;

public class List_Taksk extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__taksk);
        tabLayout = findViewById(R.id.tap_layout);
        viewPager = findViewById(R.id.view_pager);
        String NewList = getString(R.string.New_Task);
        String LastList = getString(R.string.Last_Task);
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //=================================================================================================
        Adapter home_pager_adapter = new Adapter(getSupportFragmentManager());
        home_pager_adapter.AddFragment(new List_New_Task(), NewList);
        home_pager_adapter.AddFragment(new List_Last_Task(), LastList);
        //=================================================================================================
        viewPager.setAdapter(home_pager_adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(home_pager_adapter.getFragmentTitle(0));
        tabLayout.getTabAt(1).setText(home_pager_adapter.getFragmentTitle(1));
    }
}