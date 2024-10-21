/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class ResumenMesualCuentaView.
 */
public class ResumenMesualCuentaView implements Comparable<ResumenMesualCuentaView>{

	/** The id. */
	private Long id;

	/** The visto. */
	private Boolean visto;

	/** The mes. */
	private String mes;

	/** The anio. */
	private String anio;

	/** The dia. */
	private String dia;
	
	private String referencia;
	
	private String nroLiquidacion;

	public ResumenMesualCuentaView () {
		super();
	}
	
	public ResumenMesualCuentaView (ResumenMensualCuenta resumenMensualCuenta) {
		
        this.setMes(formatearMes(resumenMensualCuenta.getFecha()));
        this.setAnio(formatearAnio(resumenMensualCuenta.getFecha()));
        this.setDia(formatearDia(resumenMensualCuenta.getFecha()));
        this.setVisto(resumenMensualCuenta.getVisto());
        this.setId(resumenMensualCuenta.getId());
        this.setReferencia(resumenMensualCuenta.getReferencia());
        this.setNroLiquidacion(resumenMensualCuenta.getNroLiquidacion());
		
	}
	
    private String formatearDia(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    /**
     * Formatear mes.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    private String formatearMes(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    /**
     * Formatear anio.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    private String formatearAnio(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
        return formateador.format(fecha);
    }
	
	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 *
	 * @param dia
	 *            the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the visto.
	 *
	 * @return the visto
	 */
	public Boolean getVisto() {
		return visto;
	}

	/**
	 * Sets the visto.
	 *
	 * @param visto
	 *            the new visto
	 */
	public void setVisto(Boolean visto) {
		this.visto = visto;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getNroLiquidacion() {
		return nroLiquidacion;
	}

	public void setNroLiquidacion(String nroLiquidacion) {
		this.nroLiquidacion = nroLiquidacion;
	}

	@Override
	public int compareTo(ResumenMesualCuentaView o) {
        int anioCompare1 = Integer.parseInt(anio);
        int anioCompare2 = Integer.parseInt(o.anio);
        int mesCompare1 = detectarMes(mes);
        int mesCompare2 = detectarMes(o.mes);
        int diaCompare1 = Integer.parseInt(dia);
        int diaCompare2 = Integer.parseInt(o.dia);
        
        DateTime fecha1 = new DateTime(anioCompare1, mesCompare1, diaCompare1, 0, 0);
        DateTime fecha2 = new DateTime(anioCompare2, mesCompare2, diaCompare2, 0, 0);
        
        return fecha2.compareTo(fecha1);
	}
	
	private int detectarMes(String mes) {
		
		int mesNumero = 0;
		
		if ("Enero".equals(mes)) {
			mesNumero = 1;
		} else if ("Febrero".equals(mes)) {
			mesNumero = 2;
		} else if ("Marzo".equals(mes)) {
			mesNumero = 3;
		} else if ("Abril".equals(mes)) {
			mesNumero = 4;
		} else if ("Mayo".equals(mes)) {
			mesNumero = 5;
		} else if ("Junio".equals(mes)) {
			mesNumero = 6;
		} else if ("Julio".equals(mes)) {
			mesNumero = 7;
		} else if ("Agosto".equals(mes)) {
			mesNumero = 8;
		} else if ("Septiembre".equals(mes)) {
			mesNumero = 9;
		} else if ("Octubre".equals(mes)) {
			mesNumero = 10;
		} else if ("Noviembre".equals(mes)) {
			mesNumero = 11;
		} else {
			mesNumero = 12;
		}
		
		return mesNumero;
	}

}
