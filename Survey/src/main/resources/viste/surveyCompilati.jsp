<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<tiles:insertAttribute name="head" />
<body>
    <tiles:insertAttribute name="navBar"/>
    <div class="containeritem d-grid mx-auto">

    <h4 class="display-4">Your Compiled Surveys</h4>

	<c:forEach items="" var="quest">
        <div class="domande p-2 bg-light">
             <p>id: ${quest.getID()}</p>
             <p>Title: ${quest.nome}</p>
             <p>Author: ${quest.creatore.mail}</p>  
             <p>Categories: ${quest.categoria}</p> 
             <a class="btn btn-light trigger pulsanti-edit" href="" ><i class="fas fa-trash-alt"></i></a>
             <a class="btn btn-light trigger pulsanti-edit" data-target="#modQuest" data-toggle="modal"><i class="fas fa-edit"></i></a>
        </div>
    </c:forEach>    

    </div>
  <tiles:insertAttribute name="scripts"/>
</body>
</html>
