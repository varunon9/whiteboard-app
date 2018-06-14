package me.varunon9.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    Utils utils;
    WebView whiteboardWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        utils = new Utils(this);

        // start WhiteboardService if not already running
        if (utils.isMyServiceRunning(WhiteboardService.class)) {
        } else {
            Intent service = new Intent(MainActivity.this, WhiteboardService.class);
            service.setAction(Constants.Actions.STARTFOREGROUND_ACTION);
            startService(service);
        }

        whiteboardWebView = (WebView) findViewById(R.id.whiteboardWebView);
        whiteboardWebView.loadUrl("file:///android_asset/whiteboard.html");
        whiteboardWebView.getSettings().setJavaScriptEnabled(true);
        whiteboardWebView.getSettings().setDomStorageEnabled(true);

        // showing Activity over lock screen - will work only if no password is set
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_close_service) {
            Intent service = new Intent(MainActivity.this, WhiteboardService.class);
            stopService(service);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        whiteboardWebView.destroy();
    }
}
