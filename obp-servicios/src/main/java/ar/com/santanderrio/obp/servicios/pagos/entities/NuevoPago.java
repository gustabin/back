/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;

/**
 * Objeto que engloba toda la informacion para validar el pago con RSA.
 *
 * @author marcelo.ruiz
 */
public class NuevoPago extends AdhesionPagoAutomatico implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8066900123547866985L;

    /** The fecha pago. */
    private String fechaPago;

    /** The codigo pago electronico 2. */
    private String codigoPagoElectronico2;

    /** The factura numero. */
    private String facturaNumero;

    /** The periodo. */
    private String periodo;

    /** The monto. */
    private String monto;

    /** The mensajeCoordenadasBanelco. */
    private String mensajeCoordenadasBanelco;

    /** mes. */
    private String mes;

    /** año. */
    private String anio;

    /** nro Anticipo. */
    private String numeroDeAnticipo;

    /** nro cuota. */
    private String numeroDeCuota;

    /** The numero referencia pago. */
    private String numeroReferenciaPago;

    /** The ultimos digitos banelco. */
    private String ultimosDigitosBanelco;

    /** The digitos ingresados banelco. */
    private String digitosIngresadosBanelco;

    /** The challenge. */
    private String challenge;

    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** The identificacion. */
    private String identificacion;

    /** The label identificacion. */
    private String labelIdentificacion;

    /** The dato adicional. */
    private String datoAdicional;

    /** The label dato adicional. */
    private String labelDatoAdicional;

    /** The numero vep. */
    private String numeroVep;

    /** The anticipo. */
    private String anticipo;

    /** The pago compras id. */
    private String pagoComprasId;

    /** The nro boleta. */
    private String nroBoleta;
    
    /** The vencimiento tarjeta. */
    private String vencimientoTarjeta;

    /** The is nuevo pago. */
    private Boolean isNuevoPago;

    private boolean isFromAdhesionDebitoAutomatico;
    
	private String nombreMedioDePago;
	
	private String medioPago;
	
	private OfertaComercialFeedback ofertaComercialFeedback;
	
	private String tipoGestor;
	
	private EmisionOfertaIntegrada emisionGastosProtegido;
	
	private String nroPolizaGastosProtegido;

    private String alias;

    private String numero;

    private String descripcionTipoCuenta;
    
	/**
     * Gets the fecha pago.
     *
     * @return the fecha pago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * Sets the fecha pago.
     *
     * @param fechaPago
     *            the new fecha pago
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Gets the numero de anticipo.
     *
     * @return the numero de anticipo
     */
    public String getNumeroDeAnticipo() {
        return numeroDeAnticipo;
    }

    /**
     * Sets the numero de anticipo.
     *
     * @param numeroDeAnticipo
     *            the new numero de anticipo
     */
    public void setNumeroDeAnticipo(String numeroDeAnticipo) {
        this.numeroDeAnticipo = numeroDeAnticipo;
    }

    /**
     * Gets the numero de cuota.
     *
     * @return the numero de cuota
     */
    public String getNumeroDeCuota() {
        return numeroDeCuota;
    }

    /**
     * Sets the numero de cuota.
     *
     * @param numeroDeCuota
     *            the new numero de cuota
     */
    public void setNumeroDeCuota(String numeroDeCuota) {
        this.numeroDeCuota = numeroDeCuota;
    }

    /**
     * Gets the codigo pago electronico 2.
     *
     * @return the codigo pago electronico 2
     */
    public String getCodigoPagoElectronico2() {
        return codigoPagoElectronico2;
    }

    /**
     * Sets the codigo pago electronico 2.
     *
     * @param codigoPagoElectronico2
     *            the new codigo pago electronico 2
     */
    public void setCodigoPagoElectronico2(String codigoPagoElectronico2) {
        this.codigoPagoElectronico2 = codigoPagoElectronico2;
    }

    /**
     * Gets the mes.
     *
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * Sets the mes.
     *
     * @param mes
     *            the new mes
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * Gets the anio.
     *
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Sets the anio.
     *
     * @param anio
     *            the new anio
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * Gets the factura numero.
     *
     * @return the factura numero
     */
    public String getFacturaNumero() {
        return facturaNumero;
    }

    /**
     * Sets the factura numero.
     *
     * @param facturaNumero
     *            the new factura numero
     */
    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    /**
     * Gets the periodo.
     *
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Sets the periodo.
     *
     * @param periodo
     *            the new periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * Gets the mensajeCoordenadasBanelco.
     *
     * @return the mensajeCoordenadasBanelco
     */
    public String getMensajeCoordenadasBanelco() {
        return mensajeCoordenadasBanelco;
    }

    /**
     * Sets the mensajeCoordenadasBanelco.
     *
     * @param mensajeCoordenadasBanelco
     *            the new mensajeCoordenadasBanelco
     */
    public void setMensajeCoordenadasBanelco(String mensajeCoordenadasBanelco) {
        this.mensajeCoordenadasBanelco = mensajeCoordenadasBanelco;
    }

    /**
     * Gets the monto.
     *
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the monto.
     *
     * @param monto
     *            the new monto
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Gets the numero referencia pago.
     *
     * @return the numero referencia pago
     */
    public String getNumeroReferenciaPago() {
        return numeroReferenciaPago;
    }

    /**
     * Sets the numero referencia pago.
     *
     * @param numeroReferenciaPago
     *            the new numero referencia pago
     */
    public void setNumeroReferenciaPago(String numeroReferenciaPago) {
        this.numeroReferenciaPago = numeroReferenciaPago;
    }

    /**
     * Gets the ultimos digitos banelco.
     *
     * @return the ultimos digitos banelco
     */
    public String getUltimosDigitosBanelco() {
        return ultimosDigitosBanelco;
    }

    /**
     * Sets the ultimos digitos banelco.
     *
     * @param ultimosDigitosBanelco
     *            the new ultimos digitos banelco
     */
    public void setUltimosDigitosBanelco(String ultimosDigitosBanelco) {
        this.ultimosDigitosBanelco = ultimosDigitosBanelco;
    }

    /**
     * Gets the digitos ingresados banelco.
     *
     * @return the digitos ingresados banelco
     */
    public String getDigitosIngresadosBanelco() {
        return digitosIngresadosBanelco;
    }

    /**
     * Sets the digitos ingresados banelco.
     *
     * @param digitosIngresadosBanelco
     *            the new digitos ingresados banelco
     */
    public void setDigitosIngresadosBanelco(String digitosIngresadosBanelco) {
        this.digitosIngresadosBanelco = digitosIngresadosBanelco;
    }

    /**
     * Gets the challenge.
     *
     * @return the challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * Sets the challenge.
     *
     * @param challenge
     *            the new challenge
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    /**
     * Gets the identificacion.
     *
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Sets the identificacion.
     *
     * @param identificacion
     *            the new identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Gets the label identificacion.
     *
     * @return the label identificacion
     */
    public String getLabelIdentificacion() {
        return labelIdentificacion;
    }

    /**
     * Sets the label identificacion.
     *
     * @param labelIdentificacion
     *            the new label identificacion
     */
    public void setLabelIdentificacion(String labelIdentificacion) {
        this.labelIdentificacion = labelIdentificacion;
    }

    /**
     * Gets the dato adicional.
     *
     * @return the dato adicional
     */
    public String getDatoAdicional() {
        return datoAdicional;
    }

    /**
     * Sets the dato adicional.
     *
     * @param datoAdicional
     *            the new dato adicional
     */
    public void setDatoAdicional(String datoAdicional) {
        this.datoAdicional = datoAdicional;
    }

    /**
     * Gets the label dato adicional.
     *
     * @return the label dato adicional
     */
    public String getLabelDatoAdicional() {
        return labelDatoAdicional;
    }

    /**
     * Sets the label dato adicional.
     *
     * @param labelDatoAdicional
     *            the new label dato adicional
     */
    public void setLabelDatoAdicional(String labelDatoAdicional) {
        this.labelDatoAdicional = labelDatoAdicional;
    }

    /**
     * Gets the numero vep.
     *
     * @return the numero vep
     */
    public String getNumeroVep() {
        return numeroVep;
    }

    /**
     * Sets the numero vep.
     *
     * @param numeroVep
     *            the new numero vep
     */
    public void setNumeroVep(String numeroVep) {
        this.numeroVep = numeroVep;
    }

    /**
     * Gets the anticipo.
     *
     * @return the anticipo
     */
    public String getAnticipo() {
        return anticipo;
    }

    /**
     * Sets the anticipo.
     *
     * @param anticipo
     *            the new anticipo
     */
    public void setAnticipo(String anticipo) {
        this.anticipo = anticipo;
    }

    /**
     * Gets the fecha vencimiento.
     *
     * @return the fecha vencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the fecha vencimiento.
     *
     * @param fechaVencimiento
     *            the new fecha vencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the pago compras id.
     *
     * @return the pagoComprasId
     */
    public String getPagoComprasId() {
        return pagoComprasId;
    }

    /**
     * Sets the pago compras id.
     *
     * @param pagoComprasId
     *            the pagoComprasId to set
     */
    public void setPagoComprasId(String pagoComprasId) {
        this.pagoComprasId = pagoComprasId;
    }

    /**
     * Gets the nro boleta.
     *
     * @return the nroBoleta
     */
    public String getNroBoleta() {
        return nroBoleta;
    }

    /**
     * Sets the nro boleta.
     *
     * @param nroBoleta
     *            the nroBoleta to set
     */
    public void setNroBoleta(String nroBoleta) {
        this.nroBoleta = nroBoleta;
    }
    
    

    public String getNroPolizaGastosProtegido() {
		return nroPolizaGastosProtegido;
	}

	public void setNroPolizaGastosProtegido(String nroPolizaGastosProtegido) {
		this.nroPolizaGastosProtegido = nroPolizaGastosProtegido;
	}
	
	

	public EmisionOfertaIntegrada getEmisionGastosProtegido() {
		return emisionGastosProtegido;
	}

	public void setEmisionGastosProtegido(EmisionOfertaIntegrada emisionGastosProtegido) {
		this.emisionGastosProtegido = emisionGastosProtegido;
	}

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String getDescripcionTipoCuenta() {
        return descripcionTipoCuenta;
    }

    @Override
    public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
        this.descripcionTipoCuenta = descripcionTipoCuenta;
    }

    /**
     * Cargar comprobante.
     *
     * @param feedback
     *            the feedback
     */
    public void cargarComprobante(NuevaRecargaOutDTO feedback) {
        this.generarFechaHoraComprobante(feedback.getFechaDePago());
        this.setNroDeComprobante(feedback.getNroComprobante());
        this.setNroControl(feedback.getNroControl());
    }

    /**
     * Cargar comprobante pago compras.
     *
     * @param pagoComprasDTO
     *            the pago compras DTO
     */
    public void cargarComprobantePagoCompras(PagoComprasOutDTO pagoComprasDTO) {
        this.generarFechaHoraComprobante(new Date());
        this.setNroDeComprobante(pagoComprasDTO.getComprobante());
        this.setFechaPago(StringUtils.left(this.getFechaHora(), 10));
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(anio);
        hcb.append(challenge);
        hcb.append(codigoPagoElectronico2);
        hcb.append(digitosIngresadosBanelco);
        hcb.append(facturaNumero);
        hcb.append(fechaPago);
        hcb.append(mensajeCoordenadasBanelco);
        hcb.append(mes);
        hcb.append(monto);
        hcb.append(numeroDeAnticipo);
        hcb.append(numeroDeCuota);
        hcb.append(numeroReferenciaPago);
        hcb.append(periodo);
        hcb.append(ultimosDigitosBanelco);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        NuevoPago other = (NuevoPago) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(anio, other.getAnio());
        eb.append(challenge, other.getChallenge());
        eb.append(codigoPagoElectronico2, other.getCodigoPagoElectronico2());
        eb.append(digitosIngresadosBanelco, other.getDigitosIngresadosBanelco());
        eb.append(facturaNumero, other.getFacturaNumero());
        eb.append(fechaPago, other.getFechaPago());
        eb.append(mensajeCoordenadasBanelco, other.getMensajeCoordenadasBanelco());
        eb.append(mes, other.getMes());
        eb.append(monto, other.getMonto());
        eb.append(numeroDeAnticipo, other.getNumeroDeAnticipo());
        eb.append(numeroDeCuota, other.getNumeroDeCuota());
        eb.append(numeroReferenciaPago, other.getNumeroReferenciaPago());
        eb.append(periodo, other.getPeriodo());
        eb.append(ultimosDigitosBanelco, other.getUltimosDigitosBanelco());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("fechaPago", fechaPago)
                .append("codigoPagoElectronico2", codigoPagoElectronico2).append("facturaNumero", facturaNumero)
                .append("periodo", periodo).append("monto", monto)
                .append("mensajeCoordenadasBanelco", mensajeCoordenadasBanelco).append("mes", mes).append("anio", anio)
                .append("numeroDeAnticipo", numeroDeAnticipo).append("numeroDeCuota", numeroDeCuota)
                .append("numeroReferenciaPago", numeroReferenciaPago)
                .append("ultimosDigitosBanelco", ultimosDigitosBanelco)
                .append("digitosIngresadosBanelco", digitosIngresadosBanelco).append("challenge", challenge).toString();
    }

    /**
     * se usa para transformar el dato enviado por FE y poner los decimales
     * correctos para enviarlo al servicio.
     *
     * @param monto
     *            the monto
     * @return the string
     */
    public String obtenerMontoFormateado(String monto) {
        if (monto.contains(".")) {
            int posPunto = monto.indexOf('.');
            int agregarCeros = monto.length() - posPunto;

            monto = monto.replace(".", "");
            if (agregarCeros == 2) {
                monto += "0";
            }

        } else {
            monto += "00";
        }

        return StringUtils.leftPad(String.valueOf(monto), 14, "0");
    }

	/**
	 * Gets the vencimiento tarjeta.
	 *
	 * @return the vencimiento tarjeta
	 */
	public String getVencimientoTarjeta() {
		return vencimientoTarjeta;
	}

	/**
	 * Sets the vencimiento tarjeta.
	 *
	 * @param vencimientoTarjeta
	 *            the new vencimiento tarjeta
	 */
	public void setVencimientoTarjeta(String vencimientoTarjeta) {
		this.vencimientoTarjeta = vencimientoTarjeta;
	}

    /**
	 * Gets the checks if is nuevo pago.
	 *
	 * @return the checks if is nuevo pago
	 */
    public Boolean getIsNuevoPago() {
        return isNuevoPago;
    }

    /**
	 * Sets the checks if is nuevo pago.
	 *
	 * @param isNuevoPago
	 *            the new checks if is nuevo pago
	 */
    public void setIsNuevoPago(Boolean isNuevoPago) {
        this.isNuevoPago = isNuevoPago;
    }

	public boolean getisFromAdhesionDebitoAutomatico() {
		return isFromAdhesionDebitoAutomatico;
	}

	public void setFromAdhesionDebitoAutomatico(boolean isFromAdhesionDebitoAutomatico) {
		this.isFromAdhesionDebitoAutomatico = isFromAdhesionDebitoAutomatico;
	}
	
	public String getNombreMedioDePago() {
		return nombreMedioDePago;
	}

	public void setNombreMedioDePago(String nombreMedioDePago) {
		this.nombreMedioDePago = nombreMedioDePago;
	}
	
	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public OfertaComercialFeedback getOfertaComercialFeedback() {
		return ofertaComercialFeedback;
	}

	public void setOfertaComercialFeedback(OfertaComercialFeedback ofertaComercialFeedback) {
		this.ofertaComercialFeedback = ofertaComercialFeedback;
	}

	public String getTipoGestor() {
		return tipoGestor;
	}

	public void setTipoGestor(String tipoGestor) {
		this.tipoGestor = tipoGestor;
	}
	
	
} 
