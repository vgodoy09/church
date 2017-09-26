package br.com.church.util;

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
}
