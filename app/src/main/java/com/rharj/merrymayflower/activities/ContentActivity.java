package com.rharj.merrymayflower.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rharj.merrymayflower.custom.JustifiedTextView;
import com.rharj.merrymayflower.R;
import com.rharj.merrymayflower.database.DatabaseHelper;
import com.rharj.merrymayflower.model.Favorite;
import com.rharj.merrymayflower.util.AppUtils;

/**
 * Created by Raji Oladayo on 2/14/15.
 */
public class ContentActivity extends BaseActivity {

    private TextView contentDetails;
    private TextView authors;
    private String details,title,author,hymmId;
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
            case R.id.action_favorites:
                //adding hymms to the favorite list
                addHymmToFavorite();

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

        contentDetails = (TextView) findViewById(R.id.content_details);
        authors = (TextView) findViewById(R.id.authors);
    }

    public void setContents(String contentDetails){
        Spanned sp = Html.fromHtml(contentDetails);
        this.contentDetails.setText(sp);
        this.authors.setText(author.trim());
    }

    public void loadContentsFromIntent(){

        details = this.getIntent().getExtras().getString("details");
        title =   this.getIntent().getExtras().getString("title");
        author =  this.getIntent().getExtras().getString("author");
        hymmId =  this.getIntent().getExtras().getString("hymmId");

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

    private void addHymmToFavorite(){
        final DatabaseHelper  databaseHelper = new DatabaseHelper(getBaseContext());
        final Favorite favorite  = new Favorite();
        if(databaseHelper.hymmExist(hymmId)){
            //Ask to remove hymm from the list
            favorite.setId(hymmId);

            AlertDialog.Builder dialogs = new AlertDialog.Builder(ContentActivity.this);
            dialogs.setMessage("Remove hymm from favorite list?");
            dialogs.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    databaseHelper.deleteFavorite(favorite);
                }
            });

            dialogs.setNegativeButton("No", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });
            dialogs.show();

        }else{
            //add hymm to the favorite list
            favorite.setId(hymmId);
            favorite.setTitle(title);

            AlertDialog.Builder dialogs = new AlertDialog.Builder(ContentActivity.this);
            dialogs.setMessage("Add hymm to favorite list?");
            dialogs.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    databaseHelper.addFavorite(favorite);
                }
            });

            dialogs.setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            dialogs.show();
        }
    }
}
