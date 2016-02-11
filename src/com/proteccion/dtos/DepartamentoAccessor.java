package com.proteccion.dtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.proteccion.helpers.TtiDbHelper;

public class DepartamentoAccessor {
	// SQL Prepared statements
	private static final String SELECT_ALL_departamentos = "SELECT * FROM departamentos WHERE codigo_pais=? ORDER BY codigo, nombre";
	private static final String INSERT_Departamento = "INSERT INTO departamentos (codigo, nombre, codigo_Departamento)";

	// SQL access variables
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet res = null;

	public DepartamentoAccessor() {

	}
	/**
	 * consulta los departamentos por el codigo del pais recibido por parametro
	 * @param pais, de tipo <b>Pais</b>
	 * @return ArrayList<Departamento>
	 */
	public ArrayList<Departamento> getAllDepartamentoPorPais(Pais pais) {
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		getConnection();
		try {
			ps = con.prepareStatement(SELECT_ALL_departamentos);
			ps.setString(1, pais.getCodigo());
			res = ps.executeQuery();
			while (res.next()) {
				load(departamentos);
			}
			closeDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return departamentos;
	}
	/**
	 * Carga un objeto de tipo Departamento del 
	 * registro de la base de datos y lo agrega 
	 * a la lista recibida por parametro
	 * @param departamentos, listado de Departamentos de tipo <b>ArrayList<Departamento></b>
	 * @throws SQLException
	 */
	private void load(ArrayList<Departamento> departamentos) throws SQLException {
		Departamento departamento = new Departamento();
		departamento.setCodigo(res.getString("codigo"));
		departamento.setNombre(res.getString("nombre"));
		departamento.setCodigoPais(res.getString("codigo_pais"));
		departamentos.add(departamento);
	}
	/**
	 * Inserta un objeto de tipo Departamento en la base de datos
	 * @param departamento
	 * @return TRUE si el registro se hizo con exito.
	 */
	public boolean insert(Departamento Departamento) {
		getConnection();
		boolean exito = false, resultado;
		try {
			ps = con.prepareStatement(INSERT_Departamento);
			ps.setString(1, Departamento.getCodigo());
			ps.setString(2, Departamento.getNombre());
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
