package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Quiz1 extends AppCompatActivity {

    private TextView feedback, title;
    private EditText e1,e2,e3;
    private Button submit, back;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        submit = (Button)findViewById(R.id.done);
        title = (TextView)findViewById(R.id.quiz1);
        back = (Button)findViewById(R.id.back_q1);
        e1 = (EditText)findViewById(R.id.v1_q1);
        e2 = (EditText)findViewById(R.id.v2_q1);
        e3 = (EditText)findViewById(R.id.v3_q1);
        feedback = (TextView)findViewById(R.id.f_q1);

        db = new DB(this);
        Intent intent = getIntent();
        String dev = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        submit.setBackgroundColor(Color.parseColor("#008000"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                String v1,v2,v3;
                v1 = e1.getText().toString();
                v2 = e2.getText().toString();
                v3 = e3.getText().toString();
                int newLevel = db.getDeviceLevel(dev);
                feedback.setText("");
                if (v1.equals("") || v1.equals("...") || v2.equals("") || v2.equals("...") || v3.equals("") || v3.equals("...")) {
                    Toast.makeText(Quiz1.this, "Nu poți lăsa spațiile necompletate!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!v1.equalsIgnoreCase("test")) {
                        result.append("Mai citește o dată NUMELE variabilei din enunț.\n");
                    }
                    if (!v2.equals("5")) {
                        result.append("Mai citește o dată VALOAREA variabilei din enunț.\n");
                    }
                    if (!v3.equalsIgnoreCase("cout")) {
                        result.append("Mai citește lecția cu afișarea datelor pe ecran.");
                    }
                    if (v1.equalsIgnoreCase("test") && v2.equals("5") && v3.equalsIgnoreCase("cout")) {
                        result.append("Felicitări, ai rezolvat perfect!");
                        Toast.makeText(Quiz1.this, "Vei trece la următorul nivel!", Toast.LENGTH_SHORT).show();
                        if(level == 1) {
                            db.updateLevel(dev,2);
                            newLevel = 2;
                        }

                        Intent quiz = new Intent(Quiz1.this,Profil.class);
                        quiz.putExtra("device",dev);
                        quiz.putExtra("level",newLevel);
                        startActivity(quiz);
                    }
                }
                feedback.setText("");
                feedback.setText(result.toString());
            }
        });

        back.setBackgroundColor(Color.BLACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Quiz1.this,Introduction11.class);
                intent3.putExtra("device",dev);
                intent3.putExtra("level",level);
                startActivity(intent3);
            }
        });

        float startAngle = 0.0f;
        float endAngle = 360.0f;
        RotateAnimation rotateAnimation = new RotateAnimation(
                startAngle,
                endAngle,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);

        title.startAnimation(rotateAnimation);
    }
}