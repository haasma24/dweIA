package com.example.dweia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FirstAct extends AppCompatActivity {

    Button commencer , ai ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        commencer = findViewById(R.id.commencer);
        ai = findViewById(R.id.AI);

        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to ChoiceRole activity
                Intent intent = new Intent(FirstAct.this, ChoiceRole.class);
                startActivity(intent); // Start the ChoiceRole activity
            }
        });

        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstAct.this , Capture.class);
                startActivity(intent);
            }
        });
    }
}