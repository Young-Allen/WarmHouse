package me.spring.entity;

public class HousePhoto extends IdEntity{
    private static final long serialVersionUID = 1L;

    private String code;
    private String photocode;
    private String title;
    private String location;
    private String description;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhotocode() {
		return photocode;
	}
	public void setPhotocode(String photocode) {
		this.photocode = photocode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public HousePhoto() {
		super();
	}
	public HousePhoto(String code, String photocode, String title, String location, String description) {
		super();
		this.code = code;
		this.photocode = photocode;
		this.title = title;
		this.location = location;
		this.description = description;
	}
	@Override
	public String toString() {
		return "HousePhoto [code=" + code + ", photocode=" + photocode + ", title=" + title + ", location=" + location
				+ ", description=" + description + "]";
	}
}
