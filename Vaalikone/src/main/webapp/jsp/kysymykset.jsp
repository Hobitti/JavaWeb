<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Kysymys" %>   
    
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
    <script>
		$(document).on("click", "#q1_op1", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
			$.get("${pageContext.request.contextPath}/SaveAnswer", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
				$("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
			});
		});
	</script>
</head>
<body>
<header class="welcome">
	<h1>Tervetuloa vaalikoneeseen!</h1>
	<p>Vaalikoneessa vastaat kysymyksiin eri kategorioista, jonka jälkeen näät sinulle sopivimmat ehdokkaat.</p>
	<p>Kysymyksiin vastataan asteikolla 1 - 5, jotka vastaatat mielipidettä täysin eri mieltä - täysin samaa mieltä. Jos vastauksestaan ei ole varma voi kysymykseen vastata neutraalilla mielipiteellä.</p>
	<p>Jos et ole varma mitä kysymyksellä tarkoitetaan, paina info-painiketta (i) kysymyksen vieressä, jolloin kysymykseen tulee lisäselite.</p>
</header>
<div id="somediv"></div>
<section class="questions">
<%
ArrayList<Kysymys> qList = (ArrayList<Kysymys>)request.getAttribute("kysymys_list");

for (int i = 0; qList != null && i < qList.size(); i++) {
	Kysymys q = qList.get(i);
	out.println("<div class='question_card'>");
		out.println("<h2>" + q.getKysymys() + " <span></span></h2>");
		if(q.getSelite() != null) {
			out.println("<p>" + q.getSelite() + "</p>");
		}
		out.println("<form action='saveAnswer'>");
			out.println("<input type='radio' id='q" + q.getId() + "_op1' name='q" + q.getId() + "_answer' value='1'>");
		    out.println("<label for='q" + q.getId() + "_op1'>Täysin eri mieltä</label>");
		    		
		    out.println("<input type='radio' id='q" + q.getId() + "_op2' name='q" + q.getId() + "_answer' value='2'>");
		    out.println("<label for='q" + q.getId() + "_op2'>Täysin eri mieltä</label>");
		    		
		    out.println("<input type='radio' id='q" + q.getId() + "_op3' name='q" + q.getId() + "_answer' value='3'>");
		    out.println("<label for='q" + q.getId() + "_op3'>Täysin eri mieltä</label>");
		    		
		    out.println("<input type='radio' id='q" + q.getId() + "_op4' name='q" + q.getId() + "_answer' value='4'>");
		    out.println("<label for='q" + q.getId() + "_op4'>Täysin eri mieltä</label>");
		    		
		    out.println("<input type='radio' id='q" + q.getId() + "_op5' name='q" + q.getId() + "_answer' value='5'>");
		    out.println("<label for='q" + q.getId() + "_op5'>Täysin eri mieltä</label>");
		out.println("</form>");
	out.println("</div>");
	
}
%>

</section>
</body>
</html>