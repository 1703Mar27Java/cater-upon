<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<div class="pageContent">
		<h1>Welcome ${userBean.getUser_Username()}</h1>
		<h2>View or Update Your User Info.</h2>

		<div id="divUpdates">

			Email: <div id="em">${userBean.getUser_Email()} </div>
			<input id="inTxtEm" type="text" size="15" placeholder="Enter Your New Email" />
			<button id="btnShowUpdateFormEmail" type="button"
				class="btn btn-xs btn-warning">Update Email</button>
			<button id="btnSubmitEmail" type="button"
				class="btn btn-xs btn-warning">Submit Email</button>
			<br>
			<button id="btnShowPassForm" type="button"
				class="btn btn-xs btn-warning">Update Password</button>
			<input id="inTxtPw1" type="password" size="10"
				placeholder="Enter your password" /> 
			<input id="inTxtPw2" type="password" size="10" 
				placeholder="Confirm your password" />
			<button id="btnUpdatePass" type="button"
				class="btn btn-xs btn-warning">Update Password</button>

		</div>

		<!-- Info bar -->
		<div id="alertTxt" class="alert alert-warning" role="alert"></div>
		
		<div id = "upcomingEvents">
			<h1>Your upcoming events:</h1>
			${uOrders.toString()}
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		//hide pw reset form by default
		$("#inTxtPw1").hide();
		$("#inTxtPw2").hide();
		$("#btnUpdatePass").hide();
		$("#alertTxt").hide();
		$("#inTxtEm").hide();
		$("#btnSubmitEmail").hide();
	});
	$("#btnShowUpdateFormEmail").click(function() {
		//show email update form
		$("#inTxtEm").show();
		$("#btnSubmitEmail").show();
		$("#btnShowUpdateFormEmail").hide();
	});
	$("#btnSubmitEmail").click(function() {
		//hide email update form
		$("#inTxtEm").hide();
		$("#btnSubmitEmail").hide();
		$("#btnShowUpdateFormEmail").show();
		//perform ajax to update email
		var em=$("#inTxtEm").val();
		var u = "${userBean.getUser_Username()}";
		var emData= {
				"em" : em,
				"u"  : u
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
	$("#btnUpdatePass").click(function() {
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
		} 
		else if(pw1.length<4) {
			$("#alertTxt").show();
			$("#alertTxt").text("Password must be more than 4 characters long!!");
		} 
		else {
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
					$("#alertTxt").text("Successfully updated password!");
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