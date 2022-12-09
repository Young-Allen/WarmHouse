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
.showEditForm{
	position: absolute;	
	top: 0vh;
	left: 30vw;
	z-index: 1;
	height: 50vh;
}
td,
th{
	white-space: nowrap;
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
     			<th>房源标题</th>
     			<th>房型</th>
     			<th>面积</th>
     			<th>朝向</th>
     			<th>楼层</th>
     			<th>总楼层</th>
     			<th>建造年份</th>
     			<th>所属区域</th>
     			<th>产权</th>
     			<th>装修</th>
     			<th>状态</th>
     			<th>操作</th>
   			</tr>
		</thead>
		<c:forEach items="${sessionScope.houseinfoList}" var="houseinfo">
			<tr>
				<td>${houseinfo.id}</td>
				<td>${houseinfo.title}</td>
				<td>${houseinfo.suiteRoom}室${houseinfo.suiteHall}厅${houseinfo.suiteBathroom}卫</td>
				<td>${houseinfo.area}</td>
				<td>${houseinfo.direction}</td>
				<td>${houseinfo.floor}</td>
				<td>${houseinfo.totalFloor}</td>
				<td>${houseinfo.birth}</td>
				<td>${houseinfo.housebelong}</td>
				<td>${houseinfo.propertyrights}</td>
				<td>${houseinfo.decoration}</td>
				<td>${houseinfo.housestatus}</td>
				<td>
					<button><a href="${basePath}/information/housePhotoList?code=${houseinfo.code}" target="content-box">照片</a></button>
					<button onclick="editInfo('${houseinfo.code}')"><a href="${basePath}/information/houseInfoEdit?code=${houseinfo.code}" target="content-box">编辑</a></button>
					<button onclick="deleteInfo('${houseinfo.code}')">删除</button>
				</td>
			</tr>
		</c:forEach>
     </table>
     
     <script>
	  	function editInfo(code){
			/* window.location.href  = "${basePath}/information/houseInfoEdit?code="+code;  */
		}
 	     
     	function deleteInfo(code){
     		let r = confirm("确认删除此条记录？");
     		if (r == true){
    			window.location.href  = "${basePath}/information/houseInfoDelete?code="+code; 
     		}
     	} 
     	
     	function hideModal(){
     		document.querySelector('.modal').style.display = 'none';
     	}
     </script>
</body>
</html>