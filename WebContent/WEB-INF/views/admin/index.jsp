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
      <link rel="stylesheet" href="<c:url value='resources/assets/css/admin.css'/>">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
      <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
      
      <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    
    <script src="https://kit.fontawesome.com/41a8d7d896.js"></script>
    <style>
    .image-cover {
	    position: relative;
	}
		
	.image-cover::before {
	    content: "";
	    position: absolute;
	    top: 0;
	    left: 0;
	    right: 0;
	    bottom: 0;
	    /* Adjust the color values to achieve desired darkness */
	    background: linear-gradient(to top, rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0.25));
	}
    </style>
   </head>

    <body id="body-pd" class=" bg-light">
        <c:if test="${sho == 1}">
            <script>
                window.onload = function () {
                    $("#mymodal").modal("show");
                    //document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'
                };
            </script>
        </c:if>
        <c:if test="${not empty orderAccept}">
            <script>
                window.onload = function () {
                    $("#orderacceptmodal").modal("show");
                };
            </script>
        </c:if>
        <c:if test="${not empty orderDeny}">
            <script>
                window.onload = function () {
                    $("#orderdenymodal").modal("show");
                };
            </script>
        </c:if>

        
		<header class="header" id="header">
		   <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
		   <div class="header_img"> <img src="https://i.pinimg.com/originals/9d/5f/14/9d5f14a67e7e5a00101a6baf8a59fe7e.jpg" alt=""> </div>
		</header>
		<div class="l-navbar" id="nav-bar">
		   <nav class="nav">
		      <div>
		         <a href="admin/" class="nav_logo"> <i class="bx bx-layer nav_logo-icon"></i> <span class="nav_logo-name">BBBootstrap</span> </a>
		         <div class="nav_list">
		            <a href="admin/" class="nav_link active"> <i class="bx bx-grid-alt nav_icon"></i> <span class="nav_name">Dashboard</span> </a>
		            <a href="admin/cars/" class="nav_link"> <i class="bx bx-user nav_icon"></i> <span class="nav_name">Cars</span> </a>
		            <a href="admin/types/" class="nav_link"> <i class="bx bx-message-square-detail nav_icon"></i> <span class="nav_name">Category</span> </a>
		            <a href="admin/brands/" class="nav_link"> <i class="bx bx-bookmark nav_icon"></i> <span class="nav_name">Brand</span> </a> 
		            <a href="admin/stats/" class="nav_link"> <i class="bx bx-bar-chart-alt-2 nav_icon"></i> <span class="nav_name">Stats</span> </a>
		         </div>
		      </div>
		      <a href="#" class="nav_link"> <i class="bx bx-log-out nav_icon"></i> <span class="nav_name">SignOut</span> </a>
		   </nav>
		</div>
		<!--Container Main start-->
		<div class=" bg-light">
		   <h4>Main Components</h4>
		   <ul></ul>
			<c:if test="${not empty message}">
			<div class="alert alert-primary mt-5" role="alert">
			  ${message }
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			</c:if>
			
			
		<div class="content mt-5">
			<div class="row col-12">
				<div class="col-sm-9 login-section-wrapper">
				<div class="row">
                  	<div class="col-sm-2">
                  		<div class="mb-2 d-flex justify-content-between align-items-center">
		                     <div class="px-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"> <span>Filters <i class="fa fa-angle-down"></i></span></div>
		                  </div>
                  	</div>
                  	<div class="col-sm-1">
                  		<a href="admin/?clear=1"><button type="submit" class="btn badge badge-info mt-1">Clear filter</button></a>
                  	</div>
                  
                  
                  </div>
					
                  <div class="collapse container" id="collapseExample">
                     <div class="row">
                        <form:form action="admin/" class="row g-3" modelAttribute="filter_order">
                           <div class="col-md-3">
                              <label for="inputAddress" class="form-label">ID</label>
                              <form:input path="idFilter" name="id" type="text" class="form-control" id="inputAddress" placeholder="Id"/>
                           </div>
                           <div class="col-md-9">
                              <label for="inputAddress" class="form-label">Customer</label>
                              <form:input path="customerFilter" name="customer" type="text" class="form-control" id="inputAddress" placeholder="Customer name..." />
                           </div>
                           <div class="col-md-5">
                              <label for="inputEmail4" class="form-label">Email</label>
                              <form:input path="emailFilter" name="email" type="text" class="form-control" id="min_price"  placeholder="Email" />
                           </div>
                           <div class="col-md-5">
                              <label for="inputPassword4" class="form-label">Phone</label>
                              <form:input path="phoneFilter" name="phone" type="text" class="form-control"   placeholder="Phone" />
                           </div>
                           <div class="col-md-2 form-group">
                              <label for="sel1">Status:</label>
                              <form:select path="statusFilter" class="form-control">
                                 <form:options items="${status}" />
                              </form:select>
                           </div>
                           <div class="col-12">
                              <button type="submit" class="btn btn-primary">Search</button>
                           </div>
                           <div class="col-12">
                              <p>
                           </div>
                        </form:form>
                        
                     </div>
                  </div>
				</div>
				<div class="col-sm-3 login-section-wrapper">
					<jsp:useBean id="pagedListHolder" scope="request"
			           type="org.springframework.beans.support.PagedListHolder" />
			        <c:url value="admin/" var="pagedLink">
			           <c:param name="p" value="~" />
			        </c:url>
			        <div>
			           <tg:paging pagedListHolder="${pagedListHolder}"
			              pagedLink="${pagedLink}" />
			        </div>
				</div>
			</div>
		</div>
        

        <div class="card mt-1">
            <div class="card-body">
                <h4 class="card-title">Basic Table</h4>
                <p class="card-description"> Basic table with card </p>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" width="10%">ID</th>
                                <th scope="col" width="15%">Car</th>
                                <th scope="col" width="10%">Customer</th>
                                <th scope="col" width="10%">Email</th>
                                <th scope="col" width="10%">Phone</th>
                                <th scope="col" width="10%">Date</th>
                                <th scope="col" width="10%">Amount</th>
                                <th scope="col" class="text-center" width="15%"><span>Total</span></th>
                                <th scope="col" width="10%">Status</th>
                                <th colspan="2" scope="col" class="text-center" width="20%"><span>Action</span></th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="o" items="${pagedListHolder.pageList}">
                               <tr>
                                  
                                  <td>${o.id }</td>
                                  <td>${o.car.name }</td>
                                  <td>${o.customer }</td>
                                  <td>${o.email }</td>
                                  <td>${o.phone }</td>
                                  <td>${o.datebuy }</td>
                                  <td>${o.amount }</td>
                                  <td class="text-center"><span class="fw-bolder">${o.total } VND</span></td>
                                  <td>
                                     <c:choose>
                                        <c:when test="${o.stat == 2}">
                                           <label class="badge badge-danger">Denied</label>
                                        </c:when>
                                        <c:when test="${o.stat == -1 }">
                                           <label class="badge badge-warning">Pending</label>
                                        </c:when>
                                        <c:when test="${o.stat == 1 }">
                                           <label class="badge badge-success">Accepted</label>
                                        </c:when>
                                     </c:choose>
                                  </td>
                                  <c:choose>
                                  <c:when test="${o.stat == -1 }">
                                  <td><a href="admin/${o.id }.htm?linkAccept&p=~">
                                   <button type="button" rel="tooltip" class="btn btn-info btn-just-icon btn-sm">
                                   <i class="material-icons">Accept</i>
                                   </button></a>
                                  </td>
                                  <td><a href="admin/${o.id }.htm?linkDeny&p=~">
                                   <button type="button" rel="tooltip" class="btn btn-danger btn-just-icon btn-sm">
                                   <i class="material-icons">Deny</i>
                                   </button></a>
                                  </td>
                                  </c:when>
                                  <c:when test="${o.stat != -1 }">
                                  <td> </td>
                                  <td> </td>
                                  </c:when>
                                  </c:choose>
                               </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
		</div>

		
            
        
        
        
        
        <div class="modal fade" id="orderacceptmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Order</h5>
                    </div>
                    <div class="modal-body">
						<div class="image-cover card bg-dark text-white">
						  <img class="card-img" src="${orderAccept.car.img }" alt="Card image">
						  <div class="card-img-overlay mt-5">						  
						    <h5 class="card-title">Car: ${orderAccept.car.name }</h5>
						    <p class="card-text">Customer: ${orderAccept.customer }</p>
						    <p class="card-text">Email: ${orderAccept.email }</p>
						    <p class="card-text">Phone: ${orderAccept.phone }</p>
						    <p class="card-text">Amount: ${orderAccept.amount }</p>
						    
						  </div>
						</div>
						<p class="card-text mt-2">Choose an expected date</p>
                        <form action="admin/?p=~">
                        	<input name="idorderaccept" type="hidden" class="form-control" value="${orderAccept.id }"/>
                        	<input name="expecteddate" id="datepicker" width="276" required/>
                        	<button name="btnAccept" type="submit" rel="tooltip" class="btn mt-2 btn-info btn-just-icon btn-sm">
	                        <i class="material-icons">Accept</i>
	                        </button>
                        </form>
					    <script>
					        $('#datepicker').datepicker({
					            uiLibrary: 'bootstrap4'
					        });
					        $.datepicker.formatDate('dd-mm-yy', dateTypeVar);
					    </script>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="orderdenymodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Order</h5>
                    </div>
                    <div class="modal-body">
						<div class="image-cover card bg-dark text-white">
						  <img class="card-img" src="${orderDeny.car.img }" alt="Card image">
						  <div class="card-img-overlay mt-5">						  
						    <h5 class="card-title">Car: ${orderDeny.car.name }</h5>
						    <p class="card-text">Customer: ${orderDeny.customer }</p>
						    <p class="card-text">Email: ${orderDeny.email }</p>
						    <p class="card-text">Phone: ${orderDeny.phone }</p>
						    <p class="card-text">Amount: ${orderDeny.amount }</p>
						    
						  </div>
						</div>
                        <form action="admin/?p=~" method="post">
                        	<input name="idorderdeny" type="hidden" class="form-control" value="${orderDeny.id }"/>
                        	<label class="mt-1">Reason:</label>
                        	<textarea name="disc" class="form-control mt-1" id="exampleFormControlTextarea1" rows="3"></textarea>
                        	<button name="btnDeny" type="submit" rel="tooltip" class="btn mt-2 btn-danger btn-just-icon btn-sm">
	                        <i class="material-icons">Deny</i>
	                        </button>
                        </form>
					    <script>
					        $('#datepicker').datepicker({
					            uiLibrary: 'bootstrap4'
					        });
					    </script>
                    </div>
                </div>
            </div>
        </div>
        
        
        <div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Order</h5>
                    </div>
                    <div class="modal-body">
                        <form action="../order.htm?name=${car.name }" method="post">
                            <div class="form-group">
                                <label for="inputAddress2">Name</label>
                                <input type="text" class="form-control" id="inputAddress2" value="${car.name}" />
                            </div>
                            <div class="form-group">
                                <label for="inputAddress">Image</label>
                                <input type="text" class="form-control" id="inputAddress" />
                            </div>
                            <div class="form-group">
                                <label for="inputAddress2">Video</label>
                                <input type="text" class="form-control" id="inputAddress2" />
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="inputEmail4">Amount</label>
                                    <input type="email" class="form-control" id="inputEmail4" />
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputPassword4">Price</label>
                                    <input type="password" class="form-control" id="inputPassword4" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Discription</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="sel1">Types:</label>
                                    <select name="type" class="form-control" id="sel1">
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
                                <div class="form-group col-md-4">
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
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
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
         
         function openModalFuntion(){
         	$('#exampleModalCenter').modal('show')
         }
         linkColor.forEach(l=> l.addEventListener('click', colorLink))
         
         // Your code to run since DOM is loaded and ready
         });
      </script>

      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
