/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

/**
 * The Class ConsultarOrdenesDTO.
 */
public class ConsultarOrdenesDTO implements Comparable<ConsultarOrdenesDTO> {

	/** The descripcion. */
	private String descripcion;

	/** The fecha. */
	private String fecha;

	/** The fecha hora comprobante. */
	private String fechaHoraComprobante;

	/** The numero orden. */
	private String numeroOrden;

	/** The estado. */
	private String estado;

	/** The moneda. */
	private String moneda;

	/** The importe. */
	private String importe;

	/** The cantidad nominal. */
	private String cantidadNominal;

	/** The moneda especie. */
	private String monedaEspecie;

	/** The precio. */
	private String precio;

	/** The cuenta titulo. */
	private String cuentaTitulo;

	/** The tramo. */
	private String tramo;

	/** The importe debitar. */
	private String importeDebitar;

	/** The cuenta debito. */
	private String cuentaDebito;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The tipo precio label. */
	private String tipoPrecioLabel;

	/** The tipo precio adjudicada label. */
	private String tipoPrecioAdjudicadaLabel;

	/** The tipo cambio. */
	private String tipoCambio;

	/** The cantidad adjudicada. */
	private String cantidadAdjudicada;

	/** The precio adjudicada. */
	private String precioAdjudicada;

	/** The fecha liquidacion. */
	private String fechaLiquidacion;

	/** The comision. */
	private double comision;

	/** The impuesto. */
	private double impuesto;

	/** The permite reversa. */
	private boolean permiteReversa = false;

	/** The fecha adjudicacion. */
	private String fechaAdjudicacion;

	/** The fecha cierre. */
	private String fechaCierre;

	/** The legal pliegue. */
	private String legalPliegue;

	/** The legal canal. */
	private String legalCanal;
	
	/** The cod instrumento. */
	private String codInstrumento;
	
	/** The desc instrumento. */
	private String descInstrumento;
	
	/** The sucursal. */
	private String sucursal;
	
	/** The cuenta operativa. */
	private String cuentaOperativa;
	
	/** The fecha deb prov. */
	private String fechaDebProv;
	
	/** The tipo de orden. */
	private String tipoDeOrden;
	
	private String tipoPliego;
	
	private String especieDestino;
	
	private String monedaEspecieDestino;
	
