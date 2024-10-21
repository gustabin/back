package ar.com.santanderrio.obp.servicios.inversiones.maps.utils;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

public class ServiciosDeInversionUtilTest {

	/**
	 * Transforma el Código de moneda de MAPS, a símbolo de moneda.
	 * Ejemplo: "GBP" ---> "£"
	 *     		"EUR" ---> "€"
	 *     		"USD" ---> "U$S"
	 *     		"JPY" ---> "¥"
	 */	
	@Test
	public void testSimboloMoneda() {
		boolean esRespuestaVacia = false;
		String symbol = "";
		String countryCode2="";
		Map<Currency, Locale> map = getCurrencyLocaleMap();
		String[] countries = { "POR","GBP", "FKP", "EGP", "GIP", "SHP", "SSP", "EUR", "USD", "JPY", "CNY", "BOB", "BOV","ARS",
				               "PEN", "XSU", "BRL", "VES", "XBA", "XBB", "XBC", "XBD", "XTS", "XXX", "XAU", "XPD", "XPT", "XAG", "",null };

		for (String countryCode : countries) {
			if ("FKP".equals(countryCode) || "EGP".equals(countryCode) || "GIP".equals(countryCode)
					|| "SHP".equals(countryCode) || "SSP".equals(countryCode)) {
				countryCode2 = countryCode;
				countryCode = "GBP";
			}
			if ("BOV".equals(countryCode)) {
				countryCode2 = countryCode;
				countryCode = "BOB";
			}

			if ("".equals(countryCode) || "null".equals(countryCode) || null == countryCode || "SHP".equals(countryCode)
					|| "XBA".equals(countryCode) || "XBB".equals(countryCode) || "XBC".equals(countryCode)
					|| "XBD".equals(countryCode) || "XTS".equals(countryCode) || "XXX".equals(countryCode)
					|| "XAU".equals(countryCode) || "XPD".equals(countryCode) || "XPT".equals(countryCode)
					|| "XAG".equals(countryCode)) {
				esRespuestaVacia = true;
			}

			if (!"XSU".equals(countryCode) && !"VES".equals(countryCode) && !esRespuestaVacia && !"POR".equals(countryCode)) {
				Currency c = Currency.getInstance(countryCode);
				symbol = c.getSymbol(map.get(c));
				if ("$".equals(symbol)&& !"ARS".equals(countryCode)) {
					symbol = "U$S";
				}
				if ("B$".equals(symbol)) {
					symbol = "BS";
				}
			} else {
				if ("XSU".equals(countryCode)) {
					symbol = "SI$";
				}
				if ("VES".equals(countryCode)) {
					symbol = "₪";
				}
				if ("POR".equals(countryCode)) {
					symbol = " %";
				}
				if (esRespuestaVacia) {
					symbol = "";
				}
			}
			if("".equals(countryCode2)){
				System.out.println("For country " + countryCode + ", currency symbol is " + symbol);
			}
			else{
				System.out.println("For country " + countryCode2 + ", currency symbol is " + symbol);
				countryCode2 = "";
			}
		}
	}
		 
	public static Map<Currency, Locale> getCurrencyLocaleMap() {
		Map<Currency, Locale> map = new HashMap<Currency, Locale>();
		for (Locale locale : Locale.getAvailableLocales()) {
			try {
				Currency currency = Currency.getInstance(locale);
				map.put(currency, locale);
			} catch (Exception e) {
				// skip strange locale
			}
		}
		return map;
	}
	
	
	
	
	/**
	 * Transforma el Código de moneda de MAPS, a símbolo de moneda.
	 * Ejemplo: "GBP" ---> "£"
	 *     		"USD" ---> "U$S"
	 *     		"JPY" ---> "¥"
	 */	
	public static Map<Currency, Locale> getCurrencyLocaleMap2() {
		Map<Currency, Locale> map = new HashMap<Currency, Locale>();
		for (Locale locale : Locale.getAvailableLocales()) {
			try {
				Currency currency = Currency.getInstance(locale);
				map.put(currency, locale);
			} catch (Exception e) {
				// skip strange locale
			}
		}
		return map;
	}
	
