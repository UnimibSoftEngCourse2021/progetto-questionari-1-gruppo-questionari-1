<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<div class="containeritem d-grid mx-auto">
    <h1 class="display-4">Your Questions</h1>

    <tiles:insertAttribute name="searchQuestion"/>
    <tiles:insertAttribute name="noElement"/>
    <c:forEach items="${listaDomande}" var="domanda">
    	<div class="domande p-2 bg-light">
    		<div style="max-width: 200px; max-height: 200px; margin: 10px;">
        		<img style="max-width: 100%; max-height: 100%;" src="../img/bg-ocean.jpg" alt="">
        	</div>
    	<p>${domanda.testo}</p>
    	<c:forEach items="${domanda.opzioni}" var="opzione">
    		<div>
    			<input type="radio" id=${domanda.id} name=${domanda.id}/><label>${opzione.descrizioneOpzione}</label><br/>
    		</div>
    	</c:forEach>

		</div>				
	</c:forEach>
    <tiles:insertAttribute name="newQuestionButton"/>
</div>
<tiles:insertAttribute name="popUpNewQuestion"/>