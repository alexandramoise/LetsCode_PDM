package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.os.Build;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button buton;
    private TextView text;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton = (Button)findViewById(R.id.button);
        buton.setBackgroundColor(Color.WHITE);

        String deviceName = Build.MODEL;

        db = new DB(this);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // db.updateLevel(deviceName,2);
                if(! db.isUser(deviceName))
                    db.addDevice(deviceName);
                int level = db.getDeviceLevel(deviceName);
                //if(deviceLevel == 0) { deviceLevel = 1; }
                Intent intent = new Intent(MainActivity.this, Profil.class);
                intent.putExtra("device",deviceName);
                intent.putExtra("level",level);
                startActivity(intent);
            }
        });

        text = (TextView) findViewById(R.id.textView);
        float startAngle = 0.0f;
        float endAngle = 360.0f;

        RotateAnimation rotateAnimation = new RotateAnimation(
                startAngle,
                endAngle,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);

        text.startAnimation(rotateAnimation);
    }
}