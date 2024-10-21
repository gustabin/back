/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaOperacionesResponse.
 */
public class DatosConsultaOperacionesResponse {
	
    
	/** The orden id. */
	@JsonProperty("OrdenId")
	private String ordenId;
	    
	/** The cod sistema. */
	@JsonProperty("CodSistema")
	private String codSistema;
	    
	/** The tipo operacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;
	
    /** The especie. */
    @JsonProperty("Especie")
    private String especie;  
    
    /** The secid. */
    @JsonProperty("Secid")
    private String secid;
    
    /** The tipo especie. */
    @JsonProperty("TipoEspecie")
    private String tipoEspecie;
    
    /** The cod moneda. */
    @JsonProperty("CodMoneda")
    private String codMoneda;
    
    /** The fecha liq. */
    @JsonProperty("FechaLiq")
    private String fechaLiq;
    
    /** The cant inicial. */
    @JsonProperty("CantInicial")
    private String cantInicial;
    
    /** The precio limite. */
    @JsonProperty("PrecioLimite")
    private String precioLimite;
    
    /** The capital. */
    @JsonProperty("Capital")
    private String capital;
    
    /** The capital V. */
    @JsonProperty("CapitalV")
    private String capitalV;
    
    /** The detalle. */
    @JsonProperty("Detalle")
    private String detalle;
    
    /** The estado. */
    @JsonProperty("Estado")
    private String estado;
    
    /** The hora emi. */
    @JsonProperty("HoraEmi")
    private String horaEmi;
    
    /** The fcia agente. */
    @JsonProperty("FciaAgente")
    private String fciaAgente;
    
    /** The cno. */
    @JsonProperty("Cno")
    private String cno;
    
    /** The mone liq. */
    @JsonProperty("MoneLiq")
    private String moneLiq;
    
    /** The num orden origen. */
    @JsonProperty("NumOrdenOrigen")
    private String numOrdenOrigen;

    /** The fecha orden. */
    @JsonProperty("FechaOrden")
    private String fechaOrden;
    
    /** The oper alta. */
    @JsonProperty("OperAlta")
    private String operAlta;
    
    /** The interes 8. */
    @JsonProperty("Interes8")
    private String interes8;
    
    /** The precio uni 8. */
    @JsonProperty("PrecioUni8")
    private String precioUni8;
    
    /** The valor interno 8. */
    @JsonProperty("ValorInterno8")
    private String valorInterno8;
    
    /** The plazo. */
    @JsonProperty("Plazo")
    private String plazo;
    
    /** The sector. */
    @JsonProperty("Sector")
    private String sector;
    
    /** The num mae. */
    @JsonProperty("NumMae")
    private String numMae;
    
    /** The canttitulo 8. */
    @JsonProperty("Canttitulo8")
    private String canttitulo8;

    /** The cant titulo 8 V. */
    @JsonProperty("CantTitulo8V")
    private String cantTitulo8V;
    
    /** The cost ord. */
    @JsonProperty("CostOrd")
    private String costOrd;
    
    /** The derechos. */
    @JsonProperty("Derechos")
    private String derechos;
    
    /** The arancel porc. */
    @JsonProperty("ArancelPorc")
    private String arancelPorc;
    
    /** The mercado operacion. */
    @JsonProperty("MercadoOperacion")
    private String mercadoOperacion;
    
    /** The mercado origen. */
    @JsonProperty("MercadoOrigen")
    private String mercadoOrigen;
    
    /** The mercado oper descripcion. */
    @JsonProperty("MercadoOperDescripcion")
    private String mercadoOperDescripcion;

    /** The iva. */
    @JsonProperty("Iva")
    private String iva;
    
    /** The comision. */
    @JsonProperty("Comision")
    private String comision; 

    /** The impuesto. */
    @JsonProperty("Impuesto")
    private String impuesto;

	/** The cantidad. */
	@JsonProperty("Cantidad")
    private String cantidad;
	
	/** The precio. */
	@JsonProperty("Precio")
	private String precio;
	 
	/** The suc cta oper. */
	@JsonProperty("SucCtaOper")
	private String sucCtaOper;
	 
	/** The tipo cta oper. */
	@JsonProperty("TipoCtaOper")
	private String tipoCtaOper;

    /** The nro cta oper. */
    @JsonProperty("NroCtaOper")
	private String nroCtaOper;
	    
    /** The cuenta titulos. */
    @JsonProperty("CuentaTitulos")
    private String cuentaTitulos;

	/** The codigo. */
	@JsonProperty("CODIGO")
	private String codigo;
	 
