package me.spring.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import me.spring.bean.Result;
import me.spring.dao.HousePhotoDAO;
import me.spring.dao.ImageInfoDAO;
import me.spring.entity.HousePhoto;
import me.spring.entity.ImageInfo;
import me.spring.service.HousePhotoService;
import me.spring.utils.RequestEntity;

@Service
public class HousePhotoImp implements HousePhotoService{
	@Autowired
    HousePhotoDAO housephotoDAO;
	@Autowired
    ImageInfoDAO imageinfoDAO;
	
	@Override
	public List<HousePhoto> getByCode(String code) {
		return housephotoDAO.selectByCode(code);
	}

	@Override
	@Transactional
	public Result add(HousePhoto housephoto, CommonsMultipartFile file, HttpServletRequest request) {
		Result res = new Result();
		ImageInfo imageinfo = new ImageInfo();
		Map<String, Object> result = new HashMap<String, Object>();
		
		housephotoDAO.insert(housephoto);
		result = RequestEntity.saveSingleFile(request, file);
		imageinfo = (ImageInfo)result.get("imageinfo");
		imageinfo.setPhotocode(housephoto.getPhotocode());
		imageinfoDAO.insert(imageinfo);
		
		res.setCode((Integer)result.get("resultCode"));
		res.setMsg((String)result.get("resultMsg"));
		res.setData((ImageInfo)result.get("imageinfo"));
		
		return res;

	}

	@Override
	public Result delete(String photocode) {
		Result result = new Result();
		try {
			ImageInfo imginfo = imageinfoDAO.getByPhotocode(photocode);
			RequestEntity.deleteServerFile(	imginfo.getSavingfilename());
			imageinfoDAO.delete(photocode);
			housephotoDAO.delete(photocode);
			result.setCode(1);
			result.setMsg("删除成功");
		}catch(Exception e) {
			result.setCode(-1);
            result.setMsg("删除失败");
            e.printStackTrace();  
		}
		return result;
	}

	@Override
	public ResponseEntity check(String photocode) {
		ImageInfo imginfo = imageinfoDAO.getByPhotocode(photocode);
		String savingfilename = imginfo.getSavingfilename();
		String originalfilename = imginfo.getOriginalfilename();
		String contenttype = imginfo.getContenttype();
		return RequestEntity.check(savingfilename, originalfilename, contenttype);
	}

	@Override
	public ResponseEntity download(String photocode) {
		ImageInfo imginfo = imageinfoDAO.getByPhotocode(photocode);
		String savingfilename = imginfo.getSavingfilename();
		String[] imgtype = imginfo.getContenttype().split("/");
		String contenttype = imgtype[imgtype.length - 1];
		HousePhoto housephoto = housephotoDAO.selectByPhotocode(photocode);
		String filename = housephoto.getTitle() + "." + contenttype;
		
		System.out.println("filename: " + filename);
		System.out.println("contenttype: " + contenttype);
		
		return RequestEntity.download(savingfilename, filename);
	}

	@Override
	@Transactional
	public Integer update(HousePhoto housephoto, CommonsMultipartFile file, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		int res1 = housephotoDAO.update(housephoto);
		int res2 = 0;
		
		if(!(file.getOriginalFilename().equals(""))) {
			result = RequestEntity.saveSingleFile(request, file);
			ImageInfo imageinfo = (ImageInfo)result.get("imageinfo");
			imageinfo.setPhotocode(housephoto.getPhotocode());
			res2 = imageinfoDAO.update(imageinfo);
		}
		return res1+res2;
	}

}
