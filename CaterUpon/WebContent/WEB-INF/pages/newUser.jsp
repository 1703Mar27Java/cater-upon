<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ page import="com.revature.enums.States"%>
<%@ page import="com.revature.enums.Cuisines"%>
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
		</div>
		</nav>
	</div>

	<div class="pageContent">
		<label>Choose your user type:</label> <select id="typeSel">
			<option>Choose One:</option>
			<option>Caterer</option>
			<option>Customer</option>
		</select>
		<div id="user">
			<table>
				<tr>
					<td>Username:</td>
					<td><input id="inUser" type="text" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="inPW1" type="password" /></td>
				</tr>
				<tr>
					<td>Verify PW:</td>
					<td><input id="inPW2" type="password" /></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><input id="inEm" type="text" /></td>
				</tr>
			</table>

		</div>
		<div id="caterer">
			<table>
				<tr>
					<td>Username:</td>
					<td><input id="inUserCat" type="text" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="inPW1Cat" type="password" /></td>
				</tr>
				<tr>
					<td>Verify PW:</td>
					<td><input id="inPW2Cat" type="password" /></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><input id="inEmCat" type="text" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input id="inCity" type="text" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><select id="inState" style="float: left; width: 150px;"
						class="form-control">
							<c:forEach var="entry" items="${States.values()}">
								<option>${entry}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Cuisine Type:</td>
					<td><select id="inCuisine" style="float: left; width: 150px;"
						class="form-control">
							<c:forEach var="entry" items="${Cuisines.values()}">
								<option>${entry}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>ZIP:</td>
					<td><input id="inZip" type="number" /></td>
				</tr>
				<tr>
					<td>Search Radius:</td>
					<td><input id="inRad" type="number" /></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input id="inDescr" type="text" /></td>
				</tr>
			</table>
		</div>
	<button id="submit" class="btn btn-warning" >Submit</button>
	</div>
</body>
<script>
	$(document).ready(function() {
		$("#user").hide();
		$("#caterer").hide();
		$("#submit").hide();
	});
	$("#typeSel").change(function() {
		
		var sel = $("#typeSel").val()
		if (sel == "Choose One:") {
			$("#user").hide();
			$("#caterer").hide();
			$("#submit").hide();
		} else if (sel == "Caterer") {
			$("#user").hide();
			$("#caterer").show();
			$("#submit").show();
		} else if (sel == "Customer") {
			$("#user").show();
			$("#caterer").hide();
			$("#submit").show();
		}
	})
	$("#submit").click(function(){
		var sel = $("#typeSel").val()
		if (sel == "Caterer") {
			var u  =$("#inUserCat").val();
			var pw1=$("#inPW1Cat").val();
			var pw2=$("#inPW2Cat").val();
			var e  =$("#inEmCat").val();
			var c  =$("#inCity").val();
			var s  =$("#inState").val();
			var z  =$("#inZip").val();
			var r  =$("#inRad").val();
			var ct =$("#inCuisine").val();
			var d  =$("#inDescr").val();
			if(pw1==pw2){
				var data={
					"u" : u,
					"pw": pw1,
					"e" : e,
					"c" : c,
					"s" : s, 
					"z" : z,
					"r" : r,
					"ct": ct,
					"d" : d
				}
				console.log(data)
				$.ajax({
					type : "POST",
					dataType : 'json',
					url : "newCaterer",
					data : data, // Note it is important without stringifying
					complete : function(data) {
						window.location.href = '/CaterUpon/login';
					}
				});
			}
		}
		else if (sel == "Customer") {
			var u  =$("#inUser").val();
			var pw1=$("#inPW1").val();
			var pw2=$("#inPW2").val();
			var e  =$("#inEm").val();
			if(pw1==pw2){
				var data={
					"u" : u,
					"pw": pw1,
					"e" : e
				}
				console.log(data)
				$.ajax({
					type : "POST",
					dataType : 'json',
					url : "newUser",
					data : data, // Note it is important without stringifying
					complete : function(data) {
						window.location.href = '/CaterUpon/login';
					}
				});
			}
		}
	})
</script>
</html>