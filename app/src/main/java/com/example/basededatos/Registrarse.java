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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registrarse extends AppCompatActivity {

    private TextView edtId,edtNom, edtApeP, edtApeM, edtCorr, edtNumC, edtContr, msgContrasena;
    private Button boton;


    private int contador=0;
    private String convertir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        boton = (Button)findViewById(R.id.btnRegistrarse);

        edtId = (TextView)findViewById(R.id.numUsuario);

        //edtId =(TextView)findViewById(R.id.edtNumHouse);
        //edtId = (TextView)findViewById(R.id.edtNumHouse);
        edtNom = (TextView)findViewById(R.id.edtName);
        edtApeP = (TextView)findViewById(R.id.edtLastNameP);
        edtApeM = (TextView)findViewById(R.id.edtLastNameM);
        edtCorr = (TextView)findViewById(R.id.edtMail);
        edtNumC = (TextView)findViewById(R.id.edtNumHouse);
        edtContr = (TextView)findViewById(R.id.edtPassword);

        numUser();

        msgContrasena = (TextView)findViewById(R.id.edtPassword);
        msgContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Máximo 6 Dígitos",Toast.LENGTH_LONG).show();
            }
        });

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
            //workstation id=BDHermandad.mssql.somee.com;packet size=4096;user id=Ivan15_SQLLogin_1;pwd=gucy98d7qb;data source=BDHermandad.mssql.somee.com;persist security info=False;initial catalog=BDHermandad
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return  cnn;
    }



    public void agregar(View view)
    {
        try {
            Statement stm = conexionBD().createStatement();
            PreparedStatement pst = conexionBD().prepareStatement("insert into Vecinos values(?,?,?,?,?,?,?)");


            //edtId.setText(contador);
            //pst.setString(1,edtId.getText().toString());
            //pst.setString(1,edtNumC.getText().toString());
            pst.setString(1,edtId.getText().toString());
            pst.setString(2,edtNom.getText().toString());

            pst.setString(3,edtApeP.getText().toString());
            pst.setString(4,edtApeM.getText().toString());
            pst.setString(5,edtCorr.getText().toString());
            pst.setString(6,edtNumC.getText().toString());
            pst.setString(7,edtContr.getText().toString());


            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"Registrado",Toast.LENGTH_LONG).show();
            //contador++;
            Intent inicio = new Intent(this, Inicio.class);
            inicio.putExtra("correo",edtCorr.getText().toString());
            startActivity(inicio);

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Ocurrió un error, verifique sus datos",Toast.LENGTH_LONG).show();
        }
    }

    public void numUser()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select COUNT(*) from vecinos");
            if (rs.next()) {
                convertir = rs.getString(1);
                contador = Integer.parseInt(convertir);
                contador = contador+1;
                edtId.setText(Integer.toString(contador));

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

    public void atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}