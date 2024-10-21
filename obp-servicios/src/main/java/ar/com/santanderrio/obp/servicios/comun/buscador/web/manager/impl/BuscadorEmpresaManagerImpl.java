/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.PrestacionView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.BuscadorEmpresaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.NuevaRecargaManager;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.ProvisionDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * The Interface BuscadorArchivoPagoManager.
 */

@Component
@Scope("singleton")
public class BuscadorEmpresaManagerImpl implements BuscadorEmpresaManager {

    /** The service. */
    @Autowired
    private BuscadorEmpresaBO buscadorEmpresaBO;

    @Autowired
    private EstadisticaManager estadisticaManager;

    @Autowired
    private NuevaRecargaManager nuevaRecargaManager;


	/** The respuesta factory. */
	@Autowired
    private RespuestaFactory respuestaFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchByRubroEmpresa(String term) {
        return buscadorEmpresaBO.search(term);
    }

    /**
     * Search empresa pago automatico.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.
     * BuscadorArchivoPagoManager#searchEmpresaPagoAutomatico(java.lang.String)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchEmpresaPagoAutomatico(String terminoBusqueda) {
        return buscadorEmpresaBO.searchPagoAutomatico(terminoBusqueda);
    }

    /**
     * Gets the by codigo.
     *
     * @param codigo the codigo
     * @return the by codigo
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.
     * BuscadorArchivoPagoManager#getByCodigo(java.lang.String)
     */
    @Override
    public Respuesta<MedioPagoView> getByCodigo(String codigo) {
        return buscadorEmpresaBO.getByCodigo(codigo);
    }

    /**
     * Search empresa recarga celulares.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.
     * BuscadorEmpresaManager#searchEmpresaRecargaCelulares(java.lang.String)
     */
    @Override
    public Respuesta<EmpresaRecargaCelularView> searchEmpresaRecargaCelulares() {
    	Respuesta<EmpresaRecargaCelularView> respuesta = buscadorEmpresaBO.searchEmpresaRecargaCelulares();
    	
    	EmpresaRecargaCelularView body = respuesta.getRespuesta();
		if(body != null) {
			Respuesta<List<CelularView>> agendaCelulares = nuevaRecargaManager.obtenerCelulares();
			body.setNumerosAgendados(
    			EstadoRespuesta.OK.equals(agendaCelulares.getEstadoRespuesta()) && 
    			!agendaCelulares.getRespuesta().isEmpty()  ? agendaCelulares.getRespuesta() : null);
		}
    	if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_SELECCION_COMPANIA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    	} else {
			estadisticaManager.add(EstadisticasConstants.RECARGA_CELULARES_SELECCION_COMPANIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    	}
    	
    	return respuesta;
    }

    /**
     * Search empresa debito automatico en tarjeta.
     *
     * @param terminoBusqueda the termino busqueda
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.
     * BuscadorEmpresaManager#searchEmpresaDebitoAutomaticoEnTarjeta(java.lang.
     * String)
     */
    @Override
    public Respuesta<EmpresaDebitoAutomaticoTarjetaView> searchEmpresaDebitoAutomaticoEnTarjeta(
            String terminoBusqueda) {
        return buscadorEmpresaBO.searchEmpresaDebitoAutomaticoEnTarjeta(terminoBusqueda);

    }

    /**
     * Search empresa nuevo pago.
     *
     * @param term the term
     * @return the respuesta
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.BuscadorEmpresaManager#searchEmpresaNuevoPago(java.lang.String)
     */
    @Override
    public Respuesta<BuscadorEmpresaRta> searchEmpresaNuevoPago(String term) {
        return buscadorEmpresaBO.searchEmpresaNuevoPago(term);
    }

	/**
	 * Search empresa recarga transporte.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<BuscadorEmpresaRta> searchEmpresaRecargaTransporte() {
		return buscadorEmpresaBO.searchEmpresaRecargaTransporte();
	}

	/**
	 * Obtener empresas.
	 *
	 * @param empresa the empresa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<EmpresasDebinRecurrenteView> obtenerEmpresas(String empresa) {
		Respuesta<EmpresasDebinRecurrenteView> respuesta = buscadorEmpresaBO.obtenerEmpresas(empresa);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
		} else {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Obtener servicios.
	 *
	 * @param cuitEmpresa the empresa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<VendedorPrestacionesView> obtenerServicios(String cuitEmpresa) {
		Respuesta<VendedorPrestacionesView> respuestaView;

		Respuesta<SellerWithProvisionDTO> respuestaServicio = buscadorEmpresaBO.obtenerServicios(cuitEmpresa);
		if (EstadoRespuesta.OK.equals(respuestaServicio.getEstadoRespuesta()) && respuestaServicio.getRespuesta() != null) {
			List<PrestacionView> prestacionViewList = new ArrayList<PrestacionView>();
			for(ProvisionDTO prov : respuestaServicio.getRespuesta().getServicios()) {
				PrestacionView prestacionView = new PrestacionView(
						prov.getMinimo(),
						prov.getMaximo(),
						prov.getNombre(),
						prov.getTooltipReferencia());
				prestacionViewList.add(prestacionView);
			}
			VendedorPrestacionesView vendedorPrestacionesView = new VendedorPrestacionesView(
					respuestaServicio.getRespuesta().getCuit(),
					respuestaServicio.getRespuesta().getNombreFantasia(),
					prestacionViewList
			);
			estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIOS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuestaView = respuestaFactory.crearRespuestaOk(vendedorPrestacionesView);
			respuestaView.setRespuestaVacia(prestacionViewList.size() == 0);
		} else {
			estadisticaManager.add(EstadisticasConstants.CONSULTA_SERVICIOS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}

		return respuestaView;
	}

}
