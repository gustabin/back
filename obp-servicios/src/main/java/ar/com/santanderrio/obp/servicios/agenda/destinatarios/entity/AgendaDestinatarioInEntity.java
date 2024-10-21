/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;

/**
 * The Class AgendaDestinatarioInEntity.
 */
public class AgendaDestinatarioInEntity extends DestinatarioEntity {

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Instantiates a new agenda destinatario in entity.
	 */
	public AgendaDestinatarioInEntity() {
		super();
	}

	/**
	 * Instantiates a new agenda destinatario in entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param destinatario
	 *            the destinatario
	 * @param datos
	 *            the datos
	 */
	public AgendaDestinatarioInEntity(Cliente cliente, DestinatarioEntity destinatario,
			ConfirmacionAltaDestinatarioView datos) {
		this.cliente = cliente;
		this.setTipoAgendaOcurrencia(destinatario.getTipoAgendaOcurrencia());
		this.setSucursalCuentaDestinatario(destinatario.getSucursalCuentaDestinatario());
		this.setTipoCuentaDestinatario(destinatario.getTipoCuentaDestinatario());
		this.setNumeroCuentaDestinatario(destinatario.getNumeroCuentaDestinatario());
		this.setCbuDestinatario(destinatario.getCbuDestinatario());
		this.setBancoDestinatario(destinatario.getBancoDestinatario());
		this.setTipoDocumentoDestinatario(destinatario.getTipoDocumentoDestinatario());
		this.setDocumentoDestinatario(destinatario.getDocumentoDestinatario());
		this.setCaracteristicasCuentaDestinatario(destinatario.getCaracteristicasCuentaDestinatario());
		if (datos.getReferencia() != null) {
			this.setDescripcionCuentaDestinatario(
					StringUtils.rightPad(datos.getReferencia(), 30, ISBANStringUtils.ESPACIO_STRING));
		} else {
			this.setDescripcionCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 30));
		}
		this.setTitular(StringUtils.equals(this.getTipoAgendaOcurrencia().trim(), "EXT")
				? StringUtils.rightPad(this.getDescripcionCuentaDestinatario(), 64, ISBANStringUtils.ESPACIO_STRING)
				: destinatario.getTitular());
		this.setTelefonoDestinatario(destinatario.getTelefonoDestinatario());
		if (datos.getDireccionCorreo() != null) {
			this.setDireccionCorreo(
					StringUtils.rightPad(datos.getDireccionCorreo(), 100, ISBANStringUtils.ESPACIO_STRING));
		} else {
			this.setDireccionCorreo(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 100));
		}
		if (datos.getAlias() != null) {
			this.setAlias(StringUtils.rightPad(datos.getAlias(), 20, ISBANStringUtils.ESPACIO_STRING));
		} else {
			this.setAlias(destinatario.getAlias());
		}
	}

	public AgendaDestinatarioInEntity(Cliente cliente, TransferenciaDTO transferenciaDTO) {
	    this.cliente = cliente;

		if (transferenciaDTO.isHaciaOtroBanco() || ISBANStringUtils.validarCBUCtaRecaudadora(transferenciaDTO.getCbuCuenta())) {
	        this.setTipoAgendaOcurrencia("OB ");
	        this.setTipoCuentaDestinatario(StringUtils.repeat(' ', 2));
	        this.setSucursalCuentaDestinatario(StringUtils.repeat(' ', 4));
	        this.setNumeroCuentaDestinatario(StringUtils.repeat(' ', 12));
	        this.setCbuDestinatario(transferenciaDTO.getCbuCuenta());
	    } else {
            this.setTipoAgendaOcurrencia("RIO");
            if (TipoCuenta.CUENTA_UNICA_DOLARES.equals(transferenciaDTO.getTipoCuentaDestino())
                    || TipoCuenta.CUENTA_UNICA_PESOS.equals(transferenciaDTO.getTipoCuentaDestino())) {
                this.setTipoCuentaDestinatario("02");
            } else {
                this.setTipoCuentaDestinatario(
                        StringUtils.leftPad(transferenciaDTO.getTipoCuentaDestino().getCodigo().toString(), 2, '0'));
            }
            this.setSucursalCuentaDestinatario(
                    StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroSucursal(), 4, '0'));
            this.setNumeroCuentaDestinatario(
                    StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto(), 12, '0'));
            this.setCbuDestinatario(StringUtils.repeat(' ', 22));
	    }
	    if (StringUtils.isBlank(transferenciaDTO.getCuit())) {
	        this.setTipoDocumentoDestinatario(StringUtils.repeat(' ', 2));
            this.setDocumentoDestinatario(StringUtils.repeat(' ', 11));
	    } else {
	        this.setTipoDocumentoDestinatario(transferenciaDTO.isEsCuil() ? "L " : "T ");
            this.setDocumentoDestinatario(transferenciaDTO.getCuit());
	    }
	    
	    this.setBancoDestinatario(StringUtils.rightPad(transferenciaDTO.getBancoDestino(), 50, ' '));
	    this.setDescripcionCuentaDestinatario(StringUtils.repeat(' ', 30));
	    this.setCaracteristicasCuentaDestinatario(StringUtils.repeat(' ', 10));
	    this.setTitular(StringUtils.rightPad(transferenciaDTO.getTitular(), 64, ' '));
	    this.setDireccionCorreo(StringUtils.repeat(' ', 100));
        this.setTelefonoDestinatario(StringUtils.repeat(' ', 16));
	    this.setAlias(StringUtils.rightPad(transferenciaDTO.getAlias(), 20, ' '));
    }

    /**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}