package com.mihajloandrejic.fitnessapp.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.button.MaterialButton;
import com.mihajloandrejic.fitnessapp.App;
import com.mihajloandrejic.fitnessapp.R;
import com.mihajloandrejic.fitnessapp.datamodels.Equipment;
import com.mihajloandrejic.fitnessapp.datamodels.Recipe;
import com.mihajloandrejic.fitnessapp.datamodels.Tasks;
import com.mihajloandrejic.fitnessapp.datamodels.Workout;

import com.mihajloandrejic.fitnessapp.helper.Type;

import org.w3c.dom.Text;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter {

    private Tasks tasks;
    private Context context;
    private List<Type> types;
    String TAG = "test";
    View view;

    public MainAdapter(List<Type> types, Tasks tasks, Context context) {
        this.types = types;
        this.tasks = tasks;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.section_workout, parent, false);
                return new ViewHolderOne(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.section_recipe_mindset, parent, false);
                return new ViewHolderTwo(view);
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.section_mindset, parent, false);
                return new ViewHolderThree(view);
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.section_tips, parent, false);
                return new ViewHolderFour(view);
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.section_btn, parent, false);

                return new ViewHolderFive(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int mode = types.get(position).getType();
        switch (mode)
        {
            //workout
            case 0:
                ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
                viewHolderOne.body_workout_text.setText(tasks.getWorkouts().getTitle());
                viewHolderOne.workout_icon_first.setBackgroundResource(R.drawable.ic_sat);
                viewHolderOne.workout_icon_first_about.setText(tasks.getWorkouts().getTime()+"min");

                Glide.with(context)
                        .load(tasks.getWorkouts().getBackground())
                        .dontTransform()
                        .placeholder(R.drawable.ic_img_placeholder)
                        .into(viewHolderOne.workout_image);

                List<Equipment> equipmentList = tasks.getWorkouts().getEquipment();

                if (equipmentList != null)
                {
                    for (int i = 0; i < equipmentList.size(); i++)
                    {
                        Equipment equipment = equipmentList.get(i);
                        if (equipment != null)
                        {
                            for (Equipment equipment1 : equipmentList)
                            {

                                if (equipment1.getItem().equals("weights"))

                                {
                                    viewHolderOne.pair2.setVisibility(View.VISIBLE);
                                    viewHolderOne.workout_icon_second_about.setText("Weights");
                                }
                                if (equipment1.getItem().equals("scale"))
                                {
                                    viewHolderOne.pair3.setVisibility(View.VISIBLE);
                                    viewHolderOne.workout_icon_third_about.setText("Scale");
                                }
                                // ovaj uslov je jer je json pogresan, u jednom slucaju pise mat a u drugom matt
                                if (equipment1.getItem().equals("mat")||equipment.getItem().equals("matt"))
                                {
                                    viewHolderOne.pair4.setVisibility(View.VISIBLE);
                                    viewHolderOne.workout_icon_fourth_about.setText("Matt");
                                }
                            }
                        }
                    }
                }
                else
                {
                    viewHolderOne.workout_icon_first.setBackgroundResource(R.drawable.ic_sat);
                    viewHolderOne.workout_icon_first_about.setText(tasks.getWorkouts().getTime()+"min");
                }

                break;

            //recipe
            case 1:

                ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
                viewHolderTwo.body_recipe_text.setText(tasks.getRecipes().getTitle());
                Glide.with(context)
                        .load(tasks.getRecipes().getBackground())
                        .dontTransform()
                        .placeholder(R.drawable.ic_img_placeholder)
                        .into(viewHolderTwo.body_recipe_image);
                break;
            //mindset
            case 2:

                ViewHolderThree viewHolderThree = (ViewHolderThree) holder;
                viewHolderThree.body_mindset_text.setText(tasks.getMindset().getTitle());
                Glide.with(context)
                        .load(tasks.getMindset().getBackground())
                        .dontTransform()
                        .placeholder(R.drawable.ic_img_placeholder)
                        .into(viewHolderThree.body_mindset_image);

                break;
            //workout tips
            case 3:
                ViewHolderFour viewHolderFour = (ViewHolderFour) holder;

                viewHolderFour.tips_text1_third.setText(tasks.getWorkoutTip());
                viewHolderFour.tips_text1.setText(App.getWeeklyP()+ " completed programs");
                viewHolderFour.tips_text1_second.setText(App.getMonthlyP()+ " completed programs");

                break;
            //button
            case 4:
//                ViewHolderFive viewHolderFive = (ViewHolderFive) holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position).getType();
    }

    //workout
    public class ViewHolderOne extends RecyclerView.ViewHolder {


        LinearLayout pair1;
        LinearLayout pair2;
        LinearLayout pair3;
        LinearLayout pair4;

        TextView workout_icon_first_about;
        TextView workout_icon_second_about;
        TextView workout_icon_third_about;
        TextView workout_icon_fourth_about;
        TextView body_workout_text;


        ImageView workout_icon_first;
        ImageView workout_image;
        ImageView body_recipe_image;


        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);


            workout_image = itemView.findViewById(R.id.body_workout_image);
            body_recipe_image = itemView.findViewById(R.id.body_recipe_image);

            pair1 = itemView.findViewById(R.id.pair1);
            pair2 = itemView.findViewById(R.id.pair2);
            pair3 = itemView.findViewById(R.id.pair3);
            pair4 = itemView.findViewById(R.id.pair4);

            workout_icon_first_about = itemView.findViewById(R.id.workout_icon_first_about);
            workout_icon_first = itemView.findViewById(R.id.workout_icon_first);
            workout_icon_second_about = itemView.findViewById(R.id.workout_icon_second_about);
            workout_icon_third_about = itemView.findViewById(R.id.workout_icon_third_about);
            workout_icon_fourth_about = itemView.findViewById(R.id.workout_icon_fourth_about);

            body_workout_text = itemView.findViewById(R.id.body_workout_text);

        }
    }

    //recipe
    public class ViewHolderTwo extends RecyclerView.ViewHolder {


        ImageView body_recipe_image;
        TextView body_recipe_text;


        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);

            body_recipe_image = itemView.findViewById(R.id.body_recipe_image);
            body_recipe_text = itemView.findViewById(R.id.body_recipe_text);
        }
    }
    //mindset
    public class ViewHolderThree extends RecyclerView.ViewHolder {


        ImageView body_mindset_image;
        TextView body_mindset_text;


        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);

            body_mindset_image = itemView.findViewById(R.id.body_mindset_image);
            body_mindset_text = itemView.findViewById(R.id.body_mindset_text);
        }
    }

    //workout tips
    public class ViewHolderFour extends RecyclerView.ViewHolder {

        TextView tips_text1_third;
        TextView tips_text1;
        TextView tips_text1_second;


        public ViewHolderFour(@NonNull View itemView) {
            super(itemView);
            tips_text1_third = itemView.findViewById(R.id.tips_text1_third);
            tips_text1 = itemView.findViewById(R.id.tips_text1);
            tips_text1_second = itemView.findViewById(R.id.tips_text1_second);
        }
    }

    //button
    public class ViewHolderFive extends RecyclerView.ViewHolder {


//        MaterialButton materialButton;

        public ViewHolderFive(@NonNull View itemView) {
            super(itemView);
//            materialButton = itemView.findViewById(R.id.bottom_button);
        }
    }

}
