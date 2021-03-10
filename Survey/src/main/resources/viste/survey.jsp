<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="containeritem d-grid mx-auto">
	    
	<h1 class="display-4">Survey</h1>
	   
	<form action="creaQuestionario" method="get">
	     <p>Inserisci il nome del Questionario</p>
	          <input type="text" name="nome" class="form-control" placeholder="Example?"/>
	     <p>Inserisci la categoria</p>
	          <input type="text" name="categoria" class="form-control" placeholder="category1"/>
		 <input style="margin-left: 40%;" class="btn trigger " type="submit" value="Conferma" />
	</form>
</div>


