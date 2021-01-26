package com.mihajloandrejic.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.mihajloandrejic.fitnessapp.adapters.MainAdapter;
import com.mihajloandrejic.fitnessapp.datamodels.Completed;
import com.mihajloandrejic.fitnessapp.datamodels.Event;
import com.mihajloandrejic.fitnessapp.datamodels.Events;
import com.mihajloandrejic.fitnessapp.datamodels.Tasks;
import com.mihajloandrejic.fitnessapp.datamodels.Tip;
import com.mihajloandrejic.fitnessapp.datamodels.User;
import com.mihajloandrejic.fitnessapp.helper.Type;
import com.mihajloandrejic.fitnessapp.netutils.ApiInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    String TAG = "test";
    private ImageView userImage;
    private ImageView reorderBtn;
    private TextView userPoints;
    private TextView messageTime;
    private TextView userName;
    private TextView userLevel;
    private TextView headerDay;
    private MaterialButton calendarBtn;
    private MaterialButton materialPoints;
    Context context;
    private int dayC;
    private int monthC;
    private int date;
    private int time;
    private String day;
    private String month;
    ConstraintLayout constraintLayout;
    LazyLoader lazyLoader;
    private MaterialButton mondayBtn;
    private MaterialButton tuesdayBtn;
    private MaterialButton wednesdayBtn;
    private MaterialButton thursdayBtn;
    private MaterialButton fridayBtn;
    private MaterialButton saturdayBtn;
    private MaterialButton sundayBtn;
    private MaterialButton previousBtn = null;

    private String jsonUser;
    private String jsonEvents;
    private String jsonTask1;
    private String jsonTask4;
    private String jsonTask7;

    private int mondayId;
    private int tuesdayId;
    private int wednesdayId;
    private int thursdayId;
    private int fridayId;
    private int saturdayId;
    private int sundayId;
    List<Type> list;
    List<Type> list2;

    User user;
    Events events;
    Tasks tasks;
    Tasks taskEvent1;
    Tasks taskEvent2;
    Tasks taskEvent3;


    ColorStateList strokeColor = null;
    int strokeWidth = 0;
    ColorStateList backgroundTintList = null;
    ColorStateList textColors = null;


    RecyclerView rvMain;

    ImageView placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         context = this;

         previousBtn = null;



        Calendar calendar = Calendar.getInstance();
        dayC = calendar.get(Calendar.DAY_OF_WEEK);
        monthC = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);
        time = calendar.get(Calendar.HOUR_OF_DAY);
        currentMonth(monthC);
        currentDay(dayC);


