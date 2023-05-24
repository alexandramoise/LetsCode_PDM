package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Operations5 extends AppCompatActivity {

    private TextView text;
    private Button done, back, next;
    private RadioButton a1, a2, a3, a4;

    private int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations5);

        text = (TextView)findViewById(R.id.titleo5);
        done = (Button)findViewById(R.id.doneo5);
        next = (Button)findViewById(R.id.nexto5);
        back = (Button)findViewById(R.id.backo5);
        a1 = (RadioButton)findViewById(R.id.a1);
        a2 = (RadioButton)findViewById(R.id.a2);
        a3 = (RadioButton)findViewById(R.id.a3);
        a4 = (RadioButton)findViewById(R.id.a4);

        Intent intent = getIntent();
        String dev = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 1;
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 2;
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 3;
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = 4;
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected == 0) {
                    Toast.makeText(Operations5.this,"Trebuie sa alegi o optiune!",Toast.LENGTH_SHORT).show();
                } else if(selected!=2) {
                    Toast.makeText(Operations5.this,"Ordinea operatiilor: adunarea se face ultima, mai incearca!",Toast.LENGTH_SHORT).show();
                    if(selected==1) {
                        a1.setChecked(false);
                    } else if (selected==2) {
                        a2.setChecked(false);
                    } else {
                        a3.setChecked(false);
                    }
                } else {
                    Toast.makeText(Operations5.this,"Felicitari, ai raspuns corect!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Operations5.this,Operations6.class);
                intent2.putExtra("device",dev);
                intent2.putExtra("level",level);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Operations5.this,Operations4.class);
                intent3.putExtra("device",dev);
                intent3.putExtra("level",level);
                startActivity(intent3);
            }
        });

        back.setBackgroundColor(Color.BLACK);
        next.setBackgroundColor(Color.BLACK);
        done.setBackgroundColor(Color.parseColor("#008000"));

        ObjectAnimator slideRight = ObjectAnimator.ofFloat(text, "translationX", 0f, 500f);
        slideRight.setDuration(1000);
        slideRight.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator slideLeft = ObjectAnimator.ofFloat(text, "translationX", 500f, 0f);
        slideLeft.setDuration(1000);
        slideLeft.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(slideRight, slideLeft);
        animatorSet.start();
    }
}