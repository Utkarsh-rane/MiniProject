package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDaoImpl;
import pojos.Candidate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin_page")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		try(PrintWriter pw=response.getWriter()) {
			pw.print("<h5>In admin page ....</h5>");
			AdminDaoImpl adminDao=new AdminDaoImpl();
			List<Candidate> toptwo=adminDao.toptwo();
			pw.print("<h3>Top two candidates ....</h5><table border=2px><tr><th>name</th><th>Party</th><th>votes</th></tr>");
			for(Candidate c: toptwo)
			{
				pw.write("<tr><td> "+c.getName()+"</td><td>"+c.getParty()+"</td><td>"+c.getVotes()+"</td></tr>");
			}
			pw.print("</table></h3>");
			
			List<Candidate> partylist=adminDao.votesAnalysis();
			pw.print("<h3> Voter Analisys ....</h5><table border=2px><tr><th>Party</th><th>votes</th></tr>");
			for(Candidate c: partylist)
			{
				pw.write("<tr><td>"+c.getParty()+"</td><td>"+c.getVotes()+"</td></tr>");
			}
			pw.print("</table></h3>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
