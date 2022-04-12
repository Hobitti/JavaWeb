package app;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Vastaukset;
import data.Kysymys;

/**
 * Servlet implementation class ShowFish
 */
@WebServlet("/editVastaus")
public class EditVastaus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/javaweb", "root", "root");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditVastaus() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vastaukset list = null;
		Kysymys kys=null;
		Integer.parseInt("23");
		
		if (dao.getConnection()) {
			list = dao.readVastaus("2");
			kys=dao.readKysymys(list.getKysymysId()+"");
			System.out.println("Connection OK!");
			
		} else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("vastaus_list", list);
		request.setAttribute("kysymys", kys);
		request.setAttribute("dao", dao);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/editVastaus.jsp");
		rd.forward(request, response);
	}
}