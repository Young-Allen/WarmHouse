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
a{
	text-decoration:none;
	color:#333;
}
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
td,
th{
	white-space: nowrap;
}
.searchUserBtn{
    font-size: 17px;
    font-weight: 700;
    margin: 5px 8px;
}
.searchUserBtn:hover {
    background-color: rgb(47,146,249);
}
.searchUserBtn {
    display: inline-block;
    width: 90px;
    height: 40px;
    line-height: 40px;
    background-color: rgb(64,158,255);
    color: white;
    text-align: center;
    border-radius: 10px;
}
.searchUserBtn a{
	color: white;
}
</style>
<body>
	<c:if test="${show == 1}">
		<div class="modal">
			<h3 style="text-align: center">${msg}</h3>
			<div class="con" onclick="hideModal()">确认</div>
		</div>
	</c:if>
	<table width="90%" height="100%" class="usertable">
     	<thead>
     		<tr>
     			<th>ID</th>
     			<th>照片编号</th>
     			<th>照片标题</th>
     			<th>位置</th>
     			<th>描述</th>
     			<th>操作</th>
   			</tr>
		</thead>
		<c:forEach items="${housePhotoList}" var="housephoto">
			<tr>
				<td>${housephoto.id}</td>
				<td>${housephoto.photocode}</td>
				<td>${housephoto.title}</td>
				<td>${housephoto.location}</td>
				<td>${housephoto.description}</td>
				<td>
				
					<button><a href="${basePath}/houseimage/check?photocode=${housephoto.photocode}" target="_blank">查看</a></button>
					<button><a href="${basePath}/houseimage/download?photocode=${housephoto.photocode}" target="_blank">下载</a></button>
					<button><a href="${basePath}/houseimage/edit?username=${houseinfoUsername}&photocode=${housephoto.photocode}&code=${code}">编辑</a></button>
					<button onclick="deletePicture('${housephoto.photocode}')">删除</button>
				</td>
			</tr>
		</c:forEach>
     </table>
     <span class="searchUserBtn" onclick="addHousePhoto()">添加</span>
     <span class="searchUserBtn"><a href="${basePath}/information/listinfo?username=${houseinfoUsername}&pageNum=1&pageSize=10" target="content-box">返回</a></span>
     
     <script>
     	function addHousePhoto(){
			window.location.href  = "${basePath}/information/housePhotoEdit?username=${houseinfoUsername}&code=${code}"; 
     	}
     	function deletePicture(photocode){
     		let r = confirm("确认删除此条记录？");
     		if (r == true){
    			window.location.href  = "${basePath}/houseimage/delete?username=${houseinfoUsername}&code=${code}&photocode="+photocode; 
     		}
     	}
     </script>
</body>
</html>