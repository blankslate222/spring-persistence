<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Player</title>
</head>
<body>
<h3>Create Player</h3>
<sf:form id="createPlayer" modelAttribute="player" action="${pageContext.request.contextPath}/player/create" method="post">
<table><tr>
<tr><td>Email:</td><td><sf:input path="email" id="email" type="email"/></td></tr>
<tr><td>First Name:</td><td><sf:input path="firstName" id="firstName" type="text"/></td></tr>
<tr><td>Last Name:</td><td><sf:input path="lastName" id="lastName" type="text"/></td></tr>
<tr><td>Address:</td><td><sf:input path="address" id="address" type="text"/></td></tr>
<tr><td>Description:</td><td><sf:textarea path="description" id="description" cols="50" rows="5"></sf:textarea></td></tr>
<tr><td>Sponsor:</td>
<td><sf:input path="sponsor.id" id="sponsor" type="text" /></td></tr>
<tr><td><input type="submit" id="create" value="Create"/></td></tr>
</table>
</sf:form>
</body>
</html>