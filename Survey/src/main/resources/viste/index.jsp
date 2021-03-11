<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en" style="height: 100%;">

  
    <tiles:insertAttribute name="head" />
  
  

<body>

 

    <tiles:insertAttribute name="navBar"/>
    <div style="margin-top: 12rem; margin-bottom: 12rem;">
        <tiles:insertAttribute name="masthead"/>

    </div>
   
  

	  


  
    <tiles:insertAttribute name="scripts"/>
  
  
 

  
</body>

</html>
