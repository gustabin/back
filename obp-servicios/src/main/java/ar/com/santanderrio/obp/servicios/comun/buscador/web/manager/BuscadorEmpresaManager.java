/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.buscador.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * The Interface BuscadorArchivoPagoManager.
 */
public interface BuscadorEmpresaManager {

    /**
     * Obtiene la lista de medios de pago habilitados para pago automatico: que
     * cumplan con los siguientes requisitos Pes_Habilitado = S Pes_prepago = N.
     *
     * @param term
     *            the term
     * @return the respuesta
     */
    Respuesta<BuscadorEmpresaRta> searchByRubroEmpresa(String term);

    /**
     * Obtiene la lista de medios de pago habilitados para pago automatico: que
     * cumplan con los siguientes requisitos Pes_Habilitado = S Pes_prepago = N
     * (PesPA_habilitado = S O Addi_habilitado = S).
     *
     * @param term
     *            el termino a buscar
     * @return the respuesta
     */
    Respuesta<BuscadorEmpresaRta> searchEmpresaPagoAutomatico(String term);

    /**
     * Obtiene medio de pago por fiid.
     *
     * @param codigo
     *            the codigo
     * @return the by codigo
     */
    Respuesta<MedioPagoView> getByCodigo(String codigo);

    /**
     * Obtiene la lista de medios de pago habilitados para pago automatico: que
     * cumplan con los siguientes requisitos Pes_Habilitado = S Pes_prepago = N
     * (rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    Respuesta<EmpresaRecargaCelularView> searchEmpresaRecargaCelulares();

    /**
     * Obtiene la lista de medios de pago habilitados para debito automatico en
     * tarjeta: que cumplan con los siguientes requisitos Pes_habilitado = S y
     * Addi_habilitado = N (rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    Respuesta<EmpresaDebitoAutomaticoTarjetaView> searchEmpresaDebitoAutomaticoEnTarjeta(String terminoBusqueda);

    /**
	 * Search empresa nuevo pago.
	 *
	 * @param query
	 *            the query
	 * @return the respuesta
	 */
    Respuesta<BuscadorEmpresaRta> searchEmpresaNuevoPago(String query);

	/**
	 * Obtiene la lista de medios de pago habilitados para pago automatico: que
	 * cumplan con los siguientes requisitos Pes_Habilitado = S Pes_prepago = N
	 * (rubroFantasia = transporte).
	 *
	 * @return the respuesta
	 */
	Respuesta<BuscadorEmpresaRta> searchEmpresaRecargaTransporte();
	
	/**
	 * Obtener empresas.
	 *
	 * @param empresa the empresa
	 * @return the respuesta
	 */
	Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(String empresa);

	/**
	 * Obtener servicios.
	 *
	 * @param cuitEmpresa the empresa
	 * @return the respuesta
	 */
	Respuesta<VendedorPrestacionesView> obtenerServicios(String cuitEmpresa);
	
}