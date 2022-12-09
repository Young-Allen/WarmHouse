//显示隐藏个人信息/退出登录按钮
function showUserMenu(){
    let usermenu = document.querySelector('.user-menu')
    if (usermenu.style.display == "none") {
        usermenu.style.display = 'block'
    } else {
        usermenu.style.display = 'none'
    }
}
