/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagoMisCuentasConDeudaBuilder;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntityBuilder;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class ImporteVariablePorDefectoMedioPagoBOImpl.
 */
@Component
public class ImporteVariablePorDefectoMedioPagoBOImpl implements TipoMedioPagoBO {

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(final MedioPago medioPago) {
        return medioPago.getTipoPago() != null && medioPago.getTipoPago().equals(3)
                && !"0".equals(medioPago.getTipoImporte());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).custom(pagoMisCuentasDTO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarAltaScompRequest(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC, ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO, ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView)
     */
    @Override
    public AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView) {
        return new PagoMisCuentasConDeudaBuilder(cliente).setPagoInEntity(pago).setPagoResponse(res);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.CUSTOM_IMPORTE_VARIABLE;
    }
    
}
