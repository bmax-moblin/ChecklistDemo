package dev.bmax.checklistdemo.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import dev.bmax.checklistdemo.R;
import dev.bmax.checklistdemo.abs.HomeScreen;
import dev.bmax.checklistdemo.data.AsyncMessageProvider;
import dev.bmax.checklistdemo.logic.HomeScreenLogic;

/**
 * Clicking on the floating action button starts multiple parallel asynchronous tasks.
 * Each one of them will finish at a different point in time. The app logic cannot proceed
 * until all of the tasks are finished. A Checklist object is used to synchronize the results
 * of the tasks. The final message is build from pieces and shown.
 */
public class HomeScreenActivity extends AppCompatActivity implements HomeScreen.Presentation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLogic = new HomeScreenLogic(this, new AsyncMessageProvider());

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogic.actionButtonPressed();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mFab, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showMessage("Under construction.");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Private property.
     */
    private FloatingActionButton mFab;
    private HomeScreen.Logic mLogic;
}
