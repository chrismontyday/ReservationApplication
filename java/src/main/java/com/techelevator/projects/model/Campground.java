package com.techelevator.projects.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {
//A campground includes an id, name, open month, closing month, and a daily fee.

	private int id;
	private String name;
	private LocalDate openMonth;
	private LocalDate closingMonth;
	private BigDecimal fee;
	
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
	public LocalDate getOpenMonth() {
		return openMonth;
	}
	public void setOpenMonth(LocalDate openMonth) {
		this.openMonth = openMonth;
	}
	public LocalDate getClosingMonth() {
		return closingMonth;
	}
	public void setClosingMonth(LocalDate closingMonth) {
		this.closingMonth = closingMonth;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
}
