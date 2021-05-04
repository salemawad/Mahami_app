package com.example.final_projects;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class New_Task extends AppCompatActivity {

    private Button data_btn,time_btn;
    private static final String TAG = "New_Task";
    private DatePickerDialog.OnDateSetListener OnDateSetListener;
    int t2Hour, t2Minute, year, month, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);
        data_btn = findViewById(R.id.date_picker_actions);
        time_btn = findViewById(R.id.time_picker_actions);

        //====================================================================TIME BUTTON=========================================================================
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //تعريف time picker dialog
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(
                        New_Task.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;
                                //تخزين الساعة والدقائق ك نص
                                String time = t2Hour + ":" + t2Minute;
                                //جعل الفورمات 24 ساعة
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat f24hour = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date data = f24hour.parse(time);
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat f12Hour = new SimpleDateFormat(
                                            "hh:mm:aa"
                                    );
                                    //Set selected time on text view
                                    assert data != null;
                                    time_btn.setText(f12Hour.format(data));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //Set transparent background
                timePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed previous selected time
                timePickerDialog1.updateTime(t2Hour, t2Minute);
                //Show Dialog
                timePickerDialog1.show();
            }
        });

//===========================================================DATE BUTTON====================================================================================
        data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        New_Task.this, android.R.style.
                        Theme_Holo_Light_Dialog_MinWidth, OnDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        OnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int day, int month, int year) {
                Log.d(TAG, "onDateSet: date: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                data_btn.setText(date);
            }
        };
    }
}

