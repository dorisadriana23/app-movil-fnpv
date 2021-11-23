package com.adriana.fnpv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FormNovedades extends AppCompatActivity {
    EditText editcodh, mNombres, mApellidos, mIdentificacion, mNovedad,mEditText;
    Spinner spinner, planets_spinner;
    Button btnGuardarnovedades,btnAñadir;
    private ListView mListView;
    double rand;
    public static final String TAG = "DocSnippets";

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_novedades_users);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        db.setFirestoreSettings(settings);
        mNombres = findViewById(R.id.editnombre);
        mApellidos = findViewById(R.id.editapellidos);
        mIdentificacion = findViewById(R.id.editidentificacion);
        mListView=findViewById(R.id.ListView);
        planets_spinner = findViewById(R.id.planets_spinner);
        btnGuardarnovedades = findViewById(R.id.btnGuardarnovedades);
        btnAñadir=findViewById(R.id.btnAñadir);
        rand = Math.random();
        editcodh=findViewById(R.id.editcodh);
        editcodh.setText("" + rand);
        spinner = findViewById(R.id.novedades);

        //noinspection Convert2Lambda

        btnGuardarnovedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbtnGuardarnovedades();
            }
        });
    }

    private void setbtnGuardarnovedades() {
        Map<String, Object> map = new HashMap<>();

        String nombres = mNombres.getText().toString();
        String apellidos = mApellidos.getText().toString();
        String identificacion = mIdentificacion.getText().toString();
        String novedad = mNovedad.getText().toString();
        String codh = editcodh.getText().toString();

        map.put("Nombres", nombres);
        map.put("Apellidos", apellidos);
        map.put("Identificacion", identificacion);
        map.put("Novedades", novedad);
        map.put("CODH", rand);

        db.collection("Form_Novedades")
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

        Intent pasarte = new Intent(FormNovedades.this, LoginActivity.class);
        startActivity(pasarte);
        planets_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }


}




