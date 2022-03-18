package com.codekolih.lockpass.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.R;
import com.codekolih.lockpass.adapters.AdapterCategorias;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CategoriaFragment extends Fragment {


    private RecyclerView recyclerview;
    private List<Categorias> listCategorias;
    private AdapterCategorias adapterCategorias;


    public CategoriaFragment() {

    }


    public interface ejecutarCategoria{

        public void actualizarItem();
    }

    public void onFilterFuncionFragment(String newText) {

        final List<Categorias> filteredModelList = filter(listCategorias, newText);
        adapterCategorias.setFilter(filteredModelList);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categoria, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.Recycler_Categorias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listCategorias = new ArrayList<>();

        listCategorias.add(new Categorias("nombre1","estado1","descripcion1"));
        listCategorias.add(new Categorias("nombre2","estado2","descripcion2"));
        listCategorias.add(new Categorias("nombre3","estado3","descripcion3"));
        listCategorias.add(new Categorias("nombre4","estado4","descripcion4"));

        adapterCategorias = new AdapterCategorias(listCategorias);


        recyclerview.setAdapter(adapterCategorias);


    }






    private List<Categorias> filter(List<Categorias> lista, String query) {
        query = query.toLowerCase();

        final List<Categorias> filteredModelList = new ArrayList<>();
        for (Categorias model : lista) {
            final String text = model.getNombre_categoria().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}