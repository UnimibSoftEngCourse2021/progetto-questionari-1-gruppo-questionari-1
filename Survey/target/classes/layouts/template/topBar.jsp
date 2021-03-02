<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- this bar will stay upon every web app page, it contains the web app title on the left and it could contain some buttons on the right-->


<link rel = "stylesheet" type = "text/css" href = "style/topBar.css">
<div id = "bar">
	<h1 id = "title">Survey.io</h1>
	<div class="buttons" id = "buttons">

		<tiles:insertAttribute name="notLoggedButtons" />
		
		
	  </div>
</div>