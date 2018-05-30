package company_management.bean;

import java.util.ArrayList;

public class CompanyBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405457781559423830L;
	private String idcompany;
	private String company;
	private String phone;
	private String email;
	private ArrayList <EmployeeBean> companyEmployees;
	
	public ArrayList<EmployeeBean> getCompanyEmployees() {
		return companyEmployees;
	}
	public void setCompanyEmployees(ArrayList<EmployeeBean> companyEmployees) {
		this.companyEmployees = companyEmployees;
	}
	public String getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(String idcompany) {
		this.idcompany = idcompany;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
