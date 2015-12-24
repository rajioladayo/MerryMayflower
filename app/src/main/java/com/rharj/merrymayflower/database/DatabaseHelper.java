package com.rharj.merrymayflower.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rharj.merrymayflower.model.Favorite;
import com.rharj.merrymayflower.util.AppConstants;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raji Oladayo on 9/20/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private List<Favorite> listFavorite;
    private Cursor cursor;
    private SQLiteDatabase database;

    // Database lock to prevent conflicts.
    public static final Object[] databaseLock = new Object[0];

    private final static String TAG = DatabaseHelper.class.getSimpleName();


    public DatabaseHelper(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AppConstants.CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_FAVORITE);
    }

    public Long addFavorite(Favorite favorite) {
        Long ret = null;

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {
                //Prepare the customer information that will be saved to the database
                ContentValues values = new ContentValues();

                values.put(AppConstants.FAVORITE_TITLE, favorite.getTitle());
                values.put(AppConstants.FAVORITE_ID, favorite.getId());

                //Attempt to insert the client information into the transaction table
                try {
                    ret = database.insert(AppConstants.TABLE_FAVORITE, null, values);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to favorite to database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }

    public int deleteFavorite(Favorite  favorite) {
        int ret = 0;

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
           database = getWritableDatabase();

            //Ensure the database exists
            if (database != null) {

                //Attempt to delete information into the favorite table
                try {
                    ret = database.delete(AppConstants.TABLE_FAVORITE, AppConstants.FAVORITE_ID + "= ?", new String[]{String.valueOf(favorite.getId())});
                } catch (Exception e) {
                    Log.e(TAG, "Unable to delete favorite from database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }
        return ret;
    }

    public List<Favorite> getAllFavorities() {
        listFavorite = new ArrayList<Favorite>();

        //Lock database for writing
        synchronized (databaseLock) {
            //Get a writable database
            database = getReadableDatabase();

            //Ensure the database exists
            if (database != null) {

                //Attempt to delete information into the favorite table
                try {
                    cursor = database.query(true, AppConstants.TABLE_FAVORITE, new String[]{
                            AppConstants.FAVORITE_ID, AppConstants.FAVORITE_TITLE}, null, null, null, null, null, null);

                    if (cursor.moveToFirst()) {
                        do {
                            Favorite favorite = new Favorite();
                            favorite.setId(cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.FAVORITE_ID)));
                            favorite.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.FAVORITE_TITLE)));
                            listFavorite.add(favorite);
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Unable to delete favorite from database " + e.getMessage());
                }
                //Close database connection
                database.close();
            }
        }

        return listFavorite;
    }

    public boolean hymmExist(String hymmId){
        synchronized (databaseLock){
            database = getReadableDatabase();
            if(database != null){
                try{
                    cursor = database.query(AppConstants.TABLE_FAVORITE,new String[]{AppConstants.FAVORITE_ID},
                            AppConstants.FAVORITE_ID + "=?", new String[]{hymmId},
                            null,null,null);
                    if(cursor.getCount() <= 0){
                        cursor.close();
                        return false;
                    }else{
                        cursor.close();
                        return true;
                    }

                }catch(Exception e){
                    Log.e(TAG,"Unable to query the database" + e.getMessage());
                }

                database.close();
            }
        }
        return false;
    }

}
