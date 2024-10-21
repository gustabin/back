/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaPrestamosEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoOpenCreditBO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.PrestamoCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoOpenCreditDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CabeceraReportePagosOpenCreditEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ItemReportePagoOpenCreditEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ReportePagosOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagosFechaOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;

/**
 * OLYMPUS PrestamoOpenCreditoBOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class PrestamoOpenCreditBOImpl implements PrestamoOpenCreditBO {

	/** The destino prestamo DAO. */
	@Autowired
	private DeudaPrestamoDAO deudaPrestamoDAO;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

	/** The destino prestamo open credit DAO. */
	@Autowired
	private PrestamoOpenCreditDAO prestamoOpenCreditDAO;

	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoOpenCreditBOImpl.class);

	/** Identificador prestamo open credit. */
	private static final String CLASE_PPRESTAMO_OPEN_CREDIT = "O";

	/** Tipo prestamo open credit. */
	private static final String TIPO_PRESTAMO_OPEN_CREDIT = "00";

	/** Descripcion de prestamo open credit. */
	private static final String DESCRIPCION_OPEN_CREDIT = "Open Credit";

	/** Detalle de cuotas coodigo evento. */
	private static final String DETALLE_CUOTAS_COD_EVENTO = "COBR";

	/** Detalle de cuotas num registros. */
	private static final String DETALLE_CUOTAS_NUM_REGISTROS = "000";

	/** The Constant REPORTE_PAGOS_MINIMOS. */
	private static final String REPORTE_PAGOS_MINIMOS = "HistorialPagosMinimos";

	/**
	 * obtenerPrestamosOpenCredit.
	 *
	 * @param prestamoOpenCreditInDTO
	 *            the prestamo open credit in DTO
	 * @return Respuesta<List<PrestamoOpenCreditDTO>>
	 */
	@Override
	public Respuesta<List<PrestamoOpenCreditDTO>> obtenerPrestamosOpenCredit(
			PrestamoOpenCreditInDTO prestamoOpenCreditInDTO) {
		LOGGER.info("PrestamoOpenCreditBOImpl - Iniciando metodo obtenerPrestamosOpenCredit");
		Cliente cliente = prestamoOpenCreditInDTO.getCliente();
		List<ItemMensajeRespuesta> listaItems = new ArrayList<ItemMensajeRespuesta>();
		List<PrestamoOpenCreditDTO> prestamosOpenCreditDTO = new ArrayList<PrestamoOpenCreditDTO>();
		Integer cantErrores = 0;
		Integer cantPrestamos = 0;

		for (Cuenta cuenta : cliente.getCuentas()) {
			try {
				if (isOpenCredit(cuenta)) {
					cantPrestamos++;
					PrestamoOpenCreditDTO prestamoDTO = obtenerPrestamoOpenCredit(cliente, cuenta);
					prestamosOpenCreditDTO.add(prestamoDTO);
				}
			} catch (DAOException e) {
				LOGGER.error("Error en el DAO de deudaPrestamoDAO Open Credit.", e);
				cantErrores++;
			} catch (RuntimeException e) {
				LOGGER.error("Error generico en deudaPrestamoDAO Open Credit.", e);
				cantErrores++;
			}
		}
		if (cantPrestamos != 0 && cantErrores == cantPrestamos) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS_OC,
					CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
		}
		if (cantErrores > 0) {
            listaItems.add(crearItem(CodigoMensajeConstantes.ERROR_PARCIAL_BUSCAR_PRESTAMOS,
                    TipoError.ERROR_PARCIAL_BUSCAR_PRESTAMOS_OC));
        }

		if (CollectionUtils.isNotEmpty(listaItems)) {
		    LOGGER.debug("PrestamoOpenCreditBOImpl - Retornando prestamos open credit WARNING");
            return respuestaFactory.crearRespuestaWarning(prestamosOpenCreditDTO, listaItems);
        } else {
            LOGGER.debug("PrestamoOpenCreditBOImpl - Retornando prestamos open credit OK");
            return respuestaFactory.crearRespuestaOk(prestamosOpenCreditDTO);
        }
	}
	
	/**
     * Crear item.
     *
     * @param errorParcialBuscarPrestamos
     *            the error parcial buscar prestamos
     * @param tipoError
     *            the tipo error
     * @return the item mensaje respuesta
     */
    private ItemMensajeRespuesta crearItem(String errorParcialBuscarPrestamos, TipoError tipoError) {

        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(errorParcialBuscarPrestamos);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
        item.setTipoError(tipoError.getDescripcion());
        return item;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoOpenCreditBO#
	 * obtenerDetallePagosMinimos(ar.com.santanderrio.obp.servicios.prestamos.
	 * dto.ConsultaPagosMinimosOpenCreditInDTO)
	 */
	@Override
	public Respuesta<DetallePagosMinimosOpenCreditDTO> obtenerDetallePagosMinimos(
			ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosInDTO) {
		LOGGER.info("PrestamoOpenCredit BO iniciando obtenerDetallePagosMinimos");
		DetallePagosMinimosOpenCreditDTO detallePagosMinimosDTO;
		ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = generarConsultaCuotaPagosInEntity(
				consultaPagosMinimosInDTO);
		try {
			LOGGER.debug("Iniciando llamada prestamoCuotaPagaDAO");
			ConsultaCuotaPagaOutEntity detalleOutEntity = prestamoOpenCreditDAO
					.obtenerCuotasPagasPrestamo(consultaPagosMinimosInDTO.getCliente(), consultaCuotaPagaEntity);
			while (detalleOutEntity.getHayMasRegistros()) {
				consultaCuotaPagaEntity.setRellamada(detalleOutEntity);
				LOGGER.debug("Iniciando RE llamada prestamoCuotaPagaDAO");
				ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntityRellamada = prestamoOpenCreditDAO
						.obtenerCuotasPagasPrestamo(consultaPagosMinimosInDTO.getCliente(), consultaCuotaPagaEntity);
				detalleOutEntity.agregarResultados(consultaCuotaPagaOutEntityRellamada);
			}
			detallePagosMinimosDTO = generarDetallePagosMinimosDTO(detalleOutEntity, consultaPagosMinimosInDTO);

		} catch (DAOException e) {
			LOGGER.error("Error en el DAO de cuotaPaga Open Credit.", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
					CodigoMensajeConstantes.PRESTAMOS_OPEN_CREDIT_ERROR_GENERICO);
		}
		LOGGER.debug("Finalizando metodo obtenerDetallePagosMinimos");
		return respuestaFactory.crearRespuestaOk(detallePagosMinimosDTO);
	}

	/**
	 * exportarHistorialPagosMinimos.
	 *
	 * @param reportePagosMinimosOpenCreditInView
	 *            the reporte pagos minimos open credit in view
	 * @param cliente
	 *            the cliente
	 * @return Respuesta Reporte
	 */
	@Override
	public Respuesta<Reporte> exportarHistorialPagosMinimos(
			ReportePagosMinimosOpenCreditInView reportePagosMinimosOpenCreditInView, Cliente cliente) {
		LOGGER.info("PrestamoOpenCredit BO iniciando exportarHistorialPagosMinimos");
		ReportePagosOpenCreditInEntity reportePagosMinimosEntity = new ReportePagosOpenCreditInEntity();
		reportePagosMinimosEntity.setCabecera(crearCabeceraReportePagos(reportePagosMinimosOpenCreditInView));

		DetallePagosMinimosOpenCreditView detallePagos = reportePagosMinimosOpenCreditInView.getDetallePagosMinimos();
		List<ItemReportePagoOpenCreditEntity> pagosReporte = new ArrayList<ItemReportePagoOpenCreditEntity>();
		for (PagosFechaOpenCreditView pagosFechaOpenCredit : detallePagos.getPagosFecha()) {
			for (PagoOpenCreditView pagoOpenCredit : pagosFechaOpenCredit.getPagos()) {
				ItemReportePagoOpenCreditEntity itemPago = crearItemReportePagos(pagoOpenCredit,
						reportePagosMinimosOpenCreditInView.getLineaCreditoTotal());
				pagosReporte.add(itemPago);
			}

		}
		reportePagosMinimosEntity.setPagos(pagosReporte);
		Respuesta<Reporte> reporte = reporteDAO.obtenerReporte(reportePagosMinimosEntity, REPORTE_PAGOS_MINIMOS,
				cliente,true);
		LOGGER.debug("PrestamoOpenCredit BO finalizando exportarHistorialPagosMinimos");
		return reporte;
	}

	/**
	 * crearItemReportePagos.
	 *
	 * @param pagoOpenCredit
	 *            the pago open credit
	 * @param lineaCreditoTotal
	 *            the linea credito total
	 * @return the item reporte pago open credit entity
	 */
	private ItemReportePagoOpenCreditEntity crearItemReportePagos(PagoOpenCreditView pagoOpenCredit,
			String lineaCreditoTotal) {
		ItemReportePagoOpenCreditEntity item = new ItemReportePagoOpenCreditEntity();
		item.setNumeroPagoMinimo(pagoOpenCredit.getNumeroPagoMinimo());
		item.setFechaVencimiento(pagoOpenCredit.getFechaVencimiento());
		item.setPagoMinimo(pagoOpenCredit.getPagoMinimo());
		item.setLineaCreditoTotal(lineaCreditoTotal);
		return item;
	}

	/**
	 * crearCabeceraReportePagos.
	 *
	 * @param reportePagosMinimosOpenCreditInView
	 *            the reporte pagos minimos open credit in view
	 * @return CabeceraReportePagosOpenCreditEntity
	 */
	private CabeceraReportePagosOpenCreditEntity crearCabeceraReportePagos(
			ReportePagosMinimosOpenCreditInView reportePagosMinimosOpenCreditInView) {
		CabeceraReportePagosOpenCreditEntity cabeceraEntity = new CabeceraReportePagosOpenCreditEntity();
		cabeceraEntity.setDisponibleParaUso(reportePagosMinimosOpenCreditInView.getDisponibleParaUso());
		cabeceraEntity.setLineaCreditoTotal(reportePagosMinimosOpenCreditInView.getLineaCreditoTotal());
		cabeceraEntity.setNumeroPrestamo(reportePagosMinimosOpenCreditInView.getNumeroPrestamo());
		return cabeceraEntity;
	}

	/**
	 * generarDetallePagosMinimosDTO.
	 *
	 * @param detalleOutEntity
	 *            the detalle out entity
	 * @param consultaPagosMinimosOpenCreditInDTO
	 *            the consulta pagos minimos open credit in DTO
	 * @return the detalle pagos minimos open credit DTO
	 */
	private DetallePagosMinimosOpenCreditDTO generarDetallePagosMinimosDTO(ConsultaCuotaPagaOutEntity detalleOutEntity,
			ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosOpenCreditInDTO) {
		LOGGER.debug("PrestamoOpenCreditBOImpl generarDetallePagosMinimosDTO");
		DetallePagosMinimosOpenCreditDTO detallePagosMinimosDTO = new DetallePagosMinimosOpenCreditDTO();
		detallePagosMinimosDTO.setDespcripcion(DESCRIPCION_OPEN_CREDIT);
		detallePagosMinimosDTO
				.setNumeroPrestamo(consultaPagosMinimosOpenCreditInDTO.getCuenta().getNroCuentaProducto());
		List<PagoOpenCreditDTO> pagosDTO = new ArrayList<PagoOpenCreditDTO>();
		for (PrestamoCuotaPagaOutEntity pagoEntity : detalleOutEntity.getCuotasPagas()) {
			PagoOpenCreditDTO pagoDTO = new PagoOpenCreditDTO();
			pagoDTO.setPagoMinimo(pagoEntity.getImporteCargo());
			pagoDTO.setFechaVencimiento(pagoEntity.getFeliq());
			pagoDTO.setIntCompensatoriosPeriodo(pagoEntity.getIntinire());
			pagoDTO.setTasaTNA(pagoEntity.getTna());
			pagoDTO.setImpuestos(pagoEntity.getImpinire());
			pagoDTO.setNumeroPagoMinimo(ISBANStringUtils.eliminarCeros(pagoEntity.getNumrec()));
			pagoDTO.setDivisa(DivisaEnum.fromCodigoString(pagoEntity.getDivisaPago().trim()));
			pagoDTO.setCapital(pagoEntity.getCapinire());
			pagoDTO.setTasaTEA(pagoEntity.getTae());
			pagoDTO.setCftConImp(pagoEntity.getCftna());
			pagoDTO.setCftSinImp(pagoEntity.getCftnasi());
			pagosDTO.add(pagoDTO);
		}
		detallePagosMinimosDTO.setPagos(pagosDTO);
		return detallePagosMinimosDTO;
	}

	/**
	 * generarConsultaCuotaPagosInEntity.
	 *
	 * @param consultaPagosMinimosInDTO
	 *            the consulta pagos minimos in DTO
	 * @return the consulta cuota paga in entity
	 */
	private ConsultaCuotaPagaInEntity generarConsultaCuotaPagosInEntity(
			ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosInDTO) {
		LOGGER.debug("PrestamoOpenCreditBOImpl generarConsultaCuotaPagosInEntity");
		ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = new ConsultaCuotaPagaInEntity();
		consultaCuotaPagaEntity.setNumRegistros(DETALLE_CUOTAS_NUM_REGISTROS);
		consultaCuotaPagaEntity.setOficina(
				ISBANStringUtils.formateadorConCerosIzq(consultaPagosMinimosInDTO.getCuenta().getNroSucursal(), 4));
		consultaCuotaPagaEntity.setCuenta(ISBANStringUtils
				.formateadorConCerosIzq(consultaPagosMinimosInDTO.getCuenta().getNroCuentaProducto(), 12));
		consultaCuotaPagaEntity.setCodEvento(DETALLE_CUOTAS_COD_EVENTO);
		consultaCuotaPagaEntity.setFecInicio(consultaPagosMinimosInDTO.getFechaInicio());
		consultaCuotaPagaEntity.setFecFin(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		consultaCuotaPagaEntity.setTipomov(" ");
		consultaCuotaPagaEntity.setTimestamp(ISBANStringUtils.fillStr("", 26));
		consultaCuotaPagaEntity.setCodconli(ISBANStringUtils.fillStr("", 3));
		consultaCuotaPagaEntity.setNumSecuencia(ISBANStringUtils.fillStr("", 3));
		return consultaCuotaPagaEntity;
	}

	/**
	 * Obtener prestamo open credit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the prestamo open credit DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private PrestamoOpenCreditDTO obtenerPrestamoOpenCredit(Cliente cliente, Cuenta cuenta) throws DAOException {
		LOGGER.debug("PrestamoOpenCreditBOImpl  obtenerPrestamoOpenCredit iniciando llamadas DAO");
		ConsultaPrestamoOpenCreditInEntity consultaOpenCreditInEntity = new ConsultaPrestamoOpenCreditInEntity();
		consultaOpenCreditInEntity.setCliente(cliente);
		consultaOpenCreditInEntity.setNumeroPrestamo(cuenta.getNroCuentaProducto());
		consultaOpenCreditInEntity.setTipoPrestamo(TIPO_PRESTAMO_OPEN_CREDIT);
		consultaOpenCreditInEntity.setSucursal(cuenta.getNroSucursal());
		consultaOpenCreditInEntity.setFechaDelDia(new SimpleDateFormat("yyyyMMdd").format(new Date()));

		// Obtener informacion general del prestamo
		LOGGER.debug("PrestamoOpenCreditBOImpl llamando servicio DAO deudaPrestamo");
		Prestamo deudaPrestamo = deudaPrestamoDAO.consultarDeudaDePrestamoOpenCredit(consultaOpenCreditInEntity);
		deudaPrestamo.setCuenta(cuenta);
		// Obtener informacion especifica de prestamo open credit
		LOGGER.debug("PrestamoOpenCreditBOImpl llamando servicio DAO openCredit");
		ConsultaPrestamoOpenCreditOutEntity prestamoOpenCreditOutEntity = prestamoOpenCreditDAO
				.consultarPrestamo(consultaOpenCreditInEntity);
		// Generacion del prestamo dto con ambos resultados
		PrestamoOpenCreditDTO prestamoOpenCreditDTO = generarPrestamoOpenCreditDTO(deudaPrestamo,
				prestamoOpenCreditOutEntity);
		LOGGER.info("PrestamoOpenCreditBOImpl  obtenerPrestamoOpenCredit finalizando  llamada DAO");
		return prestamoOpenCreditDTO;
	}

	/**
	 * generarPrestamoOpenCreditDTO.
	 *
	 * @param deudaPrestamo
	 *            the deuda prestamo
	 * @param prestamoOpenCreditOutEntity
	 *            the prestamo open credit out entity
	 * @return the prestamo open credit DTO
	 */
	private PrestamoOpenCreditDTO generarPrestamoOpenCreditDTO(Prestamo deudaPrestamo,
			ConsultaPrestamoOpenCreditOutEntity prestamoOpenCreditOutEntity) {
		LOGGER.debug("PrestamoOpenCreditBOImpl generarPrestamoOpenCreditDTO");
		PrestamoOpenCreditDTO prestamoOpenCreditDTO = new PrestamoOpenCreditDTO();
		prestamoOpenCreditDTO.setDescripcion(DESCRIPCION_OPEN_CREDIT);
		prestamoOpenCreditDTO.setCuenta(deudaPrestamo.getCuenta());
		prestamoOpenCreditDTO.setLineaCreditoTotal(prestamoOpenCreditOutEntity.getCapitalSolicitado());
		prestamoOpenCreditDTO.setDisponibleParaUso(prestamoOpenCreditOutEntity.getCapitalDispuesto());
		prestamoOpenCreditDTO.setVencimientoCuota(deudaPrestamo.getVencimientoCuota());
		prestamoOpenCreditDTO.setPagoMinimo(deudaPrestamo.getImporteTotalCuota());
		prestamoOpenCreditDTO.setTasaTNA(deudaPrestamo.getTasaPrestamo());
		prestamoOpenCreditDTO.setIntCompensatoriosPeriodo(deudaPrestamo.getImporteIntereses());
		prestamoOpenCreditDTO.setIntCompensatoriosPostVencimiento(deudaPrestamo.getInteresCompensatorioPendiente());
		prestamoOpenCreditDTO.setInteresesPunitorios(deudaPrestamo.getImportesPunitorios());
		prestamoOpenCreditDTO.setIva(deudaPrestamo.getImporteIVA());
		prestamoOpenCreditDTO.setIngresosBrutos(deudaPrestamo.getIngresosBrutos());
		prestamoOpenCreditDTO.setOtrosImpuestos(deudaPrestamo.getOtrosImpuestos());
		prestamoOpenCreditDTO.setDivisa(deudaPrestamo.getDivisa());
		prestamoOpenCreditDTO
				.setNumeroRecibo(Integer.parseInt(ISBANStringUtils.eliminarCeros(deudaPrestamo.getNumeroRecibo())));
		prestamoOpenCreditDTO.setFechaInicio(prestamoOpenCreditOutEntity.getFechasInicio().substring(10, 20));
		prestamoOpenCreditDTO.setCapital(deudaPrestamo.getImporteAmortizacion());
		prestamoOpenCreditDTO.setTasaTEA(deudaPrestamo.getTasaAnualEfectiva());
		prestamoOpenCreditDTO.setCftConImp(deudaPrestamo.getCostoFinancieroTotal());
		prestamoOpenCreditDTO.setCftSinImp(deudaPrestamo.getCostoFinancieroTotalSinImpuestos());
		return prestamoOpenCreditDTO;
	}

	/**
	 * Validacion de tipo y clase de cuenta open credit.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is open credit
	 */
	private boolean isOpenCredit(Cuenta cuenta) {
		return (CLASE_PPRESTAMO_OPEN_CREDIT.equals(cuenta.getClaseCuenta()) && TipoCuentaPrestamosEnum
				.fromCodigo(cuenta.getTipoCuenta()) == TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30);

	}

}
