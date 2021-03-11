<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en" style="height: 100%;">

  
    <tiles:insertAttribute name="head" />
  
  

<body>

 

    <tiles:insertAttribute name="navBar"/>
    <div class="containeritem d-grid mx-auto">
    

    
	    
        <h1 class="display-4">Nome del questionario</h1>
           
        <form action="compilaQuestionario" method="get">
            <div class="domande p-2 bg-light">
                
                </div>
             <input style="margin-left: 40%;" class="btn trigger " type="submit" value="Conferma" />
        </form>
    </div>
   
  

	  


  
    <tiles:insertAttribute name="scripts"/>
  
  
 

  
</body>

</html>
