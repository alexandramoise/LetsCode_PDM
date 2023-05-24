package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class Introduction3 extends AppCompatActivity {

    private TextView text;
    private Button next, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction3);

        Intent intent = getIntent();
        String dev = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        next = (Button)findViewById(R.id.button5);
        back = (Button)findViewById(R.id.button4);
        back.setBackgroundColor(Color.BLACK);
        next.setBackgroundColor(Color.BLACK);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Introduction3.this,Introduction4.class);
                intent2.putExtra("device",dev);
                intent2.putExtra("level",level);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Introduction3.this,Introduction2.class);
                intent3.putExtra("device",dev);
                intent3.putExtra("level",level);
                startActivity(intent3);
            }
        });

        text = (TextView)findViewById(R.id.title4);
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