package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosDetallesPlazoFijoResponseEntity {

	@JsonProperty("CuentaInversor")
	private String cuentaInversor;

	@JsonProperty("NroPlazoFijo")
	private String nroPlazoFijo;

	@JsonProperty("Participante")
	private String Participante;

	@JsonProperty("FechaConstitucion")
	private String fechaConstitucion;

	@JsonProperty("Plazo")
	private String plazo;

	@JsonProperty("FechaVencimiento")
	private String fechaVencimiento;

	@JsonProperty("CapitalInvertido")
	private String capitalInvertido;

	@JsonProperty("TasaNominalAnual")
	private String tasaNominalAnual;

	@JsonProperty("TasaEfectivaAnual")
	private String tasaEfectivaAnual;

	@JsonProperty("InteresesTotales")
	private double interesesTotales;

	@JsonProperty("InteresesPercibidos")
	private double interesesPercibidos;

	@JsonProperty("InteresesDevengados")
	private double interesesDevengados;

	@JsonProperty("AccionAlVencimiento")
	private String accionAlVencimiento;

	@JsonProperty("CuentaOrigenDestino")
	private String cuentaOrigenDestino;

	@JsonProperty("FrecuenciaCobroInteres")
	private String frecuenciaCobroInteres;

	@JsonProperty("FrecuenciaPrecancelacion")
	private String frecuenciaPrecancelacion;

	@JsonProperty("FechaMinimaCancelacion")
	private String fechaMinimaCancelacion;

	@JsonProperty("FechaSolicitudCancelacion")
	private String fechaSolicitudCancelacion;

	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;

	@JsonProperty("ClausulaAjuste")
	private String clausulaAjuste;

	@JsonProperty("Impuestos")
	private String impuestos;

	@JsonProperty("Custodia")
	private String custodia;

	@JsonProperty("Modalidad")
	private String modalidad;

	@JsonProperty("Comprobante")
	private String comprobante;

	@JsonProperty("Estado")
	private String estado;

	@JsonProperty("Producto")
	private String producto;

	@JsonProperty("Subproducto")
	private String subproducto;

	@JsonProperty("Moneda")
	private String moneda;

	@JsonProperty("TipoCuenta")
	private String tipoCuenta;

	@JsonProperty("Sucursal")
	private String surcursal;
	
	@JsonProperty("TipoPlazoFijo")
	private String tipoPF;
	
	@JsonProperty("CodPlazoFijo")
	private String codPlazoFijo;

	@JsonProperty("NombrePlazoFijo")
	private String nombrePlazoFijo;
	
	
	/**
	 * @return the codPlazoFijo
	 */
	public String getCodPlazoFijo() {
		return codPlazoFijo;
	}

	/**
	 * @param codPlazoFijo the codPlazoFijo to set
	 */
	public void setCodPlazoFijo(String codPlazoFijo) {
		this.codPlazoFijo = codPlazoFijo;
	}

	/**
	 * @return the tipoPF
	 */
	public String getTipoPF() {
		return tipoPF;
	}
	
	public String getNombrePF() {
		return nombrePlazoFijo;
	}
	

	/**
	 * @param tipoPF the tipoPF to set
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * @return the surcursal
	 */
	public String getSurcursal() {
		return surcursal;
	}

	/**
	 * @param surcursal the surcursal to set
	 */
	public void setSurcursal(String surcursal) {
		this.surcursal = surcursal;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the subproducto
	 */
	public String getSubproducto() {
		return subproducto;
	}

	/**
	 * @param subproducto the subproducto to set
	 */
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the cuentaInversor
	 */
	public String getCuentaInversor() {
		return cuentaInversor;
	}

	/**
	 * @param cuentaInversor the cuentaInversor to set
	 */
	public void setCuentaInversor(String cuentaInversor) {
		this.cuentaInversor = cuentaInversor;
	}

	/**
	 * @return the nroPlazoFijo
	 */
	public String getNroPlazoFijo() {
		return nroPlazoFijo;
	}

	/**
	 * @param nroPlazoFijo the nroPlazoFijo to set
	 */
	public void setNroPlazoFijo(String nroPlazoFijo) {
		this.nroPlazoFijo = nroPlazoFijo;
	}

	/**
	 * @return the participante
	 */
	public String getParticipante() {
		return Participante;
	}

	/**
	 * @param participante the participante to set
	 */
	public void setParticipante(String participante) {
		Participante = participante;
	}

	/**
	 * @return the fechaConstitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * @param fechaConstitucion the fechaConstitucion to set
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the capitalInvertido
	 */
	public String getCapitalInvertido() {
		return capitalInvertido;
	}

	/**
	 * @param capitalInvertido the capitalInvertido to set
	 */
	public void setCapitalInvertido(String capitalInvertido) {
		this.capitalInvertido = capitalInvertido;
	}

	/**
	 * @return the tasaNominalAnual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * @param tasaNominalAnual the tasaNominalAnual to set
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
	}

	/**
	 * @return the tasaEfectivaAnual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * @param tasaEfectivaAnual the tasaEfectivaAnual to set
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * @return the interesesTotales
	 */
	public double getInteresesTotales() {
		return interesesTotales;
	}

	/**
	 * @param interesesTotales the interesesTotales to set
	 */
	public void setInteresesTotales(double interesesTotales) {
		this.interesesTotales = interesesTotales;
	}

	/**
	 * @return the interesesPercibidos
	 */
	public double getInteresesPercibidos() {
		return interesesPercibidos;
	}

	/**
	 * @param interesesPercibidos the interesesPercibidos to set
	 */
	public void setInteresesPercibidos(double interesesPercibidos) {
		this.interesesPercibidos = interesesPercibidos;
	}

	/**
	 * @return the interesesDevengados
	 */
	public double getInteresesDevengados() {
		return interesesDevengados;
	}

	/**
	 * @param interesesDevengados the interesesDevengados to set
	 */
	public void setInteresesDevengados(double interesesDevengados) {
		this.interesesDevengados = interesesDevengados;
	}

	/**
	 * @return the accionAlVencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * @param accionAlVencimiento the accionAlVencimiento to set
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * @return the cuentaOrigenDestino
	 */
	public String getCuentaOrigenDestino() {
		return cuentaOrigenDestino;
	}

	/**
	 * @param cuentaOrigenDestino the cuentaOrigenDestino to set
	 */
	public void setCuentaOrigenDestino(String cuentaOrigenDestino) {
		this.cuentaOrigenDestino = cuentaOrigenDestino;
	}

	/**
	 * @return the frecuenciaCobroInteres
	 */
	public String getFrecuenciaCobroInteres() {
		return frecuenciaCobroInteres;
	}

	/**
	 * @param frecuenciaCobroInteres the frecuenciaCobroInteres to set
	 */
	public void setFrecuenciaCobroInteres(String frecuenciaCobroInteres) {
		this.frecuenciaCobroInteres = frecuenciaCobroInteres;
	}

	/**
	 * @return the frecuenciaPrecancelacion
	 */
	public String getFrecuenciaPrecancelacion() {
		return frecuenciaPrecancelacion;
	}

	/**
	 * @param frecuenciaPrecancelacion the frecuenciaPrecancelacion to set
	 */
	public void setFrecuenciaPrecancelacion(String frecuenciaPrecancelacion) {
		this.frecuenciaPrecancelacion = frecuenciaPrecancelacion;
	}

	/**
	 * @return the fechaMinimaCancelacion
	 */
	public String getFechaMinimaCancelacion() {
		return fechaMinimaCancelacion;
	}

	/**
	 * @param fechaMinimaCancelacion the fechaMinimaCancelacion to set
	 */
	public void setFechaMinimaCancelacion(String fechaMinimaCancelacion) {
		this.fechaMinimaCancelacion = fechaMinimaCancelacion;
	}

	/**
	 * @return the fechaSolicitudCancelacion
	 */
	public String getFechaSolicitudCancelacion() {
		return fechaSolicitudCancelacion;
	}

	/**
	 * @param fechaSolicitudCancelacion the fechaSolicitudCancelacion to set
	 */
	public void setFechaSolicitudCancelacion(String fechaSolicitudCancelacion) {
		this.fechaSolicitudCancelacion = fechaSolicitudCancelacion;
	}

	/**
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * @param fechaLiquidacion the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * @return the clausulaAjuste
	 */
	public String getClausulaAjuste() {
		return clausulaAjuste;
	}

	/**
	 * @param clausulaAjuste the clausulaAjuste to set
	 */
	public void setClausulaAjuste(String clausulaAjuste) {
		this.clausulaAjuste = clausulaAjuste;
	}

	/**
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * @param impuestos the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * @return the custodia
	 */
	public String getCustodia() {
		return custodia;
	}

	/**
	 * @param custodia the custodia to set
	 */
	public void setCustodia(String custodia) {
		this.custodia = custodia;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
