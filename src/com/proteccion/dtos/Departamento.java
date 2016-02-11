package com.proteccion.dtos;

public class Departamento {
	private String codigo;
	private String nombre;
	private String codigoPais;
	
	public Departamento() {
		super();
	}

	public Departamento(String codigo, String nombre, String codigoPais) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.codigoPais = codigoPais;
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

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	
}
