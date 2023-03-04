package com.codekolih.lockpass.Principal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.codekolih.lockpass.DataBase.BaseDatos.CuentaDB;
import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.DataBase.Clases.Cuentas;
import com.codekolih.lockpass.DataBase.ConstantsDB;
import com.codekolih.lockpass.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class RegistroCuentas extends AppCompatActivity {

    private final CuentaDB db = new CuentaDB(RegistroCuentas.this);
    private static final int REQUEST_PICK_FILE = 1; //for File browsing
    static final String TIPO_CATEGORIA = "tipo_categoria";
    private EditText edit_cue_nombre,edit_cue_password,edit_cue_usuario;
    private TextView txt_cue_categoria,txt_cue_expandir;
    private Button btn_guardar;
    private Categorias categoria;
    private Cuentas cuenta;
    private Boolean modificar = false;
    private String id_categoria;
    private  int id_cuentas;
    private ScrollView scrollviewregistro;
    private LinearLayout layout_expanded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuentas);

        Bundle extras = getIntent().getExtras();


        // *** configurar app_bar
        app_bar_configuracion();

        //**asignar referencias
        txt_cue_categoria = findViewById(R.id.text_cue_categoria);
        edit_cue_nombre= findViewById(R.id.edit_cue_nombre);
        edit_cue_password= findViewById(R.id.txt_cue_password);
        btn_guardar = findViewById(R.id.btn_cue_guardar);
        txt_cue_expandir = findViewById(R.id.text_cue_expandir);
        layout_expanded = findViewById(R.id.layout_cue_expander);
        edit_cue_usuario = findViewById(R.id.editt_cue_usuario);

        txt_cue_expandir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout_expanded.setVisibility(View.VISIBLE);
            }
        });


        scrollviewregistro = findViewById(R.id.scrollViewRegistro);

        scrollviewregistro.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollviewregistro.post(new Runnable() {
                    public void run() {
                        scrollviewregistro.scrollTo(0, scrollviewregistro.getBottom());
                    }
                });
            }
        });


        // *** recibir result activitys
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            if(intent != null) {

                                categoria = (Categorias) intent.getSerializableExtra(TIPO_CATEGORIA);
                                id_categoria = categoria.getId_categoria().toString();
                                txt_cue_categoria.setText(categoria.getNombre_categoria());

                                // Toast.makeText(getApplicationContext(),categoria.getNombre_categoria(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });


        //**Acciones Referencias

        txt_cue_categoria.setOnClickListener(view -> {

            mStartForResult.launch(new Intent(this, ListaCategoria.class));

        });


        btn_guardar.setOnClickListener(view -> {

            String temp_id_categoria = categoria.getId_categoria().toString();
            String temp_nombre = edit_cue_nombre.getText().toString();
            String temp_pass = edit_cue_password.getText().toString();
            String temp_usua = edit_cue_usuario.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
            Date date = new Date();
            String fecha_cuenta = dateFormat.format(date);


            if (!temp_nombre.isEmpty() && !temp_pass.isEmpty() && !temp_id_categoria.isEmpty() && !temp_usua.isEmpty()){

                String nota_cuenta = "NOTA EJEMPLO";
                String link_cuenta = "WWW.CUENTAS.COM.UY";
                Cuentas cuentas = new Cuentas(temp_id_categoria,temp_nombre,temp_pass,fecha_cuenta,nota_cuenta,link_cuenta,temp_usua);
                RegistrarCuenta(cuentas);

            }else{
                //faltaron datos
            }

        });


        if (extras!=null){
           cuenta = (Cuentas) extras.getSerializable(ConstantsDB.CUENTA);
           visualizar_cuenta_seleccionada();
        }

    }

    private void visualizar_cuenta_seleccionada() {

        modificar = true;
        id_cuentas = cuenta.getId_cuenta();
        btn_guardar.setText("Editar");

    }


    public void RegistrarCuenta(Cuentas cuentas) {

        try {
            db.insertarCuenta(cuentas);
            finish();

        } catch (Exception e) {
            printLog("Registro","Error Base datos");

        }
    }


    private void printLog(String tag, String msg){

        Log.e(tag, msg);

    }


    private void eliminarCuenta(){

        try {

            db.eliminarCuentas(id_cuentas);
            finish();

        } catch (Exception e) {
            printLog("Registro","Error Base datos");
        }

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

                    if (modificar){

                        eliminarCuenta();

                    }

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