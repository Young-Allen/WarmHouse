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
<style>
.getRole{
	width: 150px;
	height: 33px;
	border: 1px solid #ccc;
}
.getRole:active{
	border: 1px solid #409eff;
}
.getRole option{
	text-align: center;
	fontsize: 15px;
}
</style>
<body>

	<div class="user-manage">
	    <div class="searchUser">
	    	<form class="listuser" action="${basePath}/systable/systb" method="post" target="userTableList">
	    		<span>系统表</span>
	         	<select class="getRole" name="tableName" onChange="submitSearchUser()">
	         	<c:forEach items="${sessionScope.totalTable}" var="totalTable">
         			<option value="${totalTable.tableTitle}">${totalTable.tableName}</option>
				</c:forEach>
				</select>
	   			<span>代码</span><input placeholder="请输入代码" type="text" class="getCode" name="code">
	   			<span>标题</span><input placeholder="请输入标题" type="text" class="getTitle" name="title">
	   			
	         	<input type="text" class="pageNum" name="pageNum" style="display:none" value="">
	         	<input type="text" class="pageSize" name="pageSize" style="display:none" value="">
	         	<span class="searchUserBtn" onclick="submitSearchUser()"><a target="usertable">查询</a></span>
	         	<span class="searchUserBtn" onclick="addSystemTable()">添加</span>
	         	<span class="searchUserBtn" onclick="resetSearchUser()">重置</span>
	    	</form>
	    </div>	  
	    
	<div class="user-table" >
   		<iframe id="userframe" name="userTableList" src="systemtable" width="100%" height="100%" frameborder="0"  scrolling="yes"></iframe>
	</div>    
	
    <script>
	    function submitSearchUser(){
			document.querySelector('.pageNum').value = 1;
			document.querySelector('.pageSize').value = 10;
			document.querySelector('.listuser').submit();
	    };
	   
	    function resetSearchUser(){
	    	document.querySelector('.getCode').value = "";
	    	document.querySelector('.getTitle').value = "";
	    	document.querySelector('.getRole').options[0].selected = true
	    };
	    
	   	function addSystemTable(){
	   		window.location.href = "${basePath}/systable/systbAdd";
	   	};
	    
    </script>
</body>

</html>