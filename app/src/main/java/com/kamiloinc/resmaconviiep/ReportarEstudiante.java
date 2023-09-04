package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.kamiloinc.resmaconviiep.Model.DataSliderDocentes;
import com.kamiloinc.resmaconviiep.Model.DataSpinnerEstudiante;
import com.kamiloinc.resmaconviiep.Model.DataSpinnerFaltasCometidas;
import com.kamiloinc.resmaconviiep.Model.DataVerTodosLosReportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ReportarEstudiante extends AppCompatActivity {


    Spinner ListCursos, ListEstudiantes, ListTipoDeFalta, ListFaltas, peridos, docenteRealizaFalta, spinnerTipoFaltasNumero2, spinnerFaltaCometidaNumero2,spinnerTipoFaltasNumero3, spinnerFaltaCometidaNumero3;

    Spinner spinnerVerFaltasProtocolo ;

    TextView txtTitutloProtocolo , txtSelectTipoFaltaProtocolo;

    TextView txtTituloProcoloFaltasTipo1N1,txtTituloProcoloFaltasTipo1N2,txtTituloProcoloFaltasTipo1N3,txtTituloProcoloFaltasTipo1N4;

    TextView txtTituloProcoloFaltasTipo2N1,txtTituloProcoloFaltasTipo2N2,txtTituloProcoloFaltasTipo2N3,txtTituloProcoloFaltasTipo2N4,txtTituloProcoloFaltasTipo2N5,txtTituloProcoloFaltasTipo2N6;

    TextView txtTituloProcoloFaltasTipo3N1,txtTituloProcoloFaltasTipo3N2,txtTituloProcoloFaltasTipo3N3,txtTituloProcoloFaltasTipo3N4,txtTituloProcoloFaltasTipo3N5,txtTituloProcoloFaltasTipo3N6,txtTituloProcoloFaltasTipo3N7,txtTituloProcoloFaltasTipo3N8,txtTituloProcoloFaltasTipo3N9,txtTituloProcoloFaltasTipo3N10;
    TextView selectEstudiante, SelecFaltaTipo, SelecFaltaCometida, TxtDocenteRealizaFalta , txtTipoFaltaNumero2, txtFaltaCometidaNumero2,txtTipoFaltaNumero3, txtFaltaCometidaNumero3;

    ProgressDialog pd;

    FirebaseFirestore firebaseFirestore;

    FirebaseUser user;

    String cursoSelect, estudianteSelect, tipoFaltaSelect, faltaSelect ,periSelect,docenteSelectFalta,tipoFaltaSelectNumero2,faltaSelectNumero2,tipoFaltaSelectNumero3,faltaSelectNumero3, tipoFaltaSelectProtocolo;

    EditText editCompromisoEstudiante;

    Button reportarEstudiante, agregarSegundaFalta, agregarTercerFalta, verHistorial, cerrarHistorial;

    RecyclerView historialEstudiante;

    Bundle data;

    List<DataVerTodosLosReportes> listDatos;
    AdaptadorVerReportes adaptadorEstudiantes;
    FirebaseStorage storageRef;
    FirebaseFirestore db;

    RecyclerView recyclerView;

    String UsuarioAdmin;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_estudiante);

        UsuarioAdmin = "depresivo";

        data = getIntent().getExtras();

        selectEstudiante = findViewById(R.id.textSelecEstudiante);
        SelecFaltaTipo = findViewById(R.id.textSelecFaltaTipo);
        SelecFaltaCometida = findViewById(R.id.textSelecFaltaCometida);
        TxtDocenteRealizaFalta = findViewById(R.id.textSelecDocenteQueReporta);
        txtTipoFaltaNumero2 = findViewById(R.id.textSelecFaltaTipoNumero2);
        txtFaltaCometidaNumero2 = findViewById(R.id.textSelecFaltaCometidaNumero2);
        txtTipoFaltaNumero3 = findViewById(R.id.textSelecFaltaTipoNumero3);
        txtFaltaCometidaNumero3 = findViewById(R.id.textSelecFaltaCometidaNumero3);
        txtTitutloProtocolo = findViewById(R.id.textSelecVerProtocolo);
        txtSelectTipoFaltaProtocolo = findViewById(R.id.textSelecFaltaTipoProtocolo);

        txtTituloProcoloFaltasTipo1N1 = findViewById(R.id.idTxtProtocoloFaltasTipo1N1);
        txtTituloProcoloFaltasTipo1N2 = findViewById(R.id.idTxtProtocoloFaltasTipo1N2);
        txtTituloProcoloFaltasTipo1N3 = findViewById(R.id.idTxtProtocoloFaltasTipo1N3);
        txtTituloProcoloFaltasTipo1N4 = findViewById(R.id.idTxtProtocoloFaltasTipo1N4);

        txtTituloProcoloFaltasTipo2N1 = findViewById(R.id.idTxtProtocoloFaltasTipo2N1);
        txtTituloProcoloFaltasTipo2N2 = findViewById(R.id.idTxtProtocoloFaltasTipo2N2);
        txtTituloProcoloFaltasTipo2N3 = findViewById(R.id.idTxtProtocoloFaltasTipo2N3);
        txtTituloProcoloFaltasTipo2N4 = findViewById(R.id.idTxtProtocoloFaltasTipo2N4);
        txtTituloProcoloFaltasTipo2N5 = findViewById(R.id.idTxtProtocoloFaltasTipo2N5);
        txtTituloProcoloFaltasTipo2N6 = findViewById(R.id.idTxtProtocoloFaltasTipo2N6);

        txtTituloProcoloFaltasTipo3N1 = findViewById(R.id.idTxtProtocoloFaltasTipo3N1);
        txtTituloProcoloFaltasTipo3N2 = findViewById(R.id.idTxtProtocoloFaltasTipo3N2);
        txtTituloProcoloFaltasTipo3N3 = findViewById(R.id.idTxtProtocoloFaltasTipo3N3);
        txtTituloProcoloFaltasTipo3N4 = findViewById(R.id.idTxtProtocoloFaltasTipo3N4);
        txtTituloProcoloFaltasTipo3N5 = findViewById(R.id.idTxtProtocoloFaltasTipo3N5);
        txtTituloProcoloFaltasTipo3N6 = findViewById(R.id.idTxtProtocoloFaltasTipo3N6);
        txtTituloProcoloFaltasTipo3N7 = findViewById(R.id.idTxtProtocoloFaltasTipo3N7);
        txtTituloProcoloFaltasTipo3N8 = findViewById(R.id.idTxtProtocoloFaltasTipo3N8);
        txtTituloProcoloFaltasTipo3N9 = findViewById(R.id.idTxtProtocoloFaltasTipo3N9);
        txtTituloProcoloFaltasTipo3N10 = findViewById(R.id.idTxtProtocoloFaltasTipo3N10);

        ListCursos = findViewById(R.id.idSpinnerSeleCursos);
        ListEstudiantes = findViewById(R.id.idSpinnerSeleEstudiante);
        ListTipoDeFalta = findViewById(R.id.idSpinnerSeleFaltaTipoDeFalta);
        ListFaltas = findViewById(R.id.idSpinnerSeleFalta);
        peridos = findViewById(R.id.idSpinnerSelePeriodos);
        docenteRealizaFalta = findViewById(R.id.idSpinnerSeleDocenteRealizaFalta);
        spinnerTipoFaltasNumero2 = findViewById(R.id.idSpinnerSeleFaltaTipoDeFaltaNumero2);
        spinnerFaltaCometidaNumero2 = findViewById(R.id.idSpinnerSeleFaltaNumero2);
        spinnerTipoFaltasNumero3 = findViewById(R.id.idSpinnerSeleFaltaTipoDeFaltaNumero3);
        spinnerFaltaCometidaNumero3 = findViewById(R.id.idSpinnerSeleFaltaNumero3);
        spinnerVerFaltasProtocolo = findViewById(R.id.idSpinnerSeleFaltaTipoDeFaltaProtocolo);

        editCompromisoEstudiante = findViewById(R.id.editCompromisoEstudiante);

        reportarEstudiante = findViewById(R.id.btnReportarEstudiante);
        agregarSegundaFalta = findViewById(R.id.btnAgregarSegundaFalta);
        agregarTercerFalta = findViewById(R.id.btnAgregarTercerFalta);
        verHistorial = findViewById(R.id.btnRevisarHistorialEstudiante);
        cerrarHistorial = findViewById(R.id.btnCerrarHistorial);


        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        pd= new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance();
        historialEstudiante = findViewById(R.id.rcVerHistorialEstudiante);
        historialEstudiante.setHasFixedSize(true);
        historialEstudiante.setLayoutManager(new LinearLayoutManager(this));
        listDatos = new ArrayList<DataVerTodosLosReportes>();

        roles();
        llenarSpinerCursos();
        subimosFirestore();
        referenciar2Admin();
        referenciar2User();
        funcionesBtnNuevasFaltas();
        llenarSpinnerTipoDeFaltasNumero2();
        llenarSpinerTipoFaltasNumero3();
        llenarSpinnerTipoFaltasProtocolo();



    }

    private void roles() {

        FirebaseUser usuarioLogin = FirebaseAuth.getInstance().getCurrentUser();


        if (usuarioLogin.getEmail().equals(UsuarioAdmin)) {

            BottomNavigationView bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
            BottomNavigationView bottomNavigationViewUser = findViewById(R.id.bottomNavigationView);


            bottomNavigationViewAdmin.setVisibility(View.VISIBLE);
            bottomNavigationViewUser.setVisibility(View.GONE);



        }else {

            BottomNavigationView bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
            BottomNavigationView bottomNavigationViewUser = findViewById(R.id.bottomNavigationView);


            bottomNavigationViewAdmin.setVisibility(View.GONE);
            bottomNavigationViewUser.setVisibility(View.VISIBLE);

        }


    }

    private void llenarListaHistorial() {

        db.collection(estudianteSelect).orderBy("fecha", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaImgCorreo = document.getString("imgCorreo");
                                String cadenaNombreUser = document.getString("nombreUser");
                                String cadenaAnio = document.getString("año");
                                String cadenapersonaReportada = document.getString("personaReportada");
                                String cadenacursoSeleccionado = document.getString("cursoSeleccionado");
                                String cadenaPeriodo = document.getString("periodoAcademico");
                                String cadenaDocenteQRP = document.getString("docenteQRF");
                                String cadenafaltaCometidaN1 = document.getString("faltaCometidaNum1");
                                String cadenafaltaCometidaN2 = document.getString("faltaCometidaNum2");
                                String cadenafaltaCometidaN3 = document.getString("faltaCometidaNum3");
                                String cadenacompromisoEstudiante = document.getString("correctivosPedagogicos");




                                DataVerTodosLosReportes datos = new DataVerTodosLosReportes(cadenaImgCorreo,cadenaNombreUser, cadenaAnio,cadenacursoSeleccionado,cadenaPeriodo,cadenaDocenteQRP,cadenafaltaCometidaN1,cadenafaltaCometidaN2,cadenafaltaCometidaN3,cadenapersonaReportada,cadenacompromisoEstudiante);
                                listDatos.add(datos);

                                adaptadorEstudiantes = new AdaptadorVerReportes(ReportarEstudiante.this, listDatos);
                                historialEstudiante.setAdapter(adaptadorEstudiantes);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



    }

    private void llenarSpinnerTipoFaltasProtocolo() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, R.layout.styli_spiner);

        spinnerVerFaltasProtocolo.setAdapter(adapter);

        spinnerVerFaltasProtocolo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipoFaltaSelectProtocolo = adapterView.getItemAtPosition(i).toString();

                if (tipoFaltaSelectProtocolo.equals("Selecionar Tipo de Falta")) {

                    txtTituloProcoloFaltasTipo1N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N4.setVisibility(View.GONE);

                    txtTituloProcoloFaltasTipo2N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N6.setVisibility(View.GONE);

                    txtTituloProcoloFaltasTipo3N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N6.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N7.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N8.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N9.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N10.setVisibility(View.GONE);


                } else if (tipoFaltaSelectProtocolo.equals("Situaciones Tipo 1")){

                    txtTituloProcoloFaltasTipo1N3.setText(Html.fromHtml(getString(R.string.txtProtocolo2)));

                    txtTituloProcoloFaltasTipo1N1.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo1N2.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo1N3.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo1N4.setVisibility(View.VISIBLE);

                    txtTituloProcoloFaltasTipo2N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N6.setVisibility(View.GONE);

                    txtTituloProcoloFaltasTipo3N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N6.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N7.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N8.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N9.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N10.setVisibility(View.GONE);

                } else if (tipoFaltaSelectProtocolo.equals("Situaciones Tipo 2")){

                    txtTituloProcoloFaltasTipo2N1.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo2N2.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo2N3.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo2N4.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo2N5.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo2N6.setVisibility(View.VISIBLE);

                    txtTituloProcoloFaltasTipo1N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N4.setVisibility(View.GONE);

                    txtTituloProcoloFaltasTipo3N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N6.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N7.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N8.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N9.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo3N10.setVisibility(View.GONE);


                }else if (tipoFaltaSelectProtocolo.equals("Situaciones Tipo 3")){

                    txtTituloProcoloFaltasTipo3N1.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N2.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N3.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N4.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N5.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N6.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N7.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N8.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N9.setVisibility(View.VISIBLE);
                    txtTituloProcoloFaltasTipo3N10.setVisibility(View.VISIBLE);

                    txtTituloProcoloFaltasTipo2N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N4.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N5.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo2N6.setVisibility(View.GONE);

                    txtTituloProcoloFaltasTipo1N1.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N2.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N3.setVisibility(View.GONE);
                    txtTituloProcoloFaltasTipo1N4.setVisibility(View.GONE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerTipoFaltasNumero3() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, R.layout.styli_spiner);

        spinnerTipoFaltasNumero3.setAdapter(adapter);

        spinnerTipoFaltasNumero3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipoFaltaSelectNumero3 = adapterView.getItemAtPosition(i).toString();

                if (tipoFaltaSelectNumero3.equals("Selecionar Tipo de Falta")) {


                } else {

                    llenarSpinerFaltasCometidasNumero3();
                    txtFaltaCometidaNumero3.setVisibility(View.VISIBLE);
                    spinnerFaltaCometidaNumero3.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerFaltasCometidasNumero3() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerFaltasCometidas> faltasCometidas = new ArrayList<>();

        firebaseFirestore.collection(tipoFaltaSelectNumero3).orderBy("descripcion", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaDescri = document.getString("descripcion");


                                DataSpinnerFaltasCometidas datos = new DataSpinnerFaltasCometidas(cadenaDescri);
                                faltasCometidas.add(datos);

                                ArrayAdapter<DataSpinnerFaltasCometidas> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, faltasCometidas);
                                spinnerFaltaCometidaNumero3.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

        spinnerFaltaCometidaNumero3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSelectNumero3 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void llenarSpinnerTipoDeFaltasNumero2() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, R.layout.styli_spiner);

        spinnerTipoFaltasNumero2.setAdapter(adapter);

        spinnerTipoFaltasNumero2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipoFaltaSelectNumero2 = adapterView.getItemAtPosition(i).toString();

                if (tipoFaltaSelectNumero2.equals("Selecionar Tipo de Falta")) {


                } else {

                    llenarSpinerFaltasCometidasNumero2();
                    txtFaltaCometidaNumero2.setVisibility(View.VISIBLE);
                    spinnerFaltaCometidaNumero2.setVisibility(View.VISIBLE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerFaltasCometidasNumero2() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerFaltasCometidas> faltasCometidas = new ArrayList<>();

        firebaseFirestore.collection(tipoFaltaSelectNumero2).orderBy("descripcion", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaDescri = document.getString("descripcion");


                                DataSpinnerFaltasCometidas datos = new DataSpinnerFaltasCometidas(cadenaDescri);
                                faltasCometidas.add(datos);

                                ArrayAdapter<DataSpinnerFaltasCometidas> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, faltasCometidas);
                                spinnerFaltaCometidaNumero2.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

        spinnerFaltaCometidaNumero2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSelectNumero2 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void funcionesBtnNuevasFaltas() {

        agregarSegundaFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelecFaltaTipo.setVisibility(View.GONE);
                ListTipoDeFalta.setVisibility(View.GONE);
                agregarSegundaFalta.setVisibility(View.GONE);

                txtTipoFaltaNumero2.setVisibility(View.VISIBLE);
                spinnerTipoFaltasNumero2.setVisibility(View.VISIBLE);
                agregarTercerFalta.setVisibility(View.VISIBLE);

            }
        });

        agregarTercerFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtTipoFaltaNumero2.setVisibility(View.GONE);
                spinnerTipoFaltasNumero2.setVisibility(View.GONE);
                agregarTercerFalta.setVisibility(View.GONE);

                txtTipoFaltaNumero3.setVisibility(View.VISIBLE);
                spinnerTipoFaltasNumero3.setVisibility(View.VISIBLE);



            }
        });

        verHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                historialEstudiante.setVisibility(View.VISIBLE);
                cerrarHistorial.setVisibility(View.VISIBLE);
                verHistorial.setVisibility(View.GONE);

            }
        });

        cerrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                historialEstudiante.setVisibility(View.GONE);
                cerrarHistorial.setVisibility(View.GONE);
                verHistorial.setVisibility(View.VISIBLE);

            }
        });

    }


    private void llenarSpinerCursos() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Cursos, R.layout.styli_spiner);

        ListCursos.setAdapter(adapter);

        ListCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cursoSelect = adapterView.getItemAtPosition(i).toString();

                if (cursoSelect.equals("Selecionar Curso")){


                }else{
                    llenarSpinerEstudiante();
                    selectEstudiante.setVisibility(View.VISIBLE);
                    ListEstudiantes.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterPeri = ArrayAdapter.createFromResource(this,R.array.PeriodosAcademicos, R.layout.styli_spiner);

        peridos.setAdapter(adapterPeri);

        peridos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                periSelect = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void llenarSpinerEstudiante() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerEstudiante> estudiantes = new ArrayList<>();

        firebaseFirestore.collection(cursoSelect).orderBy("nombre", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombre = document.getString("nombre");


                                DataSpinnerEstudiante datos = new DataSpinnerEstudiante(cadenaNombre);
                                estudiantes.add(datos);

                                ArrayAdapter<DataSpinnerEstudiante> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, estudiantes);
                                ListEstudiantes.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

              ListEstudiantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                      estudianteSelect = adapterView.getItemAtPosition(i).toString();
                      llenarSpinerTipoFaltas();
                      llenarListaHistorial();
                      verHistorial.setVisibility(View.VISIBLE);
                      agregarSegundaFalta.setVisibility(View.VISIBLE);
                      SelecFaltaTipo.setVisibility(View.VISIBLE);
                      ListTipoDeFalta.setVisibility(View.VISIBLE);

                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> adapterView) {

                  }
              });


    }

    private void llenarSpinerTipoFaltas() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoFaltas, R.layout.styli_spiner);

        ListTipoDeFalta.setAdapter(adapter);

        ListTipoDeFalta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tipoFaltaSelect = adapterView.getItemAtPosition(i).toString();

                if (tipoFaltaSelect.equals("Selecionar Tipo de Falta")){


                }else {

                    llenarSpinerFaltasCometidas();
                    SelecFaltaCometida.setVisibility(View.VISIBLE);
                    ListFaltas.setVisibility(View.VISIBLE);

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerFaltasCometidas() {

        pd.setTitle("Cargando...");

        pd.show();


        List<DataSpinnerFaltasCometidas> faltasCometidas = new ArrayList<>();

        firebaseFirestore.collection(tipoFaltaSelect).orderBy("descripcion", Query.Direction.ASCENDING).get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            pd.dismiss();

                            for (QueryDocumentSnapshot document : task.getResult()) {



                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaDescri = document.getString("descripcion");


                                DataSpinnerFaltasCometidas datos = new DataSpinnerFaltasCometidas(cadenaDescri);
                                faltasCometidas.add(datos);

                                ArrayAdapter<DataSpinnerFaltasCometidas> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, faltasCometidas);
                                ListFaltas.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

        ListFaltas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                faltaSelect = adapterView.getItemAtPosition(i).toString();
                llenarSpinerDocenteRealizaFalta();
                TxtDocenteRealizaFalta.setVisibility(View.VISIBLE);
                docenteRealizaFalta.setVisibility(View.VISIBLE);

                faltaSelectNumero2 = "";
                faltaSelectNumero3 = "";



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void llenarSpinerDocenteRealizaFalta() {

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

                                ArrayAdapter<DataSliderDocentes> arrayAdapter = new ArrayAdapter<>(ReportarEstudiante.this, R.layout.styli_spiner, docentes);
                                docenteRealizaFalta.setAdapter(arrayAdapter);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }


                });

        docenteRealizaFalta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                docenteSelectFalta = adapterView.getItemAtPosition(i).toString();

                txtTitutloProtocolo.setVisibility(View.VISIBLE);
                txtSelectTipoFaltaProtocolo.setVisibility(View.VISIBLE);
                spinnerVerFaltasProtocolo.setVisibility(View.VISIBLE);
                editCompromisoEstudiante.setVisibility(View.VISIBLE);
                reportarEstudiante.setVisibility(View.VISIBLE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


    private void subimosFirestore() {

        reportarEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String periodoSeleccionado = periSelect.trim();
                String cursoSeleccionado = cursoSelect.trim();
                String faltaCometidaN1 = faltaSelect.trim();
                String faltaCometidaN2 = faltaSelectNumero2.trim();
                String faltaCometidaN3 = faltaSelectNumero3.trim();
                String docenteQueReporta = docenteSelectFalta.trim();
                String personaReportada = estudianteSelect.trim();
                String correctivosPedagogicos = editCompromisoEstudiante.getText().toString().trim();


                if (cursoSeleccionado.isEmpty() && faltaCometidaN1.isEmpty() && faltaCometidaN2.isEmpty() && faltaCometidaN3.isEmpty() && docenteQueReporta.isEmpty() && personaReportada.isEmpty() && correctivosPedagogicos.isEmpty() ){

                    pd.setTitle("Por favor ingresa todos los datos para poder continuar...");

                    pd.show();

                }else{

                    subimosDataFirestore(periodoSeleccionado,cursoSeleccionado,faltaCometidaN1,faltaCometidaN2,faltaCometidaN3,docenteQueReporta,personaReportada,correctivosPedagogicos);
                    subimosDataPersonal(periodoSeleccionado,cursoSeleccionado,faltaCometidaN1,faltaCometidaN2,faltaCometidaN3,docenteQueReporta,personaReportada,correctivosPedagogicos);
                    subimosDataHistorial(periodoSeleccionado,cursoSeleccionado,faltaCometidaN1,faltaCometidaN2,faltaCometidaN3,docenteQueReporta,personaReportada,correctivosPedagogicos);

                }

            }
        });


    }



    private void subimosDataFirestore(String periodoSeleccionado, String cursoSeleccionado, String faltaCometidaN1, String faltaCometidaN2, String faltaCometidaN3, String docenteQueReporta, String personaReportada, String correctivosPedagogicos) {

        String anio = data.getString("anioSeleccionadoEstudiante");
        String todo = "Todos";

        String MeloFirestore = periodoSeleccionado +" "+ anio + " " + todo;

        pd.setTitle("Reportando Estudiante...");

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

        map.put("periodoAcademico",periodoSeleccionado);
        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometidaNum1", faltaCometidaN1);
        map.put("faltaCometidaNum2", faltaCometidaN2);
        map.put("faltaCometidaNum3", faltaCometidaN3);
        map.put("docenteQRF", docenteQueReporta);
        map.put("personaReportada", personaReportada);
        map.put("correctivosPedagogicos", correctivosPedagogicos);


        firebaseFirestore.collection(MeloFirestore).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarEstudiante.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });



    }

    private void subimosDataPersonal(String periodoSeleccionado, String cursoSeleccionado, String faltaCometidaN1, String faltaCometidaN2, String faltaCometidaN3, String docenteQueReporta, String personaReportada, String correctivosPedagogicos) {

        String anio = data.getString("anioSeleccionadoEstudiante");

        pd.setTitle("Reportando Estudiante...");

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


        map.put("periodoAcademico",periodoSeleccionado);
        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometidaNum1", faltaCometidaN1);
        map.put("faltaCometidaNum2", faltaCometidaN2);
        map.put("faltaCometidaNum3", faltaCometidaN3);
        map.put("docenteQRF", docenteQueReporta);
        map.put("personaReportada", personaReportada);
        map.put("correctivosPedagogicos", correctivosPedagogicos);

        firebaseFirestore.collection(nombreUser).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarEstudiante.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void subimosDataHistorial(String periodoSeleccionado, String cursoSeleccionado, String faltaCometidaN1, String faltaCometidaN2, String faltaCometidaN3, String docenteQueReporta, String personaReportada, String correctivosPedagogicos) {

        String anio = data.getString("anioSeleccionadoEstudiante");

        pd.setTitle("Reportando Estudiante...");

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


        map.put("periodoAcademico",periodoSeleccionado);
        map.put("cursoSeleccionado", cursoSeleccionado);
        map.put("faltaCometidaNum1", faltaCometidaN1);
        map.put("faltaCometidaNum2", faltaCometidaN2);
        map.put("faltaCometidaNum3", faltaCometidaN3);
        map.put("docenteQRF", docenteQueReporta);
        map.put("personaReportada", personaReportada);
        map.put("correctivosPedagogicos", correctivosPedagogicos);

        firebaseFirestore.collection(personaReportada).add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Reporte Agregado Correctamente", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportarEstudiante.this, MenuReportes.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(ReportarEstudiante.this, "Error Al Reportar Estudiante", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void referenciar2Admin() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewAdmin);


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

    private void referenciar2User() {

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
                }

                return false;
            }
        });
    }

}