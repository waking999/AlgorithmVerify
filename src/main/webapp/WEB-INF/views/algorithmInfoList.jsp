<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>
<!-- 
<script>
$(document).ready(function() {
	$.ajax({
		  url: "algorithmInfoListGetListStr",
		  
		  success: function( data ) {
			  alert("dddddddd");
		    $( "#algorithmInfListData" ).html("hello");
		  }
		});
});

</script>
 -->
<body>
	<h2>Algorithm Info List</h2>
	<table border="1">
		<tr>
			<th></th>
			<th>Name</th>
			<th>Upload Data</th>
			<th>filePath</th>
			<th>Log</th>
			<th></th>

		</tr>
		<!--  
		<div id="algorithmInfListData">
		</div>
		-->
		 
		<c:forEach var="ai" items="${varAlgorithmInfoList}">
			<tr>
				<td><input type="checkbox" />
				</td>
				
				<td><a href="#">${ai.name}</a></td>
				<td>${ai.uploadDate}</td>
				<td><a href="#">${ai.filePath}</a></td>
				<td><a href="#">${ai.log}</a></td>
				<td><a href="runAlgorithm?algorithmName=${ai.name}">Run</a></td>
			</tr>
		</c:forEach>

	</table>
	<br/>
	<a href='<c:url value="compareAlg"/>'>Compare Algorithms</a>
	<br/>
	
	<button>Upload</button><button>Delete</button>
</body>
</html>