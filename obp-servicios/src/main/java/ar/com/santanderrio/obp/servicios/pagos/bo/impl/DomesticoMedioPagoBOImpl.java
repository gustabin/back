/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
 * The Class DomesticoMedioPagoBOImpl.
 */
@Component("domesticoMedioPagoBO")
public class DomesticoMedioPagoBOImpl implements TipoMedioPagoBO{

    /** The codigo rubro domesticos. */
    @Value("#{'${MEDIODEPAGO.TIPO.DOMESTICOS}'.split(',')}")
    private List <String> codigoRubroDomesticos;
    
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(MedioPago medioPago) {
        return medioPago.getTipoPago() != null && medioPago.getTipoPago().equals(2)
                && codigoRubroDomesticos.contains(medioPago.getPescodigorubro());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).pagoDomestico(pagoMisCuentasDTO);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#generarAltaScompRequest(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC, ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity, ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO, ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView)
     */
    @Override
    public AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView) {
        PagoMisCuentasAFIPBuilder builder = new PagoMisCuentasAFIPBuilder(cliente).setPagoInEntity(pago)
                .setPagoResponse(res).isDomestico(true);
        if (pagoMisCuentasDTO != null) {
            return builder.setIdentificacion2(pagoMisCuentasDTO.getCuitEmpleador())
                    .setDatosAdicionales1(pagoMisCuentasDTO.getPeriodoPago().substring(0, 2))
                    .setDatosAdicionales2(pagoMisCuentasDTO.getPeriodoPago().substring(2));
        }
        return builder.setIdentificacion2(pmView.getCuitEmpleador())
                .setDatosAdicionales1(pmView.getPeriodoPago().substring(0, 2))
                .setDatosAdicionales2(pmView.getPeriodoPago().substring(2));
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.DOMESTICO;
    }

    /**
	 * Sets the codigo rubro domesticos.
	 *
	 * @param codigoRubroDomesticos
	 *            the new codigo rubro domesticos
	 */
    public void setCodigoRubroDomesticos(List<String> codigoRubroDomesticos) {
        this.codigoRubroDomesticos = codigoRubroDomesticos;
    }
    
}