	private String relacionDerechosCanje;

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
	 * @param sucursal
	 *            the new sucursal
	 */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
    public void setCuentaOperativa(String cuentaOperativa) {
        this.cuentaOperativa = cuentaOperativa;
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
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
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
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaOperativa) {
		this.cuentaTitulo = cuentaOperativa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ConsultarOrdenesDTO o) {

		if (Integer.parseInt(numeroOrden) < Integer.parseInt(o.numeroOrden)) {
			return 1;
		}
		
		if (Integer.parseInt(numeroOrden) > Integer.parseInt(o.numeroOrden)) {
			return -1;
		}
		
		return 0;
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
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
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
	 * @param cuentaAdjudicada
	 *            the new cantidad adjudicada
	 */
	public void setCantidadAdjudicada(String cuentaAdjudicada) {
		this.cantidadAdjudicada = cuentaAdjudicada;
	}

	/**
	 * Gets the precio adjudicada.
	 *
	 * @return the precio adjudicada
	 */
	public String getPrecioAdjudicada() {
		return precioAdjudicada;
	}

	/**
	 * Sets the precio adjudicada.
	 *
	 * @param precioAdjudicada
	 *            the new precio adjudicada
	 */
	public void setPrecioAdjudicada(String precioAdjudicada) {
		this.precioAdjudicada = precioAdjudicada;
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
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public double getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the new comision
	 */
	public void setComision(double comision) {
		this.comision = comision;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public double getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the new impuesto
	 */
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * Gets the cantidad nominal.
	 *
	 * @return the cantidad nominal
	 */
	public String getCantidadNominal() {
		return cantidadNominal;
	}

	/**
	 * Sets the cantidad nominal.
	 *
	 * @param cantidadNominal
	 *            the new cantidad nominal
	 */
	public void setCantidadNominal(String cantidadNominal) {
		this.cantidadNominal = cantidadNominal;
	}

	/**
	 * Checks if is permite reversa.
	 *
	 * @return true, if is permite reversa
	 */
	public boolean isPermiteReversa() {
		return permiteReversa;
	}

	/**
	 * Sets the permite reversa.
	 *
	 * @param permiteReversa
	 *            the new permite reversa
	 */
	public void setPermiteReversa(boolean permiteReversa) {
		this.permiteReversa = permiteReversa;
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
	 * Gets the legal pliegue.
	 *
	 * @return the legal pliegue
	 */
	public String getLegalPliegue() {
		return legalPliegue;
	}

	/**
	 * Sets the legal pliegue.
	 *
	 * @param legalPliegue
	 *            the new legal pliegue
	 */
	public void setLegalPliegue(String legalPliegue) {
		this.legalPliegue = legalPliegue;
	}

	/**
	 * Gets the legal canal.
	 *
	 * @return the legal canal
	 */
	public String getLegalCanal() {
		return legalCanal;
	}

	/**
	 * Sets the legal canal.
	 *
	 * @param legalCanal
	 *            the new legal canal
	 */
	public void setLegalCanal(String legalCanal) {
		this.legalCanal = legalCanal;
	}

	/**
	 * Gets the tipo precio label.
	 *
	 * @return the tipo precio label
	 */
	public String getTipoPrecioLabel() {
		return tipoPrecioLabel;
	}

	/**
	 * Sets the tipo precio label.
	 *
	 * @param tipoPrecio
	 *            the new tipo precio label
	 */
	public void setTipoPrecioLabel(String tipoPrecio) {
		this.tipoPrecioLabel = tipoPrecio;
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
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fecha hora comprobante
	 */
	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the new fecha hora comprobante
	 */
	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	/**
	 * Gets the tipo precio adjudicada label.
	 *
	 * @return the tipo precio adjudicada label
	 */
	public String getTipoPrecioAdjudicadaLabel() {
		return tipoPrecioAdjudicadaLabel;
	}

	/**
	 * Sets the tipo precio adjudicada label.
	 *
	 * @param tipoPrecioAdjudicadaLabel
	 *            the new tipo precio adjudicada label
	 */
	public void setTipoPrecioAdjudicadaLabel(String tipoPrecioAdjudicadaLabel) {
		this.tipoPrecioAdjudicadaLabel = tipoPrecioAdjudicadaLabel;
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
	 * @param codInstrumento
	 *            the new cod instrumento
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
	 * @param descInstrumento
	 *            the new desc instrumento
	 */
    public void setDescInstrumento(String descInstrumento) {
        this.descInstrumento = descInstrumento;
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
	 * @param fechaDebProv
	 *            the new fecha deb prov
	 */
    public void setFechaDebProv(String fechaDebProv) {
        this.fechaDebProv = fechaDebProv;
    }

    /**
	 * Gets the tipo de orden.
	 *
	 * @return the tipo de orden
	 */
    public String getTipoDeOrden() {
        return tipoDeOrden;
    }

    /**
	 * Sets the tipo de orden.
	 *
	 * @param tipoDeOrden
	 *            the new tipo de orden
	 */
    public void setTipoDeOrden(String tipoDeOrden) {
        this.tipoDeOrden = tipoDeOrden;
    }

	public String getTipoPliego() {
		return tipoPliego;
	}

	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	public String getMonedaEspecieDestino() {
		return monedaEspecieDestino;
	}

	public void setMonedaEspecieDestino(String monedaEspecieDestino) {
		this.monedaEspecieDestino = monedaEspecieDestino;
	}

	public String getRelacionDerechosCanje() {
		return relacionDerechosCanje;
	}

	public void setRelacionDerechosCanje(String relacionDerechosCanje) {
		this.relacionDerechosCanje = relacionDerechosCanje;
	}
	
}
