/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.route.Route;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.EncabezadoTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FormaDePagoTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.RecargaTarjetaManager;

/**
 * The Class TarjetasRecargablesRoute.
 */
public class TarjetasRecargablesRoute extends Route<Respuesta<List<PagoPendiente>>> {

	/**
	 * Instantiates a new tarjetas recargables route.
	 */
	public TarjetasRecargablesRoute() {
		ApplicationContext ctx = ContextHolder.getContext();
		this.recargaTarjetaManager = ctx.getBean(RecargaTarjetaManager.class);
		this.encabezadoTarjetasBO = ctx.getBean(EncabezadoTarjetasBO.class);

	}

	/** The sesion cliente. */

	private Cliente cliente;

	/** The recarga tarjeta manager. */
	@Autowired
	private RecargaTarjetaManager recargaTarjetaManager;

	/** The encabezado tarjetas BO. */
	@Autowired
	private EncabezadoTarjetasBO encabezadoTarjetasBO;

	/** The Constant TIPO_DESCRIPCION_TR. */
	private static final String TIPO_DESCRIPCION_TR = "Recarga - Tarjeta Recargable";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.route.Route#onCall()
	 */
	@Override
	public Respuesta<List<PagoPendiente>> onCall() {
		Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
		ArrayList<PagoPendiente> lista = new ArrayList<PagoPendiente>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(lista);
		try {

			Collection<? extends Cuenta> recargables = TarjetaBOUtils.filtrarRecargables(cliente.getCuentas());
			Iterator it = recargables.iterator();
			while (it.hasNext()) {
				Cuenta cuenta = (Cuenta) it.next();
				if (!TarjetaUtils.isRecargableAdicional(cuenta)) {
					Respuesta<AdhesionTarjeta> adhesionRespuesta = encabezadoTarjetasBO.obtenerAdhesionTarjeta(cliente,
							cuenta);
					AdhesionTarjeta adhesion = adhesionRespuesta.getRespuesta();
					if (FormaDePagoTarjetaEnum.SIN_AGENDA_PROGRAMADA.equals(adhesion.getFormaDePago())
							|| FormaDePagoTarjetaEnum.POR_CAJA.equals(adhesion.getFormaDePago())) {
						continue;
					}

					PagoPendiente pagoPendienteView = getPagoProgramadoTarjetaRecargable(cuenta, adhesion);
					Cuenta cuentaDebito = cliente
							.getCuentaPorNumero(StringUtils.leftPad(adhesion.getNroCuentaDebito(), 16, "0"));
					pagoPendienteView.setTipoCuentaDebito(cuentaDebito.getTipoCuentaEnum().getDescripcion());
					pagoPendienteView.setNroCuentaProducto(cuentaDebito.getNroCuentaProducto());
					pagoPendienteView
							.setNroCuentaDebito(ISBANStringUtils.formatearSucursal(cuentaDebito.getNroSucursal()) + "-"
									+ ISBANStringUtils.formatearNumeroCuenta(cuentaDebito.getNroCuentaProducto()));
					pagoPendienteView.setAliasCuentaDebito(cuentaDebito.getAlias());
					respuesta.getRespuesta().add(pagoPendienteView);
				}
			}
		} catch (Exception e) {
			logger.debug("error", e);
			respuesta.getRespuesta().clear();
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}

		return respuesta;
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

	/**
	 * Gets the recarga tarjeta manager.
	 *
	 * @return the recarga tarjeta manager
	 */
	public RecargaTarjetaManager getRecargaTarjetaManager() {
		return recargaTarjetaManager;
	}

	/**
	 * Sets the recarga tarjeta manager.
	 *
	 * @param recargaTarjetaManager
	 *            the new recarga tarjeta manager
	 */
	public void setRecargaTarjetaManager(RecargaTarjetaManager recargaTarjetaManager) {
		this.recargaTarjetaManager = recargaTarjetaManager;
	}

	/**
	 * Gets the encabezado tarjetas BO.
	 *
	 * @return the encabezado tarjetas BO
	 */
	public EncabezadoTarjetasBO getEncabezadoTarjetasBO() {
		return encabezadoTarjetasBO;
	}

	/**
	 * Sets the encabezado tarjetas BO.
	 *
	 * @param encabezadoTarjetasBO
	 *            the new encabezado tarjetas BO
	 */
	public void setEncabezadoTarjetasBO(EncabezadoTarjetasBO encabezadoTarjetasBO) {
		this.encabezadoTarjetasBO = encabezadoTarjetasBO;
	}

	/**
	 * Gets the pago programado tarjeta recargable.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param adhesion
	 *            the adhesion
	 * @return the pago programado tarjeta recargable
	 */
	private PagoPendiente getPagoProgramadoTarjetaRecargable(Cuenta cuenta, AdhesionTarjeta adhesion) {
		PagoPendiente pagoPendienteView = new PagoPendiente();

		pagoPendienteView.setFechaPagoProgramado(adhesion.getFechaDeProximaAgenda());
		pagoPendienteView.setVencimiento(adhesion.getFechaDeProximaAgenda());
		pagoPendienteView.setFrecuenciaRecarga(adhesion.getFormaDePago().getDescripcionCorta());
		pagoPendienteView.setFechaProxRecarga(ISBANStringUtils.formatearFecha(adhesion.getFechaDeProximaAgenda()));
		String numero = TarjetaUtils.obtenerNroTarjetaEnmascarada(cuenta);
		pagoPendienteView.setNombreEmpresa(cuenta.getTipoCuentaEnum().getDescripcion() + " " + numero);
		pagoPendienteView.setDescripcion(pagoPendienteView.getNombreEmpresa());
		pagoPendienteView.setNroCuentaOrigen(adhesion.getNroCuentaDebito());
		pagoPendienteView.setNroTarjetaCredito(numero);
		pagoPendienteView.setImporte(adhesion.getImporteDeAgendamiento());
		pagoPendienteView.setTipoPago(TipoDePagoEnum.TARJETARECARGABLEPROGRAMADO);
		pagoPendienteView.setDescripcion(TIPO_DESCRIPCION_TR);
		pagoPendienteView.setEditable(true);

		return pagoPendienteView;

	}

}
