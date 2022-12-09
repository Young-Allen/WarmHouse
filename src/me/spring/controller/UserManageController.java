package me.spring.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.HouseInformation;
import me.spring.entity.User;
import me.spring.entity.UserRole;
import me.spring.service.UserService;

@Controller
@RequestMapping("/usermanage")
public class UserManageController {
	@Autowired
    UserService userService;
	@Autowired
	private HttpSession session;
	
	 
    @RequestMapping(value = "/userTable", produces = "text/html;charset=utf-8")
    public String userTable() {
        return "mainPages/manage/usertable";
    }
    
    @RequestMapping(value = "/showEditForm", produces = "text/html;charset=utf-8")
    public String showEditForm() {
        return "mainPages/editform";
    }
    
    @RequestMapping(value = "/userTableList", produces = "text/html;charset=utf-8")
    public String userTableList() {
        return "mainPages/manage/usertablelist";
    }
    
    @RequestMapping(value = "/toUserManage", produces = "text/html;charset=utf-8")
    public String toUserManage() {
        return "mainPages/manage/usermanage";
    }
    
	@RequestMapping(value = "/listuser", produces = "text/html;charset=utf-8")
    public String listuser(int pageNum, int pageSize, Model model) {
		User user = new User();
		PageInfo<User> userPageInfo = userService.listUser(user,pageNum,pageSize);
		
		model.addAttribute("userPageInfo", userPageInfo);
		model.addAttribute("userList", userPageInfo.getList());
		
		session.setAttribute("userPageInfo", userPageInfo);
		session.setAttribute("userList", userPageInfo.getList());
		
        return "redirect:/usermanage/toUserManage";
    }
	
	@RequestMapping(value = "/usertb", produces = "text/html;charset=utf-8")
    public String listUserTable(User user, int pageNum, int pageSize, Integer flag, Model model) {
		if(flag != null) {
			if((User)session.getAttribute("userInfo") != null) {
				user = (User)session.getAttribute("userInfo");
			}
		}

		System.out.println(user);
		PageInfo<User> userPageInfo = userService.listUser(user,pageNum,pageSize);
		
		session.setAttribute("userPageInfo", userPageInfo);
		session.setAttribute("userList", userPageInfo.getList());
		session.setAttribute("userInfo", user);
		
		return "mainPages/manage/usertable";
    }
	
	@RequestMapping(value = "/userDelete", produces = "text/html;charset=utf-8")
    public String userDelete(int Id, String username, String actusername, Model model) {
		Result result = new Result();
		User deuser = new User(username);deuser.setId(null);
		User acuser = new User(actusername);acuser.setId(null);
		
		result = userService.delete(acuser, deuser);
		
		System.out.println(result);
		
		model.addAttribute("msg", result.getMsg());
		model.addAttribute("show", 1);
		
		return "mainPages/manage/usertablelist";
    }
	
	@RequestMapping(value = "/userEdit", produces = "text/html;charset=utf-8")
    public String userEdit(String deusername, String actusername, Model model) {
		Result result = new Result();
		System.out.println("actusername : " + actusername);
		System.out.println("deusername : " + deusername);
		
		User deuser = new User(deusername);deuser.setId(null);
		User acuser = new User(actusername);acuser.setId(null);
		
		result = userService.editUser(acuser,deuser);
		
		if(result.getCode() == -1) {
			model.addAttribute("msg", result.getMsg());
			model.addAttribute("show", 1);
			return "mainPages/manage/usertablelist";
		}else{
			model.addAttribute("tempUser", result.getData());
			return "mainPages/manage/editform";
		}
    }
	
	
	@RequestMapping(value = "/userSubmit", produces = "text/html;charset=utf-8")
    public String userSubmit(User user, Model model) {
		userService.update(user);
		model.addAttribute("user", (User)session.getAttribute("user"));
		return "mainPages/manage/usertablelist";
    }
	
	@RequestMapping(value = "/userAdd", produces = "text/html;charset=utf-8")
    public String userAdd(Model model) {
		return "mainPages/manage/adduserform";
    }
	
	@RequestMapping(value = "/addUserSubmit", produces = "text/html;charset=utf-8")
    public String addUserSubmit(User user, Model model) {
		String tempPwd = user.getPassword();
    	Result result = new Result();
    	try {
    		result = userService.register(user);
    		if(result.getCode() == -1) {
    			model.addAttribute("showMsg", "1");
    			model.addAttribute("msg", result.getMsg());
    			return "mainPages/manage/adduserform";
    		}
    	}catch (Exception e){
    		result.setCode(-1);
            result.setMsg("注册失败");
            e.printStackTrace();  
    	}
        
		return "mainPages/manage/usermanage";
    }
}
