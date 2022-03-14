package com.hfad.wellbeingtech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<>();



        RecyclerView recyclerView = findViewById(R.id.recyclerViewr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CardData [] mcardData  = new CardData[] {

                new CardData("Stress Level",R.drawable.stress),
                new CardData("Incompetence Level",R.drawable.competence),
                new CardData("Difficulty Level",R.drawable.difficulty),
                new CardData("Submit Results",R.id.submit_btn)

        };

        CardAdapter cardAdapter = new CardAdapter(mcardData, MainActivity.this);
        recyclerView.setAdapter(cardAdapter);

    }

}