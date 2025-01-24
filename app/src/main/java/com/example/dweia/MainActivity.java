////package com.example.dweia;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.fragment.app.Fragment;
////import androidx.fragment.app.FragmentManager;
////import androidx.fragment.app.FragmentTransaction;
////
////import android.os.Bundle;
////
////import com.example.bottomnavigation.databinding.ActivityMainBinding;
////
////public class MainActivity extends AppCompatActivity {
////
////    ActivityMainBinding binding;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        binding = ActivityMainBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
////
////        replaceFragment(new HomeFragment());
////        binding.bottomNavigationView.setBackground(null);
////
////        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
////
////            switch (item.getItemId()) {
////                case R.id.home:
////                    replaceFragment(new HomeFragment());
////                    break;
////
////                case R.id.shorts:
////                    replaceFragment(new ShortsFragment());
////                    break;
////
////                case R.id.subscriptions:
////                    replaceFragment(new SubscriptionFragment());
////                    break;
////
////                case R.id.library:
////                    replaceFragment(new LibraryFragment());
////                    break;
////            }
////
////            return true;
////
////        });
////
////    }
////
////    private void replaceFragment(Fragment fragment) {
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.frame_layout, fragment);
////        fragmentTransaction.commit();
////    }
////}
//
//
//package com.example.dweia;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.os.Bundle;
//
//import com.example.dweia.databinding.ActivityMainBinding;
//
//public class MainActivity extends AppCompatActivity {
//
//    ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        binding = ActivityMainBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
////
////        replaceFragment(new HomeFragment());
//////        binding.bottomNavigationView.setBackground(null);
//////
//////        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//////            switch (item.getItemId()) {
//////                case R.id.home:
//////                    replaceFragment(new HomeFragment());
//////                    break;
//////                case R.id.user:
//////                    replaceFragment(new UserFragment());
//////                    break;
//////                case R.id.stats:
//////                    replaceFragment(new StatsFragment());
//////                    break;
//////                case R.id.notif:
//////                    replaceFragment(new NotifFragment());
//////                    break;
//////            }
//////            return true;
//////        });
////        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
////            Fragment selectedFragment = null;
////            switch (item.getItemId()) {
////                case R.id.home:
////                    selectedFragment = new HomeFragment();
////                    break;
////                case R.id.user:
////                    selectedFragment = new UserFragment();
////                    break;
////                case R.id.stats:
////                    selectedFragment = new StatsFragment();
////                    break;
////                case R.id.notif:
////                    selectedFragment = new NotifFragment();
////                    break;
////                default:
////                    throw new IllegalArgumentException("Unexpected item ID");
////            }
////            if (selectedFragment != null) {
////                replaceFragment(selectedFragment);
////            }
////            return true;
////        });
////
////    }
////
////    private void replaceFragment(Fragment fragment) {
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.frame_layout, fragment);
////        fragmentTransaction.commit();
////    }
////}
//    }}

package com.example.dweia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dweia.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private ImageButton qrCodeButton;
    private TextView welcomeMessage, userName;
    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // Use ActivityMainBinding to set up the layout
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
//
//        // Bind UI elements
//        welcomeMessage = findViewById(R.id.welcome_message);
//        addButton = findViewById(R.id.addbbtton);
//        qrCodeButton = findViewById(R.id.generateQRButton);
//        userName = findViewById(R.id.user_name);
//
//        // Display user info if logged in
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            welcomeMessage.setText("Salut,");
//            userName.setText(name != null && !name.isEmpty() ? name : email);
//        } else {
//            // Redirect to sign-in if no user is logged in
//            Intent intent = new Intent(AuxHomeActivity.this, SignInActivity.class);
//            startActivity(intent);
//            finish();
//        }
//
//        // Button listeners
//        addButton.setOnClickListener(v -> {
//            Intent intent = new Intent(AuxHomeActivity.this, AddMember.class);
//            startActivity(intent);
//        });
//
//        qrCodeButton.setOnClickListener(v -> {
//            Intent intent = new Intent(AuxHomeActivity.this, activity_generate_qr.class);
//            startActivity(intent);
//        });
//
//        // Set the default fragment to HomeFragment
//        replaceFragment(new HomeFragment());
//
//        // Remove background from BottomNavigationView
//        binding.bottomNavigationView.setBackground(null);
//
//        // Set up BottomNavigationView item selection listener
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//
//            // Use if-else for handling item selection
//            if (item.getItemId() == R.id.home) {
//                selectedFragment = new HomeFragment();
//            } else if (item.getItemId() == R.id.user) {
//                selectedFragment = new UserFragment();
//            } else if (item.getItemId() == R.id.stats) {
//                selectedFragment = new StatsFragment();
//            } else if (item.getItemId() == R.id.notif) {
//                selectedFragment = new NotifFragment();
//            } else {
//                throw new IllegalArgumentException("Unexpected item ID");
//            }
//
//            // Replace the fragment if a valid one is selected
//            if (selectedFragment != null) {
//                replaceFragment(selectedFragment);
//            }
//            return true;
//        });
//    }
//
//    // Method to replace the fragment in the FrameLayout
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
    }
}
