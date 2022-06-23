package com.prototype_1.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button additionButton,subtractionButton,multiplicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        additionButton=findViewById(R.id.additionButton);
        multiplicationButton=findViewById(R.id.multiplicationButton);
        subtractionButton=findViewById(R.id.subtractionButton);

        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PerformanceActivity.class);
                intent.putExtra("tag",1);
                startActivity(intent);
                finish();
            }
        });
        subtractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),game.class);
                intent.putExtra("tag",2);
                startActivity(intent);
                finish();
            }
        });
    }
}