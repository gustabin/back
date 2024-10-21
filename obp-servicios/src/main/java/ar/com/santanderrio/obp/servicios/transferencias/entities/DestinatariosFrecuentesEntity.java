package ar.com.santanderrio.obp.servicios.transferencias.entities;

public class DestinatariosFrecuentesEntity {

	private Integer id;
	private String nup;
	private String tipoCuenta;
	private String sucursal;
	private String nroCuenta;
	private String cbu;

	public DestinatariosFrecuentesEntity() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

}
