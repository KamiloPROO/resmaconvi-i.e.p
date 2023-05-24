package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgregarTiposDeFalta extends AppCompatActivity {

    Button agregarFaltas;

    EditText descripcion;

    Spinner spinerFalta;

    String faltaSpinner;

    FirebaseFirestore firebaseFirestore;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tipo_de_falta);


        pd= new ProgressDialog(this);

        firebaseFirestore = FirebaseFirestore.getInstance();

        agregarFaltas = findViewById(R.id.btnAgregarTipoFalta);
        descripcion = findViewById(R.id.editDescriFalta);
        spinerFalta = findViewById(R.id.idSpinnerTipoFalta);


        referenciar();
        referenciar2();

    }

    private void referenciar() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, android.R.layout.simple_spinner_item);

        spinerFalta.setAdapter(adapter);

        spinerFalta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSpinner = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        agregarFaltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String tipoFalta = faltaSpinner.trim();
                String descriFalta = descripcion.getText().toString().trim();



                if (descriFalta.isEmpty() && tipoFalta.isEmpty()) {


                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();



                }else {

                    subimosFirebase(tipoFalta,descriFalta);

                }


            }

            private void subimosFirebase(String tipoFalta, String descriFalta) {

                pd.setTitle("Añadiendo Tipo de Falta...");

                pd.show();

                String id = UUID.randomUUID().toString();

                Map<String, Object> map = new HashMap<>();

                map.put("id", id);
                map.put("tipoFalta", tipoFalta);
                map.put("descripcion", descriFalta);



                firebaseFirestore.collection(tipoFalta).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        pd.dismiss();

                        Toast.makeText(AgregarTiposDeFalta.this, "Tipo de falta Agregada Correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AgregarTiposDeFalta.this, Administrador.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();

                        Toast.makeText(AgregarTiposDeFalta.this, "Error Al Agregar Tipo de Falta", Toast.LENGTH_SHORT).show();


                    }
                });



            }
        });


    }

    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setSelectedItemId(R.id.admin);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext()
                                , Perfil.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.admin:
                        startActivity(new Intent(getApplicationContext()
                                , Administrador.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.inicio:
                        startActivity(new Intent(getApplicationContext()
                                , Inicio.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.reportar:
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }

}