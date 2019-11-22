package com.techelevator.projects.model;

import java.time.LocalDate;

public class Park {
	//Prints all parks which all include an id, name, location, established date, area, annual visitor count, and
		//description
	private int id;
	private String name;
	
	private String location;
	private LocalDate estDate;
	private int area;
	private int visCount;
	private String description;
	
//	public Park(String location, int estDate, int area, int visCount, String description) {
//		this.location = location;
//		this.estDate = estDate;
//		this.area = area;
//		this.visCount = visCount;
//		this.description = description;
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getEstDate() {
		return estDate;
	}
	public void setEstDate(LocalDate estDate) {
		this.estDate = estDate;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getVisCount() {
		return visCount;
	}
	public void setVisCount(int visCount) {
		this.visCount = visCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
