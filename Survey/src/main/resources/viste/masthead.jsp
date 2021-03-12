<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!-- Masthead -->
<header class="masthead text-white text-center">
  <div class="overlay"></div>
    <div class="container">
      <div class="row">
      <div class="col-xl-9 mx-auto">
        <h1 class="mb-5">Cerca un questionario in base al suo codice identificativo o categoria</h1>
      </div>
      <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">

        <form action="ricercaQuestionario" method="get">
          <div class="form-row">
            <div class="col-12 col-md-9 mb-2 mb-md-0">
              <input type="text" name ="id" class="form-control form-control-lg" placeholder="Inserisci codice del questionario da cercare .." required>
            </div>
          <div class="col-12 col-md-3">
            <button type="submit" class="btn btn-block btn-lg btn-light">Search</button>
          </div>
          </div>
        </form>

      </div>
    </div>
  </div>
</header>