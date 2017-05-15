<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ page import="com.revature.enums.States"%>
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
<link type="text/css" media="all"
	href=<c:url value="/resources/css/tooltip.css" /> rel="stylesheet">
<body>

	<!-- Page  header here. Contains logo and site navigation -->
	<div class="header">
		<nav class="navbar navbar-inverse" style="background-color: #495867 ;">
		<div class="container">
			<!-- Logo Image Goes Here -->
			<a href="/CaterUpon/"> <img
				src=<c:url value="/resources/img/logo.png" /> width="300"
				height="75" style="float: left;">
			</a>
			<h1 style="float: left; color: #F7A247;">&nbsp&nbsp&nbsp
				Welcome, ${userBean.getUser_Username()}</h1>



			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown navbar-right"><a href="#"
						class="dropdown-toggle " data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false" style="color: #F7A247;">Dropdown
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu " style="background-color: #495860;">
							<li><a href="caterer" style="color: #F7A247;">View Your
									Event Requests</a></li>

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




	<div class="pageContent">


		<h2>Update Your Public Profile Here</h2>
		<div id="alertTxt" class="alert alert-warning" role="alert"></div>
		<div class="container">
			<div class="row" style="width: 90%;">
				<div class="col-md-4">
					<a href="#"
						data-tooltip="Any customers performing a search will see this as a description of your business.">
						Public description of your business</a>
				</div>
				<div id="descrTxt" class="col-md-5">${catererBean.getCaterer_Description() }
				</div>
				<div class="col-md-1">
					<button data-toggle="modal" data-target="#descrMod" type="button"
						class="btn btn-warning">Update Description</button>
				</div>
			</div>
			<div class="row" style="width: 90%;">
				<div class="col-md-4">
					<a href="#"
						data-tooltip="Your location helps determine what customers will see your business when performing a search">
						Location </a>
				</div>
				<div id="locTxt" class="col-md-5">${catererBean.getCaterer_City()},
					${catererBean.getCaterer_State().getState_Name()}
					${catererBean.getCaterer_Zipcode()}</div>
				<div class="col-md-1">
					<button data-toggle="modal" data-target="#locMod" type="button"
						class="btn btn-warning">Update Location</button>
				</div>
			</div>

			<div class="row" style="width: 90%;">
				<div class="col-md-4">
					<a href="#"
						data-tooltip="Any customers performing a search within this radius of your location will be able to see your profile during a search.">
						Maximum search radius </a>
				</div>
				<div id="radText" class="col-md-5">${catererBean.getCaterer_SearchRadius() }
					miles</div>
				<div class="col-md-1">
					<button data-toggle="modal" data-target="#radMod" type="button"
						class="btn btn-warning">Update Radius</button>
				</div>
			</div>
			<div class="row" style="width: 90%;">
				<div class="col-md-4">Email</div>
				<div id="em" class="col-md-5">${userBean.getUser_Email() }</div>
				<div class="col-md-1">
					<button data-toggle="modal" data-target="#emMod" type="button"
						class="btn btn-warning">Update Email</button>
				</div>
			</div>
			<div class="row" style="width: 90%;">
				<div class="col-md-4"></div>
				<div class="col-md-5"></div>
				<div class="col-md-1">
					<button data-toggle="modal" data-target="#pwMod" type="button"
						class="btn btn-warning">Update Password</button>
				</div>
			</div>
		</div>


		<div id="modals">
			<!-- modal to display Location update -->
			<div class="modal fade" id="locMod" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>City:</label><input id="inCity" type="text" />
						</div>
						<div class="modal-footer">
							<p style="float: left; font-size: 75%">State:</p>
							<select id="inState" style="float: left; width: 100px;"
								class="form-control">
								<c:forEach var="entry" items="${States.values()}">
									<option>${entry}</option>
								</c:forEach>
							</select> <label>Zip:</label><input id="inZip" type="number"
								style="width: 150px;" />
							<button style="float: right;" id="locUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal to display description update -->
			<div class="modal fade" id="descrMod" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>Description:</label>
							<textarea id="inDescr" rows="4" cols="50"></textarea>
						</div>
						<div class="modal-footer">
							<button style="float: right;" id="descrUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal to display radius update -->
			<div class="modal fade" id="radMod" role="dialog">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>Search Radius<br> (in miles):
							</label><input id="inRadius" type="number" style="width: 200px;" />
						</div>
						<div class="modal-footer">

							<button style="float: right;" id="radiusUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal to email update -->
			<div class="modal fade" id="emMod" role="dialog">
				<div class="modal-dialog ">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>New Email:</label> <input id="modEmail" type="text" />
						</div>
						<div class="modal-footer">
							<button style="float: right;" id="emailUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal to password update -->
			<div class="modal fade" id="pwMod" role="dialog">
				<div class="modal-dialog ">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>New Password:</label> <input id="modPW1" type="text" /> <label>Verify
								Password:</label> <input id="modPW2" type="text" />
						</div>
						<div class="modal-footer">
							<button style="float: right;" id="pwUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal to password update -->
			<div class="modal fade" id="errorMod" role="dialog">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label>New Password:</label><input type="password"
								placeholder="Enter Password" id="modPW1" required> <label>Verify
								Password:</label><input type="password" placeholder="Verify Password"
								id="modPW2" required>
						</div>
						<div class="modal-footer">
							<button style="float: right;" id="pwUpdate" type="button"
								class="btn btn-default" data-dismiss="modal">Update</button>
						</div>
					</div>
				</div>
			</div>
		</div>





		<h2>Your current average user review is ${catererAverageReview}</h2>
		<br>

		<button id="showReviews" type="button" class="btn btn-warning">Show
			Reviews</button>


		<div class="container">
			<div class="row">
				<div class="col-md-1"></div>
				<div id="review" class="allreviews col-md-6 ">
					<c:forEach var="row" items="${reviewList}">
						<div class="column singlereview">
							<div class="row">
								<img src="http://dummyimage.com/60x60/666/ffffff&text=Anonymous"
									class="img-rounded" />


								<div class="review-block-rate">
									<c:forEach begin="0" end="${row.getReview_Rating()-1}">
										<button type="button" class="btn btn-warning btn-xs"
											aria-label="Left Align">
											<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										</button>
									</c:forEach>
									<c:if test="${row.getReview_Rating()<5}">
										<c:forEach begin="0" end="${4 - row.getReview_Rating()}">
											<button type="button" class="btn btn-default btn-grey btn-xs"
												aria-label="Left Align">
												<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
											</button>
										</c:forEach>
									</c:if>

								</div>
							</div>
							<div class="column">
								<div class="review-block-description">"${row.getReview_Message()}"</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>


