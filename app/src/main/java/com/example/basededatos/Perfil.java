package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Perfil extends AppCompatActivity {

    private TextView edtMail, edtPassw, reportes, nombreCompleto,numRepo, correo;
    //private Button boton;
    private  String name, nombre, apellidoP, apellidoM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        reportes = (TextView)findViewById(R.id.lblNombreCo);

        nombreCompleto = findViewById(R.id.lblAccount);

        correo = findViewById(R.id.lblCorreoElecU);



        Bundle bundle = getIntent().getExtras();
        name = (bundle.getString("correo"));
        consulta();

    }



    public void consulta()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select * from Vecinos where nombre ='"+name.toString()+"'");
            //ResultSet rs2 = stm.executeQuery("select count(*) from Reportes");

            if (rs.next()) {

                nombre = (rs.getString(2));
                apellidoP = (rs.getString(3));
                apellidoM = (rs.getString(4));
                correo.setText(rs.getString(5));

                nombreCompleto.setText(name+" "+apellidoP+" "+apellidoM);


                
                
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



}