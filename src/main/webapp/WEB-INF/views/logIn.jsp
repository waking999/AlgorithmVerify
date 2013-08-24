<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>
	<h2>Log In</h2>
	<form:form modelAttribute="user" action="logInSubmit">
		<fieldset>
			<table>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="password" /></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" id="logIn" value="Log In" /></td>
				</tr>
			</table>
		</fieldset>
	</form:form>

</body>
</html>