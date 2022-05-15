package com.alpha.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton start_btn;
    private RadioButton radioButton2,radioButton3,radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = findViewById(R.id._start_btn);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);

                if (!radioButton2.isChecked() && !radioButton3.isChecked() && !radioButton4.isChecked())
                {
                    Snackbar.make(view,"Please select an option.",Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (radioButton2.isChecked())
                    {
                        intent.putExtra("two",true);
                    }
                    if (radioButton3.isChecked())
                    {
                        intent.putExtra("three",true);
                    }

                    if (radioButton4.isChecked())
                    {
                        intent.putExtra("four",true);
                    }

                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}