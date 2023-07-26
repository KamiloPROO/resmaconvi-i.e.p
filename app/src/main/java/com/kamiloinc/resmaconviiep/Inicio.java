package com.kamiloinc.resmaconviiep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

public class Inicio extends AppCompatActivity {

   Button faltas1, faltas2, faltas3;

   Button verRuta, cerrarRuta , verSituaciones,cerrarSituaciones, verDerechosEstudiante, cerrarDerechosEstudiante,verDerechosDocente, cerrarDerechosDocente;

   TextView tituRuta, tituRuta1, tituRuta2,tituRuta3,tituRuta4,tituRuta5,tituRuta6,tituRuta7,tituRuta8 ;

   TextView tituSituaciones, tituSituaciones1,tituSituaciones2,tituSituaciones3,tituSituaciones4,tituSituaciones5,tituSituaciones6,tituSituaciones7,tituSituaciones8 ;

   TextView tituDerechosEstudiante,tituDerechosEstudiante1,tituDerechosEstudiante2,tituDerechosEstudiante3,tituDerechosEstudiante4  ;

    TextView tituDerechosDocente,tituDerechosDocente1,tituDerechosDocente2,tituDerechosDocente3,tituDerechosDocente4  ;

    LottieAnimationView lottieRuta, lottieRuta1, lottieRuta2, lottieRuta3, lottieRuta4, lottieSituaciones;

   Button derechosEstudiante,deberesEstudiante,derechosDocente,deberesDocente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        faltas1 = findViewById(R.id.btnSituaciones1);
        faltas2 = findViewById(R.id.btnSituaciones2);
        faltas3 = findViewById(R.id.btnSituaciones3);

        derechosEstudiante = findViewById(R.id.btnVerDerechosEstudiantes);
        deberesEstudiante = findViewById(R.id.btnVerDeberesEstudiantes);
        derechosDocente = findViewById(R.id.btnVerDerechosDocentes);
        deberesDocente = findViewById(R.id.btnVerDeberesDocentes);

        //Ruta de Aprendizaje

        verRuta = findViewById(R.id.idBtnVerRutaManual);
        cerrarRuta = findViewById(R.id.idBtnCerrarRutaAprendizaje);

        tituRuta = findViewById(R.id.idTxtTituloRutaAprendizaje);
        tituRuta1 = findViewById(R.id.idTxtTituloRutaAprendizaje1);
        tituRuta2 = findViewById(R.id.idTxtTituloRutaAprendizaje2);
        tituRuta3 = findViewById(R.id.idTxtTituloRutaAprendizaje3);
        tituRuta4 = findViewById(R.id.idTxtTituloRutaAprendizaje4);
        tituRuta5 = findViewById(R.id.idTxtTituloRutaAprendizaje5);
        tituRuta6 = findViewById(R.id.idTxtTituloRutaAprendizaje6);
        tituRuta7 = findViewById(R.id.idTxtTituloRutaAprendizaje7);
        tituRuta8 = findViewById(R.id.idTxtTituloRutaAprendizaje8);

        lottieRuta = findViewById(R.id.idLottieRutaAprendizaje);
        lottieRuta1 = findViewById(R.id.idLottieRutaAprendizaje1);
        lottieRuta2 = findViewById(R.id.idLottieRutaAprendizaje2);
        lottieRuta3 = findViewById(R.id.idLottieRutaAprendizaje3);
        lottieRuta4 = findViewById(R.id.idLottieRutaAprendizaje4);

        //Tipos de Faltas Manual de Convivencia

        verSituaciones = findViewById(R.id.idBtnVerSituacionesManual);
        cerrarSituaciones = findViewById(R.id.idBtnCerrarSituaciones);

        tituSituaciones = findViewById(R.id.idTxtTituloVerSituaciones);
        tituSituaciones1 = findViewById(R.id.idTxtTituloVerSituaciones1);
        tituSituaciones2= findViewById(R.id.idTxtTituloVerSituaciones2);
        tituSituaciones3 = findViewById(R.id.idTxtTituloVerSituaciones3);
        tituSituaciones4 = findViewById(R.id.idTxtTituloVerSituaciones4);
        tituSituaciones5 = findViewById(R.id.idTxtTituloVerSituaciones5);
        tituSituaciones6 = findViewById(R.id.idTxtTituloVerSituaciones6);
        tituSituaciones7 = findViewById(R.id.idTxtTituloVerSituaciones7);
        tituSituaciones8 = findViewById(R.id.idTxtTituloVerSituaciones8);

        lottieSituaciones = findViewById(R.id.idLottieSituaciones);

        //Derechos y deberes Estudiantes

