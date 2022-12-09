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
body {
      margin: 0;
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
	margin: 5px 30vw;
}
</style>
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/user/userSubmit" traget="_blank">
            <div class="form-control">
            	<c:if test="${showUserInfo == 1}">
                <label for="username">登录名称</label>
   				</c:if>
                <input type="text" id="username" placeholder="请输入登录名称" name="username" value="${showUser.username}" readonly="readonly"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
            	<c:if test="${showUserInfo == 1}">
                <label for="nickname">用户昵称</label>
   				</c:if>
                <input type="text" id="nickname" placeholder="请输入用户昵称" name="nickname" value="${showUser.nickname}"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
            	<c:if test="${showUserInfo == 1}">
                <label for="phone">联系方式</label>
   				</c:if>
                <input type="text" id="phone" placeholder="请输入联系方式" name="phone" value="${showUser.phone}"/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
	             <label for="forget-email">邮箱</label>
	             <input type="text" id="forget-email" name="email" placeholder="请输入邮箱" value="${showUser.email}"/>
	             <small>错误信息</small>
           	</div>
            <div class="form-control">
            	<c:if test="${showUserInfo == 1}">
                <label for="password">密码</label>
   				</c:if>
                <input type="password" id="password" placeholder="请输入密码"  name="password" />
                <small>错误信息</small>
            </div>
            <div class="form-control">
            	<c:if test="${showUserInfo == 1}">
                <label for="password2">确认密码</label>
   				</c:if>
                <input type="password" id="password2" placeholder="请再次输入密码" />
                <small>错误信息</small>
            </div>
            <button type="submit">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	  	const form = document.getElementById('form');
	    const nickname = document.getElementById('nickname');
	    const phone = document.getElementById('phone');
	    const email = document.getElementById('forget-email');
	    const password = document.getElementById('password');
	    const password2 = document.getElementById('password2');

	    function hideform() {
			window.location.href  = "${basePath}/user/welcome";
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
	        let isRequired = false;
	        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	        if (re.test(input.value.trim())) {
	            showSuccess(input);
                isRequired = true;
	        } else {
	            showError(input, '邮箱不合法');
	        }
	        return isRequired;
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

	    // Event listeners
	    form.addEventListener('submit', function(e) {
	        e.preventDefault();

	        if (!checkRequired([nickname, phone, email]) && checkPasswordsMatch(password, password2)
	        		&& checkEmail(email)) {
	            console.log("登录成功");
	            form.submit();
	        }
	    });		
</script>
</html>