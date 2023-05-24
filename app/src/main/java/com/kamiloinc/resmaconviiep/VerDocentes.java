package com.kamiloinc.resmaconviiep;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.kamiloinc.resmaconviiep.Model.DataDocentes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VerDocentes extends AppCompatActivity {


    List<DataDocentes> listDatos;
    AdaptadorDocentes adaptadorDocentes;
    FirebaseStorage storageRef;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    TextView txttoast;

    ImageView imgtoast;


    SwipeRefreshLayout refreshLayout2;

    int number = 0;

    int REQUEST_CODE = 200;

    FirebaseUser userAdmin = FirebaseAuth.getInstance().getCurrentUser();
    String admin = "stevenarb98@gmail.com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_docentes);

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.rcVerDocentes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listDatos = new ArrayList<DataDocentes>();

        referenciar2();
        llenarLista();
        //permisos();
    }

 /*   private void permisos() {

        int PermisoCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int PermisoAlmacenamiento = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (PermisoCamara == PackageManager.PERMISSION_GRANTED && PermisoAlmacenamiento == PackageManager.PERMISSION_GRANTED) {


        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

    }*/


    private void llenarLista() {

        db.collection("Docentes").orderBy("nombre", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String cadenaNombre = document.getString("nombre");
                                String cadenaNumDocumen = document.getString("numDocumento");
                                String cadenaCorreo = document.getString("correo");
                                String cadenaSede = document.getString("sede");
                                String cadenaCargo = document.getString("cargo");
                                String cadenanespecialidad = document.getString("especialidad");
                                String cadenaNivelEnseñanza = document.getString("nivelDeEnsenianza");
                                String cadenaNumTelefono = document.getString("numTelefono");


                                DataDocentes datos = new DataDocentes(cadenaNombre, cadenaNumDocumen, cadenaCorreo, cadenaCargo, cadenaSede, cadenanespecialidad, cadenaNivelEnseñanza, cadenaNumTelefono);
                                listDatos.add(datos);

                                adaptadorDocentes = new AdaptadorDocentes(VerDocentes.this, listDatos);
                                recyclerView.setAdapter(adaptadorDocentes);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void referenciar3() {
        txttoast = findViewById(R.id.toasttxt);
        imgtoast = findViewById(R.id.imgtoast);

       // refreshLayout2 = findViewById(R.id.refresh2);
        refreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onRefresh() {

                number++;
                Toast toast3 = new Toast(getApplicationContext());

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.lytLayout));

                TextView txtMsg = (TextView) layout.findViewById(R.id.toasttxt);
                txtMsg.setText("Actualizado");
                toast3.setDuration(Toast.LENGTH_SHORT);
                toast3.setView(layout);
                toast3.show();

                refreshLayout2.setRefreshing(false);

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
                    case R.id.admin:
                        return true;
                }

                return false;
            }
        });



    }
}