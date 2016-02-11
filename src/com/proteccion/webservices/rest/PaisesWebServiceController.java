package com.proteccion.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.proteccion.dtos.Pais;
import com.proteccion.dtos.PaisAccessor;
/**
 * 
 * @author gerlin.torres
 *
 */
@Path("getPais")
public class PaisesWebServiceController
{
	/**
	 * Consulta todos los paises y retorna en
	 * formato JSON un listado.
	 * @return Cadena en formato JSON con todos los paises.
	 */
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public String mostrarPaisJSON() {
    	System.out.println("Entro................");
    	PaisAccessor pa;
    	List<Pais> listaPaises;
    	Gson gson;
    	String cadenaJSonPais;
    	
    	pa=new PaisAccessor();
    	listaPaises=pa.getAllPais();
		gson=new Gson();
		cadenaJSonPais = gson.toJson(listaPaises);
		return cadenaJSonPais;
    }
    /**
	 * Consulta todos los paises y retorna en
	 * formato XML un listado.
	 * @return Cadena en formato XML con todos los paises.
	 */
    @GET //Indicamos que este método se ejecutará al recibir una petición por get
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML) //Indicamos que el tipo de salida es texto plano, XML, HTML o JSON
    public Pais mostrarPaisXML() {
		Pais pais = new Pais("COL","COLOMBIA");
		return pais;
    }
}