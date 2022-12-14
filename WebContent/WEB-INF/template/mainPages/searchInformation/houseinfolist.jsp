<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	<link rel="stylesheet" type="text/css" href="${basePath}/css/pa.css">
    <script type="text/javascript" src="${basePath}/js/qu.js"></script>
    <script type="text/javascript" src="${basePath}/js/paging.js"></script>
</head>
<body>

    <c:forEach items="${sessionScope.houseinfoList}" var="houseinfo">
    <div class="info-box">
    		<div class="img-box">
    		<c:if test="${hosueImgInfoMap[houseinfo.code][0].savingfilename == null}">
    			<img style="width: 100%; height: 100%; border-radius: 10px;" src="${basePath}/img/noimg.png"/>
    		</c:if>
    		<c:if test="${hosueImgInfoMap[houseinfo.code][0].savingfilename != null}">
    			<img style="width: 100%; height: 100%; border-radius: 20px;" src="data:image/jpg;base64,${hosueImgInfoMap[houseinfo.code][0].dataBase64}"/>
    		</c:if>
        	</div>
    		
    		<c:if test="${hosueImgInfoMap[houseinfo.code][0].savingfilename == null}">
   		        <div class="more"><a>更多照片</a></div>
    		</c:if>
    		<c:if test="${hosueImgInfoMap[houseinfo.code][0].savingfilename != null}">
   		        <div class="more"><a href="${basePath}/searchInformation/moreImg?code=${houseinfo.code}" target="content-box">更多照片</a></div>
    		</c:if>
        <div class="info-con">
            <div class="info-con-son1">
                <div class="con-son1-1">
                    <h2>${houseinfo.title}</h2>
                </div>
                <div class="con-son1-2">
                    <div style="font-size:xx-large; font-weight: 800;color: #ffff00">
                        <img src="${basePath}/img/money1.png">
                        <fmt:formatNumber value="${houseinfo.price}" type="currency" currencySymbol=""></fmt:formatNumber>万元
                    </div>
                    <div>单位房价：
                    <fmt:formatNumber value="${houseinfo.price / houseinfo.area * 10000}" type="currency" currencySymbol=""></fmt:formatNumber>元
                     </div>
                </div>
            </div>
            <div class="info-house">
                <div class="tag1">${houseinfo.suiteRoom}室${houseinfo.suiteHall}厅${houseinfo.suiteBathroom}卫</div>
                <div class="tag1">${houseinfo.area}㎡</div>
                <div class="tag1">${houseinfo.direction}</div>
                <div class="tag1">${houseinfo.floor}/${houseinfo.totalFloor}层</div>
                <div class="tag1">${houseinfo.birth}年建造</div>
                <div class="tag1">${houseinfo.housebelong}</div>
                <div class="tag1">${houseinfo.propertyrights}</div>
                <div class="tag1">${houseinfo.decoration}</div>
                <div class="tag1">${houseinfo.housestatus}</div>
            </div>
        </div>
   	</div>
    </c:forEach>
    
</body>
</html>