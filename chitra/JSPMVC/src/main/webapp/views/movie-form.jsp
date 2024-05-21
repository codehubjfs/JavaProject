<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InsertMovie</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<%
        String email=(String)session.getAttribute("email");
	%>
	<div class = "container">
		<div class="float-right">
			<label>${email}</label>|
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
		<h1>Movie Directory</h1>
		<hr/>
		
		<div class = "row">
			<div class = "col-md-4">
				<form action = "${pageContext.request.contextPath}/MovieController" method="POST">
				
					<div class = "form-group">
						<input type = "hidden" class = "form-control" name = "mid" placeholder = "Enter MovieId" value = "${movie.movieId}"/>
					</div>
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "mname" placeholder = "Enter MovieName" value = "${movie.movieName}"/>
					</div>
				
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "mtype" placeholder = "Enter MovieType" value = "${movie.movieType}"/>
					</div>
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "mlanguage"  placeholder = "Enter MovieLanguage" value = "${movie.movieLanguage}"/>
					</div>
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "mduration" placeholder = "Enter MovieDuration" value = "${movie.movieDuration}"/>
					</div>
				
					<button type = "submit" class = "btn btn-primary">Save</button>
				</form>
			</div>
		</div>
		<a href = "${pageContext.request.contextPath}/MovieController?action=LIST">Back to List</a>
	</div>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>