<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<div id="signup" class="modal fade" role="dialog">
    <div class="modal-dialog">

  <div class="modal-content">
    <div class="modal-body">
      <button data-dismiss="modal" class="close">&times;</button>
      <h5>Sign up</h5>

      <form action = "registrazioneUtente" method ="get">
        <input type="email" name="email" class="email form-control" placeholder="E-mail" required/>
        <input type="text" name="nome" class="nome form-control" placeholder="Nome" required/>
        <input type="text" name="cognome" class="cognome form-control" placeholder="Cognome" required/>
        <input type="password" name="password" class="password form-control" placeholder="Password" required/>
        <input class="btn signup" type="submit" value="Sign up" />
      </form>

      </div>
    </div>
  </div>  
</div>