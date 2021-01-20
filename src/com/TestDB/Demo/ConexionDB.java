package com.TestDB.Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	public static Connection createConn() throws SQLException, ClassNotFoundException {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// nombre del servidor
			String nombre_servidor = "localhost";
			// numero del puerto
			String numero_puerto = "3306";
			// SID
			String sid = "testtable";
			// URL "jdbc:oracle:thin:@nombreServidor:numeroPuerto:SID"
			String url = "jdbc:mysql://" + nombre_servidor + ":" + numero_puerto + "/" + sid + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			// Nombre usuario y password
			String usuario = "root";
			String password = "";

			// Se carga el driver JDBC

			return DriverManager.getConnection(url, usuario, password);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		}

	}
}