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
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/user-manage.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/paging.css">
    <script type="text/javascript" src="${basePath}/js/qu.js"></script>
    <script type="text/javascript" src="${basePath}/js/paging.js"></script>
</head>
<style>
.searchUser span {
    font-size: 14px;
    font-weight: 700;
    color: black;
    margin: 0px 8px;
}

.getRole:active{
	border: 1px solid #409eff;
}
.getRole option{
	text-align: center;
	fontsize: 15px;
}
.user-manage .searchUser{
	padding: 0px 10px;
}
.user-manage .searchUser .getCode,
.user-manage .searchUser .getusername
{
    width: 200px;
}
.user-manage .searchUser .getTitle{
 	width: 300px;
}

.user-manage .searchUser .getSuiteRoom,
.user-manage .searchUser .getSuiteHall,
.user-manage .searchUser .getsuiteBathroom
{
    width: 30px;
}
.user-manage .searchUser .getArea,
.user-manage .searchUser .getPrice{
	width: 60px;
}
.getDirection{
	width: 60px;
	height: 33px;
	border: 1px solid #ccc;
}
.user-manage .searchUser .getBirth{
	width: 60px;
}
.user-manage .searchUser .getFloor, 
.user-manage .searchUser .getTotalFloor{
	width: 40px;
}
.getHousebelong,
.getDecoration{
	width: 100px;
	height: 33px;
	border: 1px solid #ccc;
}
</style>
<body>
	<div class="user-manage">
	    <div class="searchUser">
	    	<form class="listuser" action="${basePath}/information/infotb" method="post" target="userTableList">
	    		<span>注册编号：</span><input placeholder="请输入编号" type="text" class="getCode" name="code">
    			<span>房源标题：</span><input placeholder="请输入房源标题" type="text" class="getTitle" name="title">
    			<span>售房人：</span><input placeholder="请输入售房人姓名" type="text" class="getSalesman" name="salesman">
	    		<span class="searchUserBtn" onclick="submitSearchUser()"><a target="usertable">查询</a></span>
	         	<span class="searchUserBtn" onclick="add()">添加</span>
	         	<span class="searchUserBtn" onclick="resetSearchUser()">重置</span><br>
	         	
	    		<span>房型：</span>
	    		<input type="number" id="suiteRoom" name="suiteRoom" min="0" class="getSuiteRoom">室
	    		<input type="number" id="suiteHall" name="suiteHall" min="0" class="getSuiteHall">厅
	    		<input type="number" id="suiteBathroom" name="suiteBathroom" min="0" class="getsuiteBathroom">卫 <span>|</span> 
	    		
	    		<span>面积：</span>
				<input type="number" id="area" name="area" min="0.0" class="getArea" step="0.01">㎡<span>|</span> 
	    		
	    		<span>朝向：</span>
	         	<select class="getDirection" name="direction" onChange="submitSearchUser()">
		         	<c:forEach items="${directionList}" var="direction">
		         		<c:if test="${direction.code == 00}">
						<option value="">${direction.title}</option>
		         		</c:if>
		         		<c:if test="${direction.code != 00}">
		         		<option value="${direction.title}">${direction.title}</option>
		         		</c:if>
					</c:forEach>
				</select><span>|</span> 
	   			
	   			<span>建造年份：</span>
				<input type="number" id="birth" name="birth" min="0" max="2022" class="getBirth">年<span>|</span> 
				
				<span>楼层：</span>
				<input type="number" id="floor" name="floor" min="0" class="getFloor">
				<span>总楼层：</span>
				<input type="number" id="totalFloor" name="totalFloor" min="0" class="getTotalFloor"><br>
				
				<span>所属区：</span>
	         	<select class="getHousebelong" name="housebelong" onChange="submitSearchUser()">
         			<c:forEach items="${housebelongList}" var="housebelong">
         			<c:if test="${housebelong.code == 00}">
	         			<option value="">${housebelong.title}</option>
       				</c:if>
       				<c:if test="${housebelong.code != 00}">
       					<option value="${housebelong.title}">${housebelong.title}</option>
       				</c:if>
					</c:forEach>
				</select><span>|</span> 
				
				<span>价格：</span>
				<input type="number" id="price" name="price" min="0.0" class="getPrice" step="0.01">万元<span>|</span> 
				
				<span>装修：</span>
	         	<select class="getDecoration" name="decoration">
         			<c:forEach items="${decorationList}" var="decoration">
         			<c:if test="${decoration.code == 00}">
         				<option value="">${decoration.title}</option>
         			</c:if>
         			<c:if test="${decoration.code != 00}">
	         			<option value="${decoration.title}">${decoration.title}</option>
	         		</c:if>
					</c:forEach>
				</select><span>|</span> 
				
				<span>产权：</span>
	         	<select class="getDecoration" name="propertyrights">
         			<c:forEach items="${propertyrightsList}" var="propertyrights">
         			<c:if test="${propertyrights.code == 00}">
         				<option value="">${propertyrights.title}</option>
         			</c:if>
         			<c:if test="${propertyrights.code != 00}">
         				<option value="${propertyrights.title}">${propertyrights.title}</option>
         			</c:if>
					</c:forEach>
				</select><span>|</span> 
				
				<span>物业：</span>
	         	<select class="getDecoration" name="property">
         			<c:forEach items="${propertyList}" var="property">
         			<c:if test="${property.code == 00}">
         				<option value="">${property.title}</option>
         			</c:if>
         			<c:if test="${property.code != 00}">
         				<option value="${property.title}">${property.title}</option>
         			</c:if>
					</c:forEach>
				</select><span>|</span> 
				
				<span>状态：</span>
	         	<select class="getDecoration" name="housestatus" onChange="submitSearchUser()">
         			<c:forEach items="${housestatusList}" var="housestatus">
         			<c:if test="${housestatus.code == 00}">
         				<option value="">${housestatus.title}</option>
         			</c:if>
         			<c:if test="${housestatus.code != 00}">
         				<option value="${housestatus.title}">${housestatus.title}</option>
         			</c:if>
					</c:forEach>
				</select>
				
	         	<input type="text" class="pageNum" name="pageNum" style="display:none" value="">
	         	<input type="text" class="pageSize" name="pageSize" style="display:none" value="">
	         	
	    	</form>
	    </div>	  
	    
	<div class="user-table" >
   		<iframe id="userframe" name="userTableList" src="informationTable" width="100%" height="100%" frameborder="0"  scrolling="yes"></iframe>
	</div>    
	
    <script>
	    function submitSearchUser(){
			document.querySelector('.pageNum').value = 1;
			document.querySelector('.pageSize').value = 10;
			document.querySelector('.listuser').submit();
	    };
	   
	    function resetSearchUser(){
	    	document.querySelector('.listuser').reset();
	    };
	    
	    function add(){
	    	window.location.href = "${basePath}/information/addInfotb";
	    }
    </script>
</body>

</html>