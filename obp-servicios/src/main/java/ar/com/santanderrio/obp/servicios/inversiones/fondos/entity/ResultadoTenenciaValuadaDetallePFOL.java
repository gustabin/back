/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase resultado del servicio ObtenerTenenciaValuadaDetallePFOnline.
 *
 * @author marcelo.ruiz
 */
public class ResultadoTenenciaValuadaDetallePFOL {

	/** The accion vencimiento. */
	@JsonProperty("AccionVencimiento")
	private String accionVencimiento;

	/** The ajuste capital acumulado. */
	@JsonProperty("AjusteCapitalAcumulado")
	private BigDecimal ajusteCapitalAcumulado;

	/** The canal. */
	@JsonProperty("Canal")
	private BigDecimal canal;

	/** The capital inicial. */
	@JsonProperty("CapitalInicial")
	private BigDecimal capitalInicial;

	/** The capital inicio expresado indice. */
	@JsonProperty("CapitalInicioExpresadoIndice")
	private BigDecimal capitalInicioExpresadoIndice;

	/** The centro cuenta inversion. */
	@JsonProperty("CentroCuentaInversion")
	private String centroCuentaInversion;

	/** The cod producto. */
	@JsonProperty("CodProducto")
	private String codProducto;

	/** The cod sub producto. */
	@JsonProperty("CodSubProducto")
	private String codSubProducto;

	/** The comprobante. */
	@JsonProperty("Comprobante")
	private String comprobante;

	/** The cuenta accion vencimiento. */
	@JsonProperty("CuentaAccionVencimiento")
	private String cuentaAccionVencimiento;

	/** The cuenta debito. */
	@JsonProperty("CuentaDebito")
	private String cuentaDebito;

	/** The descripcion impuesto 1. */
	@JsonProperty("DescripcionImpuesto1")
	private String descripcionImpuesto1;

	/** The descripcion impuesto 2. */
	@JsonProperty("DescripcionImpuesto2")
	private String descripcionImpuesto2;

	/** The descripcion impuesto 3. */
	@JsonProperty("DescripcionImpuesto3")
	private String descripcionImpuesto3;

	/** The descripcion impuesto 4. */
	@JsonProperty("DescripcionImpuesto4")
	private String descripcionImpuesto4;

	/** The descripcion impuesto 5. */
	@JsonProperty("DescripcionImpuesto5")
	private String descripcionImpuesto5;

	/** The descripcion tipo plazo fijo. */
	@JsonProperty("DescripcionTipoPlazoFijo")
	private String descripcionTipoPlazoFijo;

	/** The devengado. */
	@JsonProperty("Devengado")
	private Long devengado;

	/** The entidad cuenta inversion. */
	@JsonProperty("EntidadCuentaInversion")
	private String entidadCuentaInversion;

	/** The estado. */
	@JsonProperty("Estado")
	private String estado;

	/** The fecha constitucion. */
	@JsonProperty("FechaConstitucion")
	private String fechaConstitucion;

	/** The fecha devengado. */
	@JsonProperty("FechaDevengado")
	private String fechaDevengado;

	/** The fecha vencimiento. */
	@JsonProperty("FechaVencimiento")
	private String fechaVencimiento;

	/** The frecuencia cobro intereses. */
	@JsonProperty("FrecuenciaCobroIntereses")
	private BigDecimal frecuenciaCobroIntereses;

	/** The GUIDError. */
	@JsonProperty("GUIDError")
	private String guidError;

	/** The hora constitucion. */
	@JsonProperty("HoraConstitucion")
	private String horaConstitucion;

	/** The impuesto CER. */
	@JsonProperty("ImpuestoCER")
	private BigDecimal impuestoCER;

	/** The int men var deveng DIVA. */
	@JsonProperty("IntMenVarDevengDIVA")
	private BigDecimal intMenVarDevengDIVA;

	/** The int vard deven DIVA. */
	@JsonProperty("IntVardDevenDIVA")
	private BigDecimal intVardDevenDIVA;

	/** The interes. */
	@JsonProperty("Interes")
	private BigDecimal interes;

	/** The interes dev tasa fija acum. */
	@JsonProperty("InteresDevTasaFijaAcum")
	private BigDecimal interesDevTasaFijaAcum;

	/** The interes dev X cap aju acum. */
	@JsonProperty("InteresDevXCapAjuAcum")
	private BigDecimal interesDevXCapAjuAcum;

	/** The interes devengado acum. */
	@JsonProperty("InteresDevengadoAcum")
	private BigDecimal interesDevengadoAcum;

