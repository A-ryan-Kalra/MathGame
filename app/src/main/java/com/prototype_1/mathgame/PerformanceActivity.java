package com.prototype_1.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class PerformanceActivity extends AppCompatActivity {

    TextView performanceScoreTextView;
    TextView performanceLifeTextView;
    TextView performanceTimeTextView;
    TextView performanceNextQuestionTextView;

    Button performanceOkButton;
    Button performanceNextQuestionButton;

    Random random=new Random();

    int userChoice,sum;
    int number1,number2;
    int UserScore,UserLife=3;

    CountDownTimer timer1;

    private static final long START_TIME_IN_MILIS=10000;
    long TIME_LEFT_IN_MILIS=START_TIME_IN_MILIS;

    EditText performanceWriteAnswerEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        performanceScoreTextView=findViewById(R.id.performanceDisplayScoreTextView);
        performanceLifeTextView=findViewById(R.id.performanceDisplayLifeTextView);
        performanceTimeTextView=findViewById(R.id.performanceDisplayTimeTextView);
        performanceNextQuestionTextView=findViewById(R.id.performanceNextQuestionTextView);

        performanceOkButton=findViewById(R.id.performanceOkButton);
        performanceNextQuestionButton=findViewById(R.id.performanceNextQuestionButton);

        performanceWriteAnswerEditText=findViewById(R.id.performanceWriteAnswerEditTExt);

        performanceScoreTextView.setText(""+UserScore);
        performanceLifeTextView.setText(""+UserLife);

        performanceGameContinue();


        performanceOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTime();
               userChoice= Integer.parseInt(performanceWriteAnswerEditText.getText().toString());
                if(userChoice==sum){
                    performanceNextQuestionTextView.setText("Congratulations, You are correct!");
                    UserScore=UserScore+10;
                    performanceScoreTextView.setText(""+UserScore);
                    performanceLifeTextView.setText(""+UserLife);
                }
                else
                {
                    performanceNextQuestionTextView.setText("Oof, You are wrong this time!");
                    UserScore=UserScore-5;
                    UserLife--;
                    performanceScoreTextView.setText(""+UserScore);
                    performanceLifeTextView.setText(""+UserLife);
                }
                performanceWriteAnswerEditText.setText("");
            }
        });
        performanceNextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTime();
                resetTime();
                String s=performanceWriteAnswerEditText.getText().toString();
                if (UserLife==0){
                    Toast.makeText(getApplicationContext(), "Game Over!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("score",UserScore);
                    startActivity(intent);
                    finish();
                }else {
                    performanceGameContinue();
                }
            }
        });
    }
    public void performanceGameContinue(){
        number1=random.nextInt(70);
        number2=random.nextInt(70);

        sum=number1+number2;

        performanceNextQuestionTextView.setText(number1+" + "+number2);
        startTimer();
    }
    public void startTimer(){
        timer1=new CountDownTimer(TIME_LEFT_IN_MILIS,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TIME_LEFT_IN_MILIS=millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                pauseTime();
                resetTime();
                updateCountDown();
                performanceOkButton.setEnabled(false);
                performanceWriteAnswerEditText.setHint("Time is up!!");
            }
        }.start();
    }
    public void updateCountDown(){
        int seconds= (int) ((TIME_LEFT_IN_MILIS/1000)%60);
        String displayTime=String.format(Locale.getDefault(),"%02d",seconds);
        performanceTimeTextView.setText(displayTime);
    }
    public void pauseTime(){
        timer1.cancel();
    }
    public void resetTime(){
        TIME_LEFT_IN_MILIS=START_TIME_IN_MILIS;
        updateCountDown();
    }
}