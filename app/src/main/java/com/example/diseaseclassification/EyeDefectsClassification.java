package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EyeDefectsClassification extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1000;
    private static final int CAMERA_REQEUST_CODE = 10001;
    private static final String TAG = "RESULTS";
    private Button takepicture;
    private ClassificationModel imageClassifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_defects_classification);

        takepicture = (Button) findViewById(R.id.eclassify);
        initializeUIElements();
    }

    private void initializeUIElements() {
        Boolean category =  false;
        try {
            imageClassifier = new ClassificationModel(this, category);
        } catch (IOException e) {
            Log.e("Image Classifier Error", "ERROR: " + e);
        }
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hasPermission()) {
                    openCamera();
                } else {
                    requestPermission();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // if this is the result of our camera image request
        if (requestCode == CAMERA_REQEUST_CODE) {
            // getting bitmap of the image
            Bitmap photo = (Bitmap) Objects.requireNonNull(Objects.requireNonNull(data).getExtras()).get("data");
            // displaying this bitmap in imageview
            // pass this bitmap to classifier to make prediction
            List<ClassificationModel.Recognition> predicitons = imageClassifier.recognizeImage(
                    photo, 0);
            // creating a list of string to display in list view
            final List<String> predicitonsList = new ArrayList<>();
            for (ClassificationModel.Recognition recog : predicitons) {
                predicitonsList.add(recog.getName());

                //checks what is the results to show user the appropriate screen
                if ((predicitonsList.get(0)).equals("0 Bulging_Eyes") || (predicitonsList.get(0)).equals("1 Cataracts") ||
                        (predicitonsList.get(0)).equals("2 Crossed_Eyes") || (predicitonsList.get(0)).equals("3 Glaucoma") || (predicitonsList.get(0)).equals("4 Uveitis")){
                    //disease detected
                    Intent intent = new Intent(EyeDefectsClassification.this, ClassificationResults.class );
                    startActivity(intent);
                }else {
                    //not detected
                    Intent intent = new Intent(EyeDefectsClassification.this, eye_results_nd.class );
                    startActivity(intent);
                }
            }
            Log.d(TAG, "onActivityResult: Outside Loop "+predicitonsList.get(0));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // if this is the result of our camera permission request
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (hasAllPermissions(grantResults)) {
                openCamera();
            } else {
                requestPermission();
            }
        }
    }

    private boolean hasAllPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED)
                return false;
        }
        return true;
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQEUST_CODE);
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

}