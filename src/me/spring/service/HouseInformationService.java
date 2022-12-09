package me.spring.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.HouseInformation;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;

public interface HouseInformationService {
	public PageInfo<HouseInformation> listHouseInfoTable(HouseInformation houseinfo, int pageNum, int pageSize);
		
	public List<TotalTable> listTotalTable();

	public Result delete(String code);
	
	public HouseInformation getByCode(String code);
	
	public Result editSystemTable(SystemTable systemTable);
	
	public int update(HouseInformation houseinfo);
	
	public Result add(HouseInformation houseinfo);
}
