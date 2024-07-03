package com.cibertec.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Empleado {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id_empleado;
	    private String nombre;
	    private String apellido;
	    private double salario;

	    @ManyToOne
	    @JoinColumn(name = "id_departamento")
	    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(int id_empleado,String nombre, String apellido, double salario, Departamento departamento) {
		this.id_empleado = id_empleado;
    	this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.departamento = departamento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
