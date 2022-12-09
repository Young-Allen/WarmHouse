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
.form-control{
	margin-bottom: 0px;
}
.form{
	padding: 10px 50px;
}
.info {
       position: absolute;
       z-index: 200;
       margin: 10px 31vw;
       width: 20vw;
       height: 20vh;
       border-radius: 20px;
       background-color: rgb(107, 101, 101);
}
 .card {
    width: 100%;
    height: 100%;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    gap: 12px;
    background: rgb(255 255 255);
    border-radius: 20px;
    box-shadow: 6px 6px 12px #d9d9d9, -6px -6px 12px #e7e7e7;
}
.card__title {
     color: #e74c3c;
 }
 .card__form {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.card__form button {
    border: 0;
    background: rgb(52,152,219);
    color: #fff;
    padding: 0.68em;
    border-radius: 14px;
    font-weight: bold;
}

.sign-up:hover {
    opacity: 0.8;
}
</style>
	<c:if test="${showMsg == '1'}">
	 <div class="info">
        <div class="card">
       	 	<h2 class="card__title">${msg}</h2>
            <div class="card__form">
                <button class="sign-up" onclick="hideInfo()">确定</button>
            </div>
        </div>
    </div>
	</c:if>
	
    <div class="edit-box">
        <form id="form" class="form" method="post" action="${basePath}/usermanage/addUserSubmit">
            <div class="form-control">
                <label for="username">登录名称</label>
                <input type="text" id="username" placeholder="请输入登录名称" name="username" value=""/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="nickname">用户昵称</label>
                <input type="text" id="nickname" placeholder="请输入用户昵称" name="nickname" value=""/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="phone">联系方式</label>
                <input type="text" id="phone" placeholder="请输入联系方式" name="phone" value=""/>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="email">联系方式</label>
                <input type="text" id="email" placeholder="请输入邮箱" name="email" value=""/>
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
		const username = document.getElementById('username');
	    const nickname = document.getElementById('nickname');
	    const phone = document.getElementById('phone');
	    const email = document.getElementById('email');
	    const password = document.getElementById('password');
	    const password2 = document.getElementById('password2');
	}
		function hideInfo(){
	    	document.querySelector('.info').style.display = 'none'
		};
		
	    function hideform() {
			window.location.href  = "${basePath}/usermanage/listuser?pageNum=1&pageSize=10";
		};
	    
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

	    function subform(){
			if (!checkRequired([username,nickname,phone,email,password,password2])
					&& checkPasswordsMatch(password, password2) && checkEmail(email)) {
				form.submit();
			}
	    }
</script>
</html>