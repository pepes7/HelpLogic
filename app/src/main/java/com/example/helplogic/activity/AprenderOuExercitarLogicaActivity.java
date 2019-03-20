package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.helplogic.R;

public class AprenderOuExercitarLogicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_ou_exercitar_logica);
    }

    public void aprender(View view) {
        startActivity(new Intent(getApplicationContext(), AprenderLogicaActivity.class));
    }

    public void exercitar(View view) {
        startActivity(new Intent(getApplicationContext(), ExercitarLogicaActivity.class));
    }
}
