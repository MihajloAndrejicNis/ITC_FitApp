package com.mihajloandrejic.fitnessapp;

public class RetroMain {

//    package com.mihajloandrejic.fitnessapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
//import com.google.android.material.button.MaterialButton;
//import com.mihajloandrejic.fitnessapp.adapters.MainAdapter;
//import com.mihajloandrejic.fitnessapp.datamodels.Completed;
//import com.mihajloandrejic.fitnessapp.datamodels.Event;
//import com.mihajloandrejic.fitnessapp.datamodels.Events;
//import com.mihajloandrejic.fitnessapp.datamodels.Tasks;
//import com.mihajloandrejic.fitnessapp.datamodels.Tip;
//import com.mihajloandrejic.fitnessapp.datamodels.User;
//import com.mihajloandrejic.fitnessapp.helper.RoundedCornersTransform;
//import com.mihajloandrejic.fitnessapp.helper.Type;
//import com.mihajloandrejic.fitnessapp.netutils.ApiClient;
//import com.mihajloandrejic.fitnessapp.netutils.ApiInterface;
//import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Transformation;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//
//    ApiInterface apiInterface;
//    String TAG = "test";
//    private int eventId;
//    User user;
//    private ImageView userImage;
//    private ImageView reorderBtn;
//    private TextView userPoints;
//    private TextView messageTime;
//    private TextView userName;
//    private TextView userLevel;
//    private TextView headerDay;
//    private MaterialButton calendarBtn;
//    private MaterialButton materialPoints;
//    Context context;
//    private int dayC;
//    private int monthC;
//    private int date;
//    private int time;
//    private String day;
//    private String month;
//    ConstraintLayout constraintLayout;
//    LazyLoader lazyLoader;
//    private MaterialButton mondayBtn;
//    private MaterialButton tuesdayBtn;
//    private MaterialButton wednesdayBtn;
//    private MaterialButton thursdayBtn;
//    private MaterialButton fridayBtn;
//    private MaterialButton saturdayBtn;
//    private MaterialButton sundayBtn;
//
//
//    RecyclerView rvMain;
//
//
////    imageView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN); za menjanje boje kruga ()
//
////    materialButton.setBackgroundTintList(ContextCompat.getColorStateList(this@MyActivity, R.color.myCustomColor)); za menjanje pozadine dugmeta
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//         context = this;
//
//        Calendar calendar = Calendar.getInstance();
//        dayC = calendar.get(Calendar.DAY_OF_WEEK);
//        monthC = calendar.get(Calendar.MONTH);
//        date = calendar.get(Calendar.DATE);
//        time = calendar.get(Calendar.HOUR_OF_DAY);
//        currentMonth(monthC);
//
//
//
////        recyclerview
//        rvMain = findViewById(R.id.mainRv);
//
//
//
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//        initUserRv();
//
//        initEventsRv();
//
//        initViews();
//
//
//        mondayBtn.setOnClickListener(v -> {
////                if (response.body().getEvents().get(0).getDay().equals("Monday") && response.body().getEvents().get(0).getEventId() != 0)
////                {
////                    initTasksRv(events.get(0).getEventId());
////                }
//
//            headerDay.setText("Monday");
//        });
//        tuesdayBtn.setOnClickListener(v -> headerDay.setText("Thursday"));
//
//        wednesdayBtn.setOnClickListener(v -> headerDay.setText("Wednesday"));
//
//        thursdayBtn.setOnClickListener(v -> headerDay.setText("Thursday"));
//
//        fridayBtn.setOnClickListener(v -> headerDay.setText("Friday"));
//
//        saturdayBtn.setOnClickListener(v -> headerDay.setText("Saturday"));
//
//        sundayBtn.setOnClickListener(v -> headerDay.setText("Sunday"));
//
//
//    }
//
//    private void currentHour(int time)
//    {
//
//        int[] morning = new int[]{ 5,6,7,8,9,10,11 };
//        int[] afternoon = new int[]{ 12,13,14,15,16};
//        int[] evening = new int[]{ 17,18,19,20,21,22,23,0,1,2,3,4  };
//
//        for (int part : morning)
//        {
//            if (time == part)
//            {
//                messageTime.setText("Good Morning ");
//            }
//        }
//
//        for (int part : afternoon)
//        {
//            if (time == part)
//            {
//                messageTime.setText("Good Afternoon ");
//            }
//        }
//
//        for (int part : evening)
//        {
//            if (time == part)
//            {
//                messageTime.setText("Good Evening ");
//            }
//        }
//
//    }
//
//
//    private void currentMonth(int monthC)
//    {
//        switch (monthC) {
//
//            case Calendar.JANUARY:
//                month = " January";
//                break;
//            case Calendar.FEBRUARY:
//                month = " February";
//                break;
//            case Calendar.MARCH:
//                month = " March";
//                break;
//            case Calendar.APRIL:
//                month = " April";
//                break;
//            case Calendar.MAY:
//                month = " May";
//                break;
//            case Calendar.JUNE:
//                month = " June";
//                break;
//            case Calendar.JULY:
//                month = " July";
//                break;
//            case Calendar.AUGUST:
//                month = " August";
//                break;
//            case Calendar.SEPTEMBER:
//                month = " September";
//                break;
//            case Calendar.OCTOBER:
//                month = " October";
//                break;
//            case Calendar.NOVEMBER:
//                month = " November";
//                break;
//            case Calendar.DECEMBER:
//                month = " December";
//                break;
//        }
//    }
//
//    private void initViews() {
//        userImage = findViewById(R.id.header_userImage);
//        reorderBtn = findViewById(R.id.header_reorder_document);
//        messageTime = findViewById(R.id.header_message_time);
//        userName = findViewById(R.id.header_userName);
//        userLevel = findViewById(R.id.header_userLevel);
//        headerDay = findViewById(R.id.header_day);
//        calendarBtn = findViewById(R.id.header_calendar);
//        materialPoints = findViewById(R.id.material_points);
//        constraintLayout = findViewById(R.id.main_cons);
//        lazyLoader = findViewById(R.id.loader);
//
////        buttons
//        mondayBtn = findViewById(R.id.header_btn_monday);
//        tuesdayBtn = findViewById(R.id.header_btn_tuesday);
//        wednesdayBtn = findViewById(R.id.header_btn_wednesday);
//        thursdayBtn = findViewById(R.id.header_btn_thursday);
//        fridayBtn = findViewById(R.id.header_btn_friday);
//        saturdayBtn = findViewById(R.id.header_btn_saturday);
//        sundayBtn = findViewById(R.id.header_btn_sunday);
//
//
//    }
//
//    private void initUserRv()
//    {
//        Call<User> usersGetCall = apiInterface.getUsers();
//        usersGetCall.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//
//             App.setUser(response.body());
//             user = App.getUser();
//                final Transformation transformation = new RoundedCornersTransform();
//
//
//                Picasso.get()
//                        .load(user.getUserImage())
//                        .transform(transformation)
//                        .fit().centerCrop()
//                        .placeholder(R.drawable.ic_img_placeholder)
//                        .into(userImage);
//
//
//                userName.setText(user.getUserName());
//                userLevel.setText(user.getUserLevel());
//
//                calendarBtn.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.fill_color));
//                materialPoints.setBackground(getResources().getDrawable(R.drawable.ic_points_rec));
//
//                calendarBtn.setText(date + month);
//                materialPoints.setText(String.valueOf(user.getUserPoints()));
//                currentHour(time);
//
//                lazyLoader.setVisibility(View.GONE);
//                constraintLayout.setVisibility(View.VISIBLE);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//
//
//
//    }
//
//    private void initEventsRv()
//    {
//        Call<Events> eventsCall = apiInterface.getEvents();
//        eventsCall.enqueue(new Callback<Events>() {
//            @Override
//            public void onResponse(Call<Events> call, Response<Events> response) {
//
//                List<Event> events = response.body().getEvents();
//
//                App.setWeeklyP(response.body().getWeeklyProgress());
//                App.setMonthlyP(response.body().getMonthlyProgress());
//
////                mozda da se ovde pamti 7 dana 7 id a
//                App.setEventId(events.get(0).getEventId());
//
//
////                mozda cu mroati da narpvim recyclerView...
//
////                App.setEventId(events.get(0).getEventId());
//                int proba = events.get(0).getEventId();
//
//
//                initTasksRv(events.get(0).getEventId());
//
//            }
//
//            @Override
//            public void onFailure(Call<Events> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//
//    }
//
//    private void initTasksRv(int eventId)
//    {
//        Call<Tasks> tasksCall = apiInterface.getTasks(eventId);
//        tasksCall.enqueue(new Callback<Tasks>() {
//            @Override
//            public void onResponse(Call<Tasks> call, Response<Tasks> response) {
////
//                Tasks tasks = response.body();
//                List<Type> list = new ArrayList<Type>();
//                Tip tip = new Tip(tasks.getWorkoutTip());
//                Completed completed = new Completed(tasks.isCompleted());
//
//                list.add(tasks.getWorkouts());
//                list.add(tasks.getRecipes());
//                list.add(tasks.getMindset());
//                list.add(tip);
//                list.add(completed);
//
//                MainAdapter mainAdapter = new MainAdapter(list, tasks, MainActivity.this);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
//                rvMain.addItemDecoration(dividerItemDecoration);
//                rvMain.setHasFixedSize(true);
//                rvMain.setLayoutManager(linearLayoutManager);
//                rvMain.setAdapter(mainAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<Tasks> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//
//    }
//
//}
}
