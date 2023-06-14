<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>

	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

		<!-- put new button: Add Customer -->

        			<input type="button" value="Add Customer"
        				   onclick="window.location.href='showFormForAdd'; return false;"
        				   class="add-button"
        			/>

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Movie Name</th>
					<th>release date</th>
					<th>rating</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempMovie" items="${movies}">
                            <!-- construct an "update" link with customer id -->
         					<c:url var="updateLink" value="/movies/showFormForUpdate">
         						<c:param name="movieId" value="${tempMovie.id}" />
         					</c:url>
                   <!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/movies/delete">
						<c:param name="movieId" value="${tempMovie.movieId}" />
					</c:url>
					<tr>
						<td> ${tempMovie.movieName} </td>
						<td> ${tempMovie.releaseDate} </td>
						<td> ${tempMovie.rating} </td>
					    <td>
                   <!-- display the update link -->
                        <a href="${updateLink}">Update</a>
                       |
                       <a href="${deleteLink}"
                      onclick="if (!(confirm('Are you sure you want to delete this movie?'))) return false">Delete</a>
                     </td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>








