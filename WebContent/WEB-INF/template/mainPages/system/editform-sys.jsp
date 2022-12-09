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
.getRole{
	width: 280px;
	height: 40px;
	border: 1px solid #ccc;
	margin-top: 0px;
	margin-bottom: 25px;
	font-size: 15px;
}
.getRole:active{
	border: 1px solid #409eff;
}
.getRole option{
	text-align: center;
}
.edit-box{
	margin: 5px 30vw;
}
</style>
    <div class="edit-box">
    	<c:if test="${addFlag==1}">
    	<form id="form" class="form" method="post" action="${basePath}/systable/addSysTableSubmit">
    	</c:if>
    	<c:if test="${addFlag!=1}">
 	    <form id="form" class="form" method="post" action="${basePath}/systable/sysTableSubmit">
    	</c:if>
    		
    		<c:if test="${addFlag != 1}">
			<div class="form-control" style="display:none">
                <label for="systable">系统表</label>
                <select class="getRole" name="tableName" id="systable">
				  	<option value="${showSystemTable.tableName}"></option>
				</select>
            </div>    		
    		</c:if>
            
            <c:if test="${addFlag == 1}">
            <label for="code" style="color:#777">系统表</label>
            <select class="getRole" name="tableName">
	         	<c:forEach items="${totalTable}" var="totalTable">
         			<option value="${totalTable.tableTitle}">${totalTable.tableName}</option>
				</c:forEach>
			</select>
            </c:if>
           
            <div class="form-control">
                <label for="code">代码</label>
                <c:if test="${addFlag != 1}">
                <input type="text" id="code" placeholder="请输入代码" name="code" value="${showSystemTable.code}" readonly="readonly"/>
                </c:if>
                <c:if test="${addFlag == 1}">
                <input type="text" id="code" placeholder="请输入代码" name="code" value="${showSystemTable.code}"/>
                </c:if>
                <small>错误信息</small>
            </div>
            <div class="form-control">
                <label for="title">标题</label>
                <input type="text" id="title" placeholder="请输入标题" name="title" value="${showSystemTable.title}"/>
                <small>错误信息</small>
            </div>
            <button type="submit" onclick="subform()">提交</button>
            <button type="button" onclick="hideform()">取消</button>
        </form>
    </div>
</body>
<script>
	  	const form = document.getElementById('form');
	    const title = document.getElementById('title');

	    function hideform() {
	    	if(${addFlag == 1}){
				window.location.href  = "${basePath}/systable/listsystem?pageNum=1&pageSize=10";
	    	}else{
				window.location.href  = "${basePath}/systable/systemtableList";
	    	}
		}
	    
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

	    // Get fieldname
	    function getFieldName(input) {
	        const formControl = input.parentElement;
	        const label = formControl.querySelector('label');
	        return label.innerText;
	    }

	    // Event listeners
	    form.addEventListener('submit', function(e) {
	        e.preventDefault();
	        
	        if (!checkRequired([title])) {
	            form.submit();
	        }
	    });		
</script>
</html>