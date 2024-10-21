/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.AltaComprobantePlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ComprobantePlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.ContenidoTenenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.FinalizarPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.InteresesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.MinimosPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimulacionPrecancelableUVADTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.SimularPlazoFijoOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaPlazoFijoBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TenenciaTotalPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.FrecuenciaCobroPFInteresanteOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RouterApiResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.tenencias.dto.SolicitarPrecancelarOutDTO;

/**
 * The Interface PlazoFijoBO.
 */
public interface PlazoFijoBO {

	/**
	 * Simular plazo fijo.
	 *
	 * @param inDTO
	 *            the in DTO
	 * @param cliente
	 *            the cliente
	 * @param cantCuentas
	 *            cantidad de cuentas debito del cliente
	 * @return the respuesta
	 */
	Respuesta<SimularPlazoFijoOutDTO> simularPlazoFijo(SimularPlazoFijoInDTO inDTO, Cliente cliente, int cantCuentas);

	/**
	 * Devuelve el importe minimo y el plazo minimo para plazos fijos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipo banca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<MinimosPlazoFijoDTO> consultarMinimos(Cliente cliente, String tipoBanca);

	/**
	 * Obtiene las acciones al vencimiento disponibles para un plazo fijo
	 * determinado, el cual se recibe por parametro.
	 *
	 * @param codigoPlazo
	 *            the codigo plazo
	 * @return the respuesta
	 */
	Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView, Cliente cliente);

	/**
	 * Ver comprobante.
	 *
	 * @param comprobantePlazoFijoInDTO
	 *            the comprobante plazo fijo in DTO
	 * @return the respuesta
	 */
	Respuesta<ComprobantePlazoFijoOutDTO> verComprobante(ComprobantePlazoFijoInDTO comprobantePlazoFijoInDTO);

	/**
	 * Finalizar plazo fijo.
	 *
	 * @param finalizarPlazoFijoDTO
	 *            the finalizar plazo fijo DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FinalizarPlazoFijoDTO> finalizarPlazoFijo(FinalizarPlazoFijoDTO finalizarPlazoFijoDTO, Cliente cliente);

	/**
	 * Vaciar cache plazos fijos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarCachePlazosFijos();

	/**
	 * Vaciar cache tasas.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarCacheTasas();

	/**
	 * Calcular intereses.
	 *
	 * @param inDTO
	 *            the in DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<InteresesDTO> calcularIntereses(SimularPlazoFijoInDTO inDTO, Cliente cliente);

	/**
	 * Consulta las tasas de los plazos fijos habilitados.
	 *
	 * @param cliente
	 *            the cliente
	 * @param bancaSeleccionada
	 * 			the banca seleccionada
	 * @return the respuesta
	 */
	Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(Cliente cliente, String bancaSeleccionada);

	/**
	 * Recupera las tenenecias plazo fijo del cliente Para Banca Personal.
	 * correspondiente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaTotalPlazoFijoDTO> obtenerTenenciasPlazoFijo(Cliente cliente);

	/**
	 * Recupera las tenenecias plazo fijo del cliente Para Banca Privada.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaPlazoFijoBprivDTO> obtenerTenenciasPlazoFijoBpriv(Cliente cliente, List<CuentaTituloView> cuentasInversionesPFBpriv);

	/**
	 * Solicitud Precancelar Plazo Fijo Banca Privada.
	 *
	 * @param inview
	 *            the inview
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<SolicitarPrecancelarOutDTO> solicitarPrecancelarPlazoFijo(SolicitarPrecancelarInView inview,
			Cliente cliente, Boolean isUvaPrecancelable, boolean uvaPrecaBPriv);

	/**
	 * Precancelar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inView
	 *            the in view
	 * @param opcion
	 *            the opcion
	 * @return the respuesta
	 */
	Respuesta<SimulacionPrecancelableDTO> precancelar(Cliente cliente, SimularPrecancelarInView inView, String opcion);

	/**
	 * Modificar accion vencimiento.
	 *
	 * @param inView
	 *            the in view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<String> modificarAccionVencimiento(ModificarAccionInView inView, Cliente cliente);

	/**
	 * Obtener el detalle de la frecuencia de cobro de intereses.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(DetalleInteresesViewRequest viewRequest,
			Cliente cliente);
	
	/**
	 * Obtiene la descripcion de una accion al vencimiento por codigo.
	 *
	 * @param codigoPlazo
	 *            the codigo plazo
	 * @param codigoAccion
	 *            the codigo accion
	 * @return the respuesta
	 */
	String descripcionAccionesAlVencimiento(String codigoPlazo, String codigoAccion);
	
	/**
	 * Vaciar cache plazos fijos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarCacheAcciones();

	/**
	 * Alta de comprobante en SCOMP.
	 *
	 * @param altaComprobantePlazoFijoDTO
	 *            the alta comprobante plazo fijo DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<AltaComprobantePlazoFijoDTO> altaComprobante(AltaComprobantePlazoFijoDTO altaComprobantePlazoFijoDTO,Cliente cliente);

	/**
	 * Obtener detalle PF interesante BP.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<FrecuenciaCobroPFInteresanteOutEntity> obtenerDetallePFInteresanteBP(DetallePFInteresanteInView detalleIn) throws BusinessException;

	Respuesta<Reporte> obtenerPlazosFijosReporte(TenenciaPlazoFijoView tenenciaPFView, Cliente cliente);
	
	Respuesta<Reporte> obtenerPlazosFijosReporteBP(List<ContenidoTenenciaBprivDTO> listaPlazosFijosBP, Cliente cliente);

	Respuesta<SimulacionPrecancelableUVADTO> precancelarUVA(Cliente cliente, SimularPrecancelarInView inView, String opcion);
	
	boolean esCuentaRepatriacion(Cuenta cuenta);

	RecomendacionResponseEntity obtenerRecomendacion(RecomendacionInView viewRequest);
	
	RouterApiResponseEntity llamarRouterApi(String criptoKey, String canal, String subCanal) throws DAOException;
}