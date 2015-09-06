package com.example.io.io.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.io.io.R;
import com.example.io.io.database.ContestantsDataSource;
import com.example.io.io.database.TrainingsDataSource;
import com.example.io.io.models.Contestant;
import com.example.io.io.models.Training;

public class CreateContestantActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contestant);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_contestant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void saveContestant(View view) {
        Contestant contestant = new Contestant(0, ((EditText) findViewById(R.id.name)).getText().toString());
        ContestantsDataSource dataSource = new ContestantsDataSource(this.getBaseContext());
        long id = dataSource.createOrUpdateContestant(contestant);
        CreateContestantActivity.this.finish();
    }
}
