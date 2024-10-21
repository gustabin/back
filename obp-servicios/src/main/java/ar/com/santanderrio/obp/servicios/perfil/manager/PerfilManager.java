/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;
import ar.com.santanderrio.obp.servicios.perfil.view.CambioNombreView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConfirmarMailView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.view.LogoutAppView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilDetalleDeudorView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;

/**
 * The Interface PerfilManager.
 */
public interface PerfilManager {

	/**
	 * Obtener perfil.
	 *
	 * @return the respuesta
	 */
	Respuesta<PerfilView> obtenerPerfil();

	/**
	 * Obtener detalle deudor.
	 *
	 * @param consultaPerfil
	 *            the consulta perfil
	 * @return the respuesta
	 */
	Respuesta<PerfilDetalleDeudorView> obtenerDetalleDeudor(ConsultaPerfil consultaPerfil);

	Respuesta<LogoutAppView> logoutAppConfiguracion();

	Respuesta<LogoutAppView> logoutAppFeedback();
	
	Respuesta<ConfirmarMailView> confirmarEmail(ConfirmarMailViewIn confirmarMailIn);
	
	Respuesta<List<BonificacionVigenteView>> obtenerBonificaciones();
	
	Respuesta<Void> grabarEstadisticaVerBonificaciones();
	
	Respuesta<CambioNombreView> cambioNombreConfig();	

	Respuesta<CambioNombreView> cambioNombreFeedback(String nombreElegido);
	
}
