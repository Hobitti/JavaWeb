package app;
import data.Ehdokas;
import data.Kysymys;
import data.Vastaukset;
import dao.Dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
        
        int total = 0;
        float average = 0;
        int count = 0;
        
        Map<Integer, Float> topEhdokkaatAvg = null;
        ArrayList<Ehdokas> topEhdokkaat = new ArrayList<Ehdokas>();
        
        // k‰yd‰‰n k‰ytt‰j‰n vastaukset l‰pi
        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            count++;
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
            	
                String paramValue = paramValues[i];
                if(Integer.parseInt(paramValue) == 0) {
                	count--;
                } 
                
                if(paramValue != null) {
                	total += Integer.parseInt(paramValue);
                }
            }  
        }
        
        if(count != 0) {
        	average = total / (float)count;
        	
        	if (dao.getConnection()) {
            	topEhdokkaatAvg = dao.readBestEhdokkaat(average);
    			System.out.println("Connection OK!");
    		} else {
    			System.out.println("No connection to database");
    		}
            
            if(topEhdokkaatAvg != null) {
            	for (Map.Entry<Integer, Float> me : topEhdokkaatAvg.entrySet()) {
            		topEhdokkaat.add(dao.readEhdokas(Integer.toString(me.getKey())));
               }
            }
            
            request.setAttribute("top_ehdokkaat", topEhdokkaat);
        } else {
        	request.setAttribute("top_ehdokkaat", null);
        }
        
        
//        Debugging messages
//        
//        response.getWriter().print("Count: " + count);
//        response.getWriter().print("<br>");
//        response.getWriter().print("<br>");
//        response.getWriter().print("Total: " + total);
//        response.getWriter().print("<br>");
//        response.getWriter().print("Average: " + average);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/parhaat_ehdokkaat.jsp");
		rd.forward(request, response);
        
    }

}
