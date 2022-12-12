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
import me.spring.entity.UserHeadimg;

@Repository
@Mapper
public interface UserDAO {
    @Select("Select id,username,nickname,password,phone from t_user")
    public List<User> findAll();    
    
    @Update("<script>update t_user "+
    	    "<set>" + 
    	    "   <if test=\"user.username != '' and user.username != null\">" + 
    	    "          username = #{user.username}," + 
    	    "   </if>" + 
    	    "   <if test=\"user.nickname != '' and user.nickname != null\">" + 
    	    "          nickname = #{user.nickname}," + 
    	    "   </if>" + 
    	    "   <if test=\"user.phone != '' and user.phone != null\">" + 
    	    "          phone = #{user.phone}," + 
    	    "   </if>" + 
    	    "   <if test=\"user.password != '' and user.password != null\">" + 
    	    "          password = #{user.password}," + 
    	    "   </if>" + 
    	    "</set>  " +           
    	    " where username = #{user.username}" +     
    	    "</script>")
    public int update(@Param("user") User user);
    
    @Delete("Delete from t_user where id = #{user.id} ")
    public int delete(@Param("user") User user);
    
    @Select(" <script>Select *\r\n" + 
    		"        from t_user\r\n" + 
    		"        <where>\r\n" + 
    		"            <if test=\"user.id != null and user.id != ''\">\r\n" + 
    		"                and id = #{user.id}\r\n" + 
    		"            </if>\r\n" + 
    		"            <if test=\"user.username != null and user.username != ''\">\r\n" + 
    		"                and username = #{user.username}\r\n" + 
    		"            </if>\r\n" + 
    		"        </where></script>")
    public User selectByFactors(@Param("user") User user);
    
    @Select("Select * from t_user where id = #{user.id}")
    public User getById(@Param("user") User user);
    
    @Select("SELECT * from t_user WHERE username = #{user.username}")
    public User getByUsername(@Param("user") User user);
    
    @Select("SELECT * from t_user WHERE username = #{username}")
    public User getByStringUsername(@Param("username") String username);
    
    @Select("SELECT * from t_user where username = #{user.username} AND password = #{user.password}")
    public User getUserByLogin(@Param("user") User user);
    
    @Insert("Insert into t_user(username,nickname,password,phone,email) values(#{user.username},#{user.nickname},#{user.password},#{user.phone},#{user.email})")
    public int insert(@Param("user") User user);
    
    @Insert("Insert into t_userheadimg(username,photocode) values(#{userHeadimg.username},#{userHeadimg.photocode})")
    public int insertUserimg(@Param("userHeadimg") UserHeadimg userHeadimg);
    
    @Update("<script>update t_user\r\n" + 
    		"        <set>\r\n" + 
    		"            <if test=\"user.nickname != '' and user.nickname != null\">\r\n" + 
    		"                nickname = #{user.nickname},\r\n" + 
    		"            </if>\r\n" + 
    		"            <if test=\"user.phone != '' and user.phone != null\">\r\n" + 
    		"                phone = #{user.phone},\r\n" + 
    		"            </if>\r\n" + 
    		"            <if test=\"user.email != '' and user.email != null\">\r\n" + 
    		"                email = #{user.email},\r\n" + 
    		"            </if>\r\n" + 
    	    "            <if test=\"user.password != '' and user.password != null\">" + 
    	    "   	         password = #{user.password}," + 
		    "            </if>" + 
    		"        </set>\r\n" + 
    		"        <where>\r\n" + 
    		"            username = #{user.username}\r\n" + 
    		"        </where>"+
    		"</script>")
    public int updateUser(@Param("user") User user);
    
    @Select("<script>select *\r\n" + 
    		"        from t_user\r\n" + 
    		"        <where>\r\n" + 
    		"            <if test=\"user.username != null and user.username != ''\">\r\n" + 
    		"                and username like concat(concat('%',#{user.username},'%'))\r\n" + 
    		"            </if>\r\n" + 
    		"            <if test=\"user.nickname != null and user.nickname != ''\">\r\n" + 
    		"                and nickname like concat(concat('%',#{user.nickname},'%'))\r\n" + 
    		"            </if>\r\n" + 
    		"            <if test=\"user.phone != null and user.phone != ''\">\r\n" + 
    		"                and phone like concat(concat('%',#{user.phone},'%'))\r\n" + 
    		"            </if>" +
    		"            <if test=\"user.email != null and user.email != ''\">\r\n" + 
    		"                and email = #{user.email}\r\n" + 
    		"            </if>" +
    		"</where></script>")
    public List<User> getByFactors(@Param("user") User user);
}
