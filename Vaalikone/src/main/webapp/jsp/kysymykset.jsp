<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Kysymys" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kysymykset</title>
</head>
<body>
<h1>Vaalikone</h1>
<p>Vastaa kysymyksiin eri kategorioista ja saat lopussa sinulle sopivat ehdokkaat.</p>

<%
ArrayList<Kysymys> qList = (ArrayList<Kysymys>)request.getAttribute("kysymys_list");

for (int i = 0; qList != null && i < qList.size(); i++) {
	Kysymys q = qList.get(i);
	out.println("<h2>Kysymys " + (i + Integer.parseInt("1")) + "</h2>");
	out.println("<h3>" + q.getKysymys()+"</h3>");
	if(q.getSelite() != null) {
		out.println("<p>" + q.getSelite()+"</p>");
	}
	out.println("");
	
}
%>
</body>
</html>