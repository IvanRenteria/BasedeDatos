package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.StringSearch;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reportes extends AppCompatActivity {

    ListView listViewPrueba;
    ArrayList<String> listaInfo;
    ArrayList<ClassListItems> listaRepo;
    String correo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        listViewPrueba = (ListView) findViewById(R.id.listViewReportes);



        listaRepo = new ArrayList<ClassListItems>();
        Toast.makeText(getApplicationContext(), "Pulse cualquier reporte", Toast.LENGTH_LONG).show();

        consulta();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
            listViewPrueba.setAdapter(adaptador);


        listViewPrueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = listaRepo.get(position).getIdReporte();

                try
                {
                    String idUsario, fecha, estadoRepo;
                    Statement stm = conexionBD().createStatement();
                    ResultSet rs = stm.executeQuery("Select * from Reportes where idReporte='"+informacion.toString()+"'");
                    if(rs.next())
                    {
                        idUsario = rs.getString(2);
                        fecha = rs.getString(5);
                        estadoRepo = rs.getString(6);
                        Toast.makeText(getApplicationContext(),"idUsuario: "+idUsario.toString()+"\n" + "Fecha: "+ fecha.toString()
                                + "\n"+ "Estado Reporte: "+ estadoRepo.toString(),Toast.LENGTH_LONG).show();
                    }

                } catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });
        Bundle bundle = getIntent().getExtras();
        correo = (bundle.getString("correo"));

    }


    public void consulta() {
        try {
            Statement stm = conexionBD().createStatement();
            ClassListItems datos = null;

            listaRepo = new ArrayList<ClassListItems>();



            ResultSet rs = stm.executeQuery("Select * from Reportes");

            while(rs.next()) {
                datos = new ClassListItems();

                datos.setidReporte(rs.getString(1));

                //datos.setIdUsuario(rs.getString(2));

                datos.setMotivo(rs.getString(3));
                datos.setDescripcion(rs.getString(4));

                //datos.setFecha(rs.getString(5));
                //datos.setEstadoRepo(rs.getString(6));



                listaRepo.add(datos);


            }
            obtenerLista();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void obtenerLista() {

        listaInfo=new ArrayList<String>();


        for(int i=0; i<listaRepo.size();i++)
        {
            /*
            listaInfo.add(listaRepo.get(i).getIdReporte()+" - "+listaRepo.get(i).getMotivo()+" - "+
                    listaRepo.get(i).getDescripcion()+" - "+listaRepo.get(i).getFecha()+" - "+listaRepo.get(i).getEstadoRepo());


             */
            listaInfo.add(listaRepo.get(i).getIdReporte()+"       -       "+listaRepo.get(i).getMotivo()+"           -            "+listaRepo.get(i).getDescripcion());



        }

    }


    public Connection conexionBD() {
            Connection cnn = null;
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                //cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.14;databaseName=HermandadBD;user=sa;password=123;");
                cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://sql5101.site4now.net;databaseName=DB_A6DB98_hermandad;user=DB_A6DB98_hermandad_admin;password=holacomoestas1;");
                //Toast.makeText(getApplicationContext(), "Conectado",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return cnn;
        }

    public void CrearReportes(View view)
    {
        Intent inicio = new Intent(this, CrearReporte.class);
        inicio.putExtra("correo",correo.toString());
        startActivity(inicio);
    }
    public void atras(View view) {
        Intent atras = new Intent(this, Inicio.class);
        startActivity(atras);
    }

}


