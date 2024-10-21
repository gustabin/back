package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoCuentaTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

public class DeudaProductoPromesaPagoView {

	private String producto;
	
	private String deuda;
	
	private AtrasoDeudaPromesaPagoEnum tipoAtrasoDeuda;
	
	private static final String TIPO_CUENTA_PRESTAMO_PERSONAL = "30";

	private static final String TIPO_CUENTA_PRESTAMO_PRENDARIO = "31";
	
	private static final String DESCUBIERTO1 = "00";
	
	private static final String DESCUBIERTO2 = "03";
	
	private static final String DESCUBIERTO3 = "09";
	
	private static final String DESCUBIERTO4 = "10";

	private static final int DIA_MINIMO_TRAMO1 = 3;
	
	private static final int DIA_MAXIMO_TRAMO1 = 30;

	private static final int DIA_MINIMO_TRAMO2 = 31;
	
	private static final int DIA_MAXIMO_TRAMO2 = 60;
	
	
	public DeudaProductoPromesaPagoView() {
		super();
	}
	
	public DeudaProductoPromesaPagoView(String[] datosProducto, Cuenta cuenta) {
		
		if (!"0".equals(datosProducto[4])) {
    		BigDecimal bigMonto = new BigDecimal(datosProducto[4].replaceAll("\\,", "\\."));
			this.deuda = DivisaEnum.PESO.getSimbolo() + " " + ISBANStringUtils.formatearSaldo(bigMonto);
		} else {
			this.deuda = DivisaEnum.PESO.getSimbolo() + " " + "0,00";
		}
		this.producto = obtenerProducto(cuenta);
		this.tipoAtrasoDeuda = obtenerAtrasoDeuda(datosProducto[5]);
		
	}
	
	private String obtenerProducto(Cuenta cuenta) {
		
		String productoString = StringUtils.EMPTY;
		
		if (TIPO_CUENTA_PRESTAMO_PERSONAL.equals(cuenta.getTipoCuenta())) {
			productoString = "Préstamo Personal";	
		} else if (TIPO_CUENTA_PRESTAMO_PRENDARIO.equals(cuenta.getTipoCuenta())) {
			productoString = "Préstamo Prendario";	
		} else if (TipoCuentaTarjetaEnum.getTipoCuentaTarjetaFromTipoCuenta(TipoCuenta.VISA).getCodigo().equals(cuenta.getTipoCuenta())) {
			productoString = TipoCuentaTarjetaEnum.TIPOCTA_VISA.getAbreviatura() + " " + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA);
		} else if (TipoCuentaTarjetaEnum.getTipoCuentaTarjetaFromTipoCuenta(TipoCuenta.MASTERCARD).getCodigo().equals(cuenta.getTipoCuenta())) {
			productoString = TipoCuentaTarjetaEnum.TIPOCTA_MASTER.getAbreviatura() + " " + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_MASTER);
		} else if (TipoCuentaTarjetaEnum.getTipoCuentaTarjetaFromTipoCuenta(TipoCuenta.AMEX).getCodigo().equals(cuenta.getTipoCuenta())) {
			productoString = TipoCuentaTarjetaEnum.TIPOCTA_AMEX.getAbreviatura() + " " + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_AMEX);
		} else if (DESCUBIERTO1.equals(cuenta.getTipoCuentaSinUnificar()) || DESCUBIERTO2.equals(cuenta.getTipoCuentaSinUnificar()) ||
				DESCUBIERTO3.equals(cuenta.getTipoCuentaSinUnificar()) || DESCUBIERTO4.equals(cuenta.getTipoCuentaSinUnificar())) {
			productoString = "Descubierto";
		}
		
		return productoString;
		
	}
	
	private AtrasoDeudaPromesaPagoEnum obtenerAtrasoDeuda(String fechaInicioDeuda) {
		
		String anio = fechaInicioDeuda.substring(0,4);
		String mes = fechaInicioDeuda.substring(4,6);
		String dia = fechaInicioDeuda.substring(6,8);
		DateTime fechaInicioDeudaJodaTime = new DateTime(anio+"-"+mes+"-"+dia);
		DateTime fechaHoyJodaTime = new DateTime();
		Interval intervalo = new Interval(fechaInicioDeudaJodaTime, fechaHoyJodaTime);
		Days diasTranscurridos = Days.daysIn(intervalo);
		int diasTranscurridosInt = diasTranscurridos.getDays();
		
		if (diasTranscurridosInt >= DIA_MINIMO_TRAMO1 && diasTranscurridosInt <= DIA_MAXIMO_TRAMO1) {
			return AtrasoDeudaPromesaPagoEnum.TRAMO_1;
		} else if (diasTranscurridosInt >= DIA_MINIMO_TRAMO2 && diasTranscurridosInt <= DIA_MAXIMO_TRAMO2) {
			return AtrasoDeudaPromesaPagoEnum.TRAMO_2;
		} else {
			return AtrasoDeudaPromesaPagoEnum.TRAMO_3;
		}
		
	}
	
	
	
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDeuda() {
		return deuda;
	}

	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
		
	public AtrasoDeudaPromesaPagoEnum getTipoAtrasoDeuda() {
		return tipoAtrasoDeuda;
	}

	public void setTipoAtrasoDeuda(AtrasoDeudaPromesaPagoEnum tipoAtrasoDeuda) {
		this.tipoAtrasoDeuda = tipoAtrasoDeuda;
	}
	
	
	public static void main(String[] args) {
	
		List<String> fechas = new ArrayList<String>();
		
		DateTime fechaJodaTime = new DateTime();
		
		while (fechas.size() <= 7) {
			if (fechaJodaTime.getDayOfWeek() != 6 && fechaJodaTime.getDayOfWeek() != 7) {
				fechas.add(fechaJodaTime.toString());
				fechaJodaTime = fechaJodaTime.plusDays(1);
			} else {
				fechaJodaTime = fechaJodaTime.plusDays(1);
			}
		}
		
		for (String fecha : fechas) {
			System.out.println(fecha);
		}
		
	}
		
}
