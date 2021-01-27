package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrearReporte extends AppCompatActivity {

    private TextView edtMotivo, edtDescripcion, fecha, lblNumRepo, edtEstadoReporte, numUsua, correo;
    private int contador=0;
    private String convertir, nombre, numUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reporte);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);


        fecha = (TextView) findViewById(R.id.lblFechaRepo);

        lblNumRepo = (TextView)findViewById(R.id.lblNumRepo);
        edtMotivo = (TextView)findViewById(R.id.edtMotivo);
        edtDescripcion = (TextView)findViewById(R.id.edtDescripcion);
        edtEstadoReporte = (TextView)findViewById(R.id.lblEstadoRepo);

        //numUsua = (TextView)findViewById(R.id.txtNumUsuario);

        //correo = (TextView)findViewById(R.id.txtNumUsuario);

        Bundle bundle = getIntent().getExtras();
        nombre = (bundle.getString("correo"));

        obtenerFecha();

        numUser();
        //consulta();

/*
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = System.currentTimeMillis();
                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = sdf.format(date);
                fecha.setText(dateString);
            }
        });

 */
    }

    public void agregar(View view)
    {
        consulta();
        try {

            Statement stm = conexionBD().createStatement();
            PreparedStatement pst = conexionBD().prepareStatement("insert into Reportes values(?,?,?,?,?,?)");

            pst.setString(1,lblNumRepo.getText().toString());
            pst.setString(2,numUsuario.toString());
            pst.setString(3,edtMotivo.getText().toString());
            pst.setString(4,edtDescripcion.getText().toString());
            pst.setString(5,fecha.getText().toString());
            pst.setString(6,edtEstadoReporte.getText().toString());




            pst.executeUpdate();
/*
            Intent inicio = new Intent(this, Inicio.class);
            startActivity(inicio);
*/
            Toast.makeText(getApplicationContext(),"Reporte Creado",Toast.LENGTH_LONG).show();
            //contador++;

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Ocurrió un error, verifique sus datos",Toast.LENGTH_LONG).show();
        }
    }


    public void consulta()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select * from Vecinos where nombre ='"+nombre.toString()+"'");


            if (rs.next()) {

                numUsuario = (rs.getString(1));
                /*
                apellidoP.setText(rs.getString(2));
                apellidoM.setText(rs.getString(3));
                */

/*
                Intent inicio = new Intent(this, Inicio.class);
                inicio.putExtra("correo",edtMail.getText().toString());
                startActivity(inicio);

 */
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Usuario no Encontrado",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public Connection conexionBD()
    {
        Connection cnn=null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            //cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.14;databaseName=HermandadBD;user=sa;password=123;");
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://sql5101.site4now.net;databaseName=DB_A6DB98_hermandad;user=DB_A6DB98_hermandad_admin;password=holacomoestas1;");
            //Toast.makeText(getApplicationContext(), "Conectado",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return  cnn;
    }

    public void obtenerFecha()
    {
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = sdf.format(date);
        fecha.setText(dateString);
    }

    public void numUser()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select COUNT(*) from Reportes");
            if (rs.next()) {
                convertir = rs.getString(1);
                contador = Integer.parseInt(convertir);
                contador = contador+1;
                lblNumRepo.setText(Integer.toString(contador));

            }
            else
            {
                Toast.makeText(getApplicationContext(),"No hay registros",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void Atras(View view)
    {
        Intent atras = new Intent(this, Reportes.class);
        startActivity(atras);
    }

}