//        recyclerview
        rvMain = findViewById(R.id.mainRv);
        Gson gson = new Gson();


         jsonUser = loadJSONFromAsset(this, "User");
         jsonEvents = loadJSONFromAsset(this, "Events");
         jsonTask1 = loadJSONFromAsset(this, "Event1");
         jsonTask4 = loadJSONFromAsset(this, "Event4");
         jsonTask7 = loadJSONFromAsset(this, "Event7");


         user = gson.fromJson(jsonUser, User.class);
         events =  gson.fromJson(jsonEvents, Events.class);
         taskEvent1 = gson.fromJson(jsonTask1, Tasks.class);
         taskEvent2 = gson.fromJson(jsonTask4, Tasks.class);
         taskEvent3 = gson.fromJson(jsonTask7, Tasks.class);



        initViews();
        initUserRv();
        initEventsRv();


        messageTime.setTextColor(getResources().getColor(R.color.message_color));

        for (int i = 0; i < events.getEvents().size() ; i++)
        {
        int id = events.getEvents().get(i).getEventId();
        String day = events.getEvents().get(i).getDay();
            switch (day)
            {
                case "Monday":
                    mondayId = id;
                    break;
                case "Tuesday":
                    tuesdayId = id;
                    break;
                case "Wednesday":
                    wednesdayId = id;
                    break;
                case "Thursday":
                    thursdayId = id;
                    break;
                case "Friday":
                    fridayId = id;
                    break;
                case "Saturday":
                    saturdayId = id;
                    break;
                case "Sunday":
                    sundayId = id;
                    break;
            }
        }

        mondayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        tuesdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        wednesdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        thursdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        fridayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        saturdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        sundayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));

        if (taskEvent1.isCompleted()) {
            //pon
            mondayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fill_color));
        } else {
            mondayBtn.setTextColor(getResources().getColor(R.color.no_completed_text));
            mondayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        }

        if (taskEvent2.isCompleted()) {
            //cet
            thursdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fill_color));
        } else {
            thursdayBtn.setTextColor(getResources().getColor(R.color.no_completed_text));
            thursdayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        }

        if (taskEvent3.isCompleted()) {
            //ned
            sundayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fill_color));
        } else {
            sundayBtn.setTextColor(getResources().getColor(R.color.no_completed_text));
            sundayBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.no_event_no_completed));
        }

        switch (dayC) {

            case Calendar.MONDAY:
                headerDay.setText("Monday");
                setColorClicked(mondayBtn);
                initTasksRv(mondayId);
                break;
            case Calendar.TUESDAY:
                headerDay.setText("Tuesday");
                setColorClicked(tuesdayBtn);
                initTasksRv(tuesdayId);
                break;
            case Calendar.WEDNESDAY:
                headerDay.setText("Wednesday");
                setColorClicked(wednesdayBtn);
                initTasksRv(wednesdayId);
                break;
            case Calendar.THURSDAY:
                headerDay.setText("Thursday");
                setColorClicked(thursdayBtn);
                initTasksRv(thursdayId);
                break;
            case Calendar.FRIDAY:
                headerDay.setText("Friday");
                setColorClicked(fridayBtn);
                initTasksRv(fridayId);
                break;
            case Calendar.SATURDAY:
                headerDay.setText("Saturday");
                setColorClicked(saturdayBtn);
                initTasksRv(saturdayId);
                break;
            case Calendar.SUNDAY:
                headerDay.setText("Sunday");
                setColorClicked(sundayBtn);
                initTasksRv(sundayId);
                break;

        }

        mondayBtn.setOnClickListener(v -> {
            headerDay.setText("Monday");
            setColorClicked(mondayBtn);
            initTasksRv(mondayId);
        });

        tuesdayBtn.setOnClickListener(v ->{

            headerDay.setText("Tuesday");
            setColorClicked(tuesdayBtn);
            initTasksRv(tuesdayId);

        });

        wednesdayBtn.setOnClickListener(v -> {

            headerDay.setText("Wednesday");
            setColorClicked(wednesdayBtn);
            initTasksRv(wednesdayId);

        });

        thursdayBtn.setOnClickListener(v ->{

            headerDay.setText("Thursday");
            setColorClicked(thursdayBtn);
            initTasksRv(thursdayId);
        });

        fridayBtn.setOnClickListener(v ->{

            headerDay.setText("Friday");
            setColorClicked(fridayBtn);
            initTasksRv(fridayId);

        });

        saturdayBtn.setOnClickListener(v ->{

            headerDay.setText("Saturday");
            setColorClicked(saturdayBtn);
            initTasksRv(saturdayId);

        });

        sundayBtn.setOnClickListener(v ->{

            headerDay.setText("Sunday");
            setColorClicked(sundayBtn);
            initTasksRv(sundayId);

        });
        reorderBtn.setOnClickListener(v -> {
            Toast.makeText(context, "You can reorder elements but not save your order...", Toast.LENGTH_SHORT).show();
        });
        


    }



    private void initViews() {
        userImage = findViewById(R.id.header_userImage1);
        reorderBtn = findViewById(R.id.header_reorder_document);
        messageTime = findViewById(R.id.header_message_time);
        userName = findViewById(R.id.header_userName);
        userLevel = findViewById(R.id.header_userLevel);
        headerDay = findViewById(R.id.header_day);
        calendarBtn = findViewById(R.id.header_calendar);
        materialPoints = findViewById(R.id.material_points);
        constraintLayout = findViewById(R.id.main_cons);
        lazyLoader = findViewById(R.id.loader);
        placeholder = findViewById(R.id.placeholder);

//        buttons
        mondayBtn = findViewById(R.id.header_btn_monday);
        tuesdayBtn = findViewById(R.id.header_btn_tuesday);
        wednesdayBtn = findViewById(R.id.header_btn_wednesday);
        thursdayBtn = findViewById(R.id.header_btn_thursday);
        fridayBtn = findViewById(R.id.header_btn_friday);
        saturdayBtn = findViewById(R.id.header_btn_saturday);
        sundayBtn = findViewById(R.id.header_btn_sunday);

    }


    private void initUserRv()
    {
        Picasso.get()
                .load(user.getUserImage())
                .fit().centerCrop()
                .placeholder(R.drawable.ic_img_placeholder)
                .into(userImage);

        userName.setText(user.getUserName());
        userName.setTextColor(getResources().getColor(R.color.username_text_color));

        userLevel.setText(user.getUserLevel());
        userLevel.setTextColor(getResources().getColor(R.color.fill_color));

        calendarBtn.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.fill_color));
        calendarBtn.setText(date + month);

        materialPoints.setText(String.valueOf(user.getUserPoints()));
        materialPoints.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.fill_color)));
        materialPoints.setStrokeWidth(6);

        currentHour(time);

        lazyLoader.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);

    }

    private void initEventsRv()
    {
        List<Event> eventsList = events.getEvents();

        App.setWeeklyP(events.getWeeklyProgress());
        App.setMonthlyP(events.getMonthlyProgress());

    }



    private void initTasksRv(int eventId)
    {
        rvMain.setVisibility(View.VISIBLE);

        if (eventId == 1)
        {
            tasks = taskEvent1;
        }
        else if (eventId == 2)
        {
            tasks = taskEvent2;
        }
        else if (eventId == 3)
        {
            tasks = taskEvent3;

        }
        else if (eventId == 0)
        {
            placeholder.setVisibility(View.VISIBLE);
            rvMain.setVisibility(View.GONE);
            return;
        }

        list = new ArrayList<Type>();

        Tip tip = new Tip(tasks.getWorkoutTip());
        Completed completed = new Completed(tasks.isCompleted());

        list.add(tasks.getWorkouts());
        list.add(tasks.getRecipes());
        list.add(tasks.getMindset());
        list.add(tip);
        list.add(completed);

        MainAdapter mainAdapter = new MainAdapter(list, tasks, MainActivity.this);

//        if (list2 != null)
//        {
//             mainAdapter = new MainAdapter(list2, tasks, MainActivity.this);
//        }
//        else
//        {
//         mainAdapter = new MainAdapter(list, tasks, MainActivity.this);
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvMain.setLayoutManager(linearLayoutManager);

        rvMain.setHasFixedSize(true);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvMain);

        rvMain.setAdapter(mainAdapter);

    }



    private void setColorClicked(MaterialButton clickBtn) {
        if (previousBtn != null) {

            previousBtn.setStrokeColor(strokeColor);
            previousBtn.setStrokeWidth(strokeWidth);
            previousBtn.setBackgroundTintList(backgroundTintList);
            previousBtn.setTextColor(textColors);
        }

        strokeColor = clickBtn.getStrokeColor();
        strokeWidth = clickBtn.getStrokeWidth();
        backgroundTintList = clickBtn.getBackgroundTintList();
        textColors = clickBtn.getTextColors();

        previousBtn = clickBtn;

        clickBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.fill_color_light));
        clickBtn.setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.fill_color)));
        clickBtn.setStrokeWidth(10);
        clickBtn.setTextColor(getResources().getColor(R.color.fill_color));
    }

