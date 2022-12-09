package me.spring.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.spring.entity.HouseInformation;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;

public interface HouseInformationDAO {
	@Select("<script>select * from t_houseinformation\r\n" + 
			"    <where>\r\n" + 
			"        <if test=\"houseInfo.code != null and houseInfo.code != ''\">\r\n" + 
			"            and code like concat(concat('%',#{houseInfo.code},'%'))\r\n" + 
			"        </if>\r\n" + 
			"        <if test=\"houseInfo.title != null and houseInfo.title != ''\">\r\n" + 
			"            and title like concat(concat('%',#{houseInfo.title},'%'))\r\n" + 
			"        </if>\r\n" + 
			"        <if test=\"houseInfo.suiteRoom != null and houseInfo.suiteRoom != ''\">\r\n" + 
			"            and suiteRoom = #{houseInfo.suiteRoom}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.suiteHall != null and houseInfo.suiteHall != ''\">\r\n" + 
			"            and suiteHall = #{houseInfo.suiteHall}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.suiteBathroom != null and houseInfo.suiteBathroom != ''\">\r\n" + 
			"            and suiteBathroom = #{houseInfo.suiteBathroom}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.area != null and houseInfo.area != ''\">\r\n" + 
			"            and area like concat(concat('%',#{houseInfo.area},'%'))\r\n" + 
			"        </if>\r\n" + 
			"        <if test=\"houseInfo.direction != null and houseInfo.direction != ''\">\r\n" + 
			"            and direction = #{houseInfo.direction}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.floor != null and houseInfo.floor != ''\">\r\n" + 
			"            and floor = #{houseInfo.floor}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.totalFloor != null and houseInfo.totalFloor != ''\">\r\n" + 
			"            and totalFloor = #{houseInfo.totalFloor}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.birth != null and houseInfo.birth != ''\">\r\n" + 
			"            and birth = #{houseInfo.birth}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.housebelong != null and houseInfo.housebelong != ''\">\r\n" + 
			"            and housebelong = #{houseInfo.housebelong}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.price != null and houseInfo.price != ''\">\r\n" + 
			"            and price = #{houseInfo.price}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.decoration != null and houseInfo.decoration != ''\">\r\n" + 
			"            and decoration = #{houseInfo.decoration}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.property != null and houseInfo.property != ''\">\r\n" + 
			"            and property = #{houseInfo.property}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.propertyrights != null and houseInfo.propertyrights != ''\">\r\n" + 
			"            and propertyrights = #{houseInfo.propertyrights}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.salesman != null and houseInfo.salesman != ''\">\r\n" + 
			"            and salesman = #{houseInfo.salesman}\r\n" + 
			"        </if>\r\n" +
			"        <if test=\"houseInfo.housestatus != null and houseInfo.housestatus != ''\">\r\n" + 
			"            and housestatus = #{houseInfo.housestatus}\r\n" + 
			"        </if>\r\n" +
			"    </where>" +
	"</script>")
	public List<HouseInformation> getByFactors(@Param("houseInfo") HouseInformation houseInfo);
	
	@Select("select * from t_houseinformation where code = #{code}")
	public HouseInformation selectByCode(@Param("code") String code);
	
	@Delete("Delete from t_houseinformation where code = #{code}")
	public int delete(@Param("code") String code);
	
	
	@Select("Select * from t_systable")
	public List<TotalTable> getTotalTable();
	
	@Update("<script>update  t_houseinformation"+
    	    "<set>" + 
    	    "   <if test=\"houseinfo.title != '' and houseinfo.title != null\">" + 
    	    "          title = #{houseinfo.title}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.suiteRoom != '' and houseinfo.suiteRoom != null\">" + 
    	    "          suiteRoom = #{houseinfo.suiteRoom}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.suiteHall != '' and houseinfo.suiteHall != null\">" + 
    	    "          suiteHall = #{houseinfo.suiteHall}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.suiteBathroom != '' and houseinfo.suiteBathroom != null\">" + 
    	    "          suiteBathroom = #{houseinfo.suiteBathroom}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.area != '' and houseinfo.area != null\">" + 
    	    "          area = #{houseinfo.area}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.direction != '' and houseinfo.direction != null\">" + 
    	    "          direction = #{houseinfo.direction}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.floor != '' and houseinfo.floor != null\">" + 
    	    "          floor = #{houseinfo.floor}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.totalFloor != '' and houseinfo.totalFloor != null\">" + 
    	    "          totalFloor = #{houseinfo.totalFloor}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.birth != '' and houseinfo.birth != null\">" + 
    	    "          birth = #{houseinfo.birth}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.housebelong != '' and houseinfo.housebelong != null\">" + 
    	    "          housebelong = #{houseinfo.housebelong}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.price != '' and houseinfo.price != null\">" + 
    	    "          price = #{houseinfo.price}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.decoration != '' and houseinfo.decoration != null\">" + 
    	    "          decoration = #{houseinfo.decoration}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.property != '' and houseinfo.property != null\">" + 
    	    "          property = #{houseinfo.property}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.propertyrights != '' and houseinfo.propertyrights != null\">" + 
    	    "          propertyrights = #{houseinfo.propertyrights}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.salesman != '' and houseinfo.salesman != null\">" + 
    	    "          salesman = #{houseinfo.salesman}," + 
    	    "   </if>" + 
    	    "   <if test=\"houseinfo.housestatus != '' and houseinfo.housestatus != null\">" + 
    	    "          housestatus = #{houseinfo.housestatus}," + 
    	    "   </if>" + 
    	    "</set>  " +           
    	    " where code = #{houseinfo.code}" +     
    	    "</script>")
	public int update(@Param("houseinfo") HouseInformation houseinfo);
	
	@Insert("Insert into t_houseinformation(code,title,suiteRoom,suiteHall,suiteBathroom,area,direction,"
			+ "floor,totalFloor,birth,housebelong,price,decoration,property,propertyrights,salesman,housestatus) "
			+ "values(#{houseinfo.code},#{houseinfo.title},#{houseinfo.suiteRoom},#{houseinfo.suiteHall},"
			+ "#{houseinfo.suiteBathroom},#{houseinfo.area},#{houseinfo.direction},#{houseinfo.floor},"
			+ "#{houseinfo.totalFloor},#{houseinfo.birth},#{houseinfo.housebelong},#{houseinfo.price},"
			+ "#{houseinfo.decoration},#{houseinfo.property},#{houseinfo.propertyrights},#{houseinfo.salesman},"
			+ "#{houseinfo.housestatus})")  
	public int insert(@Param("houseinfo") HouseInformation houseinfo);
}
