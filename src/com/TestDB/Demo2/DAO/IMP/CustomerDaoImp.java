package com.TestDB.Demo2.DAO.IMP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.TestDB.Demo.ConexionDB;
import com.TestDB.Demo2.DAO.CustomerDAO;
import com.TestDB.Demo2.Model.Customer;

public class CustomerDaoImp implements CustomerDAO {
	String qury;

	@Override
	public Customer getCustomer(Long id) throws Exception {

		Customer result;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder("SELECT * FROM customer2 WHERE CUSTOMER_ID = " + id);

			pstm = conn.prepareStatement(sql.toString());
			// pstm.setString(1, number);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			rs.next();

			result = new Customer(rs.getLong("CUSTOMER_ID"), rs.getString("Name"), rs.getString("EMAIL"),
					rs.getString("GENDER"), rs.getInt("INCOME"), rs.getInt("AGE"), rs.getTimestamp("CREATED_DATE"));
			System.out.println(rs);

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
		return result;
	}

	@Override
	public List<Customer> getFilter(String Name) throws Exception {

		List<Customer> result = new ArrayList<Customer>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder("SELECT * FROM customer2");

			pstm = conn.prepareStatement(sql.toString());
			// pstm.setString(1, number);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {

				result.add(new Customer(rs.getLong("CUSTOMER_ID"), rs.getString("Name"), rs.getString("EMAIL"),
						rs.getString("GENDER"), rs.getInt("INCOME"), rs.getInt("AGE"),
						rs.getTimestamp("CREATED_DATE")));

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
		return result;
	}

	@Override
	public List<Customer> filterByAddressOrName(String Name) throws Exception {

		List<Customer> result = new ArrayList<Customer>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder(
					"SELECT * FROM customer2 where EMAIL like '" + Name + "' or name like '" + Name + "'");

			pstm = conn.prepareStatement(sql.toString());
			// pstm.setString(1, number);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {

				result.add(new Customer(rs.getLong("CUSTOMER_ID"), rs.getString("Name"), rs.getString("EMAIL"),
						rs.getString("GENDER"), rs.getInt("INCOME"), rs.getInt("AGE"),
						rs.getTimestamp("CREATED_DATE")));

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
		return result;
	}

	@Override
	public Boolean AddCustomer(Customer customer) throws Exception {

		Connection conn = null;
		PreparedStatement pstm = null;
		int rs;

		try {
			conn = ConexionDB.createConn();

			qury = "INSERT INTO `customer2`(`customer_id`, `name`, `email`, `gender`, `created_date`, `age`, `income`) VALUES ("
					+ customer.getId() + ",'" + customer.getFirstName() + "','" + customer.getEmail() + "','"
					+ customer.getGender() + "',?," + customer.getAge() + "," + customer.getIncome() + ")";

			System.out.println(qury);

			pstm = conn.prepareStatement(qury);
			pstm.setTimestamp(1, getTimestamp(customer.getBirthday()));

			rs = pstm.executeUpdate();

			if (pstm != null)
				pstm.close();
			if (conn != null) {
				conn.close();
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public Boolean DeleteCustomer(Customer customer) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		int rs;

		try {
			conn = ConexionDB.createConn();

			qury = "DELETE FROM `customer2` WHERE CUSTOMER_ID = " + customer.getId();

			StringBuilder sql = new StringBuilder(qury);

			pstm = conn.prepareStatement(sql.toString());
			// pstm.setString(1, number);

			rs = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {

			if (pstm != null)
				pstm.close();
			if (conn != null) {
				conn.close();
			}

		}
		return true;
	}

	@Override
	public Boolean UpdateCustomer(Customer customer) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		int rs;

		try {
			conn = ConexionDB.createConn();

			System.out.println(customer.toString());
			qury = "UPDATE `customer2` SET `NAME` = '" + customer.getFirstName() + "', `EMAIL` = '"
					+ customer.getEmail() + "',`GENDER` = '" + customer.getGender() + "'," + "`INCOME` = "
					+ customer.getIncome() + ",`AGE` = " + customer.getAge()
					+ ", `CREATED_DATE` = ? WHERE `CUSTOMER_ID` = " + customer.getId();

			StringBuilder sql = new StringBuilder(qury);
			System.out.println(sql.toString());			

			pstm = conn.prepareStatement(sql.toString());
			pstm.setTimestamp(1, getTimestamp(customer.getBirthday()));
			// pstm.setString(1, number);

			rs = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {

			if (pstm != null)
				pstm.close();
			if (conn != null) {
				conn.close();
			}

		}
		return true;
	}
	
	@Override
	public int countGenero(String gender) throws Exception {

		int result;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder("SELECT count(gender) FROM customer2 WHERE gender like ?");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, gender);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			rs.next();

			result = rs.getInt("count(gender)");
			System.out.println(rs);

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
		return result;
	}
	
	@Override
	public List<String> getColumn(String Name) throws Exception {

		List<String> result = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = ConexionDB.createConn();
			StringBuilder sql = new StringBuilder("SELECT created_date, count(?) as NumberOfPersons FROM `customer2` GROUP by created_date");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, Name);

			rs = pstm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {

				result.add(rs.getString(Name) +" "+rs.getInt("NumberOfPersons"));
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
		return result;
	}
	
	

	public Timestamp getTimestamp(Date date) {
		return date == null ? null : new java.sql.Timestamp(date.getTime());
	}
}
