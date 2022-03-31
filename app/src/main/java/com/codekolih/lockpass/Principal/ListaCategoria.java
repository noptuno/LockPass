package com.codekolih.lockpass.Principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codekolih.lockpass.DataBase.BaseDatos.CategoriaDB;
import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.R;
import com.codekolih.lockpass.adapters.AdapterCategorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaCategoria extends AppCompatActivity {

    private List<Categorias> listCategorias;
    private AdapterCategorias adapterCategorias;
    private CategoriaDB db;
    private Button list_cat_crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categoria);


        //**asignar referencias

        list_cat_crear = findViewById(R.id.btn_list_cat_guardar);
        list_cat_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaCategoria.this, RegistroCategoria.class);
                startActivity(intent);
            }
        });


        app_bar_configuracion();

        Cargar_list_categorias();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recicler_list_categoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterCategorias);


        adapterCategorias.setOnNoteSelectedListener(new AdapterCategorias.OnNoteSelectedListener(){
            @Override
            public void onClick(Categorias categoria) {

               // Toast.makeText(getApplicationContext(),categoria.toString(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra(RegistroCuentas.TIPO_CATEGORIA, categoria);
                setResult(RESULT_OK, intent);
                finish();

            }

        });



    }

    private void Cargar_list_categorias() {

            try {
                db = new CategoriaDB(this);
                listCategorias = db.loadCategoria();
                adapterCategorias = new AdapterCategorias(listCategorias);

            } catch (Exception e) {
                Log.e("error", "mensajed");
            }
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