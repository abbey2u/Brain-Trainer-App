package com.example.braintrainer;

import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;TextView resultTextView;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfTheCorrectAnswer;
    int score = 0;
    TextView pointTextView ;
    int numberOfQuestions = 0;
    TextView sumTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView timer;
    Button playAgainButton;
    RelativeLayout startLayout;


    public void playAgainButton(View view){
        score = 0;
        numberOfQuestions = 0;

        timer.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                resultTextView.setText(" Your score "+ Integer.toString(score)+ " / "+ Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfTheCorrectAnswer = random.nextInt(4);
        answer.clear();

        int inCorrectAnswer;

        for (int i =0; i<4; i++){

            if (i == locationOfTheCorrectAnswer){

                answer.add(a + b);

            }else {
                inCorrectAnswer = random.nextInt(4);
                while (inCorrectAnswer == a + b){
                    inCorrectAnswer = random.nextInt(4);
                }

                answer.add(inCorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));
    }


    public void chooseAnswer(View view){

        // Log.i("Tag", (String)view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfTheCorrectAnswer))){
            score++;
            resultTextView.setText(" Correct! ");
        }else {
            resultTextView.setText(" Wrong! ");
        }

        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score)+" / " + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        startLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgainButton(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        resultTextView = findViewById(R.id.resultTextView);
        pointTextView = findViewById(R.id.pointTextView);
        button= findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        timer = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        startLayout = findViewById(R.id.startLayout);

    }

}
