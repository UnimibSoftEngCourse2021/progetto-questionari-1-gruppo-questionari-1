<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<div id="modQuest" class="modal fade" role="dialog">
    <div class="modal-dialog">

<div class="modal-content">
    <div class="modal-body">
      <button data-dismiss="modal" class="close">&times;</button>
      <h5>Modficia Questionario</h5>
      <form action = "modificaQuestionario" method ="get">
        <input type="text" name="nome" placeholder="Nome"/>
        <input type="text" name="cognome" placeholder="Categoria"/>
        <input class="btn signup" type="submit" value="Modifica" />
      </form>
    </div>
  </div>
</div>  
</div>