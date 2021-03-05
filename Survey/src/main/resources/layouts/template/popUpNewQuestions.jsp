
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
          <input type="text" name="domanda" class="form-control" placeholder="Domanda"/>
          <input type="text" name="password" class="password form-control" placeholder="Password"/>
          <input class="btn trigger " type="submit" value="Login" />
        </form>
      </div>
      </div>
    </div>  
    </div>