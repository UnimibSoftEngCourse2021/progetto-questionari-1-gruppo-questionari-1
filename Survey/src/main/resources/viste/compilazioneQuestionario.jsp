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
        <h1 class="display-4">${questionario.nome}</h1>
        <form action="compilaQuestionario" method="POST">
            <div class="domande p-2 bg-light">
                <c:forEach items="${questionario.domande}" var="domanda">
                    <div class="domande p-2 bg-light">
                    <div style="max-width: 200px; max-height: 200px; margin: 10px;">
                        <img style="max-width: 100%; max-height: 100%;" src="../img/bg-ocean.jpg" alt="">
                    </div>
                    <p>${domanda.testo}</p>
                    <c:if test = "${domanda.domandaChiusa == true}">
                        <div>
                            <c:forEach items="${domanda.opzioni}" var="opzione">
                                <div>
                                    <input type="radio" name = "${domanda.id}" value="${opzione.descrizioneOpzione}"/><label>${opzione.descrizioneOpzione}</label><br/>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test = "${domanda.domandaChiusa == false}">
                        <textarea rows="4" cols="50" name = "${domanda.id}">Inserisci risposta qui ..</textarea>
                    </c:if>
                </div>				
                </c:forEach>
            </div>
            <input type="hidden" name = "id" value = "${idQuestionario}" />
            <input style="margin-left: 40%;" class="btn trigger " type="submit" value="Conferma" />
        </form>

    </div>
    <tiles:insertAttribute name="scripts"/>
</body>
</html>
