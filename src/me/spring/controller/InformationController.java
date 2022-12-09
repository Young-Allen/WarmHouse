package me.spring.controller;


import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.UserDAO;
import me.spring.entity.HouseInformation;
import me.spring.entity.HousePhoto;
import me.spring.entity.SystemTable;
import me.spring.entity.User;
import me.spring.service.HouseInformationService;
import me.spring.service.HousePhotoService;
import me.spring.service.SystemTableService;

@Controller
@RequestMapping("/information")
public class InformationController {
	@Autowired
    HouseInformationService houseInformationService;
	@Autowired
    SystemTableService systemTableService;
	@Autowired
	HousePhotoService housePhotoService;
	@Autowired
	UserDAO userDAO;
	@Autowired
	private HttpSession session;
    
	@RequestMapping(value = "/informationTable", produces = "text/html;charset=utf-8")
    public String informationtable() {
        return "mainPages/information/informationtable";
    }
	
	@RequestMapping(value = "/houseInfoList", produces = "text/html;charset=utf-8")
    public String houseInfoList() {
        return "mainPages/information/houseinfolist";
    }
	
	@RequestMapping(value = "/listinfo", produces = "text/html;charset=utf-8")
    public String listinfo(int pageNum, int pageSize, Model model) {
		HouseInformation houseinfo = new HouseInformation();
		System.out.println(houseinfo);
		
		PageInfo<HouseInformation> houseinfoPageInfo = houseInformationService.listHouseInfoTable(houseinfo,pageNum,pageSize);
		List<SystemTable> directionList = systemTableService.getSystable("t_direction");
		List<SystemTable> housebelongList = systemTableService.getSystable("t_housebelong");
		List<SystemTable> decorationList = systemTableService.getSystable("t_decoration");
		List<SystemTable> propertyrightsList = systemTableService.getSystable("t_propertyrights");
		List<SystemTable> propertyList = systemTableService.getSystable("t_property");
		List<SystemTable> housestatusList = systemTableService.getSystable("t_housestatus");
		
		session.setAttribute("directionList", directionList);
		session.setAttribute("housebelongList", housebelongList);
		session.setAttribute("decorationList", decorationList);
		session.setAttribute("propertyrightsList", propertyrightsList);
		session.setAttribute("propertyList", propertyList);
		session.setAttribute("housestatusList", housestatusList);
		
		session.setAttribute("houseinfoPageInfo", houseinfoPageInfo);
		session.setAttribute("houseinfoList", houseinfoPageInfo.getList());
        return "mainPages/information/infotable";
    }
	
	@RequestMapping(value = "/infotb", produces = "text/html;charset=utf-8")
    public String listSystemTable(HouseInformation houseinfo, int pageNum, int pageSize, Integer flag, Model model) {
		if(flag != null) {
			if((HouseInformation)session.getAttribute("houseinfo") != null) {
				houseinfo = (HouseInformation)session.getAttribute("houseinfo");
			}
		}
		
		PageInfo<HouseInformation> houseinfoPageInfo = houseInformationService.listHouseInfoTable(houseinfo,pageNum,pageSize);

		session.setAttribute("houseinfoPageInfo", houseinfoPageInfo);
		session.setAttribute("houseinfoList", houseinfoPageInfo.getList());
		session.setAttribute("houseinfo", houseinfo);
		
		return "mainPages/information/informationtable";
    }
	
	@RequestMapping(value = "/houseInfoDelete", produces = "text/html;charset=utf-8")
    public String systemTableDelete(String code, Model model) {
		Result result = new Result();
		result = houseInformationService.delete(code);
		
		model.addAttribute("msg", result.getMsg());
		model.addAttribute("show", 1);
		
		return "mainPages/information/houseinfolist";
    }
	
	
	@RequestMapping(value = "/houseInfoEdit", produces = "text/html;charset=utf-8")
    public String houseInfoEdit(String code, Model model) {
		List<User> allUser = userDAO.findAll();
		System.out.println(code);
		HouseInformation houseinfo = houseInformationService.getByCode(code);
		model.addAttribute("showHouseInfoTable", houseinfo);
		model.addAttribute("allUser", allUser);
		model.addAttribute("houseInfoFlag", -1);

		return "mainPages/information/editform-houseInfo";
    }
	
	
	@RequestMapping(value = "/houseInfoSubmit", produces = "text/html;charset=utf-8")
    public String houseInfoSubmit(HouseInformation houseinfo, Model model) {
		System.out.println(houseinfo);
		houseInformationService.update(houseinfo);
		return "mainPages/information/infotable";
    }
	
	@RequestMapping(value = "/housePhotoList", produces = "text/html;charset=utf-8")
    public String housePhotoList(String code, Model model) {
		List<HousePhoto> housePhotoList = housePhotoService.getByCode(code);
		model.addAttribute("housePhotoList", housePhotoList);
		session.setAttribute("code", code);
		return "mainPages/information/housephoto-list";
    }
	
	@RequestMapping(value = "/housePhotoEdit", produces = "text/html;charset=utf-8")
    public String housePhotoEdit(String code, Model model) {
		List<SystemTable> showhouseList = systemTableService.getSystable("t_showhouse");
		model.addAttribute("houselocationList", showhouseList);
		session.setAttribute("code", code);
		return "mainPages/information/editform-housephoto";
    }
	
	//添加
	@RequestMapping(value = "/addInfotb", produces = "text/html;charset=utf-8")
    public String addInfotb(String code, Model model) {
		List<User> allUser = userDAO.findAll();
		model.addAttribute("allUser", allUser);
		model.addAttribute("houseInfoFlag", 1);
		
		return "mainPages/information/editform-houseInfo";
    }
	
	@RequestMapping(value = "/addHouseInfoSubmit", produces = "text/html;charset=utf-8")
    public String addHouseInfoSubmit(HouseInformation houseinfo, Model model) {
		Result result = new Result();
		houseinfo.setCode(UUID.randomUUID().toString().replace("-", ""));
		System.out.println(houseinfo);
		result = houseInformationService.add(houseinfo);
		return "mainPages/information/infotable";
    }
	
}
