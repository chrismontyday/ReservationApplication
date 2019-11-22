package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParksDAO;

public class JDBCParkDAO implements ParksDAO {

	private JdbcTemplate jdbcTemplate;
	private String location;
	private int estDate;
	private int area;
	private int visCount;

	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Park> getAllAvailableParks() {

		List<Park> allParks = new ArrayList<>();
		String sqlGetAllParks = "SELECT name, location, area, establish_date, description FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park tempPark = new Park();
			tempPark.setName(results.getString("name"));
			tempPark.setLocation(results.getString("location"));
			tempPark.setEstDate(results.getDate("establish_date").toLocalDate());
			tempPark.setArea(results.getInt("area"));
			tempPark.setDescription(results.getString("description"));
			allParks.add(tempPark);
		}

		return allParks;
	}
	
	@Override
	public String getDescription(String choice) {
		List<Park> allParks = new ArrayList<>();
		String sqlGetAllParks = "SELECT description FROM park WHERE name = '" + choice + "'";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park tempPark = new Park();
			tempPark.setName(results.getString("description"));			
			allParks.add(tempPark);
		}
		String description = allParks.get(0).getDescription();
		return description;
	}
	}

