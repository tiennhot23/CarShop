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
      	<link rel="stylesheet" href="<c:url value='resources/assets/css/loading.css'/>">
      	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" id="bootstrap-css">
      	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		
		 <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
		 <style type="text/css" media="screen">
	      div.hidden{
			   display: none
			}
	    </style>
   </head>
<body>
<div id="loading">
</div>
<script>
  $(window).on('load', function () {
	  window.setTimeout( finish, 2000 );
	  function finish(){
		  $('#loading').hide();
		    $("div#main").removeClass("hidden");
	  }
    
  }) 
</script>
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
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</header>
<div class="card-section hidden" id="main">
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
                           <div class="row col-12">
		                  	<div class="col-sm-11">
		                  		<div class="mb-2 d-flex justify-content-between align-items-center">
				                     <button type="submit" class="btn btn-lg">Search</button>
				                  </div>
		                  	</div>
		                  	<div class="col-sm-1">
		                  		<button name="clear" style="float: right;" type="submit" class="btn badge badge-info">Clear filter</button>
		                  	</div>
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
						                <a class="fancybox" rel="ligthbox" onclick="showCar('${c.id }', '${c.img}', '${c.name }','${c.price }', '${c.type.name }', '${c.brand.name }', '${c.amount }', '${c.disc }')">
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
						  <img id="modal-car-img" class="card-img" src="${car.img }" alt="Card image">
						  <div class="card-img-overlay mt-5">
						  	<h5 id="modal-car-name" class="card-title">Car: ${car.name }</h5>	
						    <p id="modal-car-price" class="card-text">Price: ${car.price }</p>
						    <p id="modal-car-type" class="card-text">Type: ${car.type.name }</p>
						    <p id="modal-car-brand" class="card-alg-left card-text">Brand: ${car.brand.name }</p>
						    
                            <div class="card-alg-right ">
				                <a onclick="showOrder()" class=" btn btn-lg">Buy now</a>
				            </div>
				            <p class="card-text">.</p>
						    <p id="modal-car-amount" class="card-text">Amount: ${car.amount }</p>
						    <p id="modal-car-disc" class="card-text">Discription: ${car.disc }</p>
						  </div>
						</div>
                    </div>
             	</div>
            </div>
        </div>
        
        <div class="modal fade" id="ordermodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
            	<div class="modal-content">
                    <div class="modal-body">
						<div class="row modal-row">
						    <div class="col-md-6 mb-4 mb-md-0">
						    	<h5 id="modal-order-car" class="card-title"></h5>
						      <img id="modal-order-img" width="100%" border-radius= "50%" src="${car.img }" alt="Card image">
						    </div>
						    <div class="col-md-6">
								<form action="cars/order.htm" method="post">
								  <div class="form-row">
								  	<div class="form-group col-md-12">
									    <label for="inputAddress2">Name</label>
									    <input name="customer" type="text" class="form-control" id="inputAddress2" placeholder="Your name" required>
									  </div>
								    <div class="form-group col-md-6">
								      <label for="inputEmail4">Email</label>
								      <input name="email" type="email" class="form-control" id="inputEmail4" placeholder="Email" required>
								    </div>
								    <div class="form-group col-md-6">
								      <label for="inputPassword4">Phone</label>
								      <input name="phone" type="text" class="form-control" id="inputPassword4" placeholder="Phone number" pattern="[0][0-9]{9}" required>
								    </div>
								  </div>
								  <div class="form-group">
								    <label for="inputAddress">Address</label>
								    <input name="addres" type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" required>
								  </div>
								  
								  <div class="form-row">
								  	<div class="form-group col-md-2">
								      <label for="inputZip">Amount</label>
								      <input name="amount" type="number" class="form-control" id="amountBuy" value="1" min="1" onclick="calcTotalPrice('amountBuy')" required>
								    </div>
								    <div class="form-group col-md-6">
								      <label for="inputCity">Total price</label>
								      <input type="number" class="form-control" id="modal-order-total" min="0" disabled>
								    </div>
								    <div class="form-group col-md-1">
								      <label for="inputCity">Unit</label>
								      <label class="mt-1" for="inputCity">$</label>
								    </div>
								    <div class="form-group">
								      <input type="hidden" class="form-control" id="modal-order-price" min="0" disabled>
								      <input name="carid" type="hidden" class="form-control" id="modal-order-carid" >
								    </div>
								  </div>
								  <div class="form-group">
								    <div class="form-check">
								      <input class="form-check-input" type="checkbox" id="lincenceCheck" onclick="onCheck('lincenceCheck', 'btnBuy')">
								      <label class="form-check-label ml-2" for="gridCheck">
								        I've read all the 
								      </label>
								      <a href=""><span>linsence</span></a>
								    </div>
								  </div>
								  <button type="button" class="btn btn-secondary mr-2" style="float: right;" data-dismiss="modal">Cancel</button>
								  <button id="btnBuy" type="submit" class="btn btn-lg" disabled>Buy</button>
								</form>
						    </div>
						  </div>
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
function showCar(id, img, name, price, type, brand, amount, disc){
	document.getElementById("modal-car-img").src = img;
	document.getElementById("modal-car-name").innerHTML = name;
	document.getElementById("modal-car-price").innerHTML = price + " $";
	document.getElementById("modal-car-type").innerHTML = "Type: " + type;
	document.getElementById("modal-car-brand").innerHTML = "Brand: " + brand;
	document.getElementById("modal-car-amount").innerHTML = "Amount: " + amount;
	document.getElementById("modal-car-disc").innerHTML = disc;
	
	document.getElementById("modal-order-img").src = img;
	document.getElementById("modal-order-car").innerHTML = name;
	document.getElementById("modal-order-total").value = price;
	document.getElementById("modal-order-price").value = price;
	document.getElementById("modal-order-carid").value = id;
	$("#carmodal").modal("show");
}
function showOrder(){
	
	$("#carmodal").modal("hide");
	$('#ordermodal').modal({backdrop: 'static', keyboard: false});
	$("#ordermodal").modal("show");
	
}
function onCheck(checkbox, button){
	if(document.getElementById(checkbox).checked){
		document.getElementById(button).disabled = false;
	}else{
		document.getElementById(button).disabled = true;
	}
}
function calcTotalPrice(amount){
	var a = document.getElementById(amount).value;
	document.getElementById("modal-order-total").value = a * document.getElementById("modal-order-price").value;
}
</script>
<script type="text/javascript">
$('[data-dismiss=modal]').on('click', function (e) {
    var $t = $(this),
        target = $t[0].href || $t.data("target") || $t.parents('.modal') || [];

  $(target)
    .find("input,textarea,select")
       .val('')
       .end()
    .find("input[type=checkbox], input[type=radio]")
       .prop("checked", "")
       .end();
})
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
</html>