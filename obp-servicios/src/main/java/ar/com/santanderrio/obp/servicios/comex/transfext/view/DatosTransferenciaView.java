/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;


/**
 * The Class DatosTransferenciaView.
 *
 * @author IT Resources
 */
public class DatosTransferenciaView {
	
	/** The concepto codigo. */
	private String conceptoCodigo;
	
	/** The concepto descripcion. */
	private String conceptoDescripcion;

	/** The concepto id. */
	private String conceptoID;
	
	/** The origen. */
	private String origen;
	
	/** The destino. */
	private String destino;
	
	/** The gasto a cargo. */
	private String gastoACargo;
	
	/** The descripcion banco . */
	private String descripcionBanco;

	/** The codigo banco. SWIFT O ABA*/
	private String codigoBanco;
	
	/** The descripcion banco intermediario. */
	private String descripcionBancoIntermediario;

	/** The codigo banco intermediario SWIFT O ABA. */
	private String codigoBancoIntermediario;
	
	/** The destino banco intermediario. */
	private String destinoBancoIntermediario;
	
	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;
	
	/** The id cuenta origen. */
	private Integer idCuentaOrigen;
	
	/** The banco obligatorio. */
	private BancoOutView bancoObligatorio;
	
	/** The banco intermediario. */
	private BancoOutView bancoIntermediario;
	
	/** The banco moneda. */
	private String moneda;

	private String descripcionTipoCuenta;
	
	/**
	 * Gets the concepto codigo.
	 *
	 * @return the conceptoCodigo
	 */
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * Sets the concepto codigo.
	 *
	 * @param conceptoCodigo
	 *            the conceptoCodigo to set
	 */
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

	/**
	 * Gets the concepto descripcion.
	 *
	 * @return the conceptoDescripcion
	 */
	public String getConceptoDescripcion() {
		return conceptoDescripcion;
	}

	/**
	 * Sets the concepto descripcion.
	 *
	 * @param conceptoDescripcion
	 *            the conceptoDescripcion to set
	 */
	public void setConceptoDescripcion(String conceptoDescripcion) {
		this.conceptoDescripcion = conceptoDescripcion;
	}

	/**
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Gets the destino.
	 *
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Sets the destino.
	 *
	 * @param destino
	 *            the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * Gets the gasto A cargo.
	 *
	 * @return the gastoACargo
	 */
	public String getGastoACargo() {
		return gastoACargo;
	}

	/**
	 * Sets the gasto A cargo.
	 *
	 * @param gastoACargo
	 *            the gastoACargo to set
	 */
	public void setGastoACargo(String gastoACargo) {
		this.gastoACargo = gastoACargo;
	}

	/**
	 * Gets the descripcion banco.
	 *
	 * @return the descripcionBanco
	 */
	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	/**
	 * Sets the descripcion banco.
	 *
	 * @param descripcionBanco
	 *            the descripcionBanco to set
	 */
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	/**
	 * Gets the codigo banco.
	 *
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Sets the codigo banco.
	 *
	 * @param codigoBanco
	 *            the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Gets the descripcion banco intermediario.
	 *
	 * @return the descripcionBancoIntermediario
	 */
	public String getDescripcionBancoIntermediario() {
		return descripcionBancoIntermediario;
	}

	/**
	 * Sets the descripcion banco intermediario.
	 *
	 * @param descripcionBancoIntermediario
	 *            the descripcionBancoIntermediario to set
	 */
	public void setDescripcionBancoIntermediario(String descripcionBancoIntermediario) {
		this.descripcionBancoIntermediario = descripcionBancoIntermediario;
	}

	/**
	 * Gets the codigo banco intermediario.
	 *
	 * @return the codigoBancoIntermediario
	 */
	public String getCodigoBancoIntermediario() {
		return codigoBancoIntermediario;
	}

	/**
	 * Sets the codigo banco intermediario.
	 *
	 * @param codigoBancoIntermediario
	 *            the codigoBancoIntermediario to set
	 */
	public void setCodigoBancoIntermediario(String codigoBancoIntermediario) {
		this.codigoBancoIntermediario = codigoBancoIntermediario;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipoCuentaOrigen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the tipoCuentaOrigen to set
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the concepto ID.
	 *
	 * @return the conceptoID
	 */
	public String getConceptoID() {
		return conceptoID;
	}

	/**
	 * Sets the concepto ID.
	 *
	 * @param conceptoID
	 *            the conceptoID to set
	 */
	public void setConceptoID(String conceptoID) {
		this.conceptoID = conceptoID;
	}

	/**
	 * Gets the id cuenta origen.
	 *
	 * @return the idCuentaOrigen
	 */
	public Integer getIdCuentaOrigen() {
		return idCuentaOrigen;
	}

	/**
	 * Sets the id cuenta origen.
	 *
	 * @param idCuentaOrigen
	 *            the idCuentaOrigen to set
	 */
	public void setIdCuentaOrigen(Integer idCuentaOrigen) {
		this.idCuentaOrigen = idCuentaOrigen;
	}

	/**
	 * Gets the destino banco intermediario.
	 *
	 * @return the destinoBancoIntermediario
	 */
	public String getDestinoBancoIntermediario() {
		return destinoBancoIntermediario;
	}

	/**
	 * Sets the destino banco intermediario.
	 *
	 * @param destinoBancoIntermediario
	 *            the destinoBancoIntermediario to set
	 */
	public void setDestinoBancoIntermediario(String destinoBancoIntermediario) {
		this.destinoBancoIntermediario = destinoBancoIntermediario;
	}

	/**
	 * Gets the banco obligatorio.
	 *
	 * @return the bancoObligatorio
	 */
	public BancoOutView getBancoObligatorio() {
		return bancoObligatorio;
	}

	/**
	 * Sets the banco obligatorio.
	 *
	 * @param bancoObligatorio
	 *            the bancoObligatorio to set
	 */
	public void setBancoObligatorio(BancoOutView bancoObligatorio) {
		this.bancoObligatorio = bancoObligatorio;
	}

	/**
	 * Gets the banco intermediario.
	 *
	 * @return the bancoIntermediario
	 */
	public BancoOutView getBancoIntermediario() {
		return bancoIntermediario;
	}

	/**
	 * Sets the banco intermediario.
	 *
	 * @param bancoIntermediario
	 *            the bancoIntermediario to set
	 */
	public void setBancoIntermediario(BancoOutView bancoIntermediario) {
		this.bancoIntermediario = bancoIntermediario;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * @param descripcionTipoCuenta the descripcionTipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	
	
}
