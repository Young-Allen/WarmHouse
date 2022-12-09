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
.form-control label{
	display: none;
}
.edit-box{
	margin: 5px 30vw;
}
</style>
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/usermanage/userSubmit">
            <div class="form-control">
                <label for="username">登录名称</label>
                <input type="text" id="username" name="username" value="${tempUser.username}" readonly="readonly"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="nickname">用户昵称</label>
                <input type="text" id="nickname" placeholder="请输入用户昵称" name="nickname" value="${tempUser.nickname}"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="phone">联系方式</label>
                <input type="text" id="phone" placeholder="请输入联系方式" name="phone" value="${tempUser.phone}"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="password">密码</label>
                <input type="password" id="password" placeholder="请输入密码"  name="password" />
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="password2">确认密码</label>
                <input type="password" id="password2" placeholder="请再次输入密码" />
                <small>错误信息</small>
            </div>
            <button type="button" onclick="subform()">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	window.onload=function(){
		const form = document.getElementById('form');
	    const nickname = document.getElementById('nickname');
	    const phone = document.getElementById('phone');
	    const password = document.getElementById('password');
	    const password2 = document.getElementById('password2');
	}
	    function hideform() {
			window.location.href  = "${basePath}/usermanage/userTableList";
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

	    // Check email is valid
	    function checkEmail(input) {
	        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	        if (re.test(input.value.trim())) {
	            showSuccess(input);
	        } else {
	            showError(input, '邮箱不合法');
	        }
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

	    // Check passwords match
	    function checkPasswordsMatch(input1, input2) {
	        if (input1.value !== input2.value) {
	            showError(input2, '密码不匹配');
	            return false;
	        } else {
	            return true;
	        }
	    }

	    // Get fieldname
	    function getFieldName(input) {
	        const formControl = input.parentElement;
	        const label = formControl.querySelector('label');
	        return label.innerText;
	    }

	    function subform(){
			if (!checkRequired([nickname, phone]) && checkPasswordsMatch(password, password2)) {
				form.submit();
			}
	    }
</script>
</html>