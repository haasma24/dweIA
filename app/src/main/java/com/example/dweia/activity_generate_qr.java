package com.example.dweia;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

public class activity_generate_qr extends AppCompatActivity {

    private EditText inputText;
    private Button generateQRButton;
    private ImageView qrCodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        // Initialize UI elements
        inputText = findViewById(R.id.inputText);
        generateQRButton = findViewById(R.id.generateQRButton);
        qrCodeImageView = findViewById(R.id.qrCodeImageView);

        // Set OnClickListener for Generate Button
        generateQRButton.setOnClickListener(v -> {
            String qrContent = inputText.getText().toString().trim();

            if (qrContent.isEmpty()) {
                Toast.makeText(this, "Please enter text to generate QR Code", Toast.LENGTH_SHORT).show();
                return;
            }

            generateQRCode(qrContent);
        });
    }

    private void generateQRCode(String qrContent) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            // Generate QR Code as a Bitmap
            int size = 512; // Width/Height of the QR Code
            com.google.zxing.common.BitMatrix bitMatrix = writer.encode(qrContent, BarcodeFormat.QR_CODE, size, size);

            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF); // Black or White
                }
            }

            // Display the QR Code in the ImageView
            qrCodeImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Toast.makeText(this, "Error generating QR Code: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
