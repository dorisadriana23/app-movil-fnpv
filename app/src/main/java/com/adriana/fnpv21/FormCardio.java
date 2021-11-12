package com.adriana.fnpv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class FormCardio extends AppCompatActivity {
    EditText mEditTextHta_users_atendidos, mEditextDm_users_atendidos, mHta_Usuarios_ingresan,mDm_users_ingresan;
    EditText mobservaciones, edithta_dmSum,edithta_dmSuma;

    Button mButtonGuardarDatos;
    public static final String TAG = "DocSnippets";

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cardio);
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();


        db.setFirestoreSettings(settings);
        mEditTextHta_users_atendidos = findViewById(R.id.hta_atendidos);
        mEditextDm_users_atendidos= findViewById(R.id.dm_atendidos);
        mHta_Usuarios_ingresan = findViewById(R.id.ingresan_hta);
        mDm_users_ingresan = findViewById(R.id.ingresan_dm);
        mobservaciones = findViewById(R.id.observaciones);
        mButtonGuardarDatos = findViewById(R.id.btnGuardar);
        LinearLayout linearLayout;

        edithta_dmSum = findViewById(R.id.edithta_dm);
        edithta_dmSuma = findViewById(R.id.ediTHTA_DM);

        linearLayout = findViewById(R.id.linerLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hta_atendidos = mEditTextHta_users_atendidos.getText().toString();
                String dm_atendidos = mEditextDm_users_atendidos.getText().toString();
                String users_ingresan_hta= mHta_Usuarios_ingresan.getText().toString();
                String user_ingresan_dm = mDm_users_ingresan.getText().toString();

                //usuarios que ingresan

                int suma = Integer.valueOf(hta_atendidos)  + Integer.valueOf(dm_atendidos);
                String result = String.valueOf(suma);
                edithta_dmSum.setText("Total usuarios atendidos"+result);
                // ingresan
                int sumas = Integer.valueOf(users_ingresan_hta)  + Integer.valueOf(user_ingresan_dm);
                String results = String.valueOf(sumas);
                edithta_dmSuma.setText("Total usuarios que ingresan " +results);
            }
        });


        mButtonGuardarDatos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setmButtonGuardarDatos();

                EditText sum;
                sum = findViewById(R.id.edithta_dm);
                sum.setText(mEditTextHta_users_atendidos.getText() + "/" + mEditextDm_users_atendidos.getText());

                //la otra suma
                EditText suma;
                suma = findViewById(R.id.ingresan_hta);
                suma.setText(mHta_Usuarios_ingresan.getText() + "/" + mDm_users_ingresan.getText());

            }
        });
    }

    private void setmButtonGuardarDatos() {
        Map<String, Object> map = new HashMap<>();

        String hta_atendidos = mEditTextHta_users_atendidos.getText().toString();
        String dm_atendidos = mEditextDm_users_atendidos.getText().toString();
        String Hta_ingresan = mDm_users_ingresan.getText().toString();
        String Dm_ingresan = mDm_users_ingresan.getText().toString();

        map.put("hta", hta_atendidos);
        map.put("dm", dm_atendidos);
        map.put("Hta", Hta_ingresan);
        map.put("Dm", Dm_ingresan);

        db.collection("form_cardio")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        Intent pasarte = new Intent(FormCardio.this, FormNovedades.class);
        startActivity(pasarte);
    }


}


