//生成分页页面
function setSimplePagination(resp){
	let totalPages = document.querySelector(".totalPages")
	let totalRecords = document.querySelector(".totalRecords")
	let totalPage = Math.ceil(resp.data.pages) 
	let totalRe = resp.data.total
	
	
	totalPages.innerText = totalPage
	totalRecords.innerText = totalRe
	
	console.log("totalPage = " + totalPage)
	console.log("totalRe = " + totalRe)
	
	let slp = new SimplePagination(totalPage)
	slp.init({
	    container: '.box',
	    maxShowBtnCount: 3,
	    onPageChange: state => {
	    	console.log('pagination change:', state.pageNumber)
	    	let pageContent = document.querySelector('.pageContent');
			let index = pageContent.selectedIndex ;            
			let pageSize = pageContent.options[index].value;
			
	    	changPageNum(state.pageNumber, pageSize)
	    }
	})
	document.getElementById('page-go').addEventListener('submit', e => {
		e.preventDefault()
		slp.gotoPage(+document.getElementById('page-num').value)
	})
}



function selChange(){
	let pageContent = document.querySelector('.pageContent');
	let index = pageContent.selectedIndex ;            
	let pageSize = pageContent.options[index].value;
	getusermanage(1, pageSize)
}

function editUser(userID){
	showUserModal()
	alert("点击了用户" + userID)
}

function deleteUser(userID){
	alert("点击了用户" + userID)
}

function submitSearchUser(action){
	let username = document.querySelector('.getUsername').value;
	let nickname = document.querySelector('.getNickname').value;
	let phone = document.querySelector('.getPhone').value;
	
	axios({
		"method":"post",
        "url":baseURL + "user/" + action,
        "data":{
        	username: username,
        	nickname: nickname,
        	phone: phone,
        }
	}).then(function (resp){
		console.log(resp.data)
		//生成表格
		setUserTable(resp);
		//生成分页页面
        setSimplePagination(resp)
	})
}

function resetSearchUser(){
	let username = document.querySelector('.getUsername').value = ""
	let nickname = document.querySelector('.getNickname').value = ""
	let phone = document.querySelector('.getPhone').value = ""
}