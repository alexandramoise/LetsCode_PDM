package com.example.letscodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Introduction7 extends AppCompatActivity {

    private Button submit, back, next;
    private EditText input;
    private TextView console;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction7);

        submit = (Button)findViewById(R.id.cout);
        back = (Button)findViewById(R.id.button8);
        next = (Button)findViewById(R.id.button9);
        back.setBackgroundColor(Color.BLACK);
        next.setBackgroundColor(Color.BLACK);
        input = (EditText)findViewById(R.id.input);
        console = (TextView)findViewById(R.id.ecran);

        Intent intent = getIntent();
        String dev = intent.getStringExtra("device");
        int level = intent.getIntExtra("level",1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = input.getText().toString();
                if(content.equals("") || content.equals("ceva")) {
                    Toast.makeText(Introduction7.this,"Trebuie sa completezi cu un text!",Toast.LENGTH_SHORT).show();
                } else {
                    console.setText("ECRAN: \n" + content);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Introduction7.this,Introduction8.class);
                intent2.putExtra("device",dev);
                intent2.putExtra("level",level);
                startActivity(intent2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Introduction7.this,Introduction6.class);
                intent3.putExtra("device",dev);
                intent3.putExtra("level",level);
                startActivity(intent3);
            }
        });
    }
}