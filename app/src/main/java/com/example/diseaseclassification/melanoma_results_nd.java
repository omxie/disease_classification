package com.example.diseaseclassification;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class melanoma_results_nd extends AppCompatActivity {
    private static final int CAMERA_REQEUST_CODE = 10001;
    private static final String TAG = "RESULTS";
    private Button takepicture;
    private ClassificationModel imageClassifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melanoma_results_nd);

        takepicture = (Button) findViewById(R.id.mclassify_re);
        try {
            imageClassifier = new ClassificationModel(this, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();

            }
        });

    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQEUST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // if this is the result of our camera image request
        if (requestCode == CAMERA_REQEUST_CODE) {
            // getting bitmap of the image
            Bitmap photo = (Bitmap) Objects.requireNonNull(Objects.requireNonNull(data).getExtras()).get("data");
            // displaying this bitmap in imageview
            //imageView.setImageBitmap(photo);
            // pass this bitmap to classifier to make prediction
            List<ClassificationModel.Recognition> predicitons = imageClassifier.recognizeImage(
                    photo, 0);
            // creating a list of string to display in list view
            final List<String> predicitonsList = new ArrayList<>();
            for (ClassificationModel.Recognition recog : predicitons) {
                predicitonsList.add(recog.getName());
                //checks what is the results to show user the appropriate screen
                if ((predicitonsList.get(0)).equals("0 Benign")){
                    //Benign = Melanoma is not cancerous.
                    Intent intent = new Intent(melanoma_results_nd.this, melanoma_results_nd.class );
                    startActivity(intent);
                }else {
                    //Malignant = Melanoma is cancerous.
                    Intent intent = new Intent(melanoma_results_nd.this, MelanomaResults.class );
                    startActivity(intent);
                }
            }
            Log.d(TAG, "onActivityResult: Outside Loop "+predicitonsList.get(0));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}