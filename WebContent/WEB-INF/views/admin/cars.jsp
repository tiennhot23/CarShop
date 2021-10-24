<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html>
<head>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet" href="<c:url value='resources/assets/css/admin.css'/>">
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> 

<script src="<c:url value='resources/assets/scripts/script.js'/>"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body id="body-pd" class=" bg-light">
    <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
        <div class="header_img"> <img src="https://i.pinimg.com/originals/9d/5f/14/9d5f14a67e7e5a00101a6baf8a59fe7e.jpg" alt=""> </div>
    </header>
    <div class="l-navbar" id="nav-bar">
	    <nav class="nav">
	        <div>
	            <a href="admin/" class="nav_logo"> <i class="bx bx-layer nav_logo-icon"></i> <span class="nav_logo-name">BBBootstrap</span> </a>
	            <div class="nav_list">
	                <a href="admin/" class="nav_link"> <i class="bx bx-grid-alt nav_icon"></i> <span class="nav_name">Dashboard</span> </a>
	                <a href="admin/cars" class="nav_link  active"> <i class="bx bx-user nav_icon"></i> <span class="nav_name">Cars</span> </a>
	                <a href="admin/types" class="nav_link"> <i class="bx bx-message-square-detail nav_icon"></i> <span class="nav_name">Category</span> </a>
	                <a href="admin/brands" class="nav_link"> <i class="bx bx-bookmark nav_icon"></i> <span class="nav_name">Brand</span> </a> 
	                <a href="admin/stats" class="nav_link"> <i class="bx bx-bar-chart-alt-2 nav_icon"></i> <span class="nav_name">Stats</span> </a>
	            </div>
	        </div>
	        <a href="#" class="nav_link"> <i class="bx bx-log-out nav_icon"></i> <span class="nav_name">SignOut</span> </a>
	    </nav>
	</div>

    <!--Container Main start-->
    <div class=" bg-light">
        <h4>Main Components</h4>
		<div class="container mt-5 px-2">
		    <div class="mb-2 d-flex justify-content-between align-items-center">
		        <div class="position-relative"> <span class="position-absolute"><i class="fa"></i></span> <input class="form-control w-100" placeholder="Search by order#, name..."> </div>
		        <div class="px-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"> <span>Filters <i class="fa fa-angle-down"></i></span></div>
		    </div>
			<div class="collapse container" id="collapseExample">
                <div class="row">
                	
					<form class="row g-3">
					  <div class="col-12">
					    <label for="inputAddress" class="form-label">Name</label>
					    <input name="search" type="text" class="form-control" id="inputAddress" placeholder="Car name..." value=${filter_car.name }>
					  </div>
					  <div class="col-md-6">
					    <label for="inputEmail4" class="form-label">From</label>
					    <input name="min" type="number" class="form-control" id="min_price" min="0" onchange="document.getElementById('max_price').min=this.value;"  placeholder="Minimum price" value=${filter_car.min }>
					  </div>
					  <div class="col-md-6">
					    <label for="inputPassword4" class="form-label">To</label>
					    <input name="max" type="number" class="form-control" id="max_price" max="1000000000000000" min="document.getElementById('min_price').value" placeholder="Maximum price" value=${filter_car.max }>
					  </div>
					  <div class="col-md-2 form-group">
						  <label for="sel1">Types:</label>
						  <select name="type" class="form-control" id="sel1"">
						  	<option value="none" selected disabled hidden>
						          Select type
						    </option>
						  	<c:forEach var="c" items="${types }">
			                	<c:choose>
			                	<c:when test="${c.name == filter_car.type }">
			                		<option selected>${c.name }</option>
			                	</c:when>
			                	<c:when test="${c.name != filter_car.type }">
			                		<option>${c.name }</option>
			                	</c:when>
			                	</c:choose>
							</c:forEach>
						  </select>
					  </div>
					  <div class="col-md-2 form-group">
						  <label for="sel1">Brands:</label>
						  <select name="brand" class="form-control" id="sel1">
						  	<option value="none" selected disabled hidden>
						          Select brand
						    </option>
						  	<c:forEach var="c" items="${brands }">
			                	<c:choose>
			                	<c:when test="${c.name == filter_car.type }">
			                		<option selected>${c.name }</option>
			                	</c:when>
			                	<c:when test="${c.name != filter_car.type }">
			                		<option>${c.name }</option>
			                	</c:when>
			                	</c:choose>
							</c:forEach>
						  </select>
					  </div>
					  
					  <div class="col-12">
					    <p>
					  </div>
					  <div class="col-12">
					    <button type="submit" class="btn btn-primary">Search</button>
					  </div>
					</form>
                    
                </div>
            </div>
		    
		    
		    
		    <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin/cars.htm?search=${filter_car.name }&min=${filter_car.min }&max=${filter_car.max }&type=${filter_car.type }&brand=${filter_car.brand }" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div>
				<tg:paging pagedListHolder="${pagedListHolder}"
					pagedLink="${pagedLink}" />
			</div>
			<div class="page-content page-container" id="page-content">
			    <div class="padding">
			        <div class="row container d-flex justify-content-center">
			            <div class="grid-margin stretch-card">
			                <div class="card">
			                    <div class="card-body">
			                        <h4 class="card-title">Basic Table</h4>
			                        <p class="card-description"> Basic table with card </p>
			                        <div class="table-responsive">
			                            <table class="table">
											<thead>
								                <tr class="bg-light">
								                    <th scope="col" width="5%">Status</th>
								                    <th scope="col" width="15%">#</th>
								                    <th scope="col" width="15%">Name</th>
								                    <th scope="col" width="15%">Type</th>
								                    <th scope="col" width="15%">Brand</th>
								                    <th scope="col" width="15%">Amount</th>
								                    <th scope="col" class="text-center" width="20%"><span>Revenue</span></th>
								                    <th colspan="2" scope="col" class="text-center" width="20%"><span>Action</span></th>
								                </tr>
								            </thead>
			                                <tbody>
								            	<c:forEach var="c" items="${pagedListHolder.pageList}">
													<tr>
									                    <td>
									                    <c:choose>
									                    <c:when test="${c.amount == 0 }">
									                    	<i class="fa fa-dot-circle-o text-danger"></i><span class="ms-1"></span></c:when>
									                    <c:when test="${c.amount > 0 }">
									                    	<i class="fa fa-check-circle-o green"></i><span class="ms-1"></span></c:when>
									                    </c:choose>
									                    </td>
									                    <td><img src="${c.img }" width="50"></td>
									                    <td>${c.name }</td>
									                    <td>${c.type.name }</td>
									                    <td>${c.brand.name }</td>
									                    <td>${c.amount }</td>
									                    <td class="text-center"><span class="fw-bolder">${c.price } VND</span></td>
														<td><a href="admin/cars/${c.name}.htm?linkEdit">
															<button type="button" rel="tooltip" class="btn btn-info btn-just-icon btn-sm" data-original-title="" title="">
							                                    <i class="material-icons">person</i>
							                                </button></a></td>
														<td><a href="admin/cars/${c.name}.htm?linkDelete role="button">
															<button type="button" rel="tooltip" class="btn btn-danger btn-just-icon btn-sm" data-original-title="" title="">
							                                    <i class="material-icons">close</i>
							                                </button></a></td>
													</tr>
												</c:forEach>
								                <tr>
								                    <td><i class="fa fa-dot-circle-o text-danger"></i><span class="ms-1"></span></td>
								                    <td><img src="https://i.imgur.com/VKOeFyS.png" width="50"></td>
								                    <td><label class="badge badge-success">Completed</label></td>
								                    <td><label class="badge badge-danger">Pending</label></td>
								                    <td><label class="badge badge-warning">In progress</label></td>
								                    <td><label class="badge badge-info">Fixed</label></td>
								                    <td class="text-center"><span class="fw-bolder">$0.99</span> <i class="fa fa-ellipsis-h ms-2"></i></td>
								                    <td><a href="admin/cars/${c.name}.htm?linkEdit">
														<button type="button" rel="tooltip" class="btn btn-info btn-just-icon btn-sm" data-original-title="" title="">
						                                    <i class="material-icons">person</i>
						                                </button></a></td>
													<td><a href="admin/cars/${c.name}.htm?linkDelete role="button">
														<button type="button" rel="tooltip" class="btn btn-danger btn-just-icon btn-sm" data-original-title="" title="">
						                                    <i class="material-icons">close</i>
						                                </button></a></td>
								                </tr>
								            </tbody>
			                            </table>
			                        </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			<p style="height:10">
			</p>
		</div>
    </div>
    <!--Container Main end-->
    

<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function(event) {

	const showNavbar = (toggleId, navId, bodyId, headerId) =>{
	const toggle = document.getElementById(toggleId),
	nav = document.getElementById(navId),
	bodypd = document.getElementById(bodyId),
	headerpd = document.getElementById(headerId)

	// Validate that all variables exist
	if(toggle && nav && bodypd && headerpd){
	toggle.addEventListener('click', ()=>{
	// show navbar
	nav.classList.toggle('show')
	// change icon
	toggle.classList.toggle('bx-x')
	// add padding to body
	bodypd.classList.toggle('body-pd')
	// add padding to header
	headerpd.classList.toggle('body-pd')
	})
	}
	}

	showNavbar('header-toggle','nav-bar','body-pd','header')

	/*===== LINK ACTIVE =====*/
	const linkColor = document.querySelectorAll('.nav_link')

	function colorLink(){
	if(linkColor){
	linkColor.forEach(l=> l.classList.remove('active'))
	this.classList.add('active')
	}
	}
	linkColor.forEach(l=> l.addEventListener('click', colorLink))

	// Your code to run since DOM is loaded and ready
	});
</script>
</body>
</html>


