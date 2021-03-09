<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>






<div class="containeritem d-grid mx-auto">
    
    <h1 class="display-4">Your Questions</h1>
    <tiles:insertAttribute name="searchQuestion"/>

    <tiles:insertAttribute name="noElement"/>
    <tiles:insertAttribute name="domandaModificabile"/>

    
    
    <tiles:insertAttribute name="newQuestionButton"/>
    

</div>

<tiles:insertAttribute name="popUpNewQuestion"/>