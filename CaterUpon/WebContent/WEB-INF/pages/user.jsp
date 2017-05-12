<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.revature.enums.StatusTypes"%>

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
			<a href="/CaterUpon/">
				<img src=<c:url value="/resources/img/logo.png" />
					style="float: left;" width="300" height="75">
			</a>
			<h1 style="float:left; color: #F7A247;">&nbsp&nbsp&nbsp Welcome, ${userBean.getUser_Username()}</h1>
			
			<div style="float:right;" class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown navbar-right"><a href="#"
						class="dropdown-toggle " data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false" style="color: #F7A247;">Dropdown
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu " style="background-color: #495860;">
							<li><a href="userSearch" style="color: #F7A247;">Create
									an Event Request</a></li>

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
	<!-- section to update password and email -->
	<div class="pageContent">
		
		<h2>View or Update Your User Info.</h2>

		<div id="divUpdates">

			Email:
			<div id="em">${userBean.getUser_Email()}</div>
			<input id="inTxtEm" type="text" size="15"
				placeholder="Enter Your New Email" />
			<button id="btnShowUpdateFormEmail" type="button"
				class="btn btn-xs btn-warning">Update Email</button>
			<button id="btnSubmitEmail" type="button"
				class="btn btn-xs btn-warning">Submit Email</button>
			<br>
			<button id="btnShowPassForm" type="button"
				class="btn btn-xs btn-warning">Update Password</button>
			<input id="inTxtPw1" type="password" size="10"
				placeholder="Enter your password" /> <input id="inTxtPw2"
				type="password" size="10" placeholder="Confirm your password" />
			<button id="btnUpdatePass" type="button"
				class="btn btn-xs btn-warning">Update Password</button>

		</div>

		<!-- Info bar -->
		<div id="alertTxt" class="alert alert-warning" role="alert"></div>

		<!-- Display user orders here. Orders are displayed in pending, then approved, then fulfilled or denied -->
		<div id="upcomingEvents">
			<h1>Your Pending Events:</h1>
			<table class="table table-responsive">
				<tr class="info">

					<th>Date of Event</th>
					<th>Number of Attendees</th>
					<th>Location of Event</th>
					<th>Cost of Event</th>
					<th>Order Comments</th>
				</tr>
				<c:forEach var="row" items="${uOrders}">
					<c:if test="${row.getOrder_Status().getStatus_Id()==1}">
						<tr class="warning">
							<td><c:out value="${row.getOrder_Date()}" /></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td>
								<c:out value="${row.getOrder_City()}" />, 
								<c:out value="${row.getOrder_State().getState_Name()}" />
							</td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<br>
			<h1>Your Upcoming Events:</h1>
			<table class="table table-responsive">
				<tr class="info">

					<th>Date of Event</th>
					<th>Number of Attendees</th>
					<th>Location of Event</th>
					<th>Cost of Event</th>
					<th>Order Comments</th>
					<th>Order Status</th>
				</tr>

				<c:forEach var="row" items="${uOrders}">
					<c:if test="${row.getOrder_Status().getStatus_Id()!=1 && row.getOrder_Status().getStatus_Id()!=5}">
						<tr class="warning">
							<td><c:out value="${row.getOrder_Date()}" /></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td>
								<c:out value="${row.getOrder_City()}" />, 
								<c:out value="${row.getOrder_State().getState_Name()}" />
							</td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
							<td><c:out value="${row.getOrder_Status().getStatus_Type()}" /></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<h1>Your Past Events:</h1>
			<table id="pastEvents" class="table table-responsive">
				<tr class="info">

					<th>Date of Event</th>
					<th>Number of Attendees</th>
					<th>Location of Event</th>
					<th>Cost of Event</th>
					<th>Order Comments</th>
					<th>Order Status</th>
					<th></th>
				</tr>
				<c:forEach var="row" items="${uOrders}">
					<c:if
						test="${row.getOrder_Status().getStatus_Id()==5 || row.getOrder_Status().getStatus_Id()==3}">
						<tr class="warning">

							<td><c:out value="${row.getOrder_Date()}" /></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td><c:out value="${row.getOrder_City()}" />, <c:out
									value="${row.getOrder_State().getState_Name()}" /></td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
							<td><c:out value="${row.getOrder_Status().getStatus_Type()}" /></td>
							<c:if test="${row.getOrder_revFlag()!=1}">
								<td><button data-toggle="modal" data-target="#myModal"
										type="button" class="btn btn-warning">Submit a review</button></td>
							</c:if>
							<c:if test="${row.getOrder_revFlag()==1}">
								<td>(Reviewed)</td>
							</c:if>
							<td style="visibility: hidden;"><c:out
									value="${row.getOrder_Id()}" /></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="form-group">
							<label for="comment">Please enter a review for your
								order:</label>
							<textarea class="form-control" rows="5" id="comment"></textarea>
						</div>
						<div class="modal-footer">
							<p style="float: left; font-size: 75%">Please Provide a
								Rating:</p>
							<select id="modalSelect" style="float: left; width: 60px;" class="form-control">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							<button style="float: right;" id="modalSbmt" type="button"
								class="btn btn-default" data-dismiss="modal">Submit
								Review</button>
						</div>
					</div>
				</div>
			</div>


			<br>
		</div>
	</div>
