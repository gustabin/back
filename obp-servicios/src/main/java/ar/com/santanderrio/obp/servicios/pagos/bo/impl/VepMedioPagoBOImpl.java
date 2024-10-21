/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagoMisCuentasVEPBuilder;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntityBuilder;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class VepMedioPagoBOImpl.
 */
@Component
public class VepMedioPagoBOImpl implements TipoMedioPagoBO{

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(final MedioPago medioPago) {
        return medioPago.getTipoPago() != null && medioPago.getTipoPago().equals(3)
                && "F".equals(medioPago.getTipoEmpresa());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).vep(pagoMisCuentasDTO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarAltaScompRequest(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC, ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO, ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView)
     */
    @Override
    public AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView) {
        PagoMisCuentasVEPBuilder builder = new PagoMisCuentasVEPBuilder(cliente).setPagoInEntity(pago)
                .setPagoResponse(res);
        
        // si cuitEmpleador es distinto de 11 viene del calendario
        
        if (pagoMisCuentasDTO != null) {
            if (StringUtils.length(pagoMisCuentasDTO.getCuitEmpleador()) == 11) {
                return builder.setIdentificacion2(pagoMisCuentasDTO.getCuitEmpleador());
            }
        }
        return builder;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.VEP;
    }

}
