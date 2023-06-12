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

import dao.PlayerDaoImpl;


@WebServlet("/players")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PlayerDaoImpl playerDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			playerDao=new PlayerDaoImpl();
		}catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	public void destroy() {
		try {
			playerDao.cleanUp();
		}catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			String team=request.getParameter("team");
			String name=request.getParameter("nm");
			String dob=request.getParameter("dob");
			String avg=request.getParameter("avg");
			String wicket=request.getParameter("wicket");
			
			boolean result=playerDao.validatePlayer(team, name, dob, avg, wicket, wicket); ///there is error i added wickect reapeat fix in future
			if(result)
			{
				pw.print("registration succsess");
				pw.print("<br/><br/><a href='add_players.html'>to add another playe</a>");
			}
			else
			{
				pw.print("failed");
				pw.print("<br/><br/><a href='add_players.html'>to add another player</a>");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
