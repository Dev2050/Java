package company_management.bean;

public class EmployeeBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1549250885233338087L;
	
	private String idemployee;
	private String name;
	private String surname;
	private String badge;
	private String fk_company;
	
	public String getIdemployee() {
		return idemployee;
	}
	public void setIdemployee(String idemployee) {
		this.idemployee = idemployee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getFk_company() {
		return fk_company;
	}
	public void setFk_company(String fk_company) {
		this.fk_company = fk_company;
	}
	
	
}
