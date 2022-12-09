package me.spring.entity;

public class SystemTable extends IdEntity {
    private static final long serialVersionUID = 1L;
    
    private String tableName;
    private String code;
    private String title;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SystemTable(String tableName, String code, String title) {
		super();
		this.tableName = tableName;
		this.code = code;
		this.title = title;
	}
	public SystemTable() {
		super();
	}
	@Override
	public String toString() {
		return "SystemTable [tableName=" + tableName + ", code=" + code + ", title=" + title + "]";
	}
}
