package me.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import me.spring.entity.User;
import me.spring.entity.UserRole;

@Repository
@Mapper
public interface UserRoleDAO {
	@Insert("Insert into t_userrole(username,nickname,role) values(#{user.username},#{user.nickname},'普通用户')")
	public int insertRole(@Param("user") User user);
	
	@Insert("Insert into t_userrole(username,nickname,role) values(#{userrole.username},#{userrole.nickname},#{userrole.role})")
	public int addRole(@Param("userrole") UserRole userrole);
	
	@Select("select * from t_userrole where username = #{user.username}")
	public List<UserRole> getUserRoleByUsername(@Param("user") User user);
	
	@Delete("Delete from t_userrole where username = #{user.username}")
	public int delete(@Param("user") User user);
    
	@Update("<script>update t_userrole "+
    	    "<set>" + 
    	    "   <if test=\"userrole.username != ''\">" + 
    	    "          username = #{userrole.username}," + 
    	    "   </if>" + 
    	    "   <if test=\"userrole.nickname != ''\">" + 
    	    "          nickname = #{userrole.nickname}," + 
    	    "   </if>" + 
    	    "   <if test=\"userrole.role != ''\">" + 
    	    "          role = #{userrole.role}," + 
    	    "   </if>" + 
    	    "</set>  " +           
    	    " where username = #{userrole.username}" +     
    	    "</script>")
	public int update(@Param("userrole") UserRole userrole);
	
	@Select("<script>select *\r\n" + 
	"        from t_userrole\r\n" + 
	"        <where>\r\n" + 
	"            <if test=\"userrole.username != null and userrole.username != ''\">\r\n" + 
	"                and username like concat(concat('%',#{userrole.username},'%'))\r\n" + 
	"            </if>\r\n" + 
	"            <if test=\"userrole.role != null and userrole.role != ''\">\r\n" + 
	"                and role = #{userrole.role}\r\n" + 
	"            </if>\r\n" + 
	"        </where>" +
	"</script>")
	public List<UserRole> getByFactors(@Param("userrole") UserRole userrole);
	 
}
