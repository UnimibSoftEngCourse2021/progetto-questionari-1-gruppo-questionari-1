<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>






<div class="containerdomande d-grid mx-auto">
    <h1 class="display-4">Your Questions</h1>
    <tiles:insertAttribute name="searchQuestion"/>

    <div class="domande p-2 bg-light">

      <p>Il testo della domanda andra qua ? </p>
      <p>Le risposte della domanda andranno qui</p>
      <a class="btn btn-light trigger pulsanti-edit"><i class="fas fa-trash-alt"></i></a>
      <a class="btn btn-light trigger pulsanti-edit" ><i class="fas fa-edit"></i></a>

    </div>
    <div class="domande p-2 bg-light">

      <p>Il testo della domanda andra qua ? </p>
      <p><ul>
        <li>Oppure</li>
        <li>Qui</li>
      </ul></p>
      <a class="btn btn-light trigger pulsanti-edit"><i class="fas fa-trash-alt"></i></a>
      <a class="btn btn-light trigger pulsanti-edit"><i class="fas fa-edit"></i></a>

    </div>
    <div class="domande p-2 bg-light">Domanda 1</div>
    
    <tiles:insertAttribute name="newQuestionButton"/>
    

</div>

<tiles:insertAttribute name="popUpNewQuestion"/>