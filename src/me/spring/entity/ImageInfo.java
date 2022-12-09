package me.spring.entity;

public class ImageInfo extends IdEntity {
    private static final long serialVersionUID = 1L;
    
    private String photocode;
    private String savingfilename;
    private String originalfilename;
    private String contenttype;
    
	public String getPhotocode() {
		return photocode;
	}
	public void setPhotocode(String photocode) {
		this.photocode = photocode;
	}
	public String getSavingfilename() {
		return savingfilename;
	}
	public void setSavingfilename(String savingfilename) {
		this.savingfilename = savingfilename;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ImageInfo() {
		super();
	}
	public ImageInfo(String photocode, String savingfilename, String originalfilename, String contenttype) {
		super();
		this.photocode = photocode;
		this.savingfilename = savingfilename;
		this.originalfilename = originalfilename;
		this.contenttype = contenttype;
	}
	@Override
	public String toString() {
		return "Image [photocode=" + photocode + ", savingfilename=" + savingfilename + ", originalfilename="
				+ originalfilename + ", contenttype=" + contenttype + "]";
	}
}
