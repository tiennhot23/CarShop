<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
      	<base href="${pageContext.servletContext.contextPath}/">
      	<link rel="stylesheet" href="<c:url value='resources/assets/css/public/cars.css'/>">
      	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" id="bootstrap-css">
      	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		
		 <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
   </head>
<body>
<header class="header-area overlay">
    <div class="page-header">
        <div class="container">
            <div class="row">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="page-caption">
                        <nav class="navbar navbar-expand-md navbar-dark">
							<div class="container">
								<a href="#" class="navbar-brand">Bootdey.com</a>
								
								<button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#main-nav">
									<span class="menu-icon-bar"></span>
									<span class="menu-icon-bar"></span>
									<span class="menu-icon-bar"></span>
								</button>
								
								<div id="main-nav" class="collapse navbar-collapse">
									<ul class="navbar-nav ml-auto">
										<li><a href="#" class="nav-item nav-link active">Home</a></li>
										<li><a href="#" class="nav-item nav-link">About Us</a></li>
										<li><a href="cars/" class="nav-item nav-link">Cars</a></li>
										<li class="dropdown">
											<a href="#" class="nav-item nav-link" data-toggle="dropdown">Portfolio</a>
											<div class="dropdown-menu">
												<a href="#" class="dropdown-item">Dropdown Item 1</a>
												<a href="#" class="dropdown-item">Dropdown Item 2</a>
												<a href="#" class="dropdown-item">Dropdown Item 3</a>
												<a href="#" class="dropdown-item">Dropdown Item 4</a>
												<a href="#" class="dropdown-item">Dropdown Item 5</a>
											</div>
										</li>
										<li><a href="login.htm" class="nav-item nav-link">Login</a></li>
									</ul>
								</div>
							</div>
						</nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</header>
<div class="card-section">
   <div class="container">
       <div class="card-block bg-white mb30">
           <div class="row">
               <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                   <!-- section-title -->
                   <div class="section-title mb-0">
                       <h2>All about Hike. We share our knowledge on blog</h2>
                       <p>Our approach is very simple: we define your problem and give the best solution. </p>
                       
                   </div>
                   <!-- /.section-title -->
               </div>
           </div>
       </div>
       <div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                   <!-- section-title -->
                   <div class="section-title mb-0">
						<div class="container page-top">
							<div class="row">
							<div class="col-lg-3 col-md-4 col-xs-6 thumb">
				                <a href="https://images.pexels.com/photos/62307/air-bubbles-diving-underwater-blow-62307.jpeg?auto=compress&cs=tinysrgb&h=650&w=940" class="fancybox" rel="ligthbox">
				                    <img  src="https://images.pexels.com/photos/62307/air-bubbles-diving-underwater-blow-62307.jpeg?auto=compress&cs=tinysrgb&h=650&w=940" class="zoom img-fluid "  alt="">
				                </a>
				                
				            </div>
							</div>
						</div>	
                   </div>
                   <!-- /.section-title -->
               </div>
       </div>
   </div>
</div>



<script type="text/javascript">
jQuery(function($) {
    $(window).on('scroll', function() {
		if ($(this).scrollTop() >= 200) {
			$('.navbar').addClass('fixed-top');
		} else if ($(this).scrollTop() == 0) {
			$('.navbar').removeClass('fixed-top');
		}
	});
	
	function adjustNav() {
		var winWidth = $(window).width(),
			dropdown = $('.dropdown'),
			dropdownMenu = $('.dropdown-menu');
		
		if (winWidth >= 768) {
			dropdown.on('mouseenter', function() {
				$(this).addClass('show')
					.children(dropdownMenu).addClass('show');
			});
			
			dropdown.on('mouseleave', function() {
				$(this).removeClass('show')
					.children(dropdownMenu).removeClass('show');
			});
		} else {
			dropdown.off('mouseenter mouseleave');
		}
	}
	
	$(window).on('resize', adjustNav);
	
	adjustNav();
});
$(document).ready(function(){
	  $(".fancybox").fancybox({
	        openEffect: "none",
	        closeEffect: "none"
	    });
	    
	    $(".zoom").hover(function(){
			
			$(this).addClass('transition');
		}, function(){
	        
			$(this).removeClass('transition');
		});
	});
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
</html>