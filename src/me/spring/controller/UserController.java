package me.spring.controller;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.spring.bean.Result;
import me.spring.dao.UserDAO;
import me.spring.entity.User;
import me.spring.entity.UserHeadimg;
import me.spring.entity.UserRole;
import me.spring.service.UserService;
import me.spring.utils.MailUtils;
import me.spring.utils.RequestEntity;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    HttpSession session;
    
    @RequestMapping(value = "/toLogin", produces = "text/html;charset=utf-8")
    public String toLogin() {
        return "login/login";
    }
    
    @RequestMapping(value = "/toForget", produces = "text/html;charset=utf-8")
    public String toForget() {
        return "login/forget";
    }
    
    @RequestMapping(value = "/judge", produces = "text/html;charset=utf-8")
    public String judge(String isRedirect, Model model) {
    	System.out.println(isRedirect);
    	if(isRedirect.equals("1")) {
    		model.addAttribute("msg", "用户不存在/密码错误");
    		model.addAttribute("showMsg", "1");
    	}else if(isRedirect.equals("2")){
    		model.addAttribute("msg", "登录成功");
    		model.addAttribute("showMsg", "2");
    	}
        return "login/login";
    }
    
    @RequestMapping(value = "/failedReg", produces = "text/html;charset=utf-8")
    public String failedReg(Result result, Model model) {
    	System.out.println(result);
    	
    	model.addAttribute("user", result.getData());
		model.addAttribute("msg", result.getMsg());
		model.addAttribute("showMsg", "1");
		model.addAttribute("showLogo", "1");
        return "login/login";
    }
    
    @RequestMapping(value = "/index", produces = "text/html;charset=utf-8")
    public String toIndex() {
        return "mainPages/index";
    }
    
    @RequestMapping(value = "/welcome", produces = "text/html;charset=utf-8")
    public String welcome() {
        return "mainPages/welcome";
    }
    
    @RequestMapping(value = "/showEditForm", produces = "text/html;charset=utf-8")
    public String showEditForm() {
        return "mainPages/editform";
    }
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", produces = "text/html;charset=utf-8")
    public String login(User user, String remember, Model model, HttpServletResponse response, RedirectAttributes attr) {
		String pwd = user.getPassword();
    	Result result = new Result();
    	result =  userService.login(user);
		HashMap<String, Object> userInfo = (HashMap<String, Object>) result.getData();
    	User resUser = (User) userInfo.get("user");
		List<UserRole> userRole = (List<UserRole>) userInfo.get("userRole");
    	
    	if(resUser == null) {//没有查询到登录的用户信息
    		attr.addAttribute("isRedirect", "1");
    		Cookie c1 = new Cookie("username", user.getUsername());
			Cookie c2 = new Cookie("password", pwd);
			c1.setMaxAge(0);
			c2.setMaxAge(0);
			response.addCookie(c1);
			response.addCookie(c2);
			
    		return "redirect:/user/judge";
    	}
    	
    	if(remember != null) {
    		Cookie c1 = new Cookie("username", user.getUsername());
			Cookie c2 = new Cookie("password", pwd);
			c1.setMaxAge(259200);
			c2.setMaxAge(259200);
			response.addCookie(c1);//添加Cookie
			response.addCookie(c2);
			System.out.println("勾选了记住密码");
		}
    	
    	UserHeadimg userHeadimg = userService.getHeadimg(user);
    	if(userHeadimg != null) {
        	userHeadimg.setDataBase64(RequestEntity.dataBase64(userHeadimg.getSavingfilename()));
    	}
    	
		session.setAttribute("userHeadimg", userHeadimg);
		session.setAttribute("user",resUser);
    	session.setAttribute("userRole",userRole);
    	session.setMaxInactiveInterval(-1);
    	
		attr.addAttribute("isRedirect", "2");
		return "redirect:/user/judge";
    }
    
    @RequestMapping(value = "/register", produces = "text/html;charset=utf-8")
    public String register(User user, Model model,RedirectAttributes attr) {
    	String tempPwd = user.getPassword();
    	Result result = new Result();
    	try {
    		result = userService.register(user);
    		if(result.getCode() == -1) {
    			user.setPassword(tempPwd);
    			result.setData(user);
    			attr.addFlashAttribute("result", result);
    	        return "redirect:/user/failedReg";
    		}
    	}catch (Exception e){
    		result.setCode(-1);
            result.setMsg("注册失败");
            e.printStackTrace();  
    	}
        
        user.setPassword("");
        model.addAttribute("user", user);
        
    	return "login/login";
    }
    
    @RequestMapping(value = "/logout", produces = "text/html;charset=utf-8")
    public String logout(){
        session.invalidate();
        return "redirect:/user/toLogin";
    }
    
    @RequestMapping(value = "/getForgetCode", produces = "text/html;charset=utf-8")
    public String getForgetCode(User userForget, Model model) throws Exception{
    	System.out.println(userForget);
    	List<User> resuser = userDAO.getByFactors(userForget);
    	if(resuser.size() == 0) {
        	model.addAttribute("showMsg", "1");
        	model.addAttribute("msg", "用户名或邮箱错误");
        	return "login/forget";
    	}else {
    		String captcha = RandomStringUtils.randomAlphanumeric(10);
    		session.setAttribute("captcha", captcha);
    		session.setAttribute("changePwdByUsername", userForget.getUsername());
    		System.out.println("开始发送邮件" + captcha);
        	MailUtils.sendMsg(userForget.getEmail(), "重置密码", captcha);
        	model.addAttribute("showMsg", "0");
        	model.addAttribute("showLogo", "1");
        	
        	return "login/forget";
    	}
    }
    
    @RequestMapping(value = "/changePassword", produces = "text/html;charset=utf-8")
    public String changePassword(String password, String captcha, Model model){
    	String tempcaptcha = (String)session.getAttribute("captcha");
    	if(captcha.equals(tempcaptcha)) {
    		String changePwdByUsername = (String)session.getAttribute("changePwdByUsername");
    		User user = new User(changePwdByUsername, password);
    		int res = userService.update(user);
    		if(res > 0) {
    			session.removeAttribute("changePwdByUsername");
    			session.removeAttribute("captcha");
    			model.addAttribute("showMsg", "1");
    			model.addAttribute("msg", "修改成功");
    		}
    		return "redirect:/user/toLogin";
    	}else {
    		model.addAttribute("showMsg", "1");
    		model.addAttribute("msg", "验证码错误");
        	model.addAttribute("showLogo", "1");
    		System.out.println("验证码错误");
    		return "login/forget";
    	}
    }
    
    
    @RequestMapping(value = "/userInfo", produces = "text/html;charset=utf-8")
    public String userInfo(User user, Model model){
    	System.out.println("user" + user);
    	User resuser = userService.getByFactors(user);
    	System.out.println("resuser" + resuser);

    	model.addAttribute("showUser", resuser);
    	model.addAttribute("showUserInfo", 1);
        return "mainPages/userInfo";
    }
    
    
    @RequestMapping(value = "/userSubmit", produces = "text/html;charset=utf-8")
    public String userSubmit(User user, Model model){
    	System.out.println(user);
    	userService.update(user);
    	if(user.getPassword() != "") {
    		session.invalidate();
    		return "login/login";
    	}
    	User resuser = userService.getByFactors(user);
		session.setAttribute("user", resuser);

        return "mainPages/welcome";
    }
    

    @RequestMapping(value = "/userHeadimg", produces = "text/html;charset=utf-8")
    public String userHeadimg(String username, Model model){
    	model.addAttribute("username", username);
        return "mainPages/userHeadimg";
    }
    
    @RequestMapping(value = "/userHeadimgSubmit", produces = "text/html")
    public String userHeadimgSubmit(@RequestParam("file1") CommonsMultipartFile file, User user, Model model, HttpServletRequest request){
		userService.addHeadimg(user,file,request);
    	
    	UserHeadimg userHeadimg = userService.getHeadimg(user);
    	if(userHeadimg != null) {
        	userHeadimg.setDataBase64(RequestEntity.dataBase64(userHeadimg.getSavingfilename()));
    	}
		session.setAttribute("userHeadimg", userHeadimg);
        return "mainPages/welcome";
    }
}