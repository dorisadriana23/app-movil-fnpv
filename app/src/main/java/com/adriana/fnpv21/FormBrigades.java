package com.adriana.fnpv21;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class FormBrigades extends Activity {

    private TextView tv;
    EditText mEditTextnombreconductor;
    EditText mEditTextAuxiliarEncargado;
    Button mButtonGuardarDatos;

    public static final String TAG = "DocSnippets";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_brigades);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        db.setFirestoreSettings(settings);

        mEditTextnombreconductor = findViewById(R.id.editConductor_n);
        mEditTextAuxiliarEncargado = findViewById(R.id.editAuxiliar_zona);
        mButtonGuardarDatos = findViewById(R.id.btnGuardarAsistent);

        //noinspection Convert2Lambda
        mButtonGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setmButtonGuardarDatos();
            }
        });
    }


    private void setmButtonGuardarDatos() {
        String nombre_conductor = mEditTextnombreconductor.getText().toString();
        String Auxiliar_encargado = mEditTextAuxiliarEncargado.getText().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("name_chofer", nombre_conductor);
        map.put("asistent_birgade", Auxiliar_encargado);
        //db.collection("formulario_brigadas").document("2").set(map);

        db.collection("formulario_brigadas")
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
    }

}

