/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.route.Route;
import ar.com.santanderrio.obp.servicios.route.RouteBuilder;
import ar.com.santanderrio.obp.servicios.route.RouteBuilderException;

/**
 * Route Builder de Pagos Totales,
 * <P>
 * Permite orquestar asincronicamente los servicios de Pago Mis Cuentas,
 * Tarjetas y Prestamos para la obtencion de los pagos totales.
 * </P>
 *
 * @author ignacio.valek
 * @see {@link RouteBuilder}
 * @see {@link Route}
 * @since Aug 24, 2016
 */
public class PagosTotalesRouteBuilder extends RouteBuilder<List<PagoPendiente>> {

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosTotalesRouteBuilder.class);

	/** The Constant TIMESLEEP. */
	private static final long TIMESLEEP = 300;
	/** The Constant DESCRIPCION_LOG. */
	private static final String DESCRIPCION_LOG = "Descripcion: {}.";
	/** The Constant ERROR_LOG. */
	private static final String ERROR_LOG = "Ha ocurrido un error en servicio {}. " + DESCRIPCION_LOG;

	/** The route pagos mis cuentas. */
	PagosMisCuentasRoute routePagosMisCuentas = null;

	/** The route tarjetas. */
	TarjetasRoute routeTarjetas = null;

	/** The route tarjetas. */
	TarjetasRecargablesRoute routeTarjetasRecargables = null;

	/** The route prestamos. */
	PrestamosRoute routePrestamos = null;

	/** The route debitos. */
	DebitoAutomaticoRoute routeDebitos = null;

	/** The resp pagos mis cuentas. */
	Future<Respuesta<List<PagoPendiente>>> respPagosMisCuentas = new AsyncResult<Respuesta<List<PagoPendiente>>>(null);

	/** The resp tarjetas. */
	Future<Respuesta<List<PagoPendiente>>> respTarjetas = new AsyncResult<Respuesta<List<PagoPendiente>>>(null);

	/** The resp tarjetas recargables. */
	Future<Respuesta<List<PagoPendiente>>> respTarjetasRecargables = new AsyncResult<Respuesta<List<PagoPendiente>>>(
			null);

	/** The resp prestamos. */
	Future<Respuesta<List<PagoPendiente>>> respPrestamos = new AsyncResult<Respuesta<List<PagoPendiente>>>(null);

	/** The resp debitos. */
	Future<Respuesta<List<PagoPendiente>>> respDebitos = new AsyncResult<Respuesta<List<PagoPendiente>>>(null);

	/** The estadistica manager. */
	EstadisticaManager estadisticaManager;

	/**
	 * Instantiates a new pagos totales route builder.
	 *
	 * @param cliente
	 *            the cliente
	 * @param estadisticaManager
	 *            the estadistica manager
	 */
	public PagosTotalesRouteBuilder(Cliente cliente, EstadisticaManager estadisticaManager) {
		super();
		this.routePagosMisCuentas = new PagosMisCuentasRoute();
		this.routePagosMisCuentas.setCliente(cliente);
		this.routePrestamos = new PrestamosRoute();
		this.routePrestamos.setCliente(cliente);
		this.routeTarjetas = new TarjetasRoute();
		this.routeTarjetas.setCliente(cliente);
		this.routeTarjetasRecargables = new TarjetasRecargablesRoute();
		this.routeTarjetasRecargables.setCliente(cliente);
		this.routeDebitos = new DebitoAutomaticoRoute();
		this.routeDebitos.setCliente(cliente);
		this.estadisticaManager = estadisticaManager;
	}

	/**
	 * Process.
	 *
	 * @throws RouteBuilderException
	 *             the route builder exception
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.route.RouteBuilder#process()
	 */
	@Override
	public void process() throws RouteBuilderException {
		super.configure();

		respPagosMisCuentas = super.getExecutor().submit(routePagosMisCuentas);
		respDebitos = super.getExecutor().submit(routeDebitos);
		respTarjetas = super.getExecutor().submit(routeTarjetas);
		respTarjetasRecargables = super.getExecutor().submit(routeTarjetasRecargables);

		respPrestamos = super.getExecutor().submit(routePrestamos);

		while (!respPagosMisCuentas.isDone() || !respTarjetas.isDone() || !respPrestamos.isDone()
				|| !respDebitos.isDone() || !respTarjetasRecargables.isDone()) {
			try {
				Thread.sleep(TIMESLEEP);
			} catch (InterruptedException e) {
				LOGGER.error(ERROR_LOG, "Error Thread.sleep", e);
			}
		}
	}

	/**
	 * Manage responses.
	 *
	 * @return the respuesta
	 * @throws RouteBuilderException
	 *             the route builder exception
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.route.RouteBuilder#manageResponses()
	 */
	@Override
	public Respuesta<List<PagoPendiente>> manageResponses() throws RouteBuilderException {
		List<Respuesta<List<PagoPendiente>>> listaRespuestas = new ArrayList<Respuesta<List<PagoPendiente>>>();
		try {

		    if(respPagosMisCuentas.get() != null) {
		        grabarEstadisticaPagos(respPagosMisCuentas.get().getEstadoRespuesta(),
		                EstadisticasConstants.VENCIMIENTOS_PAGOMISCUENTA);
		        listaRespuestas.add(respPagosMisCuentas.get());
		    }
		    if(respDebitos.get() != null) {
		        grabarEstadisticaPagos(respDebitos.get().getEstadoRespuesta(),
		                EstadisticasConstants.PAGOS_ADHERIDOS_DEBITO_AUTOMATICO);
		        listaRespuestas.add(respDebitos.get());
		    }

		    if(respTarjetas.get() != null) {
		        grabarEstadisticaPagos(respTarjetas.get().getEstadoRespuesta(),
		                EstadisticasConstants.VENCIMIENTOS_TARJETAS);
		        listaRespuestas.add(respTarjetas.get());
		        listaRespuestas.add(respTarjetasRecargables.get());
		    }

		    if(respPrestamos.get() != null) {
			grabarEstadisticaPagos(respPrestamos.get().getEstadoRespuesta(),
					EstadisticasConstants.VENCIMIENTOS_PRESTAMOS);
			listaRespuestas.add(respPrestamos.get());
		    }

		} catch (InterruptedException e) {
			LOGGER.error(ERROR_LOG, "InterruptedException", e);
		} catch (ExecutionException e) {
			LOGGER.error(ERROR_LOG, "ExecutionException", e, e.getStackTrace());
		}
		Respuesta<List<PagoPendiente>> respuesta = unificarRespuesta(listaRespuestas);
		// se ordena de nuevo en PagosManagerImpl. REVISAR
		// Collections.sort(respuesta.getRespuesta())
		return respuesta;

	}

	/**
	 * Grabar estadistica pagos.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 */
	private void grabarEstadisticaPagos(EstadoRespuesta estadoRespuesta, String codigoTransaccion) {
		String codigoError;
		switch (estadoRespuesta) {
		case OK:
			codigoError = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
			break;
		case WARNING:
		    codigoError = EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING;
		    break;
		case ERROR:
		default:
			codigoError = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
			break;
		}
		estadisticaManager.add(codigoTransaccion, codigoError);
	}

	/**
	 * Unificar respuesta.
	 *
	 * @param listaRespuestas
	 *            the lista respuestas
	 * @return the respuesta
	 */
	private Respuesta<List<PagoPendiente>> unificarRespuesta(List<Respuesta<List<PagoPendiente>>> listaRespuestas) {
		int errores = 0;
		boolean warning = false;
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		respuesta.setRespuesta(new ArrayList<PagoPendiente>());
		for (Respuesta<List<PagoPendiente>> respuestaActual : listaRespuestas) {
			//
			if (respuestaActual.getEstadoRespuesta().equals(EstadoRespuesta.OK)
					|| respuestaActual.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				if (respuestaActual.getRespuesta() != null) {
					respuesta.getRespuesta().addAll(respuestaActual.getRespuesta());
				}
				if (respuestaActual.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
					warning = true;
					if (respuesta.getItemsMensajeRespuesta() == null) {
						List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
						respuesta.setItemMensajeRespuesta(items);
						if (respuestaActual.getItemsMensajeRespuesta() != null
								&& respuestaActual.getItemsMensajeRespuesta().size() > 0) {
							respuesta.getItemsMensajeRespuesta().add(respuestaActual.getItemsMensajeRespuesta().get(0));
						}
					}
				}
			} else {
				errores++;
			}
		}
		//
		determinarEstadoRespuesta(respuesta, errores, warning, listaRespuestas.size());
		return respuesta;
	}

	/**
	 * Determinar estado respuesta.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param errores
	 *            the errores
	 * @param warning
	 *            the warning
	 * @param cantidadRegistros
	 *            the cantidad registros
	 */
	private void determinarEstadoRespuesta(Respuesta<List<PagoPendiente>> respuesta, int errores, boolean warning,
			int cantidadRegistros) {
		if (cantidadRegistros == 0 || (errores == 0 && !warning)) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			return;
		}
		if (errores == cantidadRegistros) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return;
		}
		if (respuesta.getRespuesta().size() == 0) {
			LOGGER.info("Error parcial sin deuda para mostrar, se representa con un error generico");
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return;
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			return;
		}
	}
}
