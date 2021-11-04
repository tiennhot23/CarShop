<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
      	<%@include file="/WEB-INF/views/include/header.jsp"%>
      	<base href="${pageContext.servletContext.contextPath}/">
      	<link rel="stylesheet" href="<c:url value='resources/assets/css/public/index.css'/>">
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
					<li><a id="footertag" class="nav-item nav-link">About Us</a></li>
					<li><a href="cars/" class="nav-item nav-link">Cars</a></li>
					<li class="dropdown">
						<a href="#" class="nav-item nav-link" data-toggle="dropdown">Explore</a>
						<div class="dropdown-menu">
							<a id="videotag" class="dropdown-item">Video</a>
							<a id="typetag" class="dropdown-item">Type</a>
							<a id="brandtag" class="dropdown-item">Brand</a>
						</div>
					</li>
					<li><a href="login.htm" class="nav-item nav-link">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="banner">
		<div class="container">
			<h1>IDRISCAR</h1>
			<h2>We are here to help you BuyaCar with confidence.</h2>
			<p>A dream without ambition is like a car without gas... you're not going anywhere.</p>
			<button class="button button-primary" id="getstart">Get Start</button>
		</div>
	</div>
</header>
<div id="main">
	<section id="content" class="content">
		<div style="margin-top: 100px;" class="container">
			<h1>Let's explore.</h1>
		</div>
		<div  style="margin-top: 100px;" class="container">
			<div style="margin-top: 100px;"  id="videos" class="row">
				<div class="col-md-4">
					<div class="embed-responsive embed-responsive-4by3">
					  <iframe width="420" height="345" src="https://www.youtube.com/embed/LALgQSrVsaQ"></iframe>
					</div>
				</div>
				<div class="col-md-4">
					<div class="embed-responsive embed-responsive-4by3">
					  <iframe width="420" height="345" src="https://www.youtube.com/embed/SRqBLf_BWqc"></iframe>
					</div>
				</div>
				<div class="col-md-4">
					<div class="embed-responsive embed-responsive-4by3">
					  <iframe width="420" height="345" src="https://www.youtube.com/embed/yDK5n53vQ0g"></iframe>
					</div>
				</div>
			</div>
			<div style="margin-top: 100px;"  id="types" class="carousel slide" data-ride="carousel">
			  <div class="carousel-inner">
			    <c:forEach var="c" items="${types}" begin="0" end="0">
				<div class="carousel-item active">
				<a href="cars/?typeFilter=${c.name}&brandFilter=" class="fancybox" rel="ligthbox">
			      <img class="d-block w-100" src="${c.img }">
			    </a>
			      <div class="carousel-caption d-none d-md-block">
				    <h1>${c.name } Car</h1>
				    <p>${c.disc }</p>
				  </div>
			    </div>
                </c:forEach>
			    <c:forEach var="c" items="${types}" begin="1">
				<div class="carousel-item">
				<a href="cars/?typeFilter=${c.name}&brandFilter=" class="fancybox" rel="ligthbox">
			      <img class="d-block w-100" src="${c.img }">
			    </a>
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
			<div style="margin-top: 100px;" id="brands" class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
	                   <!-- section-title -->
	                   <div class="section-title mb-0">
							<div class="container page-top">
								<div class="row">
									<c:forEach var="c" items="${brands}">
										<div class="col-lg-3 col-md-4 col-xs-6 thumb">
							                <a href="cars/?brandFilter=${c.name}&typeFilter=" class="fancybox" rel="ligthbox">
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
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
		
	</section>
</div>
</body>
</html>