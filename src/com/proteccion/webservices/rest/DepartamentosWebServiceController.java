package com.proteccion.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.proteccion.dtos.Departamento;
import com.proteccion.dtos.DepartamentoAccessor;
import com.proteccion.dtos.Pais;
import com.proteccion.dtos.PaisAccessor;
/**
 * 
 * @author gerlin.torres
 *
 */
@Path("getDepartamento")
public class DepartamentosWebServiceController
{
	/**
	 * Consulta los departamentos del pais recibido como parametro y retorna en
	 * formato JSON un listado.
	 * @param codigoPais
	 * @return Cadena en formato JSON con los departamentos de un pais especificado.
	 */
    @GET
    @Path("{codigoPais}")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarDepartamentosDelPaisJSON(@PathParam("codigoPais") String codigoPais) {
    	DepartamentoAccessor da;
    	Pais pais;
    	Gson gson;
    	String cadenaJSonDepartamentos;
    	List<Departamento> listaDepartamentos;
    	
    	da=new DepartamentoAccessor();
    	pais=new Pais(codigoPais,null);
    	listaDepartamentos=da.getAllDepartamentoPorPais(pais);
		gson=new Gson();
		cadenaJSonDepartamentos = gson.toJson(listaDepartamentos);
		return cadenaJSonDepartamentos;
    }
    /**
	 * Consulta los departamentos del pais recibido como parametro y retorna en
	 * formato XML un listado.
	 * @param codigoPais
	 * @return Cadena en formato XML con los departamentos de un pais especificado.
	 */
    @GET
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public Departamento mostrarDepartamentosXML() {
    	Departamento departamento = new Departamento("COL","COLOMBIA",null);
		return departamento;
    }
}