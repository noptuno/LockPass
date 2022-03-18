package com.codekolih.lockpass.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codekolih.lockpass.DataBase.BaseDatos.CuentaDB;
import com.codekolih.lockpass.DataBase.Clases.Cuenta;
import com.codekolih.lockpass.R;

import java.util.ArrayList;
import java.util.Objects;

public class RegistroCuentas extends AppCompatActivity {

    private final CuentaDB db = new CuentaDB(RegistroCuentas.this);

    private EditText cue_nombre,cue_password;
    private TextView cue_categoria;
    private Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuentas);

        // *** configurar app_bar
        app_bar_configuracion();

        //**asignar referencias

        cue_categoria = findViewById(R.id.text_cue_categoria);
        cue_nombre= findViewById(R.id.edit_cue_nombre);
        cue_password= findViewById(R.id.edit_cue_password);
        btn_guardar = findViewById(R.id.btn_cue_guardar);


        //**Acciones Referencias

        ListenerReferencias();


    }

    private void ListenerReferencias() {

        cue_categoria.setOnClickListener(view -> {


            Intent intent = new Intent(RegistroCuentas.this, ListaCategoria.class);
            startActivity(intent);
        });

        btn_guardar.setOnClickListener(view -> {

            String temp_nombre = cue_nombre.getText().toString();
            String temp_pass = cue_password.getText().toString();

            if (temp_nombre.equals(" ") && !temp_pass.equals(" ")){

                String id_categoria = "1";
                String nombre_cuenta = cue_nombre.getText().toString();
                String password_cuenta = cue_password.getText().toString();
                String fecha_cuenta = "02/03/2022";
                String nota_cuenta = "NOTA EJEMPLO";
                String link_cuenta = "WWW.CUENTAS.COM.UY";

                Cuenta cuenta = new Cuenta(id_categoria,nombre_cuenta,password_cuenta,fecha_cuenta,nota_cuenta,link_cuenta);
                RegistrarCuenta(cuenta);

            }

        });

    }

    public void RegistrarCuenta(Cuenta cuenta) {

        try {
            db.insertarCuenta(cuenta);
            cargarLista();

        } catch (Exception e) {
            printLog("Registro","Error Base datos");

        }
    }

    private void cargarLista() {

        try {
            ArrayList<Cuenta> list = db.loadCuenta();
            for (Cuenta cuentas : list) {
                Log.i("---> BD Cuenta: ", cuentas.toString() );
            }

        } catch (Exception e) {
            Log.e("error", "mensaje");

        }

    }

    private void printLog(String tag, String msg){
        Log.e(tag, msg);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registro, menu);
        return true;
    }


    //averiguar que es esto SuppresLint
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_borrar:


                break;

            case R.id.action_categoria:

                Intent intent = new Intent(RegistroCuentas.this, ListaCategoria.class);
                startActivity(intent);

                break;


            case R.id.action_salir:

                    finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void app_bar_configuracion(){
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}