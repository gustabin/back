/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.route;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.route.Route;

/**
 * The Class PagosMisCuentasRoute.
 * 
 * @author ignacio.valek
 */
public class PagosMisCuentasRoute extends Route<Respuesta<List<PagoPendiente>>> {

	/** The pagos pendientes BO. */
	PagosPendientesBO pagosPendientesBO;

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Instantiates a new pagos mis cuentas route.
	 */
	public PagosMisCuentasRoute() {
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

		// LLamada a banelco para consulta de cuentas
		Respuesta<List<CuentaPagoMisCuentas>> respuestaCuentasBanelco = pagosPendientesBO
				.obtenerCuentasHabilitadas(cliente);
		// Si hubo error en banelco
		if (respuestaCuentasBanelco.getEstadoRespuesta() == EstadoRespuesta.ERROR) {
			Respuesta<List<PagoPendiente>> respuestaPagoMisCuentas = new Respuesta<List<PagoPendiente>>();
			respuestaPagoMisCuentas.setItemMensajeRespuesta(respuestaCuentasBanelco.getItemsMensajeRespuesta());
			respuestaPagoMisCuentas.setRespuestaVacia(respuestaCuentasBanelco.isRespuestaVacia());
			respuestaPagoMisCuentas.setEstadoRespuesta(respuestaCuentasBanelco.getEstadoRespuesta());
			return respuestaPagoMisCuentas;
		} else {
			// Si no hay cuentas habilitadas banelco NO consulta a Pagos Mis
			// Cuentas
			if (respuestaCuentasBanelco.getRespuesta().isEmpty()) {
				// Devuelvo una respuesta OK pero sin elementos
				Respuesta<List<PagoPendiente>> respuestaPagoMisCuentas = new Respuesta<List<PagoPendiente>>();
				respuestaPagoMisCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
				respuestaPagoMisCuentas.setRespuesta(new ArrayList<PagoPendiente>());
				return respuestaPagoMisCuentas;
			}
			return pagosPendientesBO.obtenerPagosPendientes(cliente);
		}
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
