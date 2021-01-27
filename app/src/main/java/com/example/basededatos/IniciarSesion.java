package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;

public class IniciarSesion extends AppCompatActivity {


    private TextView edtMail, edtPassw, nombre, apellidoP, apellidoM;
    private Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        boton = (Button) findViewById(R.id.btnIniciar);

        edtMail = (TextView) findViewById(R.id.edtCorreo);
        edtPassw = (TextView) findViewById(R.id.edtContrasena);


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




    public void consulta(View view)
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select * from Vecinos where correoElectronico ='"+edtMail.getText().toString()+"' and contraseña ='"+edtPassw.getText().toString()+"'");


            if (rs.next()) {

                edtMail.setText(rs.getString(5));
                /*
                apellidoP.setText(rs.getString(2));
                apellidoM.setText(rs.getString(3));
                */


                Intent inicio = new Intent(this, Inicio.class);
                inicio.putExtra("correo",edtMail.getText().toString());
                startActivity(inicio);
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


    public void Atras(View view)
    {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
    public void Recuperar(View view)
    {
        Intent recuperar = new Intent(this, RecuperarContrasena.class);
        startActivity(recuperar);
    }





    /*
    public void entrar(View view)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.host.host","smpt.google.com");
        properties.put("mail.smpt.socketFactory.port","465");
        properties.put("mail.smpt.socketFactory.class","javax.net.ssql.SQQLSocketFactory");
        properties.put("mail.smpt.auth","true");
        properties.put("mail.smtp.port","465");

        try
        {
            session = Session.getDefaultInstance()
        }
        catch ()
        {

        }
    }

*/

}
