package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarContrasena extends AppCompatActivity {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;
    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        context = this;

        sub = findViewById(R.id.edtSubject);
        msg = findViewById(R.id.edtBody);
        reciep = findViewById(R.id.edtCorreoRecu);
        boton = (Button) findViewById(R.id.btnEnviar);


    }
}