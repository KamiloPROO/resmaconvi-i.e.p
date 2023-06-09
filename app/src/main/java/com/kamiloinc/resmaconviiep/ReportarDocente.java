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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportarDocente extends AppCompatActivity {

    Spinner listDocentes, spinnerFaltas, spinnerPeriodos;

    ProgressDialog pd;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser user;

    String faltaSelect, docenteSelect, periodoSelect;

    EditText editDescriReporte;

    Button agregarReporte;

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_docente);

        data = getIntent().getExtras();

        listDocentes = findViewById(R.id.idSpinnerReportDocente);
        firebaseFirestore = FirebaseFirestore.getInstance();
        spinnerFaltas = findViewById(R.id.idSpinnerTipoFal);
        spinnerPeriodos = findViewById(R.id.idSpinnerPeriodos);
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

                                ArrayAdapter<DataSliderDocentes> arrayAdapter = new ArrayAdapter<>(ReportarDocente.this, R.layout.styli_spiner, docentes);
                                listDocentes.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });




    }

    private void subirFirestore() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FaltasDocentes, R.layout.styli_spiner);

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

        ArrayAdapter<CharSequence> adapterPeriodos = ArrayAdapter.createFromResource(this,R.array.PeriodosAcademicos, R.layout.styli_spiner);

        spinnerPeriodos.setAdapter(adapterPeriodos);

        spinnerPeriodos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                periodoSelect = adapterView.getItemAtPosition(i).toString();

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
                String periodoSelectEspinner = periodoSelect.trim();
                String descriReporte = editDescriReporte.getText().toString().trim();


                if (tipoFalta.isEmpty() && docenteSelectEspinner.isEmpty() && descriReporte.isEmpty() && periodoSelectEspinner.isEmpty()){

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();


                }else{

                subimosFirestore(periodoSelectEspinner,tipoFalta,docenteSelectEspinner,descriReporte);
                subimosPersonal(periodoSelectEspinner,tipoFalta,docenteSelectEspinner,descriReporte);

                }


            }




        });


    }



    private void subimosFirestore(String periodoSelectEspinner,String tipoFalta, String docenteSelectEspinner, String descriReporte) {

        String anio = data.getString("anioSeleccionadoDocente");
        String docen = "Docente";

        String guardarCollecion =  periodoSelectEspinner +" "+ anio +" "+ docen ;

        pd.setTitle("Reportando Docente...");

        pd.show();

        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Date date = new Date();
        String fecha = date.toString();

        Map<String, Object> map = new HashMap<>();


        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);

        map.put("id", id);
        map.put("año",anio);
        map.put("fecha", fecha);
        map.put("tipoFaltaSeleccionado", "Incumplimiento a sus Obligaciones");
        map.put("periodoAcademico", periodoSelectEspinner);



        map.put("cursoSeleccionado", "Docente");
        map.put("faltaCometida", tipoFalta);
        map.put("personaReportada", docenteSelectEspinner);
        map.put("compromisoEstudiante", descriReporte);



        firebaseFirestore.collection(guardarCollecion).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private void subimosPersonal(String periodoSelectEspinner, String tipoFalta, String docenteSelectEspinner, String descriReporte) {

        String anio = data.getString("anioSeleccionadoDocente");

        pd.setTitle("Reportando Docente...");

        pd.show();

        String id = UUID.randomUUID().toString();
        String idUser = user.getUid();
        String correoUser = user.getEmail();
        String nombreUser = user.getDisplayName();
        String imgCorreo = user.getPhotoUrl().toString();

        Date date = new Date();
        String fecha = date.toString();

        Map<String, Object> map = new HashMap<>();


        map.put("idUser",idUser);
        map.put("correoUser",correoUser);
        map.put("nombreUser",nombreUser);
        map.put("imgCorreo",imgCorreo);

        map.put("id", id);
        map.put("año",anio);
        map.put("fecha", fecha);
        map.put("tipoFaltaSeleccionado", "Incumplimiento a sus Obligaciones");
        map.put("periodoAcademico", periodoSelectEspinner);




        map.put("cursoSeleccionado", "Docente");
        map.put("faltaCometida", tipoFalta);
        map.put("personaReportada", docenteSelectEspinner);
        map.put("compromisoEstudiante", descriReporte);




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