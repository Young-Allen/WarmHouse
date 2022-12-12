package me.spring.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.spring.entity.HousePhoto;
import me.spring.entity.ImageInfo;
import me.spring.entity.User;
import me.spring.entity.UserHeadimg;

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

	@Select("SELECT username,\r\n" + 
			"	t_userheadimg.photocode,\r\n" + 
			"	t_img.savingfilename\r\n" + 
			"FROM\r\n" + 
			"	`t_userheadimg`,\r\n" + 
			"	`t_img` \r\n" + 
			"WHERE\r\n" + 
			"	t_userheadimg.photocode = t_img.photocode \r\n" + 
			"	AND username = #{user.username}")
	public UserHeadimg getHeadimg(@Param("user") User user);
}
