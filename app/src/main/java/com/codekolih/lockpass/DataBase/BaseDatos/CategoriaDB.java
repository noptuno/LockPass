package com.codekolih.lockpass.DataBase.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.DataBase.ConstantsDB;

import java.util.ArrayList;

public class CategoriaDB {
    private SQLiteDatabase db;
    private CategoriaDB.DBHelper dbHelper;

    public CategoriaDB(Context context) {
        dbHelper = new CategoriaDB.DBHelper(context);
    }


    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if(db!=null){
            db.close();
        }
    }

    private ContentValues clienteMapperContentValues(Categorias categoria) {
        ContentValues cv = new ContentValues();


        cv.put(ConstantsDB.CAT_IDCATEGORIA, categoria.getId_categoria());
        cv.put(ConstantsDB.CAT_NOMBRE_CATEGORIA, categoria.getNombre_categoria());
        cv.put(ConstantsDB.CAT_ESTADO_CATEGORIA, categoria.getEstado_categoria());
        cv.put(ConstantsDB.CAT_DESCRIPCION_CATEGORIA, categoria.getDescripcion_categoria());


        return cv;
    }

    public long insertarCuenta(Categorias categorias) {

            this.openWriteableDB();
            long rowID = db.insert(ConstantsDB.TABLA_CATEGORIAS, null, clienteMapperContentValues(categorias));
            this.closeDB();
            return rowID;
    }

    public ArrayList<Categorias> loadCategoria() {

        ArrayList<Categorias> list = new ArrayList<>();
        this.openReadableDB();
        String[] campos = new String[]{ConstantsDB.CAT_IDCATEGORIA,ConstantsDB.CAT_NOMBRE_CATEGORIA,ConstantsDB.CAT_ESTADO_CATEGORIA, ConstantsDB.CAT_DESCRIPCION_CATEGORIA};
        Cursor c = db.query(ConstantsDB.TABLA_CATEGORIAS, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                Categorias categoria = new Categorias();
                categoria.setId_categoria(c.getInt(0));
                categoria.setNombre_categoria(c.getString(1));
                categoria.setEstado_categoria(c.getString(2));
                categoria.setDescripcion_categoria(c.getString(3));
                list.add(categoria);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;


    }


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, ConstantsDB.DB_NAME, null, ConstantsDB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ConstantsDB.TABLA_CUENTA_SQL);
            db.execSQL(ConstantsDB.TABLA_CATEGORIAS_SQL);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        }
    }


}
