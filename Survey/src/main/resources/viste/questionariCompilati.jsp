<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en" style="height: 100%;">

<tiles:insertAttribute name="head" />
<body>
    <!--view nuova che si occupa attraverso un foreach di stampare tutti i dati delle compilazioni con un bottone ogniuna che permette di vederne le risposte-->
    <tiles:insertAttribute name="navBar"/>
    <div class="containeritem d-grid mx-auto">
        <h4 class="display-4">Your Surveys</h4>
	    <c:forEach items="${compilazioni}" var="comp">
            <div class="domande p-2 bg-light">
                 <p>Id: ${comp.getID()}</p>
                 <c:if test = "${comp.getCompilatore() != null}">
                    <p>Author: ${comp.getCompilatore().getMail()}</p> 
                 </c:if>
                 <c:if test = "${comp.getCompilatore() == null}">
                    <p>Author: Unknown</p> 
                 </c:if>
                   
                 <!-- da fare funzione mapper nel controller che gestisce 'visualizzaQuestionarioCompilato/{id}' -->
                 <a class="btn btn-light trigger pulsanti-edit" href="visualizzaQuestionarioCompilato/${comp.getID()}" ><i class="fas fa-trash-alt"></i></a>
            </div>
        </c:forEach>          
    </div>
    
    <tiles:insertAttribute name="scripts"/>
</body>
</html>
