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
body,
html {
	align-items: baseline;
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
	width: auto;
	margin: 50px 5vw;
	padding: 20px;
}
.listuser span {
    font-size: 14px;
    font-weight: 700;
    color: black;
    margin: 0px 8px;
}
.listuser input {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 6px;
    width: 200px;
    font-size: 14px;
    font-family: "Microsoft soft";
}
.listuser .getTitle{
	width: 455px;
}
.listuser .getSuiteRoom,
.listuser .getSuiteHall,
.listuser .getsuiteBathroom,
.listuser .getArea,
.listuser .getFloor,
.listuser .getTotalFloor,
.listuser .getPrice{
	width: 60px;
}
.listuser .getBirth{
	width: 70px;
}
.getDirection {
    width: 60px;
    height: 33px;
    border: 1px solid #ccc;
}
.getHousebelong, .getDecoration {
    width: 100px;
    height: 33px;
    border: 1px solid #ccc;
}
.listuser .Btn{
	cursor: pointer;
	position: relative;
	display: inline-block;
	margin-top: 10px;
	left: 750px;
    width: 100px;
    height: 30px;
    line-height: 30px;
    background-color: rgb(64,158,255);
    text-align: center;
    border-radius: 10px;
    color: #fff;
}
.getRole {
    width: 195px;
    height: 30px;
    border: 1px solid #ccc;
    margin-top: 0px;
    color: #777;
    margin-bottom: 5px;
    font-size: 15px;
}
</style>
    <div class="edit-box">
    	<c:if test="${houseInfoFlag==1}">
        <form class="listuser" action="${basePath}/information/addHouseInfoSubmit" method="post" target="content-box">
    	</c:if>
    	<c:if test="${houseInfoFlag!=1}">
    	<form class="listuser" action="${basePath}/information/houseInfoSubmit" method="post" target="content-box">
    	</c:if>
    	
        	<c:if test="${houseInfoFlag!=1}">
        	<span>注册编号：</span><input placeholder="请输入编号" type="text" class="getCode" name="code" value="${showHouseInfoTable.code}" readonly="readonly">
        	</c:if>
        	<c:if test="${houseInfoFlag==1}">
        	<span>注册编号：</span><input placeholder="请输入编号" type="text" class="getCode" name="code" value="${showHouseInfoTable.code}">
        	</c:if>
        	
   			<span>房源标题：</span><input placeholder="请输入房源标题" type="text" class="getTitle" name="title" value="${showHouseInfoTable.title}">
   			
   			<span>售房人：</span>
   			<select class="getRole" id="getRole" name="salesman">
            	<c:forEach items="${allUser}" var="auser">
            		<c:if test="${auser.username == showHouseInfoTable.salesman}">
            		 	<option value="${auser.username}" selected="selected">${auser.username}</option>
            		</c:if>
            		<c:if test="${auser.username != showHouseInfoTable.salesman}">
            		 	<option value="${auser.username}">${auser.username}</option>
            		</c:if>
            	</c:forEach>
			</select><br>
   			
    		<span>房型：</span>
    		<input type="number" id="suiteRoom" name="suiteRoom" min="0" class="getSuiteRoom" value="${showHouseInfoTable.suiteRoom}">室
    		<input type="number" id="suiteHall" name="suiteHall" min="0" class="getSuiteHall" value="${showHouseInfoTable.suiteHall}">厅
    		<input type="number" id="suiteBathroom" name="suiteBathroom" min="0" class="getsuiteBathroom" value="${showHouseInfoTable.suiteBathroom}">卫 <span>|</span> 
    		
    		<span>面积：</span>
			<input type="number" id="area" name="area" min="0.0" class="getArea" step="0.01" value="${showHouseInfoTable.area}">㎡<span>|</span> 
    		
    		<span>朝向：</span>
         	<select class="getDirection" name="direction">
	         	<c:forEach items="${directionList}" var="direction">
	         		<c:if test="${direction.code == 00 && direction.title == showHouseInfoTable.direction}">
         			<option value="" selected="selected" >${direction.title}</option>
         			</c:if>
         			<c:if test="${direction.code == 00 && direction.title != showHouseInfoTable.direction}">
         			<option value="">${direction.title}</option>
         			</c:if>
	         		<c:if test="${direction.code != 00 && direction.title == showHouseInfoTable.direction}">
	         		<option value="${direction.title}" selected="selected">${direction.title}</option>
	         		</c:if>
       				<c:if test="${direction.code != 00 && direction.title != showHouseInfoTable.direction}">
	         		<option value="${direction.title}">${direction.title}</option>
	         		</c:if>
				</c:forEach>
			</select><span>|</span> 
   			
   			<span>建造年份：</span>
			<input type="number" id="birth" name="birth" min="0" max="2022" class="getBirth" value="${showHouseInfoTable.birth}">年<span>|</span> 
			
			<span>楼层：</span>
			<input type="number" id="floor" name="floor" min="0" class="getFloor" value="${showHouseInfoTable.floor}">
			<span>总楼层：</span>
			<input type="number" id="totalFloor" name="totalFloor" min="0" class="getTotalFloor" value="${showHouseInfoTable.totalFloor}"><br>
			
			<span>所属区：</span>
         	<select class="getHousebelong" name="housebelong">
        			<c:forEach items="${housebelongList}" var="housebelong">
        			<c:if test="${housebelong.code == 00 && housebelong.title == showHouseInfoTable.housebelong}">
         			<option value="" selected="selected">${housebelong.title}</option>
      				</c:if>
      				<c:if test="${housebelong.code == 00 && housebelong.title != showHouseInfoTable.housebelong}">
         			<option value="">${housebelong.title}</option>
      				</c:if>
      				<c:if test="${housebelong.code != 00 && housebelong.title == showHouseInfoTable.housebelong}">
   					<option value="${housebelong.title}" selected="selected">${housebelong.title}</option>
      				</c:if>
      				<c:if test="${housebelong.code != 00 && housebelong.title != showHouseInfoTable.housebelong}">
   					<option value="${housebelong.title}">${housebelong.title}</option>
      				</c:if>
				</c:forEach>
			</select><span>|</span> 
			
			<span>价格：</span>
			<input type="number" id="price" name="price" min="0.0" class="getPrice" step="0.01" value="${showHouseInfoTable.price}">万元<span>|</span> 
			
			<span>装修：</span>
         	<select class="getDecoration" name="decoration">
        			<c:forEach items="${decorationList}" var="decoration">
        			<c:if test="${decoration.code == 00 && decoration.title == showHouseInfoTable.decoration}">
        				<option value="" selected="selected">${decoration.title}</option>
        			</c:if>
        			<c:if test="${decoration.code == 00 && decoration.title != showHouseInfoTable.decoration}">
        				<option value="">${decoration.title}</option>
        			</c:if>
        			<c:if test="${decoration.code != 00 && decoration.title == showHouseInfoTable.decoration}">
         				<option value="${decoration.title}" selected="selected">${decoration.title}</option>
         			</c:if>
         			<c:if test="${decoration.code != 00 && decoration.title != showHouseInfoTable.decoration}">
         				<option value="${decoration.title}">${decoration.title}</option>
         			</c:if>
				</c:forEach>
			</select><span>|</span> 
			
			<span>产权：</span>
         	<select class="getDecoration" name="propertyrights">
        			<c:forEach items="${propertyrightsList}" var="propertyrights">
        			<c:if test="${propertyrights.code == 00 && propertyrights.title == showHouseInfoTable.propertyrights}">
        				<option value="" selected="selected">${propertyrights.title}</option>
        			</c:if>
        			<c:if test="${propertyrights.code == 00 && propertyrights.title != showHouseInfoTable.propertyrights}">
        				<option value="">${propertyrights.title}</option>
        			</c:if>
        			<c:if test="${propertyrights.code != 00 && propertyrights.title == showHouseInfoTable.propertyrights}">
        				<option value="${propertyrights.title}" selected="selected">${propertyrights.title}</option>
        			</c:if>
        			<c:if test="${propertyrights.code != 00 && propertyrights.title != showHouseInfoTable.propertyrights}">
        				<option value="${propertyrights.title}">${propertyrights.title}</option>
        			</c:if>
				</c:forEach>
			</select><span>|</span> 
			
			<span>物业：</span>
         	<select class="getDecoration" name="property">
        			<c:forEach items="${propertyList}" var="property">
        			<c:if test="${property.code == 00 && property.title == showHouseInfoTable.property}">
        				<option value="" selected="selected">${property.title}</option>
        			</c:if>
       				<c:if test="${property.code == 00 && property.title != showHouseInfoTable.property}">
        				<option value="">${property.title}</option>
        			</c:if>
        			<c:if test="${property.code != 00 && property.title == showHouseInfoTable.property}">
        				<option value="${property.title}" selected="selected">${property.title}</option>
        			</c:if>
        			<c:if test="${property.code != 00 && property.title != showHouseInfoTable.property}">
        				<option value="${property.title}">${property.title}</option>
        			</c:if>
				</c:forEach>
			</select><span>|</span> 
			
			<span>状态：</span>
         	<select class="getDecoration" name="housestatus">
        			<c:forEach items="${housestatusList}" var="housestatus">
        			<c:if test="${housestatus.code == 00 && housestatus.title == showHouseInfoTable.housestatus}">
        				<option value="" selected="selected">${housestatus.title}</option>
        			</c:if>
        			<c:if test="${housestatus.code == 00 && housestatus.title != showHouseInfoTable.housestatus}">
        				<option value="">${housestatus.title}</option>
        			</c:if>
        			<c:if test="${housestatus.code != 00 && housestatus.title == showHouseInfoTable.housestatus}">
        				<option value="${housestatus.title}" selected="selected">${housestatus.title}</option>
        			</c:if>
        			<c:if test="${housestatus.code != 00 && housestatus.title != showHouseInfoTable.housestatus}">
        				<option value="${housestatus.title}">${housestatus.title}</option>
        			</c:if>
				</c:forEach>
			</select><br>
			
			<span class="Btn" onclick="submitSearchUser()"><a target="usertable">提交</a></span>
         	<span class="Btn" onclick="cancel()"><a target="content-box">取消</a></span>
         	<span class="Btn" onclick="resetForm()">重置</span><br>
    	</form>
    </div>
</body>
<script>
	   	function submitSearchUser(){
	   		document.querySelector('.listuser').submit();
	   	}
	   	
	   	function resetForm(){
	   		document.querySelector('.listuser').reset();
	   	}
	   	
	   	function cancel(){
	   		window.location.href  = "${basePath}/information/listinfo?pageNum=1&pageSize=10";
	   	}
</script>
</html>