package me.spring.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;

public interface SystemTableDAO {
	@Select("<script>select id,code,title\r\n" + 
			"    <if test=\"systable.tableName != null and systable.tableName != ''\"> \r\n" + 
			"       from ${systable.tableName}\r\n" + 
			"    </if>\r\n" + 
			"    <if test=\"systable.tableName == null or systable.tableName == ''\">\r\n" + 
			"       from `t_area`\r\n" + 
			"    </if>          \r\n" + 
			"    <where>\r\n" + 
			"        <if test=\"systable.code != null and systable.code != ''\">\r\n" + 
			"            and code like concat(concat('%',#{systable.code},'%'))\r\n" + 
			"        </if>\r\n" + 
			"        <if test=\"systable.title != null and systable.title != ''\">\r\n" + 
			"            and title like concat(concat('%',#{systable.title},'%'))\r\n" + 
			"        </if>\r\n" + 
			"    </where>" +
	"</script>")
	public List<SystemTable> getByFactors(@Param("systable") SystemTable systable);
	
	@Select("<script>select id,code,title\r\n" + 
			"    <if test=\"systable.tableName != null and systable.tableName != ''\"> \r\n" + 
			"       from ${systable.tableName}\r\n" + 
			"    </if>\r\n" + 
			"    <if test=\"systable.tableName == null or systable.tableName == ''\">\r\n" + 
			"       from `t_area`\r\n" + 
			"    </if>          \r\n" + 
			"    <where>\r\n" + 
			"        <if test=\"systable.code != null and systable.code != ''\">\r\n" + 
			"            and code like concat(concat('%',#{systable.code},'%'))\r\n" + 
			"        </if>\r\n" + 
			"    </where>" +
	"</script>")
	public SystemTable getByCode(@Param("systable") SystemTable systable);
	
	@Select("Select * from t_systable")
	public List<TotalTable> getTotalTable();
	
	@Delete("<script>Delete from \r\n" + 
			"    <if test=\"systable.tableName != null and systable.tableName != ''\"> \r\n" + 
			"       ${systable.tableName}\r\n" + 
			"    </if>\r\n" + 
			"    <if test=\"systable.tableName == null or systable.tableName == ''\">\r\n" + 
			"       `t_area`\r\n" + 
			"    </if>          \r\n" + 
			" where code = #{systable.code}</script>")
	public int delete(@Param("systable") SystemTable systable);
	
	@Update("<script>update"+
			"    <if test=\"systable.tableName != null and systable.tableName != ''\"> \r\n" + 
			"       ${systable.tableName}\r\n" + 
			"    </if>\r\n" + 
			"    <if test=\"systable.tableName == null or systable.tableName == ''\">\r\n" + 
			"       `t_area`\r\n" + 
			"    </if>          \r\n" + 
    	    "<set>" + 
    	    "   <if test=\"systable.title != '' and systable.title != null\">" + 
    	    "          title = #{systable.title}," + 
    	    "   </if>" + 
    	    "</set>  " +           
    	    " where code = #{systable.code}" +     
    	    "</script>")
	public int update(@Param("systable") SystemTable systable);
	
	@Select("Select * from ${tableName}")
	public List<SystemTable> selectSystable(String tableName);
	
	@Insert("Insert into ${systable.tableName}(code,title) values(#{systable.code},#{systable.title})")
	public int insert(@Param("systable") SystemTable systable);
}
