/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagoMisCuentasSinDeudaBuilder;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntityBuilder;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class ElectronicoMedioPagoBOImpl.
 */
@Component
public class ElectronicoMedioPagoBOImpl implements TipoMedioPagoBO {

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.ELECTRONICO;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(final MedioPago medioPago) {
        return medioPago.getTipoPago() != null && medioPago.getTipoPago().equals(1)
                && !TipoNuevoPagoEnum.SUBE_RECARGA.getDescripcion().equals(medioPago.getFiid())
                && !TipoNuevoPagoEnum.MOVI_TRANSP_RECARGA.getDescripcion().equals(medioPago.getFiid())
                && !TipoNuevoPagoEnum.CELULAR_RECARGA.getDescripcion().equals(medioPago.getRubroFantasia());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).pagoElectronico(pagoMisCuentasDTO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarAltaScompRequest(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC, ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO, ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView)
     */
    @Override
    public AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView) {
    	if(!StringUtils.isNotBlank(res.getNumeroControl()) || !StringUtils.isNotBlank(res.getComprobantePorServicio())){
    		res.setNumeroControl(pago.getNumeroControl());
    		res.setComprobantePorServicio(pago.getComprobantePorServicio());
    	}
        return new PagoMisCuentasSinDeudaBuilder(cliente).setPagoInEntity(pago).setPagoResponse(res);
    }
}
