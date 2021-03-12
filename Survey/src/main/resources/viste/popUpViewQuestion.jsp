<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<div id="scegli-domanda-questionario" class="modal fade" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="display: flex; align-items: center;" >
      	<div class="modal-body">
        	<h4>Choose question</h4>

        	<form action="aggiungiDomanda" method="get">
	        <c:forEach items="${listaDomande}" var="domanda">
	    			<div class="domande p-2 bg-light">
	    				<div style="max-width: 200px; max-height: 200px; margin: 10px;">
	        			<img style="max-width: 100%; max-height: 100%;" src="../img/bg-ocean.jpg" alt="">
	        		</div>
	    			<input type="radio" id ="domandaScelta" name="domandaScelta" value=${domanda.id} />
	    			<p>${domanda.testo}</p>

	    			<c:forEach items="${domanda.opzioni}" var="opzione">
	    				<div>
	    					<input type="radio" id=${domanda.id} name=${domanda.id} /><label>${opzione.descrizioneOpzione}</label><br/>
	    				</div>
	    			</c:forEach>

	    			<a class="btn btn-light trigger pulsanti-edit" ><i class="fas fa-trash-alt"></i></a>
	    			<a class="btn btn-light trigger pulsanti-edit" data-target="#aggiungi-domanda" data-toggle="modal" ><i class="fas fa-edit"></i></a>
				</div>				
				</c:forEach>

          		<input style="margin-left: 40%;" class="btn trigger " type="submit" value="Conferma" />
       		</form>

      	</div>
      </div>
    </div>  
</div>