<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<div id="login" class="modal fade" role="dialog">
	<div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-body">
      <button data-dismiss="modal" class="close">&times;</button>
      <h4>Login</h4>
      <form action = "accedi" method ="get">
        <input type="email" name="email" class="email form-control" placeholder="E-mail"/>
        <input type="password" name="password" class="password form-control" placeholder="Password"/>
        <input class="btn login" type="submit" value="Login" />
      </form>
    </div>
  </div>
</div>  
</div>
