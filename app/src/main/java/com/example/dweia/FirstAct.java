package com.example.dweia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FirstAct extends AppCompatActivity {

    Button commencer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        commencer = findViewById(R.id.commencer);

        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to ChoiceRole activity
                Intent intent = new Intent(FirstAct.this, ChoiceRole.class);
                startActivity(intent); // Start the ChoiceRole activity
            }
        });
    }
}