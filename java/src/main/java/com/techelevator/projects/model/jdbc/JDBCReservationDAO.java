package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.ReservationsDAO;
import com.techelevator.projects.model.Site;

public class JDBCReservationDAO implements ReservationsDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		
	}

	

	@Override
	public int[] getSiteId(int campId) {
		List<Site> allCamps = new ArrayList<>();

		String sqlGetAllCamps = "SELECT site_id FROM site WHERE campground_id = " + campId + ";";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCamps);
		while (results.next()) {
			Site tempSite = new Site();
			tempSite.setId(results.getInt("site_id"));
			allCamps.add(tempSite);
		}
		int[] returnArray = new int[allCamps.size()];
		for (int i = 0; i < allCamps.size(); i++) {
			int a = allCamps.get(i).getId();
			returnArray[i] = a;
		}
		return returnArray;
	}

	@Override
	public List<Reservation> getAllAvailableReservations(int campId, LocalDate from, LocalDate to) {
		List<Reservation> allRes = new ArrayList<>();
		int[] siteIds = getSiteId(campId);
		Date fromDate = Date.valueOf(from);
		Date toDate = Date.valueOf(to);

		String sqlGetAllCamps = 
		"SELECT cg.campground_id, site_number, max_occupancy, visitors, accessible, utilities, daily_fee, from_date, to_date FROM site " + 
	    "INNER JOIN campground cg ON site.campground_id = cg.campground_id INNER JOIN reservation r ON site.site_id = r.site_id " +
	    "INNER JOIN park ON cg.park_id = park.park_id " +
	    "WHERE cg.campground_id = ? AND from_date > ? AND to_date < ? " +
	    "ORDER BY visitors DESC LIMIT 5;"; 
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCamps, campId, fromDate, toDate);
		while (results.next()) {
			Reservation tempRes = new Reservation();
			tempRes.setId(results.getInt("site_number"));
			tempRes.setCampground(results.getString("campground_id"));
			tempRes.setMax_occupancy(results.getInt("max_occupancy"));
			tempRes.setAccessible(results.getBoolean("accessible"));
			tempRes.setUntilities(results.getBoolean("utilities"));
			tempRes.setDaily_fee(results.getBigDecimal("daily_fee"));
			tempRes.setFromDate(results.getDate("from_date").toLocalDate());
			tempRes.setToDate(results.getDate("to_date").toLocalDate());
			allRes.add(tempRes);
		}
		
		return allRes;
	}
	
	@Override
	public List<Reservation> getReservations(int siteId, String name, LocalDate from, LocalDate to) {
		List<Reservation> allRes = new ArrayList<>();
		Date fromDate = Date.valueOf(from);
		Date toDate = Date.valueOf(to);
		String insertReservation = "INSERT INTO reservation (site_id, name, from_date, to_date) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(insertReservation, siteId, name, fromDate, toDate); 
		
		String sqlRes = 
				"SELECT * FROM reservation WHERE site_id =? AND name=? AND from_date=? AND to_date=?"; 
				
				SqlRowSet results = jdbcTemplate.queryForRowSet(sqlRes, siteId, name, fromDate, toDate);
				while (results.next()) {
					Reservation tempRes = new Reservation();
					tempRes.setId(results.getInt("reservation_id"));
					tempRes.setMax_occupancy(results.getInt("site_id"));
					tempRes.setName(results.getString("name"));
					tempRes.setFromDate(results.getDate("from_date").toLocalDate());
					tempRes.setToDate(results.getDate("to_date").toLocalDate());
					allRes.add(tempRes);
				}
				return allRes;
		
	}
	
 }
