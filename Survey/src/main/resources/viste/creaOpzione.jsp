<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1>Crea opzione</h1>

<section class="container">
	<div class="portlet light bordered">
		<div class="portlet-body form">
				<form:form  method="POST" modelAttribute="newOpzione">
				<div class="form-body">
				
					<div class="form-group">
						<label for="testo">inserisci il testo dell'opzione</label>
						<form:input id="testo" path="testo" type="text" class="form-control" placeholder="inserisci il testo"/>  
					</div>
					
				</div>
				
				<hr class="line-form">
				
				<div class="form-actions">
					<input type="submit" id="btnAdd" class="btn btn-primary form-buttons" value = "aggiungiOpzione" />
					<input type="submit" id="btnAbort" class="btn btn-default form-buttons" value = "annulla" />
				</div>
			
				</form:form>
			</div>
	</div>
</section>