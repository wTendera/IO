package com.example.io.io.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.io.io.R;
import com.example.io.io.database.ContestantsDataSource;
import com.example.io.io.database.OrdersDataSource;
import com.example.io.io.database.UsersDataSource;
import com.example.io.io.interfaces.Order;
import com.example.io.io.models.Contestant;
import com.example.io.io.models.Exercising;
import com.example.io.io.models.Running;
import com.example.io.io.models.Swimming;

import java.util.ArrayList;

public class CreateOrderActivity extends ActionBarActivity {
    long training_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        training_id = getIntent().getLongExtra("training_id", 0);
        setContentView(R.layout.activity_create_order);
        Spinner users = ((Spinner) findViewById(R.id.users));
        ContestantsDataSource contestantsDataSource = new ContestantsDataSource(this);

        ArrayList<Contestant> arr = (ArrayList<Contestant>) contestantsDataSource.getAllContestants();
        ArrayList<String> res = new ArrayList<>();
        for(Contestant c : arr) {
            res.add(c.getId() + " " + c.getName());
            System.out.println(c.getId() + " " + c.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, res);
        users.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_order, menu);
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

    public void saveOrder(View view) {
        OrdersDataSource dataSource = new OrdersDataSource(this);
        Spinner type = (Spinner) findViewById(R.id.type);
        Spinner user = ((Spinner) findViewById(R.id.users));
        int id =  Integer.parseInt(((String) user.getSelectedItem()).split(" ")[0]);
        switch(type.getSelectedItemPosition()) {
            case (0) :
                dataSource.createOrUpdateOrder(new Running(0,0,id, training_id, 0));
                break;
            case (1) :
                dataSource.createOrUpdateOrder(new Swimming(0,1,id, training_id, 0));
                break;
            case (2) :
                dataSource.createOrUpdateOrder(new Exercising(0,2,id, training_id, 0));
                break;
        }

        setResult(1, new Intent(CreateOrderActivity.this, TrainingActivity.class));
        finish();
    }
}
