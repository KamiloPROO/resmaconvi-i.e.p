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

public class AgregarDeberesDerechos extends AppCompatActivity {

    Button agregarDeber;

    EditText descripcion;

    Spinner spinerDeber;

    String deberSpinner;

    FirebaseFirestore firebaseFirestore;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_deberes_derechos);

        pd= new ProgressDialog(this);

        firebaseFirestore = FirebaseFirestore.getInstance();

        agregarDeber = findViewById(R.id.btnAgregarDeberDerecho);
        descripcion = findViewById(R.id.editDescriDeberDerecho);
        spinerDeber = findViewById(R.id.idSpinnerDeberDerecho);

        referenciar();
        referenciar2();

    }

    private void referenciar() {


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.DeberesDerechos, R.layout.styli_spiner);

        spinerDeber.setAdapter(adapter);

        spinerDeber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                deberSpinner = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        agregarDeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String tipoFalta = deberSpinner.trim();
                String descriFalta = descripcion.getText().toString().trim();



                if (descriFalta.isEmpty() && tipoFalta.isEmpty()) {


                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();



                }else {

                    subimosFirebase(tipoFalta,descriFalta);

                }


            }

            private void subimosFirebase(String tipoFalta, String descriFalta) {

                pd.setTitle("Añadiendo Dato...");

                pd.show();

                String id = UUID.randomUUID().toString();

                Map<String, Object> map = new HashMap<>();

                map.put("id", id);
                map.put("tipoDeber", tipoFalta);
                map.put("descripcion", descriFalta);



                firebaseFirestore.collection(tipoFalta).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        pd.dismiss();

                        Toast.makeText(AgregarDeberesDerechos.this, "Dato Agregado Correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AgregarDeberesDerechos.this, Administrador.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();

                        Toast.makeText(AgregarDeberesDerechos.this, "Error Al Agregar Dato", Toast.LENGTH_SHORT).show();


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