/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.buscador.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * The Interface BuscadorMedioDePagoBO.
 *
 * @author pablo.martin.gore
 */
public interface BuscadorEmpresaBO {

    /**
     * Busqueda en lucene todas las coicidencia de term.
     *
     * @param term
     *            the term
     * @return the respuesta
     */
    Respuesta<BuscadorEmpresaRta> search(String term);

    /**
     * Busca las empresas que matcheen con ese termino y tengan pago automatico
     * habilitado (PesPA_habilitado = S O Addi_habilitado = S).
     *
     * @param term
     *            the term
     * @return the respuesta
     */
    Respuesta<BuscadorEmpresaRta> searchPagoAutomatico(String term);

    /**
     * obtiene medio de pago por codigo.
     *
     * @param codigo
     *            the codigo
     * @return the by codigo
     */
    Respuesta<MedioPagoView> getByCodigo(String codigo);

    /**
     * Busca las empresas que matcheen con ese termino y sean empresas de recarga de
     * celular (rubroFantasia = RECARGA DE CELULARES).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    Respuesta<EmpresaRecargaCelularView> searchEmpresaRecargaCelulares();

    /**
     * Busca las empresas que matcheen con ese termino y sean empresas que admitan
     * adhesion a debito automatico en tarjeta (Pes_habilitado = S y Addi_habilitado
     * = N).
     *
     * @param terminoBusqueda
     *            the termino busqueda
     * @return the respuesta
     */
    Respuesta<EmpresaDebitoAutomaticoTarjetaView> searchEmpresaDebitoAutomaticoEnTarjeta(String terminoBusqueda);

    /**
     * Search empresa por cuit Y servicio.
     *
     * @param cuit
     *            the cuit
     * @param servicio
     *            the servicio
     * @return the respuesta
     */
    Respuesta<MedioPago> searchEmpresaPorCuitYServicio(String cuit, String servicio);

    /**
	 * Search empresa nuevo pago.
	 *
	 * @param term
	 *            the term
	 * @return the respuesta
	 */
    Respuesta<BuscadorEmpresaRta> searchEmpresaNuevoPago(String term);
    /**
     * Busca las empresas que matcheen con ese termino y sean empresas de recarga de
     * transporte (rubroFantasia =).
     *
     * @return the respuesta
     */
    Respuesta<BuscadorEmpresaRta> searchEmpresaRecargaTransporte();

	/**
	 * Obtener empresas.
	 *
	 * @param termino the termino
	 * @return the respuesta
	 */
	Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(String termino);
	
	/**
	 * Obtener servicios.
	 *
	 * @param cuitEmpresa the cuit empresa
	 * @return the respuesta
	 */
	Respuesta<SellerWithProvisionDTO> obtenerServicios(String cuitEmpresa);


}