	public String obtenerSimboloMoneda(String codigoMoneda){
		boolean esRespuestaVacia = false;
		String symbol = "";
		String countryCode=codigoMoneda;
		Map<Currency, Locale> map = getCurrencyLocaleMap2();
		
		if ("FKP".equals(countryCode) || "EGP".equals(countryCode) || "GIP".equals(countryCode)
				|| "SHP".equals(countryCode) || "SSP".equals(countryCode)) {
			countryCode = "GBP";
		}
		if ("BOV".equals(countryCode)) {
			countryCode = "BOB";
		}

		if ("".equals(countryCode) || "null".equals(countryCode) || null == countryCode || "SHP".equals(countryCode)
				|| "XBA".equals(countryCode) || "XBB".equals(countryCode) || "XBC".equals(countryCode)
				|| "XBD".equals(countryCode) || "XTS".equals(countryCode) || "XXX".equals(countryCode)
				|| "XAU".equals(countryCode) || "XPD".equals(countryCode) || "XPT".equals(countryCode)
				|| "XAG".equals(countryCode)) {
			esRespuestaVacia = true;
		}

		if (!"XSU".equals(countryCode) && !"VES".equals(countryCode) && !esRespuestaVacia && !"POR".equals(countryCode)) {
			Currency c = Currency.getInstance(countryCode);
			symbol = c.getSymbol(map.get(c));
			if ("$".equals(symbol)&& !"ARS".equals(countryCode)) {
				symbol = "U$S";
			}
			if ("B$".equals(symbol)) {
				symbol = "BS";
			}
		} else {
			if ("XSU".equals(countryCode)) {
				symbol = "SI$";
			}
			if ("VES".equals(countryCode)) {
				symbol = "₪";
			}
			if ("POR".equals(countryCode)) {
				symbol = " %";
			}
			if (esRespuestaVacia) {
				symbol = "";
			}
		}
		return symbol;
	}
	
	

	
	public int cantidadDecimales(String valor) {
		String[] parts = valor.split("\\.");
		if (parts.length > 1) {
			return parts[1].length();
		}
		return 0;
	}
	
	public static int leadingZerosCount(String s){
		   int zeros=0;
		   for(int i=0; i<s.length();i++) {
		      if(s.charAt(i)=='0')
		        zeros++;
		      else
		        break;
		   }
		   return zeros;
		}
	
	
	public String obtenerImporte(String valorString, String parteDecimal, String parteEntera, BigDecimal incremento) {
		String incrementoString = incremento.toString();
		int cantidadDecimales = cantidadDecimales(incrementoString);
		switch (cantidadDecimales) {
		case 0:
			return parteEntera;
		case 1:
			return valorString;
		case 2:
			return valorString;
		}
		if (leadingZerosCount(parteDecimal) == parteDecimal.length()) {
			return parteEntera;
		} else {
			return parteEntera + "," + parteDecimal;
		}

	}
	
	
	public int getNumberOfDecimalPlaces(BigDecimal bigDecimal) { 
	    String string = bigDecimal.stripTrailingZeros().toPlainString(); 
	    int index = string.indexOf("."); 
	    return index < 0 ? 0 : string.length() - index - 1; 
	} 
	
	public boolean containsChar(String s, char search) {
	    if (s.length() == 0)
	        return false;
	    else
	        return s.charAt(0) == search || containsChar(s.substring(1), search);
	}
	
	@Test
	public void TestValorComprobante() throws ImporteConvertException {
		
		String simbolo="GBP";
		char punto = '.';
		BigDecimal  valor = new BigDecimal(112302.5234);
//		BigDecimal  valor = new BigDecimal(0.134);
//		BigDecimal  valor = new BigDecimal(134);
		BigDecimal  incremento = new BigDecimal(0.12345);
//		BigDecimal  incremento = new BigDecimal(1);
		
		
		incremento = incremento.setScale(5, BigDecimal.ROUND_HALF_EVEN);
		valor =valor.setScale(getNumberOfDecimalPlaces(incremento), BigDecimal.ROUND_HALF_EVEN);
		
		String valorImprDecimal = valor.toString();
		if (containsChar(valor.toString(), punto)) {
			String[] partes = valor.toString().split("\\.");
			valorImprDecimal = partes[1];
		}	
		String valorString = ISBANStringUtils.formatearSaldo(valor);		
		String[] partesValorString = valorString.split(",");
		String parteEnteraValorString = partesValorString[0];
		
		String valorRetorno = "";
		if ("POR".equals(simbolo)) {
			valorRetorno = (obtenerImporte(valorString,valorImprDecimal,parteEnteraValorString,incremento)).concat(obtenerSimboloMoneda(simbolo));
		} else {
			valorRetorno = (obtenerSimboloMoneda(simbolo)).concat(" "+obtenerImporte(valorString,valorImprDecimal,parteEnteraValorString,incremento));
		}
		System.out.println("Valor de retorno:"+valorRetorno );
		
	}
	
	
}
