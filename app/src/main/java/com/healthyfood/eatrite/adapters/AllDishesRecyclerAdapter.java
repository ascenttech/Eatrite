package com.healthyfood.eatrite.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.activities.ProfileActivity;
import com.healthyfood.eatrite.custom.CustomTextView;
import com.healthyfood.eatrite.data.AllDishesData;
import com.healthyfood.eatrite.imageloader.ImageLoader;
import com.healthyfood.eatrite.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class AllDishesRecyclerAdapter extends RecyclerView.Adapter<AllDishesRecyclerAdapter.ViewHolder> {

    private Context context;
    private ImageView add,subtract,background;
    private CustomTextView description,price,quantity;
    ViewHolder vHolder;
    ArrayList<AllDishesData> allDishesData;
    private ImageLoader imageLoader;

    public AllDishesRecyclerAdapter(Context context, ArrayList<AllDishesData> allDishesData) {
        this.context = context;
        this.allDishesData = allDishesData;
        imageLoader = new ImageLoader(context);
        Log.d(Constants.LOG_TAG,Constants.ALL_DISHES_RECYCLER_ADAPTER);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View v;
        public ViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.row_all_dishes,viewGroup,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        findViews(viewHolder);
        setViews(position);
    }

    private void findViews(ViewHolder viewHolder) {

        vHolder = viewHolder;

        background = (ImageView) viewHolder.v.findViewById(R.id.background_image_all_dishes_activity);
        description = (CustomTextView) viewHolder.v.findViewById(R.id.description_text_included);
        add = (ImageView)viewHolder.v.findViewById(R.id.add_image_included);
        subtract = (ImageView)viewHolder.v.findViewById(R.id.subtract_image_included);
        price = (CustomTextView) viewHolder.v.findViewById(R.id.price_text_included);
        quantity = (CustomTextView) viewHolder.v.findViewById(R.id.quantity_text_included);


    }

    private void setViews(int position) {

        imageLoader.DisplayImage(Constants.allDishesData.get(position).getImage(),background);
        background.setTag("background_"+position);
        background.setOnClickListener(listener);

        price.setText(Constants.allDishesData.get(position).getPrice());
        quantity.setText(Constants.allDishesData.get(position).getQuantity());
        description.setText(Constants.allDishesData.get(position).getDescription());

        add.setTag("add_"+position);
        add.setOnClickListener(listener);

        subtract.setTag("subtract_"+position);
        subtract.setOnClickListener(listener);


    }

    public void add(int position){

        int startQuantity = Integer.parseInt(allDishesData.get(position).getQuantity());
        ++startQuantity;
        Constants.allDishesData.get(position).setQuantity(String.valueOf(startQuantity));

        String details = Constants.allDishesData.get(position).getPrice()+"_"+Constants.allDishesData.get(position).getQuantity();
        Constants.order.put(Constants.allDishesData.get(position).getId(),details);
    }

    public void subtract(int position){

        int startQuantity = Integer.parseInt(quantity.getText().toString());
        if(startQuantity != 0){

            startQuantity--;
            Constants.allDishesData.get(position).setQuantity(String.valueOf(startQuantity));

            String details = Constants.allDishesData.get(position).getPrice()+"_"+Constants.allDishesData.get(position).getQuantity();
            if(startQuantity == 0){

                Constants.order.remove(Constants.allDishesData.get(position).getId());
            }
            else{
                Constants.order.put(Constants.allDishesData.get(position).getId(),details);
            }


        }
    }

    @Override
    public int getItemCount() {
        return allDishesData.size();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String tagDetails[] = v.getTag().toString().split("_");

            String tag = tagDetails[0];
            int position = Integer.parseInt(tagDetails[1]);

            switch(tag){

                case "add": add(position);
                    notifyDataSetChanged();
                    break;
                case "subtract": subtract(position);
                    notifyDataSetChanged();
                    break;

            }



        }
    };

}
