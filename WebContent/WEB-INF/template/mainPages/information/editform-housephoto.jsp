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
    <link rel="stylesheet" type="text/css" href="${basePath}/css/editForm.css">
</head>
<style>
body,
html {
	align-items: baseline;
    -moz-user-select: none;
    /*火狐*/
    /*选中文字时避免出现蓝色背景*/
    -webkit-user-select: none;
    /*webkit浏览器*/
    /*选中文字时避免出现蓝色背景*/
    -ms-user-select: none;
    /*IE10*/
    /*选中文字时避免出现蓝色背景*/
    user-select: none;
    /*选中文字时避免出现蓝色背景*/
}
.edit-box{
	width: auto;
	margin: 50px 20vw;
	padding: 20px;
}
.listuser span {
    font-size: 14px;
    font-weight: 700;
    color: black;
    margin: 0px 8px;
}
.listuser input {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 6px;
    width: 200px;
    font-size: 14px;
    font-family: "Microsoft soft";
}
.listuser .getphotocode,
.listuser .getDescription{
	width: 495px;
}
.listuser .getTitle{
	width: 300px;
}
.listuser .getDecoration{
    width: 100px;
    height: 33px;
    border: 1px solid #ccc;
}
.listuser .Btn{
	cursor: pointer;
	position: relative;
	display: inline-block;
	margin-top: 10px;
	left: 355px;
    width: 100px;
    height: 30px;
    line-height: 30px;
    background-color: rgb(64,158,255);
    text-align: center;
    border-radius: 10px;
    color: #fff;
}

</style>
    <div class="edit-box">
    <h2>信息录入</h2>
    <c:if test="${editflag == 1}">
        <form class="listuser" action="${basePath}/houseimage/update" method="post" target="content-box" enctype="multipart/form-data">
    </c:if>
    <c:if test="${editflag != 1}">
        <form class="listuser" action="${basePath}/houseimage/add" method="post" target="content-box" enctype="multipart/form-data">
    </c:if>
    
        	<input type="text" style="display: none" name="username" value="${houseinfoUsername}"><br>
    		
    		<c:if test="${editflag == 1}">
    			<input type="text" style="display: none" name="code" value="${housephoto.code}"><br>
    		</c:if>
    		<c:if test="${editflag != 1}">
    		    <input type="text" style="display: none" name="code" value="${code}"><br>
    		</c:if>
    		
        	<c:if test="${editflag == 1}">
    		<span>照片编号：</span><input type="text" class="getphotocode" name="photocode" value="${housephoto.photocode}" readonly="readonly"><br>
        	</c:if>
        	<c:if test="${editflag != 1}">
        	<span>照片编号：</span><input type="text" class="getphotocode" name="photocode" value="${housephoto.photocode}"><br>
        	</c:if>
        
    		<span>照片标题：</span><input type="text" class="getTitle" name="title" value="${housephoto.title}">
    		
    		<span>展示位置：</span>
         	<select class="getDecoration" name="location">
        			<c:forEach items="${houselocationList}" var="housesLocation">
        			<c:if test="${housesLocation.code == 00 && housesLocation.title == showHouseInfoTable.housestatus}">
        				<option value="" selected="selected">${housesLocation.title}</option>
        			</c:if>
        			<c:if test="${housesLocation.code == 00 && housesLocation.title != showHouseInfoTable.housestatus}">
        				<option value="">${housesLocation.title}</option>
        			</c:if>
        			<c:if test="${housesLocation.code != 00 && housesLocation.title == showHouseInfoTable.housestatus}">
        				<option value="${housesLocation.title}" selected="selected">${housesLocation.title}</option>
        			</c:if>
        			<c:if test="${housesLocation.code != 00 && housesLocation.title != showHouseInfoTable.housestatus}">
        				<option value="${housesLocation.title}">${housesLocation.title}</option>
        			</c:if>
				</c:forEach>
			</select><br>
			
    		<span>详细描述：</span><input type="text" class="getDescription" name="description" value="${housephoto.description}"><br>
         	<span>展示图片：</span><input name="file1" type="file"/><br>
		
			
			<span class="Btn" onclick="submitSearchUser()"><a target="usertable">提交</a></span>
         	<span class="Btn" onclick="cancel()"><a target="content-box">取消</a></span>
    	</form>
    </div>
</body>
<script>
	   	function submitSearchUser(){
	   		document.querySelector('.listuser').submit();
	   	}
	   	
	   	function cancel(){
	   		window.location.href  = "${basePath}/information/housePhotoList?username=${houseinfoUsername}&code=${code}";
	   	}
</script>
</html>