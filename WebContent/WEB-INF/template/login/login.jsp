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
	<title>登录</title>
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
	<%
		String username = "";
		String password = "";
		Cookie[] c = request.getCookies();
		if (c != null) {
			for (int i = 0; i < c.length; i++) {
				if ("username".equals(c[i].getName())) {
					username = c[i].getValue();
				} else if ("password".equals(c[i].getName())) {
					password = c[i].getValue();
				}
			}
		} else {
			username = " ";
			password = " ";
		}
	%>
	
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
    
    <c:if test="${showMsg == '2'}">
	 <div class="info">
        <div class="card">
       	 	<h2 class="card__title">${msg}</h2>
            <div class="card__form">
                <button class="sign-up" onclick="toIndex()">确定</button>
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
                    <form id="login-form" class="form" action="${basePath}/user/login">
                        <h2>登录</h2>
                        <div class="form-control">
                            <label for="login-username">登录名称</label>
                            <input type="text" id="login-username" name="username" value="<%=username%>" placeholder="请输入登录名称" />
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="login-password">密码</label>
                            <input type="password" id="login-password" name="password" value="<%=password%>" placeholder="请输入密码" />
                            <small>错误信息</small>
                        </div>
                        
                        <label style="color: #777;" for="remember-password">记住密码</label>
                        <input type="checkbox" id="remember-password" name="remember"/>
                        
                        <h4>没有账号？
                            <a onclick="toregister()">去注册</a>
                        </h4>
                        <h4>
                        	<a href="${basePath}/user/toForget" target="_blank">忘记密码</a>
                        </h4>
                        <button type="submit" class="login-submit">登录</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="two item">
            <div class="right">
                <div class="edit-box">
                    <form id="form" class="form register" action="${basePath}/user/register">
                        <h2>注册</h2>
                        <div class="form-control">
                            <label for="username">登录名称</label>
                            <input type="text" id="username" name="username" placeholder="请输入登录名称" value="${user.username}"/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="nickname">用户昵称</label>
                            <input type="text" id="nickname" name="nickname" placeholder="请输入用户昵称" value="${user.nickname}"/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="phone">联系方式</label>
                            <input type="text" id="phone" name="phone" placeholder="请输入联系方式" value="${user.phone}"/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="email">邮箱</label>
                            <input type="text" id="email" name="email" placeholder="请输入邮箱" value=""/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="password">密码</label>
                            <input type="password" id="password" name="password" placeholder="请输入密码" value="${user.password}"/>
                            <small>错误信息</small>
                        </div>
                        <div class="form-control">
                            <label for="password2">确认密码</label>
                            <input type="password" id="password2" placeholder="请再次输入密码" value="${user.password}"/>
                            <small>错误信息</small>
                        </div>
                        <button type="submit">注册</button>
                        <button type="button" onclick="toLogin()">返回</button>
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
    
    <script type="text/javascript" src="${basePath}/js/login.js"></script>
    <script>
		//解决子框架嵌套的问题
		if(window != window.parent){
		    window.parent.location.reload(true);
		}
	    function hideInfo() {
	    	document.querySelector('.info').style.display = 'none'
	    }
	    function toIndex(){
			window.location.href = "${basePath}/user/index";	    	
	    }
    </script>
</body>
</html>