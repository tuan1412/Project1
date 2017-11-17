package app.services;

import java.time.LocalDate;

public class DateIncrementer {

	public static String addOneDay(String date) {
		return LocalDate.parse(date).plusDays(1).toString();
	}
}

