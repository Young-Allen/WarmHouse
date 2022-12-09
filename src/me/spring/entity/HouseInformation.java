package me.spring.entity;

public class HouseInformation extends IdEntity {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String title;
    private String suiteRoom;
    private String suiteHall;
    private String suiteBathroom;
    private String area;
    private String direction;
    private String floor;
    private String totalFloor;
    private String birth;
    private String housebelong;
    private String price;
    private String decoration;
    private String property;
    private String propertyrights;
    private String salesman;
    private String housestatus;
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
	public String getSuiteRoom() {
		return suiteRoom;
	}
	public void setSuiteRoom(String suiteRoom) {
		this.suiteRoom = suiteRoom;
	}
	public String getSuiteHall() {
		return suiteHall;
	}
	public void setSuiteHall(String suiteHall) {
		this.suiteHall = suiteHall;
	}
	public String getSuiteBathroom() {
		return suiteBathroom;
	}
	public void setSuiteBathroom(String suiteBathroom) {
		this.suiteBathroom = suiteBathroom;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getTotalFloor() {
		return totalFloor;
	}
	public void setTotalFloor(String totalFloor) {
		this.totalFloor = totalFloor;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getHousebelong() {
		return housebelong;
	}
	public void setHousebelong(String housebelong) {
		this.housebelong = housebelong;
	}
	public String getPropertyrights() {
		return propertyrights;
	}
	public void setPropertyrights(String propertyrights) {
		this.propertyrights = propertyrights;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getHousestatus() {
		return housestatus;
	}
	public void setHousestatus(String housestatus) {
		this.housestatus = housestatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public HouseInformation(String code, String title, String suiteRoom, String suiteHall, String suiteBathroom,
			String area, String direction, String floor, String totalFloor, String birth, String housebelong,
			String propertyrights, String decoration, String housestatus) {
		super();
		this.code = code;
		this.title = title;
		this.suiteRoom = suiteRoom;
		this.suiteHall = suiteHall;
		this.suiteBathroom = suiteBathroom;
		this.area = area;
		this.direction = direction;
		this.floor = floor;
		this.totalFloor = totalFloor;
		this.birth = birth;
		this.housebelong = housebelong;
		this.propertyrights = propertyrights;
		this.decoration = decoration;
		this.housestatus = housestatus;
	}
	
	public HouseInformation(String code, String title, String suiteRoom, String suiteHall, String suiteBathroom,
			String area, String direction, String floor, String totalFloor, String birth, String housebelong,
			String price, String decoration, String property, String propertyrights, String salesman,
			String housestatus) {
		super();
		this.code = code;
		this.title = title;
		this.suiteRoom = suiteRoom;
		this.suiteHall = suiteHall;
		this.suiteBathroom = suiteBathroom;
		this.area = area;
		this.direction = direction;
		this.floor = floor;
		this.totalFloor = totalFloor;
		this.birth = birth;
		this.housebelong = housebelong;
		this.price = price;
		this.decoration = decoration;
		this.property = property;
		this.propertyrights = propertyrights;
		this.salesman = salesman;
		this.housestatus = housestatus;
	}
	public HouseInformation() {
		super();
	}
	@Override
	public String toString() {
		return "HouseInformation [code=" + code + ", title=" + title + ", suiteRoom=" + suiteRoom + ", suiteHall="
				+ suiteHall + ", suiteBathroom=" + suiteBathroom + ", area=" + area + ", direction=" + direction
				+ ", floor=" + floor + ", totalFloor=" + totalFloor + ", birth=" + birth + ", housebelong="
				+ housebelong + ", price=" + price + ", decoration=" + decoration + ", property=" + property
				+ ", propertyrights=" + propertyrights + ", salesman=" + salesman + ", housestatus=" + housestatus
				+ "]";
	}
	
}
