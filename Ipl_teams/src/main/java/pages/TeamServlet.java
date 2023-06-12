package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlayerDaoImpl;
import dao.TeamDaoImpl;

import pojos.Teams;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/add_player_form")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private TeamDaoImpl TeamDao;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			TeamDao=new TeamDaoImpl();
		}catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			TeamDao.cleanUp();
		}catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			List<Teams> teams =TeamDao.getteamDetails();
			pw.print("<from action='process_form'");
			pw.print("<h2>Add a player to IPL team</h2><br><br>");
			
			pw.print("<table style='background-color: lightgrey; '>");
			pw.print("<tr><td>Choose a team</td>");
			pw.print(" <td><select name='team_id'>");
			for (Teams te:teams)
				pw.print(" <option value='"+te.getTeam_id()+"'>"+te.getAbbrevation()+"</option>");
			pw.print(" </select></td ></tr>");
			
			pw.print("<tr> <td>Name</td>");
			pw.print(" <td><input type='text' name='nm'/></td></tr>");
			pw.print("<tr> <td>dob</td>");
			pw.print("<td><input type='date' name='dob'/></td> </tr>");
			pw.print("<tr> <td>Batting Average</td>");
			pw.print(" <td><input type='number' name='avg'/></td> </tr>");
			pw.print("<tr> <td>Wickets Taken</td>");
			pw.print(" <td><input type='number' name='wickets'/></td> </tr>");
			pw.print("</table>");
			pw.print("<h5><input type='submit' value='Add A Player'/></h5>");
		
			pw.print("</form>");
			
		} catch (SQLException e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}
		}

	}


