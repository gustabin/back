/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class CuentaSaldoInEntity.
 */
public class CuentaSaldoInEntity {

	/** The Constant CANAL_OPERACION. */
	private static final String CANAL_OPERACION = "79";
	/** The cuenta. */
	private String cuenta;

	/** The fecha desde. */
	private Date fechaDesde;

	/** The fecha hasta. */
	private Date fechaHasta;

	/** The canal. */
	private String canal;

	/** The usuario. */
	private String usuario;

	/** The pass. */
	private String pass;

	/**
	 * Instantiates a new cuenta saldo in entity.
	 */
	public CuentaSaldoInEntity() {
		this.canal = CANAL_OPERACION;
		this.fechaDesde = new Date();
		this.fechaHasta = new Date();
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
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass
	 *            the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuentaSaldoInEntity [cuenta=" + cuenta + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta
				+ ", canal=" + canal + ", usuario=" + usuario + ", pass=" + pass + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(canal);
		hcb.append(cuenta);
		hcb.append(fechaDesde);
		hcb.append(fechaHasta);
		hcb.append(usuario);
		hcb.append(pass);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CuentaSaldoInEntity other = (CuentaSaldoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(canal, other.canal);
		eb.append(cuenta, other.cuenta);
		eb.append(fechaDesde, other.fechaDesde);
		eb.append(fechaHasta, other.fechaHasta);
		eb.append(usuario, other.usuario);
		eb.append(pass, other.pass);
		return eb.isEquals();
	}
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		

		Date fecha = ISBANStringUtils.formatearFecha("30-01-2018", "dd-MM-yyyy");
		
		System.out.println(fecha.toString());
		
		
	}

}
