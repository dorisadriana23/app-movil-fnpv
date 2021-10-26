package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void pasar(View view) {
        Intent pasar=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(pasar);
    }
}
