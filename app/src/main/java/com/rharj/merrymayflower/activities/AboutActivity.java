package com.rharj.merrymayflower.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.rharj.merrymayflower.R;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


/**
 * Created by Raji Oladayo on 3/3/2015.
 */
public class AboutActivity extends BaseActivity {

    private Menu myMenu = null;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.about_title);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        myMenu = menu;

        //Hiding the share and favorite menu on the menu activity
        menu.findItem(R.id.action_shares).setVisible(false);
        menu.findItem(R.id.action_favorites).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:
                //launch the home screen
                navigateToHomeScreen();
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

}
