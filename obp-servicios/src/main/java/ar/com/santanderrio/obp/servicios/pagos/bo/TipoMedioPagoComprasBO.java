/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

/**
 * The Interface TipoMedioPagoComprasBO.
 */
public interface TipoMedioPagoComprasBO {
    
    /**
	 * Gets the tipo nuevo pago enum.
	 *
	 * @return the tipo nuevo pago enum
	 */
    TipoNuevoPagoEnum getTipoNuevoPagoEnum();
    
    /**
	 * Determina mediante un medioPago si corresponde a un tipo nuevo pago.
	 *
	 * @param medioPago
	 *            the medio pago
	 * @return true, if successful
	 */
    boolean esTipoNuevoPago(final MedioPago medioPago);
    
    /**
	 * Convierte la informacion del dto a la solicigada por el servicio de pago
	 * mis cuentas.
	 *
	 * @param pagoMisCuentasDTO
	 *            the pago mis cuentas DTO
	 * @return the pago in entity
	 */
    PagoInEntity generarPagoInEntity(final PagoMisCuentasDTO pagoMisCuentasDTO);

}
