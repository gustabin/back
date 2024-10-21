package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.debinapi.dto.RecurrenceActionEnum;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithCategoryDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.ConsultaDebinOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.CreditoPorContracargoOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;

/**
 * The Interface DebinRecurrenteDAO.
 */
public interface DebinRecurrenteDAO {

	/**
	 * Obtener empresas.
	 *
	 * @param empresa the empresa
	 * @return the list
	 * @throws Exception the exception
	 */
	List<SellerWithCategoryDTO> obtenerEmpresas(String empresa) throws Exception;
	
	/**
	 * Obtener servicios.
	 *
	 * @param cuit the cuit
	 * @return the seller with provision DTO
	 * @throws Exception the exception
	 */
	SellerWithProvisionDTO obtenerServicios(String cuit) throws Exception;

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the recurrence DTO
	 * @throws Exception the exception
	 */
	RecurrenceDTO crearRecurrencia(CrearRecurrenciaView recurrencia) throws Exception;

	AccionRecurrenciaResponseView applyActionToRecurrence(RecurrenciaView recurrencia, RecurrenceActionEnum action,
														   String successfulMessage) throws Exception;

	ConsultaDebinOutEntity consultaDebin(Cliente cliente, String idDebin) throws DAOException;

	CreditoPorContracargoOutEntity creditoPorContracargo(Cliente cliente, String canal, String idContracargo) throws DAOException;

	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the reporte
	 */
	Reporte generarComprobantePDF(DatosComprobanteDebinRecurrente datosComprobante);
}
