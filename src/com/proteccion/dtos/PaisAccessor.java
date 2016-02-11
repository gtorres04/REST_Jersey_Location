package com.proteccion.dtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.proteccion.helpers.TtiDbHelper;

public class PaisAccessor {
	// SQL Prepared statements
	private static final String SELECT_ALL_PAISES = "SELECT * FROM paises ORDER BY codigo, nombre";
	private static final String INSERT_PAIS = "INSERT INTO paises (codigo, nombre)";

	// SQL access variables
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet res = null;

	public PaisAccessor() {

	}
	/**
	 * consulta todos los paises
	 * @return ArrayList<Ciudad>
	 */
	public ArrayList<Pais> getAllPais() {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		getConnection();
		try {
			ps = con.prepareStatement(SELECT_ALL_PAISES);
			res = ps.executeQuery();
			while (res.next()) {
				load(paises);
			}
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paises;
	}
	/**
	 * Carga un objeto de tipo Pais del 
	 * registro de la base de datos y lo agrega 
	 * a la lista recibida por parametro
	 * @param paises, listado de Paises de tipo <b>ArrayList<Pais></b>
	 * @throws SQLException
	 */
	private void load(ArrayList<Pais> paises) throws SQLException {
		Pais pais = new Pais();
		pais.setCodigo(res.getString("codigo"));
		pais.setNombre(res.getString("nombre"));
		paises.add(pais);
	}
	/**
	 * Inserta un objeto de tipo Pais en la base de datos
	 * @param pais
	 * @return TRUE si el registro se hizo con exito.
	 */
	public boolean insert(Pais Pais) {
		getConnection();
		boolean exito = false, resultado;
		try {
			ps = con.prepareStatement(INSERT_PAIS);
			ps.setString(1, Pais.getCodigo());
			ps.setString(2, Pais.getNombre());
			resultado = ps.execute();
			con.commit();
			closeDatabase();
			exito = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exito;
	}
	/**
	 * Obtiene la conección de la fuente de datos
	 */
	private void getConnection() {
		try {
			con = TtiDbHelper.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Cierra la transaccion y la conección a la base de datos.
	 */
	private void closeDatabase() {
		closeResultSet();
		closePreparedStatement();
		closeConnection();
	}
	/**
	 * Cierra la conección a la base de datos.
	 */
	private void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {

		}
	}

	private void closePreparedStatement() {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {

		}
	}

	private void closeResultSet() {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {

		}
	}
}