	/** The moneda descripcion. */
	@JsonProperty("MONEDA_DESCRIPCION")
	private String monedaDescripcion;
	    
	/** The signo. */
	@JsonProperty("SIGNO")
	private String signo;
	 
	/** The abreviatura caja valores. */
	@JsonProperty("Abreviatura_Caja_Valores")
	private String abreviaturaCajaValores;
	 
	/** The motivo rechazo. */
	@JsonProperty("Motivo_Rechazo")
	private String motivoRechazo;
	    
	/** The canal. */
	@JsonProperty("Canal")
	private String canal;

	/** The comprobante. */
	@JsonProperty("Comprobante")
	private String comprobante;

	/** The id. */
	@JsonProperty("Id")
	private String id;

	/** The fecha alta. */
	@JsonProperty("FechaAlta")
	private String fechaAlta;
	    
	/** The usuario alta. */
	@JsonProperty("UsuarioAlta")
	private String usuarioAlta;

	/** The enable. */
	@JsonProperty("Enable")
	private String enable;

	/** The is new. */
	@JsonProperty("IsNew")
	private String isNew;

	
	/**
	 * Gets the orden id.
	 *
	 * @return the orden id
	 */
	public String getOrdenId() {
		return ordenId;
	}

	/**
	 * Sets the orden id.
	 *
	 * @param ordenId
	 *            the new orden id
	 */
	public void setOrdenId(String ordenId) {
		this.ordenId = ordenId;
	}

	/**
	 * Gets the cod sistema.
	 *
	 * @return the cod sistema
	 */
	public String getCodSistema() {
		return codSistema;
	}

