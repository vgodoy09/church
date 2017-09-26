package br.com.church.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class UtilsFormat {

	public static String valorMonetario(Double total) {
		String formatado = "";
		BigDecimal result = new BigDecimal(total);
		Locale ptBr = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
		formatado = nf.format(result);
		return formatado;
	}
	
	public static Double soma(List<Double> total) {
		Double result = 0.0;
		for(Double d: total){
			result += d;
		}
		return result;
	}
}
