package com.adriana.fnpv21;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormBrigades extends Activity {

    private TextView tv;
    EditText mEditTextnombreconductor, mEditTextAuxiliarEncargado ,mEditTextLugarEvento,mEditTextDecriptionActivit,mEditTextnumberAsistents;
    EditText datePickerDateEvent;

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
       mEditTextLugarEvento=findViewById(R.id.editlugar_evento);
       datePickerDateEvent = findViewById(R.id.editfecha_evento);
        mEditTextnombreconductor = findViewById(R.id.editConductor_n);
        mEditTextAuxiliarEncargado = findViewById(R.id.editAuxiliar_zona);
        mEditTextDecriptionActivit=findViewById(R.id.editdescripcion_actividad);
        mEditTextnumberAsistents=findViewById(R.id.editasistentes);
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
        Map<String, Object> map = new HashMap<>();

        String lugar_evento = mEditTextLugarEvento.getText().toString();
        String nombre_conductor = mEditTextnombreconductor.getText().toString();
        String fecha_evento = datePickerDateEvent.getText().toString();
        String Auxiliar_encargado = mEditTextAuxiliarEncargado.getText().toString();
        String descripcion_actividad = mEditTextAuxiliarEncargado.getText().toString();

        map.put("lugar_evento",lugar_evento);
        map.put("name_chofer", nombre_conductor);
        map.put("date_event",fecha_evento);
        map.put("asistent_birgade", Auxiliar_encargado);
        map.put("descripcion_activity",descripcion_actividad);

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


