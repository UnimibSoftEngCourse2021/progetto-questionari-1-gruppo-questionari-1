<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>





<div class="domande p-2 bg-light">

    <div style="max-width: 200px; max-height: 200px; margin: 10px;">
        <img style="max-width: 100%; max-height: 100%;" src="../img/bg-ocean.jpg" alt="">
    </div>
    <p>cosa vedi in questa foto?</p>
    <a class="btn btn-light trigger pulsanti-edit"><i class="fas fa-trash-alt"></i></a>
    <a class="btn btn-light trigger pulsanti-edit" data-target="#aggiungi-domanda" data-toggle="modal" ><i class="fas fa-edit"></i></a>

</div>