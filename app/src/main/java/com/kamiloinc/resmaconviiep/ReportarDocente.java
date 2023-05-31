package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.integrity.internal.a;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kamiloinc.resmaconviiep.Model.DataDocentes;
import com.kamiloinc.resmaconviiep.Model.DataSliderDocentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportarDocente extends AppCompatActivity {

    Spinner listDocentes, spinnerFaltas;

    ProgressDialog pd;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser user;

    String faltaSelect, docenteSelect;

    EditText editDescriReporte;

    Button agregarReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_docente);

        listDocentes = findViewById(R.id.idSpinnerReportDocente);
        firebaseFirestore = FirebaseFirestore.getInstance();
        spinnerFaltas = findViewById(R.id.idSpinnerTipoFal);
        editDescriReporte = findViewById(R.id.editReportDocente);
        agregarReporte = findViewById(R.id.btnReportarDocente);

        user = FirebaseAuth.getInstance().getCurrentUser();


        pd= new ProgressDialog(this);

        llenarSpiner();
        referenciar2();
        subirFirestore();

    }





    private void llenarSpiner() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSliderDocentes> docentes = new ArrayList<>();

        firebaseFirestore.collection("Docentes").orderBy("nombre", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombre = document.getString("nombre");


                                DataSliderDocentes datos = new DataSliderDocentes(cadenaNombre);
                                docentes.add(datos);

                                ArrayAdapter<DataSliderDocentes> arrayAdapter = new ArrayAdapter<>(ReportarDocente.this, android.R.layout.simple_dropdown_item_1line, docentes);
                                listDocentes.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });




    }

    private void subirFirestore() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, android.R.layout.simple_spinner_item);

        spinnerFaltas.setAdapter(adapter);

        spinnerFaltas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSelect = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        listDocentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                docenteSelect = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        agregarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tipoFalta = faltaSelect.trim();
                String docenteSelectEspinner = docenteSelect.trim();
                String descriReporte = editDescriReporte.getText().toString().trim();


                if (tipoFalta.isEmpty() && docenteSelectEspinner.isEmpty() && descriReporte.isEmpty()){

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();


                }else{

                subimosFirestore(tipoFalta,docenteSelectEspinner,descriReporte);
                subimosPersonal(tipoFalta,docenteSelectEspinner,descriReporte);

                }


            }




        });


    }



    private void subimosFirestore(String tipoFalta, String docenteSelectEspinner, String descriReporte) {

        pd.setTitle("Reportando Docente...");

        pd.show();

        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("tipoFalta", tipoFalta);
        map.put("docenteSeleccionado", docenteSelectEspinner);
        map.put("descripcionFalta",descriReporte);
        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);



        firebaseFirestore.collection("Reportes").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarDocente.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarDocente.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarDocente.this, "Error Al Reportar Docente", Toast.LENGTH_SHORT).show();


            }
        });






    }

    private void subimosPersonal(String tipoFalta, String docenteSelectEspinner, String descriReporte) {

        pd.setTitle("Reportando Docente...");

        pd.show();

        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("tipoFalta", tipoFalta);
        map.put("docenteSeleccionado", docenteSelectEspinner);
        map.put("descripcionFalta",descriReporte);
        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);



        firebaseFirestore.collection(nombreUser).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarDocente.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarDocente.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarDocente.this, "Error Al Reportar Docente", Toast.LENGTH_SHORT).show();


            }
        });





    }

    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setSelectedItemId(R.id.reportar);

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