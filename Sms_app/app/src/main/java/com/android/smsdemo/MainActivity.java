package com.android.smsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToInbox(View v) {
        Intent intent = new Intent(MainActivity.this, ReceiveSMSActivity.class);
        startActivity(intent);
    }

    public void goToCompose(View v) {
        Intent intent = new Intent(MainActivity.this, SendSmsActivity.class);
        startActivity(intent);
    }
}
