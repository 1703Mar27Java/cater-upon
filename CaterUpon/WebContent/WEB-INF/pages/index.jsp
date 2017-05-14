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
<meta charset="ISO-8859-1">
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
	href=<c:url value="/resources/css/login.css" /> rel="stylesheet">
<body>
	<div class="header">
		<nav class="navbar navbar-inverse" style="background-color: #495867 ;">
		<div class="container">
			<!-- Logo Image Goes Here -->
			<img src=<c:url value="/resources/img/logo.png" />
				style="float: left;" width="300" height="75">
		</nav>
	</div>

	<div class="pageContent">
		<div id="form" class="container  ">
			<div class="row">
				<div class="col-md-12">
					<div class="pr-wrap">
						<div class="pass-reset">
							<label> Enter the email you signed up with</label> <input
								type="email" placeholder="Email" /> <input type="submit"
								value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
						</div>
					</div>
					<div class="wrap ">
						<p class="form-title ">Sign In</p>
						<form:form method="POST" action="login"
							class="login card card-container">
							<form:input path="user_Username" type="text"
								placeholder="Username" />
							<form:input path="user_Password" type="password"
								placeholder="Password" />
							<input type="submit" value="Sign In"
								class="btn btn-success btn-sm" />
							<div class="remember-forgot">
								<div class="row">
									<div class="col-md-6">
										<div class="checkbox">
											<a href="newUser" class="btn btn-warning btn-xs" role="button">New User</a>
											
										</div>
									</div>
									<div class="col-md-6 forgot-pass-content">
										<button data-toggle="modal" data-target="#emMod" type="button"
											class="btn btn-warning btn-xs">Reset Email</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>

		</div>

	</div>
	<!-- modal to email update -->
	<div class="modal fade" id="emMod" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="form-group">
					<label> Email to reset:</label> <input id="modEmail" type="text" />
				</div>
				<div class="modal-footer">
					<button style="float: right;" id="emailUpdate" type="button"
						class="btn btn-default" data-dismiss="modal">Update</button>
				</div>
			</div>
		</div>
	</div>
			
</body>

<script src=<c:url value="/resources/scripts/bg.js" />></script>
<script>
	$("#emailUpdate").click(function(){
		console.log("reseting email "+$("#modEmail").val())
		var e = $("#modEmail").val();
		data={"e":e};
		$.ajax({
			type : "POST", 
			dataType : 'json',
			url : "resetEmail",
			data : data, // Note it is important without stringifying
			complete : function(data) {
				
			}
		});
	});
	
	$(document)
			.ready(
					function() {
						$('.forgot-pass').click(function(event) {
							$(".pr-wrap").toggleClass("show-pass-reset");
						});

						$('.pass-reset-submit').click(function(event) {
							$(".pr-wrap").removeClass("show-pass-reset");
						});

						var images = [ 'cake.jpg', 'pizza.jpg', 'salmon.jpg',
								'sliders.jpg', 'stirfry.jpg' ];
						$('body')
								.css(
										{
											'background-image' : 'url(<c:url value="/resources/img/" />'
													+ images[Math.floor(Math
															.random()
															* images.length)]
													+ ')'
										});
					})
</script>
</html>