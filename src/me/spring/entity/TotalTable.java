package me.spring.entity;

public class TotalTable extends IdEntity {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String tableName;
    private String tableTitle;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableTitle() {
		return tableTitle;
	}
	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public TotalTable(String code, String tableName, String tableTitle) {
		super();
		this.code = code;
		this.tableName = tableName;
		this.tableTitle = tableTitle;
	}
	public TotalTable() {
		super();
	}
	@Override
	public String toString() {
		return "TotalTable [code=" + code + ", tableName=" + tableName + ", tableTitle=" + tableTitle + "]";
	}
    
    
}
