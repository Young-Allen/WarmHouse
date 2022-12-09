package me.spring.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.dao.SystemTableDAO;
import me.spring.dao.UserRoleDAO;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;
import me.spring.service.SystemTableService;
import me.spring.service.UserService;

@Service
public class SystemTableImp implements SystemTableService{
	@Autowired
    UserRoleDAO userRoleDAO;
	@Autowired
    SystemTableDAO systemTableDAO;
	@Autowired
    UserService userService;
	 
	@Override
	public PageInfo<SystemTable> listSysTable(SystemTable systable, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SystemTable> systableList = systemTableDAO.getByFactors(systable);
    	PageInfo<SystemTable> systablePageInfo = new PageInfo<>(systableList, 5);
    	
    	System.out.println("userRolePageInfo = " + systablePageInfo);
    	System.out.println("userRoleList = " + (List<SystemTable>)systablePageInfo.getList());
		
    	return systablePageInfo;
	}

	@Override
	public List<TotalTable> listTotalTable() {
		return systemTableDAO.getTotalTable();
	}
	
	@Override
    @Transactional
	public Result delete(SystemTable systemTable) {
		Result result = new Result();
		List<SystemTable> systableList = systemTableDAO.getByFactors(systemTable);
		
		System.out.println("systableList = " + systableList);
		if(systableList != null) {
			int resLine = systemTableDAO.delete(systemTable);
			if(resLine > 0) {
				result.setCode(1);
	    		result.setMsg("删除成功");
			}else {
				result.setCode(-1);
	    		result.setMsg("删除失败");
			}
		}
	    return result;
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
	public int update(SystemTable systable) {
		return systemTableDAO.update(systable);
	}

	@Override
	public List<SystemTable> getSystable(String tableName) {
		return systemTableDAO.selectSystable(tableName);

	}

	@Override
	public int addSysTable(SystemTable systemTable) {
		List<SystemTable> systbList = systemTableDAO.selectSystable(systemTable.getTableName());
		String tempcode = systbList.get(systbList.size()-1).getCode();
		Integer code = Integer.parseInt(tempcode) + 1;
		tempcode = code.toString();
		systemTable.setCode(tempcode);
		
		return systemTableDAO.insert(systemTable);
	}
}
