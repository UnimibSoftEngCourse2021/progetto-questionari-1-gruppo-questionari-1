<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


 <!-- Navigation -->
 <nav class="navbar navbar-light bg-light static-top">
    <div class="container">
      <a class="navbar-brand" href="#">Survey.io</a>
      <div style="float: right;">
      <a class="btn btn-light trigger" data-target="#login" data-toggle="modal" href="" >Log In</a>
	    <a class="btn btn-light trigger" data-target="#signup" data-toggle="modal" href="">Sign Up</a>
	  </div>
    </div>
  </nav>
  
  <tiles:insertAttribute name="popuplogin"/>
  <tiles:insertAttribute name="popupsignin"/>
  
  
        