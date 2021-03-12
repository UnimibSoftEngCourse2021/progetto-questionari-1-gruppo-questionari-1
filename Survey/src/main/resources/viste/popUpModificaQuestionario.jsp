<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<div id="modQuest" class="modal fade" role="dialog">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content" style="display: flex; align-items: center;" >
      <div class="modal-body">
        <h5>Modficia Questionario</h5>

       
          <input type="text" name="nome" placeholder="Nome"/>
          <input type="text" name="categoria" placeholder="Categoria"/>
          <input class="btn signup" type="submit" value="Modifica" />
       
        
      </div>
    </div>
  </div>  
</div>