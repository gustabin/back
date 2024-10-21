package ar.com.santanderrio.obp.servicios.debinws.manager;


import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.ConfiguracionGrillaDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultaDetalleDebinWSOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.DebinWSEliminarOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;


/**
 * DebinWSManager.
 */
public interface DebinWSManager {


    /**
	 * Consulta debin.
	 *
	 * @param consultaDebinInView
	 *            the consulta debin in view
	 * @return the respuesta
	 */
    Respuesta<ConsultaDebinWSOutView> consultaDebin(ConsultaDebinWSInView consultaDebinInView);

    /**
	 * Consulta detalle debin.
	 *
	 * @param consultaDetalleDebinWSOutView
	 *            the consulta detalle debin WS out view
	 * @return the respuesta
	 */
    Respuesta<ConsultaDetalleDebinWSOutView> consultaDetalleDebin(ConsultaDetalleDebinWSInView consultaDetalleDebinWSOutView);

    /**
     * descargarComprobante
     * @return
     */
    Respuesta<Reporte> descargarComprobante(String tipo);

    /**
     * Eliminar debin.
     *
     * @return the respuesta
     */
    Respuesta<DebinWSEliminarOutView> eliminarDebin();

    /**
     * pagarDebin
     * @return
     */
	Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView);

	/**
     * pagarDebin
     * @return
     */
    Respuesta<RechazarDebinWSOutDTO> rechazarDebin();

	/**
	 * Devuelve las operaciones habilitadas para el action sheet y los combos
	 * de estado para la grilla de debin
	 * 
	 * @return
	 */
	Respuesta<ConfiguracionGrillaDebinWSView> configuracionGrillaDebinWSView();

}