package com.proteccion.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.proteccion.dtos.Ciudad;
import com.proteccion.dtos.CiudadAccessor;
import com.proteccion.dtos.Departamento;
import com.proteccion.dtos.DepartamentoAccessor;
import com.proteccion.dtos.Departamento;
import com.proteccion.dtos.DepartamentoAccessor;
/**
 * 
 * @author gerlin.torres
 *
 */
@Path("getCiudad")
public class CiudadesWebServiceController
{
	/**
	 * Consulta las ciudades del departamento recibido como parametro y retorno en
	 * formato JSON un listado.
	 * @param codigoDepartamento
	 * @return Cadena en formato JSON con las ciudades de un departamentos especificado
	 */
    @GET
    @Path("{codigoDepartamento}")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarCiudadesDelDepartamentoJSON(@PathParam("codigoDepartamento") String codigoDepartamento) {
    	CiudadAccessor ca;
    	String cadenaJSonDepartamentos;
    	Departamento departamento;
    	List<Ciudad> listaCiudades;
    	Gson gson;
    	ca=new CiudadAccessor();
    	departamento=new Departamento(codigoDepartamento,null,null);
    	listaCiudades=ca.getAllCiudadPorDepartamento(departamento);
		gson=new Gson();
		cadenaJSonDepartamentos = gson.toJson(listaCiudades);
		return cadenaJSonDepartamentos;
    }
    /**
	 * Consulta las ciudades del departamento recibido como parametro y retorno en
	 * formato XML un listado.
	 * @param codigoDepartamento
	 * @return Cadena en formato XML con las ciudades de un departamentos especificado
	 */
    @GET
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public Departamento mostrarDepartamentoXML() {
		Departamento Departamento = new Departamento("COL","COLOMBIA",null);
		return Departamento;
    }
}