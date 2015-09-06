package com.rharj.merrymayflower.activities;

import android.os.Bundle;
import android.view.Menu;
import com.rharj.merrymayflower.R;


/**
 * Created by Raji Oladayo on 3/3/2015.
 */
public class AboutActivity extends BaseActivity {

    private Menu myMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        getSupportActionBar().setTitle("About - Merry Mayflower");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        myMenu = menu;

        //Hiding the share and favorite menu on the menu activity
        menu.findItem(R.id.action_shares).setVisible(false);
        menu.findItem(R.id.action_favorites).setVisible(false);
        //menu.findItem(R.id.action_search).setVisible(false);

        return true;
    }
}
