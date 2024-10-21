package ar.com.santanderrio.obp.servicios.prestamos.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosAgregarCBUEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelarPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmacionStopDebitDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoSueldosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.InfoPrestamosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoSueldosPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CompStopDebitPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.view.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionPrestamoRequestView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteStopDebitPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoSueldoTasaSubsidiadaView;

/**
 * Gestiona la logica de negocio relacionada a Prestamo.
 * 
 * @author
 *
 */
public interface PrestamoBO {

	/**
	 * Obtener prestamos.
	 *
	 * @param obtenerPrestamosInDTO
	 *            the obtener prestamos in DTO
	 * @return the respuesta
	 */
	Respuesta<List<PrestamoDTO>> obtenerPrestamos(ObtenerPrestamosInDTO obtenerPrestamosInDTO);
	

	/**
	 * Obtener prestamos stop debit.
	 *
	 * @param confirmacionStopDebitDTO the confirmacion stop debit DTO
	 * @param sesionCliente the sesion cliente
	 * @return the respuesta
	 */
	Respuesta<CompStopDebitPrestamoOutEntity> confirmarPrestamosStopDebit(ConfirmacionStopDebitDTO confirmacionStopDebitDTO, SesionCliente sesionCliente);
	
	
	/**
	 * Obtener prestamo sueldo.
	 *
	 * @param obtenerPrestamosInDTO the obtener prestamos in DTO
	 * @return the respuesta
	 */
	Respuesta<PrestamoSueldosPermitidoDTO> obtenerPrestamoSueldo(ObtenerPrestamosInDTO obtenerPrestamosInDTO);
	