        verDerechosEstudiante = findViewById(R.id.idBtnVerDerechosEstudiantes);
        cerrarDerechosEstudiante = findViewById(R.id.idBtnCerrarDerechosEstudiantes);

        tituDerechosEstudiante = findViewById(R.id.txtTituVerDerechosEstudiante);
        tituDerechosEstudiante1 = findViewById(R.id.txtTituVerDerechosEstudiante1);
        tituDerechosEstudiante2 = findViewById(R.id.txtTituVerDerechosEstudiante2);
        tituDerechosEstudiante3 = findViewById(R.id.txtTituVerDerechosEstudiante3);
        tituDerechosEstudiante4 = findViewById(R.id.txtTituVerDerechosEstudiante4);

        //Derechos y deberes Docentes

        verDerechosDocente = findViewById(R.id.idBtnVerDerechosDocentes);
        cerrarDerechosDocente = findViewById(R.id.idBtnCerrarDerechosDocentes);

        tituDerechosDocente = findViewById(R.id.txtTituVerDerechosDocentes);
        tituDerechosDocente1 = findViewById(R.id.txtTituVerDerechosDocentes1);
        tituDerechosDocente2 = findViewById(R.id.txtTituVerDerechosDocentes2);
        tituDerechosDocente3 = findViewById(R.id.txtTituVerDerechosDocentes3);
        tituDerechosDocente4 = findViewById(R.id.txtTituVerDerechosDocentes4);





