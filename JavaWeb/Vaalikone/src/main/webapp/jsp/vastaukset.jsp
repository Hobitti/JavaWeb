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
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Vaalikone</title>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    
</head>
<body>
<div class="grid-container">
  <div class="grid-left"></div>
  <div class="grid-item">
  <%
ArrayList<Vastaukset> qList = (ArrayList<Vastaukset>)request.getAttribute("vastaus_list");

for (int i = 0; qList != null && i < qList.size(); i++) {
	Vastaukset q = qList.get(i);
	out.println(request.getParameter("id"));
	out.println(request.getParameter("mode"));
	out.println("<h2>" + q.getId() + " <span>"+ q.getPerustelu() +"</span></h2>");		
	out.println(" <p> <a href='./vastaukset?id="+q.getId() +"&mode=2' >Muokkaa kysymyksiä</a> <br>");
	out.println("<a href='./vastaukset?id="+q.getId() +"&mode=1' onclick='window.confirm()'>Poista kysymyksiä</a></p> ");

  
}

%>
		
		</div>
  <div class="grid-right"></div>  
</div>

</body>
</html>