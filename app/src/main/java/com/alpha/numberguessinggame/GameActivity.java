package com.alpha.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast,textViewRight,textViewHint;
    private EditText input_et;
    private AppCompatButton confirm_btn;
    boolean twoDigit,threeDigit,fourDigit;
    Random r = new Random();
    int random;
    int remainingright = 10;
    ArrayList<Integer> guessList = new ArrayList<>();
    int userattempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast = findViewById(R.id._last);
        textViewRight = findViewById(R.id._right_tv);
        textViewHint = findViewById(R.id._hint_tv);
        input_et = findViewById(R.id._input_et);
        confirm_btn = findViewById(R.id._confirm_btn);

        twoDigit = getIntent().getBooleanExtra("two",false);
        threeDigit = getIntent().getBooleanExtra("three",false);
        fourDigit = getIntent().getBooleanExtra("four",false);

        if (twoDigit)
        {
            random = r.nextInt(90)+10;
        }

        if (threeDigit)
        {
            random = r.nextInt(900)+100;
        }

        if (fourDigit)
        {
            random = r.nextInt(9000)+1000;
        }
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = input_et.getText().toString();
                
                if (input_et.equals(""))
                {
                    Toast.makeText(GameActivity.this, "Please enter a guess.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    userattempts++;
                    remainingright--;
                    int userguess = Integer.parseInt(guess);
                    guessList.add(userguess);

                    textViewLast.setText("Your last guess is : "+guess);
                    textViewRight.setText("Your remaining rigts : "+remainingright);

                    if (random == userguess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle("Number Guessing Game");
                        builder.setMessage("Congratulations my guess was "+random +
                                "\n\n You know my guess after "+userattempts+
                                "attempts \n\n Your Guesses "+guessList+
                                "\n\n Would you like to play again");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();


                    }
                    if (random > userguess)
                    {
                        textViewHint.setText("Increase Your Guess");
                    }
                    if (random < userguess)
                    {
                        textViewHint.setText("Decrease Your Guess");
                    }
                    if (remainingright == 0)
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle("Number Guessing Game");
                        builder.setMessage("Sorry your right to guess is over"
                                +"\n\n My guess was "+random
                                +"\n\n Your Guesses "+guessList+
                                "\n\n Would you like to play again");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }
                    input_et.setText("");

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.setCancelable(true);
            }
        });
        builder.create().show();
    }
}