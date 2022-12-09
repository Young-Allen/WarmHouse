package me.spring.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;

public interface SystemTableService {
	public PageInfo<SystemTable> listSysTable(SystemTable systable, int pageNum, int pageSize);
	
	public List<TotalTable> listTotalTable();

	public Result delete(SystemTable systemTable);
	
	public Result editSystemTable(SystemTable systemTable);
	
	public int update(SystemTable systemTable);
	
	public List<SystemTable> getSystable(String tableName);
	
	public int addSysTable(SystemTable systemTable);
	
}
