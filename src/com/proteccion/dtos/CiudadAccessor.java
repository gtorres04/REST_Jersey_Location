package com.proteccion.dtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.proteccion.helpers.TtiDbHelper;

public class CiudadAccessor {
	// SQL Prepared statements
	private static final String SELECT_ALL_ciudades = "SELECT * FROM ciudades WHERE codigo_departamento=? ORDER BY codigo, nombre";
	private static final String INSERT_ciudad = "INSERT INTO ciudades (codigo, nombre, codigo_departamento)";

	// SQL access variables
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet res = null;

	public CiudadAccessor() {

	}
	/**
	 * consulta las ciudades por el codigo del departamento recibido por parametro
	 * @param departamento, de tipo <b>Departamento</b>
	 * @return ArrayList<Ciudad>
	 */
	public ArrayList<Ciudad> getAllCiudadPorDepartamento(Departamento departamento) {
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		getConnection();
		try {
			ps = con.prepareStatement(SELECT_ALL_ciudades);
			ps.setString(1, departamento.getCodigo());
			res = ps.executeQuery();
			while (res.next()) {
				load(ciudades);
			}
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ciudades;
	}
	/**
	 * Carga un objeto de tipo Ciudad del 
	 * registro de la base de datos y lo agrega 
	 * a la lista recibida por parametro
	 * @param ciudades, listado de Ciudades de tipo <b>ArrayList<Ciudad></b>
	 * @throws SQLException
	 */
	private void load(ArrayList<Ciudad> ciudades) throws SQLException {
		Ciudad ciudad = new Ciudad();
		ciudad.setCodigo(res.getString("codigo"));
		ciudad.setNombre(res.getString("nombre"));
		ciudad.setCodigoDepartamento(res.getString("codigo_departamento"));
		ciudades.add(ciudad);
	}
	/**
	 * Inserta un objeto de tipo Ciudad en la base de datos
	 * @param ciudad
	 * @return TRUE si el registro se hizo con exito.
	 */
	public boolean insert(Ciudad ciudad) {
		getConnection();
		boolean exito = false, resultado;
		try {
			ps = con.prepareStatement(INSERT_ciudad);
			ps.setString(1, ciudad.getCodigo());
			ps.setString(2, ciudad.getNombre());
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
