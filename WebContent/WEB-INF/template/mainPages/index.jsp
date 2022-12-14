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
    <title>温暖之家后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/model.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/user-manage.css">
    <script type="text/javascript" src="${basePath}/js/navititle.js"></script>
    <script type="text/javascript" src="${basePath}/js/content.js"></script>
</head>
    <div id="mySidenav" class="sidenav">
        <div class="logo">
            <img src="${basePath}/img/logo_nosignal.png" style="width:100px;height:100px">
            <span class="signal">给你一个<br>温暖的家</span>
        </div>
        <div class="big_box">
            <div class="left_daohang">
                <!-- 第一块：一级标题1 -->
                <div class="item1" name='1'>
                    <span class="item1_text">用户信息管理</span>
                    <div class="you item1_img"></div>
                </div>
                <div class="item1_item2">
                  	<c:forEach var="userRole" items="${userRole}">
				    	<c:if test="${userRole.role == '管理员' || userRole.role == '所有'}">
                  			<div class="item2">
                        		<span class="item2_text"><a href="${basePath}/usermanage/listuser?pageNum=1&pageSize=10" target="content-box">用户管理</a></span>
                    		</div>
				    	</c:if>
				    	<c:if test="${userRole.role == '系统管理员' || userRole.role == '所有'}">
                  			<div class="item2">
                        		<span class="item2_text"><a href="${basePath}/userpermission/listuser?pageNum=1&pageSize=10" target="content-box">权限管理</a></span>
                    		</div>
				    	</c:if>
     				</c:forEach>
                </div>
                <div class="item1" name='1'>
                    <span class="item1_text">房源信息管理</span>
                    <div class="you item1_img"></div>
                </div>
                <div class="item1_item2">
                  	<c:forEach var="userRole" items="${userRole}">
                  		<c:if test="${userRole.role == '普通用户' || userRole.role == '所有'}">
                  			<div class="item2">
                  				<span class="item2_text"><a href="${basePath}/searchInformation/listinfo?username=${user.username}&pageNum=1&pageSize=10" target="content-box">信息查询</a></span>
                    		</div>
                    		<div class="item2">
                    		    <span class="item2_text"><a href="${basePath}/information/listinfo?username=${user.username}&pageNum=1&pageSize=10" target="content-box">信息发布</a></span>
                    		</div>
				    	</c:if>
				    	<c:if test="${userRole.role == '管理员' || userRole.role == '所有'}">
                    		<div class="item2">
                        		<span class="item2_text"><a href="${basePath}/information/listinfo?pageNum=1&pageSize=10" target="content-box">信息管理</a></span>
                    		</div>
				    	</c:if>
     				</c:forEach>
                </div>
                <div class="item1" name='1'>
                    <span class="item1_text">系统信息管理</span>
                    <div class="you item1_img"></div>
                </div>
                <div class="item1_item2">
                  	<c:forEach var="userRole" items="${userRole}">
				    	<c:if test="${userRole.role == '系统管理员' || userRole.role == '所有'}">
                    		<div class="item2">
                        		<span class="item2_text"><a href="${basePath}/systable/listsystem?pageNum=1&pageSize=10" target="content-box">系统表管理</a></span>
                    		</div>
				    	</c:if>
     				</c:forEach>
                </div>
            </div>
        </div>
    </div>
 
    <div id="main">
       <!-- 顶部导航栏 -->
       <div class="title-nav">
           <div class="userinfo" onclick="showUserMenu()">
               
               <span class="username">
               	<c:if test="${userHeadimg == null}">
               		<img class="headimg" src="${basePath}/img/0.jpg">
   				</c:if>
				<c:if test="${userHeadimg != null}">
					<img  class="headimg" src="data:image/jpg;base64,${userHeadimg.dataBase64}"/>
   				</c:if>
               	<c:if test="${user.nickname == null}">
              	 		未登录
   				</c:if>
               	${user.nickname}
               </span>
               
               <span>
               <c:forEach var="userRole" items="${userRole}">
               	<c:if test="${userRole.role == '普通用户' || userRole.role == '所有'}">
              	 		<div class="icon1">普通用户</div>
   				</c:if>
   				<c:if test="${userRole.role == '管理员' || userRole.role == '所有'}">
               		<div class="icon2">管理员</div>
   				</c:if>
   				<c:if test="${userRole.role == '系统管理员' || userRole.role == '所有'}">
               		<div class="icon3">系统管理员</div>
   				</c:if>
				</c:forEach>
               </span>
               
               <span style="margin-left: 180px">
            	 	<div class="icon1" style="background-color: #606266">小组信息</div>
            	 	<div class="icon1" style="background-color: #606266; color: red">王晓东：0204414</div>
            	 	<div class="icon1" style="background-color: #606266">罗嘉骏：0204435</div>
            	 	<div class="icon1" style="background-color: #606266">赖经涛：0204438</div>
            	 	<div class="icon1" style="background-color: #606266">黄海森：0204361</div>
               </span>
           </div>
           <div class="user-menu">
        		<span class="triangle"></span>
               	<div><a href="${basePath}/user/userInfo?username=${user.username}" target="content-box">个人信息</a></div>
               	<div><a href="${basePath}/user/userHeadimg?username=${user.username}" target="content-box">修改头像</a></div>
               	<div><a href="${basePath}/user/logout">退出登录</a></div>
           </div>
       </div>
    
       <!-- 编辑区 -->
       <div class="edit-space">
           <div class="content">
           		<iframe name="content-box" src="welcome" width="100%" height="100%" frameborder="0"  scrolling="no"></iframe>
           </div>
       </div>
    </div>
    
    <script>
    window.onload = function() {
        let item1s = document.querySelectorAll('.item1')
        let item1_item2s = document.querySelectorAll('.item1_item2')
        let item2s = document.querySelectorAll(".item2")

        //如果没有二级导航，就不显示下拉栏
        for (let i = 0; i < item1s.length; i++) {
            if (item1_item2s[i].children.length == 0) {
                item1s[i].children[1].className = 'item1_img'
            }
        }

        //点击下拉栏显示隐藏二级导航
        for (let i = 0; i < item1s.length; i++) {
            item1s[i].addEventListener('click', function(val) {
                if (item1_item2s[i].style.display == "none") {
                    item1_item2s[i].style.display = "block"
                    item1s[i].children[1].style.transform = 'rotate(90deg)'
                } else {
                    item1_item2s[i].style.display = "none"
                    item1s[i].children[1].style.transform = 'rotate(0deg)'
                }
            })
        };
    }
</script>
</body>

</html>