//    callback za reorder

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition =  target.getAdapterPosition();

            Collections.swap(list, fromPosition, toPosition);
            list2 = list;
            Collections.swap(list2, fromPosition, toPosition);



            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    private void currentHour(int time)
    {

        int[] morning = new int[]{ 5,6,7,8,9,10,11 };
        int[] afternoon = new int[]{ 12,13,14,15,16};
        int[] evening = new int[]{ 17,18,19,20,21,22,23,0,1,2,3,4  };

        for (int part : morning)
        {
            if (time == part)
            {
                messageTime.setText("Good Morning ");
            }
        }
        for (int part : afternoon)
        {
            if (time == part)
            {
                messageTime.setText("Good Afternoon ");
            }
        }
        for (int part : evening)
        {
            if (time == part)
            {
                messageTime.setText("Good Evening ");
            }
        }
    }

    private void currentDay(int dayC)
    {
        switch (dayC) {

            case Calendar.MONDAY:
                break;
            case Calendar.TUESDAY:
                break;
            case Calendar.WEDNESDAY:
                break;
            case Calendar.THURSDAY:
                break;
            case Calendar.FRIDAY:
                break;
            case Calendar.SATURDAY:
                break;
            case Calendar.SUNDAY:
                break;

        }
    }


    private void currentMonth(int monthC)
    {
        switch (monthC) {

            case Calendar.JANUARY:
                month = " January";
                break;
            case Calendar.FEBRUARY:
                month = " February";
                break;
            case Calendar.MARCH:
                month = " March";
                break;
            case Calendar.APRIL:
                month = " April";
                break;
            case Calendar.MAY:
                month = " May";
                break;
            case Calendar.JUNE:
                month = " June";
                break;
            case Calendar.JULY:
                month = " July";
                break;
            case Calendar.AUGUST:
                month = " August";
                break;
            case Calendar.SEPTEMBER:
                month = " September";
                break;
            case Calendar.OCTOBER:
                month = " October";
                break;
            case Calendar.NOVEMBER:
                month = " November";
                break;
            case Calendar.DECEMBER:
                month = " December";
                break;
        }
    }



    public String loadJSONFromAsset(Activity activity, String fileName) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(fileName+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}