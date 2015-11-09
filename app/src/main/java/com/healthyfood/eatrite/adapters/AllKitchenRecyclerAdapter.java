package com.healthyfood.eatrite.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.activities.AllDishesActivity;
import com.healthyfood.eatrite.custom.CustomTextView;
import com.healthyfood.eatrite.data.AllKitchenData;
import com.healthyfood.eatrite.imageloader.ImageLoader;
import com.healthyfood.eatrite.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class AllKitchenRecyclerAdapter extends RecyclerView.Adapter<AllKitchenRecyclerAdapter.ViewHolder> {

    private Context context;

    private ImageView restaurantImage ;
    private CustomTextView restaurantName ;
    private ImageLoader imageLoader;
    private ArrayList<AllKitchenData> allKitchenData;

    public AllKitchenRecyclerAdapter(Context context, ArrayList<AllKitchenData> allKitchenData) {
        this.context = context;
        this.allKitchenData = allKitchenData;
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_all_kitchen,viewGroup,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        findViews(viewHolder);
        setViews(position);

    }

    public void findViews(ViewHolder viewHolder){

        restaurantImage = (ImageView) viewHolder.view.findViewById(R.id.restaurant_image_all_kitchen_activity);
        restaurantName = (CustomTextView)viewHolder.view.findViewById(R.id.restaurant_text_all_kitchen_activity);

    }

    public void setViews(int position){

        imageLoader.DisplayImage(Constants.allKitchenData.get(position).getImage(),restaurantImage);

        restaurantName.setText(Constants.allKitchenData.get(position).getName());
        restaurantName.setTag("name_"+position);
        restaurantName.setOnClickListener(listener);


    }

    @Override
    public int getItemCount() {
        return allKitchenData.size();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String tagDetails[] = v.getTag().toString().split("_");
            int position = Integer.parseInt(tagDetails[1]);

            Intent i = new Intent(context, AllDishesActivity.class);
            i.putExtra("position",position);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

//            String tagDetails[] = v.getTag().toString().split("_");
//            int position = Integer.parseInt(tagDetails[1]);
//

        }
    };


}
