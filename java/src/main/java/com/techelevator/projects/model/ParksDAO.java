package com.techelevator.projects.model;

import java.util.List;

public interface ParksDAO {

	//Prints all parks which all include an id, name, location, established date, area, annual visitor count, and
	//description
	public List<Park> getAllAvailableParks();

	String getDescription(String choice);

}
