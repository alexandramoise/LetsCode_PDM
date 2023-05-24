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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz3 extends AppCompatActivity {

    private TextView text;
    private Button done, back;
    private RadioButton a1, a2, a3;
    private int selected = 0;
    private DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);

        text = (TextView)findViewById(R.id.q3);
        done = (Button)findViewById(R.id.doneq3);
        back = (Button)findViewById(R.id.backq3);
        a1 = (RadioButton)findViewById(R.id.an1);
        a2 = (RadioButton)findViewById(R.id.an2);
        a3 = (RadioButton)findViewById(R.id.an3);

        Intent intent = getIntent();
        String dev = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        db = new DB(this);

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

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newLevel = db.getDeviceLevel(dev);
                if(selected == 0) {
                    Toast.makeText(Quiz3.this,"Trebuie sa alegi o optiune!",Toast.LENGTH_SHORT).show();
                } else if(selected!=1) {
                    if(selected==2) {
                        a2.setChecked(false);
                        Toast.makeText(Quiz3.this,"Bine bine, revino cand ai chef :)",Toast.LENGTH_SHORT).show();
                    } else if (selected==3) {
                        a3.setChecked(false);
                        Toast.makeText(Quiz3.this,"Ia un calculator, gandeste-te inca o data si daca nu stii revino mai tarziu!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Quiz3.this,"Felicitari, ai raspuns corect!",Toast.LENGTH_SHORT).show();
                    if(level == 3) {
                        db.updateLevel(dev,4);
                        newLevel = 4;
                    }
                    Intent quiz = new Intent(Quiz3.this,Profil.class);
                    quiz.putExtra("device",dev);
                    quiz.putExtra("level",newLevel);
                    startActivity(quiz);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Quiz3.this,Last6.class);
                intent3.putExtra("device",dev);
                intent3.putExtra("level",level);
                startActivity(intent3);
            }
        });

        back.setBackgroundColor(Color.BLACK);
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