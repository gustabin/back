/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.route;

import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.route.Route;

/**
 * The Class DebitoAutomaticoRoute.
 * 
 * @author ignacio.valek
 */
public class DebitoAutomaticoRoute extends Route<Respuesta<List<PagoPendiente>>> {

	/** The pagos pendientes BO. */
	PagosPendientesBO pagosPendientesBO;

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Instantiates a new debito automatico route.
	 */
	public DebitoAutomaticoRoute() {
		super();
		ApplicationContext ctx = ContextHolder.getContext();
		this.pagosPendientesBO = ctx.getBean(PagosPendientesBO.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.route.Route#onCall()
	 */
	@Override
	public Respuesta<List<PagoPendiente>> onCall() {
		return pagosPendientesBO.obtenerDebitosAutomaticos(cliente);
	}

	/**
	 * Gets the pagos pendientes BO.
	 *
	 * @return the pagos pendientes BO
	 */
	public PagosPendientesBO getPagosPendientesBO() {
		return pagosPendientesBO;
	}

	/**
	 * Sets the pagos pendientes BO.
	 *
	 * @param pagosPendientesBO
	 *            the new pagos pendientes BO
	 */
	public void setPagosPendientesBO(PagosPendientesBO pagosPendientesBO) {
		this.pagosPendientesBO = pagosPendientesBO;
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
