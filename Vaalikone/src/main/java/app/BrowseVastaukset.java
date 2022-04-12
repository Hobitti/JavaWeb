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


@WebServlet("/vastaukset")
public class BrowseVastaukset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	
	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/javaweb", "root", "root");
	}

	
	public BrowseVastaukset() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Vastaukset> list = null;
	
		if (dao.getConnection()) {
			list = dao.readEhdokasVastaukset("2");
			System.out.println("Connection OK!");
			if(list==null) System.out.println("saatana");
		} else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("vastaus_list", list);
		request.setAttribute("dao", dao);
		
		

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/vastaukset.jsp");
		rd.forward(request, response);
	}
}
