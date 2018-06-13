package company_management.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;

public class SaveMySQL {

	private static final String DB_DRIVER=
			"com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = 
			"jdbc:mysql://localhost:3306/company_management";
	private static final String DB_USER= "root";
	private static final String DB_PASSWORD= "root";
	
	public void insertCompany(CompanyBean company)
		throws SQLException{
		Statement stmt = null;
		Connection conn = null;
		try{
			conn=getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String insertCompany = "INSERT INTO COMPANY (idcompany, company_name, phone, email, date_ins)";
			insertCompany += "VALUES('"+company.getIdcompany()+"','"+company.getCompany()+"','"+company.getPhone()+"','"+company.getEmail()+"',"+" SYSDATE())";
			System.out.println("INSERT QUERY:"+insertCompany);
			stmt.executeUpdate(insertCompany);
			ArrayList<EmployeeBean> employees = company.getCompanyEmployees();
			for(EmployeeBean employee:employees){
				String insertEmployee="INSERT INTO EMPLOYEE (idemployee, surname, name, badge, FK_company, date_ins )";
				insertEmployee += "VALUES('" +employee.getIdemployee() +"','" 
				+employee.getSurname() +"', '"
				+employee.getName() +"', '"
				+employee.getBadge() +"', '"
				+employee.getFk_company() +"', " 
				+" SYSDATE())";
				System.out.println("INSERT EMPLOYEE: "+insertEmployee);
				stmt.executeUpdate(insertEmployee);
			}
			conn.commit();
		}catch(SQLException sqle){
			if(conn != null) conn.rollback();
			System.out.println("INSERT ERROR: transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode()+ " : "+ sqle.getMessage());
		}catch(Exception err){
			if(conn != null) conn.rollback();
			System.out.println("GENERIC ERROR: transaction is being rolled back");
			throw new SQLException(err.getMessage());
		}finally{
			if(stmt != null) stmt.close();
			if(stmt !=null) conn.close();
		}	
		}
	
	public ArrayList<CompanyBean> searchCompanies()
	throws SQLException,IOException {
		Statement stmt = null;
		Connection conn = null;
		try{
			conn = getDBConnection();
			stmt = conn.createStatement();
			String searchCompany = "SELECT * from COMPANY ";
			System.out.println("QUERY: "+searchCompany);
			ResultSet companyList = stmt.executeQuery(searchCompany);
			
			ArrayList<CompanyBean> companyListInDB = new ArrayList <CompanyBean> ();
			while(companyList.next()){
				CompanyBean company = new CompanyBean();
				String idCompany = companyList.getString("idcompany");
				String company_name = companyList.getString("company_name");
				company.setIdcompany(idCompany); //idcompany
				companyListInDB.add(company);
			}return companyListInDB;
		}catch(SQLException sqle){
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err){
			throw new SQLException(err.getMessage());
		}finally{
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		}
	//connecting 
	private static Connection getDBConnection() throws SQLException,Exception{
		System.out.println("----------MYSQL JDBC Connection----------");
		Connection dbConnection = null;
		try{
			Class.forName(DB_DRIVER);
		}catch(ClassNotFoundException e){
			System.out.println("ERROR: MySQL JDBC Driver not found");
			throw new Exception(e.getMessage());
		}try{
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			
		}catch(SQLException e){
			System.out.println("Connection to Company_Management database failed");
			throw new SQLException(e.getErrorCode() +" : "+e.getMessage());
		}
		return dbConnection;
	}
}