</body>
<script>
	$(document).ready(function() {
		$("#alertTxt").hide();
		$("#review").hide();
	});

	$('#showReviews').click(function() {
		console.log("${reviewList}")
		//$('#reviewBucket').text("${reviewList}")
		$("#review").toggle();
	});

	//function to handle modal submit buttons
	$('#modals').on('click', 'button', function() {
		if ($(this)[0].id == "emailUpdate") {
			var u = '${userBean.getUser_Username()}';
			var e = $("#modEmail").val();
			var data = {
				"u" : u,
				"em" : e
			}
			$.ajax({
				type : "POST",
				/*contentType : 'application/json; charset=utf-8',*///use Default contentType
				dataType : 'json',
				url : "setEmail",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					$("#alertTxt").show();
					$("#alertTxt").text("Successfully updated email!");
					$("#em").text(data.responseText);
				}
			});
		} else if ($(this)[0].id == "pwUpdate") {
			var pw1 = $('#modPW1').val();
			var pw2 = $('#modPW2').val();
			//compare passwords, then perform ajax call
			if (pw1 == pw2) {
				var u = '${userBean.getUser_Username()}';
				var data = {
					"pw" : pw1,
					"u" : u
				}
				console.log(data);
				$.ajax({
					type : "POST",
					/*contentType : 'application/json; charset=utf-8',*///use Default contentType
					dataType : 'json',
					url : "resetPass",
					data : data, // Note it is important without stringifying
					success : function(data) {
						console.log("SUCCESS: ", data);
						$("#alertTxt").show();
						$("#alertTxt").text("Successfully updated password!");
					},
					error : function(e) {
						console.log("ERROR: ", e);

					},

				});
			} else {
				$("#alertTxt").show();
				$("#alertTxt").text("Passwords must match!");
			}
		} else if ($(this)[0].id == "radiusUpdate") {
			var u = '${userBean.getUser_Id()}';
			var r = $("#inRadius").val();
			var data = {
				"r" : r,
				"u" : u
			}
			$.ajax({
				type : "POST",
				/*contentType : 'application/json; charset=utf-8',*///use Default contentType
				dataType : 'json',
				url : "setRad",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					console.log("SUCCESS: ", data);
					$("#alertTxt").show();
					$("#alertTxt").text("Successfully updated radius!");
					$('#radText')[0].innerHTML = data.responseText + " miles"
				}

			});

		} else if ($(this)[0].id == "descrUpdate") {

			var d = $("#inDescr").val();
			var data = {
				"d" : d,
				"u" : ""
			}
			$.ajax({
				type : "POST",
				/*contentType : 'application/json; charset=utf-8',*///use Default contentType
				dataType : 'json',
				url : "setDescr",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					console.log("SUCCESS: ", data);
					$("#alertTxt").show();
					$("#alertTxt").text("Successfully updated description!");
					$('#descrTxt')[0].innerHTML = data.responseText;
				}

			});
		} else if ($(this)[0].id == "locUpdate") {
			var c = $("#inCity")[0].value
			var s = $("#inState").val();
			var z = $("#inZip").val();
			var data = {
				"city" : c,
				"state" : s,
				"zip" : z
			}
			console.log(data)

			$.ajax({
				type : "POST",

				dataType : 'json',
				url : "setLoc",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					console.log("SUCCESS: ", data);
					$("#alertTxt").show();
					$("#alertTxt").text("Successfully updated Location!");
					$('#locTxt')[0].innerHTML = data.responseText;
				}

			});

		}
	});
</script>
</html>