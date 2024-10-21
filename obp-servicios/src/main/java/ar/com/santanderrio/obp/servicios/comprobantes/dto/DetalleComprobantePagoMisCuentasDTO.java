/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCAfipView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class DetalleComprobantePagoMisCuentasDTO.
 */
public class DetalleComprobantePagoMisCuentasDTO extends DetalleComprobanteDTO {

	/** The Constant LABEL_DINAMICO. */
	public static final String LABEL_DINAMICO = "Identificación";

	/** The tipo PMC. */
	protected String tipoPMC;

	/** The empresa. */
	protected String empresa;

	/** The label dinamico. */
	protected String labelDinamico;

	/** The hora de pago. */
	protected String horaDePago;

	/** The importe. */
	protected BigDecimal importe;

	/** The nro control. */
	protected String nroControl;

	/** The medio de pago. */
	protected String nroMedioDePago;

	/** The medio de pago. */
	protected TipoCuenta tipoMedioDePago;

	/** The cuit cuil VEP. */
	protected CuitDTO cuitVEP;

	/** The fecha vencimiento. */
	protected Date fechaVencimiento;

	/** The codigo validacion. */
	protected String codigoValidacion;

	/** The transaccion. */
	protected String transaccion;

	/** The leyenda empresa. */
	protected String leyendaEmpresa;

	/** The moneda. */
	protected String moneda;

	/** The leyenda factura. */
	protected List<String> leyendaFactura;

	/** The factura. */
	protected String factura;

	/** The fiid. */
	protected String fiid;

	/** The es pago con deuda. */
	protected boolean esPagoConDeuda;
	
	/** The id cliente empresa. */
	protected String idClienteEmpresa;

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura
	 *            the factura to set
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/**
	 * Sets the leyenda factura.
	 *
	 * @param leyendaFactura
	 *            the leyendaFactura to set
	 */
	public void setLeyendaFactura(List<String> leyendaFactura) {
		this.leyendaFactura = leyendaFactura;
	}

	/**
	 * Gets the tipo PMC.
	 *
	 * @return the tipoPMC
	 */
	public String getTipoPMC() {
		return tipoPMC;
	}

