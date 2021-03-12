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


<body>
    <div class="domande p-2 bg-light">
        <c:forEach items="${risp}" var="comp">
            <div class="domande p-2 bg-light">
                <p>-----------------------------------------------</p>
                <p> Testo : ${comp.getDomanda().getTesto()}</p>
                <p> Risposta : ${comp.getRisposta()}</p>
                <p>-----------------------------------------------</p>
            </div>
        </c:forEach>          
    </div>
    
  
</body>

</html>
