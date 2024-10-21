/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.sei;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;

/**
 * The Class RangoCuota.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RangoCuota {

	/** The cant min cuotas. */
	private String cantMinCuotas;

	/** The cant max cuotas. */
	private String cantMaxCuotas;

	/** The tna. */
	private String tna;

	/** The tipo de tasa. */
	private String tipoDeTasa;
	
	/** The indicador Linea Uva. */
	private String indLineaUva;
	
	/** The destinos prestamos seleccion. */
	private List<DestinoPrestamoSeleccionView> destinosPrestamosSeleccion;

	/** The importe minimo. */
	private BigDecimal importeMinimo;
	
	/** The importe maximo. */
	private BigDecimal importeMaximo;
	
	private String indPreaprobado;
	
	private String fechaSolicitudHasta;
	
	private String fechaVigenciaHasta;
	
	/**
	 * Instantiates a new rango cuota.
	 */
	public RangoCuota() {
		super();
	}

	/**
	 * Instantiates a new rango cuota.
	 *
	 * @param infoPrestamos
	 *            the info prestamos
	 * @param listaDestinos
	 *            the lista destinos
	 */
	public RangoCuota(PrestamoPermitidoEntity infoPrestamos, List<DestinoPrestamoSeleccionView> listaDestinos) {
		this.setCantMinCuotas(ISBANStringUtils.eliminarCeros(infoPrestamos.getMinCantCuotas()));
		this.setCantMaxCuotas(ISBANStringUtils.eliminarCeros(infoPrestamos.getMaxCantCuotas()));
		this.setTipoDeTasa(infoPrestamos.getTpoTasa());
		this.setTna(infoPrestamos.getValorTasa());
		this.setDestinosPrestamosSeleccion(listaDestinos);
		this.setIndLineaUva(infoPrestamos.getIndLineaUVA().toUpperCase());
		this.setImporteMinimo(formatearValor(infoPrestamos.getMinImpPrest(), 4));
		this.setImporteMaximo(formatearValor(infoPrestamos.getMaxImpPrest(), 4));
		this.setIndPreaprobado("N");
	}

	/**
	 * Gets the cant min cuotas.
	 *
	 * @return the cant min cuotas
	 */
	public String getCantMinCuotas() {
		return cantMinCuotas;
	}

	/**
	 * Sets the cant min cuotas.
	 *
	 * @param cantMinCuotas
	 *            the new cant min cuotas
	 */
	public void setCantMinCuotas(String cantMinCuotas) {
		this.cantMinCuotas = cantMinCuotas;
	}

	/**
	 * Gets the cant max cuotas.
	 *
	 * @return the cant max cuotas
	 */
	public String getCantMaxCuotas() {
		return cantMaxCuotas;
	}

	/**
	 * Sets the cant max cuotas.
	 *
	 * @param cantMaxCuotas
	 *            the new cant max cuotas
	 */
	public void setCantMaxCuotas(String cantMaxCuotas) {
		this.cantMaxCuotas = cantMaxCuotas;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the tipo de tasa.
	 *
	 * @return the tipo de tasa
	 */
	public String getTipoDeTasa() {
		return tipoDeTasa;
	}

	/**
	 * Sets the tipo de tasa.
	 *
	 * @param tipoDeTasa
	 *            the new tipo de tasa
	 */
	public void setTipoDeTasa(String tipoDeTasa) {
		this.tipoDeTasa = tipoDeTasa;
	}

	/**
	 * Gets the destinos prestamos seleccion.
	 *
	 * @return the destinos prestamos seleccion
	 */
	public List<DestinoPrestamoSeleccionView> getDestinosPrestamosSeleccion() {
		return destinosPrestamosSeleccion;
	}

	/**
	 * Sets the destinos prestamos seleccion.
	 *
	 * @param destinosPrestamosSeleccion
	 *            the new destinos prestamos seleccion
	 */
	public void setDestinosPrestamosSeleccion(List<DestinoPrestamoSeleccionView> destinosPrestamosSeleccion) {
		this.destinosPrestamosSeleccion = destinosPrestamosSeleccion;
	}

	/**
	 * Gets the indicador Linea Uva.
	 *
	 * @return the ind linea uva
	 */
	public String getIndLineaUva() {
		return indLineaUva;
	}

	/**
	 * Sets the indicador Linea Uva.
	 *
	 * @param indLineaUva
	 *            the linea uva
	 */
	public void setIndLineaUva(String indLineaUva) {
		this.indLineaUva = indLineaUva;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public BigDecimal getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the importe minimo
	 */
	public void setImporteMinimo(BigDecimal importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public BigDecimal getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the new importe maximo
	 */
	public void setImporteMaximo(BigDecimal importeMaximo) {
		this.importeMaximo = importeMaximo;
	}
	
	/**
	 * Formatear valor.
	 *
	 * @param valor
	 *            the valor
	 * @param cantidadLugares
	 *            the cantidad lugares
	 * @return the big decimal
	 */
	private BigDecimal formatearValor(String valor, int cantidadLugares) {
		if (("0").equals(ISBANStringUtils.eliminarCeros(valor))) {
			return BigDecimal.ZERO;
		}
		String numeroFormat = ISBANStringUtils.eliminarCeros(valor);
		String numeroParteUno = numeroFormat.substring(0, numeroFormat.length() - cantidadLugares);
		String numeroParteDos = numeroFormat.substring(numeroFormat.length() - cantidadLugares);
		String numeroParaConvertir = numeroParteUno + "." + numeroParteDos;
		return new BigDecimal(numeroParaConvertir);
	}

	public String getIndPreaprobado() {
		return indPreaprobado;
	}

	public void setIndPreaprobado(String indPreaprobado) {
		this.indPreaprobado = indPreaprobado;
	}
	
	public String getFechaSolicitudHasta() {
		return fechaSolicitudHasta;
	}

	public void setFechaSolicitudHasta(String fechaSolicitudHasta) {
		this.fechaSolicitudHasta = fechaSolicitudHasta;
	}

	public String getFechaVigenciaHasta() {
		return fechaVigenciaHasta;
	}

	public void setFechaVigenciaHasta(String fechaVigenciaHasta) {
		this.fechaVigenciaHasta = fechaVigenciaHasta;
	}
}