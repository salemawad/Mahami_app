package com.example.final_projects;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.final_projects.Alarm.Alarm_Receiver;
import com.example.final_projects.Alarm.TimePickerFragment;
import com.example.final_projects.DBHelper.DBHelper;

import java.util.Calendar;

public class New_Task extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button time_btn;
    private static final String TAG = "New_Task";
    private DatePickerDialog.OnDateSetListener OnDateSetListener;
    int t2Hour, t2Minute, year, month, day;
    public Button submit, View_Task;
    DBHelper DB;
    public EditText editDis ,editName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);
        DB = new DBHelper(this);

        time_btn = findViewById(R.id.time_picker_actions);
        submit = findViewById(R.id.Submit_Button_new_task);
        EditText editName = findViewById(R.id.edit_name_new_task);
        EditText editDis = findViewById(R.id.edit_dis_new_task);
        View_Task = findViewById(R.id.View_task);
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //===========================================================================
        //Listener For insert data into SQLite Database Table (UserDetails)
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskNameText = editName.getText().toString();
                String taskDesText = editDis.getText().toString();
                String task_time_text = time_btn.getText().toString();

                Boolean CheckInsertData = DB.InsertUserData(taskNameText, taskDesText);
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
                 //   buffer.append("Time Of Task :").append(res.getString(3)).append("\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(New_Task.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        //=================================================================================
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    //=================================================================================
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        startAlarm(c);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm_Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1,intent,0);
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1);
        }

        // Initialize Our Alarm
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,0);
        startAlarm(c);
    }
}

