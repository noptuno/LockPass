package com.codekolih.lockpass.DataBase.Clases;

public class Cuenta {

    Integer id_cuenta;
    Integer id_categoria;
    String nombre_cuenta;
    String password_cuenta;
    String fecha_cuenta;
    String nota_cuenta;
    String link_cuenta;

    public Cuenta() {
    }

    public Cuenta(Integer id_cuenta, Integer id_categoria, String nombre_cuenta, String password_cuenta, String fecha_cuenta, String nota_cuenta, String link_cuenta) {
        this.id_cuenta = id_cuenta;
        this.id_categoria = id_categoria;
        this.nombre_cuenta = nombre_cuenta;
        this.password_cuenta = password_cuenta;
        this.fecha_cuenta = fecha_cuenta;
        this.nota_cuenta = nota_cuenta;
        this.link_cuenta = link_cuenta;
    }

    public Integer getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Integer id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public String getPassword_cuenta() {
        return password_cuenta;
    }

    public void setPassword_cuenta(String password_cuenta) {
        this.password_cuenta = password_cuenta;
    }

    public String getFecha_cuenta() {
        return fecha_cuenta;
    }

    public void setFecha_cuenta(String fecha_cuenta) {
        this.fecha_cuenta = fecha_cuenta;
    }

    public String getNota_cuenta() {
        return nota_cuenta;
    }

    public void setNota_cuenta(String nota_cuenta) {
        this.nota_cuenta = nota_cuenta;
    }

    public String getLink_cuenta() {
        return link_cuenta;
    }

    public void setLink_cuenta(String link_cuenta) {
        this.link_cuenta = link_cuenta;
    }
}
