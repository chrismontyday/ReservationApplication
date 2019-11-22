package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.CampgroundDAO;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.ParksDAO;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.ReservationsDAO;
import com.techelevator.projects.model.Site;
import com.techelevator.projects.model.SiteDAO;
import com.techelevator.projects.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.projects.model.jdbc.JDBCParkDAO;
import com.techelevator.projects.model.jdbc.JDBCReservationDAO;
import com.techelevator.projects.model.jdbc.JDBCSiteDAO;

public class CampgroundCLI {

	// create the static final for DAO
	private static final String PARK_MENU_DISPLAY_PARKS = "Select a Park for More Details";
	private static final String[] PARK_MENU_OPTIONS = new String[] { PARK_MENU_DISPLAY_PARKS };

	private static final String MAIN_MENU_OPTIONS_PARKS = "View Parks Interface";
	private static final String MAIN_MENU_OPTIONS_QUIT = "Exit Menu";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTIONS_PARKS, MAIN_MENU_OPTIONS_QUIT };

	private static final String CAMP_MENU_OPTION_ALL_CAMPGROUNDS = "View All Campgrounds";
	private static final String CAMP_MENU_SEARCH_AVAILABLE_RESERVATIONS = "Search for Reservation";
	private static final String CAMP_MENU_RETURN_TO_PREVIOUS = "Go Back";
	private static final String[] CAMP_MENU_OPTIONS = new String[] { CAMP_MENU_OPTION_ALL_CAMPGROUNDS,
			 CAMP_MENU_RETURN_TO_PREVIOUS };

	private static final String RESERVATION_MENU_SEARCH_AVAILABLE = "Search for Available Reservation Date";
	private static final String RESERVATION_MENU_RETURN_TO_PREVIOUS = "Go Back";
	private static final String[] RESERVATION_MENU_OPTIONS = new String[] { RESERVATION_MENU_SEARCH_AVAILABLE,
			RESERVATION_MENU_RETURN_TO_PREVIOUS };

	private static final String RESERVATION_MENU_ENTER_FROM = "What is the arrival date? (format: MM/dd/yyyy) >>> ";
	private static final String RESERVTION_MENU_GO_BACK_FROM = "Go Back";
	private static final String[] SEARCH_RESERVATION_MENU_OPTIONS_FROM = new String[] { RESERVATION_MENU_ENTER_FROM,
			RESERVTION_MENU_GO_BACK_FROM };

	private static final String RESERVATION_MENU_ENTER_TO = "What is the departure date? (format: MM/dd/yyyy) >>> ";
	private static final String RESERVTION_MENU_GO_BACK_TO = "Go Back";
	private static final String[] SEARCH_RESERVATION_MENU_OPTIONS_TO = new String[] { RESERVATION_MENU_ENTER_TO,
			RESERVTION_MENU_GO_BACK_TO };

	private static final String RESERVATION_CHANGE = "Yes";
	private static final String RESERVATION_DONT_CHANGE = "No";
	private static final String[] CHANGE_RESERVATION_DATES = new String[] { RESERVATION_CHANGE,
			RESERVATION_DONT_CHANGE };

	// need to create variables and declare list
	private Menu menu;
	public ParksDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationsDAO reservationDAO;
	private long numChoice;
	private Park selectedPark;
	private List<Campground> campsOfParkId;
	private long selectedCampgroundId = 0;
	private long selectedSiteId = 0;
	private LocalDate arrival;
	private LocalDate departure;
	private List<Site> availableSites = null;
	private String park = null;
	private String camp = null;

	public static void main(String[] args) {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);

		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}

	public void run() {
		System.out.println("           (                 ,&&&.");
		System.out.println("            )                .,.&&");
		System.out.println("           (  (              \\=__/");
		System.out.println("               )             ,'-'.");
		System.out.println("         (    (  ,,      _.__|/ /|");
		System.out.println("          ) /\\ -((------((_|___/ |");
		System.out.println("        (  // | (`'      ((  `'--|");
		System.out.println("      _ -.;_/ \\\\--._      \\\\ \\-._/.");
		System.out.println("     (_;-// | \\ \\-'.\\    <_,\\_\\`--'|");
		System.out.println("     ( `.__ _  ___,')      <_,-'__,'");
		System.out.println("       `'(_ )_)(_)_)'");

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTIONS_PARKS)) {
				manageParks();
			} else if (choice.equals(MAIN_MENU_OPTIONS_QUIT)) {
				System.out.println("Thank You for Visiting! Have a Great Day!");
				break;
			}

		}

	}

	private void manageParks() {

		System.out.println("\nSelect a Park");
		List<Park> allParks = parkDAO.getAllAvailableParks();
		String[] newArray = new String[allParks.size() + 1];
		for (int i = 0; i < allParks.size(); i++) {
			newArray[i] = allParks.get(i).getName();
		}
		newArray[allParks.size()] = "Quit";
		String choice = (String) menu.getChoiceFromOptions(newArray);
		park = choice;
		System.out.print("\nPlease choose an option >>> ");
		if (choice.equals("Quit")) {
			return;
		}
		BackToCampMenu(choice);

	}

	private void BackToCampMenu(String choice) {

		String userInput = (String) menu.getChoiceFromOptions(CAMP_MENU_OPTIONS);

		if (userInput.equals(CAMP_MENU_OPTION_ALL_CAMPGROUNDS)) {
			String[] allCamps = campgroundDAO.getAllAvailableCampgrounds(choice);
			String choice2 = (String) menu.getChoiceFromOptions(allCamps);
			camp = choice2;
			if (choice2.contentEquals("Quit")){
				return;
			} else
			manageReservations(choice2);
		} else if (userInput.equals(CAMP_MENU_SEARCH_AVAILABLE_RESERVATIONS)) {
			System.out.println("\nSearch for Campground Reservation\n");
		} else if (choice.equals(CAMP_MENU_RETURN_TO_PREVIOUS)) {
			manageParks();
		}
	}

	private void manageReservations(String choice2) {
		System.out.println("Select a Command");

		String choice = (String) menu.getChoiceFromOptions(RESERVATION_MENU_OPTIONS);
		if (choice.equals(RESERVATION_MENU_SEARCH_AVAILABLE)) {
			// catch all date exceptions method
			getFromDate(choice2);
			manageMakeReservation(choice2);

		} else if (choice.equals(RESERVATION_MENU_RETURN_TO_PREVIOUS)) {
			BackToCampMenu(choice);
		}
	}

	private void manageMakeReservation(String choice) {

		Scanner input = new Scanner(System.in);
		System.out.print("\nWhich site should be reserved >>> ");
		String userInput = input.next();
		int siteChoice = Integer.parseInt(userInput);
		System.out.print("What name should the reservation be made under? (no spaces) >>> ");
		String userInput2 = input.next().toString();
		String reservationName = userInput2;

		System.out.println("\nWould you like to change you reservation dates?");
		String choice3 = (String) menu.getChoiceFromOptions(CHANGE_RESERVATION_DATES);
		if (choice3.equals(RESERVATION_CHANGE)) {
			updateDates();
			confirmReservation(siteChoice, choice, reservationName);
		} else if (choice3.equals(RESERVATION_DONT_CHANGE)) {

			confirmReservation(siteChoice, choice, reservationName);
		}
		run();
	}

	private void confirmReservation(int siteId, String camp, String name) {
		System.out.println("You want to reserve site " + siteId + "\nat the campground '" + camp + "'\nfrom the dates "
				+ arrival + " to " + departure + "\nunder the name " + name + "\nAre you sure?");
		String choice3 = (String) menu.getChoiceFromOptions(CHANGE_RESERVATION_DATES);
		if (choice3.equals(RESERVATION_CHANGE)) {
			System.out.println("\nMaking Reservation\n");

			List<Reservation> newRes = reservationDAO.getReservations(siteId, name, arrival, departure);
			printReservation(newRes);
			newReservation();

		} else if (choice3.equals(RESERVATION_DONT_CHANGE)) {
			System.out.println("Routing you back");
			BackToCampMenu(park);
		}
	}

	private void newReservation() {

		System.out.println("Would you like to make a new reservation?");
		String choice3 = (String) menu.getChoiceFromOptions(CHANGE_RESERVATION_DATES);
		if (choice3.equals(RESERVATION_CHANGE)) {

		} else if (choice3.equals(RESERVATION_DONT_CHANGE)) {
			System.out.println("Happy Camping!");

		}
	}

	private void updateDates() {

		Scanner input = new Scanner(System.in);
		System.out.println("\nWhat is the arrival date? (format: MM/dd/yyyy) >>> ");
		String userInput = input.next().toString();
		arrival = stringToLocalDate(userInput);

		System.out.println("\nWhat is the departure date? (format: MM/dd/yyyy) >>> ");
		String userInput2 = input.next().toString();
		departure = stringToLocalDate(userInput2);

	}

	private void getFromDate(String choice) {
		System.out.println("\n You have chosen to book at " + choice + "\n");

		Scanner input = new Scanner(System.in);
		System.out.println("\nWhat is the arrival date? (format: MM/dd/yyyy) >>> ");
		String userInput = input.next().toString();
		//if (userInput.)
		arrival = stringToLocalDate(userInput);

		System.out.println("\nWhat is the departure date? (format: MM/dd/yyyy) >>> ");
		String userInput2 = input.next().toString();
		departure = stringToLocalDate(userInput2);

		int campId = campgroundDAO.getCampId(park);
		List<Reservation> availableDates = reservationDAO.getAllAvailableReservations(campId, arrival, departure);
		printAvailableDates(availableDates);
	}

	private void printAvailableDates(List<Reservation> dates) {

		for (Reservation res : dates) {
			System.out.println("Reservation Id: " + res.getId() + " in the name of  " + res.getName()
					+ " is reserved from " + res.getFromDate() + " to " + res.getToDate() + " at site number "
					+ res.getMax_occupancy());
		}
	}

	private void printReservation(List<Reservation> dates) {

		for (Reservation res : dates) {
			System.out.println("Site Number: " + res.getId() + " with campground id  " + res.getCampground()
					+ " is taken from " + res.getFromDate() + " to " + res.getToDate());
		}
	}

	private LocalDate stringToLocalDate(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDateObj = LocalDate.parse(date, dateTimeFormatter);
		return localDateObj;
	}

	private void manageGetAvailableSites() {
		System.out.println("\nResults Matching Your Search Criteria");
		availableSites = siteDAO.getAllAvailableSites(selectedCampgroundId, arrival, departure);
	}
}
