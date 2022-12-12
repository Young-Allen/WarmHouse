<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="scheme" value="${pageContext.request.scheme}" />
<c:set var="serverName" value="${pageContext.request.serverName}" />
<c:set var="serverPort" value="${pageContext.request.serverPort}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="basePath" value="${scheme}://${serverName}:${serverPort}${contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Not Found</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/404.css">
</head>
<style>

</style>
<body>
	<h1>404 Error Page</h1>
	<section class="error-container">
	  <span>4</span>
	  <span><span class="screen-reader-text">0</span></span>
	  <span>4</span>
	</section>
	<div class="link-container">
	  <a target="_blank" href="${basePath}/user/index" class="more-link">Back Home</a>
	</div>
</body>
</html>