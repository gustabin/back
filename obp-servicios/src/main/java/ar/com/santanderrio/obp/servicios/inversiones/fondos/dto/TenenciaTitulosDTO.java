/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Class TenenciaTitulosDTO.
 */
public class TenenciaTitulosDTO {

    /** The tipo. */
    private String tipo;

    /** The descripcion. */
    private String descripcion;
    
    /** the codigo. */
    private String codigoTipoProducto;

    /** The producto. */
    private String producto;

    /** The cantidad valor nominal. */
    private Double cantidadValorNominal;

    /** The precio mercado. */
    private Double precioMercado;

    /** The tenencia valuada. */
    private Double tenenciaValuada;

    /** The resultado. */
    private Double resultado;

    /** The codigoEspecieMercado. */
    private String codigoEspecieMercado;

    /** The fechaUltimaCotizacion. */
    private String fechaUltimaCotizacion;

    /** The precioPromedioDeCompra. */
    private String precioPromedioDeCompra;

    /** The valuacionAlCosto. */
    private String valuacionAlCosto;

    /** The rentasCobradas. */
    private String rentasCobradas;

    /** The amortizacionesCobradas. */
    private String amortizacionesCobradas;

    /** The estado. */
    private String estado;
    
    /** The codigoProducto. */
    private String codigoProducto;
    
    /** The dividendosCobrados. */
    private String dividendosCobrados;
    
    /** The codigo rossi. */
    private String codigoRossi;
    
    /** The habilitar compra venta. */
    private Boolean habilitarCompraVenta;
    
    private String CodigoEstadoTenencia; 

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
     * Gets the cantidad valor nominal.
     *
     * @return the cantidad valor nominal
     */
    public Double getCantidadValorNominal() {
        return cantidadValorNominal;
    }

    /**
     * Sets the cantidad valor nominal.
     *
     * @param cantidadValorNominal
     *            the new cantidad valor nominal
     */
    public void setCantidadValorNominal(Double cantidadValorNominal) {
        this.cantidadValorNominal = cantidadValorNominal;
    }

    /**
     * Gets the precio mercado.
     *
     * @return the precio mercado
     */
    public Double getPrecioMercado() {
        return precioMercado;
    }

    /**
     * Sets the precio mercado.
     *
     * @param precioMercado
     *            the new precio mercado
     */
    public void setPrecioMercado(Double precioMercado) {
        this.precioMercado = precioMercado;
    }

    /**
     * Gets the tenencia valuada.
     *
     * @return the tenencia valuada
     */
    public Double getTenenciaValuada() {
        return tenenciaValuada;
    }

    /**
     * Sets the tenencia valuada.
     *
     * @param tenenciaValuada
     *            the new tenencia valuada
     */
    public void setTenenciaValuada(Double tenenciaValuada) {
        this.tenenciaValuada = tenenciaValuada;
    }

    /**
     * Gets the resultado.
     *
     * @return the resultado
     */
    public Double getResultado() {
        return resultado;
    }

