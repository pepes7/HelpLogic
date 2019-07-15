package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

public class ExercicioLogica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_logica);
    }

    public void ex1(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioLogica1.class));
    }

    public void ex2(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioLogica2.class));
    }

    public void ex3(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioLogica3.class));
    }

    public void ex4(View view) {
        startActivity(new Intent(getApplicationContext(), ExercicioLogica4.class));
    }
}
