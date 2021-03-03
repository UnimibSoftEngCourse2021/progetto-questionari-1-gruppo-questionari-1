<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1>Crea domanda</h1>

<section class="container">
	<div class="portlet light bordered">
		<div class="portlet-body form">
				<form:form  method="POST" modelAttribute="newDomanda">
				<div class="form-body">
				
					<div class="form-group">
						<label for="testo">inserisci il testo della domanda</label>
						<form:input id="testo" path="testo" type="text" class="form-control" placeholder="inserisci il testo"/>  
					</div>
					
					<div class="form-group">
						<label for="immagine">inserisci il link dell'immagine</label>
						<form:input id="immagine" path="immagine" type="text" class="form-control" placeholder="inserisci il link dell'immagine"/> 	 
					</div>
					
					<div class="form-group">
						<label for="categoria">inserisci la categoria</label>
						<form:input id="categoria" path="categoria" type="text" class="form-control" placeholder="inserisci la categoria"/> 	 
					</div>
					
				</div>
				
				<hr class="line-form">
				
				<div class="form-actions">
					<input type="submit" id="btnAdd" class="btn btn-primary form-buttons" value = "aggiungiDomanda" />
					<input type="submit" id="btnAbort" class="btn btn-default form-buttons" value = "annulla" />
				</div>
			
				</form:form>
			</div>
	</div>
</section>