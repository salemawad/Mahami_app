package com.example.final_projects;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_projects.DBHelper.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class New_Task extends AppCompatActivity {

    private Button data_btn, time_btn;
    private static final String TAG = "New_Task";
    private DatePickerDialog.OnDateSetListener OnDateSetListener;
    int t2Hour, t2Minute, year, month, day;
    Button submit, View_Task;
    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);
        DB = new DBHelper(this);


        data_btn = findViewById(R.id.date_picker_actions);
        time_btn = findViewById(R.id.time_picker_actions);
        submit = findViewById(R.id.Submit_Button_new_task);
        EditText editName = findViewById(R.id.edit_name_new_task);
        EditText editDis = findViewById(R.id.edit_dis_new_task);
        View_Task = findViewById(R.id.View_task);

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
                month = month + 1;
                Log.d(TAG, "onDateSet: date: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;

                data_btn.setText(date);
            }

        };
        //===========================================================================
        //Listener For insert data into SQLite Database Table (UserDetails)
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskNameText = editName.getText().toString();
                String taskDesText = editDis.getText().toString();
                String task_date_text = data_btn.getText().toString();
                String task_time_text = time_btn.getText().toString();

                Boolean CheckInsertData = DB.InsertUserData(taskNameText, taskDesText, task_date_text, task_time_text);
                if (CheckInsertData == true) {
                    Toast.makeText(New_Task.this, "Successful entry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(New_Task.this, "An error occurred while entering", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //===========================================================================
        //Listener For View data into SQLite Database Table (UserDetails)
        View_Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(New_Task.this, "No Data Found ", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("Task Name :").append(res.getString(0)).append("\n");
                    buffer.append("Description :").append(res.getString(1)).append("\n");
                    buffer.append("Date Of Task :").append(res.getString(2)).append("\n\n");
                    buffer.append("Time Of Task :").append(res.getString(3)).append("\n");
//                    buffer.append("Date Of Birth :").append(res.getString(2)).append("\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(New_Task.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        // This is A new Task
    }
}

