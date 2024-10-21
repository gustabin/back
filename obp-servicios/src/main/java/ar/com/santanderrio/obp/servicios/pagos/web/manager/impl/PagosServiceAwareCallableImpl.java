/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;

/**
 * The Class PagosServiceAwareCallableImpl.
 */
public class PagosServiceAwareCallableImpl extends RequestContextAwareCallable<Respuesta<List<PagoPendiente>>> {

	/** The pagos service. */
	private PagosService pagosService;

	/** The cliente. */
	private Cliente cliente;

	/** The servicio. */
	private int servicio;

	/** The Constant PAGOMISCUENTAS. */
	public static final int PAGOMISCUENTAS = 0;

	/** The Constant TARJETAS. */
	public static final int TARJETAS = 1;

	/** The Constant PRESTAMOS. */
	public static final int PRESTAMOS = 2;

	/**
	 * Instantiates a new pagos service aware callable impl.
	 */
	public PagosServiceAwareCallableImpl() {
		super();
		ApplicationContext ctx = ContextHolder.getContext();
		this.pagosService = ctx.getBean(PagosService.class);
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
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the servicio.
	 *
	 * @return the servicio
	 */
	public int getServicio() {
		return servicio;
	}

	/**
	 * Sets the servicio.
	 *
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(int servicio) {
		this.servicio = servicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.impl.
	 * RequestContextAwareCallable#onCall()
	 */
	@Override
	public Respuesta<List<PagoPendiente>> onCall() throws ServiceException {
		switch (servicio) {
		case PAGOMISCUENTAS:
			return pagosService.obtenerPagoPendiente(cliente);
		case TARJETAS:
			return pagosService.getTarjetasPendientes(cliente);
		case PRESTAMOS:
			return pagosService.getPrestamosPendientes(cliente);
		default:
			break;
		}
		return null;
	}

}
