
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 

<html>
<head>
	<title><tiles:getAsString name="titolo" /></title>
</head>
<body>
	<tiles:insertAttribute name="topBar"/>
	<div>
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html> 
