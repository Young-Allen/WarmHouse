package me.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import me.spring.dao.UserDAO;
import me.spring.entity.HouseImgInfo;
import me.spring.entity.HouseInformation;
import me.spring.entity.HouseRange;
import me.spring.entity.SystemTable;
import me.spring.service.HouseInformationService;
import me.spring.service.HousePhotoService;
import me.spring.service.SystemTableService;
import me.spring.utils.RequestEntity;

@Controller
@RequestMapping("/searchInformation")
public class SearchInformationController {
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
        return "mainPages/searchInformation/informationtable";
    }
	
	@RequestMapping(value = "/houseInfoList", produces = "text/html;charset=utf-8")
    public String houseInfoList() {
        return "mainPages/searchInformation/houseinfolist";
    }
	
	@RequestMapping(value = "/listinfo", produces = "text/html;charset=utf-8")
    public String listinfo(int pageNum, int pageSize, Model model) {
		HouseInformation houseinfo = new HouseInformation();
		PageInfo<HouseInformation> houseinfoPageInfo = houseInformationService.listHouseInfoTable(houseinfo,pageNum,pageSize);
		Map<String, List<HouseImgInfo>> hosueImgInfoMap = houseInformationService.getImg(houseinfoPageInfo.getList());

		List<SystemTable> housebelongList = systemTableService.getSystable("t_housebelong");
		List<SystemTable> suiteList = systemTableService.getSystable("t_suite");
		List<SystemTable> areaList = systemTableService.getSystable("t_area");
		List<SystemTable> directionList = systemTableService.getSystable("t_direction");
		List<SystemTable> decorationList = systemTableService.getSystable("t_decoration");
		List<SystemTable> propertyrightsList = systemTableService.getSystable("t_propertyrights");
		List<SystemTable> propertyList = systemTableService.getSystable("t_property");
		List<SystemTable> housestatusList = systemTableService.getSystable("t_housestatus");
		
		session.setAttribute("housebelongList", housebelongList);
		session.setAttribute("suiteList", suiteList);
		session.setAttribute("areaList", areaList);
		session.setAttribute("directionList", directionList);
		session.setAttribute("decorationList", decorationList);
		session.setAttribute("propertyrightsList", propertyrightsList);
		session.setAttribute("propertyList", propertyList);
		session.setAttribute("housestatusList", housestatusList);
		
		session.setAttribute("hosueImgInfoMap", hosueImgInfoMap);
		session.setAttribute("houseinfoPageInfo", houseinfoPageInfo);
		session.setAttribute("houseinfoList", houseinfoPageInfo.getList());
        return "mainPages/searchInformation/searchInfotable";
    }
	

	@RequestMapping(value = "/infotb", produces = "text/html;charset=utf-8")
    public String listSystemTable(HouseInformation houseinfo, HouseRange rangeInfo, int pageNum, int pageSize, Integer flag, Model model) {
		if(flag != null) {
			if((HouseInformation)session.getAttribute("houseinfoFlag") != null) {
				houseinfo = (HouseInformation)session.getAttribute("houseinfoFlag");
			}
			if((HouseRange)session.getAttribute("rangeInfoFlag") != null) {
				rangeInfo = (HouseRange)session.getAttribute("rangeInfoFlag");
			}
		}
		System.out.println(houseinfo);
		PageInfo<HouseInformation> houseinfoPageInfo = houseInformationService.RangeHouseInfoTable(houseinfo,rangeInfo,pageNum,pageSize);
		Map<String, List<HouseImgInfo>> hosueImgInfoMap = houseInformationService.getImg(houseinfoPageInfo.getList());
		
		session.setAttribute("houseinfoPageInfo", houseinfoPageInfo);
		session.setAttribute("houseinfoList", houseinfoPageInfo.getList());
		session.setAttribute("hosueImgInfoMap", hosueImgInfoMap);
		session.setAttribute("houseinfoFlag", houseinfo);
		session.setAttribute("rangeInfoFlag", rangeInfo);

		System.out.println(houseinfoPageInfo);
		System.out.println(houseinfoPageInfo.getList());
		
		return "mainPages/searchInformation/informationtable";
    }
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/moreImg", produces = "text/html;charset=utf-8")
    public String moreImg(String code, Model model) {
		Map<String, List<HouseImgInfo>> hosueImgInfoMap = (Map<String, List<HouseImgInfo>>)session.getAttribute("hosueImgInfoMap");
		List<HouseImgInfo> swiperImg = hosueImgInfoMap.get(code);
		model.addAttribute("swiperImg", swiperImg);
		model.addAttribute("swiperImgSize", swiperImg.size() - 1);
		return "mainPages/searchInformation/swiper";
    }
}
