package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoEncoladoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoEncoladoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaPrestamoView;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoPreaprobadoMonoproductoInOutView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ResultadoAltaSimulacionPreaprobadoView;

/**
 * Manager para prestamos preaprobados monoproducto
 * @author A309331
 *
 */
@Component
public interface PrestamoPreaprobadoManager {

	/**
	 * Simula un prestamo preaprobado monoproducto
	 * @param prestamoPreaprobado
	 * @return
	 */
	Respuesta<ResultadoAltaSimulacionPreaprobadoView> simularPrestamosPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobado);

	/**
	 * Realiza el alta de un prestamo preaprobado monoproducto
	 * @return
	 */
	Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio);

	/**
	 * Realiza el alta de un prestamo preaprobado monoproducto a partir de un prestamo encolado
	 * @return
	 */
	Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView);

	/**
	 * Realiza la baja de solicitud de un prestamo preaprobado monoproducto a partir de un prestamo encolado
	 * @return
	 */
	void eliminarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView, PrestamoEncoladoEntity prestamoEncoladoEntity) throws DAOException;
	
	/**
	 * Realiza el alta de la cola a liquidar en 48hs de un prestamo preaprobado monoproducto
	 * @return
	 */
	Respuesta<ResultadoAltaSimulacionPreaprobadoView> encolarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio);


	/**
	 * Descarga el comprobante del prestamo preaprobado monoproducto
	 * @return
	 */
	Respuesta<ReporteView> descargarPrestamoPreaprobadoMonoproducto();

	/**
	 * Obtener simulacion de prestamo
	 *
	 * @return the respuesta
	 */
	Respuesta<ResultadoAltaSimulacionPreaprobadoView> obtenerPrestamoPreaprobadoMonoproducto();

	/**
	 * Busca oferta de prestamo preaprobado desde deeplink
	 * @return
	 */
	Respuesta<Void> calcularOfertaPreaprobadoFromDeeplink();
}
