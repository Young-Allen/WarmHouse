package me.spring.entity;

public class HouseRange {
    private static final long serialVersionUID = 1L;

    private String minArea;
    private String maxArea;
    private String minBirth;
    private String maxBirth;
    private String minPrice;
    private String maxPrice;
	public String getMinArea() {
		return minArea;
	}
	public void setMinArea(String minArea) {
		this.minArea = minArea;
	}
	public String getMaxArea() {
		return maxArea;
	}
	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}
	public String getMinBirth() {
		return minBirth;
	}
	public void setMinBirth(String minBirth) {
		this.minBirth = minBirth;
	}
	public String getMaxBirth() {
		return maxBirth;
	}
	public void setMaxBirth(String maxBirth) {
		this.maxBirth = maxBirth;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public HouseRange(String minArea, String maxArea, String minBirth, String maxBirth, String minPrice,
			String maxPrice) {
		super();
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.minBirth = minBirth;
		this.maxBirth = maxBirth;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	public HouseRange() {
		super();
	}
	@Override
	public String toString() {
		return "HouseRange [minArea=" + minArea + ", maxArea=" + maxArea + ", minBirth=" + minBirth + ", maxBirth="
				+ maxBirth + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}
}
