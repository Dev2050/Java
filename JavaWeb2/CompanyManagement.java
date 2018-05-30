package company_management.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;



/**
 * Servlet implementation class CompanyManagement
 */
@WebServlet("/CompanyManagement")
public class CompanyManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String whatsend=request.getParameter("whatsend");
		System.out.println("whatsend POST::" +whatsend);
		if(whatsend.equals("employee")){
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response);
		}
		else if(whatsend.equals("homepage")){
			response.sendRedirect("/company_management/formEmployee.jsp");
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String whatsend=request.getParameter("whatsend");
				System.out.println("Whatsend POST::" +whatsend);
				if(whatsend.equals("company")){
					String idcompany=request.getParameter("idcompany");
					System.out.println("idcompany ::" +idcompany);
					String company_name=request.getParameter("company_name");
					System.out.println("company_name ::" +company_name);
					String phone=request.getParameter("phone");
					System.out.println("phone ::" +phone);
					String email=request.getParameter("email");
					System.out.println("email ::" +email);
					
					CompanyBean company=new CompanyBean();
					ArrayList<EmployeeBean> companyEmployees=new ArrayList<EmployeeBean>();
					company.setIdcompany(idcompany);
					company.setCompany(company_name);
					company.setEmail(email);
					company.setPhone(phone);
					company.setCompanyEmployees(companyEmployees);
					
					request.getSession().removeAttribute("COMPANY");
					request.getSession().setAttribute("COMPANY", company);
					
					ServletContext sc = request.getSession().getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
					rd.forward(request, response);
					
				}
				
				
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
