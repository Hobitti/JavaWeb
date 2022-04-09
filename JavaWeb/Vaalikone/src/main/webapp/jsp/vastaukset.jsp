<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Vastaukset" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vaalikone</title>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    
</head>
<body>
<div class="grid-container">
  <div class="grid-left">1</div>
  <div class="grid-item">
  <%
ArrayList<Vastaukset> qList = (ArrayList<Vastaukset>)request.getAttribute("vastaus_list");

for (int i = 0; qList != null && i < qList.size(); i++) {
	Vastaukset q = qList.get(i);
	out.println("<div class='question_card'>");
		out.println("<h2>" + q.getKysymysId() + " <span></span></h2>");
		
		out.println(" <p> <a href='./muokkaa_kysymyksia.html?id="+q.getId() +"' >Muokkaa kysymyksiä</a> <a href='./poista_kysymyksia.html?id="+q.getId() +"' onclick='window.confirm()'>Poista kysymyksiä</a></p> ");
		
  
}
%>
		
		</div>
  <div class="grid-right">9</div>  
</div>

</body>
</html>