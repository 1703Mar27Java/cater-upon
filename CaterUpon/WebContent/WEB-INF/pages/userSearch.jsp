<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CaterUpon</title>

<link href=<c:url value="/resources/img/favicon.ico" /> rel="icon"
	type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<!-- bootstrap cdn's -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous">
	
</script>
<link type="text/css" media="all"
	href=<c:url value="/resources/css/style.css" /> rel="stylesheet">
<body>

	<!-- Page  header here. Contains logo and site navigation -->
	<div class="header">
		<nav class="navbar navbar-inverse" style="background-color: #495867 ;">
		<div class="container">
			<!-- Logo Image Goes Here -->
			<img src=<c:url value="/resources/img/logo.png" />
				style="float: left;" width="300" height="75">

			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown navbar-right"><a href="#"
						class="dropdown-toggle " data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false" style="color: #F7A247;">Dropdown
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu " style="background-color: #495860;">
							<li><a href="user" style="color: #F7A247;">View Your
									Upcoming Events</a></li>

							<li role="separator" class="divider" style="color: #F4BFFF;"></li>
							<li class="dropdown-header" style="color: #F7A247;"></li>
							<li class="dropdown-header" style="color: #F7A247;"></li>
							<li class="dropdown-header" style="color: #F7A247;"></li>

							<li><a href="logout" style="color: #F7A247;">Log Out</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</div>

	<div >

		<div class="container">
			<div class="row">
				<div id="filter-panel" class="collapse filter-panel"
					style="background-color: #495867;">
					<div class="panel panel-default">
						<div class="panel-body">
							<form class="form-inline" role="form">
								<div class="form-group">
									<label class="filter-col" style="margin-right: 0;"
										for="pref-perpage">Cuisines:</label> <select id="pref-perpage"
										class="form-control">
										<option selected="selected" value="All">All</option>
										<option value="American">American</option>
										<option value="Italian">Italian</option>
										<option value="Mexican">Mexican</option>
										<option value="German">German</option>
										<option value="Sous">Sous</option>
										<option value="Jamaican">Jamaican</option>
										<option value="Canadian">Canadian</option>
										<option value="Chinese">Chinese</option>
										<option value="Japanese">Japanese</option>
										<option value="Thai">Thai</option>
										<option value="Southern">Southern</option>
										<option value="Gourmet">Gourmet</option>
										<option value="Party Food">Party Food</option>
										<option value="Meditterranean">Meditterranean</option>
									</select>
								</div>
								<!-- form group [rows] -->
								<div class="form-group">
									<label class="filter-col" style="margin-right: 0;"
										for="pref-search">City</label> <input type="text"
										class="form-control input-sm" id="pref-search">
								</div>
								<!-- form group [search] -->
								<div class="form-group">
									<label class="filter-col" style="margin-right: 0;"
										for="pref-orderby">Order by:</label> <select id="pref-orderby"
										class="form-control">
										<option id="A">Ascending</option>
										<option id="D">Descending</option>
									</select>
								</div>
								<!-- form group [order by] -->
								<div class="form-group">
									<button type="submit" class="btn btn-default filter-col">
										<span class="glyphicon glyphicon-record"></span> Save Settings
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<button type="button" class="btn btn-primary" data-toggle="collapse"
					data-target="#filter-panel">
					<span class="glyphicon glyphicon-cog"></span> Advanced Search
				</button>
			</div>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Description</th>
					<th>City</th>
					<th>State</th>
					<th>Zipcode</th>
				</tr>
			</thead>
		</table>

	</div>
</body>
<script src=<c:url value="/resources/scripts/bg.js" />></script>
<script>
	$(document)
			.ready(
					function() {
						
					
						var cuisine = $("pref-orderby :selected").text();
						console.log(cuisine);
						
					
					});
</script>
</html>