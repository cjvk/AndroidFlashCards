package com.paypal.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);

        makeANewQuestion();

        setQuestionNumber(1);

        //this.setTitle("Hello World");
        Activity parent = getParent();
        if (parent != null) {
            parent.setTitle("Hello World");
        }

//        Intent intent = getIntent();
//        String message = "fixme";
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//        setContentView(textView);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private int mQuestionNumber;
    private static final int MAX_QUESTIONS = 10;
    private int mLeftInt;
    private int mRightInt;
    private Random mRandom = new Random();
    private static final int MAX_ATTEMPTS = 3;
    private int mCurrentAttempts = 0;

    private void setQuestionNumber (int i) {
        // construct the string, e.g. "Question 1/100"
        String message = "Question " + i + "/" + MAX_QUESTIONS;

        // set the text
        TextView textView = (TextView) findViewById(R.id.question_number);
        textView.setText(message);

        mQuestionNumber = i;
    }

    private void incrementQuestionNumber () {
        setQuestionNumber(mQuestionNumber + 1);
    }
    private int randomIntBetween (int min, int max) {
        return mRandom.nextInt(max - min + 1) + min;
    }

    private void makeANewQuestion () {
        // set new ints
        int max = 10;
        int min = 2;
        mLeftInt = randomIntBetween(min, max);
        mRightInt = randomIntBetween(min, max);

        // construct the question
        String message = Integer.toString(mLeftInt) + " x " + Integer.toString(mRightInt) + " = ?";

        // set the text
        TextView textView = (TextView) findViewById(R.id.question_text);
        textView.setText(message);

        incrementQuestionNumber();
    }

    private void setErrorText (String errorMessage) {
        TextView textView = (TextView) findViewById(R.id.error_text);
        textView.setText(errorMessage);
    }
    private void resetErrorText () {
        setErrorText("");
    }
    private void resetAttempts () {
        mCurrentAttempts = 0;
    }
    private void incrementAttempts () {
        mCurrentAttempts++;
    }
    private int calculateAnswer () {
        return mLeftInt * mRightInt;
    }
    private boolean isCorrect(String userAnswer) {
        String correctAnswer = Integer.toString(calculateAnswer());
        return correctAnswer.equalsIgnoreCase(userAnswer);
    }
    private void resetUserAnswer () {
        EditText editText = (EditText) findViewById(R.id.question_message);
        editText.getText().clear();
    }
    public static final String RANDOM_RESULT = "randomResult";

    public void answer (View view) {
        EditText editText = (EditText) findViewById(R.id.question_message);

        String userAnswer = editText.getText().toString();

        if (isCorrect(userAnswer)) {
            // are we done?
            if (mQuestionNumber < MAX_QUESTIONS) {
                makeANewQuestion();
                resetErrorText();
                resetAttempts();
                resetUserAnswer();
            } else {
                Intent returnIntent = new Intent();
                int randomResult = randomIntBetween(1, StartActivity.MAX_IMAGE_NUMBER);
                returnIntent.putExtra(RANDOM_RESULT, randomResult);
                setResult(RESULT_OK, returnIntent);
                finish();
            }

        } else {
            incrementAttempts();
            if (mCurrentAttempts < MAX_ATTEMPTS) {
                setErrorText("oops, try again");
            } else {
                String s = new StringBuilder().append(mLeftInt).append(" x ").append(mRightInt).append(" = ").append(calculateAnswer()).append(". Let's go on to the next one.").toString();
//                String message = "" + mLeftInt + " x " + mRightInt + " = " + calculateAnswer();
                setErrorText(s);
                makeANewQuestion();
                resetAttempts();
                resetUserAnswer();
            }
        }

    }

}
