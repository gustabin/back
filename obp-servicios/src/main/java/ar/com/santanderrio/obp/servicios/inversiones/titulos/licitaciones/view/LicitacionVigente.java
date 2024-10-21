/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CanalTramoCtaTitulo;

/**
 * The Class LicitacionVigente.
 */
public class LicitacionVigente {

	/** The especie. */
	private String especie;

	/** The fecha cierre. */
	private String fechaCierre;

	/** The hora cierre. */
	private String horaCierre;

	/** The moneda especie. */
	private String monedaEspecie;

	/** The codigo especie. */
	private String codigoEspecie;

	/** The codigo tramo. */
	private long codigoTramo;

	/** The codigo pliego. */
	private long codigoPliego;

	/** The codigo tramo canal. */
	private long codigoTramoCanal;

	/** The tipo oferta. */
	private String tipoOferta;

	/** The nominal min. */
	private double nominalMin;

	/** The nominal max. */
	private double nominalMax;

	/** The monto min. */
	private double montoMin;

	/** The monto max. */
	private double montoMax;

	/** The nominal increment. */
	private double nominalIncrement;

	/** The monto increment. */
	private double montoIncrement;

	/** The precio. */
	private double precio;

	/** The precio min. */
	private String precioMin;
	
	/** The precio min texto. */
	private String precioMinTexto;

	/** The precio max. */
	private double precioMax;
	
	/** The precio max texto. */
	private String precioMaxTexto;

	/** The precio increment. */
	private String precioIncrement;

	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;

	/** The fecha liquidacion titulos. */
	private String fechaLiquidacionTitulos;

	/** The correo electronico. */
	private String correoElectronico;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The tipo precio. */
	private String tipoPrecio;

	/** The moneda licitacion. */
	private String monedaLicitacion;

	/** The tramo. */
	private String tramo;

	/** The renovacion. */
	private String renovacion;

	/** The tipo cambio. */
	private String tipoCambio;

	/** The nombre archivo condiciones. */
	private String nombreArchivoCondiciones;

	/** The tipo instrumento. */
	private String tipoInstrumento;

	/** The descripcion instrumento. */
	private String descripcionInstrumento;

	/** The descripcion especie derechos. */
	private String descripcionEspecieDerechos;

	/** The relacion derechos canje. */
	private String relacionDerechosCanje;

	/** The cantidad maxima. */
	private double cantidadMaxima;

	/** The cantidad. */
	private double cantidad;

	/** The cuentas titulo. */
	private List<CanalTramoCtaTitulo> cuentasTitulo;

	/** The valida derechos. */
	private String validaDerechos;
	
	/** The tipo cuenta. */
	private String tipoCuenta;
	
	/** the nombrePdf. */
	private String nombrePdf;
	
	private String tipoEjecucion;

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fecha cierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre
	 *            the new fecha cierre
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the hora cierre.
	 *
	 * @return the hora cierre
	 */
	public String getHoraCierre() {
		return horaCierre;
	}

	/**
	 * Sets the hora cierre.
	 *
	 * @param horaCierre
	 *            the new hora cierre
	 */
	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	/**
	 * Gets the moneda especie.
	 *
	 * @return the moneda especie
	 */
	public String getMonedaEspecie() {
		return monedaEspecie;
	}

	/**
	 * Sets the moneda especie.
	 *
	 * @param monedaEspecie
	 *            the new moneda especie
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the codigo tramo.
	 *
	 * @return the codigo tramo
	 */
	public long getCodigoTramo() {
		return codigoTramo;
	}

