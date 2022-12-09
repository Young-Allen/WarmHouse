package me.spring.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.UserDAO;
import me.spring.dao.UserRoleDAO;
import me.spring.entity.User;
import me.spring.entity.UserRole;
import me.spring.service.UserRoleService;
import me.spring.service.UserService;

@Service
public class UserRoleImp implements UserRoleService{
	@Autowired
    UserRoleDAO userRoleDAO;
	@Autowired
    UserDAO userDAO;
	@Autowired
    UserService userService;
	 
	@Override
	public PageInfo<UserRole> listUserRole(UserRole userrole, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserRole> userRoleList = userRoleDAO.getByFactors(userrole);
    	PageInfo<UserRole> userRolePageInfo = new PageInfo<>(userRoleList, 5);
    	
    	System.out.println("userRolePageInfo = " + userRolePageInfo);
    	System.out.println("userRoleList = " + (List<UserRole>)userRolePageInfo.getList());
		
    	return userRolePageInfo;
	}

	@Override
    @Transactional
	public Result delete(User acuser, User deuser) {
		Result result = new Result();
		int res = userService.judgeRole(acuser,deuser);
		if(res <= 0) {
			result.setCode(-1);
			result.setMsg("权限不足");
			return result;
		}
		System.out.println("res = " + res);
		
		List<UserRole> resUser = userRoleDAO.getUserRoleByUsername(deuser);
		
		System.out.println("resUser = " + resUser);
		if(resUser != null) {
			int resLine = userRoleDAO.delete(deuser);
			if(resLine > 0) {
				result.setCode(1);
	    		result.setMsg("删除成功");
			}else {
				result.setCode(-1);
	    		result.setMsg("删除失败");
			}
		}
	    return result;
	}

	@Override
	public Result editUserRole(User acuser, User deuser) {
		Result result = new Result();
		int res = userService.judgeRole(acuser,deuser);
		if(res <= 0) {
			result.setCode(-1);
			result.setMsg("权限不足");
			return result;
		}
		System.out.println("res = " + res);
		
		List<UserRole> userrole = userRoleDAO.getUserRoleByUsername(deuser);
    	
    	result.setCode(1);
    	result.setData(userrole.get(0));
    	
    	return result;
	}

	@Override
	public int update(UserRole userrole) {
		return userRoleDAO.update(userrole);
	}

	@Override
	public Result addUserRole(UserRole userrole) {
		Result result = new Result();

		List<UserRole> resRole = userRoleDAO.getByFactors(userrole);
		if(resRole.size() != 0) {
			result.setCode(-1);
			result.setMsg("用户权限已存在");
		}else {
			User resuser = userDAO.getByStringUsername(userrole.getUsername());
			userrole.setNickname(resuser.getNickname());
			userRoleDAO.addRole(userrole);
			result.setCode(1);
			result.setMsg("添加成功");
		}
		
		System.out.println(result);
		return result;
	}

}
