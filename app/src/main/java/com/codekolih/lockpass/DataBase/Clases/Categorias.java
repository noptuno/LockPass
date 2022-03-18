package com.codekolih.lockpass.DataBase.Clases;

public class Categorias {

    Integer id_categoria;
    String nombre_categoria;
    String estado_categoria;
    String descripcion_categoria;
    String icono_categoria;

    public Categorias() {
    }

    public Categorias(String nombre_categoria, String estado_categoria, String descripcion_categoria) {
        this.nombre_categoria = nombre_categoria;
        this.estado_categoria = estado_categoria;
        this.descripcion_categoria = descripcion_categoria;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getEstado_categoria() {
        return estado_categoria;
    }

    public void setEstado_categoria(String estado_categoria) {
        this.estado_categoria = estado_categoria;
    }

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }

    public String toString() {
        return "Categorias{" +
                "id=" + id_categoria +
                "nombre=" + nombre_categoria +
                ", estado='" + estado_categoria + '\'' +
                ", descripcion='" + descripcion_categoria + '\'' +
                '}';
    }



}
