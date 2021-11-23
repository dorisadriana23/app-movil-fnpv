package com.adriana.fnpv21;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormBrigades extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static String user;
    private TextView tv;
    EditText mEditTextHoraEvento, mEditTextnombreconductor, mEditTextAuxiliarEncargado, mEditTextLugarEvento, mEditTextDecriptionActivit, mEditTextnumberAsistents;
    EditText datePickerDateEvent;
    private ListView listView;
    Button mButtonGuardarDatos, btnAdd;

    List<String> stringList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    public static final String TAG = "DocSnippets";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_brigades);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        db.setFirestoreSettings(settings);
        mEditTextLugarEvento = findViewById(R.id.editlugar_evento);
        datePickerDateEvent = findViewById(R.id.editfecha_evento);
        mEditTextHoraEvento = findViewById(R.id.edithora_evento);
        mEditTextnombreconductor = findViewById(R.id.editConductor_n);
        mEditTextAuxiliarEncargado = findViewById(R.id.editAuxiliar_zona);
        mEditTextDecriptionActivit = findViewById(R.id.editdescripcion_actividad);
        mButtonGuardarDatos = findViewById(R.id.btnGuardarAsistent);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        listView = findViewById(R.id.listviewIPS);
        listView.setOnItemClickListener(this);
        mEditTextnumberAsistents = findViewById(R.id.editasistentes);

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
        String hora_evento = mEditTextHoraEvento.getText().toString();
        String integrantes = "";
        for (int i = 0; i < stringList.size(); i++) {
            integrantes += stringList.get(i) + ",";
        }

        map.put("lugar_evento", lugar_evento);
        map.put("name_chofer", nombre_conductor);
        map.put("date_event", fecha_evento);
        map.put("hora_event", hora_evento);
        map.put("integrantes", integrantes);

        //db.collection("formulario_brigadas").document("2").set(map);

        db.collection("formulario_brigadas2")
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

        Intent pasarte = new Intent(FormBrigades.this, FormCardio.class);
        startActivity(pasarte);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String text = mEditTextnumberAsistents.getText().toString().trim();
                stringList.add(text);
                mEditTextnumberAsistents.getText().clear();
                arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
                listView.setAdapter(arrayAdapter);
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Clicked" + i, Toast.LENGTH_SHORT).show();
    }
}


