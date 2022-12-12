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
.edit-box{
	margin: 5px 30vw;
}
.headimg {
	position: relative;
	margin-left: 60px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    vertical-align: middle;
    transition: all 0.6s;
}
.headimg:hover{
    transform: scale(1.1);
}
</style>
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/user/userHeadimgSubmit" traget="_blank" enctype="multipart/form-data">
        	<div class="form-control">
        		<c:if test="${userHeadimg == null}">
               		<img class="headimg" src="${basePath}/img/0.jpg">
   				</c:if>
   				<c:if test="${userHeadimg != null}">
        			<img  class="headimg" src="data:image/jpg;base64,${userHeadimg.dataBase64}"/>
   				</c:if>
	        </div>        
        	<div class="form-control">
        		<input name="file1" type="file"/>
	        </div>
            <div class="form-control">
                <label for="username">登录名称</label>
                <input type="text" id="username" readonly="readonly" name="username" value="${username}" readonly="readonly"/>
                <small>错误信息</small>
            </div>
            <button type="submit">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	    function hideform() {
			window.location.href  = "${basePath}/user/welcome";
		}
</script>
</html>