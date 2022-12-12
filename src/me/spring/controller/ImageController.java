package me.spring.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.spring.bean.Result;
import me.spring.dao.HousePhotoDAO;
import me.spring.entity.HousePhoto;
import me.spring.entity.ImageInfo;
import me.spring.entity.SystemTable;
import me.spring.service.HousePhotoService;
import me.spring.service.SystemTableService;
import me.spring.utils.RequestEntity;

@Controller
@RequestMapping("/houseimage")
public class ImageController {
	@Autowired
	HousePhotoService housePhotoService;
	@Autowired
    SystemTableService systemTableService;
	@Autowired
	private HttpSession session;
	@Autowired
    HousePhotoDAO housephotoDAO;
	
	@RequestMapping(value = "/add", produces = "text/html;charset=utf-8")
    public String add(String username, HousePhoto housephoto, @RequestParam("file1") CommonsMultipartFile file, HttpServletRequest request, Model model,  RedirectAttributes attr) {
		Result result = new Result();
		housephoto.setPhotocode(UUID.randomUUID().toString().replace("-", ""));
		result = housePhotoService.add(housephoto,file,request);
		attr.addAttribute("code", housephoto.getCode());
		attr.addAttribute("username", username);
		
        return "redirect:/information/housePhotoList";
    }
	
	@RequestMapping(value = "/delete", produces = "text/html;charset=utf-8")
    public String housePhotoSubmit(String username, String code, String photocode, Model model, RedirectAttributes attr) {
		Result result = new Result();
		result = housePhotoService.delete(photocode);
		attr.addAttribute("username", username);
		attr.addAttribute("code", code);
        return "redirect:/information/housePhotoList";
    }
	
	@RequestMapping(value = "/check", produces = "text/html")
    public ResponseEntity check(String photocode, Model model) {
        return housePhotoService.check(photocode);
    }
	
	@RequestMapping(value = "/download", produces = "text/html")
    public ResponseEntity download(String photocode, Model model) {
        return housePhotoService.download(photocode);
    }
	
	@RequestMapping(value = "/edit", produces = "text/html")
    public String edit(String username, String photocode, String code, Model model) {
		List<SystemTable> showhouseList = systemTableService.getSystable("t_showhouse");
		HousePhoto housephoto = housephotoDAO.selectByPhotocode(photocode);
		
		model.addAttribute("housephoto", housephoto);
		model.addAttribute("houselocationList", showhouseList);
		model.addAttribute("editflag", "1");
		model.addAttribute("code", code);
		model.addAttribute("houseinfoUsername", username);
		
		return "mainPages/information/editform-housephoto";
    }
	
	@RequestMapping(value = "/update", produces = "text/html")
	 public String update(String username, HousePhoto housephoto, @RequestParam("file1") CommonsMultipartFile file, HttpServletRequest request, Model model,RedirectAttributes attr) {
		housePhotoService.update(housephoto,file,request);
		attr.addAttribute("code", housephoto.getCode());
		attr.addAttribute("username", username);
        return "redirect:/information/housePhotoList";
    }
}
