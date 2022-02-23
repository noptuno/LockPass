package com.codekolih.lockpass.DataBase;

public class ConstantsDB {
    //General
    public static final String DB_NAME = "CATEGORIA.db";
    public static final int DB_VERSION = 1;

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
}
