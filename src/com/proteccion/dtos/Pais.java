package com.proteccion.dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pais implements Serializable {
	
	private String codigo;
	private String nombre;
	
	public Pais(){
		codigo="";
		nombre="";
	}
	
	
	public Pais(String codigo, String nombre){
		this.codigo=codigo;
		this.nombre=nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Pais [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	
}
