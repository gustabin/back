/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.List;

/**
 * The Class DatosObtenerCanalTramoResponse.
 */
public class DatosObtenerCanalTramoResponse {

	/** The tipo pliego. */
	private TipoPliego tipoPliego;

	/** The codigo tramo canal. */
	private long codigoTramoCanal;

	/** The codigo pliego. */
	private long codigoPliego;

	/** The codigo tramo. */
	private long codigoTramo;

	/** The descripcion pliego. */
	private String descripcionPliego;

	/** The codigo especie. */
	private String codigoEspecie;

	/** The descripcion especie. */
	private String descripcionEspecie;

	/** The valida derechos. */
	private String validaDerechos;

	/** The codigo especie der otorg. */
	private String codigoEspecieDerOtorg;

	/** The descripcion especie derechos. */
	private String descripcionEspecieDerechos;

	/** The relacion derechos canje. */
	private String relacionDerechosCanje;

	/** The cuentas titulos. */
	private List<CanalTramoCtaTitulo> cuentasTitulos;

	/** The canal. */
	private int canal;

	/** The subcanal. */
	private int subcanal;

	/** The tramo competitivo. */
	private String tramoCompetitivo;

	/** The tipo persona. */
	private String tipoPersona;

	/** The residente. */
	private String residente;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The precio rescate. */
	private double precioRescate;

	/** The tipo cambio. */
	private double tipoCambio;

	/** The precio. */
	private double precio;

	/** The moneda. */
	private String moneda;

	/** The descripcion moneda. */
	private String descripcionMoneda;

	/** The tipo oferta. */
	private String tipoOferta;

	/** The nominal min. */
	private double nominalMin;

	/** The nominal max. */
	private double nominalMax;

	/** The nominal increment. */
	private double nominalIncrement;

	/** The monto min. */
	private double montoMin;

	/** The monto max. */
	private double montoMax;

	/** The monto increment. */
	private double montoIncrement;

	/** The tipo precio. */
	private String tipoPrecio;

	/** The precio min. */
	private String precioMin;

	/** The precio max. */
	private double precioMax;

	/** The precio increment. */
	private String precioIncrement;

	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;

	/** The fecha liquidacion titulos. */
	private String fechaLiquidacionTitulos;

	/** The fecha liquidacion valores. */
	private String fechaLiquidacionValores;

	/** The fecha hora inicio. */
	private String fechaHoraInicio;

	/** The fecha hora cierre. */
	private String fechaHoraCierre;

	/** The fecha hora reversa. */
	private String fechaHoraReversa;

	/** The correo electronico. */
	private String correoElectronico;

	/** The nombre archivo pdf. */
	private String nombreArchivoPdf;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The leyenda legal canal. */
	private String leyendaLegalCanal;

	/** The cod captfondos. */
	private String codCaptfondos;

	/** The moneda especie. */
	private String monedaEspecie;

	/** The renovacion. */
	private String renovacion;

	/** The id. */
	private String id;

	/** The fecha alta. */
	private String fechaAlta;

	/** The usuario alta. */
	private String usuarioAlta;

	/** The ip alta. */
	private String ipAlta;

	/** The enable. */
	private boolean enable;

	/** The is new. */
	private boolean isNew;

	/** The desc tipo instrumento. */
	private String descTipoInstrumento;

	/** The cod tipo instrumento. */
	private String codTipoInstrumento;
	
	private String tipoEjecucion;

	/**
	 * Gets the tipo pliego.
	 *
	 * @return the tipoPliego
	 */
	public TipoPliego getTipoPliego() {
		return tipoPliego;
	}

