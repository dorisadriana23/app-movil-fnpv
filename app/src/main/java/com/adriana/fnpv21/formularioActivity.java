package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class formularioActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


    }


    public void pasarinfo(View view) {
        Intent pasarinfo=new Intent(formularioActivity.this, formulariodos.class);
        startActivity(pasarinfo);
    }
}