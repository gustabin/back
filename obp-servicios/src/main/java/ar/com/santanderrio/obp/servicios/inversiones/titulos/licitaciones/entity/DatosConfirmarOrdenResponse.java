/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.math.BigDecimal;

/**
 * The Class DatosConfirmarOrdenResponse.
 *
 * @author juan.pablo.picate
 */
public class DatosConfirmarOrdenResponse {

	/** the tipo cambio. */
	private String tipoCambio;

	/** the cod instrumento. */
	private String codInstrumento;

	/** the desc Instrumento. */
	private String descInstrumento;

	/** The tipo precio. */
	private String tipoPrecio;

	/** the cantidad renovacion. */
	private long cantidadRenovacion;

	/** The cod tramo canal. */
	private long codTramoCanal;

	/** The cod pliego. */
	private long codPliego;

	/** The cod tramo. */
	private long codTramo;

	/** The canal. */
	private int canal;

	/** The subcanal. */
	private int subcanal;

	/** The desc pliego. */
	private String descPliego;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal. */
	private int sucursal;

	/** The tipo cuenta oper. */
	private int tipoCuentaOper;

	/** The cuenta oper. */
	private long cuentaOper;

	/** The cuenta cust. */
	private long cuentaCust;

	/** The cuenta tit. */
	private long cuentaTit;

	/** The nup. */
	private String nup;

	/** The especie. */
	private String especie;

	/** The especie otorgar. */
	private String especieOtorgar;

	/** The relacion canje. */
	private double relacionCanje;

	/** The moneda. */
	private String moneda;

	/** The cantidad. */
	private double cantidad;

	/** The monto. */
	private double monto;

	/** The precio. */
	private double precio;

	/** The correo elect. */
	private String correoElect;

	/** The monto A deb. */
	private BigDecimal montoADeb;

	/** The monto teorico. */
	private double montoTeorico;

	/** The monto comision. */
	private double montoComision;

	/** The monto impuesto. */
	private double montoImpuesto;

	/** The fecha orden. */
	private String fechaOrden;

	/** The fecha deb prov. */
	private String fechaDebProv;

	/** The fecha titulo. */
	private String fechaTitulo;

	/** The fecha adjud. */
	private String fechaAdjud;

	/** The fecha liq. */
	private String fechaLiq;

	/** The fecha cierre. */
	private String fechaCierre;

	/** The simulado. */
	private String simulado;

	/** The num orden. */
	private long numOrden;

	/** The especie otorgar descripcion. */
	private String especieOtorgarDescripcion;

	/** The especie descripcion. */
	private String especieDescripcion;

	/** The estado descripcion. */
	private String estadoDescripcion;

	/** The tramo. */
	private String tramo;

	/** The renovacion. */
	private String renovacion;

	/** The especie renovacion. */
	private String especieRenovacion;

	/** The lugar renovacion. */
	private int lugarRenovacion;

	/** The moneda especie. */
	private String monedaEspecie;

	/** The leyenda legal. */
	private String leyendaLegal;

	/** The leyenda legal can. */
	private String leyendaLegalCan;

	/** The disclaimer. */
	private String disclaimer;

	/** The desvio. */
	private double desvio;

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

	private String codMonedaEspecieDestino;
	
	private Double coeficiente;

	/**
	 * Gets the cod tramo canal.
	 *
	 * @return the codTramoCanal
	 */
	public long getCodTramoCanal() {
		return codTramoCanal;
	}

	/**
	 * Sets the cod tramo canal.
	 *
	 * @param codTramoCanal the codTramoCanal to set
	 */
	public void setCodTramoCanal(long codTramoCanal) {
		this.codTramoCanal = codTramoCanal;
	}

	/**
	 * Gets the cod pliego.
	 *
	 * @return the codPliego
	 */
	public long getCodPliego() {
		return codPliego;
	}

	/**
	 * Sets the cod pliego.
	 *
	 * @param codPliego the codPliego to set
	 */
	public void setCodPliego(long codPliego) {
		this.codPliego = codPliego;
	}

	/**
	 * Gets the cod tramo.
	 *
	 * @return the codTramo
	 */
	public long getCodTramo() {
		return codTramo;
	}