	/**
	 * Obtener prestamo sueldo.
	 *
	 * @param confirmarPrestamoSueldosInDTO the confirmar prestamo sueldos in DTO
	 * @return the respuesta
	 */
	Respuesta<PrestamoSueldosPermitidoDTO> confirmarPrestamoSueldo(ConfirmarPrestamoSueldosInDTO confirmarPrestamoSueldosInDTO);
	
	
	/**
	 * Verificar archivo prestamo sueldo.
	 *
	 * @param adjuntarArchivosInDto the adjuntar archivos in dto
	 * @return the respuesta
	 */
	Respuesta<Boolean> verificarArchivoPrestamoSueldo(AdjuntarArchivosDTO adjuntarArchivosInDto);

	
	/**
	 * Borrar doc.
	 *
	 * @param adjuntarArchivosInDto the adjuntar archivos in dto
	 * @return the respuesta
	 */
	Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto);

	/**
	 * Obtener Situacion crediticia.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<List<CalificacionCrediticiaDTO>> obtenerSituacionCrediticia(Cliente cliente);

	/**
	 * obtiene el mensaje de ayuda para el campo linea total crediticia.
	 *
	 * @return the string
	 */
	String obtenerMensajeAyudaLineaTotalCrediticia();

	/**
	 * obtiene el mensaje de ayuda para el campo linea total crediticia.
	 *
	 * @return the string
	 */
	String obtenerMensajeAyudaLineaActualDisponible();

	/**
	 * Metodo que valida la linea crediticia del cliente.
	 *
	 * @param calificacionCrediticiaDTO
	 *            the calificacion crediticia DTO
	 * @param cantidadPrestamos
	 *            the cantidad prestamos
	 * @param cliente
	 *            the cliente
	 * @return Respuesta<CalificacionCrediticiaDTO>
	 */
	Respuesta<CalificacionCrediticiaDTO> validarLineaCrediticia(CalificacionCrediticiaDTO calificacionCrediticiaDTO,
			int cantidadPrestamos, Cliente cliente);

	/**
	 * Obtener detalle prestamo.
	 *
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @param cliente
	 *            the cliente
	 * @return the prestamo
	 * @throws BusinessException
	 *             the business exception
	 */
	PrestamoDTO obtenerDetallePrestamo(String numeroPrestamo, Cliente cliente) throws BusinessException;

	/**
	 * Obtener info prestamos permitidos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the prestamo permitido out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	PrestamoPermitidoOutEntity obtenerInfoPrestamosPermitidos(Cliente cliente, Cuenta cuenta) throws BusinessException;

	/**
	 * Obtener prestamo cancelado.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<PrestamoCanceladoDTO> obtenerPrestamoCancelado(Cliente cliente) throws BusinessException;

	/**
	 * Obtener detalle prestamo por cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @return the prestamo
	 * @throws BusinessException
	 *             the business exception
	 */
	Prestamo obtenerDetallePrestamoPorCuenta(Cliente cliente, String numeroPrestamo) throws BusinessException;

	/**
	 * Obtiene cantidad de prestamos por tipo.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the info prestamos DTO
	 */
	InfoPrestamosDTO obtenerCantidadPrestamosPorClase(Cliente cliente);

	/**
	 * Obtener reporte pdf.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReportePdf(ReportComprobanteView comprobante);

	/**
	 * obtiene el mensaje de ayuda por el codigo del mensaje.
	 *
	 * @param codigoMensaje
	 *            the codigoMensaje
	 * @return the string
	 */
	String obtenerMensajeAyuda(String codigoMensaje);
	
	/**
	 * obtiene el reporte pdf por el comprobante y el tipo de prestamo.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param isUva
	 * 			  the is uva           
	 * @return the Respuesta<Reporte>
	 */
	Respuesta<Reporte> obtenerReportePdf(ReportComprobanteView comprobante, boolean isUva);
	
	

	/**
	 * Descargar comp stop debit prestamo.
	 *
	 * @param comprobanteStopDebitPrestamoView the comprobante stop debit prestamo view
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView);
	
	/**
	 * Consulta si hay prestamo a tasa subsidiada
	 * @param cliente
	 * @return
	 */
	PrestamoSueldoTasaSubsidiadaEntity consultarPrestamoATPVigente(Cliente cliente);
	
	
	/**
	 * Realiza el alta de un prestamo sueldo a tasa subsidiada
	 * @param prestamoSueldoView
	 * @return
	 */
	Respuesta<String> altaPrestamoSueldoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView, Cliente cliente, Cuenta cuenta);

	/**
	 * Obtiene comprobante de prestamo a tasa subsidiada
	 * @param comprobante
	 * @return
	 */
	Respuesta<Reporte> descargarCompPrestamoTasaSub(ComprobantePrestamoTasaSubsidiadaView comprobante);
	
	Respuesta<ConsultaCbuEntityOut> validarCbuPrestamos(ValidarCbuInDTO validarCbuInDTO) throws Exception;
	
	String agregarCBU(PrestamoSueldosAgregarCBUEntity agregarCBUEntity);
	
	Respuesta<CancelarPrestamoDTO> consultarCancelarPrestamos(CancelacionPrestamoRequestView cancelarView) throws BusinessException;

	Respuesta<CancelarPrestamoDTO> consultarCancelarPrestamos(CancelacionPrestamoRequestView cancelarView, boolean simular) throws BusinessException;

	Respuesta<Reporte> generarComprobantePDF(ComprobanteCancelacionTotalPrestamoView datosComprobante);
	
	Boolean getPrestamoCom12123(Prestamo prestamo);

	PrestamosEncoladosEntity obtenerPrestamosEncolados();

	PrestamoEncoladoEntity obtenerPrestamoEncolado(String id) throws DAOException;

	void cancelarPrestamoEncolado(String loandId) throws DAOException;

	EncolamientoResponseDTO encolarPrestamo(EncolamientoRequestDTO encolamientRequestDTO) throws DAOException;

	LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup) throws DAOException;

	LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup, String origenFront)
			throws DAOException;

	PagoCuotaResponseDTO pagarCuota(PagoCuotaRequestDTO pagoCuotaRequestDTO) throws DAOException;

	CancelacionAnticipadaResponseDTO cancelarAnticipadamente(CancelacionAnticipadaRequestDTO request) throws DAOException;

}