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
<c:if test="${not empty car}">
    <script>
        window.onload = function () {
            $("#carmodal").modal("show");
        };
    </script>
</c:if>
<c:if test="${not empty order}">
    <script>
        window.onload = function () {
            $("#ordermodal").modal("show");
        };
    </script>
</c:if>
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
                       <form:form action="cars/" class="row g-3" modelAttribute="filter_car">
                           <div class="col-12">
                              <label for="inputAddress" class="form-label">Name</label>
                              <form:input path="nameFilter" type="text" class="form-control" id="inputAddress" placeholder="Car name..."/>
                           </div>
                           <div class="col-md-6">
                              <label for="inputEmail4" class="form-label">From</label>
                              <form:input path="minFilter" type="number" class="form-control" id="min_price" min="0" onchange="document.getElementById('max_price').min=this.value;"  placeholder="Minimum price" />
                           </div>
                           <div class="col-md-6">
                              <label for="inputPassword4" class="form-label">To</label>
                              <form:input path="maxFilter" type="number" class="form-control" id="max_price" max="1000000000000000" min="document.getElementById('min_price').value" placeholder="Maximum price" />
                           </div>
                           <div class="col-md-2 form-group">
                              <label for="sel1">Types:</label>
                              <form:select path="typeFilter" items="${types }"
								itemValue="name" itemLabel="name"
								class="form-control">
		
								</form:select>
                           </div>
                           <div class="col-md-2 form-group">
                              <label for="sel1">Brands:</label>
                              <form:select path="brandFilter" items="${brands }"
								itemValue="name" itemLabel="name"
								class="form-control">
		
								</form:select>
                             
                           </div>
                           <div class="col-12">
                              <button type="submit" class="btn btn-lg">Search</button>
                           </div>
                           <div class="col-12">
                              <p>
                           </div>
                        </form:form>
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
								<c:forEach var="c" items="${pagedListHolder.pageList}">
									<div class="col-lg-3 col-md-4 col-xs-6 thumb">
						                <a href="cars/${c.id }.htm?linkCar" class="fancybox" rel="ligthbox">
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
       <jsp:useBean id="pagedListHolder" scope="request"
                     type="org.springframework.beans.support.PagedListHolder" />
                  <c:url value="cars/" var="pagedLink">
                     <c:param name="p" value="~" />
                  </c:url>
                  <div>
                     <tg:paging pagedListHolder="${pagedListHolder}"
                        pagedLink="${pagedLink}" />
                  </div>
   </div>
</div>


		<div class="modal fade" id="carmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
            	<div class="modal-content">
                    <div class="modal-body">
						<div class="image-cover card bg-dark text-white">
						  <img class="card-img" src="${car.img }" alt="Card image">
						  <div class="card-img-overlay mt-5">
						  	<h5 class="card-title">Car: ${car.name }</h5>	
						    <p class="card-text">Amount: ${car.price }</p>
						    <p class="card-text">Amount: ${car.type.name }</p>
						    <p class="card-alg-left card-text">Amount: ${car.brand.name }</p>
						    
                            <div class="card-alg-right ">
				                <a href="cars/" class=" btn btn-lg">Buy now</a>
				            </div>
						    <p class="card-text">Amount: ${car.amount }</p>
						    <p class="card-text">Amount: ${car.disc }</p>
						  </div>
						</div>
                        
					    <script>
					        $('#datepicker').datepicker({
					            uiLibrary: 'bootstrap4'
					        });
					    </script>
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