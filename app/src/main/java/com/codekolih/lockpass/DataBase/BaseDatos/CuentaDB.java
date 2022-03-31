package com.codekolih.lockpass.DataBase.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codekolih.lockpass.DataBase.Clases.Categorias;
import com.codekolih.lockpass.DataBase.Clases.Cuentas;
import com.codekolih.lockpass.DataBase.ConstantsDB;


import java.util.ArrayList;

public class CuentaDB {
    private SQLiteDatabase db;
    private CuentaDB.DBHelper dbHelper;

    public CuentaDB(Context context) {
        dbHelper = new CuentaDB.DBHelper(context);
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

    private ContentValues clienteMapperContentValues(Cuentas cuentas) {
        ContentValues cv = new ContentValues();

        cv.put(ConstantsDB.CUE_IDCUENTA, cuentas.getId_cuenta());
        cv.put(ConstantsDB.CUE_ID_CATEGORIA, cuentas.getId_categoria());
        cv.put(ConstantsDB.CUE_NOMBRE_CUENTA, cuentas.getNombre_cuenta());
        cv.put(ConstantsDB.CUE_PASSWORD_CUENTA, cuentas.getPassword_cuenta());
        cv.put(ConstantsDB.CUE_FECHA_CUENTA, cuentas.getFecha_cuenta());
        cv.put(ConstantsDB.CUE_NOTA_CUENTA, cuentas.getNota_cuenta());
        cv.put(ConstantsDB.CUE_LINK_CUENTA, cuentas.getLink_cuenta());

        return cv;
    }

    public long insertarCuenta(Cuentas cuentas) {

            this.openWriteableDB();
            long rowID = db.insert(ConstantsDB.TABLA_CUENTA, null, clienteMapperContentValues(cuentas));
            this.closeDB();
            return rowID;
    }

    public void eliminarCuentas(int idcuentas) {
        this.openWriteableDB();
        String where = ConstantsDB.CUE_IDCUENTA + "= ?";
        db.delete(ConstantsDB.TABLA_CUENTA, where, new String[]{String.valueOf(idcuentas)});
        this.closeDB();
    }


    public ArrayList<Cuentas> loadCuenta() {

        ArrayList<Cuentas> list = new ArrayList<>();
        this.openReadableDB();
        String[] campos = new String[]{ConstantsDB.CUE_IDCUENTA,ConstantsDB.CUE_ID_CATEGORIA,ConstantsDB.CUE_NOMBRE_CUENTA, ConstantsDB.CUE_PASSWORD_CUENTA, ConstantsDB.CUE_FECHA_CUENTA,ConstantsDB.CUE_NOTA_CUENTA,ConstantsDB.CUE_LINK_CUENTA};
        Cursor c = db.query(ConstantsDB.TABLA_CUENTA, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                Cuentas cuentas = new Cuentas();
                cuentas.setId_cuenta(c.getInt(0));
                cuentas.setId_categoria(c.getString(1));
                cuentas.setNombre_cuenta(c.getString(2));
                cuentas.setPassword_cuenta(c.getString(3));
                cuentas.setFecha_cuenta(c.getString(4));
                cuentas.setNota_cuenta(c.getString(5));
                cuentas.setLink_cuenta(c.getString(6));
                list.add(cuentas);
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
