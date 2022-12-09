package me.spring.service;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.User;
import me.spring.entity.UserRole;

public interface UserRoleService {
	public PageInfo<UserRole> listUserRole(UserRole userrole, int pageNum, int pageSize);
	
	public Result delete(User acuser, User deuser);
	
	public Result editUserRole(User acuser, User deuser);
	
	public int update(UserRole userrole);
	
	public Result addUserRole(UserRole userrole);
	
}
