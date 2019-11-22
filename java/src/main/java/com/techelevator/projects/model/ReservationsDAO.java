package com.techelevator.projects.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsDAO {


	List<Reservation> getAllAvailableReservations(int camp, LocalDate from, LocalDate to);

	List<Reservation> getReservations(int siteChoice, String name, LocalDate from, LocalDate to) {}


	int[] getSiteId(int campId);

}