	/**
	 * Sets the tipo pliego.
	 *
	 * @param tipoPliego
	 *            the tipoPliego to set
	 */
	public void setTipoPliego(TipoPliego tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

	/**
	 * Gets the codigo tramo canal.
	 *
	 * @return the codigoTramoCanal
	 */
	public long getCodigoTramoCanal() {
		return codigoTramoCanal;
	}

	/**
	 * Sets the codigo tramo canal.
	 *
	 * @param codigoTramoCanal
	 *            the codigoTramoCanal to set
	 */
	public void setCodigoTramoCanal(long codigoTramoCanal) {
		this.codigoTramoCanal = codigoTramoCanal;
	}

	/**
	 * Gets the codigo pliego.
	 *
	 * @return the codigoPliego
	 */
	public long getCodigoPliego() {
		return codigoPliego;
	}

	/**
	 * Sets the codigo pliego.
	 *
	 * @param codigoPliego
	 *            the codigoPliego to set
	 */
	public void setCodigoPliego(long codigoPliego) {
		this.codigoPliego = codigoPliego;
	}

	/**
	 * Gets the codigo tramo.
	 *
	 * @return the codigoTramo
	 */
	public long getCodigoTramo() {
		return codigoTramo;
	}

	/**
	 * Sets the codigo tramo.
	 *
	 * @param codigoTramo
	 *            the codigoTramo to set
	 */
	public void setCodigoTramo(long codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	/**
	 * Gets the descripcion pliego.
	 *
	 * @return the descripcionPliego
	 */
	public String getDescripcionPliego() {
		return descripcionPliego;
	}

	/**
	 * Sets the descripcion pliego.
	 *
	 * @param descripcionPliego
	 *            the descripcionPliego to set
	 */
	public void setDescripcionPliego(String descripcionPliego) {
		this.descripcionPliego = descripcionPliego;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigoEspecie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcionEspecie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the descripcionEspecie to set
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the valida derechos.
	 *
	 * @return the validaDerechos
	 */
	public String getValidaDerechos() {
		return validaDerechos;
	}

	/**
	 * Sets the valida derechos.
	 *
	 * @param validaDerechos
	 *            the validaDerechos to set
	 */
	public void setValidaDerechos(String validaDerechos) {
		this.validaDerechos = validaDerechos;
	}

	/**
	 * Gets the codigo especie der otorg.
	 *
	 * @return the codigoEspecieDerOtorg
	 */
	public String getCodigoEspecieDerOtorg() {
		return codigoEspecieDerOtorg;
	}

	/**
	 * Sets the codigo especie der otorg.
	 *
	 * @param codigoEspecieDerOtorg
	 *            the codigoEspecieDerOtorg to set
	 */
	public void setCodigoEspecieDerOtorg(String codigoEspecieDerOtorg) {
		this.codigoEspecieDerOtorg = codigoEspecieDerOtorg;
	}

	/**
	 * Gets the descripcion especie derechos.
	 *
	 * @return the descripcionEspecieDerechos
	 */
	public String getDescripcionEspecieDerechos() {
		return descripcionEspecieDerechos;
	}

	/**
	 * Sets the descripcion especie derechos.
	 *
	 * @param descripcionEspecieDerechos
	 *            the descripcionEspecieDerechos to set
	 */
	public void setDescripcionEspecieDerechos(String descripcionEspecieDerechos) {
		this.descripcionEspecieDerechos = descripcionEspecieDerechos;
	}

	/**
	 * Gets the relacion derechos canje.
	 *
	 * @return the relacionDerechosCanje
	 */
	public String getRelacionDerechosCanje() {
		return relacionDerechosCanje;
	}

	/**
	 * Sets the relacion derechos canje.
	 *
	 * @param relacionDerechosCanje
	 *            the relacionDerechosCanje to set
	 */
	public void setRelacionDerechosCanje(String relacionDerechosCanje) {
		this.relacionDerechosCanje = relacionDerechosCanje;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public int getCanal() {
		return canal;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CanalTramoCtaTitulo> getCuentasTitulo() {
		return cuentasTitulos;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CanalTramoCtaTitulo> cuentasTitulo) {
		this.cuentasTitulos = cuentasTitulo;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(int canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public int getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the subcanal to set
	 */
	public void setSubcanal(int subcanal) {
		this.subcanal = subcanal;
	}

	/**
	 * Gets the tramo competitivo.
	 *
	 * @return the tramoCompetitivo
	 */
	public String getTramoCompetitivo() {
		return tramoCompetitivo;
	}

	/**
	 * Sets the tramo competitivo.
	 *
	 * @param tramoCompetitivo
	 *            the tramoCompetitivo to set
	 */
	public void setTramoCompetitivo(String tramoCompetitivo) {
		this.tramoCompetitivo = tramoCompetitivo;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the residente.
	 *
	 * @return the residente
	 */
	public String getResidente() {
		return residente;
	}

	/**
	 * Sets the residente.
	 *
	 * @param residente
	 *            the residente to set
	 */
	public void setResidente(String residente) {
		this.residente = residente;
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
	 * Gets the precio rescate.
	 *
	 * @return the precioRescate
	 */
	public double getPrecioRescate() {
		return precioRescate;
	}

	/**
	 * Sets the precio rescate.
	 *
	 * @param precioRescate
	 *            the precioRescate to set
	 */
	public void setPrecioRescate(double precioRescate) {
		this.precioRescate = precioRescate;
	}

	/**
	 * Gets the tipo cambio.
	 *
	 * @return the tipoCambio
	 */
	public double getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * Sets the tipo cambio.
	 *
	 * @param tipoCambio
	 *            the tipoCambio to set
	 */
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
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
	 *            the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
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
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcionMoneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda
	 *            the descripcionMoneda to set
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	/**
	 * Gets the tipo oferta.
	 *
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * Sets the tipo oferta.
	 *
	 * @param tipoOferta
	 *            the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * Gets the nominal min.
	 *
	 * @return the nominalMin
	 */
	public double getNominalMin() {
		return nominalMin;
	}

	/**
	 * Sets the nominal min.
	 *
	 * @param nominalMin
	 *            the nominalMin to set
	 */
	public void setNominalMin(double nominalMin) {
		this.nominalMin = nominalMin;
	}

	/**
	 * Gets the nominal max.
	 *
	 * @return the nominalMax
	 */
	public double getNominalMax() {
		return nominalMax;
	}

	/**
	 * Sets the nominal max.
	 *
	 * @param nominalMax
	 *            the nominalMax to set
	 */
	public void setNominalMax(double nominalMax) {
		this.nominalMax = nominalMax;
	}

	/**
	 * Gets the nominal increment.
	 *
	 * @return the nominalIncrement
	 */
	public double getNominalIncrement() {
		return nominalIncrement;
	}

	/**
	 * Sets the nominal increment.
	 *
	 * @param nominalIncrement
	 *            the nominalIncrement to set
	 */
	public void setNominalIncrement(double nominalIncrement) {
		this.nominalIncrement = nominalIncrement;
	}

	/**
	 * Gets the monto min.
	 *
	 * @return the montoMin
	 */
	public double getMontoMin() {
		return montoMin;
	}

	/**
	 * Sets the monto min.
	 *
	 * @param montoMin
	 *            the montoMin to set
	 */
	public void setMontoMin(double montoMin) {
		this.montoMin = montoMin;
	}

	/**
	 * Gets the monto max.
	 *
	 * @return the montoMax
	 */
	public double getMontoMax() {
		return montoMax;
	}

	/**
	 * Sets the monto max.
	 *
	 * @param montoMax
	 *            the montoMax to set
	 */
	public void setMontoMax(double montoMax) {
		this.montoMax = montoMax;
	}

	/**
	 * Gets the monto increment.
	 *
	 * @return the montoIncrement
	 */
	public double getMontoIncrement() {
		return montoIncrement;
	}

	/**
	 * Sets the monto increment.
	 *
	 * @param montoIncrement
	 *            the montoIncrement to set
	 */
	public void setMontoIncrement(double montoIncrement) {
		this.montoIncrement = montoIncrement;
	}

	/**
	 * Gets the tipo precio.
	 *
	 * @return the tipoPrecio
	 */
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	/**
	 * Sets the tipo precio.
	 *
	 * @param tipoPrecio
	 *            the tipoPrecio to set
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * Gets the precio min.
	 *
	 * @return the precioMin
	 */
	public String getPrecioMin() {
		return precioMin;
	}

	/**
	 * Sets the precio min.
	 *
	 * @param precioMin
	 *            the precioMin to set
	 */
	public void setPrecioMin(String precioMin) {
		this.precioMin = precioMin;
	}

	/**
	 * Gets the precio max.
	 *
	 * @return the precioMax
	 */
	public double getPrecioMax() {
		return precioMax;
	}

	/**
	 * Sets the precio max.
	 *
	 * @param precioMax
	 *            the precioMax to set
	 */
	public void setPrecioMax(double precioMax) {
		this.precioMax = precioMax;
	}

	/**
	 * Gets the precio increment.
	 *
	 * @return the precioIncrement
	 */
	public String getPrecioIncrement() {
		return precioIncrement;
	}

	/**
	 * Sets the precio increment.
	 *
	 * @param precioIncrement
	 *            the precioIncrement to set
	 */
	public void setPrecioIncrement(String precioIncrement) {
		this.precioIncrement = precioIncrement;
	}

	/**
	 * Gets the fecha adjudicacion.
	 *
	 * @return the fechaAdjudicacion
	 */
	public String getFechaAdjudicacion() {
		return fechaAdjudicacion;
	}

	/**
	 * Sets the fecha adjudicacion.
	 *
	 * @param fechaAdjudicacion
	 *            the fechaAdjudicacion to set
	 */
	public void setFechaAdjudicacion(String fechaAdjudicacion) {
		this.fechaAdjudicacion = fechaAdjudicacion;
	}

	/**
	 * Gets the fecha liquidacion titulos.
	 *
	 * @return the fechaLiquidacionTitulos
	 */
	public String getFechaLiquidacionTitulos() {
		return fechaLiquidacionTitulos;
	}

	/**
	 * Sets the fecha liquidacion titulos.
	 *
	 * @param fechaLiquidacionTitulos
	 *            the fechaLiquidacionTitulos to set
	 */
	public void setFechaLiquidacionTitulos(String fechaLiquidacionTitulos) {
		this.fechaLiquidacionTitulos = fechaLiquidacionTitulos;
	}

	/**
	 * Gets the fecha liquidacion valores.
	 *
	 * @return the fechaLiquidacionValores
	 */
	public String getFechaLiquidacionValores() {
		return fechaLiquidacionValores;
	}

	/**
	 * Sets the fecha liquidacion valores.
	 *
	 * @param fechaLiquidacionValores
	 *            the fechaLiquidacionValores to set
	 */
	public void setFechaLiquidacionValores(String fechaLiquidacionValores) {
		this.fechaLiquidacionValores = fechaLiquidacionValores;
	}

	/**
	 * Gets the fecha hora inicio.
	 *
	 * @return the fechaHoraInicio
	 */
	public String getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	/**
	 * Sets the fecha hora inicio.
	 *
	 * @param fechaHoraInicio
	 *            the fechaHoraInicio to set
	 */
	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	/**
	 * Gets the fecha hora cierre.
	 *
	 * @return the fechaHoraCierre
	 */
	public String getFechaHoraCierre() {
		return fechaHoraCierre;
	}

	/**
	 * Sets the fecha hora cierre.
	 *
	 * @param fechaHoraCierre
	 *            the fechaHoraCierre to set
	 */
	public void setFechaHoraCierre(String fechaHoraCierre) {
		this.fechaHoraCierre = fechaHoraCierre;
	}

	/**
	 * Gets the fecha hora reversa.
	 *
	 * @return the fechaHoraReversa
	 */
	public String getFechaHoraReversa() {
		return fechaHoraReversa;
	}

	/**
	 * Sets the fecha hora reversa.
	 *
	 * @param fechaHoraReversa
	 *            the fechaHoraReversa to set
	 */
	public void setFechaHoraReversa(String fechaHoraReversa) {
		this.fechaHoraReversa = fechaHoraReversa;
	}

	/**
	 * Gets the correo electronico.
	 *
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Sets the correo electronico.
	 *
	 * @param correoElectronico
	 *            the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Gets the nombre archivo pdf.
	 *
	 * @return the nombreArchivoPdf
	 */
	public String getNombreArchivoPdf() {
		return nombreArchivoPdf;
	}

	/**
	 * Sets the nombre archivo pdf.
	 *
	 * @param nombreArchivoPdf
	 *            the nombreArchivoPdf to set
	 */
	public void setNombreArchivoPdf(String nombreArchivoPdf) {
		this.nombreArchivoPdf = nombreArchivoPdf;
	}

	/**
	 * Gets the leyenda legal.
	 *
	 * @return the leyendaLegal
	 */
	public String getLeyendaLegal() {
		return leyendaLegal;
	}

	/**
	 * Sets the leyenda legal.
	 *
	 * @param leyendaLegal
	 *            the leyendaLegal to set
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the leyenda legal canal.
	 *
	 * @return the leyendaLegalCanal
	 */
	public String getLeyendaLegalCanal() {
		return leyendaLegalCanal;
	}

	/**
	 * Sets the leyenda legal canal.
	 *
	 * @param leyendaLegalCanal
	 *            the leyendaLegalCanal to set
	 */
	public void setLeyendaLegalCanal(String leyendaLegalCanal) {
		this.leyendaLegalCanal = leyendaLegalCanal;
	}

	/**
	 * Gets the cod captfondos.
	 *
	 * @return the codCaptfondos
	 */
	public String getCodCaptfondos() {
		return codCaptfondos;
	}

	/**
	 * Sets the cod captfondos.
	 *
	 * @param codCaptfondos
	 *            the codCaptfondos to set
	 */
	public void setCodCaptfondos(String codCaptfondos) {
		this.codCaptfondos = codCaptfondos;
	}

	/**
	 * Gets the moneda especie.
	 *
	 * @return the monedaEspecie
	 */
	public String getMonedaEspecie() {
		return monedaEspecie;
	}

	/**
	 * Sets the moneda especie.
	 *
	 * @param monedaEspecie
	 *            the monedaEspecie to set
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
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
	 *            the renovacion to set
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the usuario alta.
	 *
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * Sets the usuario alta.
	 *
	 * @param usuarioAlta
	 *            the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * Gets the ip alta.
	 *
	 * @return the ipAlta
	 */
	public String getIpAlta() {
		return ipAlta;
	}

	/**
	 * Sets the ip alta.
	 *
	 * @param ipAlta
	 *            the ipAlta to set
	 */
	public void setIpAlta(String ipAlta) {
		this.ipAlta = ipAlta;
	}

	/**
	 * Checks if is enable.
	 *
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Checks if is new.
	 *
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * Sets the new.
	 *
	 * @param isNew
	 *            the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * Gets the desc tipo instrumento.
	 *
	 * @return the desc tipo instrumento
	 */
	public String getDescTipoInstrumento() {
		return descTipoInstrumento;
	}

	/**
	 * Gets the cod tipo instrumento.
	 *
	 * @return the cod tipo instrumento
	 */
	public String getCodTipoInstrumento() {
		return codTipoInstrumento;
	}

	/**
	 * Sets the desc tipo instrumento.
	 *
	 * @param descTipoInstrumento
	 *            the new desc tipo instrumento
	 */
	public void setDescTipoInstrumento(String descTipoInstrumento) {
		this.descTipoInstrumento = descTipoInstrumento;
	}

	/**
	 * Sets the cod tipo instrumento.
	 *
	 * @param codTipoInstrumento
	 *            the new cod tipo instrumento
	 */
	public void setCodTipoInstrumento(String codTipoInstrumento) {
		this.codTipoInstrumento = codTipoInstrumento;
	}

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}

	// public List<TramoMoneda> getTramosMonedas() {
	// return tramosMonedas;
	// }
	//
	// public void setTramosMonedas(List<TramoMoneda> tramosMonedas) {
	// this.tramosMonedas = tramosMonedas;
	// }

}
