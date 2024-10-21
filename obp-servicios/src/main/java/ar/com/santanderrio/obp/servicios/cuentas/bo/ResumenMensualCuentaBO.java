/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ComprobanteAdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;

/**
 * The Interface ResumenMensualCuentaBO.
 */
public interface ResumenMensualCuentaBO {

	/**
	 * Obtener lista resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws BusinessException;

	/**
	 * Obtener resumen descarga multiple.
	 *
	 * @param fechaResumenes
	 *            the fecha resumenes
	 * @param cuenta
	 *            the cuenta
	 * @param cantidadADescargar
	 *            the cantidad A descargar
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ReporteResumenMensualCuenta> obtenerResumenDescargaMultiple(List<ResumenMensualCuenta> fechaResumenes,
			AbstractCuenta cuenta, int cantidadADescargar) throws BusinessException;

	/**
	 * Obtener resumen puntual PDF.
	 *
	 * @param resumenPuntualCuenta
	 *            the resumen puntual cuenta
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @param id
	 *            the id
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ReporteResumenPuntual> obtenerResumenPuntualPDF(List<ReporteSeleccionado> resumenPuntualCuenta,
			IdentificacionCuenta idCuenta, Cliente cliente, Integer id) throws BusinessException;

	/**
	 * Obtener resumenes PDF.
	 *
	 * @param resumenesSeleccionados
	 *            the resumenes seleccionados
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(List<ResumenMensualCuenta> resumenesSeleccionados,
			AbstractCuenta cuenta);

	/**
	 * Obtener mensaje informativo descarga resumenes.
	 *
	 * @return the string
	 */
	String obtenerMensajeInformativoDescargaResumenes();

	/**
	 * Obtener mensaje error por cantidad fallidos.
	 *
	 * @param size
	 *            the size
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerMensajeErrorPorCantidadFallidos(int size);

	/**
	 * Obtener marca resumen online.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<Boolean> obtenerMarcaResumenOnline(AbstractCuenta cuenta);

	/**
	 * Cambiar marca impresion.
	 *
	 * @param adhesionResumenDTO
	 *            the adhesion resumen DTO
	 * @return the respuesta
	 */
	Respuesta<Void> cambiarMarcaImpresion(AdhesionResumenDTO adhesionResumenDTO);

	/**
	 * Cambiar marca impresion.
	 *
	 * @param pid
	 *            the pid
	 * @return the respuesta
	 */
	ComprobanteAdhesionResumenDTO crearComprobanteAdhesionResumen(String pid);

	/**
	 * Consultar fechas por cuenta.
	 *
	 * @param string
	 *            the string
	 * @return the respuesta
	 */
	Respuesta<List<ResumenMensualCuenta>> consultarFechasPorCuenta(String string);

}
