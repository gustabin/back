/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CondicionesGeneralesCuentaCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultaOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarReversarOrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.LicitacionesVigentesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.NuevaLicitacionDTOResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.NuevaLicitacionDTOResponseBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteCnvViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionCanjeView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionViewResp;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVIgentesBprivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeResponseView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest;

/**
 * The Interface TitulosBO.
 *
 * @author juan.pablo.picate
 */
public interface TitulosBO {

	/**
	 * Consultar ordenes licitaciones dependiendo la banca.
	 *
	 * @param cliente
	 *            the cliente
	 * @param banca
	 *            the banca
	 * @param isLicitaciones
	 *            the isLicitaciones
	 * @return the respuesta
	 */
	Respuesta<ConsultarOrdenesOutDTO> consultarLicitaciones(Cliente cliente, TipoBancaEnum banca,
			Boolean isLicitaciones);

	/**
	 * Obtiene las licitaciones vigentes para el cliente recibido.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentasDebitoPesos
	 *            the cuentas debito pesos
	 * @param cuentasDebitoDolares
	 *            the cuentas debito dolares
	 * @return the respuesta
	 */
	Respuesta<LicitacionesVigentesOutDTO> licitacionesVigentes(Cliente cliente,
			List<CuentasAdhesionDebitoView> cuentasDebitoPesos, List<CuentasAdhesionDebitoView> cuentasDebitoDolares);

	/**
	 * Obtiene las licitaciones vigentes para el cliente recibido.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(Cliente cliente,
			NuevaLicitacionViewReq request);

	/**
	 * Obtiene los datos para iniciar la suscripcion a una licitacion. Devuelve una
	 * lista de cuentas titulo con sus licitaciones dentro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param cuentasDebitoPesos
	 *            the cuentas debito pesos
	 * @param cuentasDebitoDolares
	 *            the cuentas debito dolares
	 * @return the respuesta
	 */
	Respuesta<NuevaLicitacionDTOResponse> nuevaLicitacion(Cliente cliente, NuevaLicitacionViewReq request,
			List<CuentasAdhesionDebitoView> cuentasDebitoPesos, List<CuentasAdhesionDebitoView> cuentasDebitoDolares);

