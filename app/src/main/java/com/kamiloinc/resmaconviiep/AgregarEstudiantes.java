package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgregarEstudiantes extends AppCompatActivity {

    Button agregarEstudiante;

    EditText nombre,numDocumento,sede ;

    Spinner spinnerCurso;

    String cursoSpinner;

    FirebaseFirestore firebaseFirestore;

    ProgressDialog pd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiante);

        agregarEstudiante = findViewById(R.id.btnAgregarEstudianteForm);

        nombre = findViewById(R.id.editNombreEstudiante);
        numDocumento = findViewById(R.id.editNumDocuEstudiante);
        sede = findViewById(R.id.editSedeEstudiante);
        spinnerCurso = findViewById(R.id.idSpinnerCurso);

        pd= new ProgressDialog(this);

        firebaseFirestore = FirebaseFirestore.getInstance();

        referenciar();
        referenciar2();

    }

    private void referenciar() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cursos, android.R.layout.simple_spinner_item);

        spinnerCurso.setAdapter(adapter);

        spinnerCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cursoSpinner = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        agregarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreEstudiante = nombre.getText().toString().trim();
                String numDocuEstudiante = numDocumento.getText().toString().trim();
                String cursoEstudiante = cursoSpinner.trim();
                String sedeEstudiante = sede.getText().toString().trim();



                if (nombreEstudiante.isEmpty() && numDocuEstudiante.isEmpty() && sedeEstudiante.isEmpty() && cursoEstudiante.isEmpty() ) {


                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();



                }else {

                    subimosFirebase(nombreEstudiante,numDocuEstudiante,cursoEstudiante,sedeEstudiante);

                    //subimosPersonal(nombreEstudiante,numDocuEstudiante,cursoEstudiante,sedeEstudiante);

                }


            }

            private void subimosFirebase(String nombreEstudiante, String numDocuEstudiante, String cursoEstudiante, String sedeEstudiante) {

                pd.setTitle("Añadiendo Estudiante...");

                pd.show();

                String id = UUID.randomUUID().toString();

                Map<String, Object> map = new HashMap<>();

                map.put("id", id);
                map.put("nombre", nombreEstudiante);
                map.put("numDocumento", numDocuEstudiante);
                map.put("curso", cursoEstudiante);
                map.put("sede", sedeEstudiante);


                firebaseFirestore.collection(cursoEstudiante).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        pd.dismiss();

                        Toast.makeText(AgregarEstudiantes.this, "Estudiante Agregado Correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent( AgregarEstudiantes.this, Administrador.class );
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();

                        Toast.makeText(AgregarEstudiantes.this, "Error Al Agregar Estudiante", Toast.LENGTH_SHORT).show();


                    }
                });



            }
        });


    }

    /* private void subimosPersonal(String nombreEstudiante, String numDocuEstudiante, String cursoEstudiante, String sedeEstudiante) {

        pd.setTitle("Añadiendo Estudiante...");

        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("nombre", nombreEstudiante);
        map.put("numDocumento", numDocuEstudiante);
        map.put("curso", cursoEstudiante);
        map.put("sede", sedeEstudiante);


        firebaseFirestore.collection("personal").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(AgregarEstudiantes.this, "Estudiante Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent( AgregarEstudiantes.this, Administrador.class );
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(AgregarEstudiantes.this, "Error Al Agregar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });


    }*/

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