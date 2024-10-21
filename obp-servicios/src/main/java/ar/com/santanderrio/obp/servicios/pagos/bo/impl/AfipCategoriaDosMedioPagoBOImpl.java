/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagoMisCuentasAFIPBuilder;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntityBuilder;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class AfipCategoriaDosMedioPagoBOImpl.
 */
@Component
public class AfipCategoriaDosMedioPagoBOImpl implements TipoMedioPagoBO {

    /** The domestico medio pago BO. */
    @Autowired
    @Qualifier("domesticoMedioPagoBO")
    private TipoMedioPagoBO domesticoMedioPagoBO;
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(final MedioPago medioPago) {
        return medioPago.getTipoPago() != null && medioPago.getTipoPago().equals(2)
                && ! domesticoMedioPagoBO.esTipoNuevoPago(medioPago)
                && "2".equals(medioPago.getDatosAdicionales());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).pagoNoDomestico(pagoMisCuentasDTO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarAltaScompRequest(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC, ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO, ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView)
     */
    @Override
    public AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView) {
        PagoMisCuentasAFIPBuilder builder = new PagoMisCuentasAFIPBuilder(cliente).setPagoInEntity(pago)
                .setPagoResponse(res).isDomestico(false);
        if (pagoMisCuentasDTO != null) {
            return builder.setDatosAdicionales1(pagoMisCuentasDTO.getNumeroDeAnticipo())
                    .setDatosAdicionales2(pagoMisCuentasDTO.getPeriodoPago().substring(2));
        }
        return builder.setDatosAdicionales1(pmView.getNumeroDeAnticipo())
                .setDatosAdicionales2(pmView.getPeriodoPago().substring(2));
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.AFIP_CAT2;
    }

    /**
	 * Sets the domestico medio pago BO.
	 *
	 * @param domesticoMedioPagoBO
	 *            the new domestico medio pago BO
	 */
    public void setDomesticoMedioPagoBO(TipoMedioPagoBO domesticoMedioPagoBO) {
        this.domesticoMedioPagoBO = domesticoMedioPagoBO;
    }

}
