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
import data.Kysymys;

@WebServlet("/kysymykset")
public class ReadKysymys extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/javaweb", "root", "root");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadKysymys() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Kysymys> list = null;
		
		if (dao.getConnection()) {
			list = dao.readAllKysymykset();
			System.out.println("Connection OK!");
		} else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("kysymys_list", list);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/kysymykset.jsp");
		rd.forward(request, response);
	}
}
