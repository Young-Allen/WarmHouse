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
    font-size: 16px;
    font-weight: 700;
    color: black;
    margin: 0px 0px;
}

.searchUser {
    padding: 0px 10px;
}

.searchUser .getArea,
.searchUser .getPrice,
.searchUser .getBirth {
    width: 60px;
}

.searchUser .getFloor,
.searchUser .getTotalFloor {
    width: 40px;
}

.getDecoration {
    width: 100px;
    height: 33px;
    border: 1px solid #ccc;
}

.card {
    width: 70vw;
    height: auto;
    border-radius: 20px;
    background: #ffffff;
    box-shadow: 15px 15px 30px #bebebe, -15px -15px 30px #ffffff;
    padding: 5px 5px;
    margin: auto;
}
.radio-input{
	margin: 3px 0px;
	font-size: 16px;
    font-weight: 700;
    color: black;
}

.radio-input input {
    width: 10px;
}

.radio-input label {
    font-size: 15px;
    font-weight: 700;
    color: #777;
}
.user-manage{
	width: 90vw;
}
</style>
<body>
	<div class="user-manage">
		<div class="searchUser" style="display: none">
			<div class="card">
	    	<form class="form" action="${basePath}/searchInformation/infotb" method="post" target="userTableList">
		    	
	    		<div class="radio-input">区域:
					<c:forEach items="${housebelongList}" var="housebelong">
						<c:if test="${housebelong.code == 00}">
						<input type="radio" id="'${housebelong.title}'+'1'" name="housebelong" value="" checked="checked">
						</c:if>
						<c:if test="${housebelong.code != 00}">
						<input type="radio" id="'${housebelong.title}'+'1'" name="housebelong" value="${housebelong.title}">
						</c:if>
						<label for="'${housebelong.title}'+'1'">${housebelong.title}</label>
					</c:forEach>
				</div>
	    	
	    		<div class="radio-input">房型:
					<c:forEach items="${suiteList}" var="suite">
						<c:if test="${suite.code == 00}">
						<input type="radio" id="'${suite.title}'+'2'" name="suiteRoom" value="" checked="checked">
						</c:if>
						<c:if test="${suite.code != 00}">
						<input type="radio" id="'${suite.title}'+'2'" name="suiteRoom" value="${suite.title}">
						</c:if>
						<label for="'${suite.title}'+'2'">${suite.title}</label>
					</c:forEach>
				</div>
	    	
	    		<div class="radio-input">面积:
					<c:forEach items="${areaList}" var="area">
						<c:if test="${area.code == 00}">
						<input type="radio" id="'${area.title}'+'3'" name="area" value="" checked="checked">
						</c:if>
						<c:if test="${area.code != 00}">
						<input type="radio" id="'${area.title}'+'3'" name="area" value="${area.title}">
						</c:if>
						<label for="'${area.title}'+'3'">${area.title}</label>
					</c:forEach>
				</div>
				
				<div class="radio-input">朝向:
					<c:forEach items="${directionList}" var="direction">
						<c:if test="${direction.code == 00}">
						<input type="radio" id="'${direction.title}'+'4'" name="direction" value="" checked="checked">
						</c:if>
						<c:if test="${direction.code != 00}">
						<input type="radio" id="'${direction.title}'+'4'" name="direction" value="${direction.title}">
						</c:if>
						<label for="'${direction.title}'+'4'">${direction.title}</label>
					</c:forEach>
				</div>
				
				<div class="radio-input">
					<span>建造年份：</span>
					<input type="number" id="birth" name="minBirth" min="0" max="2022" class="getBirth" value="1949">-
					<input type="number" id="birth" name="maxBirth" min="0" max="2022" class="getBirth" value="9999">年
					<span style="margin: 0px 10px">|</span> 
		    		
					<span>楼层：</span>
					<input type="number" id="floor" name="floor" min="0" class="getFloor">
					<span>总楼层：</span>
					<input type="number" id="totalFloor" name="totalFloor" min="0" class="getTotalFloor">
					<span style="margin: 0px 10px">|</span> 
					
					<span>价格：</span>
					<input type="number" id="price" name="minPrice" min="0.0" class="getPrice" step="0.01" value="0">-
					<input type="number" id="price" name="maxPrice" min="0.0" class="getPrice" step="0.01" value="999">万元			
				</div>
				
				
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
				</select><span style="margin: 0px 10px">|</span> 
				
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
				</select><span style="margin: 0px 10px">|</span> 
				
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
				</select><span style="margin: 0px 10px">|</span> 
				
				<span>状态：</span>
	         	<select class="getDecoration" name="housestatus">
	        			<c:forEach items="${housestatusList}" var="housestatus">
	        			<c:if test="${housestatus.code == 00}">
	        				<option value="">${housestatus.title}</option>
	        			</c:if>
	        			<c:if test="${housestatus.code != 00}">
	        				<option value="${housestatus.title}">${housestatus.title}</option>
	        			</c:if>
					</c:forEach>
				</select>
				
				<span class="searchUserBtn" onclick="submitSearchUser()"><a target="usertable">查询</a></span>
	         	<span class="searchUserBtn" onclick="resetSearchUser()">重置</span>
	         	<span class="searchUserBtn" onclick="hide()">取消</span><br>
	         	<input type="text" class="pageNum" name="pageNum" style="display:none" value="">
	         	<input type="text" class="pageSize" name="pageSize" style="display:none" value="">
		    </form>
		    </div>
	    </div>	  
	    
	    <div class="user-table" style="height: 95vh">
   			<iframe id="userframe" name="userTableList" src="informationTable" width="100%" height="100%" frameborder="0"  scrolling="no"></iframe>
		</div>   
	</div>   
	
	 
	
    <script>
	    function submitSearchUser(){
			document.querySelector('.pageNum').value = 1;
			document.querySelector('.pageSize').value = 10;
			document.querySelector('.form').submit();
	    };
	   
	    function resetSearchUser(){
	    	document.querySelector('.form').reset();
	    };
	    
	    function hide(){
	    	document.querySelector('.searchUser').style.display = 'none';
	    }
    </script>
</body>

</html>