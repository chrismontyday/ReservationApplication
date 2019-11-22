package com.techelevator.projects.model;

import java.util.List;

public interface CampgroundDAO {
	
	//Lists avaialable campgrounds
	public String[] getAllAvailableCampgrounds(String choice);
	public List<Campground> getCampgroundByParkId(long id);
	int getCampId(String campName);


}
