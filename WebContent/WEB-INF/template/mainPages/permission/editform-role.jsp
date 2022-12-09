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
</style>
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/userpermission/userSubmit">
            <div class="form-control">
                <label for="username">登录名称</label>
                <input type="text" id="username" placeholder="请输入登录名称" name="username" value="${showUserRole.username}" readonly="readonly"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="nickname">用户昵称</label>
                <input type="text" id="nickname" placeholder="请输入用户昵称" name="nickname" value="${showUserRole.nickname}" readonly="readonly"/>
                <small>错误信息</small>
            </div>
            <select class="getRole" name="role">
            	<c:forEach var="userRole" items="${roleList}">
            		<c:if test="${userRole.title == showUserRole.role}">
            		<option value="${userRole.title}" selected="selected">${userRole.title}</option>
            		</c:if>
            		<c:if test="${userRole.title != showUserRole.role}">
            		<option value="${userRole.title}">${userRole.title}</option>
            		</c:if>
            	</c:forEach>
			</select>
            <button type="submit" onclick="subform()">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	  	const form = document.getElementById('form');
	    const nickname = document.getElementById('nickname');

	    function hideform() {
			window.location.href  = "${basePath}/userpermission/userTableList";
		}
	    
	    // Show input error message
	    function showError(input, message) {
	        const formControl = input.parentElement;
	        formControl.className = 'form-control error';
	        const small = formControl.querySelector('small');
	        small.innerText = message + '必填';
	    }

	    // Show success outline
	    function showSuccess(input) {
	        const formControl = input.parentElement;
	        formControl.className = 'form-control success';
	    }

	    // Check required fields
	    function checkRequired(inputArr) {
	        let isRequired = false;
	        inputArr.forEach(function(input) {
	            if (input.value.trim() === '') {
	                showError(input, getFieldName(input));
	                isRequired = true;
	            } else {
	                showSuccess(input);
	            }
	        });

	        return isRequired;
	    }

	    // Get fieldname
	    function getFieldName(input) {
	        const formControl = input.parentElement;
	        const label = formControl.querySelector('label');
	        return label.innerText;
	    }

	    // Event listeners
	    form.addEventListener('submit', function(e) {
	        e.preventDefault();

            form.submit();
	    });		
</script>
</html>