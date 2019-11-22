package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Site;
import com.techelevator.projects.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	public JDBCSiteDAO(DataSource datasource) {
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<Site> getAllAvailableSites(long id, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
