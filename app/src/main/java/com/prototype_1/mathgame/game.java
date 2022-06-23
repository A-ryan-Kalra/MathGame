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

import java.util.Locale;
import java.util.Random;

public class game extends AppCompatActivity {

    TextView scoreTextView;
    TextView lifeTextView;
    TextView timeTextView;
    TextView questionTextView;

    EditText answerEditText;
    Button okButton,nextQuesButton;

    Random random=new Random();
    int number1,number2;
    int realNumber,userNumber;
    int userScore=0;
    int userLife=3;

    int tag;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILES=10000;
    Boolean timer_running;
    long time_left_in_milis=START_TIMER_IN_MILES;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreTextView=findViewById(R.id.scoreGameTextView);
        lifeTextView=findViewById(R.id.lifeScoreTextView);
        timeTextView=findViewById(R.id.timeRemainTextView);
        answerEditText=findViewById(R.id.answerEditText);
        questionTextView=findViewById(R.id.textViewQuestion);

        okButton=findViewById(R.id.okButton);
        nextQuesButton=findViewById(R.id.nextQuestionButton);
        lifeTextView.setText(""+userLife);
        scoreTextView.setText(""+userScore);
        tag=getIntent().getIntExtra("tag",0);
        if(tag==1) {
            gameContinue();
        }
        else if(tag==2){
            gameContinue2();
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                    userNumber = Integer.valueOf(answerEditText.getText().toString());
                    answerEditText.setText("");

                    if (userNumber == realNumber) {
                        userScore = userScore + 10;
                        questionTextView.setText("Congratulation, Your answer is correct!");
                        scoreTextView.setText(String.valueOf(userScore));
                        lifeTextView.setText("" + userLife);
                    } else {
                        questionTextView.setText("Oops,this time you are wrong!");
                        userScore = userScore - 5;
                        userLife--;
                        scoreTextView.setText("" + userScore);
                        lifeTextView.setText("" + userLife);
                    }

                // answerEditText.setText("");
            }
        });

        nextQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerEditText.setText("");
                pauseTimer();
                resetTimer();
                if(userLife==0){
                    Toast.makeText(getApplicationContext(), "Game is Over!", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }
                else {
                    if(tag==1) {
                        gameContinue();
                    }
                    else if(tag==2){
                        gameContinue2();
                    }
                }
            }
        });
    }
    public void gameContinue(){
        number1=random.nextInt(100);
        number2=random.nextInt(100);
        realNumber=number1+number2;

        questionTextView.setText(number1+" + "+number2);

        startTimer();

    }

    public void gameContinue2(){
        number1=random.nextInt(100);
        number2=random.nextInt(100);
        realNumber=number1-number2;

        questionTextView.setText(number1+" - "+number2);

        startTimer();

    }
    public void startTimer(){
        timer=new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timer_running=false;
                pauseTimer();
                resetTimer();
                //updateCountDownText();
                questionTextView.setText("Oof, time is up!!");
            }
        }.start();
        timer_running=true;
    }
    public void updateCountDownText(){
        int seconds=(int)(time_left_in_milis/1000)%60;
        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d",seconds);
        timeTextView.setText(timeLeftFormatted);
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running=false;
    }
    public void resetTimer(){
        time_left_in_milis=START_TIMER_IN_MILES;
        updateCountDownText();
    }
}