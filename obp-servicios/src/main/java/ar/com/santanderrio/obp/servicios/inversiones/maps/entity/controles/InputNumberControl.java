package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;


public class InputNumberControl extends ControlMaps {

	@JsonProperty("Simbolo")
	protected String simbolo;

	@JsonProperty("Valor")
	protected BigDecimal valor;
	
	@NotNull
	@JsonProperty("Incremento")
	protected BigDecimal incremento;
	
    @JsonProperty("MinValor")
    protected BigDecimal minValor;
    
    @JsonProperty("MaxValor")
    protected BigDecimal maxValor;

    
	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getIncremento() {
		return incremento;
	}

	public void setIncremento(BigDecimal incremento) {
		this.incremento = incremento;
	}

	public BigDecimal getMinValor() {
		return minValor;
	}

	public void setMinValor(BigDecimal minValor) {
		this.minValor = minValor;
	}

	public BigDecimal getMaxValor() {
		return maxValor;
	}

	public void setMaxValor(BigDecimal maxValor) {
		this.maxValor = maxValor;
	}
  
	
	/**
	 * Transforma el Código de moneda de MAPS, a símbolo de moneda. Ejemplo:
	 * "GBP" ---> "£" 
	 * "USD" ---> "U$S" 
	 * "JPY" ---> "¥"
	 */
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

	public String obtenerSimboloMoneda(String countryCode) {
		boolean esRespuestaVacia = false;
		String symbol = "";
		Map<Currency, Locale> map = getCurrencyLocaleMap();

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

		if (!"XSU".equals(countryCode) && !"VES".equals(countryCode) && !esRespuestaVacia
				&& !"POR".equals(countryCode)) {
			Currency c = Currency.getInstance(countryCode);
			symbol = c.getSymbol(map.get(c));
			if ("$".equals(symbol) && !"ARS".equals(countryCode)) {
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
	    
		int cantidadDecimales = decidirDecimales();
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
			return parteEntera + "," + parteDecimal.substring(0,cantidadDecimales);
		}

	}
	
	public int getNumberOfDecimalPlaces(BigDecimal bigDecimal) { 
	    String string = bigDecimal.stripTrailingZeros().toPlainString(); 
	    int index = string.indexOf('.'); 
	    return index < 0 ? 0 : string.length() - index - 1; 
	}
	
	
	public boolean containsChar(String s, char search) {
	    if (s.length() == 0)
	        return false;
	    else
	        return s.charAt(0) == search || containsChar(s.substring(1), search);
	}
	
	
	@Override
	public String valorComprobante() {
		char punto = '.';
		BigDecimal valorImpr =getValor();
		
		String valorImprDecimal = valorImpr.toString();
		if (containsChar(valorImpr.toString(), punto)) {
			String[] partes = valorImpr.toString().split("\\.");
			valorImprDecimal = partes[1];
		}
					
		String valorString = ISBANStringUtils.formatearSaldo(valorImpr);		
		String[] partesValorString = valorString.split(",");
		String parteEnteraValorString = partesValorString[0];
		
		String valorRetorno = "";
		valorString = ISBANStringUtils.formatearSaldo(getValor());
		if ("POR".equals(getSimbolo())) {
			valorRetorno = (obtenerImporte(valorString,valorImprDecimal,parteEnteraValorString,getIncremento())).concat(obtenerSimboloMoneda(getSimbolo()));
		} else {
			valorRetorno = (obtenerSimboloMoneda(getSimbolo())).concat(" "+obtenerImporte(valorString,valorImprDecimal,parteEnteraValorString,getIncremento()));
		}
		return valorRetorno;
	}

	
	/**
	   * Decide la cantidad de decimales a mostrar, en base al incremento y a
	   * la cantidad de decimales ingresados en el <input>
	   *
	   * @param {number} valorNumerico
	   * @returns {number}
	   * @memberof ControlInputNumberController
	   */
	  private  Integer decidirDecimales() {
	    // Si la cantidad de decimales del incremento es menor o igual a dos, devolver esa cantidad de decimales
	    if (cantidadDecimales(this.incremento.toString()) <= 2) {
	      return cantidadDecimales(this.incremento.toString());
	    }

	    // Si la cantidad de decimales del incremento es mayor a la del valor ingresado (y mayor a dos)...
	    if (cantidadDecimales(this.incremento.toString()) > cantidadDecimales(this.valor.toString())) {
	      // ...y si la cantidad de decimales del valor ingresado es menor o igual a dos, devolver dos
	      if (cantidadDecimales(this.valor.toString()) <= 2) {
	        return 2;
	      }

	      // ...(y la cantidad de decmales del valor ingresado es mayor a dos) devolver la cantidad de decimales del valor ingresado
	      return cantidadDecimales(this.valor.toString());
	    }

	    // Si la cantidad de decimales del incremento es menor a la del valor ingresado, devolver la cantidad de decimales del incremento
	    if (cantidadDecimales(this.incremento.toString()) < cantidadDecimales(this.valor.toString())) {
	      return cantidadDecimales(this.incremento.toString());
	    }

	    // Si no se cumple ninguna de las anteriores, devolver la cantidad de decimales del valor ingresado
	    return cantidadDecimales(this.valor.toString());
	  }
	
	
}
