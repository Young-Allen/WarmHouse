package me.spring.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import me.spring.bean.Result;
import me.spring.entity.SystemTable;
import me.spring.entity.TotalTable;
import me.spring.service.SystemTableService;
import me.spring.service.UserRoleService;

@Controller
@RequestMapping("/systable")
public class SystemTableController {
	@Autowired
    UserRoleService userRoleService;
	@Autowired
    SystemTableService systemTableService;
	@Autowired
	private HttpSession session;
    
	@RequestMapping(value = "/systemtable", produces = "text/html;charset=utf-8")
    public String userTable() {
        return "mainPages/system/systemtable";
    }
	
	@RequestMapping(value = "/systemtableList", produces = "text/html;charset=utf-8")
    public String userTableList() {
        return "mainPages/system/systemtablelist";
    }
	 
	@RequestMapping(value = "/listsystem", produces = "text/html;charset=utf-8")
    public String listsystem(int pageNum, int pageSize, Model model) {
		SystemTable systable = new SystemTable();
		PageInfo<SystemTable> SystemTablePageInfo = systemTableService.listSysTable(systable,pageNum,pageSize);
		List<TotalTable> totalTable = systemTableService.listTotalTable();
		
		model.addAttribute("systemTablePageInfo", SystemTablePageInfo);
		model.addAttribute("systemTableList", SystemTablePageInfo.getList());
		model.addAttribute("totalTable", totalTable);

		session.setAttribute("systemTablePageInfo", SystemTablePageInfo);
		session.setAttribute("systemTableList", SystemTablePageInfo.getList());
		session.setAttribute("totalTable", totalTable);
		
        return "mainPages/system/systable";
    }
	
	@RequestMapping(value = "/systb", produces = "text/html;charset=utf-8")
    public String listSystemTable(SystemTable systemTable, int pageNum, int pageSize, Integer flag, Model model) {
		if(flag != null) {
			if((SystemTable)session.getAttribute("systemTableInfo") != null) {
				systemTable = (SystemTable)session.getAttribute("systemTableInfo");
			}
		}
		
		System.out.println(systemTable);
		PageInfo<SystemTable> SystemTablePageInfo = systemTableService.listSysTable(systemTable,pageNum,pageSize);
		
		session.setAttribute("systemTablePageInfo", SystemTablePageInfo);
		session.setAttribute("systemTableList", SystemTablePageInfo.getList());
		session.setAttribute("systemTableInfo", systemTable);
		
		return "mainPages/system/systemtable";
    }
	
	@RequestMapping(value = "/systemTableDelete", produces = "text/html;charset=utf-8")
    public String systemTableDelete(SystemTable systemTable, Model model) {
		Result result = new Result();
		result = systemTableService.delete(systemTable);
		
		model.addAttribute("msg", result.getMsg());
		model.addAttribute("show", 1);
		
		return "mainPages/system/systemtablelist";
    }
	
	
	@RequestMapping(value = "/systemTableEdit", produces = "text/html;charset=utf-8")
    public String systemTableEdit(SystemTable systemTable, Model model) {
		System.out.println(systemTable);
		model.addAttribute("showSystemTable", systemTable);
		return "mainPages/system/editform-sys";
    }
	
	
	@RequestMapping(value = "/sysTableSubmit", produces = "text/html;charset=utf-8")
    public String sysTableSubmit(SystemTable systemTable, Model model) {
		System.out.println(systemTable);
		
		systemTableService.update(systemTable);
		
		return "mainPages/system/systemtablelist";
    }
	
	@RequestMapping(value = "/systbAdd", produces = "text/html;charset=utf-8")
    public String systbAdd(Model model) {
		List<TotalTable> totalTable = systemTableService.listTotalTable();

		model.addAttribute("addFlag", 1);
		model.addAttribute("totalTable", totalTable);
		return "mainPages/system/editform-sys";
    }
	
	@RequestMapping(value = "/addSysTableSubmit", produces = "text/html;charset=utf-8")
    public String addSysTableSubmit(SystemTable systemTable, Model model) {
		systemTableService.addSysTable(systemTable);
		return "mainPages/system/systable";
    }
	
}
