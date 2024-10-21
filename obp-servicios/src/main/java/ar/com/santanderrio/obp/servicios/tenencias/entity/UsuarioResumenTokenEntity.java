/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class UsuarioResumenTokenEntity.
 */
@XmlRootElement()
@XmlType(propOrder = {"tipoDocumento", "nroDocumento", "nroTarjeta"})
public class UsuarioResumenTokenEntity {
	
	/** The tipo documento. */
	private String tipoDocumento;
	
	/** The nro documento. */
	private String nroDocumento;
	
	/** The nro tarjeta. */
	private String nroTarjeta;

	/**
	 * Instantiates a new usuario resumen token entity.
	 */
	public UsuarioResumenTokenEntity() {
		super();
	}
	
	/**
	 * Instantiates a new usuario resumen token entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoDoc
	 *            the tipo doc
	 */
	public UsuarioResumenTokenEntity(Cliente cliente, String tipoDoc) {
		tipoDocumento = tipoDoc;
		nroDocumento = cliente.getDni();
		for(Cuenta cuenta : cliente.getCuentas()){
			if(TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())){
				nroTarjeta = StringUtils.stripStart(cuenta.getNroTarjetaCredito(), NumberUtils.BYTE_ZERO.toString());
				break;
			}
		}
		//Cualquier banelco entre las cuentas
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	@XmlElement
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the new nro documento
	 */
	@XmlElement
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	@XmlElement
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	
}
