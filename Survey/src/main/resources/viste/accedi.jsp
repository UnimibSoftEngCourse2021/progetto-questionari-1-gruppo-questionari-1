<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!-- login page -->
<div style="position: absolute; 
top: 50%;
left: 50%;
margin-top: -50px;
margin-left: -50px;
width: 100px;
height: 100px;">
<h1>Login</h1>

<form action="/accedi/login" method="POST">
    <label>Email:</label><br>
    <input type="text" name="Email" placeholder="Email"><br>
    <label>Password:</label><br>
    <input type="text" name="Password" placeholder="Password"><br><br>
    <input type="submit" value="Accedi">
</form>
</div>