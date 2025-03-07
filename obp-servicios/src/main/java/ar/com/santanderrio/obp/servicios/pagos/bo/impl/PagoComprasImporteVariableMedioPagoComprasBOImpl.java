/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoComprasBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntityBuilder;

/**
 * The Class PagoComprasImporteVariableMedioPagoComprasBOImpl.
 */
@Component
public class PagoComprasImporteVariableMedioPagoComprasBOImpl implements TipoMedioPagoComprasBO {

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoComprasBO#getTipoNuevoPagoEnum()
     */
    @Override
    public TipoNuevoPagoEnum getTipoNuevoPagoEnum() {
        return TipoNuevoPagoEnum.PAGO_COMPRAS_IMPORTE_VARIABLE;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoComprasBO#esTipoNuevoPago(ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean esTipoNuevoPago(final MedioPago medioPago) {
        return "S".equals(medioPago.getPpdctaHabilitado()) && "CD".equals(medioPago.getPpdctaModalidad())
                && "S".equals(medioPago.getPpdctaPagoParcial());
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoComprasBO#generarPagoInEntity(ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO)
     */
    @Override
    public PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO) {
        return (new PagoInEntityBuilder()).custom(pagoMisCuentasDTO);
    }
}
