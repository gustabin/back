/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaDTO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;

/**
 * The Interface CambioGrupoAfinidadBO.
 */
public interface CambioGrupoAfinidadBO {

//	Respuesta<GrupoAfinidadClienteDTO> comprobarGrupoAfinidadCliente(Cliente cliente);
	
	/**
 * Obtener cuentas tarjetas credito.
 *
 * @param isAfinidadAadvantage
 *            the is afinidad aadvantage
 * @return the respuesta
 */
Respuesta<List<Cuenta>> obtenerCuentasTarjetasCredito(boolean isAfinidadAadvantage);
	
	/**
	 * Builds the tarjetas asociadas.
	 *
	 * @param cuentasTarjetasCredito
	 *            the cuentas tarjetas credito
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<TarjetaAsociadaDTO>> buildTarjetasAsociadas(List<Cuenta> cuentasTarjetasCredito, Cliente cliente);
	
	/**
	 * Alta solicitud adhesion.
	 *
	 * @param nroSocioAdvantage
	 *            the nro socio advantage
	 * @param nup
	 *            the nup
	 * @param isUsuarioAdvantage
	 *            the is usuario advantage
	 * @return the respuesta
	 */
	Respuesta<String> altaSolicitudAdhesion(String nroSocioAdvantage, String nup, boolean isUsuarioAdvantage);
	
	/**
	 * Generar comprobante cambio grupo afinidad.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteCambioGrupoAfinidad(ComprobanteSolicitudCambioAfinidadView datosComprobante);
	
	Respuesta<String> obtenerGrupoAfinidadParaFlujos(String nroSocioAdvantage, EventosComercialesDTO ofertasComerciales);
}
