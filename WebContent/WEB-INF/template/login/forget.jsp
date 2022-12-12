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
	<title>修改密码</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/login.css">
</head>

<style>
.form-control{
	margin-bottom: 0px;
}
.form-control input{
	padding: 5px;
}
</style>
<body>
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
    
    <div class="wrapper">
        <div class="one item">
        	<div class="big-logo">
    			<img src="${basePath}/img/logo_transparent-nosignal.png">
   			</div>
            <div class="left">
                <div class="edit-box">
                    <form id="forget-form" class="form" action="${basePath}/user/getForgetCode">
                        <h2>忘记密码</h2>
                        <div class="form-control">
                            <label for="forget-username">登录名称</label>
                            <input type="text" id="forget-username" name="username" value="" placeholder="请输入登录名称" />
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="forget-email">邮箱</label>
                            <input type="text" id="forget-email" name="email" value="" placeholder="请输入邮箱" />
                            <small>错误信息</small>
                        </div>
                        <h4><a href="${basePath}/user/toLogin">返回登录</a></h4>
                        <button type="submit" class="login-submit">提交</button>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="two item">
            <div class="right">
                <div class="edit-box">
                    <form id="change-form" class="form" action="${basePath}/user/changePassword">
                        <h2>修改密码</h2>
                        <div class="form-control">
                            <label for="captcha">验证码</label>
                            <input type="text" id="captcha" name="captcha" placeholder="请输入验证码" value=""/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="changePassword">密码</label>
                            <input type="password" id="changePassword" name="password" placeholder="请输入密码" value=""/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="changePassword1">确认密码</label>
                            <input type="password" id="changePassword1" placeholder="请再次输入密码" value=""/>
                            <small>错误信息</small>
                        </div>
                        <button type="submit" class="login-submit">确认</button>
                    </form>
                </div>
            </div>
        </div>
        
       	<c:if test="${showLogo == '1'}">
   	        <div class="logo reg"></div>
       	</c:if>
       	<c:if test="${showLogo != '1'}">
   	        <div class="logo"></div>
       	</c:if>
        
    </div>

    
  	<script type="text/javascript" src="${basePath}/js/forget.js"></script>
    <script>
	    function hideInfo() {
	    	document.querySelector('.info').style.display = 'none'
	    }
	    function toForget(){
	   		window.location.href  = "${basePath}/user/toForget";
	    }

    </script>
</body>
</html>