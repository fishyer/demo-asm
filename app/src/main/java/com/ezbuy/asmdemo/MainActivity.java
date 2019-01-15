package com.ezbuy.asmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ezbuy.runtime.Cost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Cost
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "on Click");
                Toast.makeText(MainActivity.this, "on Click", Toast.LENGTH_SHORT).show();
            }
        });
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DebouncedClickPredictor.isFastClick(v)) {
//                    return;
//                }
//                Log.v("MainActivity", "on Click");
//                Toast.makeText(MainActivity.this, "on Click", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
