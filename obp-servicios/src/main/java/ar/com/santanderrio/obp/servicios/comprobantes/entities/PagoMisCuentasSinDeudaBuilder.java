/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCSinDeuda;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Class PagoMisCuentasSinDeudaBuilder.
 */
public class PagoMisCuentasSinDeudaBuilder extends AltaComprobanteRequestBuilder {

    /** The pago response. */
    private PagoPMC pagoResponse;

    /** The pago in entity. */
    PagoInEntity pagoInEntity;

    /**
     * Instantiates a new pago mis cuentas sin deuda builder.
     *
     * @param cliente
     *            the cliente
     */
    public PagoMisCuentasSinDeudaBuilder(Cliente cliente) {
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
            request = obtenerAltaPagoServConTC("36");
            comprobante = obtenerComprobantePMCSinDeudaPagoConTC();
        } else {
            request = obtenerAltaBase("2", "12");
            comprobante = obtenerComprobantePMCSinDeuda();
        }
        request.setComprobante(comprobante);
        return request;
    }

    /**
     * Obtener comprobante PMC sin deuda para pago con TC.
     *
     * @return the comprobante
     */
    private Comprobante obtenerComprobantePMCSinDeudaPagoConTC() {
        ComprobantePMCSinDeuda inEntity = new ComprobantePMCSinDeuda();
        rellenarComprobantePagoServConTC(inEntity, "36");
        inEntity.setFechaOper(formatearFechaPMC(pagoResponse.getFechaHoraBody().substring(0, 8)));
        inEntity.setHoraOper(formatearHoraConSegundos(pagoResponse.getFechaHoraBody().substring(8)));
        inEntity.setIdMedioPago(null);
        inEntity.setDescMedioPago(null);
        inEntity.setTransferencia(obtenerTransferencia());
        return inEntity;
    }

    /**
     * Obtener comprobante PMC sin deuda.
     *
     * @return the comprobante PMC sin deuda
     */
    private ComprobantePMCSinDeuda obtenerComprobantePMCSinDeuda() {
        ComprobantePMCSinDeuda inEntity = new ComprobantePMCSinDeuda();
        rellenarComprobanteBase(inEntity, "2", "12");
        inEntity.setFechaOper(formatearFechaPMC(pagoResponse.getFechaHoraBody().substring(0, 8)));
        inEntity.setHoraOper(formatearHoraConSegundos(pagoResponse.getFechaHoraBody().substring(8)));
        inEntity.setIdMedioPago(null);
        inEntity.setDescMedioPago(null);
        inEntity.setTransferencia(obtenerTransferencia());
        return inEntity;
    }

    /**
     * Obtener transferencia.
     *
     * @return the transferencia PMC sin deuda
     */
    private TransferenciaPMCSinDeuda obtenerTransferencia() {
        TransferenciaPMCSinDeuda transferencia = new TransferenciaPMCSinDeuda();
        transferencia.setEmpresa(pagoInEntity.getEmpresaNombreFantasia());
        transferencia.setMoneda("$");
        if (pagoInEntity.getMonto().contains(",")) {
            String monto = pagoInEntity.getMonto();
            monto = monto.replace(",", "").replace(".", "");
            transferencia.setImporte(formatearImportePMC(ISBANStringUtils.sacarCerosYBlancosIzq(monto)));
        } else {
            transferencia.setImporte(formatearImportePMC(
                    ISBANStringUtils.sacarCerosYBlancosIzq(pagoInEntity.getMonto()).replace(".", "")));
        }
        if (StringUtils.isNotBlank(pagoInEntity.getNroTarjeta())) {
            transferencia.setTipoTarjetaCredito((pagoInEntity.getTipoCuentaTC() == 7 ? TipoCuenta.VISA.getAbreviatura()
                    : TipoCuenta.AMEX.getAbreviatura()).toUpperCase());
            transferencia.setNumeroTarjetaCredito(StringUtils.substring(pagoInEntity.getNroTarjeta(), 9));
        } else {
            if (pagoInEntity.getTipoCuenta().equals("02")) {
                if (pagoInEntity.getMoneda().equalsIgnoreCase(DivisaEnum.PESO.getCodigo())) {
                    transferencia.setTipoCuentaDebito(
                            StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString(), 2, "0"));
                } else {
                    transferencia.setTipoCuentaDebito(
                            StringUtils.leftPad(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString(), 2, "0"));
                }
            } else {
                transferencia.setTipoCuentaDebito(pagoInEntity.getTipoCuenta());
            }
            transferencia.setSucursalCuentaDebito(StringUtils.right(pagoInEntity.getSucursalCuenta(), 3));
            transferencia.setNumeroCuentaDebito(StringUtils.right(pagoInEntity.getNumeroCuenta(), 7));
        }

        transferencia.setFechaHoraPago(pagoResponse.getFechaHoraBody());
        transferencia.setIdentificacion(pagoInEntity.getIdentificacion().replace(" ", ""));
        transferencia.setNumControl(pagoResponse.getNumeroControl());
        transferencia.setNroComprobante(StringUtils.right(pagoResponse.getComprobantePorServicio(), 8));
        return transferencia;
    }

    /**
     * Sets the pago response.
     *
     * @param pagoResponse
     *            the pago response
     * @return the pago mis cuentas sin deuda builder
     */
    public PagoMisCuentasSinDeudaBuilder setPagoResponse(PagoPMC pagoResponse) {
        this.pagoResponse = pagoResponse;
        return this;
    }

    /**
     * Sets the pago in entity.
     *
     * @param pagoInEntity
     *            the pago in entity
     * @return the pago mis cuentas sin deuda builder
     */
    public PagoMisCuentasSinDeudaBuilder setPagoInEntity(PagoInEntity pagoInEntity) {
        this.pagoInEntity = pagoInEntity;
        return this;
    }

}
