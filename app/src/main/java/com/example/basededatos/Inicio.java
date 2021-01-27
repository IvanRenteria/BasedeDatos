package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inicio extends AppCompatActivity {
    private TextView correo, contrasena, nombre, apellidoP, apellidoM;
    private String name, lastnameP,lastnameM;
    private Button btnLlamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);

        //correo = (TextView)findViewById(R.id.edtCorreo);
        //contrasena = (TextView)findViewById(R.id.edtContrasena);
        correo = (TextView)findViewById(R.id.lblNombreU);
        //apellidoP = (TextView)findViewById(R.id.lblApellidoPU);
        //apellidoM = (TextView)findViewById(R.id.lblApellidoMU);


        btnLlamar = (Button)findViewById(R.id.btnLlamarEmer);

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel: 911"));
                startActivity(i);
            }
        });

        //consulta();
        //datos();

        Bundle bundle = getIntent().getExtras();
        correo.setText(bundle.getString("correo"));
        consulta();


    }
/*
    public void datos()
    {
        Bundle bundle = getIntent().getExtras();
        nombre.setText(bundle.getString("correo"));
    }
*/
    public void consulta()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select * from Vecinos where correoElectronico ='"+correo.getText().toString()+"'");


            if (rs.next()) {

                name = (rs.getString(2));
                lastnameP = (rs.getString(3));
                lastnameM = (rs.getString(4));

                correo.setText(name+" "+lastnameP+" "+lastnameP);




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




    public void Reportes(View view)
    {
        Intent inicio = new Intent(this, Reportes.class);
        inicio.putExtra("correo",name.toString());
        startActivity(inicio);

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
    public void Perf(View view)
    {
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("correo",name.toString());
        startActivity(perfil);
    }
    public void Eventos(View view)
    {
        Intent event = new Intent(this, Eventos.class);
        event.putExtra("correo",name.toString());
        startActivity(event);
    }



    /*
    public void Iniciar_Sesion(View view)
    {
        Intent inicio = new Intent(this, IniciarSesion.class);
        startActivity(inicio);
    }
    public void Iniciar_Sesion(View view)
    {
        Intent inicio = new Intent(this, IniciarSesion.class);
        startActivity(inicio);
    }
*/
}