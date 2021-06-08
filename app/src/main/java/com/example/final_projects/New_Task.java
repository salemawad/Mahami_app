package com.example.final_projects;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.final_projects.DBHelper.DBHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.Calendar;

import static com.vansuita.pickimage.dialog.PickImageDialog.build;

public class New_Task extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener {

    private Button time_btn;
    public Button submit, View_Task;
    DBHelper DB;
    public EditText editName, editDis;
    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;


    //=================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__task);
        DB = new DBHelper(this);
        time_btn = findViewById(R.id.time_picker_actions);
        submit = findViewById(R.id.Submit_Button_new_task);
        editName = findViewById(R.id.edit_name_new_task);
        editDis = findViewById(R.id.edit_dis_new_task);
        View_Task = findViewById(R.id.View_task);
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // inflate design custm toast
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.custm_toast,(ViewGroup)findViewById(R.id.con_toast));

        TextView textView15= view.findViewById(R.id.textView15);
        textView15.setText("Successful entry");

        // Create Toast
        final  Toast toast =new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,50,500);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        //===========================================================================
        //Listener For insert data into SQLite Database Table (UserDetails)
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskNameText = editName.getText().toString();
                String taskDesText = editDis.getText().toString();
                String task_time_text = time_btn.getText().toString();
                editName.setText("");
                editDis.setText("");

                Boolean CheckInsertData = DB.InsertUserData(taskNameText, taskDesText);
                if (CheckInsertData == true) {
                    toast.show();

                 //   Toast.makeText(New_Task.this, "Successful entry", Toast.LENGTH_SHORT).show();
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
        //==============================================================================
        //get Current date and set it to DataPicker
        Calendar now = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(
                New_Task.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH)// Inital day selection
        );

        //get Current Time and set it to DataPicker
        tpd = TimePickerDialog.newInstance(
                New_Task.this,
                now.get(Calendar.HOUR_OF_DAY), // Initial Hour selection
                now.get(Calendar.MINUTE),// Initial Minute selection
                false
        );

        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });

    }
        //=================================================================================
//        time_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");
//            }
//        });
//    }

        //=================================================================================
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 0);
//        startAlarm(c);
//    }

        //=================================================================================
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private void startAlarm(Calendar c) {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, Alarm_Receiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//        if (c.before(Calendar.getInstance())) {
//            c.add(Calendar.DATE, 1);
//        }
//
//        // Initialize Our Alarm
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
//    }
        //=================================================================================

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, monthOfYear);
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tpd.show(getSupportFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        now.set(Calendar.HOUR_OF_DAY, hourOfDay);
        now.set(Calendar.MINUTE, minute);

        Intent onclickactivity = new Intent(this, List_Taksk.class);
        PendingIntent content = PendingIntent.getActivity(this,0,onclickactivity,0);

        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(editName.getText().toString())
                .content(editDis.getText().toString())
                .time(now)
                .large_icon(R.drawable.smartphone)
                .small_icon(R.drawable.notfication)
                .build();
    }
}