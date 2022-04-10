package app;
import data.Kysymys;
import dao.Dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveAnswer
 */
@WebServlet("/SaveAnswer")
public class SaveAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/javaweb", "root", "root");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        float total = 0;
        float average = 0;
        int count = 0;
        
        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            response.getWriter().print(paramName);
            response.getWriter().print("<br>");
            count++;
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
            	
                String paramValue = paramValues[i];
                if(paramValue != null) {
                	if(Integer.parseInt(paramValue) == 3) {
                		count--;
                	} else {
                		total += Integer.parseInt(paramValue);
                        response.getWriter().print(" " + paramValue);
                        response.getWriter().print("<br>");
                	}
                }
            }
        }
        
        average = total / count;
        
        response.getWriter().print("<br>");
        response.getWriter().print("Total: " + total);
        response.getWriter().print("<br>");
        response.getWriter().print("Average: " + average);
    }

}
