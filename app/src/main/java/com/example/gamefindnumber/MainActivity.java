package com.example.gamefindnumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int comp_num = 0;
    int attempts = 5;
    int digit_count = 2;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AlertDialog.Builder SetBuilder(){
        final TextView tv = (TextView)findViewById(R.id.show_label_hint);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.settings);
        builder.setSingleChoiceItems(R.array.diaps_array, 0,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        attempts = 5;
                        digit_count = 2;
                        tv.setText(R.string.show_label_hint2);
                        break;
                    case 1:
                        attempts = 7;
                        digit_count = 3;
                        tv.setText(R.string.show_label_hint3);
                        break;
                    case 2:
                        attempts = 10;
                        digit_count = 4;
                        tv.setText(R.string.show_label_hint4);
                        break;
                }
                comp_num = guessNum.rnd_comp_num(attempts);
                TextView TW_att = (TextView) findViewById(R.id.show_attempts_left);
                TextView edit_num = (TextView)(findViewById(R.id.edit_num));
                TW_att.setText(Integer.toString(attempts));
                edit_num.setText("");
            }
        });
        return builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null && savedInstanceState.getInt("num") != 0) {
            attempts = savedInstanceState.getInt("attempts");
            comp_num = savedInstanceState.getInt("num");
            digit_count = savedInstanceState.getInt("digit_count");
            TextView TW_att = (TextView) findViewById(R.id.show_attempts_left);
            TextView edit_num = (TextView) (findViewById(R.id.edit_num));
            TW_att.setText(Integer.toString(attempts));
            edit_num.setText("");
        }
        Log.i (LOG_TAG, "onCreate()");
    }

    public void restart(View view) {
        AlertDialog.Builder builder = SetBuilder();
        builder.show();
        //comp_num = guessNum.rnd_comp_num(attempts);
        Button bttn = (Button) findViewById(R.id.btn_label_guess);
        bttn.setText(R.string.btn_label_guess);
        bttn.setEnabled(true);
    }

    @Override
    protected void onPause() {
        Log.i (LOG_TAG, "onPause()");
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        Log.i (LOG_TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.i (LOG_TAG, "onStart()");
        super.onStart();
        //comp_num = guessNum.rnd_comp_num(attempts);
    }

    @Override
    protected void onStop() {
        Log.i (LOG_TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i (LOG_TAG, "onSaveInstanceState()");
        outState.putInt("attempts", attempts);
        outState.putInt("num", comp_num);
        outState.putInt("digit_count", digit_count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        Log.i (LOG_TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i (LOG_TAG, "onRestart()");
        super.onRestart();
    }

    public void guess(View view) {
        final Button bttn = (Button) findViewById(R.id.btn_label_guess);
        final Context context = this;
                int x = 0;
                try{
                    x = Integer.parseInt(((TextView)(findViewById(R.id.edit_num))).getText().toString());
                } catch(Exception e) {

                }

                if(x != 0 && ((digit_count == 2 && x > 0 && x < 100) || (digit_count == 3 && x >= 100 && x < 1000) || (digit_count == 4 && x >= 1000 && x <= 9999))){
                    if (comp_num == x){
                        bttn.setText(R.string.msgbox_guessed);
                        bttn.setEnabled(false);
                        Toast t = Toast.makeText(context, R.string.msgbox_congrats,Toast.LENGTH_LONG);
                        t.show();
                    } else{
                        TextView TW = (TextView) findViewById(R.id.show_attempts_left);
                        attempts--;
                        TW.setText(Integer.toString(attempts));
                        int MoreLess = Integer.parseInt(((TextView)(findViewById(R.id.edit_num))).getText().toString());
                        if(MoreLess < comp_num){
                            Toast t = Toast.makeText(context, R.string.msgbox_More,Toast.LENGTH_LONG);
                            t.show();
                        } else if (MoreLess > comp_num){
                            Toast t = Toast.makeText(context, R.string.msgbox_Less,Toast.LENGTH_LONG);
                            t.show();
                        }
                        if(attempts == 0){
                           // restart();
                        }
                    }

                }

    }

    public static class guessNum {
        static public int rnd_comp_num(int attempts) {

            int min = 10;
            int max = 99;
            int offset = 1;
            switch(attempts){
                case 5:
                    min = 10;
                    max = 99;
                    offset = 1;
                    break;
                case 7:
                    min = 100;
                    max = 999;
                    offset = 0;
                    break;
                case 10:
                    min = 1000;
                    max = 9999;
                    offset = 0;
                    break;
            }
            int diff = max - min;
            Random random = new Random();
            int ret = random.nextInt(diff + offset) + min;
            return ret;
        }
    }




}