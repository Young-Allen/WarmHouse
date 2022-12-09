package me.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import me.spring.bean.Result;
import me.spring.entity.HousePhoto;

public interface HousePhotoService {
	public List<HousePhoto> getByCode(String code);

	public Result add(HousePhoto housephoto, CommonsMultipartFile file, HttpServletRequest request);
	
	public Result delete(String photocode);

	public ResponseEntity check(String photocode);
	
	public ResponseEntity download(String photocode);
	
	public Integer update(HousePhoto housephoto, CommonsMultipartFile file, HttpServletRequest request);

}


