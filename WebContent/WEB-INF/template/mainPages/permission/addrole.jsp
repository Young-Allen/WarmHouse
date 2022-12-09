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
.getRole{
	width: 280px;
	height: 40px;
	border: 1px solid #ccc;
	margin-top: 0px;
	margin-bottom: 25px;
	font-size: 15px;
}
.getRole:active{
	border: 1px solid #409eff;
}
.getRole option{
	text-align: center;
}
.edit-box{
	margin: 5px 30vw;
}
.form-control {
    margin-bottom: 0px;
    padding-bottom: 0px;
}
.form-control lable{
	color: #777;
}
</style>
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/userpermission/addUserRoleSubmit">
        	<h2>添加权限</h2>
            <div class="form-control">
                <label for="username">用户</label>
                 <select class="getRole" id="getRole" name="username">
	            	<c:forEach items="${allUser}" var="auser">
	          			<option value="${auser.username}">${auser.username}</option>
	            	</c:forEach>
				</select>
            </div>
            
            <div class="form-control">
	            <label for="username">角色</label>
	            <select class="getRole" name="role">
	            	<c:forEach items="${roleList}" var="userRole">
	          			<option value="${userRole.title}">${userRole.title}</option>
	            	</c:forEach>
				</select>
			</div>
            <button type="submit" onclick="subform()">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	  	const form = document.getElementById('form');
	    const nickname = document.getElementById('nickname');
	    
	    function hideform() {
			window.location.href  = "${basePath}/userpermission/listuser?pageNum=1&pageSize=10";
		}
</script>
</html>