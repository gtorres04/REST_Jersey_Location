package com.proteccion.dtos;

public class Ciudad {
	private String codigo;
	private String nombre;
	private String codigoDepartamento;
	
	public Ciudad() {
		super();
	}

	public Ciudad(String codigo, String nombre, String codigoDepartamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.codigoDepartamento = codigoDepartamento;
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

	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	
	
	
}