	/**
	 * Sets the tipo PMC.
	 *
	 * @param tipoPMC
	 *            the tipoPMC to set
	 */
	public void setTipoPMC(String tipoPMC) {
		this.tipoPMC = tipoPMC;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the label dinamico.
	 *
	 * @return the labelDinamico
	 */
	public String getLabelDinamico() {
		return labelDinamico;
	}

	/**
	 * Sets the label dinamico.
	 *
	 * @param labelDinamico
	 *            the labelDinamico to set
	 */
	public void setLabelDinamico(String labelDinamico) {
		this.labelDinamico = labelDinamico;
	}

	/**
	 * Gets the hora de pago.
	 *
	 * @return the hora
	 */
	public String getHoraDePago() {
		return horaDePago;
	}

	/**
	 * Sets the hora de pago.
	 *
	 * @param horaDePago
	 *            the new hora de pago
	 */
	public void setHoraDePago(String horaDePago) {
		this.horaDePago = horaDePago;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the nro control.
	 *
	 * @return the nroControl
	 */
	public String getNroControl() {
		return nroControl;
	}

	/**
	 * Sets the nro control.
	 *
	 * @param nroControl
	 *            the nroControl to set
	 */
	public void setNroControl(String nroControl) {
		this.nroControl = nroControl;
	}

	/**
	 * Gets the nro medio de pago.
	 *
	 * @return the nro medio de pago
	 */
	public String getNroMedioDePago() {
		return nroMedioDePago;
	}

	/**
	 * Sets the nro medio de pago.
	 *
	 * @param nroMedioDePago
	 *            the new nro medio de pago
	 */
	public void setNroMedioDePago(String nroMedioDePago) {
		this.nroMedioDePago = nroMedioDePago;
	}

	/**
	 * Gets the tipo medio de pago.
	 *
	 * @return the tipo medio de pago
	 */
	public TipoCuenta getTipoMedioDePago() {
		return tipoMedioDePago;
	}

	/**
	 * Sets the tipo medio de pago.
	 *
	 * @param tipoMedioDePago
	 *            the new tipo medio de pago
	 */
	public void setTipoMedioDePago(TipoCuenta tipoMedioDePago) {
		this.tipoMedioDePago = tipoMedioDePago;
	}

	/**
	 * Gets the cuit VEP.
	 *
	 * @return the cuit VEP
	 */
	public CuitDTO getCuitVEP() {
		return cuitVEP;
	}

	/**
	 * Sets the cuit VEP.
	 *
	 * @param cuitVEP
	 *            the new cuit VEP
	 */
	public void setCuitVEP(CuitDTO cuitVEP) {
		this.cuitVEP = cuitVEP;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the codigo validacion.
	 *
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	/**
	 * Sets the codigo validacion.
	 *
	 * @param codigoValidacion
	 *            the codigoValidacion to set
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	/**
	 * Gets the transaccion.
	 *
	 * @return the transaccion
	 */
	public String getTransaccion() {
		return transaccion;
	}

	/**
	 * Sets the transaccion.
	 *
	 * @param transaccion
	 *            the transaccion to set
	 */
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * Gets the leyenda empresa.
	 *
	 * @return the leyendaEmpresa
	 */
	public String getLeyendaEmpresa() {
		return leyendaEmpresa;
	}

	/**
	 * Sets the leyenda empresa.
	 *
	 * @param leyendaEmpresa
	 *            the leyendaEmpresa to set
	 */
	public void setLeyendaEmpresa(String leyendaEmpresa) {
		this.leyendaEmpresa = leyendaEmpresa;
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
	 * Gets the leyenda empresa.
	 *
	 * @return the leyendaEmpresa
	 */
	public List<String> getLeyendaFactura() {
		return leyendaFactura;
	}

	/**
	 * Sets the leyenda empresa.
	 *
	 * @param leyendaFactura
	 *            the new leyenda factura
	 */
	public void setleyendaFactura(List<String> leyendaFactura) {
		this.leyendaFactura = leyendaFactura;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the new fiid
	 */
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}

	public boolean isEsPagoConDeuda() {
		return esPagoConDeuda;
	}

	public void setEsPagoConDeuda(boolean esPagoConDeuda) {
		this.esPagoConDeuda = esPagoConDeuda;
	}


	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hb = new HashCodeBuilder();
		if (fechaDePago != null) {
			hb.append(ISBANStringUtils.formatearFecha(fechaDePago));
		} else {
			hb.append(fechaDePago);
		}
		hb.append(horaDePago);
		if(!StringUtils.isBlank(idClienteEmpresa)) {
		    hb.append(idClienteEmpresa.trim());
		} else {
		    hb.append(idClienteEmpresa);
		}
		hb.append(nroControl);
		hb.append(importe);
		return hb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		}

		if (a == null) {
			return false;
		}
		if (!(a instanceof DetalleComprobanteDTO)) {
			return false;
		}

		DetalleComprobantePagoMisCuentasDTO detalle = (DetalleComprobantePagoMisCuentasDTO) a;
		if (detalle.getEsScomp() && esScomp) {
			return false;
		}
		EqualsBuilder eb = new EqualsBuilder();
		if (fechaDePago != null && detalle.getFechaDePago() != null) {
			eb.append(ISBANStringUtils.formatearFecha(fechaDePago),
					ISBANStringUtils.formatearFecha(detalle.getFechaDePago()));
		} else {
			eb.append(fechaDePago, detalle.getFechaDePago());
		}
		eb.append(horaDePago, detalle.getHoraDePago());
		if(!StringUtils.isBlank(idClienteEmpresa) && !StringUtils.isBlank(detalle.getIdClienteEmpresa())) {
            eb.append(idClienteEmpresa.trim(), detalle.getIdClienteEmpresa().trim());
        } else {
            eb.append(idClienteEmpresa.trim(), detalle.getIdClienteEmpresa());
        }
		eb.append(nroControl, detalle.getNroControl());
		eb.append(importe, detalle.getImporte());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DetalleComprobantePagoMisCuentasDTO [tipoPMC=");
		sb.append(tipoPMC);
		sb.append(", empresa= ");
		sb.append(empresa);
		sb.append(", labelDinamico= ");
		sb.append(labelDinamico);
		sb.append(", horaDePago= ");
		sb.append(horaDePago);
		sb.append(", importe= ");
		sb.append(importe);
		sb.append(", nroControl= ");
		sb.append(nroControl);
		sb.append(", nroMedioDePago= ");
		sb.append(nroMedioDePago);
		sb.append(", tipoMedioDePago= ");
		sb.append(tipoMedioDePago);
		sb.append(", cuitVEP= ");
		sb.append(cuitVEP);
		sb.append(", fechaVencimiento= ");
		sb.append(fechaVencimiento);
		sb.append(", codigoValidacion= ");
		sb.append(codigoValidacion);
		sb.append(", transaccion= ");
		sb.append(transaccion);
		sb.append(", leyendaEmpresa= ");
		sb.append(leyendaEmpresa);
		sb.append(", moneda= ");
		sb.append(moneda);
		sb.append(", leyendaFactura= ");
		sb.append(leyendaFactura);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Setear atributos comunes detalle PMC.
	 *
	 * @param view
	 *            the view
	 * @param empresa
	 *            the empresa
	 * @param fechaOperacion
	 *            the fecha operacion
	 * @param comprobante
	 *            the comprobante
	 */
	public void setearAtributosComunesDetallePMC(DetalleComprobanteView view, String empresa, String fechaOperacion,
			ComprobanteDTO comprobante) {
		setearImportes(view, comprobante);
		setearNumeroTipoYTitulo(view);
		setearNumeroYTipoCuentaOrigen(view, comprobante);
		view.setEmpresa(empresa);
		view.setFechaOperacion(fechaOperacion);
	}

	/**
	 * Sets the ear leyenda factura.
	 *
	 * @param view
	 *            the new ear leyenda factura
	 */
	public void setearLeyendaFactura(DetalleComprobantePMCView view) {
		if (getLeyendaFactura() != null && !getLeyendaFactura().isEmpty()) {
			view.setLeyendaFactura(getLeyendaFactura().get(0));
		}
	}

	/**
	 * Sets the ear fecha vencimiento.
	 *
	 * @param view
	 *            the new ear fecha vencimiento
	 */
	public void setearFechaVencimiento(DetalleComprobantePMCView view) {
		if (getFechaVencimiento() != null) {
			view.setFechaVencimiento(ISBANStringUtils.formatearFecha(getFechaVencimiento()));
		} else {
			view.setFechaVencimiento("-/-/-");
		}
	}

	/**
	 * Sets the ear elemento Y tipo adicional.
	 *
	 * @param view
	 *            the new ear elemento Y tipo adicional
	 */
	public void setearElementoYTipoAdicional(DetalleComprobantePMCAfipView view) {
		view.setElementoAdicional(getInformacionAdicional());
		view.setTipoElementoAdicional(getAnotaciones());
	}

	/**
	 * Sets the ear leyenda factura.
	 *
	 * @param view
	 *            the new ear leyenda factura
	 */
	public void setearLeyendaFactura2(DetalleComprobantePMCVEPView view) {
		if (getLeyendaFactura() != null && !getLeyendaFactura().isEmpty()) {
			view.setLeyendaFactura2(getLeyendaFactura().get(1));
		}
	}

	/**
	 * Sets the ear leyenda factura.
	 *
	 * @param view
	 *            the new ear leyenda factura
	 */
	public void setearLeyendaFactura3(DetalleComprobantePMCVEPView view) {
		if (getLeyendaFactura() != null && !getLeyendaFactura().isEmpty()) {
			view.setLeyendaFactura3(getLeyendaFactura().get(2));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
	 * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO)
	 */
	@Override
	public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
		DetalleComprobanteView view = new DetalleComprobanteView();
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
		setearAtributosComunesDetallePMC(view, getEmpresa(), sdf.format(getFechaDePago()), comprobante);
		view.setIdentificacion(getIdentificacion());
		view.setLabelDinamico(LABEL_DINAMICO);
		view.setHora(getHoraDePago());
		view.setNroControl(getNroControl());
		// Seteo el nro de comprobante en este campo,
		// porque es el que se usa para mostrar el nro de comprobante
		view.setNroTransaccion(getNroComprobante());
		return view;

	}

	@Override
	public String obtenerIdentificacionHistorial() {
		if (getIdClienteEmpresa() != null) {
			return getIdClienteEmpresa().trim();
		}
		return getIdentificacion().trim();
	}

}
