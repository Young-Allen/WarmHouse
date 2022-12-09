package me.spring.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.HouseInformationDAO;
import me.spring.dao.SystemTableDAO;
import me.spring.dao.UserRoleDAO;
import me.spring.entity.HouseInformation;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;
import me.spring.service.HouseInformationService;
import me.spring.service.SystemTableService;
import me.spring.service.UserService;
import me.spring.utils.ReflectionUtils;

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
}
