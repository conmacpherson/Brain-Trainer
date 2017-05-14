package com.example.conor.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    int answer = 0;

    final int min = 1;
    final int max = 100;

    TextView scoreTextView;
    TextView timerTextView ;
    TextView questionTextView;

    Button goButton;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;

    CountDownTimer timer;

    /**
     * Create random addition equations, calculates, and returns the results.
     * Example: a + b, where a = 1...100 and b == 1...100
     * Also sets questionTextView TextView to the equation.
     **/
    public int generateQuestion(){
        Random rand = new Random();
        int x = rand.nextInt(max - min + 1);
        int y = rand.nextInt(max - min + 1);
        answer = x + y;

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        questionTextView.setText(x + " + " + y);

        return answer;
    }

    /**
     * Randomizes the possible answers displayed on answerButtons 1...4.
     **/
    public void randomizeAnswerOrder(){

        answer = generateQuestion();

        answerButton1 = (Button) findViewById(R.id.answerButton1);
        answerButton2 = (Button) findViewById(R.id.answerButton2);
        answerButton3 = (Button) findViewById(R.id.answerButton3);
        answerButton4 = (Button) findViewById(R.id.answerButton4);

        Random rand = new Random();

        // Random number that will decide which button displays the correct result.
        int correctButtonSelect = rand.nextInt(4 - 1 + 1) + 1;
        // Random numbers that will show up on 3 buttons as an incorrect result.
        int false1 = rand.nextInt((max+35) - min + 1),
                false2 = rand.nextInt((max) - min + 1),
                    false3 = rand.nextInt((max+10) - min + 1);
        Log.i("INFO_CRECT_BTTN_SEL", Integer.toString(correctButtonSelect));
        Log.i("INFO_CRECT_ANSR", Integer.toString(answer));
        switch (correctButtonSelect){
            // answerButton1 holds the correct result.
            case 1:
                answerButton1.setText(Integer.toString(answer));
                answerButton2.setText(Integer.toString(false1));
                answerButton3.setText(Integer.toString(false2));
                answerButton4.setText(Integer.toString(false3));
                Log.i("INFO_CASE", "CASE 1 REACHED");
                break;
            // answerButton2 holds the correct result.
            case 2:
                answerButton2.setText(Integer.toString(answer));
                answerButton1.setText(Integer.toString(false1));
                answerButton3.setText(Integer.toString(false2));
                answerButton4.setText(Integer.toString(false3));
                Log.i("INFO_CASE", "CASE 2 REACHED");
                break;
            // answerButton3 holds the correct result.
            case 3:
                answerButton3.setText(Integer.toString(answer));
                answerButton1.setText(Integer.toString(false1));
                answerButton2.setText(Integer.toString(false2));
                answerButton4.setText(Integer.toString(false3));
                Log.i("INFO_CASE", "CASE 3 REACHED");
                break;
            // answerButton4 holds the correct result.
            case 4:
                answerButton4.setText(Integer.toString(answer));
                answerButton1.setText(Integer.toString(false1));
                answerButton2.setText(Integer.toString(false2));
                answerButton3.setText(Integer.toString(false3));
                Log.i("INFO_CASE", "CASE 4 REACHED");
                break;
            default:
                break;
        }
    }

    /**
     * Grants the user a point if they selected the correct button.
     * Also sets the scoreTextView to the current users score out of the total rounds.
     **/
    public void scorePoint(Button button){

        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        
        // Add a breakpoint here and see if the button is being changed on each button click or if it uses the previous button click.
        if(button.getText() == Integer.toString(answer)){
            score++;
            // answer = 0;
            scoreTextView.setText(Integer.toString(score) + "/");
            Log.i("INFO_SCORE",Integer.toString(score));
        }
        else{
            // answer = 0;
            Log.i("INFO_SCORE",Integer.toString(score));
        }
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.goButton:
                    goButton.setVisibility(View.GONE);
                    answerButton1.setVisibility(View.VISIBLE);
                    answerButton2.setVisibility(View.VISIBLE);
                    answerButton3.setVisibility(View.VISIBLE);
                    answerButton4.setVisibility(View.VISIBLE);

                    randomizeAnswerOrder();

                    timerTextView = (TextView) findViewById(R.id.timerTextView);
                    timer = new CountDownTimer(300000+100, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timerTextView.setText(String.valueOf(millisUntilFinished/1000));
                            Log.i("INFO_TIMER", "TICK " + millisUntilFinished/1000);
                        }
                        @Override
                        public void onFinish() {
                            timerTextView.setText("0");
                            goButton.setVisibility(View.VISIBLE);
                            answerButton1.setVisibility(View.GONE);
                            answerButton2.setVisibility(View.GONE);
                            answerButton3.setVisibility(View.GONE);
                            answerButton4.setVisibility(View.GONE);
                            Log.i("INFO_TIMER", "DONE");
                        }
                    }.start();
                    break;
                case R.id.answerButton1:
                    Log.i("Info_aButt1", "PRESSED");
                    scorePoint((Button) findViewById(R.id.answerButton1));
                    randomizeAnswerOrder();
                    break;
                case R.id.answerButton2:
                    Log.i("Info_aButt2", "PRESSED");
                    scorePoint((Button) findViewById(R.id.answerButton2));
                    randomizeAnswerOrder();
                    break;
                case R.id.answerButton3:
                    Log.i("Info_aButt3", "PRESSED");
                    scorePoint((Button) findViewById(R.id.answerButton3));
                    randomizeAnswerOrder();
                    break;
                case R.id.answerButton4:
                    Log.i("Info_aButt4", "PRESSED");
                    scorePoint((Button) findViewById(R.id.answerButton4));
                    randomizeAnswerOrder();
                    break;
                default:
                    Log.i("INFO_DEFAULT", "DEFAULT REACHED");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        answerButton1 = (Button) findViewById(R.id.answerButton1);
        answerButton2 = (Button) findViewById(R.id.answerButton2);
        answerButton3 = (Button) findViewById(R.id.answerButton3);
        answerButton4 = (Button) findViewById(R.id.answerButton4);

        goButton.setOnClickListener(listener);
        answerButton1.setOnClickListener(listener);
        answerButton2.setOnClickListener(listener);
        answerButton3.setOnClickListener(listener);
        answerButton4.setOnClickListener(listener);
    }
}
