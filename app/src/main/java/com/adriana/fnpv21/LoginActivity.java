package com.adriana.fnpv21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginActivity extends AppCompatActivity {


 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_login);
 }

 public void pasarregistro(View view) {
  Intent pasar=new Intent(LoginActivity.this, SingUp.class);
  startActivity(pasar);
 }

 public void pasarformulario(View view) {
  Intent pasar=new Intent(LoginActivity.this, FormBrigades.class);
  startActivity(pasar);
 }
}



