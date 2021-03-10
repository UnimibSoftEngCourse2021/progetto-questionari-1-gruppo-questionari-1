<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>





<div class="domande p-2 bg-light">
    
    
    <form action = "surveyToCompile", method= "GET">
        <p>${idQuestionario}</p>
        <p>${questionarioTrovato.nome}</p>
        <p>${questionarioTrovato.creatore.nome}</p>
        <p>${questionarioTrovato.categoria}</p>
        <input type="hidden" name = "id" value = "${idQuestionario}" />
        <input type="submit" class="btn btn-light trigger" value="Compila">
    </form> 

</div>