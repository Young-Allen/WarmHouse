    let logo = document.querySelector('.logo')
    const loginform = document.getElementById('login-form');
    const loginUsername = document.getElementById('login-username');
    const loginPassword = document.getElementById('login-password');

    const form = document.getElementById('form');
    const username = document.getElementById('username');
    const nickname = document.getElementById('nickname');
    const phone = document.getElementById('phone');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const password2 = document.getElementById('password2');

    function toregister() {
        logo.style.transform = 'translateX(0%)';
    }
    function toLogin() {
        logo.style.transform = 'translateX(100%)';
    }

    // 显示错误信息
    function showError(input, message) {
        const formControl = input.parentElement;
        formControl.className = 'form-control error';
        const small = formControl.querySelector('small');
        small.innerText = message + '必填';
    }

    // 显示成功消息
    function showSuccess(input) {
        const formControl = input.parentElement;
        formControl.className = 'form-control success';
    }

    //检查email
    function checkEmail(input) {
    	let isRequired = false;
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (re.test(input.value.trim())) {
            showSuccess(input);
            isRequired = true;
        } else {
            showError(input, '邮箱不合法');
        }
        return isRequired;
    }

    // 检查必填字段
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

    // 检查字段长度
    function checkLength(input, min, max) {
        if (input.value.length < min) {
            showError(input, `
                                $ {
                                    getFieldName(input)
                                }
                                至少 $ {
                                    min
                                }
                                个字符 `);
        } else if (input.value.length > max) {
            showError(input, `
                                $ {
                                    getFieldName(input)
                                }
                                至多 $ {
                                    max
                                }
                                个字符 `);
        } else {
            showSuccess(input);
        }
    }

    // 检查两次密码匹配
    function checkPasswordsMatch(input1, input2) {
        if (input1.value !== input2.value) {
            showError(input2, '密码不匹配');
            return false;
        } else {
            return true;
        }
    }

    // 获取字段
    function getFieldName(input) {
        const formControl = input.parentElement;
        const label = formControl.querySelector('label');
        return label.innerText;
    }

    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // if (!checkRequired([username, email, password, password2])) {
        //     checkLength(username, 3, 15);
        //     checkLength(password, 6, 25);
        //     checkEmail(email);
        //     checkPasswordsMatch(password, password2);
        // }

        if (!checkRequired([username, nickname, phone, email, password, password2]) 
        		&& checkPasswordsMatch(password, password2) && checkEmail(email)) {
        		form.submit()
        } else {
            console.log("登录失败");
        }
    });

    loginform.addEventListener('submit', function(e) {
        e.preventDefault();
        if (!checkRequired([loginUsername, loginPassword])) {
            loginform.submit();
        }
    });
    
