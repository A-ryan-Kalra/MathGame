package com.prototype_1.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView yourScoreTextView;
    Button plaAgainButton;
    Button exitButton;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        yourScoreTextView=findViewById(R.id.scoreTextView);
        plaAgainButton=findViewById(R.id.playAgainButton);
        exitButton=findViewById(R.id.exitButton);

        score= getIntent().getIntExtra("score",0);
        yourScoreTextView.setText("Your Score : "+score);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        plaAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}