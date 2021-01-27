package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class CrearEventos extends AppCompatActivity implements View.OnClickListener {

    private TextView txtfecha, txthora, numEvento, tipoEvento, descripcionEvento; //idUsuario;
    private  int dia,mes,ano, hora, minutos;
    private int contador=0;
    private String convertir, fechaEvento, idUsuario;


    //private TextView prueba1, prueba2, prueba3, prueba4, prueba5;

    private String nombre, pepe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_eventos);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);


        txtfecha = (TextView) findViewById(R.id.edtFechaEvento);
        txthora = (TextView) findViewById(R.id.edtHoraEvento);

        numEvento = (TextView)findViewById(R.id.lblNumEvento);

        tipoEvento = (TextView)findViewById(R.id.edtTipoEvento);
        descripcionEvento = (TextView)findViewById(R.id.edtDescripcionEvento);

/*
        prueba1 = (TextView)findViewById(R.id.txtprueba1);
        prueba2 = (TextView)findViewById(R.id.txtprueba2);
        prueba3 = (TextView)findViewById(R.id.txtprueba3);
        prueba4 = (TextView)findViewById(R.id.txtprueba4);
        prueba5 = (TextView)findViewById(R.id.txtprueba5);
*/


        Bundle bundle = getIntent().getExtras();
        nombre = (bundle.getString("correo"));

        numUser();
        consulta();

        txtfecha.setOnClickListener(this);
        txthora.setOnClickListener(this);


        //Toast.makeText(getApplicationContext(),nombre.toString(),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if (v == txtfecha)
        {
            final Calendar c= Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            //dayOfMonth+"/"+(monthOfYear+1)+"/"+year)
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtfecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                }
            }
            ,ano,mes,dia);
            datePickerDialog.show();
        }
        if(v == txthora)
        {
            final Calendar c= Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txthora.setText(hourOfDay+ ":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }

    }

    public void SumarFecha()
    {
        fechaEvento = txtfecha.getText().toString() + " " + txthora.getText().toString();
        //Toast.makeText(getApplicationContext(),fechaEvento.toString(),Toast.LENGTH_LONG).show();
    }
/*
    public void asignacion(View view)
    {
        SumarFecha();
        prueba1.setText(numEvento.getText().toString());
        prueba2.setText(idUsuario.toString());
        prueba3.setText(tipoEvento.getText().toString());
        prueba4.setText(descripcionEvento.getText().toString());
        prueba5.setText(fechaEvento.toString());

    }
*/
    public void agregar(View view)
    {
        SumarFecha();
        try {
            Statement stm = conexionBD().createStatement();
            PreparedStatement pst = conexionBD().prepareStatement("insert into Eventos values(?,?,?,?,?)");


            pst.setString(1, numEvento.getText().toString());
            pst.setString(2, idUsuario.toString());
            pst.setString(3, tipoEvento.getText().toString());
            pst.setString(4, descripcionEvento.getText().toString());
            pst.setString(5, fechaEvento.toString());

            pst.executeUpdate();

            Toast.makeText(getApplicationContext(), "Evento Creado", Toast.LENGTH_LONG).show();



/*
            Intent inicio = new Intent(this, Inicio.class);
            startActivity(inicio);
*/
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

                    idUsuario = (rs.getString(1));
                /*
                apellidoP.setText(rs.getString(2));
                apellidoM.setText(rs.getString(3));
                */

                    //Toast.makeText(getApplicationContext(),idUsuario.toString(),Toast.LENGTH_LONG).show();
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

    public void numUser()
    {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("select COUNT(*) from Eventos");
            if (rs.next()) {
                convertir = rs.getString(1);
                contador = Integer.parseInt(convertir);
                contador = contador+1;
                numEvento.setText(Integer.toString(contador));
                //Toast.makeText(getApplicationContext(),numEvento.getText().toString(),Toast.LENGTH_LONG).show();
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

}