	/**
	 * Sets the cod sistema.
	 *
	 * @param codSistema
	 *            the new cod sistema
	 */
	public void setCodSistema(String codSistema) {
		this.codSistema = codSistema;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the secid.
	 *
	 * @return the secid
	 */
	public String getSecid() {
		return secid;
	}

	/**
	 * Sets the secid.
	 *
	 * @param secid
	 *            the new secid
	 */
	public void setSecid(String secid) {
		this.secid = secid;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the cod moneda.
	 *
	 * @return the cod moneda
	 */
	public String getCodMoneda() {
		return codMoneda;
	}

	/**
	 * Sets the cod moneda.
	 *
	 * @param codMoneda
	 *            the new cod moneda
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	/**
	 * Gets the fecha liq.
	 *
	 * @return the fecha liq
	 */
	public String getFechaLiq() {
		return fechaLiq;
	}

	/**
	 * Sets the fecha liq.
	 *
	 * @param fechaLiq
	 *            the new fecha liq
	 */
	public void setFechaLiq(String fechaLiq) {
		this.fechaLiq = fechaLiq;
	}

	/**
	 * Gets the cant inicial.
	 *
	 * @return the cant inicial
	 */
	public String getCantInicial() {
		return cantInicial;
	}

	/**
	 * Sets the cant inicial.
	 *
	 * @param cantInicial
	 *            the new cant inicial
	 */
	public void setCantInicial(String cantInicial) {
		this.cantInicial = cantInicial;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the capital V.
	 *
	 * @return the capital V
	 */
	public String getCapitalV() {
		return capitalV;
	}

	/**
	 * Sets the capital V.
	 *
	 * @param capitalV
	 *            the new capital V
	 */
	public void setCapitalV(String capitalV) {
		this.capitalV = capitalV;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the new detalle
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	 * Gets the hora emi.
	 *
	 * @return the hora emi
	 */
	public String getHoraEmi() {
		return horaEmi;
	}

	/**
	 * Sets the hora emi.
	 *
	 * @param horaEmi
	 *            the new hora emi
	 */
	public void setHoraEmi(String horaEmi) {
		this.horaEmi = horaEmi;
	}

	/**
	 * Gets the fcia agente.
	 *
	 * @return the fcia agente
	 */
	public String getFciaAgente() {
		return fciaAgente;
	}

	/**
	 * Sets the fcia agente.
	 *
	 * @param fciaAgente
	 *            the new fcia agente
	 */
	public void setFciaAgente(String fciaAgente) {
		this.fciaAgente = fciaAgente;
	}

	/**
	 * Gets the cno.
	 *
	 * @return the cno
	 */
	public String getCno() {
		return cno;
	}

	/**
	 * Sets the cno.
	 *
	 * @param cno
	 *            the new cno
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}

	/**
	 * Gets the mone liq.
	 *
	 * @return the mone liq
	 */
	public String getMoneLiq() {
		return moneLiq;
	}

	/**
	 * Sets the mone liq.
	 *
	 * @param moneLiq
	 *            the new mone liq
	 */
	public void setMoneLiq(String moneLiq) {
		this.moneLiq = moneLiq;
	}

	/**
	 * Gets the num orden origen.
	 *
	 * @return the num orden origen
	 */
	public String getNumOrdenOrigen() {
		return numOrdenOrigen;
	}

	/**
	 * Sets the num orden origen.
	 *
	 * @param numOrdenOrigen
	 *            the new num orden origen
	 */
	public void setNumOrdenOrigen(String numOrdenOrigen) {
		this.numOrdenOrigen = numOrdenOrigen;
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
	 * @param fechaOrden
	 *            the new fecha orden
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the oper alta.
	 *
	 * @return the oper alta
	 */
	public String getOperAlta() {
		return operAlta;
	}

	/**
	 * Sets the oper alta.
	 *
	 * @param operAlta
	 *            the new oper alta
	 */
	public void setOperAlta(String operAlta) {
		this.operAlta = operAlta;
	}

	/**
	 * Gets the interes 8.
	 *
	 * @return the interes 8
	 */
	public String getInteres8() {
		return interes8;
	}

	/**
	 * Sets the interes 8.
	 *
	 * @param interes8
	 *            the new interes 8
	 */
	public void setInteres8(String interes8) {
		this.interes8 = interes8;
	}

	/**
	 * Gets the precio uni 8.
	 *
	 * @return the precio uni 8
	 */
	public String getPrecioUni8() {
		return precioUni8;
	}

	/**
	 * Sets the precio uni 8.
	 *
	 * @param precioUni8
	 *            the new precio uni 8
	 */
	public void setPrecioUni8(String precioUni8) {
		this.precioUni8 = precioUni8;
	}

	/**
	 * Gets the valor interno 8.
	 *
	 * @return the valor interno 8
	 */
	public String getValorInterno8() {
		return valorInterno8;
	}

	/**
	 * Sets the valor interno 8.
	 *
	 * @param valorInterno8
	 *            the new valor interno 8
	 */
	public void setValorInterno8(String valorInterno8) {
		this.valorInterno8 = valorInterno8;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the sector.
	 *
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 *
	 * @param sector
	 *            the new sector
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * Gets the num mae.
	 *
	 * @return the num mae
	 */
	public String getNumMae() {
		return numMae;
	}

	/**
	 * Sets the num mae.
	 *
	 * @param numMae
	 *            the new num mae
	 */
	public void setNumMae(String numMae) {
		this.numMae = numMae;
	}

	/**
	 * Gets the canttitulo 8.
	 *
	 * @return the canttitulo 8
	 */
	public String getCanttitulo8() {
		return canttitulo8;
	}

	/**
	 * Sets the canttitulo 8.
	 *
	 * @param canttitulo8
	 *            the new canttitulo 8
	 */
	public void setCanttitulo8(String canttitulo8) {
		this.canttitulo8 = canttitulo8;
	}

	/**
	 * Gets the cant titulo 8 V.
	 *
	 * @return the cant titulo 8 V
	 */
	public String getCantTitulo8V() {
		return cantTitulo8V;
	}

	/**
	 * Sets the cant titulo 8 V.
	 *
	 * @param cantTitulo8V
	 *            the new cant titulo 8 V
	 */
	public void setCantTitulo8V(String cantTitulo8V) {
		this.cantTitulo8V = cantTitulo8V;
	}

	/**
	 * Gets the cost ord.
	 *
	 * @return the cost ord
	 */
	public String getCostOrd() {
		return costOrd;
	}

	/**
	 * Sets the cost ord.
	 *
	 * @param costOrd
	 *            the new cost ord
	 */
	public void setCostOrd(String costOrd) {
		this.costOrd = costOrd;
	}

	/**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
	public String getDerechos() {
		return derechos;
	}

	/**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the new derechos
	 */
	public void setDerechos(String derechos) {
		this.derechos = derechos;
	}

	/**
	 * Gets the arancel porc.
	 *
	 * @return the arancel porc
	 */
	public String getArancelPorc() {
		return arancelPorc;
	}

	/**
	 * Sets the arancel porc.
	 *
	 * @param arancelPorc
	 *            the new arancel porc
	 */
	public void setArancelPorc(String arancelPorc) {
		this.arancelPorc = arancelPorc;
	}

	/**
	 * Gets the mercado operacion.
	 *
	 * @return the mercado operacion
	 */
	public String getMercadoOperacion() {
		return mercadoOperacion;
	}

	/**
	 * Sets the mercado operacion.
	 *
	 * @param mercadoOperacion
	 *            the new mercado operacion
	 */
	public void setMercadoOperacion(String mercadoOperacion) {
		this.mercadoOperacion = mercadoOperacion;
	}

	/**
	 * Gets the mercado origen.
	 *
	 * @return the mercado origen
	 */
	public String getMercadoOrigen() {
		return mercadoOrigen;
	}

	/**
	 * Sets the mercado origen.
	 *
	 * @param mercadoOrigen
	 *            the new mercado origen
	 */
	public void setMercadoOrigen(String mercadoOrigen) {
		this.mercadoOrigen = mercadoOrigen;
	}

	/**
	 * Gets the mercado oper descripcion.
	 *
	 * @return the mercado oper descripcion
	 */
	public String getMercadoOperDescripcion() {
		return mercadoOperDescripcion;
	}

	/**
	 * Sets the mercado oper descripcion.
	 *
	 * @param mercadoOperDescripcion
	 *            the new mercado oper descripcion
	 */
	public void setMercadoOperDescripcion(String mercadoOperDescripcion) {
		this.mercadoOperDescripcion = mercadoOperDescripcion;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the new iva
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public String getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the new comision
	 */
	public void setComision(String comision) {
		this.comision = comision;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public String getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the new impuesto
	 */
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
	 * @param precio
	 *            the new precio
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Gets the suc cta oper.
	 *
	 * @return the suc cta oper
	 */
	public String getSucCtaOper() {
		return sucCtaOper;
	}

	/**
	 * Sets the suc cta oper.
	 *
	 * @param sucCtaOper
	 *            the new suc cta oper
	 */
	public void setSucCtaOper(String sucCtaOper) {
		this.sucCtaOper = sucCtaOper;
	}

	/**
	 * Gets the tipo cta oper.
	 *
	 * @return the tipo cta oper
	 */
	public String getTipoCtaOper() {
		return tipoCtaOper;
	}

	/**
	 * Sets the tipo cta oper.
	 *
	 * @param tipoCtaOper
	 *            the new tipo cta oper
	 */
	public void setTipoCtaOper(String tipoCtaOper) {
		this.tipoCtaOper = tipoCtaOper;
	}

	/**
	 * Gets the nro cta oper.
	 *
	 * @return the nro cta oper
	 */
	public String getNroCtaOper() {
		return nroCtaOper;
	}

	/**
	 * Sets the nro cta oper.
	 *
	 * @param nroCtaOper
	 *            the new nro cta oper
	 */
	public void setNroCtaOper(String nroCtaOper) {
		this.nroCtaOper = nroCtaOper;
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
	 * @param cuentaTitulos
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * Gets the moneda descripcion.
	 *
	 * @return the moneda descripcion
	 */
	public String getMonedaDescripcion() {
		return monedaDescripcion;
	}

	/**
	 * Sets the moneda descripcion.
	 *
	 * @param monedaDescripcion
	 *            the new moneda descripcion
	 */
	public void setMonedaDescripcion(String monedaDescripcion) {
		this.monedaDescripcion = monedaDescripcion;
	}

	/**
	 * Gets the signo.
	 *
	 * @return the signo
	 */
	public String getSigno() {
		return signo;
	}

	/**
	 * Sets the signo.
	 *
	 * @param signo
	 *            the new signo
	 */
	public void setSigno(String signo) {
		this.signo = signo;
	}

	/**
	 * Gets the abreviatura caja valores.
	 *
	 * @return the abreviatura caja valores
	 */
	public String getAbreviaturaCajaValores() {
		return abreviaturaCajaValores;
	}

	/**
	 * Sets the abreviatura caja valores.
	 *
	 * @param abreviaturaCajaValores
	 *            the new abreviatura caja valores
	 */
	public void setAbreviaturaCajaValores(String abreviaturaCajaValores) {
		this.abreviaturaCajaValores = abreviaturaCajaValores;
	}

	/**
	 * Gets the motivo rechazo.
	 *
	 * @return the motivo rechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * Sets the motivo rechazo.
	 *
	 * @param motivoRechazo
	 *            the new motivo rechazo
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
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
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
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
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the usuario alta.
	 *
	 * @return the usuario alta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * Sets the usuario alta.
	 *
	 * @param usuarioAlta
	 *            the new usuario alta
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * Gets the enable.
	 *
	 * @return the enable
	 */
	public String getEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable
	 *            the new enable
	 */
	public void setEnable(String enable) {
		this.enable = enable;
	}

	/**
	 * Gets the checks if is new.
	 *
	 * @return the checks if is new
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * Sets the checks if is new.
	 *
	 * @param isNew
	 *            the new checks if is new
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	      
    
}
