package com.example.dweia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceRole extends AppCompatActivity {

    private LinearLayout layoutCareTaker, layoutFamilyMember ,layoutAux;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_role);

        // Initialize Views
        layoutCareTaker = findViewById(R.id.layout_care_taker);
        layoutFamilyMember = findViewById(R.id.layout_family_member);
        layoutAux = findViewById(R.id.layout_aux);

        // Set Click Listeners
        layoutCareTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Sign In Activity for Care Taker
                Intent intent = new Intent(ChoiceRole.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        layoutFamilyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another activity for Family Member (if required)
                Intent intent = new Intent(ChoiceRole.this, SignInActivity.class); // Update if needed
                startActivity(intent);
            }
        });

        layoutAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another activity for Family Member (if required)
                Intent intent = new Intent(ChoiceRole.this, SignInActivity.class); // Update if needed
                startActivity(intent);
        }
    });
    }
}