</body>
<!-- TODO FINISH REVIEW UPDATE, NEEDS TO PULL FROM DROP DOWN TO THE CONTROLLER -->

<script>
	//click event for "past events" table UNREVIEWED rows
	$('#pastEvents').on('click', 'button', function() {
		//getting the order id stored in the hidden field
		var oid = $(this).parent().parent().children()[7].innerHTML;
		//store the button pressed
		var btn=$(this);
		//perform ajax query when
		$('#modalSbmt').click(function() {
			var cmt = $('#comment').val();
			var rate= $("#modalSelect").val();
			var user= '${userBean.getUser_Id()}'
			var data = {
				"oid" : oid,
				"cmt" : cmt,
				"rate": rate,
				"user": user
			}
			//perform ajax call to order review module of controller
			$.ajax({
				type : "POST", 
				dataType : 'json',
				url : "setComment",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					//hide the button we pressed earlier
					btn.parent().children().hide()	
					btn.parent().append("<p>(Reviewed)</p>")
				}
			});
			
		})

	});

	$(document).ready(function() {
		//hide pw reset form by default
		$("#inTxtPw1").hide();
		$("#inTxtPw2").hide();
		$("#btnUpdatePass").hide();
		$("#alertTxt").hide();
		$("#inTxtEm").hide();
		$("#btnSubmitEmail").hide();
	});
	//This section handles user information updates
	$("#btnShowUpdateFormEmail").click(function() {
		//show email update form
		$("#inTxtEm").show();
		$("#btnSubmitEmail").show();
		$("#btnShowUpdateFormEmail").hide();
	});
	//function to reset a user's email
	$("#btnSubmitEmail").click(function() {
		//hide email update form
		$("#inTxtEm").hide();
		$("#btnSubmitEmail").hide();
		$("#btnShowUpdateFormEmail").show();
		//perform ajax to update email
		var em = $("#inTxtEm").val();
		var u = "${userBean.getUser_Username()}";
		var emData = {
			"em" : em,
			"u" : u
		}
		$.ajax({
			type : "POST",
			/*contentType : 'application/json; charset=utf-8',*///use Default contentType
			dataType : 'json',
			url : "setEmail",
			data : emData, // Note it is important without stringifying
			complete : function(data) {
				$("#alertTxt").show();
				$("#alertTxt").text("Successfully updated email!");
				$("#em").text(data.responseText);
			}
		});
	});
	$("#btnShowPassForm").click(function() {
		//show pw reset form
		$("#inTxtPw1").show();
		$("#inTxtPw2").show();
		$("#btnUpdatePass").show();
		$("#btnShowPassForm").hide();
	});
	//function to reset a user's password
	$("#btnUpdatePass").click(
			function() {
				//hide pw reset form
				$("#inTxtPw1").hide();
				$("#inTxtPw2").hide();
				$("#btnUpdatePass").hide();
				$("#btnShowPassForm").show();

				//perform ajax to update pw
				var pw1 = $("#inTxtPw1").val();
				var pw2 = $("#inTxtPw2").val();
				var u = "${userBean.getUser_Username()}";
				if (pw1 != pw2) {
					$("#alertTxt").show();
					$("#alertTxt").text("Passwords must match!!");
				} else if (pw1.length < 4) {
					$("#alertTxt").show();
					$("#alertTxt").text(
							"Password must be more than 4 characters long!!");
				} else {
					var pwData = {
						"pw" : pw1,
						"u" : u
					}
					console.log(pwData);

					$.ajax({
						type : "POST",
						/*contentType : 'application/json; charset=utf-8',*///use Default contentType
						dataType : 'json',
						url : "resetPass",
						data : pwData, // Note it is important without stringifying
						success : function(data) {
							console.log("SUCCESS: ", data);
							$("#alertTxt").show();
							$("#alertTxt").text(
									"Successfully updated password!");
						},
						error : function(e) {
							console.log("ERROR: ", e);

						},
						done : function(e) {
							console.log("DONE");
						}
					});
				}

			});
</script>
</html>