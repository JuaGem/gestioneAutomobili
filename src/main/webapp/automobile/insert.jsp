<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<meta charset="ISO-8859-1">
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuova automobile</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" action="ExecuteInsertAutomobileServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Marca  </label>
								<input type="text" name="marca" id="marca" class="form-control" value="${automobileDaInserire.marca}" placeholder="Inserire la marca" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Modello </label>
								<input type="text" name="modello" id="modello" class="form-control" value="${automobileDaInserire.modello}"  placeholder="Inserire il modello" required>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>Cilindrata </label>
								<input type="number" class="form-control" name="cilindrata" id="cilindrata" value="${automobileDaInserire.cilindrata}" placeholder="Inserire la cilindrata" required>
							</div>
							<div class="form-group col-md-3">
								<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${automobileDaInserire.dataImmatricolazione}" var="dataImmParsed"/>
								<label>Data di Immatricolazione</label>
                        		<input class="form-control" id="dataImmatricolazione" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa" value="${dataImmParsed}" name="dataImmatricolazione" required>
							</div>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Inserisci</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>