	/** The momento de cobro 1. */
	@JsonProperty("MomentoDeCobro1")
	private String momentoDeCobro1;

	/** The momento de cobro 2. */
	@JsonProperty("MomentoDeCobro2")
	private String momentoDeCobro2;

	/** The momento de cobro 3. */
	@JsonProperty("MomentoDeCobro3")
	private String momentoDeCobro3;

	/** The momento de cobro 4. */
	@JsonProperty("MomentoDeCobro4")
	private String momentoDeCobro4;

	/** The momento de cobro 5. */
	@JsonProperty("MomentoDeCobro5")
	private String momentoDeCobro5;

	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The monto cobrar. */
	@JsonProperty("MontoCobrar")
	private BigDecimal montoCobrar;

	/** The monto moneda extranjera 1. */
	@JsonProperty("MontoMonedaExtranjera1")
	private BigDecimal montoMonedaExtranjera1;

	/** The monto moneda extranjera 2. */
	@JsonProperty("MontoMonedaExtranjera2")
	private BigDecimal montoMonedaExtranjera2;

	/** The monto moneda extranjera 3. */
	@JsonProperty("MontoMonedaExtranjera3")
	private BigDecimal montoMonedaExtranjera3;

	/** The monto moneda extranjera 4. */
	@JsonProperty("MontoMonedaExtranjera4")
	private BigDecimal montoMonedaExtranjera4;

	/** The monto moneda extranjera 5. */
	@JsonProperty("MontoMonedaExtranjera5")
	private BigDecimal montoMonedaExtranjera5;

	/** The monto moneda local 1. */
	@JsonProperty("MontoMonedaLocal1")
	private BigDecimal montoMonedaLocal1;

	/** The monto moneda local 2. */
	@JsonProperty("MontoMonedaLocal2")
	private BigDecimal montoMonedaLocal2;

	/** The monto moneda local 3. */
	@JsonProperty("MontoMonedaLocal3")
	private BigDecimal montoMonedaLocal3;

	/** The monto moneda local 4. */
	@JsonProperty("MontoMonedaLocal4")
	private BigDecimal montoMonedaLocal4;

	/** The monto moneda local 5. */
	@JsonProperty("MontoMonedaLocal5")
	private BigDecimal montoMonedaLocal5;

	/** The numero certificado. */
	@JsonProperty("NumeroCertificado")
	private String numeroCertificado;

	/** The numero cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;

	/** The numero cuenta inversion. */
	@JsonProperty("NumeroCuentaInversion")
	private String numeroCuentaInversion;

	/** The numero orden. */
	@JsonProperty("NumeroOrden")
	private String numeroOrden;

	/** The numero secuencia. */
	@JsonProperty("NumeroSecuencia")
	private String numeroSecuencia;

	/** The plazo minimo precancelacion. */
	@JsonProperty("PlazoMinimoPrecancelacion")
	private BigDecimal plazoMinimoPrecancelacion;

	/** The plazo vigencia. */
	@JsonProperty("PlazoVigencia")
	private BigDecimal plazoVigencia;

	/** The porcentaje penalizacion. */
	@JsonProperty("PorcentajePenalizacion")
	private BigDecimal porcentajePenalizacion;

	/** The resultado bruto. */
	@JsonProperty("ResultadoBruto")
	private String resultadoBruto;

	/** The resultado bruto reexp. */
	@JsonProperty("ResultadoBrutoReexp")
	private String resultadoBrutoReexp;

	/** The retribuciones variables acumuladas. */
	@JsonProperty("RetribucionesVariablesAcumuladas")
	private BigDecimal retribucionesVariablesAcumuladas;

	/** The sucursal accion vencimiento. */
	@JsonProperty("SucursalAccionVencimiento")
	private BigDecimal sucursalAccionVencimiento;

	/** The sucursal cuenta. */
	@JsonProperty("SucursalCuenta")
	private String sucursalCuenta;

	/** The sucursal debito. */
	@JsonProperty("SucursalDebito")
	private BigDecimal sucursalDebito;

	/** The sucursal radicacion. */
	@JsonProperty("SucursalRadicacion")
	private BigDecimal sucursalRadicacion;

	/** The tna. */
	@JsonProperty("TNA")
	private BigDecimal tna;

	/** The tenencia valuada. */
	@JsonProperty("TenenciaValuada")
	private String tenenciaValuada;

	/** The tenencia valuada reexp. */
	@JsonProperty("TenenciaValuadaReexp")
	private String tenenciaValuadaReexp;

