/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;

/**
 * OLYMPUS PrestamoOpenCreditBO.
 *
 * @author Silvina_Luque
 */
public interface PrestamoOpenCreditBO {

	/**
	 * Obtener prestamos.
	 *
	 * @param prestamoOpenCreditInDTO
	 *            the prestamo open credit in DTO
	 * @return Respuesta<List<PrestamoOpenCreditDTO>
	 */
	Respuesta<List<PrestamoOpenCreditDTO>> obtenerPrestamosOpenCredit(PrestamoOpenCreditInDTO prestamoOpenCreditInDTO);

	/**
	 * Obtener detalle de cuotas.
	 *
	 * @param consultaPagosMinimosInDTO
	 *            the consulta pagos minimos in DTO
	 * @return the respuesta
	 */
	Respuesta<DetallePagosMinimosOpenCreditDTO> obtenerDetallePagosMinimos(
			ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosInDTO);

	/**
	 * exportarHistorialPagosMinimos.
	 *
	 * @param exportarPagosMinimosOpenCreditView
	 *            the exportar pagos minimos open credit view
	 * @param cliente
	 *            the cliente
	 * @return Reporte
	 */
	Respuesta<Reporte> exportarHistorialPagosMinimos(
			ReportePagosMinimosOpenCreditInView exportarPagosMinimosOpenCreditView, Cliente cliente);

}