	/**
	 * Sets the cod tramo.
	 *
	 * @param codTramo the codTramo to set
	 */
	public void setCodTramo(long codTramo) {
		this.codTramo = codTramo;
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
	 * Sets the canal.
	 *
	 * @param canal the canal to set
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
	 * @param subcanal the subcanal to set
	 */
	public void setSubcanal(int subcanal) {
		this.subcanal = subcanal;
	}

	/**
	 * Gets the desc pliego.
	 *
	 * @return the descPliego
	 */
	public String getDescPliego() {
		return descPliego;
	}

	/**
	 * Sets the desc pliego.
	 *
	 * @param descPliego the descPliego to set
	 */
	public void setDescPliego(String descPliego) {
		this.descPliego = descPliego;
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
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public int getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the tipo cuenta oper.
	 *
	 * @return the tipoCuentaOper
	 */
	public int getTipoCuentaOper() {
		return tipoCuentaOper;
	}

	/**
	 * Sets the tipo cuenta oper.
	 *
	 * @param tipoCuentaOper the tipoCuentaOper to set
	 */
	public void setTipoCuentaOper(int tipoCuentaOper) {
		this.tipoCuentaOper = tipoCuentaOper;
	}

	/**
	 * Gets the cuenta oper.
	 *
	 * @return the cuentaOper
	 */
	public long getCuentaOper() {
		return cuentaOper;
	}

	/**
	 * Sets the cuenta oper.
	 *
	 * @param cuentaOper the cuentaOper to set
	 */
	public void setCuentaOper(long cuentaOper) {
		this.cuentaOper = cuentaOper;
	}

	/**
	 * Gets the cuenta cust.
	 *
	 * @return the cuentaCust
	 */
	public long getCuentaCust() {
		return cuentaCust;
	}

	/**
	 * Sets the cuenta cust.
	 *
	 * @param cuentaCust the cuentaCust to set
	 */
	public void setCuentaCust(long cuentaCust) {
		this.cuentaCust = cuentaCust;
	}

	/**
	 * Gets the cuenta tit.
	 *
	 * @return the cuentaTit
	 */
	public long getCuentaTit() {
		return cuentaTit;
	}

	/**
	 * Sets the cuenta tit.
	 *
	 * @param cuentaTit the cuentaTit to set
	 */
	public void setCuentaTit(long cuentaTit) {
		this.cuentaTit = cuentaTit;
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
	 * @param nup the nup to set
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
	 * @param especie the especie to set
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the especie otorgar.
	 *
	 * @return the especieOtorgar
	 */
	public String getEspecieOtorgar() {
		return especieOtorgar;
	}

	/**
	 * Sets the especie otorgar.
	 *
	 * @param especieOtorgar the especieOtorgar to set
	 */
	public void setEspecieOtorgar(String especieOtorgar) {
		this.especieOtorgar = especieOtorgar;
	}

	/**
	 * Gets the relacion canje.
	 *
	 * @return the relacionCanje
	 */
	public double getRelacionCanje() {
		return relacionCanje;
	}

	/**
	 * Sets the relacion canje.
	 *
	 * @param relacionCanje the relacionCanje to set
	 */
	public void setRelacionCanje(double relacionCanje) {
		this.relacionCanje = relacionCanje;
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
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
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
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Gets the correo elect.
	 *
	 * @return the correoElect
	 */
	public String getCorreoElect() {
		return correoElect;
	}

	/**
	 * Sets the correo elect.
	 *
	 * @param correoElect the correoElect to set
	 */
	public void setCorreoElect(String correoElect) {
		this.correoElect = correoElect;
	}

	/**
	 * Gets the monto A deb.
	 *
	 * @return the montoADeb
	 */
	public BigDecimal getMontoADeb() {
		return montoADeb;
	}

	/**
	 * Sets the monto A deb.
	 *
	 * @param montoADeb the montoADeb to set
	 */
	public void setMontoADeb(BigDecimal montoADeb) {
		this.montoADeb = montoADeb;
	}

	/**
	 * Gets the monto teorico.
	 *
	 * @return the montoTeorico
	 */
	public double getMontoTeorico() {
		return montoTeorico;
	}

	/**
	 * Sets the monto teorico.
	 *
	 * @param montoTeorico the montoTeorico to set
	 */
	public void setMontoTeorico(double montoTeorico) {
		this.montoTeorico = montoTeorico;
	}

	/**
	 * Gets the monto comision.
	 *
	 * @return the montoComision
	 */
	public double getMontoComision() {
		return montoComision;
	}

	/**
	 * Sets the monto comision.
	 *
	 * @param montoComision the montoComision to set
	 */
	public void setMontoComision(double montoComision) {
		this.montoComision = montoComision;
	}

	/**
	 * Gets the monto impuesto.
	 *
	 * @return the montoImpuesto
	 */
	public double getMontoImpuesto() {
		return montoImpuesto;
	}

	/**
	 * Sets the monto impuesto.
	 *
	 * @param montoImpuesto the montoImpuesto to set
	 */
	public void setMontoImpuesto(double montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fechaOrden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden the fechaOrden to set
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the fecha deb prov.
	 *
	 * @return the fechaDebProv
	 */
	public String getFechaDebProv() {
		return fechaDebProv;
	}

	/**
	 * Sets the fecha deb prov.
	 *
	 * @param fechaDebProv the fechaDebProv to set
	 */
	public void setFechaDebProv(String fechaDebProv) {
		this.fechaDebProv = fechaDebProv;
	}

	/**
	 * Gets the fecha titulo.
	 *
	 * @return the fechaTitulo
	 */
	public String getFechaTitulo() {
		return fechaTitulo;
	}

	/**
	 * Sets the fecha titulo.
	 *
	 * @param fechaTitulo the fechaTitulo to set
	 */
	public void setFechaTitulo(String fechaTitulo) {
		this.fechaTitulo = fechaTitulo;
	}

	/**
	 * Gets the fecha adjud.
	 *
	 * @return the fechaAdjud
	 */
	public String getFechaAdjud() {
		return fechaAdjud;
	}

	/**
	 * Sets the fecha adjud.
	 *
	 * @param fechaAdjud the fechaAdjud to set
	 */
	public void setFechaAdjud(String fechaAdjud) {
		this.fechaAdjud = fechaAdjud;
	}

	/**
	 * Gets the fecha liq.
	 *
	 * @return the fechaLiq
	 */
	public String getFechaLiq() {
		return fechaLiq;
	}

	/**
	 * Sets the fecha liq.
	 *
	 * @param fechaLiq the fechaLiq to set
	 */
	public void setFechaLiq(String fechaLiq) {
		this.fechaLiq = fechaLiq;
	}

	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the simulado.
	 *
	 * @return the simulado
	 */
	public String getSimulado() {
		return simulado;
	}

	/**
	 * Sets the simulado.
	 *
	 * @param simulado the simulado to set
	 */
	public void setSimulado(String simulado) {
		this.simulado = simulado;
	}

	/**
	 * Gets the num orden.
	 *
	 * @return the numOrden
	 */
	public long getNumOrden() {
		return numOrden;
	}

	/**
	 * Sets the num orden.
	 *
	 * @param numOrden the numOrden to set
	 */
	public void setNumOrden(long numOrden) {
		this.numOrden = numOrden;
	}

	/**
	 * Gets the especie otorgar descripcion.
	 *
	 * @return the especieOtorgarDescripcion
	 */
	public String getEspecieOtorgarDescripcion() {
		return especieOtorgarDescripcion;
	}

	/**
	 * Sets the especie otorgar descripcion.
	 *
	 * @param especieOtorgarDescripcion the especieOtorgarDescripcion to set
	 */
	public void setEspecieOtorgarDescripcion(String especieOtorgarDescripcion) {
		this.especieOtorgarDescripcion = especieOtorgarDescripcion;
	}

	/**
	 * Gets the especie descripcion.
	 *
	 * @return the especieDescripcion
	 */
	public String getEspecieDescripcion() {
		return especieDescripcion;
	}

	/**
	 * Sets the especie descripcion.
	 *
	 * @param especieDescripcion the especieDescripcion to set
	 */
	public void setEspecieDescripcion(String especieDescripcion) {
		this.especieDescripcion = especieDescripcion;
	}

	/**
	 * Gets the estado descripcion.
	 *
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	/**
	 * Sets the estado descripcion.
	 *
	 * @param estadoDescripcion the estadoDescripcion to set
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
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
	 * @param tramo the tramo to set
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
	 * @param renovacion the renovacion to set
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the especie renovacion.
	 *
	 * @return the especieRenovacion
	 */
	public String getEspecieRenovacion() {
		return especieRenovacion;
	}

	/**
	 * Sets the especie renovacion.
	 *
	 * @param especieRenovacion the especieRenovacion to set
	 */
	public void setEspecieRenovacion(String especieRenovacion) {
		this.especieRenovacion = especieRenovacion;
	}

	/**
	 * Gets the lugar renovacion.
	 *
	 * @return the lugarRenovacion
	 */
	public int getLugarRenovacion() {
		return lugarRenovacion;
	}

	/**
	 * Sets the lugar renovacion.
	 *
	 * @param lugarRenovacion the lugarRenovacion to set
	 */
	public void setLugarRenovacion(int lugarRenovacion) {
		this.lugarRenovacion = lugarRenovacion;
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
	 * @param monedaEspecie the monedaEspecie to set
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
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
	 * @param leyendaLegal the leyendaLegal to set
	 */
	public void setLeyendaLegal(String leyendaLegal) {
		this.leyendaLegal = leyendaLegal;
	}

	/**
	 * Gets the leyenda legal can.
	 *
	 * @return the leyendaLegalCan
	 */
	public String getLeyendaLegalCan() {
		return leyendaLegalCan;
	}

	/**
	 * Sets the leyenda legal can.
	 *
	 * @param leyendaLegalCan the leyendaLegalCan to set
	 */
	public void setLeyendaLegalCan(String leyendaLegalCan) {
		this.leyendaLegalCan = leyendaLegalCan;
	}

	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	/**
	 * Gets the desvio.
	 *
	 * @return the desvio
	 */
	public double getDesvio() {
		return desvio;
	}

	/**
	 * Sets the desvio.
	 *
	 * @param desvio the desvio to set
	 */
	public void setDesvio(double desvio) {
		this.desvio = desvio;
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
	 * @param id the id to set
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
	 * @param fechaAlta the fechaAlta to set
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
	 * @param usuarioAlta the usuarioAlta to set
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
	 * @param ipAlta the ipAlta to set
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
	 * @param enable the enable to set
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
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * Gets the tipo cambio.
	 *
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * Sets the tipo cambio.
	 *
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * Gets the cod instrumento.
	 *
	 * @return the codInstrumento
	 */
	public String getCodInstrumento() {
		return codInstrumento;
	}

	/**
	 * Sets the cod instrumento.
	 *
	 * @param codInstrumento the codInstrumento to set
	 */
	public void setCodInstrumento(String codInstrumento) {
		this.codInstrumento = codInstrumento;
	}

	/**
	 * Gets the desc instrumento.
	 *
	 * @return the descInstrumento
	 */
	public String getDescInstrumento() {
		return descInstrumento;
	}

	/**
	 * Sets the desc instrumento.
	 *
	 * @param descInstrumento the descInstrumento to set
	 */
	public void setDescInstrumento(String descInstrumento) {
		this.descInstrumento = descInstrumento;
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
	 * @param tipoPrecio the tipoPrecio to set
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * Gets the cantidad renovacion.
	 *
	 * @return the cantidadRenovacion
	 */
	public long getCantidadRenovacion() {
		return cantidadRenovacion;
	}

	/**
	 * Sets the cantidad renovacion.
	 *
	 * @param cantidadRenovacion the cantidadRenovacion to set
	 */
	public void setCantidadRenovacion(long cantidadRenovacion) {
		this.cantidadRenovacion = cantidadRenovacion;
	}

	public String getCodMonedaEspecieDestino() {
		return codMonedaEspecieDestino;
	}

	public void setCodMonedaEspecieDestino(String codMonedaEspecieDestino) {
		this.codMonedaEspecieDestino = codMonedaEspecieDestino;
	}

	public Double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Double coeficiente) {
		this.coeficiente = coeficiente;
	}
}
