<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserNormal</title>
</head>
<body>
	<label>ID: <c:out value="${utilisateur.id}"/></label>
	<br>
	<label>Nom d'utilisateur: <c:out value="${utilisateur.nom}"/></label>
	<br>
	<label>Adresse email: <c:out value="${utilisateur.email}"/></label>
	<br>
	<label>Statut: normal user</label>
	<br>
	<label>Date inscription: <c:out value="${utilisateur.dateInscription}"/></label>
	<br>
	
	<form method="post" action="deconnexion">
	<input type="submit" value="deconnexion"/>
	</form>
	
</body>
</html>