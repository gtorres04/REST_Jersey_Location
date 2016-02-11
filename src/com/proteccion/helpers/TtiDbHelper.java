package com.proteccion.helpers;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.pool.*;

public class TtiDbHelper
{
	private static OracleDataSource oracleDS = null;
	
	private static final String DATASOURCE_JNDI_NAME = "jdbc/PruebaLab";

	// Private constructor prevents instantiation
	private TtiDbHelper()
	{
		
	}

	/**
	 * Crea la fuente de datos y la retorna.
	 * 
	 * @return DataSource
	 */
	public static synchronized DataSource getDataSource()
	{
		
		if (oracleDS != null)
		{
			return oracleDS;
		}
		
		try
		{
			oracleDS = new OracleDataSource();
            oracleDS.setURL("jdbc:oracle:thin:@dbmed06:1521:dllo11g2");
            oracleDS.setUser("PROTECCION_TEST_FI");
            oracleDS.setPassword("PROTECCION_TEST_FI");
			System.out.println("Valor del ds 2-->["+oracleDS+"]");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oracleDS;
	}
}
