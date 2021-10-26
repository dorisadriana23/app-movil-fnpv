package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class formulariodos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulariodos);


    }

    public void pasar3(View view) {
        Intent pasar3=new Intent(formulariodos.this, formulariotres.class);
        startActivity(pasar3);
    }

}