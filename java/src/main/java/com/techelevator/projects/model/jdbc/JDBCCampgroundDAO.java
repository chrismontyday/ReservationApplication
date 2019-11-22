package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public String[] getAllAvailableCampgrounds(String choice) {
				
		List<Campground> allCamps = new ArrayList<>();
		int campId = getCampId(choice);
		String sqlGetAllCamps = "SELECT name FROM campground WHERE park_id = ?";// + campId + ";";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCamps, campId);
		while (results.next()) {
			Campground tempCamp = new Campground();
			tempCamp.setName(results.getString("name"));			
			allCamps.add(tempCamp);
		}
		String[] returnArray = new String[allCamps.size() + 1];
		for( int i = 0; i < allCamps.size(); i++) {
			String a = allCamps.get(i).getName();
			returnArray[i] = a;
		}
		returnArray[allCamps.size()] = "Quit";
		return returnArray;
	}
	
	@Override
	public int getCampId(String campName) {
		List<Park> allParks = new ArrayList<>();
		String sqlGetAllParks = "SELECT park_id FROM park WHERE name = '" + campName +"'";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park tempPark = new Park();
			tempPark.setId(results.getInt("park_id")); 			
			allParks.add(tempPark);
		}
		int campId = allParks.get(0).getId();
		return campId;
	}

	@Override
	public List<Campground> getCampgroundByParkId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
