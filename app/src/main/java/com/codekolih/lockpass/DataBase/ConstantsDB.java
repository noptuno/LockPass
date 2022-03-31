package com.codekolih.lockpass.DataBase;

public class ConstantsDB {
    //General
    public static final String DB_NAME = "CATEGORIA.db";
    public static final int DB_VERSION = 1;
    public static final String CUENTA = "CUENTA";
    //TABLA CATEGORIA
    public static final String TABLA_CATEGORIAS = "tabla_categoria";
    public static final String CAT_IDCATEGORIA = "_id_categoria";
    public static final String CAT_NOMBRE_CATEGORIA = "nombre_categoria";
    public static final String CAT_ESTADO_CATEGORIA = "estado_categoria";
    public static final String CAT_DESCRIPCION_CATEGORIA = "descripcion_categoria";

    public static final String TABLA_CATEGORIAS_SQL =
            "CREATE TABLE  " + TABLA_CATEGORIAS + "(" +
                    CAT_IDCATEGORIA+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CAT_NOMBRE_CATEGORIA+ " TEXT," +
                    CAT_ESTADO_CATEGORIA+ " TEXT," +
                    CAT_DESCRIPCION_CATEGORIA  + " TEXT);" ;

    //TABLA CUENTA
    public static final String TABLA_CUENTA = "tabla_cuenta";
    public static final String CUE_IDCUENTA= "_id_cuenta";
    public static final String CUE_ID_CATEGORIA= "id_categoria";
    public static final String CUE_NOMBRE_CUENTA= "nombre_cuenta";
    public static final String CUE_PASSWORD_CUENTA = "password_cuenta";
    public static final String CUE_FECHA_CUENTA = "fecha_cuenta";
    public static final String CUE_NOTA_CUENTA = "nota_cuenta";
    public static final String CUE_LINK_CUENTA = "link_cuenta";

    public static final String TABLA_CUENTA_SQL =
            "CREATE TABLE  " + TABLA_CUENTA + "(" +
                    CUE_IDCUENTA+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CUE_ID_CATEGORIA+ " TEXT," +
                    CUE_NOMBRE_CUENTA+ " TEXT," +
                    CUE_PASSWORD_CUENTA+ " TEXT," +
                    CUE_FECHA_CUENTA+ " TEXT," +
                    CUE_NOTA_CUENTA+ " TEXT," +
                    CUE_LINK_CUENTA  + " TEXT);" ;



}
