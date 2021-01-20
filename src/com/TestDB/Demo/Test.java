package com.TestDB.Demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			selectExample();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void selectExample() throws Exception {
		// Boolean result = Boolean.FALSE;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder(
					"SELECT * FROM customer");

			pstm = conn.prepareStatement(sql.toString());
			//pstm.setString(1, number);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			
			
			while (rs.next()) {
				System.out.println(rs.getString("NAME"));
			}
			
			
			
			System.out.println("######## Campos ###############");
			for (int i = 1; i <= rsm.getColumnCount(); i++) {

				System.out.println(rsm.getColumnName(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (conn != null) {
				conn.close();

			}

		}

	}
}