        referenciar();
        referenciar2();
        funcionesBtnRuta();
        funcionesBtnSituaciones();
        funcionesBtnDerechosEstudiante();
        funcionesBtnDerechosDocentes();

    }

    private void funcionesBtnDerechosDocentes() {

        verDerechosDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verDerechosDocente.setVisibility(View.GONE);

                tituDerechosDocente.setVisibility(View.VISIBLE);
                tituDerechosDocente1.setVisibility(View.VISIBLE);
                tituDerechosDocente2.setVisibility(View.VISIBLE);
                tituDerechosDocente3.setVisibility(View.VISIBLE);
                tituDerechosDocente4.setVisibility(View.VISIBLE);

                derechosDocente.setVisibility(View.VISIBLE);
                deberesDocente.setVisibility(View.VISIBLE);

                cerrarDerechosDocente.setVisibility(View.VISIBLE);

            }
        });

        cerrarDerechosDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verDerechosDocente.setVisibility(View.VISIBLE);

                tituDerechosDocente.setVisibility(View.GONE);
                tituDerechosDocente1.setVisibility(View.GONE);
                tituDerechosDocente2.setVisibility(View.GONE);
                tituDerechosDocente3.setVisibility(View.GONE);
                tituDerechosDocente4.setVisibility(View.GONE);

                derechosDocente.setVisibility(View.GONE);
                deberesDocente.setVisibility(View.GONE);

                cerrarDerechosDocente.setVisibility(View.GONE);

            }
        });

    }

    private void funcionesBtnDerechosEstudiante() {

        verDerechosEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verDerechosEstudiante.setVisibility(View.GONE);

                tituDerechosEstudiante.setVisibility(View.VISIBLE);
                tituDerechosEstudiante1.setVisibility(View.VISIBLE);
                tituDerechosEstudiante2.setVisibility(View.VISIBLE);
                tituDerechosEstudiante3.setVisibility(View.VISIBLE);
                tituDerechosEstudiante4.setVisibility(View.VISIBLE);

                derechosEstudiante.setVisibility(View.VISIBLE);
                deberesEstudiante.setVisibility(View.VISIBLE);

                cerrarDerechosEstudiante.setVisibility(View.VISIBLE);

               // verDerechosDocente;
            }
        });

        cerrarDerechosEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verDerechosEstudiante.setVisibility(View.VISIBLE);

                tituDerechosEstudiante.setVisibility(View.GONE);
                tituDerechosEstudiante1.setVisibility(View.GONE);
                tituDerechosEstudiante2.setVisibility(View.GONE);
                tituDerechosEstudiante3.setVisibility(View.GONE);
                tituDerechosEstudiante4.setVisibility(View.GONE);

                derechosEstudiante.setVisibility(View.GONE);
                deberesEstudiante.setVisibility(View.GONE);

                cerrarDerechosEstudiante.setVisibility(View.GONE);



            }
        });
    }

    private void funcionesBtnSituaciones() {

        verSituaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verSituaciones.setVisibility(View.GONE);

                tituSituaciones.setVisibility(View.VISIBLE);
                tituSituaciones1.setVisibility(View.VISIBLE);
                tituSituaciones2.setVisibility(View.VISIBLE);
                tituSituaciones3.setVisibility(View.VISIBLE);
                tituSituaciones4.setVisibility(View.VISIBLE);
                tituSituaciones5.setVisibility(View.VISIBLE);
                tituSituaciones6.setVisibility(View.VISIBLE);
                tituSituaciones7.setVisibility(View.VISIBLE);
                tituSituaciones8.setVisibility(View.VISIBLE);

                lottieSituaciones.setVisibility(View.VISIBLE);

                cerrarSituaciones.setVisibility(View.VISIBLE);
                faltas1.setVisibility(View.VISIBLE);
                faltas2.setVisibility(View.VISIBLE);
                faltas3.setVisibility(View.VISIBLE);

            }
        });

        cerrarSituaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verSituaciones.setVisibility(View.VISIBLE);

                tituSituaciones.setVisibility(View.GONE);
                tituSituaciones1.setVisibility(View.GONE);
                tituSituaciones2.setVisibility(View.GONE);
                tituSituaciones3.setVisibility(View.GONE);
                tituSituaciones4.setVisibility(View.GONE);
                tituSituaciones5.setVisibility(View.GONE);
                tituSituaciones6.setVisibility(View.GONE);
                tituSituaciones7.setVisibility(View.GONE);
                tituSituaciones8.setVisibility(View.GONE);

                lottieSituaciones.setVisibility(View.GONE);

                cerrarSituaciones.setVisibility(View.GONE);
                faltas1.setVisibility(View.GONE);
                faltas2.setVisibility(View.GONE);
                faltas3.setVisibility(View.GONE);

            }
        });

    }

    private void funcionesBtnRuta() {

        verRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verRuta.setVisibility(View.GONE);

                tituRuta.setVisibility(View.VISIBLE);
                tituRuta1.setVisibility(View.VISIBLE);
                tituRuta2.setVisibility(View.VISIBLE);
                tituRuta3.setVisibility(View.VISIBLE);
                tituRuta4.setVisibility(View.VISIBLE);
                tituRuta5.setVisibility(View.VISIBLE);
                tituRuta6.setVisibility(View.VISIBLE);
                tituRuta7.setVisibility(View.VISIBLE);
                tituRuta8.setVisibility(View.VISIBLE);

                lottieRuta.setVisibility(View.VISIBLE);
                lottieRuta1.setVisibility(View.VISIBLE);
                lottieRuta2.setVisibility(View.VISIBLE);
                lottieRuta3.setVisibility(View.VISIBLE);
                lottieRuta4.setVisibility(View.VISIBLE);

                cerrarRuta.setVisibility(View.VISIBLE);

            }
        });

        cerrarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verRuta.setVisibility(View.VISIBLE);

                tituRuta.setVisibility(View.GONE);
                tituRuta1.setVisibility(View.GONE);
                tituRuta2.setVisibility(View.GONE);
                tituRuta3.setVisibility(View.GONE);
                tituRuta4.setVisibility(View.GONE);
                tituRuta5.setVisibility(View.GONE);
                tituRuta6.setVisibility(View.GONE);
                tituRuta7.setVisibility(View.GONE);
                tituRuta8.setVisibility(View.GONE);

                lottieRuta.setVisibility(View.GONE);
                lottieRuta1.setVisibility(View.GONE);
                lottieRuta2.setVisibility(View.GONE);
                lottieRuta3.setVisibility(View.GONE);
                lottieRuta4.setVisibility(View.GONE);

                cerrarRuta.setVisibility(View.GONE);

            }
        });

    }

    private void referenciar() {

        faltas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 1");
                startActivity(intent);

            }
        });

        faltas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 2");
                startActivity(intent);

            }
        });

        faltas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Inicio.this, VerTipoDeFaltas.class);
                intent.putExtra("tipoFalta","Situaciones Tipo 3");
                startActivity(intent);

            }
        });

        derechosEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, VerDeberesDerechos.class);
                intent.putExtra("tipoFalta","Derechos Estudiantes");
                startActivity(intent);
            }
        });

        deberesEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, VerDeberesDerechos.class);
                intent.putExtra("tipoFalta","Deberes Estudiantes");
                startActivity(intent);
            }
        });

        derechosDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, VerDeberesDerechos.class);
                intent.putExtra("tipoFalta","Derechos Docentes");
                startActivity(intent);
            }
        });

        deberesDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, VerDeberesDerechos.class);
                intent.putExtra("tipoFalta","Deberes Docentes");
                startActivity(intent);
            }
        });

    }



    private void referenciar2() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.inicio);

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
                    case R.id.reportar:
                        startActivity(new Intent(getApplicationContext()
                                , MenuReportes.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.inicio:
                        return true;
                }

                return false;
            }
        });
    }


}