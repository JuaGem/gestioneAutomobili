<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<jsp:include page="../header.jsp" />
	
	<title>Risultati</title>
	
	<link href="./assets/css/global.css" rel="stylesheet">

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Marca</dt>
				  <dd class="col-sm-9">${visualizza_automobile_attribute.marca}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Modello:</dt>
				  <dd class="col-sm-9">${visualizza_automobile_attribute.modello}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cilindrata:</dt>
				  <dd class="col-sm-9">${visualizza_automobile_attribute.cilindrata}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data immatricolazione:</dt>
				  <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${visualizza_automobile_attribute.dataImmatricolazione}" />  		    	
				</dl>
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ListAutomobiliServlet" class='btn btn-outline-secondary' style='width:7em'>
		            <i class='fa fa-chevron-left'></i> Indietro
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />

</body>

</html>