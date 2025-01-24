package com.example.dweia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Capture extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private ImageView imageView;
    private Bitmap capturedImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        imageView = findViewById(R.id.imageView);
        Button btnCapture = findViewById(R.id.btnCapture);
        Button btnUpload = findViewById(R.id.btnUpload);

        // Bouton pour capturer une photo
        btnCapture.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                openCamera();
            } else {
                requestCameraPermission();
            }
        });

        // Bouton pour uploader la photo
        btnUpload.setOnClickListener(v -> {
            if (capturedImage != null) {
                uploadPhoto();
            } else {
                Toast.makeText(this, "Veuillez d'abord capturer une photo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Ouvrir l'appareil photo
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            cameraLauncher.launch(cameraIntent);
        } catch (Exception e) {
            Log.e("CameraError", "Erreur lors de l'ouverture de la caméra", e);
        }
    }

    // Lancer la caméra et recevoir l'image capturée
    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    capturedImage = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(capturedImage);
                }
            });

    // Vérifier si la permission de la caméra est accordée
    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    // Demander la permission de la caméra
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Permission caméra refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Méthode pour uploader la photo
    private void uploadPhoto() {
        // Convertir le Bitmap en URI
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        capturedImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), capturedImage, "Photo", null);
        Uri imageUri = Uri.parse(path);

        try {
            // Code pour uploader l'image sur un serveur ou une base de données
            // Simulé ici avec un message
            Toast.makeText(this, "Photo uploadée : " + imageUri.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Erreur d'upload", Toast.LENGTH_SHORT).show();
            Log.e("UploadError", "Erreur lors de l'upload", e);
        }
    }
}
