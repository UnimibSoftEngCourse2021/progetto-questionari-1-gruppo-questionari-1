<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


	<c:forEach items="${listaQuestionari}" var="quest">
		<div class="domande p-2 bg-light">
	    <form action = "surveyToCompile" method= "GET">
	        <p>${quest.getID()}</p>
	        <p>${quest.nome}</p>
	        <p>${quest.creatore.nome}</p>
	        <p>${quest.categoria}</p>
	        <input type="hidden" name = "id" value = "${quest.getID()}" />
	        <input type="submit" class="btn btn-light trigger" value="Compila">
	    </form>
	    </div> 
	</c:forEach> 
