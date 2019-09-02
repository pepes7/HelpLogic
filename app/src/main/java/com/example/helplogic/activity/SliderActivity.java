package com.example.helplogic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helplogic.R;
import com.example.helplogic.activity.CadastroActivity;
import com.example.helplogic.activity.LoginActivity;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class SliderActivity extends IntroActivity {

    private Button entrar;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove os botões de avançar e voltar
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setButtonCtaVisible(false);


        //método para adicionar slider
        addSlide(new FragmentSlide.Builder()
                .background(R.color.appAzul)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.appAzul)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.appAzul)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.appAzul)
                .fragment(R.layout.intro_4)
                .canGoForward(false)
                .build());
    }

    public void btnEntrar(View view){
        startActivity(new Intent(this,LoginActivity.class));

    }

    public void btnCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
