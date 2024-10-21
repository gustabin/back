/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;

/**
 * The Interface PagoMultipleBO.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
public interface PagoMultipleBO extends BusinessObject {

	/**
	 * Ejecutar pago multiple. DTF: 10221. iatx: PAGMASSCIO100.
	 * 
	 * Este metodo invoca a RSA con la lista de pagos, solo para informar los
	 * datos. No importa la respuesta del servicio.
	 *
	 * @author Manuel.Vargas -B041299
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<PagoMultipleDTO> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView, Cliente cliente);

	/**
	 * Obtener cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<MedioPagoSelectionView> obtenerCuentas(Cliente cliente);
	
	Respuesta<ReporteView> imprimirComprobantes();
	
	Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF();
}
