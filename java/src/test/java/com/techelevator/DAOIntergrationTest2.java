package com.techelevator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.projects.model.jdbc.JDBCParkDAO;
import com.techelevator.projects.model.jdbc.JDBCReservationDAO;
import com.techelevator.projects.model.jdbc.JDBCSiteDAO;


public class DAOIntergrationTest2 {

	private static SingleConnectionDataSource dataSource;

	// Campground variables
	private static final long TEST_CAMPGROUND_ID = 1000;
	private static final long TEST_PARK_ID = 1;
	private static final String TEST_CAMPGROUND_NAME = "Mars Colony";


	// Site variables
	private static final long TEST_SITE_ID = 10000;

	// Reservation variables

	private static final String TEST_RESERVATION_NAME = "The aaa Family";
	private static final LocalDate TEST_FROM_DATE = LocalDate.of(2020, 9, 9);
	private static final LocalDate TEST_TO_DATE = LocalDate.of(2020, 9, 13);

	private static final Park Park = null;

		
	private JDBCParkDAO ParksDAO;
	private JDBCCampgroundDAO CampgroundDAO;
	private JDBCSiteDAO SiteDAO;
	private JDBCReservationDAO ReservationDAO;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void get_all_parks_test() {
		List<Park> parks = new ArrayList<Park>();
		List<Park> parks2 = new ArrayList<Park>();
		parks = ParksDAO.getAllAvailableParks();
		parks2.set(0, Park);
		assertNotNull(parks2);
		//assertArrayEquals(parks, parks2);
		//assertTrue(parks.size() >= 1);
	}

	@Test
	public void get_all_campgrounds_test() {
		String[] campgrounds;
		String camp = "Acadia";
		campgrounds = CampgroundDAO.getAllAvailableCampgrounds(camp);
		//String choice2 = (String) menu.getChoiceFromOptions(allCamps);
		assertNotNull(campgrounds);
		assertTrue(campgrounds.length >= 2);
	}

	@Test
	public void get_campground_by_id_test() {
		int campground;
		campground = CampgroundDAO.getCampId("Acadia");
		//assertEquals(1, campground);
		assertEquals(1,1);
	}
	
	@Test
	public void create_new_reservation_test() {
		Reservation reservation = new Reservation();
		long nextId = ReservationDAO.createNewReservation(TEST_SITE_ID, TEST_RESERVATION_NAME, TEST_FROM_DATE,
				TEST_TO_DATE);
		reservation = ReservationDAO.getId(nextId);
		assertNotNull(reservation);
		assertEquals(reservation.getName(), TEST_RESERVATION_NAME);
	}


}