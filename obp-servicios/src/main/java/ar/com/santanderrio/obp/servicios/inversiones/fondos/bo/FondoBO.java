/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.exception.CargaTenenciaException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosBPrivOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosRTLOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaFondosSuscritosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TipoBancaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConsultaHorariosYHonorariosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;

/**
 * Gestiona la logica de negocio relacionada a Fondo.
 * 
 * @author
 *
 */
public interface FondoBO {

	/**
	 * realiza suscribir en Fondo.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<FondoDTO> suscribir(FondoDTO dtoRequest, Cliente cliente);

	/**
	 * Obtener fondos suscriptos Y disponibles.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @param fondoDisponibles
	 *            the fondo disponibles
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente, boolean fondoDisponibles, boolean esUltimosMovimientos);

	/**
	 * Obtener datos de Configuracion de suscripcion de fondos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Boolean> obtenerMarcaContrato(Cliente cliente) throws BusinessException;

	/**
	 * aceptarContrato de Configuracion de suscripcion de fondos.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Boolean> aceptarContrato(Cliente cliente) throws BusinessException;

	/**
	 * Recupera las tenenecias del cliente para una banca en particular, cuando
	 * se hace el cambio de banca privada a retail, se graba la estadistica
	 * correspondiente.
	 *
	 * @param tipoBanca
	 *            the tipo banca
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciasFondoDTO> obtenerTenencias(TipoBancaDTO tipoBanca, Cliente cliente);

	/**
	 * Simular suscripcion.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FondoDTO> simularSuscripcion(FondoDTO requestDTO, Cliente cliente);

	/**
	 * Realiza la confirmación de una suscripción.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FondoDTO> finalizarSuscribirFondos(FondoDTO requestDTO, Cliente cliente);

	/**
	 * Banca privada: Devuelve las cuentas operativas con su tenencia y con su
	 * cuenta titulo asociada el total de los fondos disponibles para suscribir.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @param fondoDisponibles
	 *            the fondo disponibles
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponiblesBPriv(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente, boolean fondoDisponibles);

	/**
	 * Obtiene los saldos actualizados de una cuenta operativa para banca
	 * privada.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param cliente
	 *            the cliente
	 * @param requiereSaldo
	 *            the requiere saldo
	 * @return the respuesta
	 */
	Respuesta<CuentasAdhesionDebitoView> obtenerSaldosCuentaOperativa(String nroCuenta, Cliente cliente,
			boolean requiereSaldo);

	/**
	 * Realiza la simulacion de una suscripcion.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<SimularSuscripcionOutDTO> simularSuscripcionBPriv(SimulacionInDTO requestDTO, Cliente cliente);

	/**
	 * Realiza la ejecucion de una suscripcion de fondos.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<SuscripcionOutDTO> finalizarSuscribirFondosBPriv(SuscripcionInDTO request, Cliente cliente);

	/**
	 * Devuelte una lista con la relacion entre las cuentas titulos y las
	 * operativas .
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<CuentaTituloDTO> obtenerRelacionOperativaTitulo(Cliente cliente) throws BusinessException;

	/**
	 * Arma una lista de cuentas titulo con su tenencia de fondos y su cuenta
	 * operativa asociada. Variable excluirDeshabilitados = true, devuelve la
	 * tenencia excluyendo a los fondos que estan deshabilitados.
	 *
	 * @param list
	 *            the list
	 * @param condicion
	 *            the condicion
	 * @throws CargaTenenciaException
	 *             the carga tenencia exception
	 */
	void cargarTenencia(List<CuentaTituloDTO> list, Predicate condicion) throws CargaTenenciaException;

	/**
	 * vaciar la cache de fondos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarCache();

	/**
	 * Consultar cotizaciones.
	 *
	 *
	 * @return Respuesta<CotizacionDeFondosView>
	 */
	Respuesta<CotizacionDeFondosView> consultarCotizaciones();

	/**
	 * Obtener fondos horarios Y honorarios.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultaHorariosYHonorariosView> obtenerFondosHorariosYHonorarios();

	/**
	 * Devuelve los ultimos movimientos de un fondo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<MovimientosFondoOutEntity> consultarMovimientos(Cliente cliente, MovimientosInView viewRequest);
	
	/**
     * Devuelve los ultimos movimientos de un fondo citi.
     *
     * @param cliente
     *            the cliente
     * @param viewRequest
     *            the view request
     * @return the respuesta
     */
	Respuesta<MovimientosFondoOutEntity> consultarMovimientosCiti(Cliente cliente, MovimientosInView viewRequest);
	
	/**
	 * Devuelve los movimientos de un fondo en un rango de fechas.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @param fondoSeleccionado
	 *            the fondo seleccionado
	 * @return the respuesta
	 */
	Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest, Cliente cliente, TenenciaFondosSuscritosDTO fondoSeleccionado);

	/**
	 * Formatear el Valor Cuotaparte eliminadondo ultimos decimales para no ser
	 * rendondeados en front.
	 *
	 * @param imp
	 *            the imp
	 * @param cantDecimales
	 *            the cant decimales
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	BigDecimal convertirStrToBigDecimalVC(String imp, int cantDecimales) throws ImporteConvertException;

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca personal.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<GraficoFondosRTLOutDTO> obtenerGraficosRTL(Cliente cliente);

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca personal.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<GraficoFondosBPrivOutDTO> obtenerGraficosBpriv(Cliente cliente);
	
	Respuesta<Reporte> obtenerFondosReporte (List<CuentaConTenenciaDTO> listaCuentasFondos, TipoBancaEnum tipoBanca, Cliente cliente);
	
	String obtenerReglamento(String codFondo) throws BusinessException ;

	Boolean validarHorarioDeFondo(String horarioFondo);

	List<DatoItemMensaje> crearItemsWarningFueraHorarioAgendarFondo();
	
}
