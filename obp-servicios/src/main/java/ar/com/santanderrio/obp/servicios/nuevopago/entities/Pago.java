/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.util.Date;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class Pago.
 *
 * @author marcelo.ruiz
 */
public class Pago extends RsaDTO {

	/**
	 * Instantiates a new pago.
	 */
	public Pago() {
		super(OperacionesRSAEnum.NUEVO_PAGO);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2792183162909839151L;

	/** The amount. */
	private String amount;

	/** The divisa. */
	private DivisaEnum divisa;

	/** Fecha de pago. */
	private Date deliveryDate;

	/** fecha de la factura si es que tiene. */
	private Date dueDate;

	/** The account number. */
	private String accountNumber;

	/** The account type. */
	private String accountType;
	
	/** The saldo cuenta origen. */
	private String saldoCuentaOrigen;

	/** The account nick name origen. */
	private String accountNickNameOrigen;
	
	/** The account nick name destino. */
	private String accountNickNameDestino;
	
	/** The cbu. */
	private String CBU;
	
	/** Se usa en RSA. */
    private Integer cantDiasUltimoCambioClave;
    
    /** Se usa en RSA. */
    private Integer cantDiasUltimoCambioToken;
    
	private Boolean tieneCelularMyA = Boolean.FALSE;
	
	private String nombreFantasia;
	
	private String identificacionCliente;
	
	private String numeroCuentaSinFormato;
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the delivery date.
	 *
	 * @return the delivery date
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Sets the delivery date.
	 *
	 * @param deliveryDate
	 *            the new delivery date
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Sets the due date.
	 *
	 * @param dueDate
	 *            the new due date
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber
	 *            the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 *
	 * @param accountType
	 *            the new account type
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldoCuentaOrigen
	 */
	public String getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the saldoCuentaOrigen to set
	 */
	public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	/**
	 * Gets the account nick name origen.
	 *
	 * @return the accountNickNameOrigen
	 */
	public String getAccountNickNameOrigen() {
		return accountNickNameOrigen;
	}

	/**
	 * Sets the account nick name origen.
	 *
	 * @param accountNickNameOrigen
	 *            the accountNickNameOrigen to set
	 */
	public void setAccountNickNameOrigen(String accountNickNameOrigen) {
		this.accountNickNameOrigen = accountNickNameOrigen;
	}

	/**
	 * Gets the account nick name destino.
	 *
	 * @return the accountNickNameDestino
	 */
	public String getAccountNickNameDestino() {
		return accountNickNameDestino;
	}

	/**
	 * Sets the account nick name destino.
	 *
	 * @param accountNickNameDestino
	 *            the accountNickNameDestino to set
	 */
	public void setAccountNickNameDestino(String accountNickNameDestino) {
		this.accountNickNameDestino = accountNickNameDestino;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cBU
	 */
	public String getCBU() {
		return CBU;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cBU
	 *            the cBU to set
	 */
	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

	public Boolean getTieneCelularMyA() {
		return tieneCelularMyA;
	}

	public void setTieneCelularMyA(Boolean tieneCelularMyA) {
		this.tieneCelularMyA = tieneCelularMyA;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getNumeroCuentaSinFormato() {
		return numeroCuentaSinFormato;
	}

	public void setNumeroCuentaSinFormato(String numeroCuentaSinFormato) {
		this.numeroCuentaSinFormato = numeroCuentaSinFormato;
	}
		
}
