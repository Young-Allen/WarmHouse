package me.spring.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.User;
import me.spring.entity.UserHeadimg;

public interface UserService {
    public int update(User user);
    public Result delete(User user1, User user2);
    
    public Result login(User user);
    public Result register(User user);
    public int add(User user);
    
    public User getByFactors(User user);
    
    public int judgeRole(User user1, User user2);
    
    public User getByUsername(User user);
    public User getById(User user);
    
    //use
    public Result editUser(User acuser, User deuser);
    
    public List<User> listAll();
    public String listAll(int pageNum, int pageSize);
    public List<User> listByFactors(User user); 
    
    //
    public PageInfo<User> listUser(User user, int pageNum, int pageSize);
    
    public int addRole(User user);
    
	public int addHeadimg(User user, CommonsMultipartFile file, HttpServletRequest request);
	
	public UserHeadimg getHeadimg(User user);
}