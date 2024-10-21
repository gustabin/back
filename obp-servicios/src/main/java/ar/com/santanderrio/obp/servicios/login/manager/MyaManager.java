/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioGeneralSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;

/**
 * The Interface MyaManager.
 */
public interface MyaManager {

	/**
	 * Obtener estado mya.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<CredencialesMya> obtenerEstadoMya(Cliente cliente);

	/**
	 * Confirmar datos mya.
	 *
	 * @param datosMyaView
	 *            the datos mya view
	 * @return the respuesta
	 */
	Respuesta<CredencialesMya> confirmarDatosMya(DatosMyaView datosMyaView);

	/**
	 * Obtener mya.
	 *
	 * @param respuestaLoginResponseView
	 *            the respuesta login response view
	 * @return the respuesta
	 */
	Respuesta<LoginResponseView> obtenerMya(Respuesta<LoginResponseView> respuestaLoginResponseView);

	/**
	 * ** Obtiene el archivo pdf de los terminos y condiciones.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerTerminosCondicionesPDF();

	/**
	 * Inicio flujo suscripcion MyA.
	 *
	 * @return Respuesta<InicioGeneralSuscripcionMyAView>
	 */
	Respuesta<InicioGeneralSuscripcionMyAView> obtenerInicioMensajesAvisos();

	/**
	 * Inicio de producto MyA.
	 *
	 * @param producto
	 *            the producto
	 * @return Respuesta<InicioProductoSuscripcionMyAView>
	 */
	Respuesta<InicioProductoSuscripcionMyAView> obtenerInicioProductoMyA(String producto);

	/**
	 * Update mensajes.
	 *
	 * @param myaUpdateMensajeView
	 *            the mya update mensaje view
	 * @return the respuesta
	 */
	Respuesta<Void> updateMensajes(MyaUpdateMensajeView myaUpdateMensajeView);

}
