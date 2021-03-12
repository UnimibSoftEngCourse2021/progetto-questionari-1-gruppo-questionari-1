<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="col-md-10 col-lg-8 col-xl-7 mx-auto ricerca-domanda">

    <form action="cercaDomandaQuestionario" method ="get">
      <div class="form-row">
        <div class="col-12 col-md-9 mb-2 mb-md-0">
          <input style="border: 2px solid black;" type="text" name="categoria" class="form-control form-control-lg" placeholder="Inserisci parola chiave o codice...">
        </div>
        <div class="col-12 col-md-3">
        <button type="submit" class="btn btn-block btn-lg btn-light">Search</button>
      </div>
    </div>
  </form>
  
</div>