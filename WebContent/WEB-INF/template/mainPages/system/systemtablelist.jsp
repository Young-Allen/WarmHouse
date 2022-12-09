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
	<title>user table</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/user-manage.css">
 	<link rel="stylesheet" type="text/css" href="${basePath}/css/paging.css">
    <script type="text/javascript" src="${basePath}/js/qu.js"></script>
    <script type="text/javascript" src="${basePath}/js/paging.js"></script>
</head>
<style>
.modal{
	position: absolute;	
	width: 250px;
	height: 120px;
	top: 30vh;
	left: 30vw;
	background-color: rgb(255,255,255);
	border-radius: 10px;
	box-shadow: 3px 4px 11px -4px black;
}
.con{
	top: 10px;
    width: 60px;
    height: 30px;
    line-height: 30px;
    background-color: rgb(64,158,255);
    color: white;
    text-align: center;
    border-radius: 10px;
    margin: auto;
}
.con:hover {
	background-color: rgb(47,146,249);
}
.con:active {
	background-color: rgb(64,158,255);
}
.showEditForm{
	position: absolute;	
	top: 0vh;
	left: 30vw;
	z-index: 1;
	height: 50vh;
}
</style>
<body>
	<c:if test="${show == 1}">
		<div class="modal">
			<h3 style="text-align: center">${msg}</h3>
			<div class="con" onclick="hideModal()">确认</div>
		</div>
	</c:if>
	<table width="100%" height="100%" class="usertable">
     	<thead>
     		<tr>
     			<th>ID</th>
     			<th>代码</th>
     			<th>标题</th>
     			<th>操作</th>
   			</tr>
		</thead>
		<c:forEach items="${sessionScope.systemTableList}" var="systemTableList">
			<tr>
				<td>${systemTableList.id}</td>
				<td>${systemTableList.code}</td>
				<td>${systemTableList.title}</td>
				<td>
					<button onclick="editUser('${systemTableList.code}','${systemTableList.title}')">编辑</button>
					<button onclick="deleteSys('${systemTableList.code}')">删除</button>
				</td>
			</tr>
		</c:forEach>
     </table>
     
     <script>
	  	function editUser(code,title){
	  		window.location.href  = "${basePath}/systable/systemTableEdit?tableName=${sessionScope.systemTableInfo.tableName}&code="
	  				+code+"&title="+title;
		}
 	     
     	function deleteSys(code){
     		let r = confirm("确认删除此条记录？");
     		if (r == true){
    			window.location.href  = "${basePath}/systable/systemTableDelete?tableName=${sessionScope.systemTableInfo.tableName}&code="+code; 
     		}
     	}
     	
     	function hideModal(){
     		document.querySelector('.modal').style.display = 'none';
     	}
     </script>
</body>
</html>