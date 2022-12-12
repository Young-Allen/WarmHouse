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
    <title>轮播图</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/swiper.css">
</head>

<body>
	<div style="width: 90vw">
		<div class="back"><a href="${basePath}/searchInformation/listinfo?pageNum=1&pageSize=10">返回</a></div>
		<div class="container">
	        <!--  图片列表  -->
	        <ul class="ul-img">
	    		<c:forEach items="${swiperImg}" var="houseImg">
	        	<li class="li-img">
	        	    <img style="width: 100%; height: 100%" src="data:image/jpg;base64,${houseImg.dataBase64}"/>
	        	    <div class="imgInfo">[${houseImg.title}]${houseImg.location}—${houseImg.description}</div>
	            </li>
	        	</c:forEach>
	        </ul>
	
	        <!--  上一张、下一张按钮  -->
	        <div class="prevhead">
	            <span>&lt;&lt;</span>
	        </div>
	         <div class="prev">
	            <span>&lt;</span>
	        </div>
	        <div class="nexttail">
	            <span>&gt;&gt;</span>
	        </div>
			<div class="next">
	            <span>&gt;</span>
	        </div>
	        
	        <!-- 数字切换按钮 -->
	        <div class="num-box">
	            <ul class="num-ul">
		            <c:forEach items="${swiperImg}" var="houseImg"  varStatus="item">
		            <li data-index="${item.index}">${item.count}</li>
		        	</c:forEach>
	            </ul>
	        </div>
    	</div>
    	
    	
	</div>

</body>

<script>
	window.onload = function() {
	    // 获取元素节点
	    var containerDom = document.getElementsByClassName("container")[0]; // 容器
	    var ulDom = document.getElementsByClassName("ul-img")[0]; // 图片盒子
	    var prevheadDom = document.getElementsByClassName("prevhead")[0].firstElementChild; // 第一张按钮
	    var prevDom = document.getElementsByClassName("prev")[0].firstElementChild; // 上一张按钮
	    var nexttailDom = document.getElementsByClassName("nexttail")[0].firstElementChild; // 最后一张按钮
	    var nextDom = document.getElementsByClassName("next")[0].firstElementChild; // 下一张按钮
	    var numUlDom = document.getElementsByClassName("num-ul")[0]; // 数字按钮父级容器
	    var numList = document.getElementsByClassName("num-ul")[0].getElementsByTagName("li"); // 数字切换按钮列表
	
	    // 定义全局变量
	    var currentIndex = 0; // 当前显示的图片索引
	    numList[currentIndex].style.backgroundColor = "#ccc"; // 默认选中第一个数字
	    prevheadDom.addEventListener("click", firstFun);
	    // 上一张
	    prevDom.addEventListener("click", prevFun);
	    // 下一张
	    nextDom.addEventListener("click", nextFun);
	    nexttailDom.addEventListener("click", tailFun);
	    // 数字按钮点击事件
	    numUlDom.addEventListener("click", numClick);
	
	    function firstFun(){
	    	ulDom.style.transition = "0.5s";
	        let index = 0;
	        if (index == undefined) {
	            return;
	        }
	        numList[currentIndex].style.backgroundColor = ""; // 清空上一个按钮的样式
	        currentIndex = Number(index);
	        numList[currentIndex].style.backgroundColor = "#ccc";
	        ulDom.style.left = `-\${currentIndex * 800}px`;
	    }
	    
	    // 切换上一张
	    function prevFun() {
	        ulDom.style.transition = "0.5s";
	        numList[currentIndex].style.backgroundColor = ""; // 清空上一个按钮的样式
	        if (currentIndex === 0) {
	            ulDom.style.transition = "0s"; // 为了实现无缝滚动，清除动画
	            currentIndex = ${swiperImgSize};
	        } else {
	            --currentIndex;
	        }
	        ulDom.style.left = `-\${currentIndex * 800}px`;
	        numList[currentIndex].style.backgroundColor = "#ccc";
	    }
	
	    function tailFun(){
	    	ulDom.style.transition = "0.5s";
	        let index = ${swiperImgSize};
	        if (index == undefined) {
	            return;
	        }
	        numList[currentIndex].style.backgroundColor = ""; // 清空上一个按钮的样式
	        currentIndex = Number(index);
	        numList[currentIndex].style.backgroundColor = "#ccc";
	        ulDom.style.left = `-\${currentIndex * 800}px`;
	    }
	    
	    // 切换下一张
	    function nextFun() {
	        ulDom.style.transition = "0.5s";
	        numList[currentIndex].style.backgroundColor = ""; // 清空上一个按钮的样式
	        if (currentIndex === ${swiperImgSize}) {
	            ulDom.style.transition = "0s"; // 为了实现无缝滚动，清除动画
	            currentIndex = 0; // 重新播放第一张
	        } else {
	            ++currentIndex;
	        }
	        ulDom.style.left = `-\${currentIndex * 800}px`;
	        numList[currentIndex].style.backgroundColor = "#ccc"; // 设置按钮选中样式
	    }
	
	    // 数字按钮点击事件
	    function numClick(e) {
	        ulDom.style.transition = "0.5s";
	        let index = e.target.dataset.index;
	        if (index == undefined) {
	            return;
	        }
	        numList[currentIndex].style.backgroundColor = ""; // 清空上一个按钮的样式
	        currentIndex = Number(index);
	        numList[currentIndex].style.backgroundColor = "#ccc";
	        ulDom.style.left = `-\${currentIndex * 800}px`;
	    }
	
	}
</script>
</html>