	/**
	 * Sets the codigo tramo.
	 *
	 * @param codigoTramo
	 *            the new codigo tramo
	 */
	public void setCodigoTramo(long codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	/**
	 * Gets the codigo pliego.
	 *
	 * @return the codigo pliego
	 */
	public long getCodigoPliego() {
		return codigoPliego;
	}

	/**
	 * Sets the codigo pliego.
	 *
	 * @param codigoPliego
	 *            the new codigo pliego
	 */
	public void setCodigoPliego(long codigoPliego) {
		this.codigoPliego = codigoPliego;
	}

	/**
	 * Gets the codigo tramo canal.
	 *
	 * @return the codigo tramo canal
	 */
	public long getCodigoTramoCanal() {
		return codigoTramoCanal;
	}

	/**
	 * Sets the codigo tramo canal.
	 *
	 * @param codigoTramoCanal
	 *            the new codigo tramo canal
	 */
	public void setCodigoTramoCanal(long codigoTramoCanal) {
		this.codigoTramoCanal = codigoTramoCanal;
	}

	/**
	 * Gets the tipo oferta.
	 *
	 * @return the tipo oferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * Sets the tipo oferta.
	 *
	 * @param tipoOferta
	 *            the new tipo oferta
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * Gets the nominal min.
	 *
	 * @return the nominal min
	 */
	public double getNominalMin() {
		return nominalMin;
	}

	/**
	 * Sets the nominal min.
	 *
	 * @param nominalMin
	 *            the new nominal min
	 */
	public void setNominalMin(double nominalMin) {
		this.nominalMin = nominalMin;
	}

	/**
	 * Gets the nominal max.
	 *
	 * @return the nominal max
	 */
	public double getNominalMax() {
		return nominalMax;
	}

	/**
	 * Sets the nominal max.
	 *
	 * @param nominalMax
	 *            the new nominal max
	 */
	public void setNominalMax(double nominalMax) {
		this.nominalMax = nominalMax;
	}

	/**
	 * Gets the monto min.
	 *
	 * @return the monto min
	 */
	public double getMontoMin() {
		return montoMin;
	}

	/**
	 * Sets the monto min.
	 *
	 * @param montoMin
	 *            the new monto min
	 */
	public void setMontoMin(double montoMin) {
		this.montoMin = montoMin;
	}

	/**
	 * Gets the monto max.
	 *
	 * @return the monto max
	 */
	public double getMontoMax() {
		return montoMax;
	}

	/**
	 * Sets the monto max.
	 *
	 * @param montoMax
	 *            the new monto max
	 */
	public void setMontoMax(double montoMax) {
		this.montoMax = montoMax;
	}

	/**
	 * Gets the nominal increment.
	 *
	 * @return the nominal increment
	 */
	public double getNominalIncrement() {
		return nominalIncrement;
	}

	/**
	 * Sets the nominal increment.
	 *
	 * @param nominalIncrement
	 *            the new nominal increment
	 */
	public void setNominalIncrement(double nominalIncrement) {
		this.nominalIncrement = nominalIncrement;
	}

	/**
	 * Gets the monto increment.
	 *
	 * @return the monto increment
	 */
	public double getMontoIncrement() {
		return montoIncrement;
	}

	/**
	 * Sets the monto increment.
	 *
	 * @param montoIncrement
	 *            the new monto increment
	 */
	public void setMontoIncrement(double montoIncrement) {
		this.montoIncrement = montoIncrement;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio
	 *            the new precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Gets the precio min.
	 *
	 * @return the precio min
	 */
	public String getPrecioMin() {
		return precioMin;
	}

	/**
	 * Sets the precio min.
	 *
	 * @param precioMin
	 *            the new precio min
	 */
	public void setPrecioMin(String precioMin) {
		this.precioMin = precioMin;
	}

	/**
	 * Gets the precio max.
	 *
	 * @return the precio max
	 */
	public double getPrecioMax() {
		return precioMax;
	}

	/**
	 * Sets the precio max.
	 *
	 * @param precioMax
	 *            the new precio max
	 */
	public void setPrecioMax(double precioMax) {
		this.precioMax = precioMax;
	}

	/**
	 * Gets the precio increment.
	 *
	 * @return the precio increment
	 */
	public String getPrecioIncrement() {
		return precioIncrement;
	}

	/**
	 * Sets the precio increment.
	 *
	 * @param precioIncrement
	 *            the new precio increment
	 */
	public void setPrecioIncrement(String precioIncrement) {
		this.precioIncrement = precioIncrement;
	}

	/**
	 * Gets the fecha adjudicacion.
	 *
	 * @return the fecha adjudicacion
	 */
	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	/**
	 * Sets the fecha adjudicacion.
	 *
	 * @param fechaAdjudicacion
	 *            the new fecha adjudicacion
	 */
	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	/**
	 * Gets the fecha liquidacion titulos.
	 *
	 * @return the fecha liquidacion titulos
	 */
	public String getFechaLiquidacionTitulos() {
		return fechaLiquidacionTitulos;
	}

	/**
	 * Sets the fecha liquidacion titulos.
	 *
	 * @param fechaLiquidacionTitulos
	 *            the new fecha liquidacion titulos
	 */
	public void setFechaLiquidacionTitulos(String fechaLiquidacionTitulos) {
		this.fechaLiquidacionTitulos = fechaLiquidacionTitulos;
	}

	/**
	 * Gets the correo electronico.
	 *
	 * @return the correo electronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Sets the correo electronico.
	 *
	 * @param correoElectronico
	 *            the new correo electronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Gets the leyenda legal.
	 *
	 * @return the leyenda legal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the new leyenda legal
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the tipo precio.
	 *
	 * @return the tipo precio
	 */
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	/**
	 * Sets the tipo precio.
	 *
	 * @param tipoPrecio
	 *            the new tipo precio
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * Gets the moneda licitacion.
	 *
	 * @return the moneda licitacion
	 */
	public String getMonedaLicitacion() {
		return monedaLicitacion;
	}

	/**
	 * Sets the moneda licitacion.
	 *
	 * @param monedaLicitacion
	 *            the new moneda licitacion
	 */
	public void setMonedaLicitacion(String monedaLicitacion) {
		this.monedaLicitacion = monedaLicitacion;
	}

	/**
	 * Gets the tramo.
	 *
	 * @return the tramo
	 */
	public String getTramo() {
		return tramo;
	}

	/**
	 * Sets the tramo.
	 *
	 * @param tramo
	 *            the new tramo
	 */
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}

	/**
	 * Gets the renovacion.
	 *
	 * @return the renovacion
	 */
	public String getRenovacion() {
		return renovacion;
	}

	/**
	 * Sets the renovacion.
	 *
	 * @param renovacion
	 *            the new renovacion
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the tipo cambio.
	 *
	 * @return the tipo cambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * Sets the tipo cambio.
	 *
	 * @param tipoCambio
	 *            the new tipo cambio
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * Sets the nombre archivo condiciones.
	 *
	 * @param nombreArchivoCondiciones
	 *            the new nombre archivo condiciones
	 */
	public void setNombreArchivoCondiciones(String nombreArchivoCondiciones) {
		this.nombreArchivoCondiciones = nombreArchivoCondiciones;
	}

	/**
	 * Gets the nombre archivo condiciones.
	 *
	 * @return the nombre archivo condiciones
	 */
	public String getNombreArchivoCondiciones() {
		return nombreArchivoCondiciones;
	}

	/**
	 * Gets the tipo instrumento.
	 *
	 * @return the tipo instrumento
	 */
	public String getTipoInstrumento() {
		return tipoInstrumento;
	}

	/**
	 * Gets the descripcion instrumento.
	 *
	 * @return the descripcion instrumento
	 */
	public String getDescripcionInstrumento() {
		return descripcionInstrumento;
	}

	/**
	 * Sets the tipo instrumento.
	 *
	 * @param tipoInstrumento
	 *            the new tipo instrumento
	 */
	public void setTipoInstrumento(String tipoInstrumento) {
		this.tipoInstrumento = tipoInstrumento;
	}

	/**
	 * Sets the descripcion instrumento.
	 *
	 * @param descripcionInstrumento
	 *            the new descripcion instrumento
	 */
	public void setDescripcionInstrumento(String descripcionInstrumento) {
		this.descripcionInstrumento = descripcionInstrumento;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CanalTramoCtaTitulo> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CanalTramoCtaTitulo> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

	/**
	 * Gets the descripcion especie derechos.
	 *
	 * @return the descripcion especie derechos
	 */
	public String getDescripcionEspecieDerechos() {
		return descripcionEspecieDerechos;
	}

	/**
	 * Sets the descripcion especie derechos.
	 *
	 * @param descripcionEspecieDerechos
	 *            the new descripcion especie derechos
	 */
	public void setDescripcionEspecieDerechos(String descripcionEspecieDerechos) {
		this.descripcionEspecieDerechos = descripcionEspecieDerechos;
	}

	/**
	 * Gets the relacion derechos canje.
	 *
	 * @return the relacion derechos canje
	 */
	public String getRelacionDerechosCanje() {
		return relacionDerechosCanje;
	}

	/**
	 * Sets the relacion derechos canje.
	 *
	 * @param relacionDerechosCanje
	 *            the new relacion derechos canje
	 */
	public void setRelacionDerechosCanje(String relacionDerechosCanje) {
		this.relacionDerechosCanje = relacionDerechosCanje;
	}

	/**
	 * Gets the cantidad maxima.
	 *
	 * @return the cantidad maxima
	 */
	public double getCantidadMaxima() {
		return cantidadMaxima;
	}

	/**
	 * Sets the cantidad maxima.
	 *
	 * @param cantidadMaxima
	 *            the new cantidad maxima
	 */
	public void setCantidadMaxima(double cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the valida derechos.
	 *
	 * @return the valida derechos
	 */
	public String getValidaDerechos() {
		return validaDerechos;
	}

	/**
	 * Sets the valida derechos.
	 *
	 * @param validaDerechos
	 *            the new valida derechos
	 */
	public void setValidaDerechos(String validaDerechos) {
		this.validaDerechos = validaDerechos;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nombre pdf.
	 *
	 * @return the nombre pdf
	 */
	public String getNombrePdf() {
		return nombrePdf;
	}

	/**
	 * Sets the nombre pdf.
	 *
	 * @param nombrePdf
	 *            the new nombre pdf
	 */
	public void setNombrePdf(String nombrePdf) {
		this.nombrePdf = nombrePdf;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(especie);
		hcb.append(monedaEspecie);
		hcb.append(codigoEspecie);
		hcb.append(codigoTramo);
		hcb.append(codigoPliego);
		hcb.append(codigoTramoCanal);
		return hcb.toHashCode();

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		LicitacionVigente other = (LicitacionVigente) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(especie, other.getEspecie());
		eb.append(monedaEspecie, other.getMonedaEspecie());
		eb.append(codigoEspecie, other.getCodigoEspecie());
		eb.append(codigoTramo, other.getCodigoTramo());
		eb.append(codigoPliego, other.getCodigoPliego());
		eb.append(codigoTramoCanal, other.getCodigoTramoCanal());
		return eb.isEquals();
	}

	/**
	 * Gets the precio min texto.
	 *
	 * @return the precio min texto
	 */
	public String getPrecioMinTexto() {
		return precioMinTexto;
	}

	/**
	 * Sets the precio min texto.
	 *
	 * @param precioMinTexto
	 *            the new precio min texto
	 */
	public void setPrecioMinTexto(String precioMinTexto) {
		this.precioMinTexto = precioMinTexto;
	}

	/**
	 * Gets the precio max texto.
	 *
	 * @return the precio max texto
	 */
	public String getPrecioMaxTexto() {
		return precioMaxTexto;
	}

	/**
	 * Sets the precio max texto.
	 *
	 * @param precioMaxTexto
	 *            the new precio max texto
	 */
	public void setPrecioMaxTexto(String precioMaxTexto) {
		this.precioMaxTexto = precioMaxTexto;
	}

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}
	
	
}
