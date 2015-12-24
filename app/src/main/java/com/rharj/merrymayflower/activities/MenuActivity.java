package com.rharj.merrymayflower.activities;

/**
 * Created by Raji Oladayo on 2/3/2015.
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.rharj.merrymayflower.adapter.ContentAdapter;
import com.rharj.merrymayflower.model.XmlValueModels;
import com.rharj.merrymayflower.xmlparser.XMLParser;

import java.io.IOException;
import com.rharj.merrymayflower.R;
import java.util.List;

public class MenuActivity extends BaseActivity {

    private ListView contentList;
    private EditText searchText;
    List<XmlValueModels> xModels = null;
    ContentAdapter adapter;
    private String title,contentDetails,hymmId,contentAuthor;
    private boolean ToggleSearch = false;
    private Toolbar mToolBar;
    private Menu myMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initializeControls();

        loadXmlFileContents();
    }

    public void initializeControls(){

        searchText = (EditText) findViewById(R.id.search_title);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void toggleSearchField(){
        if(!ToggleSearch){

            searchText.setVisibility(View.VISIBLE);
            ToggleSearch = true;
        }
        else
        {
            searchText.setVisibility(View.GONE);
            ToggleSearch = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                toggleSearchField();
                return true;
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

    public void loadXmlFileContents(){

        try{
            XMLParser xmlparser = new XMLParser();
            xModels = xmlparser.parse(this.getAssets().open("contents.xml"));
            adapter = new ContentAdapter(this, R.layout.row_items,xModels);
            contentList = (ListView) findViewById(R.id.content_list);
            contentList.setAdapter(adapter);
            contentList.setTextFilterEnabled(true);
            contentList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MenuActivity.this,ContentActivity.class);
                    XmlValueModels details = adapter.getItem(position);
                    title = details.getTitle();
                    contentDetails = details.getDetails();
                    contentAuthor = details.getAuthor();
                    hymmId = details.getId();
                    intent.putExtra("title", title);
                    intent.putExtra("details",contentDetails);
                    intent.putExtra("author",contentAuthor);
                    intent.putExtra("hymmId",hymmId);
                    startActivity(intent);


                }
            });

        }
        catch(IOException e){

            e.printStackTrace();
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
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action_shares).setVisible(false);
        menu.findItem(R.id.action_favorites).setVisible(false);

        return true;
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
