<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>

</head>
<body>
	<div>
		<tiles:insertAttribute name="topBar"/>
	</div>
	<div>
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html> 
