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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class SingUp extends AppCompatActivity implements View.OnClickListener {


    EditText mNombre, midentificacion, mtelefono, memail, mcontrasena;
    private Button btnregistrar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        firebaseAuth = firebaseAuth.getInstance();
        mNombre = (EditText) findViewById(R.id.nombre_completo);
        midentificacion = (EditText) findViewById(R.id.identificacion_usuario);
        mtelefono = (EditText) findViewById(R.id.email_usuario);
        memail = (EditText) findViewById(R.id.usuario_telefono);
        mcontrasena = (EditText) findViewById(R.id.usuario_contrasena);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        //noinspection RedundantCast
        btnregistrar.setOnClickListener(this);


    }

    private void setBtnregistrar() {
        String Nombre = memail.getText().toString().trim();
        String Identificacion = midentificacion.getText().toString().trim();
        String telefono = mtelefono.getText().toString().trim();
        String email = memail.getText().toString().trim();
        String contrasena = mcontrasena.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"se debe ingresar un email",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this,"se debe ingresar una contraseña",Toast.LENGTH_SHORT).show();
             return;
        }
        //noinspection Convert2Lambda
        firebaseAuth.createUserWithEmailAndPassword(email,contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SingUp.this,"se ha registrado el email",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SingUp.this,"No se pudo registrar el usuari",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        setBtnregistrar();
    }

}


