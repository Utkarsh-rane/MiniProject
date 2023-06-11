package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.User;
import dao.UserDaoImpl;

/**
 * Servlet implementation class registrationServlet
 */
@WebServlet("/registrtion")
public class RegistrationServlet extends HttpServlet {
	private UserDaoImpl userDao;
	public void init() throws ServletException {
		try {
			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// re throw the exc to the caller (WC) , so that WC DOES NOT continue with the
			// rest of the servlet life cycle
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			// clean up dao
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			String email = request.getParameter("em");
			String pwd = request.getParameter("pass");
			String dob = request.getParameter("dob");
			
			boolean result=userDao.registeruser(firstName, lastName, email, pwd, dob);
			if(result)
			{
				pw.print("registration succsess");
				pw.print("<br/><br/><a href='login.html'>login</a>");
			}
			else
			{
				pw.print("failed");
				pw.print("<br/><br/><a href='login.html'>login</a>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException("err in do-post " + getClass(), e);

		}
	}

}
