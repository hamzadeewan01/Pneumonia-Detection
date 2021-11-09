package com.example.pneumoniadetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String str1 = "1";
        ImageButton button1 = findViewById(R.id.button1);
        ImageButton button2 = findViewById(R.id.button2);
        ImageButton button3 = findViewById(R.id.button3);
        ImageButton button4 = findViewById(R.id.button4);
        ImageButton button5 = findViewById(R.id.button5);
        ImageButton button6 = findViewById(R.id.button6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Normal1");

                setResult(RESULT_OK, resultIntent);
                finish();
              // startActivity(new Intent(getApplicationContext(),MainActivity.class));
               //finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Normal2");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Normal3");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Pneumonia1");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Pneumonia2");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Pneumonia3");

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
//        Drawable d1 = getResources().getDrawable(R.drawable.normal1);
//        Drawable d2 = getResources().getDrawable(R.drawable.normal2);
//        Drawable d3 = getResources().getDrawable(R.drawable.normal3);
//        Drawable d4 = getResources().getDrawable(R.drawable.pneumonia1);
//        Drawable d5 = getResources().getDrawable(R.drawable.pneumonia2);
//        Drawable d6 = getResources().getDrawable(R.drawable.pneumonia3);
//        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
//        Intent intent = new Intent(this,MainActivity.class);
//
////        button1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(getApplicationContext(), "Normal1", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        button1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Normal1", Toast.LENGTH_SHORT).show();
//                    button1.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button1.setBackground(d1);
//                    intent.putExtra("pos", "1");
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });
//
//        button2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Normal2", Toast.LENGTH_SHORT).show();
//                    button2.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button2.setBackground(d2);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });
//
//        button3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Normal3", Toast.LENGTH_SHORT).show();
//                    button3.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button3.setBackground(d3);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });
//
//        button4.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Pneumonia1", Toast.LENGTH_SHORT).show();
//                    button4.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button4.setBackground(d4);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });
//
//        button5.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Pneumonia2", Toast.LENGTH_SHORT).show();
//                    button5.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button5.setBackground(d5);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });
//
//        button6.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getApplicationContext(), "Pneumonia3", Toast.LENGTH_SHORT).show();
//                    button6.setBackground(transparentDrawable);
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    button6.setBackground(d6);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });

    }
}