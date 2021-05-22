package com.example.final_projects.Tab_Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Adapter extends FragmentPagerAdapter {
    ArrayList<Fragment> myFragment = new ArrayList<>();
    ArrayList<String> myFragmentTitle = new ArrayList<>();
    public Adapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myFragment.get(position);
    }

    @Override
    public int getCount() {
        return myFragment.size();
    }
    public void AddFragment(Fragment fragment, String title){
        myFragment.add(fragment);
        myFragmentTitle.add(title);
    }
    public String getFragmentTitle(int position){
        return myFragmentTitle.get(position);
    }
}
