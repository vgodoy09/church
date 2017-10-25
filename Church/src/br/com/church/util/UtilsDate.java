package br.com.church.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilsDate {
	
	public static Date removeDias(Date date, Integer dias) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(Calendar.DATE, gc.get(Calendar.DATE) - dias);
		return gc.getTime();
	}
	
	public static String dateFormats(Date date, String pattern){
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		String formatado = "";
		if(date != null){
			formatado = sd.format(date);
		}
		return formatado;
	}
	
	public static Date dtDateformat(String date, String fromPattern){
		String from = date;
		String originPattern = fromPattern;
		Date returnDate = null;
		SimpleDateFormat format = new SimpleDateFormat(originPattern);
		try {
			returnDate = format.parse(from);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		return returnDate;
	}
}