    /**
     * Sets the resultado.
     *
     * @param resultado
     *            the new resultado
     */
    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    /**
     * Gets the producto.
     *
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Sets the producto.
     *
     * @param producto
     *            the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
	 * Gets the codigo tipo producto.
	 *
	 * @return the codigo tipo producto
	 */
    public String getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    /**
	 * Sets the codigo tipo producto.
	 *
	 * @param codigoTipoProducto
	 *            the new codigo tipo producto
	 */
    public void setCodigoTipoProducto(String codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    /**
	 * Gets the codigo especie mercado.
	 *
	 * @return the codigo especie mercado
	 */
    public String getCodigoEspecieMercado() {
        return codigoEspecieMercado;
    }

    /**
	 * Sets the codigo especie mercado.
	 *
	 * @param codigoEspecieMercado
	 *            the new codigo especie mercado
	 */
    public void setCodigoEspecieMercado(String codigoEspecieMercado) {
        this.codigoEspecieMercado = codigoEspecieMercado;
    }

    /**
	 * Gets the fecha ultima cotizacion.
	 *
	 * @return the fecha ultima cotizacion
	 */
    public String getFechaUltimaCotizacion() {
        return fechaUltimaCotizacion;
    }

    /**
	 * Sets the fecha ultima cotizacion.
	 *
	 * @param fechaUltimaCotizacion
	 *            the new fecha ultima cotizacion
	 */
    public void setFechaUltimaCotizacion(String fechaUltimaCotizacion) {
        this.fechaUltimaCotizacion = fechaUltimaCotizacion;
    }

    /**
	 * Gets the precio promedio de compra.
	 *
	 * @return the precio promedio de compra
	 */
    public String getPrecioPromedioDeCompra() {
        return precioPromedioDeCompra;
    }

    /**
	 * Sets the precio promedio de compra.
	 *
	 * @param precioPromedioDeCompra
	 *            the new precio promedio de compra
	 */
    public void setPrecioPromedioDeCompra(String precioPromedioDeCompra) {
        this.precioPromedioDeCompra = precioPromedioDeCompra;
    }

    /**
	 * Gets the valuacion al costo.
	 *
	 * @return the valuacion al costo
	 */
    public String getValuacionAlCosto() {
        return valuacionAlCosto;
    }

    /**
	 * Sets the valuacion al costo.
	 *
	 * @param valuacionAlCosto
	 *            the new valuacion al costo
	 */
    public void setValuacionAlCosto(String valuacionAlCosto) {
        this.valuacionAlCosto = valuacionAlCosto;
    }

    /**
	 * Gets the rentas cobradas.
	 *
	 * @return the rentas cobradas
	 */
    public String getRentasCobradas() {
        return rentasCobradas;
    }

    /**
	 * Sets the rentas cobradas.
	 *
	 * @param rentasCobradas
	 *            the new rentas cobradas
	 */
    public void setRentasCobradas(String rentasCobradas) {
        this.rentasCobradas = rentasCobradas;
    }

    /**
	 * Gets the amortizaciones cobradas.
	 *
	 * @return the amortizaciones cobradas
	 */
    public String getAmortizacionesCobradas() {
        return amortizacionesCobradas;
    }

    /**
	 * Sets the amortizaciones cobradas.
	 *
	 * @param amortizacionesCobradas
	 *            the new amortizaciones cobradas
	 */
    public void setAmortizacionesCobradas(String amortizacionesCobradas) {
        this.amortizacionesCobradas = amortizacionesCobradas;
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
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
	 * Gets the dividendos cobrados.
	 *
	 * @return the dividendos cobrados
	 */
    public String getDividendosCobrados() {
        return dividendosCobrados;
    }

    /**
	 * Sets the dividendos cobrados.
	 *
	 * @param dividendosCobrados
	 *            the new dividendos cobrados
	 */
    public void setDividendosCobrados(String dividendosCobrados) {
        this.dividendosCobrados = dividendosCobrados;
    }

	/**
	 * Gets the codigo rossi.
	 *
	 * @return the codigo rossi
	 */
	public String getCodigoRossi() {
		return codigoRossi;
	}

	/**
	 * Sets the codigo rossi.
	 *
	 * @param codigoRossi
	 *            the new codigo rossi
	 */
	public void setCodigoRossi(String codigoRossi) {
		this.codigoRossi = codigoRossi;
	}

	/**
	 * Gets the habilitar compra venta.
	 *
	 * @return the habilitar compra venta
	 */
	public Boolean getHabilitarCompraVenta() {
		return habilitarCompraVenta;
	}

	/**
	 * Sets the habilitar compra venta.
	 *
	 * @param habilitarCompraVenta
	 *            the new habilitar compra venta
	 */
	public void setHabilitarCompraVenta(Boolean habilitarCompraVenta) {
		this.habilitarCompraVenta = habilitarCompraVenta;
	}

    public String getCodigoEstadoTenencia() {
		return CodigoEstadoTenencia;
	}

	public void setCodigoEstadoTenencia(String codigoEstadoTenencia) {
		CodigoEstadoTenencia = codigoEstadoTenencia;
	}
}