	/** The tenencia cotizacion CERUVA. */
	@JsonProperty("CotizacionCERUVA")
	private String cotizacionCERUVA;

	/**
	 * Gets the accion vencimiento.
	 *
	 * @return the accion vencimiento
	 */
	public String getAccionVencimiento() {
		return accionVencimiento;
	}

	/**
	 * Sets the accion vencimiento.
	 *
	 * @param accionVencimiento
	 *            the new accion vencimiento
	 */
	public void setAccionVencimiento(String accionVencimiento) {
		this.accionVencimiento = accionVencimiento;
	}

	/**
	 * Gets the ajuste capital acumulado.
	 *
	 * @return the ajuste capital acumulado
	 */
	public BigDecimal getAjusteCapitalAcumulado() {
		return ajusteCapitalAcumulado;
	}

	/**
	 * Sets the ajuste capital acumulado.
	 *
	 * @param ajusteCapitalAcumulado
	 *            the new ajuste capital acumulado
	 */
	public void setAjusteCapitalAcumulado(BigDecimal ajusteCapitalAcumulado) {
		this.ajusteCapitalAcumulado = ajusteCapitalAcumulado;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public BigDecimal getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(BigDecimal canal) {
		this.canal = canal;
	}

	/**
	 * Gets the capital inicial.
	 *
	 * @return the capital inicial
	 */
	public BigDecimal getCapitalInicial() {
		return capitalInicial;
	}

	/**
	 * Sets the capital inicial.
	 *
	 * @param capitalInicial
	 *            the new capital inicial
	 */
	public void setCapitalInicial(BigDecimal capitalInicial) {
		this.capitalInicial = capitalInicial;
	}

	/**
	 * Gets the capital inicio expresado indice.
	 *
	 * @return the capital inicio expresado indice
	 */
	public BigDecimal getCapitalInicioExpresadoIndice() {
		return capitalInicioExpresadoIndice;
	}

	/**
	 * Sets the capital inicio expresado indice.
	 *
	 * @param capitalInicioExpresadoIndice
	 *            the new capital inicio expresado indice
	 */
	public void setCapitalInicioExpresadoIndice(BigDecimal capitalInicioExpresadoIndice) {
		this.capitalInicioExpresadoIndice = capitalInicioExpresadoIndice;
	}

	/**
	 * Gets the centro cuenta inversion.
	 *
	 * @return the centro cuenta inversion
	 */
	public String getCentroCuentaInversion() {
		return centroCuentaInversion;
	}

	/**
	 * Sets the centro cuenta inversion.
	 *
	 * @param centroCuentaInversion
	 *            the new centro cuenta inversion
	 */
	public void setCentroCuentaInversion(String centroCuentaInversion) {
		this.centroCuentaInversion = centroCuentaInversion;
	}

	/**
	 * Gets the cod producto.
	 *
	 * @return the cod producto
	 */
	public String getCodProducto() {
		return codProducto;
	}

	/**
	 * Sets the cod producto.
	 *
	 * @param codProducto
	 *            the new cod producto
	 */
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	/**
	 * Gets the cod sub producto.
	 *
	 * @return the cod sub producto
	 */
	public String getCodSubProducto() {
		return codSubProducto;
	}

	/**
	 * Sets the cod sub producto.
	 *
	 * @param codSubProducto
	 *            the new cod sub producto
	 */
	public void setCodSubProducto(String codSubProducto) {
		this.codSubProducto = codSubProducto;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the cuenta accion vencimiento.
	 *
	 * @return the cuenta accion vencimiento
	 */
	public String getCuentaAccionVencimiento() {
		return cuentaAccionVencimiento;
	}

	/**
	 * Sets the cuenta accion vencimiento.
	 *
	 * @param cuentaAccionVencimiento
	 *            the new cuenta accion vencimiento
	 */
	public void setCuentaAccionVencimiento(String cuentaAccionVencimiento) {
		this.cuentaAccionVencimiento = cuentaAccionVencimiento;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the descripcion impuesto 1.
	 *
	 * @return the descripcion impuesto 1
	 */
	public String getDescripcionImpuesto1() {
		return descripcionImpuesto1;
	}

	/**
	 * Sets the descripcion impuesto 1.
	 *
	 * @param descripcionImpuesto1
	 *            the new descripcion impuesto 1
	 */
	public void setDescripcionImpuesto1(String descripcionImpuesto1) {
		this.descripcionImpuesto1 = descripcionImpuesto1;
	}

	/**
	 * Gets the descripcion impuesto 2.
	 *
	 * @return the descripcion impuesto 2
	 */
	public String getDescripcionImpuesto2() {
		return descripcionImpuesto2;
	}

	/**
	 * Sets the descripcion impuesto 2.
	 *
	 * @param descripcionImpuesto2
	 *            the new descripcion impuesto 2
	 */
	public void setDescripcionImpuesto2(String descripcionImpuesto2) {
		this.descripcionImpuesto2 = descripcionImpuesto2;
	}

	/**
	 * Gets the descripcion impuesto 3.
	 *
	 * @return the descripcion impuesto 3
	 */
	public String getDescripcionImpuesto3() {
		return descripcionImpuesto3;
	}

	/**
	 * Sets the descripcion impuesto 3.
	 *
	 * @param descripcionImpuesto3
	 *            the new descripcion impuesto 3
	 */
	public void setDescripcionImpuesto3(String descripcionImpuesto3) {
		this.descripcionImpuesto3 = descripcionImpuesto3;
	}

	/**
	 * Gets the descripcion impuesto 4.
	 *
	 * @return the descripcion impuesto 4
	 */
	public String getDescripcionImpuesto4() {
		return descripcionImpuesto4;
	}

	/**
	 * Sets the descripcion impuesto 4.
	 *
	 * @param descripcionImpuesto4
	 *            the new descripcion impuesto 4
	 */
	public void setDescripcionImpuesto4(String descripcionImpuesto4) {
		this.descripcionImpuesto4 = descripcionImpuesto4;
	}

	/**
	 * Gets the descripcion impuesto 5.
	 *
	 * @return the descripcion impuesto 5
	 */
	public String getDescripcionImpuesto5() {
		return descripcionImpuesto5;
	}

	/**
	 * Sets the descripcion impuesto 5.
	 *
	 * @param descripcionImpuesto5
	 *            the new descripcion impuesto 5
	 */
	public void setDescripcionImpuesto5(String descripcionImpuesto5) {
		this.descripcionImpuesto5 = descripcionImpuesto5;
	}

	/**
	 * Gets the descripcion tipo plazo fijo.
	 *
	 * @return the descripcion tipo plazo fijo
	 */
	public String getDescripcionTipoPlazoFijo() {
		return descripcionTipoPlazoFijo;
	}

	/**
	 * Sets the descripcion tipo plazo fijo.
	 *
	 * @param descripcionTipoPlazoFijo
	 *            the new descripcion tipo plazo fijo
	 */
	public void setDescripcionTipoPlazoFijo(String descripcionTipoPlazoFijo) {
		this.descripcionTipoPlazoFijo = descripcionTipoPlazoFijo;
	}

	/**
	 * Gets the devengado.
	 *
	 * @return the devengado
	 */
	public Long getDevengado() {
		return devengado;
	}

	/**
	 * Sets the devengado.
	 *
	 * @param devengado
	 *            the new devengado
	 */
	public void setDevengado(Long devengado) {
		this.devengado = devengado;
	}

	/**
	 * Gets the entidad cuenta inversion.
	 *
	 * @return the entidad cuenta inversion
	 */
	public String getEntidadCuentaInversion() {
		return entidadCuentaInversion;
	}

	/**
	 * Sets the entidad cuenta inversion.
	 *
	 * @param entidadCuentaInversion
	 *            the new entidad cuenta inversion
	 */
	public void setEntidadCuentaInversion(String entidadCuentaInversion) {
		this.entidadCuentaInversion = entidadCuentaInversion;
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
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fecha constitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion
	 *            the new fecha constitucion
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * Gets the fecha devengado.
	 *
	 * @return the fecha devengado
	 */
	public String getFechaDevengado() {
		return fechaDevengado;
	}

	/**
	 * Sets the fecha devengado.
	 *
	 * @param fechaDevengado
	 *            the new fecha devengado
	 */
	public void setFechaDevengado(String fechaDevengado) {
		this.fechaDevengado = fechaDevengado;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the frecuencia cobro intereses.
	 *
	 * @return the frecuencia cobro intereses
	 */
	public BigDecimal getFrecuenciaCobroIntereses() {
		return frecuenciaCobroIntereses;
	}

	/**
	 * Gets the guid error.
	 *
	 * @return the guid error
	 */
	public String getGuidError() {
		return guidError;
	}

	/**
	 * Sets the guid error.
	 *
	 * @param guidError
	 *            the new guid error
	 */
	public void setGuidError(String guidError) {
		this.guidError = guidError;
	}

	/**
	 * Sets the frecuencia cobro intereses.
	 *
	 * @param frecuenciaCobroIntereses
	 *            the new frecuencia cobro intereses
	 */
	public void setFrecuenciaCobroIntereses(BigDecimal frecuenciaCobroIntereses) {
		this.frecuenciaCobroIntereses = frecuenciaCobroIntereses;
	}

	/**
	 * Gets the hora constitucion.
	 *
	 * @return the hora constitucion
	 */
	public String getHoraConstitucion() {
		return horaConstitucion;
	}

	/**
	 * Sets the hora constitucion.
	 *
	 * @param horaConstitucion
	 *            the new hora constitucion
	 */
	public void setHoraConstitucion(String horaConstitucion) {
		this.horaConstitucion = horaConstitucion;
	}

	/**
	 * Gets the impuesto CER.
	 *
	 * @return the impuesto CER
	 */
	public BigDecimal getImpuestoCER() {
		return impuestoCER;
	}

	/**
	 * Sets the impuesto CER.
	 *
	 * @param impuestoCER
	 *            the new impuesto CER
	 */
	public void setImpuestoCER(BigDecimal impuestoCER) {
		this.impuestoCER = impuestoCER;
	}

	/**
	 * Gets the int men var deveng DIVA.
	 *
	 * @return the int men var deveng DIVA
	 */
	public BigDecimal getIntMenVarDevengDIVA() {
		return intMenVarDevengDIVA;
	}

	/**
	 * Sets the int men var deveng DIVA.
	 *
	 * @param intMenVarDevengDIVA
	 *            the new int men var deveng DIVA
	 */
	public void setIntMenVarDevengDIVA(BigDecimal intMenVarDevengDIVA) {
		this.intMenVarDevengDIVA = intMenVarDevengDIVA;
	}

	/**
	 * Gets the int vard deven DIVA.
	 *
	 * @return the int vard deven DIVA
	 */
	public BigDecimal getIntVardDevenDIVA() {
		return intVardDevenDIVA;
	}

	/**
	 * Sets the int vard deven DIVA.
	 *
	 * @param intVardDevenDIVA
	 *            the new int vard deven DIVA
	 */
	public void setIntVardDevenDIVA(BigDecimal intVardDevenDIVA) {
		this.intVardDevenDIVA = intVardDevenDIVA;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public BigDecimal getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the new interes
	 */
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	/**
	 * Gets the interes dev tasa fija acum.
	 *
	 * @return the interes dev tasa fija acum
	 */
	public BigDecimal getInteresDevTasaFijaAcum() {
		return interesDevTasaFijaAcum;
	}

	/**
	 * Sets the interes dev tasa fija acum.
	 *
	 * @param interesDevTasaFijaAcum
	 *            the new interes dev tasa fija acum
	 */
	public void setInteresDevTasaFijaAcum(BigDecimal interesDevTasaFijaAcum) {
		this.interesDevTasaFijaAcum = interesDevTasaFijaAcum;
	}

	/**
	 * Gets the interes dev X cap aju acum.
	 *
	 * @return the interes dev X cap aju acum
	 */
	public BigDecimal getInteresDevXCapAjuAcum() {
		return interesDevXCapAjuAcum;
	}

	/**
	 * Sets the interes dev X cap aju acum.
	 *
	 * @param interesDevXCapAjuAcum
	 *            the new interes dev X cap aju acum
	 */
	public void setInteresDevXCapAjuAcum(BigDecimal interesDevXCapAjuAcum) {
		this.interesDevXCapAjuAcum = interesDevXCapAjuAcum;
	}

	/**
	 * Gets the interes devengado acum.
	 *
	 * @return the interes devengado acum
	 */
	public BigDecimal getInteresDevengadoAcum() {
		return interesDevengadoAcum;
	}

	/**
	 * Sets the interes devengado acum.
	 *
	 * @param interesDevengadoAcum
	 *            the new interes devengado acum
	 */
	public void setInteresDevengadoAcum(BigDecimal interesDevengadoAcum) {
		this.interesDevengadoAcum = interesDevengadoAcum;
	}

	/**
	 * Gets the momento de cobro 1.
	 *
	 * @return the momento de cobro 1
	 */
	public String getMomentoDeCobro1() {
		return momentoDeCobro1;
	}

	/**
	 * Sets the momento de cobro 1.
	 *
	 * @param momentoDeCobro1
	 *            the new momento de cobro 1
	 */
	public void setMomentoDeCobro1(String momentoDeCobro1) {
		this.momentoDeCobro1 = momentoDeCobro1;
	}

	/**
	 * Gets the momento de cobro 2.
	 *
	 * @return the momento de cobro 2
	 */
	public String getMomentoDeCobro2() {
		return momentoDeCobro2;
	}

	/**
	 * Sets the momento de cobro 2.
	 *
	 * @param momentoDeCobro2
	 *            the new momento de cobro 2
	 */
	public void setMomentoDeCobro2(String momentoDeCobro2) {
		this.momentoDeCobro2 = momentoDeCobro2;
	}

	/**
	 * Gets the momento de cobro 3.
	 *
	 * @return the momento de cobro 3
	 */
	public String getMomentoDeCobro3() {
		return momentoDeCobro3;
	}

	/**
	 * Sets the momento de cobro 3.
	 *
	 * @param momentoDeCobro3
	 *            the new momento de cobro 3
	 */
	public void setMomentoDeCobro3(String momentoDeCobro3) {
		this.momentoDeCobro3 = momentoDeCobro3;
	}

	/**
	 * Gets the momento de cobro 4.
	 *
	 * @return the momento de cobro 4
	 */
	public String getMomentoDeCobro4() {
		return momentoDeCobro4;
	}

	/**
	 * Sets the momento de cobro 4.
	 *
	 * @param momentoDeCobro4
	 *            the new momento de cobro 4
	 */
	public void setMomentoDeCobro4(String momentoDeCobro4) {
		this.momentoDeCobro4 = momentoDeCobro4;
	}

	/**
	 * Gets the momento de cobro 5.
	 *
	 * @return the momento de cobro 5
	 */
	public String getMomentoDeCobro5() {
		return momentoDeCobro5;
	}

	/**
	 * Sets the momento de cobro 5.
	 *
	 * @param momentoDeCobro5
	 *            the new momento de cobro 5
	 */
	public void setMomentoDeCobro5(String momentoDeCobro5) {
		this.momentoDeCobro5 = momentoDeCobro5;
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
	 * Gets the monto cobrar.
	 *
	 * @return the monto cobrar
	 */
	public BigDecimal getMontoCobrar() {
		return montoCobrar;
	}

	/**
	 * Sets the monto cobrar.
	 *
	 * @param montoCobrar
	 *            the new monto cobrar
	 */
	public void setMontoCobrar(BigDecimal montoCobrar) {
		this.montoCobrar = montoCobrar;
	}

	/**
	 * Gets the monto moneda extranjera 1.
	 *
	 * @return the monto moneda extranjera 1
	 */
	public BigDecimal getMontoMonedaExtranjera1() {
		return montoMonedaExtranjera1;
	}

	/**
	 * Sets the monto moneda extranjera 1.
	 *
	 * @param montoMonedaExtranjera1
	 *            the new monto moneda extranjera 1
	 */
	public void setMontoMonedaExtranjera1(BigDecimal montoMonedaExtranjera1) {
		this.montoMonedaExtranjera1 = montoMonedaExtranjera1;
	}

	/**
	 * Gets the monto moneda extranjera 2.
	 *
	 * @return the monto moneda extranjera 2
	 */
	public BigDecimal getMontoMonedaExtranjera2() {
		return montoMonedaExtranjera2;
	}

	/**
	 * Sets the monto moneda extranjera 2.
	 *
	 * @param montoMonedaExtranjera2
	 *            the new monto moneda extranjera 2
	 */
	public void setMontoMonedaExtranjera2(BigDecimal montoMonedaExtranjera2) {
		this.montoMonedaExtranjera2 = montoMonedaExtranjera2;
	}

	/**
	 * Gets the monto moneda extranjera 3.
	 *
	 * @return the monto moneda extranjera 3
	 */
	public BigDecimal getMontoMonedaExtranjera3() {
		return montoMonedaExtranjera3;
	}

	/**
	 * Sets the monto moneda extranjera 3.
	 *
	 * @param montoMonedaExtranjera3
	 *            the new monto moneda extranjera 3
	 */
	public void setMontoMonedaExtranjera3(BigDecimal montoMonedaExtranjera3) {
		this.montoMonedaExtranjera3 = montoMonedaExtranjera3;
	}

	/**
	 * Gets the monto moneda extranjera 4.
	 *
	 * @return the monto moneda extranjera 4
	 */
	public BigDecimal getMontoMonedaExtranjera4() {
		return montoMonedaExtranjera4;
	}

	/**
	 * Sets the monto moneda extranjera 4.
	 *
	 * @param montoMonedaExtranjera4
	 *            the new monto moneda extranjera 4
	 */
	public void setMontoMonedaExtranjera4(BigDecimal montoMonedaExtranjera4) {
		this.montoMonedaExtranjera4 = montoMonedaExtranjera4;
	}

	/**
	 * Gets the monto moneda extranjera 5.
	 *
	 * @return the monto moneda extranjera 5
	 */
	public BigDecimal getMontoMonedaExtranjera5() {
		return montoMonedaExtranjera5;
	}

	/**
	 * Sets the monto moneda extranjera 5.
	 *
	 * @param montoMonedaExtranjera5
	 *            the new monto moneda extranjera 5
	 */
	public void setMontoMonedaExtranjera5(BigDecimal montoMonedaExtranjera5) {
		this.montoMonedaExtranjera5 = montoMonedaExtranjera5;
	}

	/**
	 * Gets the monto moneda local 1.
	 *
	 * @return the monto moneda local 1
	 */
	public BigDecimal getMontoMonedaLocal1() {
		return montoMonedaLocal1;
	}

	/**
	 * Sets the monto moneda local 1.
	 *
	 * @param montoMonedaLocal1
	 *            the new monto moneda local 1
	 */
	public void setMontoMonedaLocal1(BigDecimal montoMonedaLocal1) {
		this.montoMonedaLocal1 = montoMonedaLocal1;
	}

	/**
	 * Gets the monto moneda local 2.
	 *
	 * @return the monto moneda local 2
	 */
	public BigDecimal getMontoMonedaLocal2() {
		return montoMonedaLocal2;
	}

	/**
	 * Sets the monto moneda local 2.
	 *
	 * @param montoMonedaLocal2
	 *            the new monto moneda local 2
	 */
	public void setMontoMonedaLocal2(BigDecimal montoMonedaLocal2) {
		this.montoMonedaLocal2 = montoMonedaLocal2;
	}

	/**
	 * Gets the monto moneda local 3.
	 *
	 * @return the monto moneda local 3
	 */
	public BigDecimal getMontoMonedaLocal3() {
		return montoMonedaLocal3;
	}

	/**
	 * Sets the monto moneda local 3.
	 *
	 * @param montoMonedaLocal3
	 *            the new monto moneda local 3
	 */
	public void setMontoMonedaLocal3(BigDecimal montoMonedaLocal3) {
		this.montoMonedaLocal3 = montoMonedaLocal3;
	}

	/**
	 * Gets the monto moneda local 4.
	 *
	 * @return the monto moneda local 4
	 */
	public BigDecimal getMontoMonedaLocal4() {
		return montoMonedaLocal4;
	}

	/**
	 * Sets the monto moneda local 4.
	 *
	 * @param montoMonedaLocal4
	 *            the new monto moneda local 4
	 */
	public void setMontoMonedaLocal4(BigDecimal montoMonedaLocal4) {
		this.montoMonedaLocal4 = montoMonedaLocal4;
	}

	/**
	 * Gets the monto moneda local 5.
	 *
	 * @return the monto moneda local 5
	 */
	public BigDecimal getMontoMonedaLocal5() {
		return montoMonedaLocal5;
	}

	/**
	 * Sets the monto moneda local 5.
	 *
	 * @param montoMonedaLocal5
	 *            the new monto moneda local 5
	 */
	public void setMontoMonedaLocal5(BigDecimal montoMonedaLocal5) {
		this.montoMonedaLocal5 = montoMonedaLocal5;
	}

	/**
	 * Gets the numero certificado.
	 *
	 * @return the numero certificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the numero cuenta inversion.
	 *
	 * @return the numero cuenta inversion
	 */
	public String getNumeroCuentaInversion() {
		return numeroCuentaInversion;
	}

	/**
	 * Sets the numero cuenta inversion.
	 *
	 * @param numeroCuentaInversion
	 *            the new numero cuenta inversion
	 */
	public void setNumeroCuentaInversion(String numeroCuentaInversion) {
		this.numeroCuentaInversion = numeroCuentaInversion;
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
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the numero secuencia.
	 *
	 * @return the numero secuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	/**
	 * Sets the numero secuencia.
	 *
	 * @param numeroSecuencia
	 *            the new numero secuencia
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	/**
	 * Gets the plazo minimo precancelacion.
	 *
	 * @return the plazo minimo precancelacion
	 */
	public BigDecimal getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	/**
	 * Sets the plazo minimo precancelacion.
	 *
	 * @param plazoMinimoPrecancelacion
	 *            the new plazo minimo precancelacion
	 */
	public void setPlazoMinimoPrecancelacion(BigDecimal plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

	/**
	 * Gets the plazo vigencia.
	 *
	 * @return the plazo vigencia
	 */
	public BigDecimal getPlazoVigencia() {
		return plazoVigencia;
	}

	/**
	 * Sets the plazo vigencia.
	 *
	 * @param plazoVigencia
	 *            the new plazo vigencia
	 */
	public void setPlazoVigencia(BigDecimal plazoVigencia) {
		this.plazoVigencia = plazoVigencia;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentaje penalizacion
	 */
	public BigDecimal getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the new porcentaje penalizacion
	 */
	public void setPorcentajePenalizacion(BigDecimal porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the resultado bruto.
	 *
	 * @return the resultado bruto
	 */
	public String getResultadoBruto() {
		return resultadoBruto;
	}

	/**
	 * Sets the resultado bruto.
	 *
	 * @param resultadoBruto
	 *            the new resultado bruto
	 */
	public void setResultadoBruto(String resultadoBruto) {
		this.resultadoBruto = resultadoBruto;
	}

	/**
	 * Gets the resultado bruto reexp.
	 *
	 * @return the resultado bruto reexp
	 */
	public String getResultadoBrutoReexp() {
		return resultadoBrutoReexp;
	}

	/**
	 * Sets the resultado bruto reexp.
	 *
	 * @param resultadoBrutoReexp
	 *            the new resultado bruto reexp
	 */
	public void setResultadoBrutoReexp(String resultadoBrutoReexp) {
		this.resultadoBrutoReexp = resultadoBrutoReexp;
	}

	/**
	 * Gets the retribuciones variables acumuladas.
	 *
	 * @return the retribuciones variables acumuladas
	 */
	public BigDecimal getRetribucionesVariablesAcumuladas() {
		return retribucionesVariablesAcumuladas;
	}

	/**
	 * Sets the retribuciones variables acumuladas.
	 *
	 * @param retribucionesVariablesAcumuladas
	 *            the new retribuciones variables acumuladas
	 */
	public void setRetribucionesVariablesAcumuladas(BigDecimal retribucionesVariablesAcumuladas) {
		this.retribucionesVariablesAcumuladas = retribucionesVariablesAcumuladas;
	}

	/**
	 * Gets the sucursal accion vencimiento.
	 *
	 * @return the sucursal accion vencimiento
	 */
	public BigDecimal getSucursalAccionVencimiento() {
		return sucursalAccionVencimiento;
	}

	/**
	 * Sets the sucursal accion vencimiento.
	 *
	 * @param sucursalAccionVencimiento
	 *            the new sucursal accion vencimiento
	 */
	public void setSucursalAccionVencimiento(BigDecimal sucursalAccionVencimiento) {
		this.sucursalAccionVencimiento = sucursalAccionVencimiento;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the sucursal debito.
	 *
	 * @return the sucursal debito
	 */
	public BigDecimal getSucursalDebito() {
		return sucursalDebito;
	}

	/**
	 * Sets the sucursal debito.
	 *
	 * @param sucursalDebito
	 *            the new sucursal debito
	 */
	public void setSucursalDebito(BigDecimal sucursalDebito) {
		this.sucursalDebito = sucursalDebito;
	}

	/**
	 * Gets the sucursal radicacion.
	 *
	 * @return the sucursal radicacion
	 */
	public BigDecimal getSucursalRadicacion() {
		return sucursalRadicacion;
	}

	/**
	 * Sets the sucursal radicacion.
	 *
	 * @param sucursalRadicacion
	 *            the new sucursal radicacion
	 */
	public void setSucursalRadicacion(BigDecimal sucursalRadicacion) {
		this.sucursalRadicacion = sucursalRadicacion;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public BigDecimal getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(BigDecimal tna) {
		this.tna = tna;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenencia valuada reexp
	 */
	public String getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the new tenencia valuada reexp
	 */
	public void setTenenciaValuadaReexp(String tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

    /**
	 * Gets the cotizacion CERUVA.
	 *
	 * @return the cotizacionCERUVA
	 */
    public String getCotizacionCERUVA() {
        return cotizacionCERUVA;
    }

    /**
	 * Sets the cotizacion CERUVA.
	 *
	 * @param cotizacionCERUVA
	 *            the cotizacionCERUVA to set
	 */
    public void setCotizacionCERUVA(String cotizacionCERUVA) {
        this.cotizacionCERUVA = cotizacionCERUVA;
    }
	
	

}
