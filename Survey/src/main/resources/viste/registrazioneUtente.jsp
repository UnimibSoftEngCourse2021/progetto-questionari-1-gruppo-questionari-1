<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1>Registrazione utente</h1>

<section class="container">
	<div class="portlet light bordered">
		<div class="portlet-body form">
				<form:form  method="POST" modelAttribute="newUtente">
				<div class="form-body">
				
					<div class="form-group">
						<label for="nome">inserisci il nome"</label>
						<form:input id="nome" path="nome" type="text" class="form-control" placeholder="inserisci il nome"/>  
					</div>
					
					<div class="form-group">
						<label for="cognome">inserisci il cognome</label>
						<form:input id="cognome" path="cognome" type="text" class="form-control" placeholder="inserisci il cognome"/> 	 
					</div>
					
					<div class="form-group">
						<label for="mail">inserisci l'email</label>
						<form:input id="mail" path="mail" type="text" class="form-control" placeholder="inserisci l'email"/> 	 
					</div>
					
					<div class="form-group">
						<label for="password">inserisci la password</label>
						<form:input id="password" path="password" type="text" class="form-control" placeholder="inserisci la password"/> 	 
					</div>
				</div>
				
				<hr class="line-form">
				
				<div class="form-actions">
					<input type="submit" id="btnAdd" class="btn btn-primary form-buttons" value = "registrati" />
					<input type="submit" id="btnAbort" class="btn btn-default form-buttons" value = "annulla" />
				</div>
			
				</form:form>
			</div>
	</div>
</section>