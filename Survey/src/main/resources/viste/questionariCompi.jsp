<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Survey.io</title>
    <link rel = "icon" href ="../img/Survey_symbol.png" type = "image/x-icon"> 
   
</head>
<body>
    <div>
        <h4 class="display-4">Your Surveys</h4>
	    <c:forEach items="${compi}" var="comp">
            <div>
                <p>-----------------------------------------------</p>
                 <p>Id: ${comp.getID()}</p>
                 <c:if test = "${comp.getCompilatore() != null}">
                    <p>Author: ${comp.getCompilatore().getMail()}</p> 
                 </c:if>
                 <c:if test = "${comp.getCompilatore() == null}">
                    <p>Author: Unknown</p> 
                 </c:if>
                 <a href="visualizzaQuestionarioCompilato/${comp.getID()}" >Visualizza risposte</a>
                 <p>-----------------------------------------------</p>
            </div>
        </c:forEach>          
    </div>
    
</body>
</html>
