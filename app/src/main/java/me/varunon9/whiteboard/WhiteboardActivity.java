package me.varunon9.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

public class WhiteboardActivity extends AppCompatActivity {
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whiteboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        utils = new Utils(this);

        // start WhiteboardService if not already running
        if (utils.isMyServiceRunning(WhiteboardService.class)) {
        } else {
            Intent service = new Intent(WhiteboardActivity.this, WhiteboardService.class);
            service.setAction(Constants.Actions.STARTFOREGROUND_ACTION);
            startService(service);
        }

        // showing Activity over lock screen - will work only if no password is set
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.clearContentFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
