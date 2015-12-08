package com.paypal.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    /**
     *
     * @param view
     *
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to a button
        Intent intent = new Intent(this, QuestionsActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            TextView textView = (TextView) findViewById(R.id.resultFromQuestions);
            int randomResult = data.getIntExtra(QuestionsActivity.RANDOM_RESULT, 0);
            textView.setText(Integer.toString(randomResult));

            setImage(randomResult);
        }
    }

    public static final int MAX_IMAGE_NUMBER = 5;

    private void setImage(int randomResult) {
        int id = randomResultToId(randomResult);
        ImageView imageView = (ImageView) findViewById(R.id.image01);
        imageView.setImageResource(id);
    }

    private int randomResultToId(int randomResult) {
        int id;
        switch (randomResult) {
            case 1:
                id = R.drawable.image01;
                break;
            case 2:
                id = R.drawable.image02;
                break;
            case 3:
                id = R.drawable.image03;
                break;
            case 4:
                id = R.drawable.image04;
                break;
            case 5:
                id = R.drawable.image05;
                break;
//            case 6:
//                id = R.drawable.image06;
//                break;
//            case 7:
//                id = R.drawable.image07;
//                break;
//            case 8:
//                id = R.drawable.image08;
//                break;
//            case 9:
//                id = R.drawable.image09;
//                break;
//            case 10:
//                id = R.drawable.image10;
//                break;
//            case 11:
//                id = R.drawable.image11;
//                break;
//            case 12:
//                id = R.drawable.image12;
//                break;
//            case 13:
//                id = R.drawable.image13;
//                break;
//            case 14:
//                id = R.drawable.image14;
//                break;
//            case 15:
//                id = R.drawable.image15;
//                break;
            default:
                id = R.drawable.image04;
                break;
        }
        return id;
    }


    }
