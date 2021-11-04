<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header class="header" id="header">
   <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
   <div class="header_img"> <img src="https://i.pinimg.com/originals/9d/5f/14/9d5f14a67e7e5a00101a6baf8a59fe7e.jpg" alt=""> </div>
</header>

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

