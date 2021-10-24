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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

<script src="<c:url value='resources/assets/scripts/script.js'/>"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body id="body-pd">
    <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
        <div class="header_img"> <img src="https://i.pinimg.com/originals/9d/5f/14/9d5f14a67e7e5a00101a6baf8a59fe7e.jpg" alt=""> </div>
    </header>
    <div class="l-navbar" id="nav-bar">
	    <nav class="nav">
	        <div>
	            <a href="#" class="nav_logo"> <i class="bx bx-layer nav_logo-icon"></i> <span class="nav_logo-name">BBBootstrap</span> </a>
	            <div class="nav_list">
	                <a href="#" class="nav_link"> <i class="bx bx-grid-alt nav_icon"></i> <span class="nav_name">Dashboard</span> </a>
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
    <div class="height-100 bg-light">
        <h4>Main Components</h4>
		<div class="container mt-5 px-2">
		    <div class="mb-2 d-flex justify-content-between align-items-center">
		        <div class="position-relative"> <span class="position-absolute"><i class="fa"></i></span> <input class="form-control w-100" placeholder="Search by order#, name..."> </div>
		        <div class="px-2"> <span>Filters <i class="fa fa-angle-down"></i></span> <i class="fa fa-ellipsis-h ms-3"></i> </div>
		    </div>
		    <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin/cars.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div>
					<tg:paging pagedListHolder="${pagedListHolder}"
						pagedLink="${pagedLink}" />
				</div>
		    <div class="table-responsive">
		        <table class="table table-responsive table-borderless">
		            <thead>
		                <tr class="bg-light">
		                    <th scope="col" width="5%"><input class="form-check-input" type="checkbox"></th>
		                    <th scope="col" width="5%">Status</th>
		                    <th scope="col" width="15%">#</th>
		                    <th scope="col" width="15%">Name</th>
		                    <th scope="col" width="15%">Type</th>
		                    <th scope="col" width="15%">Brand</th>
		                    <th scope="col" width="15%">Amount</th>
		                    <th scope="col" class="text-end" width="20%"><span>Revenue</span></th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="c" items="${pagedListHolder.pageList}">
							<tr>
								<th scope="row"><input class="form-check-input" type="checkbox"></th>
			                    <td><i class="fa fa-check-circle-o green"></i><span class="ms-1"></span></td>
			                    <td><img src="${c.img }" width="50"></td>
			                    <td>${c.name }</td>
			                    <td>${c.type.name }</td>
			                    <td>${c.brand.name }</td>
			                    <td>${c.amount }</td>
			                    <td class="text-end"><span class="fw-bolder">${c.price } VND</span></td>
								<td><a href="admin/cars/${c.name}.htm?linkEdit"><img
										width="50" height="40"
										src="<c:url value="/resources/assets/images/edit.png"/>"></a></td>
								<td><a href="admin/cars/${c.name}.htm?linkDelete role="button"><img
										width="40" height="40"
										src="<c:url value="/resources/assets/images/delete.png"/>"></a></td>
							</tr>
						</c:forEach>
		                <tr>
		                    <th scope="row"><input class="form-check-input" type="checkbox"></th>
		                    <td><i class="fa fa-dot-circle-o text-danger"></i><span class="ms-1"></span></td>
		                    <td><img src="https://i.imgur.com/VKOeFyS.png" width="50"></td>
		                    <td>Name</td>
		                    <td>Type</td>
		                    <td>Brand</td>
		                    <td>Amount</td>
		                    <td class="text-end"><span class="fw-bolder">$0.99</span> <i class="fa fa-ellipsis-h ms-2"></i></td>
		                </tr>
		            </tbody>
		        </table>
		    </div>
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


