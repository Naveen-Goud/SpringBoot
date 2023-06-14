<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Movie Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Movie</h3>

		<form:form action="save" modelAttribute="movies" method="POST">
        <!-- need to associate this data with customer id -->
        			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="MovieName" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="releaseDate" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="rating" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style="clear; both;"></div>

		<p>
			<a href="${pageContext.request.contextPath}/movies/list">Back to List</a>
		</p>

	</div>

</body>

</html>









