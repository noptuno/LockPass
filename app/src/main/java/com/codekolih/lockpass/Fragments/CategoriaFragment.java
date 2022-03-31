package com.codekolih.lockpass.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekolih.lockpass.DataBase.BaseDatos.CategoriaDB;
import com.codekolih.lockpass.DataBase.BaseDatos.CuentaDB;
import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.DataBase.Clases.Cuentas;
import com.codekolih.lockpass.R;
import com.codekolih.lockpass.adapters.AdapterCategorias;
import com.codekolih.lockpass.adapters.AdapterCuentas;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CategoriaFragment extends Fragment {



    public CategoriaFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_categoria, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



}