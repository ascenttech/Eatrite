package com.healthyfood.eatrite.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.activities.AllKitchenActivity;
import com.healthyfood.eatrite.data.AllThemesData;
import com.healthyfood.eatrite.imageloader.ImageLoader;
import com.healthyfood.eatrite.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ADMIN on 31-10-2015.
 */
public class AllThemesGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AllThemesData> allThemesData;
    private ImageLoader imageLoader;
    private int images[] = {R.drawable.background_about_us,R.drawable.background_landing,R.drawable.background_splash_screen,R.drawable.kitchen2,R.drawable.background_about_us,R.drawable.background_landing,R.drawable.background_splash_screen,R.drawable.kitchen2};

    public AllThemesGridAdapter(Context context) {
        this.context = context;
    }

    public AllThemesGridAdapter(Context context, ArrayList<AllThemesData> allThemesData) {
        this.context = context;
        this.allThemesData = allThemesData;
        imageLoader = new ImageLoader(context);
        Log.d(Constants.LOG_TAG,Constants.ALL_THEMES_GRID_ADAPTER);

    }

    @Override
    public int getCount() {
        return 5;
//        return allThemesData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_all_themes, parent, false);
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.background_layout_all_themes);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,300);
        layoutParams.setMargins(5,5,5,5);
        rl.setLayoutParams(layoutParams);
        rl.setBackgroundResource(images[position]);
//        imageLoader.DisplayImage(Constants.allThemesData.get(position).getThemeImage());
        rl.setTag("layout_"+position);
        rl.setOnClickListener(listener);

        return v;

    }
    /**This will only fire for
     * LOLLIPOP supported devices
     * **/
    public void changeActivity(int left,int top,View v){

//        int radius = Math.max(left,top);
//        Animator anim = ViewAnimationUtils.createCircularReveal(v, left, top, 0, radius);
//        anim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
////                super.onAnimationEnd(animation);
//
//
//            }
//        });
//
//        anim.start();


    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String tagDetails[] = v.getTag().toString().split("_");
            int position = Integer.parseInt(tagDetails[1]);


//            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_NAME,Context.MODE_PRIVATE);
//            String checkForMaterialDesign = sharedPreferences.getString("materialDesign","null");
//            if(checkForMaterialDesign.equalsIgnoreCase("null")){
//
//                Intent i = new Intent(context, AllKitchenActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//
//            }
//            else{

//                changeActivity(v.getLeft(),v.getTop(),v);

                Intent i = new Intent(context, AllKitchenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("position",String.valueOf(position));
                context.startActivity(i);


//            }

        }
    };

}
