package com.rharj.merrymayflower.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.rharj.merrymayflower.R;

/**
 * Created by Raji Oladayo on 2/8/15.
 */
public class BaseActivity extends ActionBarActivity {

    private ProgressDialog mProgressDialog;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }

   /* public void startAboutActivity(){
        intent = new Intent(BaseActivity.this,AboutActivity.class);
        startActivity(intent);

    }
    public void launchFavoriteActivity(){
        intent  = new Intent(BaseActivity.this,FavoriteActivity.class);
        startActivity(intent);
    }

    private void navigateToHomeScreen(){
        intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }*/

}
