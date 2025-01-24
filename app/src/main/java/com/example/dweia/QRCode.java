package com.example.dweia;//package com.example.dweia;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//public class QRCode extends AppCompatActivity {
//
//    Button scan_btn;
//
//    TextView textView;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qrcode);
//
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//        scan_btn = findViewById(R.id.scanner);
//        textView = findViewById(R.id.text);
//
//        scan_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator intentIntegrator = new IntentIntegrator(QRCode.this);
//                intentIntegrator.setOrientationLocked(false);
//                intentIntegrator.setPrompt("Scan a QR Code");
//                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//                intentIntegrator.initiateScan();
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//        if(intentResult != null){
//            String contents = intentResult.getContents();
//            if(contents != null){
//                textView.setText(intentResult.getContents());
//            }
//        }else{
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//}
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRCode extends AppCompatActivity {

    private Button scanButton;
    private ImageView cameraImage;
    private TextView titleText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);  // Make sure this matches your XML file name

        // Initialize Views
        scanButton = findViewById(R.id.scan);
        titleText = findViewById(R.id.tv_title);
        resultText = findViewById(R.id.text);

        // Set OnClickListener for the scan button
        scanButton.setOnClickListener(v -> {
            // Start QR code scan when the button is clicked
            initiateScan();
        });
    }

    // Method to initiate the QR code scan
    private void initiateScan() {
        // Use ZXing IntentIntegrator to start the scan activity
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);  // Only QR codes
        integrator.setPrompt("Scan a QR Code");  // Custom message
        integrator.setCameraId(0);  // Use the back camera
        integrator.setBeepEnabled(true);  // Enable beep when a code is scanned
        integrator.setBarcodeImageEnabled(true);  // Capture barcode image
        integrator.initiateScan();  // Start the scan
    }

    // This method will be called when the QR code scan finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of the scan
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // If a QR code was scanned, display the result
                resultText.setText("Scanned QR Code: " + result.getContents());
                Toast.makeText(this, "QR Code Scanned Successfully!", Toast.LENGTH_SHORT).show();
            } else {
                // If no QR code was found
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
