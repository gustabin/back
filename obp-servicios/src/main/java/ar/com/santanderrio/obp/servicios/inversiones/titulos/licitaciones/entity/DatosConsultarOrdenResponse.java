/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

/**
 * The Class DatosConsultarOrdenResponse.
 */
public class DatosConsultarOrdenResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2299138655498750216L;

	/** The codigo tramo canal. */
	private String codigoTramoCanal;

	/** The tipo pliego. */
	private String tipoPliego;

	/** The codigo pliego. */
	private String codigoPliego;

	/** The codigo tramo. */
	private String codigoTramo;

	/** The canal. */
	private String canal;

	/** The sub canal. */
	private String subCanal;

	/** The descripcion pliego. */
	private String descripcionPliego;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The tipo cambio. */
	private String tipoCambio;

	/** The sucursal. */
	private String sucursal;

	/** The tipo cuenta operativa. */
	private String tipoCuentaOperativa;

	/** The cuenta operativa. */
	private String cuentaOperativa;

	/** The cuenta custodia. */
	private String cuentaCustodia;

	/** The cuenta titulos. */
	private String cuentaTitulos;

	/** The nup. */
	private String nup;

	/** The especie. */
	private String especie;

	/** The descripcion especie. */
	private String descripcionEspecie;

	/** The moneda especie. */
	private String monedaEspecie;

	/** The especie otorgar. */
	private String especieOtorgar;

	/** The descripcion especie otorgar. */
	private String descripcionEspecieOtorgar;

	/** The relacion derechos canje. */
	private double relacionDerechosCanje;

	/** The moneda. */
	private String moneda;

	/** The descripcion moneda. */
	private String descripcionMoneda;

	/** The cantidad. */
	private String cantidad;

	/** The monto. */
	private String monto;

	/** The precio. */
	private String precio;

	/** The correo electronico. */
	private String correoElectronico;

	/** The monto A debitar. */
	private String montoADebitar;

	/** The monto comision. */
	private String montoComision;

	/** The monto impuesto. */
	private String montoImpuesto;

	/** The fecha orden. */
	private String fechaOrden;

	/** The fecha deb prov. */
	private String fechaDebProv;

	/** The fecha titulos. */
	private String fechaTitulos;

	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;

	/** The fecha liquidacion. */
	private String fechaLiquidacion;

	/** The fecha cierre. */
	private String fechaCierre;

	/** The numero orden. */
	private String numeroOrden;

	/** The permite reversa. */
	private String permiteReversa;

	/** The cantidad adjudicada. */
	private String cantidadAdjudicada;

	/** The precio adjudicado. */
	private String precioAdjudicado;

	/** The renovacion. */
	private String renovacion;

	/** The especie renovacion. */
	private String especieRenovacion;

	/** The cantidad renovacion. */
	private String cantidadRenovacion;

	/** The Lugar renovacion. */
	private String LugarRenovacion;

	/** The orden externa. */
	private String ordenExterna;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The leyenda legal can. */
	private String leyendaLegalCan;

	/** The tramo competitivo. */
	private String tramoCompetitivo;

	/** The nominal min. */
	private String nominalMin;

	/** The nominal max. */
	private String nominalMax;

	/** The nominal increment. */
	private String nominalIncrement;

	/** The monto min. */
	private String montoMin;

	/** The monto max. */
	private String montoMax;

	/** The monto increment. */
	private String montoIncrement;

	/** The precio min. */
	private String precioMin;

	/** The precio max. */
	private String precioMax;

	/** The precio increment. */
	private String precioIncrement;

	/** The estado. */
	private String estado;

	/** The tipo precio. */
	private String tipoPrecio;

	/** The tipo precio adjudicado. */
	private String tipoPrecioAdjudicado;

	/** The cod instrumento. */
	private String codInstrumento;

	/** The desc instrumento. */
	private String descInstrumento;

	private String codMonedaEspecieDestino;

	/**
	 * Gets the codigo tramo canal.
	 *
	 * @return the codigo tramo canal
	 */
	public String getCodigoTramoCanal() {
		return codigoTramoCanal;
	}

	/**
	 * Sets the codigo tramo canal.
	 *
	 * @param codigoTramoCanal the new codigo tramo canal
	 */
	public void setCodigoTramoCanal(String codigoTramoCanal) {
		this.codigoTramoCanal = codigoTramoCanal;
	}

	/**
	 * Gets the tipo pliego.
	 *
	 * @return the tipo pliego
	 */
	public String getTipoPliego() {
		return tipoPliego;
	}

	/**
	 * Sets the tipo pliego.
	 *
	 * @param tipoPliego the new tipo pliego
	 */
	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

	/**
	 * Gets the codigo pliego.
	 *
	 * @return the codigo pliego
	 */
	public String getCodigoPliego() {
		return codigoPliego;
	}

	/**
	 * Sets the codigo pliego.
	 *
	 * @param codigoPliego the new codigo pliego
	 */
	public void setCodigoPliego(String codigoPliego) {
		this.codigoPliego = codigoPliego;
	}

	/**
	 * Gets the codigo tramo.
	 *
	 * @return the codigo tramo
	 */
	public String getCodigoTramo() {
		return codigoTramo;
	}

	/**
	 * Sets the codigo tramo.
	 *
	 * @param codigoTramo the new codigo tramo
	 */
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the sub canal.
	 *
	 * @return the sub canal
	 */
	public String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal the new sub canal
	 */
	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	/**
	 * Gets the descripcion pliego.
	 *
	 * @return the descripcion pliego
	 */
	public String getDescripcionPliego() {
		return descripcionPliego;
	}

	/**
	 * Sets the descripcion pliego.
	 *
	 * @param descripcionPliego the new descripcion pliego
	 */
	public void setDescripcionPliego(String descripcionPliego) {
		this.descripcionPliego = descripcionPliego;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the tipo cuenta operativa.
	 *
	 * @return the tipo cuenta operativa
	 */
	public String getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	/**
	 * Sets the tipo cuenta operativa.
	 *
	 * @param tipoCuentaOperativa the new tipo cuenta operativa
	 */
	public void setTipoCuentaOperativa(String tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa the new cuenta operativa
	 */
	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Gets the cuenta custodia.
	 *
	 * @return the cuenta custodia
	 */
	public String getCuentaCustodia() {
		return cuentaCustodia;
	}

	/**
	 * Sets the cuenta custodia.
	 *
	 * @param cuentaCustodia the new cuenta custodia
	 */
	public void setCuentaCustodia(String cuentaCustodia) {
		this.cuentaCustodia = cuentaCustodia;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

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
	 * @param especie the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
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
	 * @param monedaEspecie the new moneda especie
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
	}

	/**
	 * Gets the especie otorgar.
	 *
	 * @return the especie otorgar
	 */
	public String getEspecieOtorgar() {
		return especieOtorgar;
	}

	/**
	 * Sets the especie otorgar.
	 *
	 * @param especieOtorgar the new especie otorgar
	 */
	public void setEspecieOtorgar(String especieOtorgar) {
		this.especieOtorgar = especieOtorgar;
	}

	/**
	 * Gets the descripcion especie otorgar.
	 *
	 * @return the descripcion especie otorgar
	 */
	public String getDescripcionEspecieOtorgar() {
		return descripcionEspecieOtorgar;
	}

	/**
	 * Sets the descripcion especie otorgar.
	 *
	 * @param descripcionEspecieOtorgar the new descripcion especie otorgar
	 */
	public void setDescripcionEspecieOtorgar(String descripcionEspecieOtorgar) {
		this.descripcionEspecieOtorgar = descripcionEspecieOtorgar;
	}

	/**
	 * Gets the relacion derechos canje.
	 *
	 * @return the relacion derechos canje
	 */
	public double getRelacionDerechosCanje() {
		return relacionDerechosCanje;
	}

	/**
	 * Sets the relacion derechos canje.
	 *
	 * @param relacionDerechosCanje the new relacion derechos canje
	 */
	public void setRelacionDerechosCanje(double relacionDerechosCanje) {
		this.relacionDerechosCanje = relacionDerechosCanje;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcion moneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda the new descripcion moneda
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio the new precio
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
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
	 * @param correoElectronico the new correo electronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Gets the monto A debitar.
	 *
	 * @return the monto A debitar
	 */
	public String getMontoADebitar() {
		return montoADebitar;
	}

	/**
	 * Sets the monto A debitar.
	 *
	 * @param montoADebitar the new monto A debitar
	 */
	public void setMontoADebitar(String montoADebitar) {
		this.montoADebitar = montoADebitar;
	}

	/**
	 * Gets the monto comision.
	 *
	 * @return the monto comision
	 */
	public String getMontoComision() {
		return montoComision;
	}

	/**
	 * Sets the monto comision.
	 *
	 * @param montoComision the new monto comision
	 */
	public void setMontoComision(String montoComision) {
		this.montoComision = montoComision;
	}

	/**
	 * Gets the monto impuesto.
	 *
	 * @return the monto impuesto
	 */
	public String getMontoImpuesto() {
		return montoImpuesto;
	}

	/**
	 * Sets the monto impuesto.
	 *
	 * @param montoImpuesto the new monto impuesto
	 */
	public void setMontoImpuesto(String montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fecha orden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden the new fecha orden
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the fecha deb prov.
	 *
	 * @return the fecha deb prov
	 */
	public String getFechaDebProv() {
		return fechaDebProv;
	}

	/**
	 * Sets the fecha deb prov.
	 *
	 * @param fechaDebProv the new fecha deb prov
	 */
	public void setFechaDebProv(String fechaDebProv) {
		this.fechaDebProv = fechaDebProv;
	}

	/**
	 * Gets the fecha titulos.
	 *
	 * @return the fecha titulos
	 */
	public String getFechaTitulos() {
		return fechaTitulos;
	}

	/**
	 * Sets the fecha titulos.
	 *
	 * @param fechaTitulos the new fecha titulos
	 */
	public void setFechaTitulos(String fechaTitulos) {
		this.fechaTitulos = fechaTitulos;
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
	 * @param fechaAdjudicacion the new fecha adjudicacion
	 */
	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidación.
	 *
	 * @param fechaLiquidacion the new fecha liquidación
	 */
	public void setFechaLiquidación(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
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
	 * @param fechaCierre the new fecha cierre
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the permite reversa.
	 *
	 * @return the permite reversa
	 */
	public String getPermiteReversa() {
		return permiteReversa;
	}

	/**
	 * Sets the permite reversa.
	 *
	 * @param permiteReversa the new permite reversa
	 */
	public void setPermiteReversa(String permiteReversa) {
		this.permiteReversa = permiteReversa;
	}

	/**
	 * Gets the cantidad adjudicada.
	 *
	 * @return the cantidad adjudicada
	 */
	public String getCantidadAdjudicada() {
		return cantidadAdjudicada;
	}

	/**
	 * Sets the cantidad adjudicada.
	 *
	 * @param cantidadAdjudicada the new cantidad adjudicada
	 */
	public void setCantidadAdjudicada(String cantidadAdjudicada) {
		this.cantidadAdjudicada = cantidadAdjudicada;
	}

	/**
	 * Gets the precio adjudicado.
	 *
	 * @return the precio adjudicado
	 */
	public String getPrecioAdjudicado() {
		return precioAdjudicado;
	}

	/**
	 * Sets the precio adjudicado.
	 *
	 * @param precioAdjudicado the new precio adjudicado
	 */
	public void setPrecioAdjudicado(String precioAdjudicado) {
		this.precioAdjudicado = precioAdjudicado;
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
	 * @param renovacion the new renovacion
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the especie renovacion.
	 *
	 * @return the especie renovacion
	 */
	public String getEspecieRenovacion() {
		return especieRenovacion;
	}

	/**
	 * Sets the especie renovacion.
	 *
	 * @param especieRenovacion the new especie renovacion
	 */
	public void setEspecieRenovacion(String especieRenovacion) {
		this.especieRenovacion = especieRenovacion;
	}

	/**
	 * Gets the cantidad renovacion.
	 *
	 * @return the cantidad renovacion
	 */
	public String getCantidadRenovacion() {
		return cantidadRenovacion;
	}

	/**
	 * Sets the cantidad renovacion.
	 *
	 * @param cantidadRenovacion the new cantidad renovacion
	 */
	public void setCantidadRenovacion(String cantidadRenovacion) {
		this.cantidadRenovacion = cantidadRenovacion;
	}

	/**
	 * Gets the lugar renovacion.
	 *
	 * @return the lugar renovacion
	 */
	public String getLugarRenovacion() {
		return LugarRenovacion;
	}

	/**
	 * Sets the lugar renovacion.
	 *
	 * @param lugarRenovacion the new lugar renovacion
	 */
	public void setLugarRenovacion(String lugarRenovacion) {
		LugarRenovacion = lugarRenovacion;
	}

	/**
	 * Gets the orden externa.
	 *
	 * @return the orden externa
	 */
	public String getOrdenExterna() {
		return ordenExterna;
	}

	/**
	 * Sets the orden externa.
	 *
	 * @param ordenExterna the new orden externa
	 */
	public void setOrdenExterna(String ordenExterna) {
		this.ordenExterna = ordenExterna;
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
	 * @param leyendaLegal the new leyenda legal
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the leyenda legal can.
	 *
	 * @return the leyenda legal can
	 */
	public String getLeyendaLegalCan() {
		return leyendaLegalCan;
	}

	/**
	 * Sets the leyenda legal can.
	 *
	 * @param leyendaLegalCan the new leyenda legal can
	 */
	public void setLeyendaLegalCan(String leyendaLegalCan) {
		this.leyendaLegalCan = leyendaLegalCan;
	}

	/**
	 * Gets the tramo competitivo.
	 *
	 * @return the tramo competitivo
	 */
	public String getTramoCompetitivo() {
		return tramoCompetitivo;
	}

	/**
	 * Sets the tramo competitivo.
	 *
	 * @param tramoCompetitivo the new tramo competitivo
	 */
	public void setTramoCompetitivo(String tramoCompetitivo) {
		this.tramoCompetitivo = tramoCompetitivo;
	}

	/**
	 * Gets the nominal min.
	 *
	 * @return the nominal min
	 */
	public String getNominalMin() {
		return nominalMin;
	}

	/**
	 * Sets the nominal min.
	 *
	 * @param nominalMin the new nominal min
	 */
	public void setNominalMin(String nominalMin) {
		this.nominalMin = nominalMin;
	}

	/**
	 * Gets the nominal max.
	 *
	 * @return the nominal max
	 */
	public String getNominalMax() {
		return nominalMax;
	}

	/**
	 * Sets the nominal max.
	 *
	 * @param nominalMax the new nominal max
	 */
	public void setNominalMax(String nominalMax) {
		this.nominalMax = nominalMax;
	}

	/**
	 * Gets the nominal increment.
	 *
	 * @return the nominal increment
	 */
	public String getNominalIncrement() {
		return nominalIncrement;
	}

	/**
	 * Sets the nominal increment.
	 *
	 * @param nominalIncrement the new nominal increment
	 */
	public void setNominalIncrement(String nominalIncrement) {
		this.nominalIncrement = nominalIncrement;
	}

	/**
	 * Gets the monto min.
	 *
	 * @return the monto min
	 */
	public String getMontoMin() {
		return montoMin;
	}

	/**
	 * Sets the monto min.
	 *
	 * @param montoMin the new monto min
	 */
	public void setMontoMin(String montoMin) {
		this.montoMin = montoMin;
	}

	/**
	 * Gets the monto max.
	 *
	 * @return the monto max
	 */
	public String getMontoMax() {
		return montoMax;
	}

	/**
	 * Sets the monto max.
	 *
	 * @param montoMax the new monto max
	 */
	public void setMontoMax(String montoMax) {
		this.montoMax = montoMax;
	}

	/**
	 * Gets the monto increment.
	 *
	 * @return the monto increment
	 */
	public String getMontoIncrement() {
		return montoIncrement;
	}

	/**
	 * Sets the monto increment.
	 *
	 * @param montoIncrement the new monto increment
	 */
	public void setMontoIncrement(String montoIncrement) {
		this.montoIncrement = montoIncrement;
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
	 * @param precioMin the new precio min
	 */
	public void setPrecioMin(String precioMin) {
		this.precioMin = precioMin;
	}

	/**
	 * Gets the precio max.
	 *
	 * @return the precio max
	 */
	public String getPrecioMax() {
		return precioMax;
	}

	/**
	 * Sets the precio max.
	 *
	 * @param precioMax the new precio max
	 */
	public void setPrecioMax(String precioMax) {
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
	 * @param precioIncrement the new precio increment
	 */
	public void setPrecioIncrement(String precioIncrement) {
		this.precioIncrement = precioIncrement;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @param tipoPrecio the new tipo precio
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * Gets the tipo precio adjudicado.
	 *
	 * @return the tipo precio adjudicado
	 */
	public String getTipoPrecioAdjudicado() {
		return tipoPrecioAdjudicado;
	}

	/**
	 * Sets the tipo precio adjudicado.
	 *
	 * @param tipoPrecioAdjudicado the new tipo precio adjudicado
	 */
	public void setTipoPrecioAdjudicado(String tipoPrecioAdjudicado) {
		this.tipoPrecioAdjudicado = tipoPrecioAdjudicado;
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
	 * @param tipoCambio the new tipo cambio
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * Gets the cod instrumento.
	 *
	 * @return the cod instrumento
	 */
	public String getCodInstrumento() {
		return codInstrumento;
	}

	/**
	 * Sets the cod instrumento.
	 *
	 * @param codInstrumento the new cod instrumento
	 */
	public void setCodInstrumento(String codInstrumento) {
		this.codInstrumento = codInstrumento;
	}

	/**
	 * Gets the desc instrumento.
	 *
	 * @return the desc instrumento
	 */
	public String getDescInstrumento() {
		return descInstrumento;
	}

	/**
	 * Sets the desc instrumento.
	 *
	 * @param descInstrumento the new desc instrumento
	 */
	public void setDescInstrumento(String descInstrumento) {
		this.descInstrumento = descInstrumento;
	}

	public String getCodMonedaEspecieDestino() {
		return codMonedaEspecieDestino;
	}

	public void setCodMonedaEspecieDestino(String codMonedaEspecieDestino) {
		this.codMonedaEspecieDestino = codMonedaEspecieDestino;
	}

	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

}
