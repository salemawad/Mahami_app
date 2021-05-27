package com.example.final_projects.modle;

import android.os.Parcel;
import android.os.Parcelable;

public class Tasks implements Parcelable {

    private  String name ;
    private  String describe ;

    public Tasks() {
    }

    public Tasks( String name, String describe) {

        this.name = name;
        this.describe= describe;


    }
    protected Tasks(Parcel in) {


        name = in.readString();
       describe= in.readString();
    }

    public static final Parcelable.Creator<Tasks> CREATOR = new Parcelable.Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        describe = describe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(name);
        dest.writeString(describe);

    }
}
