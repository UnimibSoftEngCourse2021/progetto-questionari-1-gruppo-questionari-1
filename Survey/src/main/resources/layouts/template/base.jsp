
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 

<html>
<head>
	<title><tiles:getAsString name="titolo" /></title>
</head>
<body>
	<div><p>Base</p></div>
	<div>
	 <tiles:insertAttribute name="content" /> </div> 
	<div>
     <tiles:insertAttribute name="footer" />  </div>
</body>
</html> 
