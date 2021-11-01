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
      <link rel="stylesheet" href="<c:url value='resources/assets/css/public/index.css'/>">
      <link rel="stylesheet" href="<c:url value='resources/assets/css/public/footer.css'/>">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   </head>
<body>
<header class="header-area overlay">
    <nav class="navbar navbar-expand-md navbar-dark">
		<div class="container">
			<a href="#" class="navbar-brand">IDRISCAR</a>
			
			<button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#main-nav">
				<span class="menu-icon-bar"></span>
				<span class="menu-icon-bar"></span>
				<span class="menu-icon-bar"></span>
			</button>
			
			<div id="main-nav" class="collapse navbar-collapse">
				<ul class="navbar-nav ml-auto">
					<li><a href="#" class="nav-item nav-link active">Home</a></li>
					<li><a href="#footer" class="nav-item nav-link">About Us</a></li>
					<li><a href="cars/" class="nav-item nav-link">Cars</a></li>
					<li class="dropdown">
						<a href="#" class="nav-item nav-link" data-toggle="dropdown">Portfolio</a>
						<div class="dropdown-menu">
							<a href="#videos" class="dropdown-item">Video</a>
							<a href="#types" class="dropdown-item">Type</a>
							<a href="#brands" class="dropdown-item">Brand</a>
						</div>
					</li>
					<li><a href="login.htm" class="nav-item nav-link">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="banner">
		<div class="container">
			<h1>Bootstrap 4 Navbar</h1>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec elit ex. Etiam elementum lectus et tempor molestie.</p>
			<a href="#content" class="button button-primary">Get Start</a>
		</div>
	</div>
</header>
<main>
	<section id="content" class="content">
		<div style="margin-top: 100px;" class="container">
			<h1>Let's explore.</h1>
		</div>
		<div  style="margin-top: 100px;" class="container">
			<div style="margin-top: 100px;"  id="videos" class="row">
				<div class="col-md-4">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec elit ex. Etiam elementum lectus et tempor molestie. Pellentesque vestibulum dui sit amet dui volutpat sollicitudin. Etiam non erat finibus, iaculis nunc vel, convallis eros. Etiam efficitur tempor dui, vitae fringilla ipsum tristique quis. Aliquam erat volutpat. Cras ullamcorper ex et viverra vulputate. Nam lectus ligula, pretium nec risus nec, ultricies fringilla mauris. Proin quis venenatis neque, iaculis porta nulla. </p>
				</div>
				<div class="col-md-4">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec elit ex. Etiam elementum lectus et tempor molestie. Pellentesque vestibulum dui sit amet dui volutpat sollicitudin. Etiam non erat finibus, iaculis nunc vel, convallis eros. Etiam efficitur tempor dui, vitae fringilla ipsum tristique quis. Aliquam erat volutpat. Cras ullamcorper ex et viverra vulputate. Nam lectus ligula, pretium nec risus nec, ultricies fringilla mauris. Proin quis venenatis neque, iaculis porta nulla. </p>
				</div>
				<div class="col-md-4">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec elit ex. Etiam elementum lectus et tempor molestie. Pellentesque vestibulum dui sit amet dui volutpat sollicitudin. Etiam non erat finibus, iaculis nunc vel, convallis eros. Etiam efficitur tempor dui, vitae fringilla ipsum tristique quis. Aliquam erat volutpat. Cras ullamcorper ex et viverra vulputate. Nam lectus ligula, pretium nec risus nec, ultricies fringilla mauris. Proin quis venenatis neque, iaculis porta nulla. </p>
				</div>
			</div>
			<div style="margin-top: 100px;"  id="types" class="carousel slide" data-ride="carousel">
			  <div class="carousel-inner">
			    <c:forEach var="c" items="${types}" begin="0" end="0">
				<div class="carousel-item active">
			      <img class="d-block w-100" src="${c.img }">
			      <div class="carousel-caption d-none d-md-block">
				    <h1>${c.name } Car</h1>
				    <p>${c.disc }</p>
				  </div>
			    </div>
                </c:forEach>
			    <c:forEach var="c" items="${types}" begin="1">
				<div class="carousel-item">
			      <img class="d-block w-100" src="${c.img }">
			      <div class="carousel-caption d-none d-md-block">
				    <h1>${c.name } Car</h1>
				    <p>${c.disc }</p>
				  </div>
			    </div>
                </c:forEach>
			  </div>
			  <a class="carousel-control-prev" href="#types" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#types" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
			<div style="margin-top: 100px;" id="brand" class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                   <!-- section-title -->
	                   <div class="section-title mb-0">
							<div class="container page-top">
								<div class="row">
									<c:forEach var="c" items="${brands}">
										<div class="col-lg-3 col-md-4 col-xs-6 thumb">
							                <a href="cars/?brand=${c.name}" class="fancybox" rel="ligthbox">
							                    <img  src="${c.img }" class="zoom img-fluid "  alt="">
							                </a>
							                
							            </div>
	                                </c:forEach>
								</div>
							</div>	
	                   </div>
	                   <!-- /.section-title -->
	               </div>
	       </div>
		</div>
		<div id="footer" style="margin-top: 100px;">
	         <div id="part1">
	             <div id="companyinfo"> <a id="sitelink" href="#">IDRISCAR</a>
	                 <p id="title">Cool and Perfect Snippet code</p>
	                 <p id="detail">We create awesome code snippets that will help you in creating your own beautiful site. </p>
	             </div>
	             <div id="explore">
	                 <p id="txt1">Explore</p> <a class="link" href="#">Home</a> <a class="link" href="#">About</a> <a class="link" href="#">Snippet</a> <a class="link" href="#">Careers</a>
	             </div>
	             <div id="visit">
	                 <p id="txt2">Visit</p>
	                 <p class="text">Lincoln House </p>
	                 <p class="text">78 Bhulabhai Desai Road </p>
	                 <p class="text">Mumbai 400 026 </p>
	                 <p class="text">Phone: (22) 2363-3611 </p>
	                 <p class="text">Fax: (22) 2363-0350 </p>
	             </div>
	             <div id="legal">
	                 <p id="txt3">Legal</p> <a class="link1" href="#">Terms and Conditions</a> <a class="link1" href="#">Private Policy</a>
	             </div>
	             <div id="subscribe">
	                 <p id="txt4">Subscribe to US</p>
	                 <p class="text">tiennhot8@gmail.com </p>
	                 <p id="txt5">Connect With US</p> <i class="fab fa-facebook-square social fa-2x"></i> <i class="fab fa-linkedin social fa-2x"></i> <i class="fab fa-twitter-square social fa-2x"></i>
	             </div>
	         </div>
	         <div id="part2">
	             <p id="txt6"><i class="material-icons tiny"> </i>copyright 2019 BBbootstrap - All right reserved</p>
	         </div>
	     </div>
	</section>
</main>

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
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>