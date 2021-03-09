<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- Navigation -->
<nav class="navbar navbar-light bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="#">Survey.io</a>
      
      <a class="btn btn-light trigger" data-target="#menu" data-toggle="modal"  >Menu</a>
	    
    </div>
  </nav>

  <tiles:insertAttribute name="popUpMenu"/>