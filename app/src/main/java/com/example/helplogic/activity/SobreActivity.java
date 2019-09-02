package com.example.helplogic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helplogic.R;

import mehdi.sakout.aboutpage.AboutPage;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sobre);

        View aboutPage = new AboutPage(this)
                .setImage(R.drawable.logo_preta)
                .addGroup("Fale conosco")
                .addEmail("helplogic2019@gmail.com","Envie um e-mail")
                .addGroup("Acesse nosso Github")
                .addGitHub("pepes7/helplogic","Github")
                .create();

        getSupportActionBar().setTitle("Sobre");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(aboutPage);
    }
}
