/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaView.
 */
public class CuentaView implements Cloneable, Comparable<CuentaView>{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaView.class);
	
	/** The id. */
	private	Integer id;
	
	/** The numero. */
	private String numero;
	
	/** The saldoPesos. */
	private String saldoPesos;
	
	/** The descripcionTipoCuenta. */
	private String descripcionTipoCuenta;
	
	/** The alias. */
	private String alias;
	
	/** The isSaldoPesosNegativo. */
	private boolean isSaldoPesosNegativo;
	
	private String saldoPesosSinFormato;
	
	private Boolean saldoInsuficiente = Boolean.FALSE;
	

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
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}


	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripciontipoCuenta
	 *            the descripciontipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripciontipoCuenta) {
		this.descripcionTipoCuenta = descripciontipoCuenta;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the isSaldoPesosNegativo
	 */
	public boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
	public void setIsSaldoPesosNegativo(boolean isSaldoPesosNegativo) {
		this.isSaldoPesosNegativo = isSaldoPesosNegativo;
	}
	
	
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSaldoPesosSinFormato() {
		return saldoPesosSinFormato;
	}

	public void setSaldoPesosSinFormato(String saldoPesosSinFormato) {
		this.saldoPesosSinFormato = saldoPesosSinFormato;
	}
	
	public Boolean getSaldoInsuficiente() {
		return saldoInsuficiente;
	}

	public void setSaldoInsuficiente(Boolean saldoInsuficiente) {
		this.saldoInsuficiente = saldoInsuficiente;
	}

	/**
	 * Clone.
	 *
	 * @return the cuenta view
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public CuentaView clone() {
		try {
			return (CuentaView) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}

	@Override
	public int compareTo(CuentaView o) {

        BigDecimal montoPrimero = new BigDecimal(this.saldoPesosSinFormato);
        BigDecimal montoParaComparar = new BigDecimal(o.saldoPesosSinFormato);
		
		if (montoPrimero.compareTo(montoParaComparar) == (1)) {
            return 1;
        }
        if (montoPrimero.compareTo(montoParaComparar) == (-1)) {
            return -1;
        }
        return 0;
		
	}
	
}
