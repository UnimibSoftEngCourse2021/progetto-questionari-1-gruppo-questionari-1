<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!-- this bar will stay upon every web app page, it contains the web app title on the left and it could contain some buttons on the right-->


<link rel = "stylesheet" type = "text/css" href = "style/topBar.css">
<div id = "bar">
	<h1 id = "title">Survey.io</h1>
	<div id="buttons">
		<tiles:insert-attribute name="loginButton" />
		<tiles:insert-attribute name="registerButton" /> 
	</div>
</div>