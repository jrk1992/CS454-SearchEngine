<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
th {
	background: black;
	text-align: center;
	color: white;
}
</style>
</head>
<body>
	<br />
	<div class="container">
		<div align="center">
			<form action="IndexerServlet" method="POST">
				<input style="display: inline; width: 60%;" text" name="search"
					class="form-control"> <input type="submit" name="search"
					style="display: inline; padding-left: 20px; width: 130px;"
					class="btn btn-primary" value="search">
			</form>
		</div>

		<%-- <c:if test="${noy empty #### }"> --%>
		<hr style="clear: bothl">
		<div align="center">
			<table class="table table-bordered table-striped">
				<tr>
					<!-- <th>No.</th> -->
					<th>TFIDF</th>
					<th>Page Rank</th>
					 <th>AVG</th> 
					<th>File</th>

				</tr>
				<c:forEach items="${ Files }" var="x" varStatus="count">
					<tr>
						<%--  <td>${count.index + 1}</td>  --%>
						<td>${x.tfidfScore}</td>
						<td>${x.linkScore}</td>
						 <td>${x.avg}</td>
						<td><a href="file:///${x.file}">${ x.file }</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
		<%-- </c:if> --%>
	</div>
</body>
</html>