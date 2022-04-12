package app;


import java.io.IOException;
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
        
    	// muuttujien alustus
        int total = 0;
        float average = 0;
        int count = 0;
        
        Map<Integer, Float> topEhdokkaatAvg = null;
        ArrayList<Ehdokas> topEhdokkaat = new ArrayList<Ehdokas>();
        

        // k�yd��n k�ytt�j�n vastaukset l�pi enumeraattorilla
        // otetaan parametrien nimet formista saadusta POST-pyynn�st�

        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
 
        	// seuraava parametrin nimi muuttujaan
            String paramName = parameterNames.nextElement();
            
            // nostetaan counttia, jotta tiedet��n vastattujen kysymyksien m��r�
            count++;
 
            // otetaan parametrin arvot parametrin nimen mukaan
            String[] paramValues = request.getParameterValues(paramName);
            
            // k�yd��n parametrin arvot l�pi
            for (int i = 0; i < paramValues.length; i++) {
            	
            	// t�m�nhetkinen parametrin arvo muuttujaan
                String paramValue = paramValues[i];
                
                // jos vastaus on neutraali, poistetaan t�m� kierros countista
                if(Integer.parseInt(paramValue) == 0) {
                	count--;
                } 
                
                // jos parametrin arvo ei ole tyhj�, nostetaan arvojen yhteenlaskua
                if(paramValue != null) {
                	total += Integer.parseInt(paramValue);
                }
            }  
        }

        
        // jos ainakin yhteen kysymykseen on vastattu

        if(count != 0) {
        	
        	// lasketaan vastauksien arvojen keskiarvo
        	average = total / (float)count;
        	
        	// luetaan kannasta parhaat ehdokkaat keskiarvon mukaan
        	if (dao.getConnection()) {
            	topEhdokkaatAvg = dao.readBestEhdokkaat(average);
    			System.out.println("Connection OK!");
    		} else {
    			System.out.println("No connection to database");
    		}
            
        	// luetaan kannasta top ehdokkaiden tiedot listaan
            if(topEhdokkaatAvg != null) {
            	for (Map.Entry<Integer, Float> me : topEhdokkaatAvg.entrySet()) {
            		topEhdokkaat.add(dao.readEhdokas(Integer.toString(me.getKey())));	
               }
            }
            
            // lis�t��n lista parametriin
            request.setAttribute("top_ehdokkaat", topEhdokkaat);
            
        } else {
        	// kysymyksiin ei vastattu
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

        // l�hetet��n tiedot parhaat_ehdokkaat.jsp:lle
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/parhaat_ehdokkaat.jsp");
		rd.forward(request, response);
        

    }

}