	/**
	 * Obtiene los datos para iniciar la suscripcion a una licitacion. Devuelve
	 * una lista de cuentas titulo con sus licitaciones dentro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<NuevaLicitacionDTOResponseBPriv> nuevaLicitacionBPriv(Cliente cliente, NuevaLicitacionViewReq request, boolean esActionsheet)
			throws BusinessException;

	/**
	 * Realiza la reversa de una orden.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ReversarOrdenResponse> reversarOrdenLicitacion(ReversaRequestView request, Cliente cliente);

	/**
	 * Simular licitacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanco
	 *            the tipo banco
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionViewResponse> simularLicitacion(Cliente cliente, SimularLicitacionRequest request,
			String tipoBanco);

	/**
	 * Configurar licitacion reinvertir.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param banca
	 *            the banca
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionOutView> configurarLicitacionReinvertir(Cliente cliente,
			ConfigurarLicitacionViewReq request, String banca);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param banca
	 *            the banca
	 * @return the respuesta
	 */
	Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request, Cliente cliente,
			String banca);

	/**
	 * Obtener tenencia titulos RTL.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaTitulosOutDTO> obtenerTenenciaTitulosRTL(Cliente cliente);

	/**
	 * Condiciones generales cuenta custodia.
	 *
	 * @param cliente
	 *            the cliente
	 * @param condicionesAceptadas
	 *            the condiciones aceptadas
	 * @return the respuesta
	 */
	Respuesta<CondicionesGeneralesCuentaCustodiaDTO> condicionesGeneralesCuentaCustodia(Cliente cliente,
			boolean condicionesAceptadas);

	/**
	 * Establecer condiciones aceptadas.
	 *
	 * @param nup
	 *            the nup
	 * @param banca
	 *            the banca
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionOutView> establecerCondicionesAceptadas(String nup, String banca);

	/**
	 * Firmar cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<String> firmarCuentas(Cliente cliente, FirmarCuentasViewRequest request);

	/**
	 * Consulta operaciones de Compra y de Venta.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesDTO> consultarOperacionesText(ConsultaOperaciones viewRequest);

	/**
	 * Listar comprobantes CNV.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest);

	/**
	 * Obtener comprobante CNV.
	 *
	 * @param idComprobante
	 *            the id comprobante
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(String idComprobante);

	/**
	 * Obtener saldo cuentas custodia B priv.
	 *
	 * @param cliente
	 *            the cliente
	 * @param moneda
	 *            the moneda
	 * @return the obtener saldo cuentas custodia response
	 * @throws BusinessException
	 *             the business exception
	 */
	ObtenerSaldoCuentasCustodiaResponse obtenerSaldoCuentasCustodiaBPriv(Cliente cliente, String moneda)
			throws BusinessException;

	// Respuesta<InicioOrdenesCompra> inicioOrdenesCompra(Cliente cliente);

	/**
	 * Consultar ordenes Titulos dependiendo la banca.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @param banca
	 *            the banca
	 * @return the respuesta
	 */
	Respuesta<ConsultaOrdenesDTO> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest, Cliente cliente,
			TipoBancaEnum banca);

	/**
	 * Obtener tenencia titulos BP.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaTitulosOutDTO> obtenerTenenciaTitulosBP(Cliente cliente);

	/**
	 * Buscador ordenes de compra.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<DatosConsultaEspeciesResponse> busquedaOrdenesCompra(BusquedaOrdenesCompra request, Cliente cliente,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * Configurar ordenes de compra.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param cuentasPesos
	 *            the cuentas pesos
	 * @param cuentasDolares
	 *            the cuentas dolares
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(ConfiguracionOrdenesCompraRequest request,
			Cliente cliente, List<CuentasAdhesionDebitoView> cuentasPesos,
			List<CuentasAdhesionDebitoView> cuentasDolares, TipoBancaEnum tipoBancaEnum);
	
	/**
	 * Configurar ordenes de compra Banca Privada.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param cuentasOperativa
	 *            the cuentas operativa
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraBPriv(ConfiguracionOrdenesCompraRequest request,
			Cliente cliente, CuentasAdhesionDebitoView cuentasOperativa, TipoBancaEnum tipoBancaEnum);

	/**
	 * Confirmar orden compra.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<CompraVtaTitulosResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request, Cliente cliente,
			String tipoOperacion, TipoBancaEnum tipoBancaEnum);
	
	/**
	 * Finalizar Reversar Orden.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @param bancaPersonal
	 *            the banca personal
	 * @return the respuesta
	 */
	Respuesta<FinalizarReversarOrdenDTO> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest, Cliente cliente, TipoBancaEnum bancaPersonal);

	/**
	 * Obtener poder compra.
	 *
	 * @param response
	 *            the response
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the configuracion ordenes compra response
	 */
	ConfiguracionOrdenesCompraResponse obtenerPoderCompra(ConfiguracionOrdenesCompraResponse response,
			ConfiguracionOrdenesCompraRequest request, Cliente cliente, TipoBancaEnum tipoBancaEnum);

	Respuesta<Reporte> obtenerTenenciasReporte(List<TenenciaTitulosCuentaDTO> listaTenencias, TipoBancaEnum tipoBanca, Cliente cliente);
	
	Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanje(LicitacionCanjeRequest request, Cliente cliente, String banca);
	
	/**
	 * Simular licitacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanco
	 *            the tipo banco
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(Cliente cliente, SimularLicitacionCanjeRequest request);
	
	/**
	 * Finalizar licitacion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param banca
	 *            the banca
	 * @return the respuesta
	 */
	Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request, Cliente cliente,
			String banca);
	
	/**
	 * Simular licitacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanco
	 *            the tipo banco
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(Cliente cliente, SimularLicitacionCanjeRequest request);
}
