/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarClaveBilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;

/**
 * The Interface AdhesionBilleteraBO.
 */
public interface AdhesionBilleteraBO {

	/**
	 * Administrar clave.
	 *
	 * @param dto
	 *            the dto
	 * @param mode
	 *            the mode
	 * @return the respuesta
	 */
	Respuesta<Void> administrarClave(AdministrarClaveBilleteraInDTO dto, String mode);

	/**
	 * Alta usuario.
	 *
	 * @param dto
	 *            the dto
	 * @param registroSesion
	 *            the registro sesion
	 * @return the respuesta
	 */
	Respuesta<BilleteraDTO> altaUsuario(BilleteraInDTO dto, RegistroSesion registroSesion);

	/**
	 * Generar comprobante.
	 * 
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobante();

}