/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.dto;

import java.util.Date;

/**
 * The Class OperacionTitulosDTO.
 */
public class OperacionTitulosDTO {

	/** The descripcion. */
	private String descripcion;
	
	/** The fecha. */
	private Date fecha;
	
	/** The numero. */
	private String numero;
	
	/** The numero operacion. */
	private Long numeroOperacion;
	
	/** The tipo. */
	private String tipo;
	
	/** The tipo codigo. */
	private String tipoCodigo;
	
	/** The moneda. */
	private String moneda;
	
	/** The cantidad nominales. */
	private Double cantidadNominales;
	
	/** The precio. */
	private Double precio;
	
	/** The precio string. */
	private String precioString;
	
	/** The tipo operacion. */
	private String tipoOperacion;
	
	/** The cuenta. */
	private String cuenta;
	
	/** The estado. */
	private String estado;
	
	/** The moneda operacion. */
	private String monedaOperacion;
	
	/** The plazo liquidacion. */
	private Integer plazoLiquidacion;
	
	/** The mercado descripcion. */
	private String mercadoDescripcion;
	
	/** The precio limite. */
	private Double precioLimite;
	
	/** The precio referencia. */
	private String precioReferencia;
	
	/** The importe. */
	private Double importe;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The sucursal cuenta destino. */
	private String sucursalCuentaDestino;
	
	/** The tipo cuenta destino. */
	private String tipoCuentaDestino;
	
	/** The cuenta destino. */
	private String cuentaDestino;
	
	/** The comisiones. */
	private Double comisiones;
	
	/** The impuestos. */
	private Double impuestos;
	
	/** The canal ingreso. */
	private String canalIngreso;
	
	/** The codigo tipo especie. */
	private String codigoTipoEspecie;
	
	/** The fecha debito. */
	private String fechaDebito;
	
	/** The fecha cierre. */
	private String fechaCierre;
	
	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;
	
	/** The fecha liquidacion. */
	private String fechaLiquidacion;
	
	/** The cantidad. */
	private String cantidad;
	
	/** The tramo. */
	private String tramo;
	
	/** The tipo precio. */
	private String tipoPrecio;
	
	/** The importe debitar. */
	private String importeDebitar;
	
	/** The moneda especie. */
	private String monedaEspecie;
	
	/** The precio adjudicado. */
	private Double precioAdjudicado;
	
	/** The precio adjudicado string. */
	private String precioAdjudicadoString;
	
	/** The abreviatura caja valores. */
	private String abreviaturaCajaValores;
	
	/** The legal. */
	private String legal;
	
	/** The derechos. */
	private Double derechos;
	
	private String tipoPliego;
	
	private String monedaEspecieDestino;
	
	private String tipoPrecioDatoCanje;

	private String especieDestino;
	/**
	 * Gets the sucursal cuenta destino.
	 *
	 * @return the sucursal cuenta destino
	 */
	public String getSucursalCuentaDestino() {
		return sucursalCuentaDestino;
	}

	/**
	 * Sets the sucursal cuenta destino.
	 *
	 * @param sucursalCuentaDestino
	 *            the new sucursal cuenta destino
	 */
	public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
		this.sucursalCuentaDestino = sucursalCuentaDestino;
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

	/**
	 * Gets the canal ingreso.
	 *
	 * @return the canal ingreso
	 */
	public String getCanalIngreso() {
		return canalIngreso;
	}

