/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class MultiPago.
 * 
 * @see {@link PagoInEntity}
 * @lastUpdate emilio.watemberg Jan 17,2017: agregacion propiedad.
 */
public class PagoPMC extends PagoInEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5304589727223299624L;

	/** The todos OK. */
	private Boolean respuestaOK;

	/** The error general rollback. */
	private Boolean errorGeneralRollback;

	/**
	 * Instantiates a new pago PMC.
	 */
	public PagoPMC() {
		super();
	}

	/**
	 * Instantiates a new pago PMC.
	 *
	 * @param pago
	 *            the pago
	 */
	public PagoPMC(PagoInEntity pago) {
		this.setPago(pago);
	}

	/**
	 * Gets the respuesta OK.
	 *
	 * @return the respuesta OK
	 */
	public Boolean getRespuestaOK() {
		return respuestaOK;
	}

	/**
	 * Sets the respuesta OK.
	 *
	 * @param respuestaOK
	 *            the new respuesta OK
	 */
	public void setRespuestaOK(Boolean respuestaOK) {
		this.respuestaOK = respuestaOK;
	}

	/**
	 * Gets the error general rollback.
	 *
	 * @return the error general rollback
	 */
	public Boolean getErrorGeneralRollback() {
		return errorGeneralRollback;
	}

	/**
	 * Sets the error general rollback.
	 *
	 * @param errorGeneralRollback
	 *            the new error general rollback
	 */
	public void setErrorGeneralRollback(Boolean errorGeneralRollback) {
		this.errorGeneralRollback = errorGeneralRollback;
	}

	/**
	 * Sets the pago.
	 *
	 * @param pago
	 *            the new pago
	 */
	public void setPago(PagoInEntity pago) {
		setCodigoEmpresa(pago.getCodigoEmpresa());
		setIdentificacion(pago.getIdentificacion());
		setMensaje(pago.getMensaje());
		setMoneda(pago.getMoneda());
		setMonto(pago.getMonto());
		setEstadoPago(pago.getEstadoPago());
		setNumeroCuenta(pago.getNumeroCuenta());
		setNumeroFactura(pago.getNumeroFactura());
		setSucursalCuenta(pago.getSucursalCuenta());
		setTipoCuenta(pago.getTipoCuenta());
		setTipoError(pago.getTipoError());
		setTipoMonto(pago.getTipoMonto());
		setSucursalCuenta(pago.getSucursalCuenta());
		setTipoSeleccion(pago.getTipoSeleccion());
		setNumeroControl(pago.getNumeroControl());
		setComprobantePorServicio(pago.getComprobantePorServicio());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity#toString()
	 */
	@Override
	public String toString() {
		String toStringSuper = super.toString();
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		String toString = new ToStringBuilder(this).append("fechaHoraBody", getFechaHoraBody()).toString();

		return toStringSuper + toString;
	}

}
