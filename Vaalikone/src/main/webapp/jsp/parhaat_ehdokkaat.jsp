<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Ehdokas" %>

<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Sopivimmat ehdokkaat</title>
</head>
<body>

<header class="welcome">
	<h1>Sinulle sopivimmat ehdokkaat</h1>
</header>

<section class="ehdokkaat flex-row">
<%
ArrayList<Ehdokas> eList = (ArrayList<Ehdokas>)request.getAttribute("top_ehdokkaat");

if(eList != null) {
	for (int i = 0; eList != null && i < eList.size(); i++) {
		Ehdokas e = eList.get(i);
		out.println("<div class='ehdokas-card'>");
			out.println("<h2>" + e.getNimi() + "</h2>");
			out.println("<h3>" + e.getPuolueS() + "</h3>");
			out.println("<h4>" + e.getKuntaS() + "</h4>");
			if(e.getSlogan() != null) {
				out.println("<p>" + e.getSlogan() + "</p>");
			}
			out.println("<p>" + e.getKuvaus() + "</p>");
		out.println("</div>");
		
	}
} else {
	out.println("<h2>Ehdokkaita ei voida suositella.</h2>");
}

%>
</section>
<a href="/kysymykset">Vastaa uudestaan</a>
</body>
</html>