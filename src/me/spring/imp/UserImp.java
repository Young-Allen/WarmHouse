package me.spring.imp;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.ImageInfoDAO;
import me.spring.dao.UserDAO;
import me.spring.dao.UserRoleDAO;
import me.spring.entity.ImageInfo;
import me.spring.entity.User;
import me.spring.entity.UserHeadimg;
import me.spring.entity.UserRole;
import me.spring.service.UserService;
import me.spring.utils.JsonUtil;
import me.spring.utils.RequestEntity;
import me.spring.utils.SHA1;

@Service
public class UserImp implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    ImageInfoDAO imageInfoDAO;
    
    @Override
    public List<User> listAll() {      
        return userDAO.findAll();
    }
    
    @Override
    public int update(User user) {
    	if(user.getPassword() != "") {
    		try {
				user.setPassword(SHA1.encode(user.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	System.out.println("user = " + user);
    	
        return userDAO.updateUser(user);
    }
    
    //
    @Override
    @Transactional
    public Result delete(User acuser, User deuser) {
    	Result result = new Result();
    	int res = judgeRole(acuser,deuser);
    	if(res <= 0) {
    		result.setCode(-1);
    		result.setMsg("权限不足");
    		return result;
    	}
    	System.out.println("res = " + res);
    	
    	User resUser = userDAO.getByUsername(deuser);
    	
    	System.out.println("resUser = " + resUser);
    	if(resUser != null) {
    		Integer r2 = userRoleDAO.delete(resUser);
    		Integer r1 = userDAO.delete(resUser);
    		if(r1 > 0 && r2 > 0) {
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
	public User getByFactors(User user) {
		return userDAO.selectByFactors(user);
	}
	
	@Override
	public User getById(User user) {
		return userDAO.getById(user);
	}
	
	
	@Override
	@Transactional
	public Result login(User user) {
		Result result = new Result(); 
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		try {
    		user.setPassword(SHA1.encode(user.getPassword()));
    	}catch(Exception e){
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
    		 result.setCode(-1);
             result.setMsg("登录失败");
    		e.printStackTrace();
    	}
		
		try {
			List<UserRole> userRole = userRoleDAO.getUserRoleByUsername(user);
			User resuser = userDAO.getUserByLogin(user);
			userInfo.put("user",resuser);
			userInfo.put("userRole",userRole);
			result.setData(userInfo);
			result.setCode(0);
            result.setMsg("登录成功");
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            result.setCode(-1);
            result.setMsg("登录失败");
            e.printStackTrace();
		}
		
		return result;
	} 
	
	@Override
    public int add(User user) {
        return userDAO.insert(user);
    }
	@Override
	public User getByUsername(User user) {
		return userDAO.getByUsername(user);
	}
	@Override
	public int addRole(User user) {
		return userRoleDAO.insertRole(user);
	}
	
	
	@Override
	@Transactional
	public Result register(User user) {
		Result result = new Result(); 
		
		User resUser = userDAO.getByUsername(user);
    	if(resUser != null) {
    		result.setCode(-1);
    		result.setMsg("用户名称已存在！");
    		return result;
    	}
    	
    	//密码加密
    	try {
    		user.setPassword(SHA1.encode(user.getPassword()));
    	}catch(Exception e){
    		 e.printStackTrace();
    	}
    	
    	try {
            int resultCount = userDAO.insert(user);
            int roleResultCount = userRoleDAO.insertRole(user);
            if (resultCount > 0 && roleResultCount > 0) {
                result.setCode(0);
                result.setMsg("");
            } else {
                result.setCode(-1);
                result.setMsg("注册失败");
            }
        } catch (Exception e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            result.setCode(-1);
            result.setMsg("注册失败");
            e.printStackTrace();
        }
		return result;
	}
	
	//
	@Override
	public Result editUser(User acuser, User deuser) {
		Result result = new Result(); 
		
		int res = judgeRole(acuser,deuser);
    	if(res <= 0) {
    		result.setCode(-1);
    		result.setMsg("权限不足");
    		return result;
    	}
		
    	User user = userDAO.getByUsername(deuser);
    	
    	result.setCode(1);
    	result.setData(user);
    	
    	return result;
	}
	//
	@Override
	public String listAll(int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<User> userList = userDAO.findAll();
    	PageInfo<User> userPageInfo = new PageInfo<>(userList, 5);
    	
    	System.out.println("userPageInfo = " + userPageInfo);
    	
    	String str = null;
    	try {
			str = JsonUtil.toJson(userPageInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	@Override
	public List<User> listByFactors(User user) {
		return userDAO.getByFactors(user);
	}
	
	@Override
	public PageInfo<User> listUser(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userDAO.getByFactors(user);
    	PageInfo<User> userPageInfo = new PageInfo<>(userList, 5);
    	
    	System.out.println("userPageInfo = " + userPageInfo);
    	System.out.println("userList = " + (List<User>)userPageInfo.getList());
		
    	return userPageInfo;
	}
	//
	@Override
	public int judgeRole(User user1, User user2) {
		List<UserRole> role1 = userRoleDAO.getUserRoleByUsername(user1);
		List<UserRole> role2 = userRoleDAO.getUserRoleByUsername(user2);
		int role1Weight = 0;
		int role2Weight = 0;
		
		for(UserRole r1 : role1) {
			if(r1.getRole().equals("系统管理员")) {
				role1Weight += 2;
			}else if(r1.getRole().equals("管理员")) {
				role1Weight += 1;
			}else if(r1.getRole().equals("所有")) {
				role1Weight += 3;
			}
		}
		System.out.println(role1Weight);
		for(UserRole r1 : role2) {
			if(r1.getRole().equals("系统管理员")) {
				role2Weight += 2;
			}else if(r1.getRole().equals("管理员")) {
				role2Weight += 1;
			}else if(r1.getRole().equals("所有")) {
				role2Weight += 3;
			}
		}
		System.out.println(role1Weight);
		if(role1Weight > role2Weight) {
			return 1;
		}else if(role1Weight < role2Weight) {
			return -1;
		}
		return 0;
	}

	@Override
	public int addHeadimg(User user, CommonsMultipartFile file, HttpServletRequest request) {
		UserHeadimg userHeadimg = new UserHeadimg();
		ImageInfo imageinfo = new ImageInfo();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		userHeadimg.setPhotocode(uuid);
		userHeadimg.setUsername(user.getUsername());
		int res1 = userDAO.insertUserimg(userHeadimg);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result = RequestEntity.saveSingleFile(request, file);
		imageinfo = (ImageInfo)result.get("imageinfo");
		imageinfo.setPhotocode(uuid);
		int res2 = imageInfoDAO.insert(imageinfo);
		return res1 + res2;
	}

	@Override
	public UserHeadimg getHeadimg(User user) {
		UserHeadimg res = imageInfoDAO.getHeadimg(user);
		if(res == null) {
			return null;
		}else {
			return res;
		}
	}
}
