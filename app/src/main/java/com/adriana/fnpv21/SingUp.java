package com.adriana.fnpv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.adriana.fnpv21.Entity.Cargo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingUp extends AppCompatActivity implements View.OnClickListener {


    EditText mNombre, mApellido, mCargo, midentificacion, mtelefono, memail, mcontrasena;
    private Button btnregistrar, btnlogin;
    private FirebaseAuth firebaseAuth;

    private List<Cargo> listPerson = new ArrayList<Cargo>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Cargo cargo;

    ListView listV_personas;

    ArrayAdapter<Cargo> arrayAdapterPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        firebaseAuth = firebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
        mNombre = (EditText) findViewById(R.id.nombre);
        mApellido = (EditText) findViewById(R.id.apellido);
        mCargo = (EditText) findViewById(R.id.cargo);
        midentificacion = (EditText) findViewById(R.id.identificacion_usuario);
        mtelefono = (EditText) findViewById(R.id.usuario_telefono);
        memail = (EditText) findViewById(R.id.email_usuario);
        mcontrasena = (EditText) findViewById(R.id.usuario_contrasena);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        btnlogin = (Button) findViewById(R.id.btnloguearse);
        inicializarFirebase();
        //noinspection RedundantCast
        btnregistrar.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("Cargo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Cargo p = objSnaptshot.getValue(Cargo.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Cargo>(SingUp.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void setBtnregistrar() {
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
        //noinspection Convert2Lambda
        firebaseAuth.createUserWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SingUp.this, "se ha registrado el usuario con su  email", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//se presenta una colision

                                Toast.makeText(SingUp.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SingUp.this, "No se pudo registrar el usuari", Toast.LENGTH_LONG).show();

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
            case R.id.btnloguearse:
                loguearUsuario();
                break;

        }
    }

    private void loguearUsuario() {
    }
}


