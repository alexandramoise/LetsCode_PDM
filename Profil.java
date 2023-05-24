package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends AppCompatActivity {

    TextView user;
    ImageView l1,l2,l3;
    Button end;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        db = new DB(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        user = (TextView)findViewById(R.id.level);
        user.setText("Nivel: " + level);

        l1 = (ImageView)findViewById(R.id.level1);
        l2 = (ImageView)findViewById(R.id.level2);
        l3 = (ImageView)findViewById(R.id.level3);

        if(level>=2)  l2.setImageResource(R.drawable.level2);
        if(level>=3)  l3.setImageResource(R.drawable.level3);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profil.this,"Vei merge la prima lectie",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Profil.this, Introduction1.class);
                        intent.putExtra("device",username);
                        intent.putExtra("level",level);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_level(level,2,username);
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_level(level,3,username);
            }
        });


        end = (Button)findViewById(R.id.end);
        end.setBackgroundColor(Color.RED);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profil.this,"Vei reveni la pagina principala!",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent out = new Intent(Profil.this, MainActivity.class);
                        startActivity(out);
                        finish();
                    }
                }, 2000);
            }
        });
    }

    // functie ca sa verific daca user-ul poate accesa lectia pentru nivelul la care se afla
    public void check_level(int level,int check_value,String username) {
        if(level < check_value) {
            Toast.makeText(Profil.this,"Inca nu ai acces la aceasta lectie!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Profil.this,"Vei merge la a " + check_value + "-a lectie",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    if(check_value == 2) {
                        intent = new Intent(Profil.this,Operations1.class);
                    } else if(check_value == 3) {
                        intent = new Intent(Profil.this,Last1.class);
                    }
                    intent.putExtra("device",username);
                    intent.putExtra("level",check_value);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }
}