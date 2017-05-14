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

	<div>

		<div class="container">
			<div class="row">
				<div id="filter-panel" class="collapse filter-panel"
					style="background-color: #495867;">
					<div class="panel panel-default">
						<div class="panel-body">
							<form class="form-inline" name="form">
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
										class="form-control input-sm" id="pref-search" required>
								</div>
								<div class="form-group">
									<label class="filter-col" style="margin-right: 0;"
										for="pref-zip">Zip</label> <input type="number" min="10000"
										max="99999" class="form-control input-sm" id="pref-zip"
										required>
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
									<button id="searchbtn" type="button"
										class="btn btn-default filter-col">
										<span class="glyphicon glyphicon-record"></span> Search
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
				<tr class="info">
					<th>Cuisine</th>
					<th>Zipcode</th>
					<th>City</th>
					<th>State</th>
					<th>Description</th>
					<th>Caterer's Page</th>
				</tr>
			</thead>
			<tbody id="TableBody" class="warning">
			</tbody>
		</table>

	</div>
</body>
<script>
	$(document)
			.ready(
					function() {
						$('#TableBody')
								.on(
										'click',
										'#CatererBtn',
										function(e) {

											var selectedCaterer = $(this)
													.parent().parent()
													.children()[6].innerHTML;
											
											var selectedState = $(this)
											.parent().parent()
											.children()[3].innerHTML;

											console.log(selectedState);
											
											var currentCaterer = {
												"selectedCaterer" : selectedCaterer,
												"selectedState" : selectedState
											}

											$
													.ajax({
														type : "Post",
														url : "ViewCaterer",
														data : currentCaterer,
														complete : function(
																data) {
															window.location.href = "CatererProfile";

														}

													});

										});

						$("#searchbtn")
								.click(
										function() {

											var order = $("#pref-orderby")
													.val();
											var city = $("#pref-search").val();
											var cuisine = $("#pref-perpage")
													.val();
											var zip = $("#pref-zip").val();

											if (city == "" || zip == "")
												return false;

											var searchData = {
												"order" : order,
												"city" : city,
												"zip" : zip,
												"cuisine" : cuisine
											};

											console.log(searchData);

											$
													.ajax({

														type : "POST",
														dataType : 'json',
														url : "search",
														data : searchData,
														complete : function(
																data) {
															console
																	.log(data.responseText);

															var newData = data.responseText;

															if (newData != "undefined") {
																newData = newData
																		.split(",");
																console
																		.log(newData);

																if (newData[0]
																		.includes("["))
																	newData[0] = newData[0]
																			.substring(1);

																var table = document
																		.getElementById("TableBody");
																for (var i = 0; i < newData.length / 9; i++) {
																	var row = table
																			.insertRow(i);
																	row
																			.setAttribute(
																					"class",
																					"warning");
																	var cell = row
																			.insertCell(0);

																	cell.innerHTML = newData[i * 9];
																	cell
																			.setAttribute(
																					"hidden",
																					"true");

																	var k = 1;
																	for (var j = (i * 9) + 1, k = 1; k < 9; k++, j++) {

																		if (k == 7)
																			continue;
																		if (k == 1)
																			continue;

																		if (newData[j]
																				.includes("state_Name")) {
																			newData[j] = newData[j]
																					.substring(12);
																			newData[j] = newData[j]
																					.substring(
																							0,
																							newData[j].length - 1);
																		}

																		if (newData[j]
																				.includes("]"))
																			newData[j] = newData[j]
																					.substring(
																							0,
																							1);

																		if (!newData[j]
																				.includes("state_Id")) {

																			var cell = row
																					.insertCell(0);

																			if (k == 8) {
																				console.log(newData[j])
																				switch (newData[j]) {
																				case "1":
																					cell.innerHTML = "American";
																					break;
																				case "2":
																					cell.innerHTML = "Italian";
																					break;
																				case "3":
																					cell.innerHTML = "Mexican";
																					break;
																				case "4":
																					cell.innerHTML = "German";
																					break;
																				case "5":
																					cell.innerHTML = "Sous";
																					break;
																				case "6":
																					cell.innerHTML = "Jamaican";
																					break;
																				case "7":
																					cell.innerHTML = "Canadian";
																					break;
																				case "8":
																					cell.innerHTML = "Chinese";
																					break;
																				case "9":
																					cell.innerHTML = "Japanese";
																					break;
																				case "10":
																					cell.innerHTML = "Thai";
																					break;
																				case "11":
																					cell.innerHTML = "Southern";
																					break;
																				case "12":
																					cell.innerHTML = "Gourmet";
																					break;
																				case "13":
																					cell.innerHTML = "Party Food";
																					break;
																				case "14":
																					cell.innerHTML = "Mediterranean";
																					break;
																				}

																			} else
																				cell.innerHTML = newData[j];
																		}

																	}
																	var cellB = row
																			.insertCell(5);
																	cellB.innerHTML = "<button id='CatererBtn' class='btn btn-default filter-col'>Visit</button>";

																}
															}
														}
													});
										});
					});
</script>
</html>