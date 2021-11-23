package com.adriana.fnpv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mNombre, midentificacion, mtelefono, memail, mcontrasena;
    private Button btnregistrar, btnlogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = firebaseAuth.getInstance();

        memail = (EditText) findViewById(R.id.usuario);
        mcontrasena = (EditText) findViewById(R.id.contrasena);
        btnlogin=(Button)findViewById(R.id.btninicioSesion);

        btnlogin.setOnClickListener(this);
    }

    public void pasarregistrosusuarios(View view) {
        Intent pasar=new Intent(LoginActivity.this, SingUp.class);
        startActivity(pasar);
    }

    public void pasarregistrosusuariosu(View view) {
        Intent pasar=new Intent(LoginActivity.this, FormBrigades.class);
        startActivity(pasar);
    }



    private void loguearUsuario() {
        String email = memail.getText().toString().trim();
        String contrasena = mcontrasena.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "se debe ingresar un email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        //loguear un nuevo usuario
        //noinspection Convert2Lambda
        firebaseAuth.signInWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intencion = new Intent(getApplication(), FormBrigades.class);
                            intencion.putExtra(FormBrigades.user, email);
                            startActivity(intencion);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//se presenta una colision

                                Toast.makeText(LoginActivity.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(LoginActivity.this, "No se pudo registrar el usuari", Toast.LENGTH_LONG).show();

                            }
                        }

                    }

                });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnregistrar:
                setBtnregistrar();
                break;
            case R.id.btninicioSesion:
                loguearUsuario();
                break;

        }
    }

    private void setBtnregistrar() {
    }


}




