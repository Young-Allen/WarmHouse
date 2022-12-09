package me.spring.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.UserDAO;
import me.spring.entity.SystemTable;
import me.spring.entity.User;
import me.spring.entity.UserRole;
import me.spring.service.SystemTableService;
import me.spring.service.UserRoleService;

@Controller
@RequestMapping("/userpermission")
public class UserPremissionController {
	@Autowired
    UserRoleService userRoleService;
	@Autowired
	SystemTableService systemTableService;
	@Autowired
	private HttpSession session;
	@Autowired
	UserDAO userDAO;
    
	@RequestMapping(value = "/permissionUserTable", produces = "text/html;charset=utf-8")
    public String userTable() {
        return "mainPages/permission/usertable";
    }
	
	@RequestMapping(value = "/userTableList", produces = "text/html;charset=utf-8")
    public String userTableList() {
        return "mainPages/permission/usertablelist";
    }
	 
	@RequestMapping(value = "/listuser", produces = "text/html;charset=utf-8")
    public String listuser(int pageNum, int pageSize, Model model) {
		UserRole userrole = new UserRole();
		System.out.println("UserRole = " + userrole);
		System.out.println("pageNum = " + pageNum);
		System.out.println("pageSize = " + pageSize);
		
		PageInfo<UserRole> userRolePageInfo = userRoleService.listUserRole(userrole,pageNum,pageSize);
		List<SystemTable> roleList = systemTableService.getSystable("t_sysrole");
		
		model.addAttribute("userRolePageInfo", userRolePageInfo);
		model.addAttribute("userRoleList", userRolePageInfo.getList());
		model.addAttribute("roleList", roleList);
		
		session.setAttribute("userRolePageInfo", userRolePageInfo);
		session.setAttribute("userRoleList", userRolePageInfo.getList());
		session.setAttribute("roleList", roleList);
		
        return "mainPages/permission/userpermission";
    }
	
	@RequestMapping(value = "/usertb", produces = "text/html;charset=utf-8")
    public String listUserTable(UserRole userrole, int pageNum, int pageSize, Integer flag, Model model) {
		if(flag != null) {
			if((UserRole)session.getAttribute("userroleInfo") != null) {
				userrole = (UserRole)session.getAttribute("userroleInfo");
			}
		}
		System.out.println(userrole);
		PageInfo<UserRole> userRolePageInfo = userRoleService.listUserRole(userrole,pageNum,pageSize);
		
		session.setAttribute("userRolePageInfo", userRolePageInfo);
		session.setAttribute("userRoleList", userRolePageInfo.getList());
		session.setAttribute("userroleInfo", userrole);
		
		return "mainPages/permission/usertable";
    }
	
	@RequestMapping(value = "/userRoleDelete", produces = "text/html;charset=utf-8")
    public String userDelete(String deusername, String actusername, Model model) {
		Result result = new Result();
		System.out.println("deusername = " + deusername);
		System.out.println("actusername = " + actusername);
		User deuser = new User(deusername);deuser.setId(null);
		User acuser = new User(actusername);acuser.setId(null);
		
		result = userRoleService.delete(acuser, deuser);
		
		System.out.println(result);
		
		model.addAttribute("msg", result.getMsg());
		model.addAttribute("show", 1);
		
		return "mainPages/permission/usertablelist";
    }
	
	
	@RequestMapping(value = "/userRoleEdit", produces = "text/html;charset=utf-8")
    public String userRoleEdit(String deusername, String actusername, Model model) {
		Result result = new Result();
		User deuser = new User(deusername);deuser.setId(null);
		User acuser = new User(actusername);acuser.setId(null);
		
		result = userRoleService.editUserRole(acuser,deuser);
		List<SystemTable> roleList = systemTableService.getSystable("t_sysrole");

		if(result.getCode() == -1) {
			model.addAttribute("msg", result.getMsg());
			model.addAttribute("show", 1);
			return "mainPages/permission/usertablelist";
		}else{
			System.out.println(result.getData());
			System.out.println(roleList);
			model.addAttribute("roleList", roleList);
			model.addAttribute("showUserRole", result.getData());
			return "mainPages/permission/editform-role";
		}
    }
	
	
	@RequestMapping(value = "/userSubmit", produces = "text/html;charset=utf-8")
    public String userSubmit(UserRole userrole, Model model) {
		System.out.println(userrole);
		userRoleService.update(userrole);
		return "mainPages/permission/usertablelist";
    }
	
	@RequestMapping(value = "/userRoleAdd", produces = "text/html;charset=utf-8")
    public String userRoleAdd(Model model) {
		List<SystemTable> roleList = systemTableService.getSystable("t_sysrole");
		List<User> allUser = userDAO.findAll();
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("allUser", allUser);
		
		return "mainPages/permission/addrole";
    }
	
	@RequestMapping(value = "/addUserRoleSubmit", produces = "text/html;charset=utf-8")
    public String addUserRoleSubmit(UserRole userrole, Model model) {
		System.out.println(userrole);
		Result result = new Result();
		result = userRoleService.addUserRole(userrole);
		
		model.addAttribute("showAddRoleInfo", 1);
		model.addAttribute("msg", result.getMsg());
		
		return "mainPages/permission/userpermission";
    }
	
	
}
