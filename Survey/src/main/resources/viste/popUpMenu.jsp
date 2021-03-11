<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<div id="menu" class="modal fade" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
	  
	  <div class="modal-content" style="display: flex; align-items: center;" >
		<div class="modal-body">
      
      
      <div class="btn-group-vertical"style="display: inline-block">
		    <a class="btn btn-light trigger bottone-menu" href="/Survey"  >Home</a>
        <a class="btn btn-light trigger bottone-menu" href="nuovoQuestionario">Create new survey</a>
        <a class="btn btn-light trigger bottone-menu" href="gestisciDomande">Manage your questions</a>
        <a class="btn btn-light trigger bottone-menu" href="questionari">Manage your surveys</a>
        <a class="btn btn-light trigger bottone-menu" href="/">Compiled surveys</a>
		  </div>
    </div>
	  </div>
	</div>  
  </div>