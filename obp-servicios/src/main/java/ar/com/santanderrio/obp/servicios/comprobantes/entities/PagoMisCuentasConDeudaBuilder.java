/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCConDeuda;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class PagoMisCuentasConDeudaBuilder.
 */
public class PagoMisCuentasConDeudaBuilder extends AltaComprobanteRequestBuilder {

    /** The pago response. */
    private PagoPMC pagoResponse;

    /** The pago in entity. */
    PagoInEntity pagoInEntity;

    /**
     * Instantiates a new pago mis cuentas con deuda builder.
     *
     * @param cliente
     *            the cliente
     */
    public PagoMisCuentasConDeudaBuilder(Cliente cliente) {
        super(cliente);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.entities.
     * AltaComprobanteRequestBuilder#buildComprobanteRequest()
     */
    @Override
    public AltaComprobanteRequest buildComprobanteRequest() {
        AltaComprobanteRequest request = null;
        Comprobante comprobante = null;
        if (StringUtils.isNotBlank(pagoInEntity.getNroTarjeta())) {
            request = obtenerAltaPagoServConTC("38");
            comprobante = obtenerComprobantePMCConDeudaPagoConTC();
        } else {
            request = obtenerAltaBase("2", "11");
            comprobante = obtenerComprobantePMCConDeuda();
        }
        request.setComprobante(comprobante);
        return request;
    }

    /**
     * Obtener comprobante PMC con deuda para pago con TC.
     *
     * @return the comprobante PMC con deuda
     */
    private ComprobantePMCConDeuda obtenerComprobantePMCConDeudaPagoConTC() {
        ComprobantePMCConDeuda inEntity = new ComprobantePMCConDeuda();
        rellenarComprobantePagoServConTC(inEntity, "38");
        inEntity.setFechaOper(formatearFechaPMC(pagoResponse.getFechaHoraBody().substring(0, 8)));
        inEntity.setHoraOper(formatearHoraConSegundos(pagoResponse.getFechaHoraBody().substring(8)));
        inEntity.setIdMedioPago(null);
        inEntity.setDescMedioPago(null);
        inEntity.setTransferencia(obtenerTransferencia());
        return inEntity;
    }

    /**
     * Obtener comprobante PMC con deuda.
     *
     * @return the comprobante PMC con deuda
     */
    private ComprobantePMCConDeuda obtenerComprobantePMCConDeuda() {
        ComprobantePMCConDeuda inEntity = new ComprobantePMCConDeuda();
        rellenarComprobanteBase(inEntity, "2", "11");
        inEntity.setIdMedioPago(null);
        inEntity.setDescMedioPago(null);
        inEntity.setFechaOper(formatearFechaPMC(pagoResponse.getFechaHoraBody().substring(0, 8)));
        inEntity.setHoraOper(formatearHoraConSegundos(pagoResponse.getFechaHoraBody().substring(8)));
        inEntity.setTransferencia(obtenerTransferencia());
        return inEntity;
    }

    /**
     * Obtener transferencia.
     *
     * @return the transferencia PMC con deuda
     */
    private TransferenciaPMCConDeuda obtenerTransferencia() {
        TransferenciaPMCConDeuda transferencia = new TransferenciaPMCConDeuda();
        transferencia.setEmpresa(pagoInEntity.getEmpresaNombreFantasia());
        transferencia.setMoneda("$");
        transferencia.setImporte(
                formatearImportePMC(ISBANStringUtils.sacarCerosYBlancosIzq(pagoInEntity.getMonto().replace(".", ""))));
        transferencia.setIdentificacion(pagoInEntity.getIdentificacion());
        transferencia.setFechaHoraPago(pagoResponse.getFechaHoraBody());
        transferencia.setFechaVencimiento(ISBANStringUtils.formatearFecha(pagoInEntity.getFechaDePago()));
        transferencia.setFechaVencimiento(pagoInEntity.getFechaDeVencimiento());

        if (StringUtils.isNotBlank(pagoInEntity.getNroTarjeta())) {
            transferencia.setTipoTarjetaCredito((pagoInEntity.getTipoCuentaTC() == 7 ? TipoCuenta.VISA.getAbreviatura()
                    : TipoCuenta.AMEX.getAbreviatura()).toUpperCase());
            transferencia.setNumeroTarjetaCredito(StringUtils.substring(pagoInEntity.getNroTarjeta(), 9));
        } else {
            transferencia.setTipoCuentaDebito(pagoInEntity.getTipoCuenta());
            transferencia.setSucursalCuentaDebito(StringUtils.right(pagoInEntity.getSucursalCuenta(), 3));
            transferencia.setNumeroCuentaDebito(StringUtils.right(pagoInEntity.getNumeroCuenta(), 7));
        }

        transferencia.setLeyendaFactura(pagoInEntity.getNumeroFactura());
        transferencia.setNumControl(pagoResponse.getNumeroControl());
        transferencia.setNroComprobante(StringUtils.right(pagoResponse.getComprobantePorServicio(), 8));
        return transferencia;
    }

    /**
     * Sets the pago response.
     *
     * @param pagoResponse
     *            the new pago response
     * @return the pago mis cuentas con deuda builder
     */
    public PagoMisCuentasConDeudaBuilder setPagoResponse(PagoPMC pagoResponse) {
        this.pagoResponse = pagoResponse;
        return this;
    }

    /**
     * Sets the pago in entity.
     *
     * @param pagoInEntity
     *            the new pago in entity
     * @return the pago mis cuentas con deuda builder
     */
    public PagoMisCuentasConDeudaBuilder setPagoInEntity(PagoInEntity pagoInEntity) {
        this.pagoInEntity = pagoInEntity;
        return this;
    }

}
