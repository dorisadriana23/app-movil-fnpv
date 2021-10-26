package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class formulariotres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulariotres);
    }

    public void pasarusuario(View view) {
        Intent pasar=new Intent(formulariotres.this, LoginActivity.class);
        startActivity(pasar);
    }
}