/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;

/**
 * The Interface NuevaRecargaManager.
 *
 * @author florencia.n.martinez
 */
public interface NuevaRecargaManager {

	/**
	 * Obtengo Confirmacion del pago nueva recarga.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionRecargaView> obtenerConfirmacionNuevaRecarga(
			ConfirmacionRecargaView datosConfirmacionRecarga);

	/**
	 * Se graba estadistica al visualizar el comprobante.
	 *
	 * @return true, if successful
	 */
	boolean estadisticaVerComprobante();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDF();
	
	/**
	 * Continuar Pago – Carga en sesion el hash del pago.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return the respuesta
	 */
	Respuesta<Void> continuarPago(ConfirmacionRecargaView nuevoPago);
	
	/**
	 * Agregar celular.
	 *
	 * @param nuevoNumero the nuevo numero
	 * @return the respuesta
	 */
	Respuesta<Void> agregarCelular(CelularView nuevoNumero);
	
	/**
	 * Eliminar celular.
	 *
	 * @param nuevoNumero the nuevo numero
	 * @return the respuesta
	 */
	Respuesta<Void> eliminarCelular(CelularView nuevoNumero);

	/**
	 * Actualizar alias celular.
	 *
	 * @param nuevoNumero the nuevo numero
	 * @return the respuesta
	 */
	Respuesta<Void> actualizarAliasCelular(CelularView nuevoNumero);
	
	Respuesta<List<CelularView>> obtenerCelulares();
	
	/**
	 * Obtener alias.
	 *
	 * @param numeroCelular the numero celular
	 * @return the respuesta
	 */
	Respuesta<String> obtenerAlias(String numeroCelular);
	
}
