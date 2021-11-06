<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login Template</title>
  <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/assets/css/login.css">
</head>
<body>
  <div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-6 login-section-wrapper">
          <div class="brand-wrapper">
            <img src="resources/assets/images/logo.png" alt="logo" class="logo">
            <a style="font-family: 'Lobster', cursive; color: black" href="" class="navbar-brand">IDRISCAR</a>
          </div>
          <div class="login-wrapper my-auto">
            <h1 class="login-title">Log in</h1>
            <form:form action="login.htm" method="post" modelAttribute="admin">
              <div class="form-group">
                <label for="username">Username</label>
                <form:input type="text" path="username" id="username" class="form-control" placeholder="enter your username" />
                <form:errors path="username" element="errors"></form:errors>
              </div>
              <div class="form-group mb-4">
                <label for="pass">Password</label>
                <form:input type="password" path="pass" id="pass" class="form-control" placeholder="enter your passsword" />
                <form:errors path="pass" element="errors"></form:errors>
              </div>
              <input id="login" class="btn btn-block login-btn" type="submit" value="Login">
            </form:form>
            ${message }
            <p>
            <a href="#!" class="forgot-password-link">Forgot password?</a>
            <p class="login-wrapper-footer-text">Don't have an account? <a href="#!" class="text-reset">Register here</a></p>
          </div>
        </div>
        <div class="col-sm-6 px-0 d-none d-sm-block">
          <img src="resources/assets/images/login.jpg" alt="login image" class="login-img">
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
