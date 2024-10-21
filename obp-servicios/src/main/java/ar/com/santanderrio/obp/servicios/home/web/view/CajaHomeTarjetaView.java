/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

/**
 * The Class CajaHomeTarjetaView.
 */
public class CajaHomeTarjetaView extends Caja {

	/** The is tarjeta. */
	private Boolean isTarjeta;

	/** The encabezado. */
	private String encabezado;

	/** The titulo. */
	private String titulo;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo pesos value. */
	private Double saldoPesosValue;

	/** The id. */
	private String id;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The saldo dolares value. */
	private Double saldoDolaresValue;

	/** The fecha de cierre. */
	private String fechaDeCierre;

	/** The fecha de vencimiento. */
	private String fechaDeVencimiento;
	
	/** The texto link. */
	private String textoLink;
	
	private boolean isRecargable;
	
	private String producto;
	
	private String subProducto;

	/**
	 * Gets the checks if is tarjeta.
	 *
	 * @return the checks if is tarjeta
	 */
	public Boolean getIsTarjeta() {
		return isTarjeta;
	}

	/**
	 * Sets the checks if is tarjeta.
	 *
	 * @param isTarjeta
	 *            the new checks if is tarjeta
	 */
	public void setIsTarjeta(Boolean isTarjeta) {
		this.isTarjeta = isTarjeta;
	}

	/**
	 * Gets the encabezado.
	 *
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
	 * Sets the encabezado.
	 *
	 * @param encabezado
	 *            the new encabezado
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo pesos value.
	 *
	 * @return the saldo pesos value
	 */
	public Double getSaldoPesosValue() {
		return saldoPesosValue;
	}

	/**
	 * Sets the saldo pesos value.
	 *
	 * @param saldoPesosValue
	 *            the new saldo pesos value
	 */
	public void setSaldoPesosValue(Double saldoPesosValue) {
		this.saldoPesosValue = saldoPesosValue;
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
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the saldo dolares value.
	 *
	 * @return the saldo dolares value
	 */
	public Double getSaldoDolaresValue() {
		return saldoDolaresValue;
	}

	/**
	 * Sets the saldo dolares value.
	 *
	 * @param saldoDolaresValue
	 *            the new saldo dolares value
	 */
	public void setSaldoDolaresValue(Double saldoDolaresValue) {
		this.saldoDolaresValue = saldoDolaresValue;
	}

	/**
	 * Gets the fecha de cierre.
	 *
	 * @return the fecha de cierre
	 */
	public String getFechaDeCierre() {
		return fechaDeCierre;
	}

	/**
	 * Sets the fecha de cierre.
	 *
	 * @param fechaDeCierre
	 *            the new fecha de cierre
	 */
	public void setFechaDeCierre(String fechaDeCierre) {
		this.fechaDeCierre = fechaDeCierre;
	}

	/**
	 * Gets the fecha de vencimiento.
	 *
	 * @return the fecha de vencimiento
	 */
	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	/**
	 * Sets the fecha de vencimiento.
	 *
	 * @param fechaDeVencimiento
	 *            the new fecha de vencimiento
	 */
	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

    /**
	 * Gets the texto link.
	 *
	 * @return the texto link
	 */
    public String getTextoLink() {
        return textoLink;
    }

    /**
	 * Sets the texto link.
	 *
	 * @param textoLink
	 *            the new texto link
	 */
    public void setTextoLink(String textoLink) {
        this.textoLink = textoLink;
    }

	public boolean isRecargable() {
		return isRecargable;
	}

	public void setRecargable(boolean isRecargable) {
		this.isRecargable = isRecargable;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubProducto() {
		return subProducto;
	}

	public void setSubProducto(String subProducto) {
		this.subProducto = subProducto;
	}

}
