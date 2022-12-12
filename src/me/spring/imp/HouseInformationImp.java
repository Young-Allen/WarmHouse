package me.spring.imp;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.HouseInformationDAO;
import me.spring.dao.SystemTableDAO;
import me.spring.dao.UserRoleDAO;
import me.spring.entity.HouseImgInfo;
import me.spring.entity.HouseInformation;
import me.spring.entity.HouseRange;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;
import me.spring.service.HouseInformationService;
import me.spring.service.SystemTableService;
import me.spring.service.UserService;
import me.spring.utils.Parse;
import me.spring.utils.ReflectionUtils;
import me.spring.utils.RequestEntity;

@Service
public class HouseInformationImp implements HouseInformationService{
	@Autowired
    SystemTableDAO systemTableDAO;
	@Autowired
	HouseInformationDAO houseInformationDAO;
	@Autowired
    UserService userService;
	 
	@Override
	public PageInfo<HouseInformation> listHouseInfoTable(HouseInformation houseInfo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<HouseInformation> houseInfoList = houseInformationDAO.getByFactors(houseInfo);
    	PageInfo<HouseInformation> houseInfoPageInfo = new PageInfo<>(houseInfoList, 5);
    	
    	System.out.println("houseInfoPageInfo = " + houseInfoPageInfo);
    	System.out.println("houseInfoList = " + (List<HouseInformation>)houseInfoPageInfo.getList());
		
    	return houseInfoPageInfo;
	}

	@Override
	public List<TotalTable> listTotalTable() {
		return systemTableDAO.getTotalTable();
	}
	
	@Override
    @Transactional
	public Result delete(String code) {
		Result result = new Result();
		int resLine = houseInformationDAO.delete(code);
		
		if(resLine > 0) {
			result.setCode(1);
    		result.setMsg("删除成功");
		}else {
			result.setCode(-1);
    		result.setMsg("删除失败");
		}
	    return result;
	}

	@Override
	public HouseInformation getByCode(String code) {
		return houseInformationDAO.selectByCode(code);
	}
	
	@Override
	public int update(HouseInformation houseinfo) {
		return houseInformationDAO.update(houseinfo);
	}
	
	@Override
	public Result editSystemTable(SystemTable systemTable) {
		Result result = new Result();
		try{
			List<SystemTable> systableList = systemTableDAO.getByFactors(systemTable);
			result.setCode(1);
	    	result.setData(systableList.get(0));
		}catch  (Exception e){
			result.setCode(-1);
			result.setMsg("查找失败");
		}
    	return result;
	}

	@Override
	public Result add(HouseInformation houseinfo) {
		Result result = new Result();
		ReflectionUtils.stringBlankToNull(houseinfo);
		try {
			houseInformationDAO.insert(houseinfo);
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("添加失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PageInfo<HouseInformation> RangeHouseInfoTable(HouseInformation houseinfo, HouseRange rangeInfo, int pageNum, int pageSize) {
		if(!houseinfo.getArea().equals("")) {
			if(houseinfo.getArea().contains("以下")) {
				String temp = houseinfo.getArea();
				rangeInfo.setMinArea("0");
				rangeInfo.setMaxArea(temp.substring(0, temp.indexOf("㎡")));
			}else if(houseinfo.getArea().contains("以上")) {
				String temp = houseinfo.getArea();
				rangeInfo.setMinArea(temp.substring(0, temp.indexOf("㎡")));
				rangeInfo.setMaxArea("999");
			}else {
				String temp = houseinfo.getArea();
				rangeInfo.setMinArea(temp.substring(0, temp.indexOf("-")));
				rangeInfo.setMaxArea(temp.substring(temp.indexOf("-") + 1, temp.indexOf("㎡")));
			}
		}else {
			rangeInfo.setMinArea("0");
			rangeInfo.setMaxArea("999");
		}
		
		
		String tempSuiteRoom = houseinfo.getSuiteRoom();
		if(!tempSuiteRoom.equals("")) {
			NumberFormat format = NumberFormat.getInstance(Locale.CHINA);
			String chineseNumStr = tempSuiteRoom.substring(0, tempSuiteRoom.indexOf("室"));
			Integer num = Parse.zh2arbaNum(chineseNumStr);
			houseinfo.setSuiteRoom(num.toString());
		}
		
		
		System.out.println(houseinfo);
		System.out.println(rangeInfo);
		PageHelper.startPage(pageNum, pageSize);
		List<HouseInformation> houseInfoList = houseInformationDAO.selectByRange(houseinfo,rangeInfo);
    	PageInfo<HouseInformation> houseInfoPageInfo = new PageInfo<>(houseInfoList, 5);
    	
		return houseInfoPageInfo;
	}

	@Override
	public Map<String, List<HouseImgInfo>> getImg(List<HouseInformation> houseinfoList) {
		Map<String, List<HouseImgInfo>> houseInfoMap = new HashMap<String, List<HouseImgInfo>>();
		for(HouseInformation item: houseinfoList) {
			String code = item.getCode();
			List<HouseImgInfo> HouseImgInfolist = houseInformationDAO.getHouseImg(code);
			for(HouseImgInfo info: HouseImgInfolist) {
				info.setDataBase64(RequestEntity.dataBase64(info.getSavingfilename()));
			}
			houseInfoMap.put(code, HouseImgInfolist);
		}
		System.out.println(houseInfoMap);
		return houseInfoMap;
	}

}
