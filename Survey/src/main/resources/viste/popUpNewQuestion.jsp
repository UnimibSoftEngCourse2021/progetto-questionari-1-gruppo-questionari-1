
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>






<div id="aggiungi-domanda" class="modal fade" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
      
      <div class="modal-content" style="display: flex; align-items: center;" >
      <div class="modal-body">
        
        
        <h4>Create new question</h4>
        <form>
          <p>Insert questions</p>
          <input type="text" name="domanda" class="form-control" placeholder="Example?"/>
          <p>Insert options seprated by ";" or leave empty if it's an open question</p>
          <input type="text" name="opzioni" class="form-control" placeholder="option1;option2"/>
          <p>Insert Categories separate by ";"</p>
          <input type="text" name="categoia" class="form-control" placeholder="category1;category2"/>
          <input style="margin-left: 40%;" class="btn trigger " type="submit" value="Conferma" />
        </form>
      </div>
      </div>
    </div>  
    </div>