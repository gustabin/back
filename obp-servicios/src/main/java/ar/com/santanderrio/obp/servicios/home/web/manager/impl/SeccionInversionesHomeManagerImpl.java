/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionInversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.Menu;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class SeccionInversionesHomeManagerImpl.
 */
@Component
public class SeccionInversionesHomeManagerImpl extends AbstractSeccionHomeManager
		implements SeccionInversionesHomeManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SeccionInversionesHomeManagerImpl.class);

	/** The inversiones. */
	private static final String INVERSIONES = "Inversiones";

	/** The tenencia consolidada. */
	private static final String TENENCIA_CONSOLIDADA = "Tenencia Consolidada";

	/** The plazo fijo. */
	private static final String PLAZO_FIJO = "Plazos Fijos";

	/** The fondos comunes de inversion. */
	private static final String FONDOS_COMUNES_DE_INVERSION = "Fondos Comunes de Inversión";

	/** The titulos. */
	private static final String TITULOS = "Títulos Valores";

	/** The Constant ANALISIS_DE_MI_CARTERA. */
	private static final String ANALISIS_DE_MI_CARTERA = "Análisis de mi Cartera";

	/** The servicios inversion. */
	private static final String SERVICIOS_PROGRAMACION_INVERSIONES= "Servicios y Programación";


	/** The consultas controller home BO. */
	@Autowired
	private InversionesControllerHomeBO inversionesControllerHomeBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    

	@Autowired
	private InversionWSHelper inversionWSHelper;
	

	
	/**
	 * Obtener seccion inversiones.<br/>
	 * Si la accion tiene permiso de visibilidad se cargan los item segun los
	 * productos y los permisos de la base.<br/>
	 * Si no tiene visibilidad se muestra el mensaje y no se cargan items.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the seccion view
	 */

	public SeccionView obtenerSeccion(Cliente cliente) {
		ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INVERSION);
		if (moduloPermiso.isModuloOculto()) {
			LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
					AccionController.IR_INVERSION);
			return null;
		} else {
			List<SeccionView> items = new ArrayList<SeccionView>();
			SeccionView seccion = new SeccionView();
			seccion.setTitulo(INVERSIONES);
			seccion.setAccion(AccionController.IR_INVERSION.getDescripcion());
			if (moduloPermiso.tienePermisoDeVisibilidad()) {
				agregarItem(inversionesControllerHomeBO.aplicaConsolidado(cliente), items,
						AccionController.IR_INICIO_TENENCIA_CONSOLIDADA, TENENCIA_CONSOLIDADA);
				agregarItem(inversionesControllerHomeBO.aplicaPlazoFijo(cliente), items,
						AccionController.IR_INICIO_PLAZOS_FIJOS, PLAZO_FIJO);
				agregarItem(inversionesControllerHomeBO.aplicaFondos(cliente), items,
						AccionController.IR_INICIO_FONDOS_COMUNES_INVERSION, FONDOS_COMUNES_DE_INVERSION);
				agregarItem(inversionesControllerHomeBO.aplicaTitulos(cliente), items,
						AccionController.IR_INICIO_TITULOS, TITULOS);
				agregarItem(inversionesControllerHomeBO.aplicaPyl(cliente), items,
						AccionController.IR_INICIO_ANALISIS_CARTERA, ANALISIS_DE_MI_CARTERA);
				agregarItem(inversionesControllerHomeBO.aplicaServiciosInversion(cliente), items,
                        AccionController.IR_INICIO_SERVICIOS_DE_INVERSION, SERVICIOS_PROGRAMACION_INVERSIONES);
				
				List<Menu> botonesDinamicos = new ArrayList<Menu>();
				try {
					botonesDinamicos = inversionWSHelper.getItemDinamico(cliente);
					for(Menu boton:botonesDinamicos) {
						agregarItem(inversionesControllerHomeBO.aplicaDinamico(cliente),items,
								boton.getLink(), boton.getTitulo());
					}
				} catch (DAOException e) {
					LOGGER.error("Error al cargar items dinámicos de inversiones", e);
				}
				
				seccion.setItems(items);
			} else if (moduloPermiso.muestraMensaje()) {
				seccion.setMensajeNoDisponible(moduloPermiso.getMensaje());
			}
			return seccion;
		}
	}

    @Override
    public Respuesta<Void> notificarAccesoServiciosDeInversion() {
        estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_SERVICIOS_DE_INVERSIONES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(null);
    }

}
