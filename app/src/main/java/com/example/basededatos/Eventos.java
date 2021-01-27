package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Eventos extends AppCompatActivity {

    ListView listViewPrueba;
    ArrayList<String> listaInfo;
    ArrayList<ClassListItemsEvents> listaRepo;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        listViewPrueba = (ListView) findViewById(R.id.listviewEventos);

        listaRepo = new ArrayList<ClassListItemsEvents>();

        Bundle bundle = getIntent().getExtras();
        correo = (bundle.getString("correo"));
        Toast.makeText(getApplicationContext(), "Pulse cualquier evento", Toast.LENGTH_LONG).show();

        consulta();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewPrueba.setAdapter(adaptador);


        listViewPrueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = listaRepo.get(position).getIdEvento();

                try
                {
                    String nombre, apellidoP, fecha;
                    Statement stm = conexionBD().createStatement();
                    ResultSet rs = stm.executeQuery("Select * from Eventos where idEvento='"+informacion.toString()+"'");
                    if(rs.next())
                    {
                        fecha = rs.getString(5);
                        nombre = rs.getString(2);
                        //estadoRepo = rs.getString(6);
                        Toast.makeText(getApplicationContext(),"Fecha: "+fecha.toString()+"\n" + "IdUsuario: "+ nombre.toString()
                                ,Toast.LENGTH_LONG).show();
                    }

                } catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });
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


    public void consulta() {
        try {
            Statement stm = conexionBD().createStatement();
            ClassListItemsEvents datos = null;
            listaRepo = new ArrayList<ClassListItemsEvents>();


            ResultSet rs = stm.executeQuery("Select * from Eventos");

            while(rs.next()) {
                datos = new ClassListItemsEvents();

                //datos.setidReporte(rs.getString(1));
                datos.setIdEvento(rs.getString(1));
                datos.setTipoEvento(rs.getString(3));
                datos.setDescripcionEvento(rs.getString(4));

                listaRepo.add(datos);


            }
            obtenerLista();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    private void obtenerLista() {

        listaInfo = new ArrayList<String>();


        for (int i = 0; i < listaRepo.size(); i++) {

            listaInfo.add(listaRepo.get(i).getIdEvento() + "           -            " + listaRepo.get(i).getTipoEvento()
                    + "           -            " + listaRepo.get(i).getDescripcionEvento());


        }
    }
    public void CrearEvent(View view)
    {
        Intent evento = new Intent(this, CrearEventos.class);
        evento.putExtra("correo",correo.toString());
        startActivity(evento);
    }


}