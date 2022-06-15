package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinAccept extends AppCompatActivity {

    Button btncheck;
    EditText etjoinname,etjoinID,etjoinPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        btncheck = findViewById(R.id.btncheck);
        etjoinname = findViewById(R.id.etjoinname);
        etjoinID = findViewById(R.id.etjoinid);
        etjoinPW = findViewById(R.id.etjoinpw);

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),JoinChoiceMovie.class);
                startActivity(intent);
            }
        });
    }
}
