package com.example.dweia;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private ImageView ivTogglePassword, ivToggleConfirmPassword;
    private CheckBox cbTerms;
    private Button btnRegister;
    private TextView tvSignInLink;

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    // Firebase Authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        ivTogglePassword = findViewById(R.id.iv_toggle_password);
        ivToggleConfirmPassword = findViewById(R.id.iv_toggle_confirm_password);
        cbTerms = findViewById(R.id.cb_terms);
        btnRegister = findViewById(R.id.btn_register);
        tvSignInLink = findViewById(R.id.tv_sign_in_link);

        // Toggle password visibility
        ivTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.visibility);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.visibility);
            }
            etPassword.setSelection(etPassword.getText().length());
        });

        // Toggle confirm password visibility
        ivToggleConfirmPassword.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            if (isConfirmPasswordVisible) {
                etConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivToggleConfirmPassword.setImageResource(R.drawable.visibility);
            } else {
                etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivToggleConfirmPassword.setImageResource(R.drawable.visibility);
            }
            etConfirmPassword.setSelection(etConfirmPassword.getText().length());
        });

        // Register button click
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (name.isEmpty()) {
                etName.setError("Veuillez entrer votre nom");
                etName.requestFocus();
                return;
            }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Veuillez entrer un email valide");
                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                etPassword.setError("Le mot de passe doit contenir au moins 6 caractères");
                etPassword.requestFocus();
                return;
            }

            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Les mots de passe ne correspondent pas");
                etConfirmPassword.requestFocus();
                return;
            }

            if (!cbTerms.isChecked()) {
                Toast.makeText(SignUpActivity.this, "Veuillez accepter les termes et conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            // Register user with Firebase
            registerUser(email, password);
        });

        // Link to sign-in screen
        tvSignInLink.setOnClickListener(v -> {
            // Navigate to the sign-in activity
            Toast.makeText(SignUpActivity.this, "Navigating to Sign In...", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
             startActivity(intent);
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                            if (user != null) {
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> emailTask) {
                                        if (emailTask.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Email de vérification envoyé", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Échec de l'envoi de l'email de vérification", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        } else {
                            // Registration failed
                            Toast.makeText(SignUpActivity.this, "Échec de l'inscription: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
