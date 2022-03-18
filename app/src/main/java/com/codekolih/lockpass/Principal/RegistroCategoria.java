package com.codekolih.lockpass.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codekolih.lockpass.DataBase.BaseDatos.CategoriaDB;
import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.R;

import java.util.ArrayList;

public class RegistroCategoria extends AppCompatActivity {


    private EditText cat_nombre;
    private Button cat_guardar;
    private final CategoriaDB db = new CategoriaDB(RegistroCategoria.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_categoria);

        cat_nombre = findViewById(R.id.edit_cue_categoria);
        cat_guardar = findViewById(R.id.btn_cat_guardar);


        cat_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!cat_nombre.equals(" ")){


                    String nombre_categoria = cat_nombre.getText().toString();
                    String estado_categoria = "";
                    String descripcion_categoria = "";


                    Categorias cuenta = new Categorias(nombre_categoria,estado_categoria,descripcion_categoria);
                    RegistrarCategoria(cuenta);

                }


            }
        });
    }

    public boolean RegistrarCategoria(Categorias categoria) {

        try {

            db.insertarCuenta(categoria);
            cargarLista();
            return true;

        } catch (Exception e) {
            printLog("Registro","Error Base datos");
            return false;
        }
    }

    private void cargarLista() {

        try {
            ArrayList<Categorias> list = db.loadCategoria();
            for (Categorias categorias : list) {

                Log.i("---> BD Cuenta: ", categorias.toString() );

            }

            //  adapter.setNotes(list);
            // adapter.notifyDataSetChanged();

        } catch (Exception e) {
            Log.e("error", "mensaje");

        }

    }

    private void printLog(String tag, String msg){
        Log.e(tag, msg);
    }


}