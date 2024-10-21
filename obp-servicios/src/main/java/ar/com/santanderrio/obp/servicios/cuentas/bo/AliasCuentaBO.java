/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.Map;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;

/**
 * The Interface AliasCuentaBO.
 */
public interface AliasCuentaBO {

	/**
	 * Obtener alias en base al CBU.
	 *
	 * @param ip
	 *            the ip
	 * @param dni
	 *            the dni
	 * @param datosTerminal
	 *            the datos terminal
	 * @param datosTerminal2
	 *            the datos terminal 2
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> obtenerAliasCBU(String ip, String dni, String datosTerminal, String datosTerminal2);

	/**
	 * Obtener datos cliente.
	 *
	 * @param cbu
	 *            the cbu
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 */
	Respuesta<DetalleCBUView> obtenerDatosCliente(String cbu, String alias);

	/**
	 * Confirmar alta alias.
	 *
	 * @param detalle
	 *            the detalle
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> confirmarAltaAlias(DetalleCBUDTO detalle);

	/**
	 * Confirmar baja alias CBU.
	 *
	 * @param detalleCBUDTO
	 *            the detalle CBUDTO
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaCBUDTO> confirmarBajaAliasCBU(DetalleCBUDTO detalleCBUDTO);

	/**
	 * Obtener documento valido.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the detalle documento DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleDocumentoDTO obtenerDocumentoValidoDTO(Cliente cliente) throws DAOException;

	/**
	 * Obtener detalle documentos.
	 *
	 * @return the detalle documentos
	 */
	public Map<String, DetalleDocumentoDTO> obtenerDetalleDocumentos();

	/**
	 * Obtener cuit por detalle.
	 *
	 * @param detalleDocumento
	 *            the detalle documento
	 * @return the string
	 */
	String obtenerCuitPorDetalle(DetalleDocumentoDTO detalleDocumento);

}
