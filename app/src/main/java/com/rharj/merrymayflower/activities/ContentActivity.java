package com.rharj.merrymayflower.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rharj.merrymayflower.custom.JustifiedTextView;
import com.rharj.merrymayflower.R;

/**
 * Created by Raji Oladayo on 2/14/15.
 */
public class ContentActivity extends BaseActivity {

    private JustifiedTextView contentDetails;
    private TextView authors;
    private String details;
    private String title;
    private String author;
    private Toolbar mToolBar;
    private Menu myMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);

        loadContentsFromIntent();

        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);

        initializeControls();
        setContents(details);
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

    public void initializeControls(){

        contentDetails = (JustifiedTextView) findViewById(R.id.content_details);
        authors = (TextView) findViewById(R.id.authors);
    }

    public void setContents(String contentDetails){

        this.contentDetails.setText(contentDetails);
        this.authors.setText(author.trim());
    }

    public void loadContentsFromIntent(){

        details = (String) this.getIntent().getExtras().getString("details");
        title = (String) this.getIntent().getExtras().getString("title");
        author = (String) this.getIntent().getExtras().getString("author");
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
        menu.findItem(R.id.action_search).setVisible(false);

        return true;
    }

}
