package com.codekolih.lockpass.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codekolih.lockpass.DataBase.Clases.Cuentas;
import com.codekolih.lockpass.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCuentas extends RecyclerView.Adapter<AdapterCuentas.NoteViewHolder> {

    private List<Cuentas> notes;
    private OnNoteSelectedListener onNoteSelectedListener;
    private OnNoteDetailListener onDetailListener;

    public AdapterCuentas() {
        this.notes = new ArrayList<>();
    }

    public AdapterCuentas(List<Cuentas> notes) {
        this.notes = notes;
    }


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View elementoTitular = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note_cuentas, parent, false);

        return new NoteViewHolder(elementoTitular);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder view, int pos) {
        view.bind(notes.get(pos));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public List<Cuentas> getNotes() {
        return notes;
    }

    public void setNotes(List<Cuentas> notes) {
        this.notes = notes;
    }

    public void setFilter(List<Cuentas> notes){
        this.notes.addAll(notes);

    }


    public void setOnNoteSelectedListener(OnNoteSelectedListener onNoteSelectedListener) {
        this.onNoteSelectedListener = onNoteSelectedListener;
    }

    public void setOnDetailListener(OnNoteDetailListener onDetailListener) {
        this.onDetailListener = onDetailListener;
    }


    public interface OnNoteSelectedListener {
        void onClick(Cuentas note);
    }

    public interface OnNoteDetailListener {
        void onDetail(Cuentas note);
    }


    public Cuentas getposicionactual(int position) {
        return notes.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre_cuenta;
        public TextView password_cuenta;

        public NoteViewHolder(View item) {
            super(item);

            nombre_cuenta = (TextView) itemView.findViewById(R.id.txt_cue_nombre);
            password_cuenta = (TextView) itemView.findViewById(R.id.txt_cue_password);

        }

        public void bind(final Cuentas cuenta) {

            nombre_cuenta.setText(cuenta.getNombre_cuenta());
            password_cuenta.setText(cuenta.getPassword_cuenta());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteSelectedListener != null) {
                        onNoteSelectedListener.onClick(cuenta);
                    }
                }
            });
        }
    }
}
