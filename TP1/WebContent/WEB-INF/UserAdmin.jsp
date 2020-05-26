<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserAdmin</title>
</head>
<body>
	<form method="post" action="userAdmin">
		<sql:setDataSource var="bdd" driver="com.mysql.jdbc.Driver"
			url="jdbc:mysql://localhost:3306/Cours_JEE_TP1?serverTimezone=UTC"
			user="Cours_JEE_JDBC1" password="password" />
		<sql:query dataSource="${bdd}" var="result">SELECT * FROM utilisateur WHERE isadmin=false;</sql:query>
		<div align="center"><label> les utilisateurs normals</label></div>
		<table border="1" width="100%">
		<tr>
		<th>ID</th>
		<th>email</th>
		<th>nom</th>
		<th>date inscription</th>
		<th> </th>
		</tr>
		<c:forEach var="row" items="${result.rows}">
		<tr>
		<td><c:out value="${row.id}"/></td>
		<td><c:out value="${row.email}"/></td>
		<td><c:out value="${row.nom}"/></td>
		<td><c:out value="${row.date_inscription}"/></td>
		<td>supprimer utilisateur <input type="submit" id="supprimer" name="supprimer" value="${row.id}"/></td>
		</tr>
		</c:forEach>
		</table>
	</form>
	<br>
	<form method="post" action="deconnexion">
	<input type="submit" value="deconnexion"/>
	</form>
	
</body>
</html>