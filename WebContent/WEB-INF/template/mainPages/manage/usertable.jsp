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
	<title>user table</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/user-manage.css">
 	<link rel="stylesheet" type="text/css" href="${basePath}/css/paging.css">
    <script type="text/javascript" src="${basePath}/js/qu.js"></script>
    <script type="text/javascript" src="${basePath}/js/paging.js"></script>
</head>
<style>
.table-foot span{
	position: relative;
	display: inline-block;
	color: #777;
	top: -23px;
	left: 5px;
}
</style>
<body>
   	<div class="user-tablelist" >
   		<iframe id="usertablelist" name="userTableList" src="userTableList" width="100%" height="100%" frameborder="0"  scrolling="yes"></iframe>
   	</div>
   	
    <div class="table-foot" >
		 <div id="pageToolbar" style="display: inline-block;"></div>
		 <span>总记录数: ${userPageInfo.total}</span>
	</div>
	
	
    <script>
	    var page = new Paging();
	    
	    page.init({
	        target: $('#pageToolbar'),
	        count: ${userPageInfo.total}, //总记录数
	        pagesize: ${userPageInfo.pageSize}, //每页的条数
	        current: ${userPageInfo.pageNum}, //当前页码，默认为1
	        toolbar: true,
	        hash: true,
	        pageSizeList: [1, 2, 3, 5, 10, 15],
	        callback: function(pageNum, pageSize, totals) {
	        	window.location.href  = "${basePath}/usermanage/usertb?flag=1&pageNum="+pageNum+"&pageSize="+pageSize;
	        },
	        
	        changePagesize: function(pageSize,pageNum,pagecount){
	        	window.location.href  = "${basePath}/usermanage/usertb?flag=1&pageNum="+pageNum+"&pageSize="+pageSize;
	        }
	    }); 
    </script>
</body>
</html>