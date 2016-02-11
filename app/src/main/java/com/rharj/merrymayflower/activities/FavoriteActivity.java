package com.rharj.merrymayflower.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.rharj.merrymayflower.R;
//import com.rharj.merrymayflower.database.FavoriteDataSource;
import com.rharj.merrymayflower.database.DatabaseHelper;
import com.rharj.merrymayflower.model.Favorite;

import java.util.List;


/**
 * Created by Raji Oladayo on 2/24/2015.
 */
public class FavoriteActivity extends BaseActivity {

    EditText searchFavorite;
    ListView listFavorite;
    private boolean ToggleSearch = false;
    private Menu myMenu = null;
    private Toolbar mToolBar;
    //private FavoriteDataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        /*datasource = new FavoriteDataSource(this);
        datasource.open();*/


        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.favorite_title);

        initializeControls();
        populateFavoriteList();
    }

    public void initializeControls(){

        searchFavorite = (EditText) findViewById(R.id.search_favorites);
        listFavorite = (ListView) findViewById(R.id.favorite_content);
    }

    public void toggleSearchField(){
        if(!ToggleSearch){

            searchFavorite.setVisibility(View.VISIBLE);
            ToggleSearch = true;
        }
        else
        {
            searchFavorite.setVisibility(View.GONE);
            ToggleSearch = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        myMenu = menu;

        //Hiding the share and favorite menu on the menu activity
        menu.findItem(R.id.action_shares).setVisible(false);
        menu.findItem(R.id.action_favorites).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:
                //launch the home screen
                navigateToHomeScreen();
                return true;

            case R.id.action_search:
                // search action
                toggleSearchField();
                return true;

            case R.id.action_menu_favorites:
                //launch Favorites screen
                launchFavoriteActivity();
                return true;
            case R.id.action_settings:
                //launch settings screen
                return true;
            case R.id.action_help:
                //launch help screen
                return true;
            case R.id.action_about:
                //To do share
                startAboutActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startAboutActivity(){
        intent = new Intent(this,AboutActivity.class);
        startActivity(intent);

    }
    public void launchFavoriteActivity(){
        intent  = new Intent(this,FavoriteActivity.class);
        startActivity(intent);
    }

    private void navigateToHomeScreen(){
        intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void populateFavoriteList(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        List<Favorite> values = databaseHelper.getAllFavorities();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        final ArrayAdapter<Favorite> adapter = new ArrayAdapter<Favorite>(this,
                android.R.layout.simple_list_item_1, values);
        listFavorite.setAdapter(adapter);
        listFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemId = adapter.getItem(position).toString();
                intent = new Intent(getBaseContext(), ContentActivity.class);
                finish();

            }
        });
    }

}
