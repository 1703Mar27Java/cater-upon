<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CaterUpon</title>

<link href="img/favicon.ico" rel="icon" type="image/x-icon">
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
<link type="text/css" media="all" href="css/style.css" rel="stylesheet">
<link type="text/css" media="all" href="css/login.css" rel="stylesheet">
<body>
	<div class="header">
		<nav class="navbar navbar-inverse" style="background-color: #495867 ;">
			<div class="container">
				<!-- Logo Image Goes Here -->
				<img src="img/logo.png"
					style="float: left;"> 
        
          
				

			<!--  
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown navbar-right"><a href="#" class="dropdown-toggle "
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false" style="color: #F7A247	;">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu " style="background-color: #495860 ;">
							<li><a href="#" style="color: #F7A247	;">Action</a></li>
							<li><a href="#" style="color: #F7A247	;">Another action</a></li>
							<li><a href="#" style="color: #F7A247	;">Something else here</a></li>
							<li role="separator" class="divider" style="color: #F4BFFF	;"></li>
							<li class="dropdown-header" style="color: #F7A247	;"><strong>Nav header</strong></li>
							<li><a href="#" style="color: #F7A247	;">Separated link</a></li>
							<li><a href="#" style="color: #F7A247	;">One more separated link</a></li>
						</ul></li>
				</ul>
			</div>
			-->
		</nav>
	</div>

<div class="pageContent">
	<div id="form" class="container  " >
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap" >
                <div class="pass-reset" >
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap "  >
                <p class="form-title ">
                    Sign In</p>
                <form class="login card card-container" >
                <input type="text" placeholder="Username" />
                <input type="password" placeholder="Password" />
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6 forgot-pass-content">
                            <a href="javascription:void(0)" class="forgot-pass">Forgot Password?</a>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
     
</div>

</div>

</body>
<script>
$(document).ready(function () {
    
});

$(document).ready(function(){
	$('.forgot-pass').click(function(event) {
	      $(".pr-wrap").toggleClass("show-pass-reset");
	    }); 
	    
	    $('.pass-reset-submit').click(function(event) {
	      $(".pr-wrap").removeClass("show-pass-reset");
	    }); 
	    
	    
	var images = ['cake.jpeg', 'pizza.jpg', 'salmon.jpeg', 'sliders.jpg', 'stirfry.jpeg'];
	$('body').css({'background-image': 'url(img/' + images[Math.floor(Math.random() * images.length)] + ')'});
})

</script>
</html>