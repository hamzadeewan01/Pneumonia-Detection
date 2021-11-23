package com.example.pneumoniadetection;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pneumoniadetection.ml.PneumoniaModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private Button  test;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private Button predict,upload;
    private ImageView img;
    private Uri mImageUri;
    private Bitmap bitmap;
    private TextView tv;
    private GifImageView scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);
        scanner=findViewById(R.id.scangif);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        predict=findViewById(R.id.scan);
        upload=findViewById(R.id.upload);
        img=findViewById(R.id.imageView);
        tv=findViewById(R.id.result);

        ActivityResultLauncher<Intent> imgResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                            mImageUri = data.getData();

                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
                                img.setImageBitmap(bitmap);
                                scanner.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        ActivityResultLauncher<Intent> secondActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK) {
                            String resultStr = data.getStringExtra("result");
                            switch (resultStr) {
                                case "Normal1":
                                    img.setImageResource(R.drawable.normal1);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.normal1);
                                    break;
                                case "Normal2":
                                    img.setImageResource(R.drawable.normal2);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.normal2);
                                    break;
                                case "Normal3":
                                    img.setImageResource(R.drawable.normal3);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.normal3);
                                    break;
                                case "Pneumonia1":
                                    img.setImageResource(R.drawable.pneumonia1);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pneumonia1);
                                    break;
                                case "Pneumonia2":
                                    img.setImageResource(R.drawable.pneumonia2);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pneumonia2);
                                    break;
                                case "Pneumonia3":
                                    img.setImageResource(R.drawable.pneumonia3);
                                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pneumonia3);
                                    break;
                            }
                            scanner.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), resultStr, Toast.LENGTH_SHORT).show();
                        }

                        if (resultCode == RESULT_CANCELED) {
                            // Nothing Selcted
                        }
                    }
                });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*"); // by this it shows only images to file chooser
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imgResultLauncher.launch(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentTest = new Intent(getApplicationContext(), SecondActivity.class);
                secondActivityResultLauncher.launch(intentTest);
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img.getDrawable()!=null){
                    bitmap=Bitmap.createScaledBitmap(bitmap,64,64,true);
                    try {
                        PneumoniaModel model = PneumoniaModel.newInstance(getApplicationContext());

                        // Creates inputs for reference.
                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 64, 64, 3}, DataType.FLOAT32);
                        TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                        tensorImage.load(bitmap);
                        ByteBuffer byteBuffer = tensorImage.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);

                        // Runs model inference and gets result.
                        PneumoniaModel.Outputs outputs = model.process(inputFeature0);
                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                        // Releases model resources if no longer used.
                        model.close();
                        if(outputFeature0.getFloatArray()[0]>0.5){
                            tv.setText("Pneumonia");
                        }else{
                            tv.setText("Normal");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        displayExceptionMessage(e.getMessage());

                    }

                }
                else Toast.makeText(getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        // Firebase sign out
        try {
            mAuth.signOut();
            // Google sign out
            mGoogleSignInClient.signOut().addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finishAffinity();
                            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                            finish();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.logout:signOut();
                             return true;
            case R.id.about_pneumonia:startActivity(new Intent(getApplicationContext(),AboutPneumonia.class));
                                      return true;
            default:         return false;
        }
    }
}