package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SingUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
    }

    public void pasarinicio(View view) {
        Intent pasar=new Intent(SingUp.this, formularioActivity.class);
        startActivity(pasar);
    }
}