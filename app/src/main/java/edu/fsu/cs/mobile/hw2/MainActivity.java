package edu.fsu.cs.mobile.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private Button button_linear;
    private Button button_relative;
    private Button button_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_linear = findViewById(R.id.button_linear);
        button_relative = findViewById(R.id.button_relative);
        button_table = findViewById(R.id.button_table);

        button_linear.setOnClickListener(linearListener);
        button_relative.setOnClickListener(relativeListener);
        button_table.setOnClickListener(tableListener);


    }

    View.OnClickListener linearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent linearActivity = new Intent(MainActivity.this, LinearLayoutActivity.class);
            startActivity(linearActivity);
        }
    };

    View.OnClickListener relativeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent relativeActivity = new Intent(MainActivity.this, RelativeLayoutActivity.class);
            startActivity(relativeActivity);
        }
    };

    View.OnClickListener tableListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent tableActivity = new Intent(MainActivity.this, TableLayoutActivity.class);
            startActivity(tableActivity);
        }
    };
}
