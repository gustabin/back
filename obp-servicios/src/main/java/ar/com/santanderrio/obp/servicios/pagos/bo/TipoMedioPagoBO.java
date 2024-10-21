/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Interface TipoMedioPagoBO.
 */
public interface TipoMedioPagoBO {
    
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

    /**
	 * Genera Alta Scomp Request.
	 *
	 * @param cliente
	 *            the cliente
	 * @param res
	 *            the res
	 * @param pago
	 *            the pago
	 * @param pagoMisCuentasDTO
	 *            the pago mis cuentas DTO
	 * @param pmView
	 *            the pm view
	 * @return the alta comprobante request builder
	 */
    AltaComprobanteRequestBuilder generarAltaScompRequest(Cliente cliente, PagoPMC res, PagoInEntity pago,
            PagoMisCuentasDTO pagoMisCuentasDTO, PagoMultipleView pmView);
}
