package com.codekolih.lockpass.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.R;

import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.ItemViewHolder> {

    private List<Categorias> list_Categoria;
    private List<Categorias> list_Orig_Categorias;
    private OnNoteSelectedListener onNoteSelectedListener;

//constructor clase Adapter
    public AdapterCategorias(List<Categorias> categorias) {
        this.list_Categoria = categorias;
        this.list_Orig_Categorias = categorias;
    }

    public void setOnNoteSelectedListener(OnNoteSelectedListener onNoteSelectedListener) {
        this.onNoteSelectedListener = onNoteSelectedListener;
    }

    public interface OnNoteSelectedListener {
        void onClick(Categorias note);
    }

    //funcion del adapter para setear la lista

    public void setFilter(List<Categorias> categorias){
        list_Categoria = new ArrayList<>();
        list_Categoria.addAll(categorias);
        notifyDataSetChanged();
    }


//constructores del holder Referenias llama desde la Clase Adapter
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder view, int position) {
        final Categorias model = list_Categoria.get(position);
        view.bind(model);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_categoria, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public int getItemCount() {
        return list_Categoria.size();
    }

//Clase Holder para asignar las referencias R
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView id_categoria;
        public TextView nombre_categoria;
        public TextView estado_categoria;
        public TextView descripcion_categoria;


        public ItemViewHolder(View itemView) {
            super(itemView);
           // itemView.setClickable(true);
            id_categoria = (TextView) itemView.findViewById(R.id.txt_cat_id);
            nombre_categoria = (TextView) itemView.findViewById(R.id.txt_cat_nombre);
            estado_categoria = (TextView) itemView.findViewById(R.id.txt_cat_estado);
            descripcion_categoria = (TextView) itemView.findViewById(R.id.txt_cat_descripcion);
        }

        public void bind(Categorias categoria) {

           id_categoria.setText(""+ categoria.getId_categoria());
           nombre_categoria.setText(categoria.getNombre_categoria());
           estado_categoria.setText(categoria.getEstado_categoria());
           descripcion_categoria.setText(categoria.getDescripcion_categoria());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteSelectedListener != null) {
                        onNoteSelectedListener.onClick(categoria);
                    }
                }
            });


        }

    }


}
