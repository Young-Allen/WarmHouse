package me.spring.entity;

public class UserHeadimg extends IdEntity{
	 private static final long serialVersionUID = 1L;

	private String username;
	private String photocode;
	private String dataBase64;
	private String savingfilename;
	
	
	public String getSavingfilename() {
		return savingfilename;
	}
	public void setSavingfilename(String savingfilename) {
		this.savingfilename = savingfilename;
	}
	public String getDataBase64() {
		return dataBase64;
	}

	public void setDataBase64(String dataBase64) {
		this.dataBase64 = dataBase64;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhotocode() {
		return photocode;
	}

	public void setPhotocode(String photocode) {
		this.photocode = photocode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public UserHeadimg() {
		super();
	}
	public UserHeadimg(String username, String photocode) {
		super();
		this.username = username;
		this.photocode = photocode;
	}

	public UserHeadimg(String username, String photocode, String dataBase64) {
		super();
		this.username = username;
		this.photocode = photocode;
		this.dataBase64 = dataBase64;
	}

	@Override
	public String toString() {
		return "UserHeadimg [username=" + username + ", photocode=" + photocode + ", dataBase64=" + dataBase64 + "]";
	}
}
