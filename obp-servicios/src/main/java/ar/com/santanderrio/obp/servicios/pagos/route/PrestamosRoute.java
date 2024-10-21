/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.route;

import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;
import ar.com.santanderrio.obp.servicios.route.Route;

/**
 * The Class PrestamosRoute.
 * 
 * @author ignacio.valek
 */
public class PrestamosRoute extends Route<Respuesta<List<PagoPendiente>>> {

	/** The pagos service. */
	private PagosService pagosService;

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Instantiates a new prestamos route.
	 */
	public PrestamosRoute() {
		super();
		ApplicationContext ctx = ContextHolder.getContext();
		this.pagosService = ctx.getBean(PagosService.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.route.Route#onCall()
	 */
	@Override
	public Respuesta<List<PagoPendiente>> onCall() {
		return pagosService.getPrestamosPendientes(cliente);
	}

	/**
	 * Gets the pagos service.
	 *
	 * @return the pagos service
	 */
	public PagosService getPagosService() {
		return pagosService;
	}

	/**
	 * Sets the pagos service.
	 *
	 * @param pagosService
	 *            the new pagos service
	 */
	public void setPagosService(PagosService pagosService) {
		this.pagosService = pagosService;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
