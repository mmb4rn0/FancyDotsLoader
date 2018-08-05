package com.mmbarno.fancydotsloader;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mmbarno.dotsloader.DotsLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DotsLoader dotsLoaderCopy = findViewById(R.id.dot_loader);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsCount(dotsLoaderCopy.getDotsCount() + 1);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsColor(Color.parseColor("#000000"));
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsSize(dotsLoaderCopy.getDotsSize() + 5);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsSpacing(dotsLoaderCopy.getDotsSpacing() + 4);
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsCornerRadius(dotsLoaderCopy.getDotsCornerRadius() <= -1 ? 10 : -1);
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setDotsStrokeWidth(dotsLoaderCopy.getDotsStrokeWidth() + 2);
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotsLoaderCopy.setTransitionDuration(1000);
            }
        });
    }
}
