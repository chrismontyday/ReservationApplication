package com.techelevator.projects.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
	
	private int id;
	private String name;
	private String campground;
	private int max_occupancy;
	private int visitors;
	private boolean accessible;
	private boolean untilities;
	private BigDecimal daily_fee;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalDate createDate;
	
	
	public String getCampground() {
		return campground;
	}

	public void setCampground(String campground) {
		this.campground = campground;
	}

	public int getMax_occupancy() {
		return max_occupancy;
	}

	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public boolean isUntilities() {
		return untilities;
	}

	public void setUntilities(boolean untilities) {
		this.untilities = untilities;
	}

	public BigDecimal getDaily_fee() {
		return daily_fee;
	}

	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}
	
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

}
