package me.spring.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.spring.entity.HousePhoto;
import me.spring.entity.ImageInfo;

public interface ImageInfoDAO {
	@Insert("Insert into t_img(photocode,savingfilename,originalfilename,contenttype) "
			+ "values(#{imageinfo.photocode},#{imageinfo.savingfilename},#{imageinfo.originalfilename},#{imageinfo.contenttype})")
	public int insert(@Param("imageinfo") ImageInfo imageinfo);
	
	@Delete("Delete from t_img where photocode = #{photocode}")
	public int delete(@Param("photocode") String photocode);
	
	@Select("Select * from t_img where photocode = #{photocode}")
	public ImageInfo getByPhotocode(@Param("photocode") String photocode);
	
	@Update("<script>update t_img "+
            "<set>" + 
            "   <if test=\"imageinfo.savingfilename != '' and imageinfo.savingfilename != null\">" + 
            "          savingfilename = #{imageinfo.savingfilename}," + 
            "   </if>" + 
            "   <if test=\"imageinfo.originalfilename != '' and imageinfo.originalfilename != null\">" + 
            "          originalfilename = #{imageinfo.originalfilename}," + 
            "   </if>" + 
            "   <if test=\"imageinfo.contenttype != '' and imageinfo.contenttype != null\">" + 
            "          contenttype = #{imageinfo.contenttype}," + 
            "   </if>" + 
            "</set>  " +           
            " where photocode = #{imageinfo.photocode}" +     
            "</script>")
	public int update(@Param("imageinfo") ImageInfo imageinfo);
}
