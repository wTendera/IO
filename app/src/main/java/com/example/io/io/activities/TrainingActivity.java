package com.example.io.io.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.io.io.R;
import com.example.io.io.database.OrdersDataSource;
import com.example.io.io.database.TrainingsDataSource;
import com.example.io.io.interfaces.Order;
import com.example.io.io.models.Training;

import java.util.ArrayList;

public class TrainingActivity extends ActionBarActivity {
    long trainingId;
    Training training;
    ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        trainingId =  getIntent().getLongExtra("trainingId", 0);
        TrainingsDataSource dataSource = new TrainingsDataSource(this);
        OrdersDataSource ordersDataSource = new OrdersDataSource(this);
        training = dataSource.getTraining(trainingId);
        orders = (ArrayList<Order>) ordersDataSource.getOrdersForTraining(trainingId);
        setViews();
    }
    private void setViews() {
        ((TextView) findViewById(R.id.name)).setText(training.getName());
        View view = new View(getApplicationContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3));
        view.setBackgroundColor(getResources().getColor(R.color.main));
        LinearLayout main = (LinearLayout)findViewById(R.id.main_layout);
        main.addView(view);
        for (Order o : orders) {
            addRow(o.contestant(this).getName(), o.getStatus(), o.getName(), o);
        }
    }

    private void addRow(String t1, String t2, String t3, Order o) {
        LinearLayout ll = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        params.setMargins(0, 0, 0, 10);

        ll.setLayoutParams(params);
        ll.setWeightSum(8f);
        ll.addView(getTextView(t1, 3f, true));
        ll.addView(getTextView(t2, 2f, true));
        ll.addView(getTextView(t3, 2f, true));
        ll.addView(getButton(o));
        LinearLayout main = (LinearLayout)findViewById(R.id.main_layout);
        main.addView(ll);
    }

    private Button getButton(final Order o) {
        Button button = new Button(getApplicationContext());
        button.setText(o.getCommandString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), o.command(getParent()), Toast.LENGTH_SHORT).show();

                ((Button)v).setText(o.getCommandString());
            }

        });
        return button;
    }



    private TextView getTextView(String s, float weight, boolean bold) {
        TextView textView = new TextView(getApplicationContext());
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.main));
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight));
        textView.setText(s);
        if(bold)
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        return textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("a " + requestCode + " b " + resultCode);
        if (requestCode == 2) {
            if(resultCode == 2){

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("oops");
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(TrainingActivity.this, CreateOrderActivity.class);
            intent.putExtra("training_id", trainingId);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
