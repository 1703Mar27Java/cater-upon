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
							<li><a href="user" style="color: #F7A247;">View Your Upcoming Events</a></li>
							<li><a href="userSearch" style="color: #F7A247;">Perform another Search</a></li>

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
		<div class="container">
			<div class="row">
				<div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
					<!-- 					<div class="well profile"> -->
					<div class="col-sm-12">
						<div class="col-xs-12 col-sm-8">
							<h2></h2>
							<p>
								<strong>Description: <c:out
										value="${sessionScope.currentCaterer.getCaterer_Description()}"></c:out></strong>
							</p>
							<p>
								<strong>Location: <c:out
										value="${sessionScope.currentCaterer.getCaterer_City()}"></c:out></strong>
								<strong> <c:out
										value="${sessionScope.currentCaterer.getCaterer_State().getState_Name()}"></c:out></strong>
								<strong> <c:out
										value="${sessionScope.currentCaterer.getCaterer_Zipcode()}"></c:out></strong>
							</p>
							<p>
								<strong>Cuisine: <strong><c:out
											value="${sessionScope.cuisines.values()[	
								sessionScope.currentCaterer.getCaterer_CuisineId()-1].toString()}"></c:out></strong></strong>



							</p>
						</div>
						<div class="center">
							<button data-toggle="modal" data-target="#squarespaceModal"
								class="btn btn-primary center-block">Create an Order</button>
						</div>
					</div>
					<div class="col-xs-12 divider text-center">
						<div class="col-xs-12 col-sm-4 emphasis">
							<h2>
								<strong>${sessionScope.totalRating} </strong>
							</h2>
							<p>
								<small>Average Rating</small>
							</p>
							<button id="viewReview" class="btn btn-success">
								<span class="fa fa-plus-circle"></span> View Reviews
							</button>

						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- 	</div> -->

		<div id="review" class="allreviews">
			<c:forEach var="row" items="${reviews}">
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
							<c:if test="${row.getReview_Rating() < 5}">
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




	<!-- line modal -->
	<div class="modal fade" id="squarespaceModal" tabindex="-1"
		role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h3 class="modal-title" id="lineModalLabel">Create an Order</h3>
				</div>
				<div class="modal-body">

					<!-- content goes here -->
					<form>
						<div class="form-group">
							<label for="numAtt">Number of Attendees</label> <input
								class="form-control" id="numAtt"
								placeholder="Number of Attendees" type="number">
						</div>

						<div class="form-group registration-date">
							<label class="control-label col-sm-3" for="registration-date">Date:</label><br>
							<div class="input-group registration-date-time">
								<span class="input-group-addon" id="basic-addon1"><span
									class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>


								<input class="form-control" name="registration_date"
									id="registration-date" type="date"> <span
									class="input-group-addon" id="basic-addon1"> <span
									class="glyphicon glyphicon-time" aria-hidden="true"> </span></span> <input
									class="form-control" name="registration_time"
									id="registration-time" type="time"> <span
									class="input-group-btn"> </span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-12" for="inputBody">Comments
								(Allergies, Request, etc..)</label>
							<div class="col-sm-12">

								<textarea class="form-control" id="inputBody" type="text"
									rows="8" data-gramm="true"
									data-txt_gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06"
									data-gramm_id="71c8bb8e-8a51-c867-6c4f-5b14b864ac06"
									spellcheck="false" data-gramm_editor="true"
									style="z-index: auto; position: relative; line-height: 26.6667px; font-size: 14px; transition: none; overflow: auto; background: transparent !important;"></textarea>
								<div>
									<div
										style="z-index: 2; opacity: 1; margin-left: 794px; margin-top: 197px;"
										class="gr-textarea-btn " data-grammarly-reactid=".3">
										<div title="Protected by Grammarly"
											class="gr-textarea-btn_status" data-grammarly-reactid=".3.0"> </div>
									</div>
								</div>
							</div>
						</div>

						<button type="button" id="createOrderBtn" class="btn btn-default">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {

		$("#review").hide();
		$("#viewReview").click(function() {
			$("#review").toggle();
		});

		$("#createOrderBtn").click(function() {

			var numAtt = $("#numAtt").val();
			console.log(numAtt);

			var comment = $("#inputBody").val();
			console.log(comment);

			var eventDate = $("#registration-date").val();

			var eventTime = $("#registration-time").val();

			console.log(eventTime);

			if (eventDate == "" || eventTime == "")
				return false;

			var order = {
				"numAtt" : numAtt,
				"comment" : comment,
				"eventDate" : eventDate,
				"eventTime" : eventTime
			};

			console.log(order);

			$.ajax({

				type : "POST",
				dataType : 'json',
				url : "createOrder",
				data : order,
				complete : function(data) {
					console.log("success")
					window.location.href = "user";
				}

			});
		});
	});
</script>
</html>