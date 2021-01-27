package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class MainActivity extends AppCompatActivity {
/*
    private TextView textView,edtNom,edtCar;
    private Button boton;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        boton = (Button)findViewById(R.id.btnSiguiente);

        edtNom = (TextView)findViewById(R.id.edtNombre);
        edtCar = (TextView)findViewById(R.id.edtCarrera);
        textView = (TextView)findViewById(R.id.textView);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });

 */
    }
    public void Iniciar_Sesion(View view)
    {
        Intent inicio = new Intent(this, IniciarSesion.class);
        startActivity(inicio);
    }
    public void Registro(View view)
    {
        Intent regist = new Intent(this, Registrarse.class);
        startActivity(regist);
    }


    /*

    public void sqlButton(View view)
    {
        if (connection !=null)
        {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from Personas");
                while (resultSet.next())
                {
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else
        {
            textView.setText("Connection is null");
        }

}
}*/


/*
    public Connection conexionBD()
    {
        Connection cnn=null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.13;databaseName=bdprueba;user=sa;password=123;");
            Toast.makeText(getApplicationContext(), "Conectado",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return  cnn;
    }
    /
 */
/*
    public void consulta()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select * from personas");

            if (rs.next()) {
                textView.setText(rs.getString(1));

            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    */
    /*
    public void agregar()
    {
        try {
            PreparedStatement pst = conexionBD().prepareStatement("insert into Personas values(?,?)");

            pst.setString(1,edtNom.getText().toString());
            pst.setString(2,edtCar.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Registro Agregado",Toast.LENGTH_LONG).show();

    }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    */

}


