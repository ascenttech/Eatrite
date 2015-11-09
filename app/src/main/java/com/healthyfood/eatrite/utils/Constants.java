package com.healthyfood.eatrite.utils;

import com.healthyfood.eatrite.data.AllKitchenData;
import com.healthyfood.eatrite.data.AllLocationData;
import com.healthyfood.eatrite.data.AllDishesData;
import com.healthyfood.eatrite.data.AllOrdersData;
import com.healthyfood.eatrite.data.AllThemesData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class Constants {

    public static final String APP_NAME = " Eatrite ";
    public static final String LOG_TAG = " Eatrite ";

    // all the variables
    public static String userId;

    // all the classes
    public static final String ABOUT_US_ACTIVITY = " ABOUT US ACTIVITY ";
    public static final String ADDRESS_ACTIVITY = " ADDRESS ACTIVITY ";
    public static final String ALL_DISHES_ACTIVITY = " ALL DISHES ACTIVITY ";
    public static final String ALL_KITCHEN_ACTIVITY = " ALL KITCHEN ACTIVITY ";
    public static final String LANDING_ACTIVITY = " LANDING ACTIVITY ";
    public static final String LOGIN_ACTIVITY = " LOGIN ACTIVITY ";
    public static final String LOGIN_OR_REGISTER_ACTIVITY = " LOGIN OR REGISTER ACTIVITY ";
    public static final String ORDER_SUMMARY_ACTIVITY = " ORDER SUMMARY ACTIVITY ";
    public static final String PROFILE_ACTIVITY = " PROFILE ACTIVITY ";
    public static final String REGISTER_ACTIVITY = " REGISTER ACTIVITY ";
    public static final String SPLASH_SCREEN_ACTIVITY = " SPLASH SCREEN ACTIVITY ";
    public static final String YOUR_ITEMS_ACTIVITY = " YOUR ITEMS ACTIVITY ";

    // all the adapters
    public static final String ALL_DISHES_RECYCLER_ADAPTER = " ALL DISHES RECYCLER ADAPTER ";
    public static final String ALL_KITCHEN_RECYCLER_ADAPTER = " ALL KITCHEN RECYCLER ADAPTER ";
    public static final String ALL_THEMES_GRID_ADAPTER = " ALL THEMES GRID ADAPTER ";

    // all the async task
    public static final String FETCH_ALL_DISHES_ASYNC_TASK =" FETCH ALL DISHES ASYNC TASK ";
    public static final String FETCH_ALL_KITCHEN_ASYNC_TASK =" FETCH ALL KITCHEN ASYNC TASK ";
    public static final String FETCH_ALL_LOCATIONS_ASYNC_TASK =" FETCH ALL LOCATIONS ASYNC TASK ";
    public static final String FETCH_ALL_THEMES_ASYNC_TASK =" FETCH ALL THEMES ASYNC TASK ";
    public static final String FETCH_ORDER_HISTORY =" FETCH ORDER HISTORY ASYNC TASK ";
    public static final String FETCH_YOUR_ORDER_ASYNC_TASK =" FETCH YOUR ORDER ASYNC TASK ";
    public static final String LOGIN_ASYNC_TASK =" LOGIN ASYNC TASK ";
    public static final String REGISTER_ASYNC_TASK =" REGISTER ASYNC TASK ";


    // all the data
    public static ArrayList<AllDishesData> allDishesData;
    public static ArrayList<AllKitchenData> allKitchenData;
    public static ArrayList<AllLocationData> allLocationData;
    public static ArrayList<AllOrdersData> allOrdersData;
    public static ArrayList<AllThemesData> allThemesData;


    // All the maps
    public static HashMap<String,String> order;


    // all the  dev links
    public static String fetchAllLocations = "http://eatrite.food144.com/Json/locationSelection.php";

//    public static String fetchAllThemes = "http://eatrite.food144.com/Json/locationTheme.php?location_id=1";
    public static String fetchAllThemes = "http://eatrite.food144.com/Json/locationTheme.php";


//    public static String fetchAllKitchens = "http://eatrite.food144.com/Json/themeKitchen.php?theme_id=1";
    public static String fetchAllKitchens = "http://eatrite.food144.com/Json/themeKitchen.php";

//    public static String fetchAllDishes = "http://eatrite.food144.com/Json/kitchenDish.php?kitchen_id=1";
    public static String fetchAllDishes = "http://eatrite.food144.com/Json/kitchenDish.php";

    public static String fetchYourOrder = "http://eatrite.food144.com/Json/orderHistory.php?kitchen_dish_id=1";
//  public static String fetchYourOrder = "http://eatrite.food144.com/Json/orderHistory.php";

//  public static String login = "http://eatrite.food144.com/Json/userLogin.php?email=abc@gmail.com&password=123";
  public static String login = "http://eatrite.food144.com/Json/userLogin.php";

  public static String register = "http://eatrite.food144.com/Json/userRegistration.php";
//  public static String register = "http://eatrite.food144.com/Json/userRegistration.php";

    public static String address = "http://eatrite.food144.com/Json/userAddress.php";
//    public static String address = "http://eatrite.food144.com/Json/userAddress.php";


    // all production links
//    public static String fetchAllLocations = "http://eatrite.food144.com/Json/locationSelection.php";
//    public static String fetchAllThemes = "http://eatrite.food144.com/Json/locationTheme.php?location_id=1";
//    public static String fetchAllKitchens = "http://eatrite.food144.com/Json/themeKitchen.php?theme_id=1";
//    public static String fetchAllDishes = "http://eatrite.food144.com/Json/kitchenDish.php?kitchen_id=1";
//    public static String fetchYourOrder = "http://eatrite.food144.com/Json/yourOrder.php?kitchen_dish_id=1";

}
