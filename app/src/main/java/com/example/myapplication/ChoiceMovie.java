package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class ChoiceMovie extends MovieReview {

    ImageButton ib1, ib2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);

        ib1 = findViewById(R.id.ib1);
        ib2 = findViewById(R.id.ib2);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MovieReview.class);
                startActivity(intent);


            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MovieReview.class);
                startActivity(intent);
            }
        });

    }
}
