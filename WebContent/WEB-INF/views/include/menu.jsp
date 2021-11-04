<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
<!-- <div id="loading">
</div>
<script>
  $(window).on('load', function () {
    $('#loading').hide();
    $("div#main").removeClass("hidden");
  }) 
</script> -->  <!--set div class hidden -->