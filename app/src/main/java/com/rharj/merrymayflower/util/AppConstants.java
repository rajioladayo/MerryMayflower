package com.rharj.merrymayflower.util;

/**
 * Created by Raji Oladayo on 9/20/15.
 */
public class AppConstants {


    // Database version.
    public static final int DATABASE_VERSION = 1;

    // Database name.
    public static final String DATABASE_NAME = "FavoriteList";

    // Table names.
    public static final String TABLE_FAVORITE = "favourites";


    public static final String FAVORITE_ID = "favourite_id";
    public static final String FAVORITE_TITLE = "favourite_title";



    // Command to create a table of clients.
    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_FAVORITE + "("
            + FAVORITE_ID + " TEXT,"
            + FAVORITE_TITLE + " TEXT" + ")";
}
