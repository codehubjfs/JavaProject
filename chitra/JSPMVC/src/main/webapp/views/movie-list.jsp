<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Directory</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>
</head>
<body>
	<%
        String email=(String)session.getAttribute("email");
        
        //redirect user to login page if not logged in
        if(email==null){
        	response.sendRedirect("index.jsp");
        	
        }
    %>
	<div class = "container">
		<div class="float-right">
		    <label>${email}</label>|
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
		<h1>Movie Directory</h1>
		<hr/>
		
		<p>${NOTIFICATION}</p>
		
		<p>
			<button class = "btn btn-primary" onclick="window.location.href = 'views/movie-form.jsp'">Add Movie</button>
		</p>
	
		<table class = "table table-striped table-bordered" id="datatable">
			<thead>
				<tr class = "thead-dark">
					<th>MovieId</th>
					<th>MovieName</th>
					<th>MovieType</th>
					<th>MovieLanguage</th>
					<th>MovieDuration</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="movie">
					<tr>
						<td>${movie.movieId}</td>
						<td>${movie.movieName}</td>
						<td>${movie.movieType}</td>
						<td>${movie.movieLanguage}</td>
						<td>${movie.movieDuration}</td>
						<td> 
							<a href = "${pageContext.request.contextPath}/MovieController?action=EDIT&id=${movie.movieId}">Edit</a> 
							| 
							<a href = "${pageContext.request.contextPath}/MovieController?action=DELETE&id=${movie.movieId}">Delete</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.js"></script>
<script>
	$(document).ready(function(){
		$("#datatable").DataTable();
	})
</script>
</body>
</html>