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
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/user-manage.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/paging.css">
    <script type="text/javascript" src="${basePath}/js/qu.js"></script>
    <script type="text/javascript" src="${basePath}/js/paging.js"></script>
</head>
<body>
	<div class="user-manage">
	    <div class="searchUser">
	    	<form class="listuser" action="${basePath}/usermanage/usertb" method="post" target="userTableList">
	   			<span>登录名称</span><input placeholder="请输入登录名称" type="text" class="getUsername" name="username">
	         	<span>用户昵称</span><input placeholder="请输入用户昵称" type="text" class="getNickname" name="nickname">
	         	<span>联系方式</span><input placeholder="请输入联系方式" type="text" class="getPhone" name="phone">
	         	<input type="text" class="pageNum" name="pageNum" style="display:none" value="">
	         	<input type="text" class="pageSize" name="pageSize" style="display:none" value="">
	         	<span class="searchUserBtn" onclick="submitSearchUser()"><a target="usertable">查询</a></span>
	         	<span class="searchUserBtn" onclick="addUser()">添加</span>
	         	<span class="searchUserBtn" onclick="resetSearchUser()">重置</span>
	    	</form>
	    </div>	  
	    
	<div class="user-table" >
   		<iframe id="userframe" name="userTableList" src="userTable" width="100%" height="100%" frameborder="0"  scrolling="yes"></iframe>
	</div>    
	
    <script>
	    function submitSearchUser(){
			document.querySelector('.pageNum').value = 1;
			document.querySelector('.pageSize').value = 10;
			document.querySelector('.listuser').submit();
	    };
	   
	    function resetSearchUser(){
	    	document.querySelector('.getUsername').value = "";
	    	document.querySelector('.getNickname').value = "";
	    	document.querySelector('.getPhone').value = "";
	    }
	    
	    function addUser(){
	    	window.location.href  = "${basePath}/usermanage/userAdd";
	    }
    </script>
</body>

</html>