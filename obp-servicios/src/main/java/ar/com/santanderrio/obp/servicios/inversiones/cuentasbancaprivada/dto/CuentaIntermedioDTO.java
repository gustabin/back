/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;

/**
 * The Class CuentaIntermedioDTO.
 */
public class CuentaIntermedioDTO {

	/** The saldos stored procedure. */
	private CuentaSaldoOutEntity saldosStoredProcedure;
	
	/** The saldos servicio iatx. */
	private ConsultaSaldoCtasConAperturaOutEntity saldosServicioIatx;
	
	/** The is cuenta unica. */
	private Boolean isCuentaUnica;
	
	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;
	
	/** The apodo. */
	private String apodo;
	
	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The cbu. */
	private String cbu;

	/** The isCuentaTransaccional. */
	private Boolean isCuentaTransaccional;

	
	/**
	 * Gets the saldos stored procedure.
	 *
	 * @return the saldos stored procedure
	 */
	public CuentaSaldoOutEntity getSaldosStoredProcedure() {
		return saldosStoredProcedure;
	}

	/**
	 * Sets the saldos stored procedure.
	 *
	 * @param saldosStoredProcedure
	 *            the new saldos stored procedure
	 */
	public void setSaldosStoredProcedure(CuentaSaldoOutEntity saldosStoredProcedure) {
		this.saldosStoredProcedure = saldosStoredProcedure;
	}

	/**
	 * Gets the saldos servicio iatx.
	 *
	 * @return the saldos servicio iatx
	 */
	public ConsultaSaldoCtasConAperturaOutEntity getSaldosServicioIatx() {
		return saldosServicioIatx;
	}

	/**
	 * Sets the saldos servicio iatx.
	 *
	 * @param saldosServicioIatx
	 *            the new saldos servicio iatx
	 */
	public void setSaldosServicioIatx(ConsultaSaldoCtasConAperturaOutEntity saldosServicioIatx) {
		this.saldosServicioIatx = saldosServicioIatx;
	}

	/**
	 * Gets the checks if is cuenta unica.
	 *
	 * @return the checks if is cuenta unica
	 */
	public Boolean getIsCuentaUnica() {
		return isCuentaUnica;
	}

	/**
	 * Sets the checks if is cuenta unica.
	 *
	 * @param isCuentaUnica
	 *            the new checks if is cuenta unica
	 */
	public void setIsCuentaUnica(Boolean isCuentaUnica) {
		this.isCuentaUnica = isCuentaUnica;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the apodo.
	 *
	 * @return the apodo
	 */
	public String getApodo() {
		return apodo;
	}

	/**
	 * Sets the apodo.
	 *
	 * @param apodo
	 *            the new apodo
	 */
	public void setApodo(String apodo) {
		this.apodo = apodo;
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
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}	
	
	
	public Boolean getIsCuentaTransaccional() {
        return isCuentaTransaccional;
    }

	public void setIsCuentaTransacional(Boolean isCuentaTransaccional) {
	    this.isCuentaTransaccional =  isCuentaTransaccional;
	}

	
}