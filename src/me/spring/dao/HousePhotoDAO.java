package me.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.spring.entity.HousePhoto;

public interface HousePhotoDAO {
	@Select("Select * from t_housephoto where code = #{code}")
	public List<HousePhoto> selectByCode(@Param("code") String code);
	
	@Select("Select * from t_housephoto where photocode = #{photocode}")
	public HousePhoto selectByPhotocode(@Param("photocode") String photocode);
	
	@Insert("Insert into t_housephoto(code,photocode,title,location,description) "
			+ "values(#{housephoto.code},#{housephoto.photocode},#{housephoto.title},#{housephoto.location},#{housephoto.description})")
	public int insert(@Param("housephoto") HousePhoto housephoto);
	
	@Delete("Delete from t_housephoto where photocode = #{photocode}")
	public int delete(@Param("photocode") String photocode);
	
	@Update("<script>update t_housephoto "+
            "<set>" + 
            "   <if test=\"housephoto.title != '' and housephoto.title != null\">" + 
            "          title = #{housephoto.title}," + 
            "   </if>" + 
            "   <if test=\"housephoto.location != '' and housephoto.location != null\">" + 
            "          location = #{housephoto.location}," + 
            "   </if>" + 
            "   <if test=\"housephoto.description != '' and housephoto.description != null\">" + 
            "          description = #{housephoto.description}," + 
            "   </if>" + 
            "</set>  " +           
            " where photocode = #{housephoto.photocode}" +     
            "</script>")
	public int update(@Param("housephoto") HousePhoto housephoto);
}
