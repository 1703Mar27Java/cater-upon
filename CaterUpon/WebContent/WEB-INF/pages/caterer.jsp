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
			<a href="/CaterUpon/">
				<img src=<c:url value="/resources/img/logo.png" /> width="300"
					height="75" style="float: left;">
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
							<li><a href="catererInfo" style="color: #F7A247;">Edit your
									Information/Profile</a></li>

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


	<!-- This is where all the page content goes. -->
	<!-- Display Pending, then approved/upcoming, then fulfilled/denied -->
	<div class="pageContent">

		<h2>Caterer Request Page</h2>
		<br><br>
		<h1>Your Pending Event Requests:</h1>
		<table  id="pendingTbl" class="table table-responsive">
			<tr class="info">
				<th>Customer Name</th>
				<th>Date of Event</th>
				<th>Number of Attendees</th>
				<th>Location of Event</th>
				<th>Cost of Event</th>
				<th>Order Comments</th>
				<th>Approve or Deny</th>
			</tr>
			<c:forEach var="row" items="${uOrders}">
					<c:if test="${row.getOrder_Status().getStatus_Id()==1}">
						<tr class="warning">
							<td><c:out value="${row.getOrder_Customer().getUser_Username()}" /></td>
							<td><c:out value="${row.getOrder_Date().getMonth()}/${row.getOrder_Date().getDate()}/${row.getOrder_Date().getYear()+1900} at 
								${row.getOrder_Date().getHours()}:${row.getOrder_Date().getMinutes()}"/></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td>
								<c:out value="${row.getOrder_City()}" />, 
								<c:out value="${row.getOrder_State().getState_Name()}" />
								<c:out value="${row.getOrder_Zipcode()}" />
							</td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
							<td><select>
									<option>- - - -</option>
									<option>Approve</option>
									<option>Deny</option>
							</select></td>
							<td style="display: none;"><c:out
									value="${row.getOrder_Id()}" /></td>
						</tr>
					</c:if>
				</c:forEach>
		</table>
		
		<br>
		
		<h1>Your Upcoming Event Requests:</h1>
		<table id="upcomingTbl" class="table table-responsive">
			<tr class="info">
				<th>Customer Name</th>
				<th>Date of Event</th>
				<th>Number of Attendees</th>
				<th>Location of Event</th>
				<th>Cost of Event</th>
				<th>Order Comments</th>
				<th>Fulfill or Cancel</th>
			</tr>
			<c:forEach var="row" items="${uOrders}">
					<c:if test="${row.getOrder_Status().getStatus_Id()==2 || row.getOrder_Status().getStatus_Id()==4}">
						<tr class="warning">
							<td><c:out value="${row.getOrder_Customer().getUser_Username()}" /></td>
							<td><c:out value="${row.getOrder_Date().getMonth()}/${row.getOrder_Date().getDate()}/${row.getOrder_Date().getYear()+1900} at 
								${row.getOrder_Date().getHours()}:${row.getOrder_Date().getMinutes()}"/></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td>
								<c:out value="${row.getOrder_City()}" />, 
								<c:out value="${row.getOrder_State().getState_Name()}" />
								<c:out value="${row.getOrder_Zipcode()}" />
							</td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
							<td><select>
									<option>- - - - -</option>
									<option>Fulfilled</option>
									<option>Cancelled</option>
							</select></td>
							<td style="display: none;"><c:out
									value="${row.getOrder_Id()}" /></td>
						</tr>
					</c:if>
				</c:forEach>
		</table>
		
		<br>
		<h1>Your Fulfilled or Denied Event Requests:</h1>
		<table  id="fulfilledTbl" class="table table-responsive">
			<tr class="info">
				<th>Customer Name</th>
				<th>Date of Event</th>
				<th>Number of Attendees</th>
				<th>Location of Event</th>
				<th>Cost of Event</th>
				<th>Order Comments</th>
				
			</tr>
			<c:forEach var="row" items="${uOrders}">
					<c:if test="${row.getOrder_Status().getStatus_Id()==2 || row.getOrder_Status().getStatus_Id()==5}">
						<tr class="warning">
							<td><c:out value="${row.getOrder_Customer().getUser_Username()}" /></td>
							<td><c:out value="${row.getOrder_Date().getMonth()}/${row.getOrder_Date().getDate()}/${row.getOrder_Date().getYear()+1900} at 
								${row.getOrder_Date().getHours()}:${row.getOrder_Date().getMinutes()}"/></td>
							<td><c:out value="${row.getOrder_NumOfAttendees()}" /></td>
							<td>
								<c:out value="${row.getOrder_City()}" />, 
								<c:out value="${row.getOrder_State().getState_Name()}" />
								<c:out value="${row.getOrder_Zipcode()}" />
							</td>
							<td><c:out value="${row.getOrder_Amount()}" /></td>
							<td><c:out value="${row.getOrder_Comment()}" /></td>
							
						</tr>
					</c:if>
				</c:forEach>
		</table>
	</div>
</body>
<script>
	//event handler for dropdown in pending table
	$('#pendingTbl').on('change','select', function(){
		var stat= $(this)[0].value;
		var tr = $(this).parent().parent();
		var td = $(this).parent().parent().children();
		var oid=$(this).parent().parent().children()[7].innerHTML;
		console.log(stat+" for order #"+oid);
		if(stat!="- - - -"){
			var data = {
					"oid" : oid,
					"stat": stat
			}
			$.ajax({
				type : "POST", 
				dataType : 'json',
				url : "setPending",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					//hide the button we pressed earlier
					//move the row pressed to the correct table
					//approved => upcoming table
					//denied => fulfilled/denied table
					console.log(stat);
					if(stat=="Approve"){
						td[6].innerHTML="<select> <option>- - - - -</option><option>Fulfilled</option><option>Cancelled</option></select>"
						tr.appendTo($('#upcomingTbl'))
					}
					else{
						td[6].innerHTML="";
						tr.appendTo($('#fulfilledTbl'))
					}
				}
			});
		}
	});
	//event handler for dropdown in upcoming table
	$('#upcomingTbl').on('change','select', function(){
		var stat= $(this)[0].value;
		var tr = $(this).parent().parent();
		var td = $(this).parent().parent().children();
		var oid=$(this).parent().parent().children()[7].innerHTML;
		console.log(stat+" for order #"+oid);
		if(stat!="- - - - -"){
			var data = {
					"oid" : oid,
					"stat": stat
			}
			$.ajax({
				type : "POST", 
				dataType : 'json',
				url : "setUpcoming",
				data : data, // Note it is important without stringifying
				complete : function(data) {
					//hide the button we pressed earlier
					console.log(data);
					td[6].innerHTML="";
					tr.appendTo($('#fulfilledTbl'))
				
				}
			});
		}
	});
</script>
</html>