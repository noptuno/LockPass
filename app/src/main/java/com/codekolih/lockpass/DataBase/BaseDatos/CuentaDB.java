package com.codekolih.lockpass.DataBase.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codekolih.lockpass.DataBase.Clases.Cuenta;
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

    private ContentValues clienteMapperContentValues(Cuenta cuenta) {
        ContentValues cv = new ContentValues();

        cv.put(ConstantsDB.CUE_IDCUENTA, cuenta.getId_cuenta());
        cv.put(ConstantsDB.CUE_ID_CATEGORIA, cuenta.getId_categoria());
        cv.put(ConstantsDB.CUE_NOMBRE_CUENTA, cuenta.getNombre_cuenta());
        cv.put(ConstantsDB.CUE_PASSWORD_CUENTA, cuenta.getPassword_cuenta());
        cv.put(ConstantsDB.CUE_FECHA_CUENTA, cuenta.getFecha_cuenta());
        cv.put(ConstantsDB.CUE_NOTA_CUENTA, cuenta.getNota_cuenta());
        cv.put(ConstantsDB.CUE_LINK_CUENTA, cuenta.getLink_cuenta());

        return cv;
    }

    public long insertarCuenta(Cuenta cuenta) {

            this.openWriteableDB();
            long rowID = db.insert(ConstantsDB.TABLA_CUENTA, null, clienteMapperContentValues(cuenta));
            this.closeDB();
            return rowID;
    }

    public ArrayList<Cuenta> loadCuenta() {

        ArrayList<Cuenta> list = new ArrayList<>();
        this.openReadableDB();
        String[] campos = new String[]{ConstantsDB.CUE_IDCUENTA,ConstantsDB.CUE_ID_CATEGORIA,ConstantsDB.CUE_NOMBRE_CUENTA, ConstantsDB.CUE_PASSWORD_CUENTA, ConstantsDB.CUE_FECHA_CUENTA,ConstantsDB.CUE_NOTA_CUENTA,ConstantsDB.CUE_LINK_CUENTA};
        Cursor c = db.query(ConstantsDB.TABLA_CUENTA, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId_cuenta(c.getInt(0));
                cuenta.setId_categoria(c.getString(1));
                cuenta.setNombre_cuenta(c.getString(2));
                cuenta.setPassword_cuenta(c.getString(3));
                cuenta.setFecha_cuenta(c.getString(4));
                cuenta.setNota_cuenta(c.getString(5));
                cuenta.setLink_cuenta(c.getString(6));
                list.add(cuenta);
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
