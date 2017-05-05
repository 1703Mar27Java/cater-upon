$(document).ready(function(){
	$('.forgot-pass').click(function(event) {
	      $(".pr-wrap").toggleClass("show-pass-reset");
	    }); 
	    
	    $('.pass-reset-submit').click(function(event) {
	      $(".pr-wrap").removeClass("show-pass-reset");
	    }); 
	    
	    
	var images = ['cake.jpg', 'pizza.jpg', 'salmon.jpg', 'sliders.jpg', 'stirfry.jpg'];
	$('body').css({'background-image': 'url(../img/' + images[Math.floor(Math.random() * images.length)] + ')'});
})