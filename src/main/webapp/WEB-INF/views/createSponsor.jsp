<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Sponsor</title>
</head>
<body>
<h3>Create Sponsor</h3>
<sf:form id="createSponsor" modelAttribute="sponsor" action="${pageContext.request.contextPath}/sponsor" method="post">
<table><tr>
<tr><td>Name:</td><td><sf:input path="name" id="name" type="text"/></td></tr>
<tr><td>Address:</td><td><sf:input path="address" id="address" type="text"/></td></tr>
<tr><td>Description:</td><td><sf:textarea path="description" id="description" cols="50" rows="5"></sf:textarea></td></tr>
<tr><td><input type="submit" id="create" value="Create"/></td></tr>
</table>
</sf:form>
</body>
</html>