	/**
	 * Sets the canal ingreso.
	 *
	 * @param canalIngreso
	 *            the new canal ingreso
	 */
	public void setCanalIngreso(String canalIngreso) {
		this.canalIngreso = canalIngreso;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public Double getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public Double getComisiones() {
		return comisiones;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the new comisiones
	 */
	public void setComisiones(Double comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public Double getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	/**
	 * Gets the precio referencia.
	 *
	 * @return the precio referencia
	 */
	public String getPrecioReferencia() {
		return precioReferencia;
	}

	/**
	 * Sets the precio referencia.
	 *
	 * @param precioReferencia
	 *            the new precio referencia
	 */
	public void setPrecioReferencia(String precioReferencia) {
		this.precioReferencia = precioReferencia;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public Double getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(Double precioLimite) {
		this.precioLimite = precioLimite;
	}

	/**
	 * Gets the mercado descripcion.
	 *
	 * @return the mercado descripcion
	 */
	public String getMercadoDescripcion() {
		return mercadoDescripcion;
	}

	/**
	 * Sets the mercado descripcion.
	 *
	 * @param mercadoDescripcion
	 *            the new mercado descripcion
	 */
	public void setMercadoDescripcion(String mercadoDescripcion) {
		this.mercadoDescripcion = mercadoDescripcion;
	}

	/**
	 * Gets the plazo liquidacion.
	 *
	 * @return the plazo liquidacion
	 */
	public Integer getPlazoLiquidacion() {
		return plazoLiquidacion;
	}

	/**
	 * Sets the plazo liquidacion.
	 *
	 * @param plazoLiquidacion
	 *            the new plazo liquidacion
	 */
	public void setPlazoLiquidacion(Integer plazoLiquidacion) {
		this.plazoLiquidacion = plazoLiquidacion;
	}

	/**
	 * Gets the moneda operacion.
	 *
	 * @return the moneda operacion
	 */
	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	/**
	 * Sets the moneda operacion.
	 *
	 * @param monedaOperacion
	 *            the new moneda operacion
	 */
	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
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
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}


	/**
	 * Gets the numero operacion.
	 *
	 * @return the numeroOperacion
	 */
	public Long getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the numeroOperacion to set
	 */
	public void setNumeroOperacion(Long numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Gets the cantidad nominales.
	 *
	 * @return the cantidad nominales
	 */
	public Double getCantidadNominales() {
		return cantidadNominales;
	}

	/**
	 * Sets the cantidad nominales.
	 *
	 * @param cantidadNominales
	 *            the new cantidad nominales
	 */
	public void setCantidadNominales(Double cantidadNominales) {
		this.cantidadNominales = cantidadNominales;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio
	 *            the new precio
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the codigo tipo especie.
	 *
	 * @return the codigo tipo especie
	 */
	public String getCodigoTipoEspecie() {
		return codigoTipoEspecie;
	}

	/**
	 * Sets the codigo tipo especie.
	 *
	 * @param codigoTipoEspecie
	 *            the new codigo tipo especie
	 */
	public void setCodigoTipoEspecie(String codigoTipoEspecie) {
		this.codigoTipoEspecie = codigoTipoEspecie;
	}

	/**
	 * Gets the fecha debito.
	 *
	 * @return the fecha debito
	 */
	public String getFechaDebito() {
		return fechaDebito;
	}

	/**
	 * Sets the fecha debito.
	 *
	 * @param fechaDebito
	 *            the new fecha debito
	 */
	public void setFechaDebito(String fechaDebito) {
		this.fechaDebito = fechaDebito;
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
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
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
	 * Gets the importe debitar.
	 *
	 * @return the importe debitar
	 */
	public String getImporteDebitar() {
		return importeDebitar;
	}

	/**
	 * Sets the importe debitar.
	 *
	 * @param importeDebitar
	 *            the new importe debitar
	 */
	public void setImporteDebitar(String importeDebitar) {
		this.importeDebitar = importeDebitar;
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
	 * Gets the precio adjudicado.
	 *
	 * @return the precio adjudicado
	 */
	public Double getPrecioAdjudicado() {
		return precioAdjudicado;
	}

	/**
	 * Sets the precio adjudicado.
	 *
	 * @param precioAdjudicado
	 *            the new precio adjudicado
	 */
	public void setPrecioAdjudicado(Double precioAdjudicado) {
		this.precioAdjudicado = precioAdjudicado;
	}

	/**
	 * Gets the precio string.
	 *
	 * @return the precio string
	 */
	public String getPrecioString() {
		return precioString;
	}

	/**
	 * Sets the precio string.
	 *
	 * @param precioString
	 *            the new precio string
	 */
	public void setPrecioString(String precioString) {
		this.precioString = precioString;
	}

	/**
	 * Gets the precio adjudicado string.
	 *
	 * @return the precio adjudicado string
	 */
	public String getPrecioAdjudicadoString() {
		return precioAdjudicadoString;
	}

	/**
	 * Sets the precio adjudicado string.
	 *
	 * @param precioAdjudicadoString
	 *            the new precio adjudicado string
	 */
	public void setPrecioAdjudicadoString(String precioAdjudicadoString) {
		this.precioAdjudicadoString = precioAdjudicadoString;
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
	 * Gets the tipo codigo.
	 *
	 * @return the tipo codigo
	 */
	public String getTipoCodigo() {
		return tipoCodigo;
	}

	/**
	 * Sets the tipo codigo.
	 *
	 * @param tipoCodigo
	 *            the new tipo codigo
	 */
	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

    /**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
    public Double getDerechos() {
        return derechos;
    }

    /**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the new derechos
	 */
    public void setDerechos(Double derechos) {
        this.derechos = derechos;
    }

	public String getTipoPliego() {
		return tipoPliego;
	}

	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

	public String getMonedaEspecieDestino() {
		return monedaEspecieDestino;
	}

	public void setMonedaEspecieDestino(String monedaEspecieDestino) {
		this.monedaEspecieDestino = monedaEspecieDestino;
	}

	public String getTipoPrecioDatoCanje() {
		return tipoPrecioDatoCanje;
	}

	public void setTipoPrecioDatoCanje(String tipoPrecioDatoCanje) {
		this.tipoPrecioDatoCanje = tipoPrecioDatoCanje;
	}

	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}
	
	
}
