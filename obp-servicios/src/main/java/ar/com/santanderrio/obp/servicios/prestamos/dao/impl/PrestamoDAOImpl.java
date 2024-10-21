/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosCanceladosOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class PrestamoDAOImpl.
 *
 * @author florencia.n.martinez
 */
@Component("prestamoDAO")
public class PrestamoDAOImpl extends IatxBaseDAO implements PrestamoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoDAOImpl.class);

	/** The Constant ERROR_SERVICIO. */
	private static final String ERROR_SERVICIO = "001";

	/** The Constant ALTPMOPREA. */
	private static final String ALTPMOPREA = "ALTPMOPREA";

	/** The Constant VERSION. */
	private static final String VERSION = "120";

	/** The Constant CODIGO_RETORNO_OK. */
	public static final String CODIGO_RETORNO_OK = "00000000";
	
	/** The Constant CODIGO_RETORNO_OK. */
	public static final String CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE = "10000022";

	/** The Constant CODIGO_ERROR_DIA_NO_HABIL. */
	public static final String CODIGO_ERROR_DIA_NO_HABIL = "10001306";

	/** The Constant CODIGO_ERROR_DIA_FUERA_RANGO. */
	public static final String CODIGO_ERROR_DIA_FUERA_RANGO = "10000001";

	/** The Constant CODIGO_ERROR_CANTIDAD_CUOTAS. */
	public static final String CODIGO_ERROR_CANTIDAD_CUOTAS = "16010023";

	/** The Constant CODIGO_ERROR_OPERACION_INHABILITADA. */
	public static final String CODIGO_ERROR_OPERACION_INHABILITADA = "10099906";

	/** The Constant CODIGO_ERROR_CUOTA_ADELANTADA. */
	public static final String CODIGO_ERROR_CANCELAR_PRESTAMO_CUOTA_ADELANTADA = "10000141";

	/** The Constant CODIGO_ERROR_CANCELAR_PRESTAMO. */
	public static final String CODIGO_ERROR_CANCELAR_PRESTAMO_ERROR_DEUDA = "10000100";

	/** The Constant CODIGO_ERROR_CANCELAR_PRESTAMO_INCIDENCIA_FUTURA. */
	public static final String CODIGO_ERROR_CANCELAR_PRESTAMO_INCIDENCIA_FUTURA = "10001861";

	/** The Constant CODIGO_ERROR_CANCELAR_PRESTAMO. */
	public static final String CODIGO_ERROR_CANCELAR_PRESTAMO_CUOTA_IMPAGA = "10001275";

	/** The Constant CODIGO_ERROR_CANCELAR_PRESTAMO_SALDO_INSUFICIENTE. */
	public static final String CODIGO_ERROR_CANCELAR_PRESTAMO_SALDO_INSUFICIENTE = "10000515";

	/** The Constant CODIGO_ERROR_CANCELACION_PARCIAL. */
	public static final String CODIGO_ERROR_PRESTAMO_VENCIDO = "10001194";

	/** The Constant COD_RETORNO_EXT_OK. */
	private static final Integer COD_RETORNO_EXT_OK = 0;

	/** The Constant COD_ERROR_NO_ESPERADO. */
	private static final String COD_ERROR_NO_ESPERADO = "Codigo de error no esperado de iatx en servicio ALTPMOPREA.";

	/** The Constant ERROR_IATX. */
	private static final String ERROR_IATX = "Error de Iatx.";

	/** The servicio cnscontcan. */
	public final String servicioCnscontcan = "CNSCONTCAN";
	
	@Value("${SERVICIO.PREFIJO.PAGPMOCAAN}")
	private String pagpmocaanNombre;
	
	@Value("${SERVICIO.VERSION.PAGPMOCAAN}")
	private String pagpmocaanVersion;
	
	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource logoCabecera;
	
	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource logoPie;
	
	@Value("classpath:/report/cancelacionTotalPrestamo/comprobanteCancelacionTotalPrestamo.jasper")
	private Resource archivoJasperCancelacionTotalPrestamo;
	
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	@Autowired
	private IatxComm iatxComm;
	
	private List<String> prestamosCOM12123;

	/**
	 * Obtiene la simulacion o la solicitud del prestamo mediante la
	 * configuracion de su parametro inEntity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the simulador prestamo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public PrestamoOutEntity simularAdquirir(Cliente cliente, PrestamoInEntity inEntity) throws DAOException {

		if (!ValidationEntity.validate(inEntity)) {
			PrestamoOutEntity outEntity = new PrestamoOutEntity();
			outEntity.setCodigoRetornoExtendido(ERROR_SERVICIO);
			return outEntity;
		}

		IatxRequest iatxRequest = new IatxRequest(ALTPMOPREA, VERSION);
		try {
			IatxRequestData iaxtRequestData = generarRequestDataALTPMOPREA(cliente, inEntity);
			iatxRequest.setData(iaxtRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

			if (COD_RETORNO_EXT_OK.equals(iatxResponse.getErrorCode())) {
				PrestamoOutEntity outEntity = this.processTrama(iatxResponse.getTrama(), PrestamoOutEntity.class);
				outEntity.setNroComprobante(iatxResponse.getNroComprobante());
				outEntity.setFecha(iatxResponse.getFechaHoraReq());
				outEntity.setFase(inEntity.getFase());
				return outEntity;
			}

			LOGGER.debug(COD_ERROR_NO_ESPERADO);
			PrestamoOutEntity outEntity = new PrestamoOutEntity();
			outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			outEntity.setFase(inEntity.getFase());
			return outEntity;

		} catch (IatxException e) {
			LOGGER.error(ERROR_IATX, e);
			throw new DAOException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO#
	 * obtenerCancelados(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public PrestamosCanceladosOutEntity obtenerCancelados(Cliente cliente) throws DAOException {
		IatxResponse iatxResponse = null;
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			// NRO_NUP
			requestData.addBodyValue(cliente.getNup());
			// APLICATIVO (PTM - prestamos / MON - cuentas)
			requestData.addBodyValue("PTM");
			// CANT_DIAS
			requestData.addBodyValue("0530");
			// MOT_CAN (To - Todos / CS - canal 04)
			requestData.addBodyValue("CS");

			IatxRequest request = new IatxRequest(servicioCnscontcan, "120");
			request.setData(requestData);

			iatxResponse = iatxComm.exec(request);
			return this.processTrama(iatxResponse.getTrama(), PrestamosCanceladosOutEntity.class);

		} catch (IatxException e) {
			LOGGER.error("Error al ejecutar servicio CNSCONTCAN.", e);
			throw new DAOException();
		}
	}

	/**
	 * Genera el request data para invocar al servicio ALTPMOPREA.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataALTPMOPREA(Cliente cliente, PrestamoInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(inEntity.getFase());
		iatxRequestData.addBodyValue(inEntity.getTipoCuenta());
		iatxRequestData.addBodyValue(inEntity.getSucursalCuenta());
		iatxRequestData.addBodyValue(inEntity.getNumeroCuenta());
		iatxRequestData.addBodyValue(inEntity.getImportePrestamo());
		iatxRequestData.addBodyValue(inEntity.getCantidadCuotas());
		iatxRequestData.addBodyValue(inEntity.getFechaPrimerCuota());
		iatxRequestData.addBodyValue(inEntity.getTipoTasa());
		iatxRequestData.addBodyValue(inEntity.getCodProductoUG());
		iatxRequestData.addBodyValue(inEntity.getCodSubpUG());
		iatxRequestData.addBodyValue(inEntity.getDestFondosUG());
		iatxRequestData.addBodyValue(inEntity.getCodDivisaO());
		iatxRequestData.addBodyValue(inEntity.getValorTasa());
		iatxRequestData.addBodyValue(inEntity.getClausulaRevUG());
		iatxRequestData.addBodyValue(inEntity.getSucPaquete());
		iatxRequestData.addBodyValue(inEntity.getNumPaquete());
		iatxRequestData.addBodyValue(inEntity.getTpoPolizaDs());
		iatxRequestData.addBodyValue(inEntity.getTpoRiesgoDs());
		iatxRequestData.addBodyValue(inEntity.getCodCondici());
		iatxRequestData.addBodyValue(inEntity.getNIO());
		iatxRequestData.addBodyValue(inEntity.getDestFondosComerO());
		iatxRequestData.addBodyValue(inEntity.getSucPrest());
		iatxRequestData.addBodyValue(inEntity.getNumPrest());
		iatxRequestData.addBodyValue(inEntity.getEntCuentaProve());
		iatxRequestData.addBodyValue(inEntity.getSucCuentaProve());
		iatxRequestData.addBodyValue(inEntity.getNroCuentaProve());
		iatxRequestData.addBodyValue(inEntity.getDivisaCtaProve());
		iatxRequestData.addBodyValue(inEntity.getImporteAbono());
		iatxRequestData.addBodyValue(inEntity.getIndLineaUVA());
		return iatxRequestData;
	}
	
	//Cancelacion Prestamos
	@Override
	public CancelarPrestamoOutEntity consultarServicioCancelarPrestamos(Cliente cliente, CancelarPrestamoInEntity inEntity) throws DAOException {

		IatxRequest iatxRequest = new IatxRequest(pagpmocaanNombre, pagpmocaanVersion);
		try {
			IatxRequestData iaxtRequestData = generarRequestDataPAGPMOCAAN(cliente, inEntity);
			iatxRequest.setData(iaxtRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);

			if (!COD_RETORNO_EXT_OK.equals(iatxResponse.getErrorCode())) {
				LOGGER.debug(COD_ERROR_NO_ESPERADO);
				CancelarPrestamoOutEntity outEntity = new CancelarPrestamoOutEntity();
				outEntity.setMensajeErrorServicio(iatxResponse.getErrorMessage());
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
				outEntity.setTieneError(Boolean.TRUE);
				return outEntity;
			}

			CancelarPrestamoOutEntity outEntity = this.processTrama(iatxResponse.getTrama(), CancelarPrestamoOutEntity.class);
			outEntity.setNroComprobante(iatxResponse.getNroComprobante());
			outEntity.setFecha(iatxResponse.getFechaHoraReq());
			return outEntity;
		} catch (IatxException e) {
			LOGGER.error(ERROR_IATX, e);
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public Reporte generarComprobantePDF(ComprobanteCancelacionTotalPrestamoView datosComprobante) {

		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperCancelacionTotalPrestamo.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = datosComprobante.mapPDFParameters();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobanteCancelacionTotalPrestamo.pdf");
			return reporte;
		} catch (JRException ex) {
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			throw new ISBANRuntimeException(e);
		}
	}

	private IatxRequestData generarRequestDataPAGPMOCAAN(Cliente cliente, CancelarPrestamoInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(inEntity.getSucursalPmo());
		iatxRequestData.addBodyValue(inEntity.getCuentaPmo());
		iatxRequestData.addBodyValue(inEntity.getDivisa());
		iatxRequestData.addBodyValue(inEntity.getMotivoCancelacion());
		iatxRequestData.addBodyValue(inEntity.getIndSimulacion());
		iatxRequestData.addBodyValue(inEntity.getFecValor());
		iatxRequestData.addBodyValue(inEntity.getFormaPagCargo());
		iatxRequestData.addBodyValue(inEntity.getSucursalAltairCargo());
		iatxRequestData.addBodyValue(inEntity.getCuentaAltairCargo());
		iatxRequestData.addBodyValue(inEntity.getCodigoConcepto());
		iatxRequestData.addBodyValue(inEntity.getTipoApl());
		iatxRequestData.addBodyValue(inEntity.getImpApl());
		return iatxRequestData;
	}

	//Detalle Prestamo
	public void getPrestamoAdditionalData() {

	}

	@Override
	public boolean getIsPrestamosCOM12123(String numeroPrestamo) throws DAOException {
		if (prestamosCOM12123 == null) {
			prestamosCOM12123 = archivoResource.leerArchivo(ExternalPropertyType.FILE_PRESTAMOS_COM_12123);
		}
		return prestamosCOM12123.contains(numeroPrestamo);
	}

}
