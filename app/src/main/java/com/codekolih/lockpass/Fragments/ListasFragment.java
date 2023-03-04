package com.codekolih.lockpass.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codekolih.lockpass.DataBase.BaseDatos.CuentaDB;
import com.codekolih.lockpass.DataBase.Clases.Cuentas;
import com.codekolih.lockpass.DataBase.ConstantsDB;
import com.codekolih.lockpass.Principal.RegistroCuentas;
import com.codekolih.lockpass.R;
import com.codekolih.lockpass.adapters.AdapterCuentas;

import java.util.ArrayList;
import java.util.List;

public class ListasFragment extends Fragment {

    private RecyclerView recyclerview;
    private List<Cuentas> listCuentas;
    private AdapterCuentas adapterCuentas;
    private CuentaDB db;
    private Context contexto;



    public ListasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            contexto = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Debe implementar la interfaz ListFragments en su Activity");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listas, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.Recycler_Cuentas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        adapterCuentas = new AdapterCuentas();
        recyclerview.setAdapter(adapterCuentas);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapterCuentas.setOnNoteSelectedListener(new AdapterCuentas.OnNoteSelectedListener() {
            @Override
            public void onClick(Cuentas cuenta) {

                Intent intent = new Intent(contexto, RegistroCuentas.class);
                intent.putExtra(ConstantsDB.CUENTA, cuenta);
                startActivity(intent);


            }
        });

        onFilterFuncionFragment();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void onFilterFuncionFragment() {


        try {

            db = new CuentaDB(contexto);
            listCuentas = db.loadCuenta();
            adapterCuentas.setNotes(listCuentas);
            adapterCuentas.notifyDataSetChanged();


        } catch (Exception e) {
            Log.e("error", "mensajed");
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void onFilterFuncionFragment(String newText) {

        final List<Cuentas> filteredModelList = filter(listCuentas, newText);
        adapterCuentas.setNotes(filteredModelList);
        adapterCuentas.notifyDataSetChanged();


    }

    private List<Cuentas> filter(List<Cuentas> lista, String query) {
        query = query.toLowerCase();
        final List<Cuentas> filteredModelList = new ArrayList<>();
        for (Cuentas model : lista) {
            final String text = model.getNombre_cuenta().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}