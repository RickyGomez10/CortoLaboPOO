/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class producto {
    private int id;
    private String nombre;
    
    private int codigo;
    private String tipo;
    private int cantidad;
    private double precio;
    private int disponibilidad;

    public producto(int id, String nombre, int codigo, String tipo, int cantidad, double precio, int disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

  
    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
