package com.techelevator;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.DateFormatSymbols;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.projects.model.jdbc.JDBCParkDAO;
import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParksDAO;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCCampgroundDAO campDao;
	
	
	@Test
	public void test_get_campground_by_park_id() {
		List<Campground> results = campDao.getCampgroundByParkId(id);
		Assert.assertNotNull(results);
		Campground test = results.get(0);
		Assert.assertEquals("names weren't the same", "Grenada", test.getName());
	}
	
	public String getMonth (int month) {
		return new DateFormatSymbols().getMonths()[month-1];
	}
	

	private void assertCampgroundsAreEqual(Campground expected, Campground actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
	}
	
}