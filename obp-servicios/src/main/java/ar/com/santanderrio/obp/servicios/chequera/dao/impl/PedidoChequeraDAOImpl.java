/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.common.PedidoChequeraInReport;
import ar.com.santanderrio.obp.servicios.chequera.dao.PedidoChequeraDAO;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraInEntity;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraOutEntity;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class PedidoChequeraDAOImpl.
 *
 * @author desa
 */
@Component("pedidoChequeraDAO")
public class PedidoChequeraDAOImpl extends IatxBaseDAO implements PedidoChequeraDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PedidoChequeraDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant SERVICIO_PEDIDO_CHEQUERA. */
	private static final String SERVICIO_PEDIDO_CHEQUERA = "PEDCHR____";

	/** The Constant VERSION_SERVICIO_PEDIDO_CHEQUERA. */
	private static final String VERSION_SERVICIO_PEDIDO_CHEQUERA = "120";

	/** The Constant TIPO_CUENTA_UNICA. */
	private static final String TIPO_CUENTA_UNICA = "02";

	/** The Constant TIPO_CUENTA_UNICA. */
	private static final String TIPO_CUENTA_UNICA_PESOS = "09";

	/** The file jasper. */
	@Value("classpath:/report/chequera/PedidoChequeraGenerico.jasper")
	private Resource fileJasper;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant CUENTA_ORIGEN. */
	private static final String CUENTA_ORIGEN = "CUENTA_ORIGEN";

	/** The Constant TIPO_CUENTA_ORIGEN. */
	private static final String TIPO_CUENTA_ORIGEN = "TIPO_CUENTA_ORIGEN";

	/** The Constant MONEDA. */
	private static final String MONEDA = "MONEDA";

	/** The Constant SUCURSAL. */
	private static final String SUCURSAL = "SUCURSAL";

	/** The Constant DIRECCION_SUCURSAL. */
	private static final String DIRECCION_SUCURSAL = "DIRECCION_SUCURSAL";

	/** The Constant LOCALIDAD_SUCURSAL. */
	private static final String LOCALIDAD_SUCURSAL = "LOCALIDAD_SUCURSAL";

	/** The Constant LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant FECHA_OPERACION. */
	private static final String FECHA_OPERACION = "FECHA_OPERACION";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.chequera.dao.PedidoChequeraDAO#
	 * confirmarPedidoChequera(ar.com.santanderrio.obp.servicios.chequera.entity
	 * .PedidoChequeraInEntity)
	 */
	@Override
	public Respuesta<PedidoChequeraOutEntity> confirmarPedidoChequera(PedidoChequeraInEntity requestIn)
			throws DAOException {
		Respuesta<PedidoChequeraOutEntity> respuestaPedidoChequeraEntity = new Respuesta<PedidoChequeraOutEntity>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		try {
			IatxRequest request = new IatxRequest(SERVICIO_PEDIDO_CHEQUERA, VERSION_SERVICIO_PEDIDO_CHEQUERA);
			IatxRequestData requestData = new IatxRequestData(requestIn.getCliente());

			requestData.addBodyValue(requestIn.getCuenta().getTipoCuenta().equals(TIPO_CUENTA_UNICA)
					? TIPO_CUENTA_UNICA_PESOS : requestIn.getCuenta().getTipoCuenta());
			requestData.addBodyValue(requestIn.getCuenta().getNroSucursal());
			requestData.addBodyValue(requestIn.getCuenta().getNroCuentaProducto());
			requestData.addBodyValue(requestIn.getCantidadChequera());
			requestData.addBodyValue(requestIn.getCantidadCheque());
			requestData.addBodyValue(requestIn.getTipoChequera());

			request.setData(requestData);

			IatxResponse iatxResponse = iatxComm.exec(request);

			PedidoChequeraOutEntity pedidoChequeraEntity = processTrama(iatxResponse.getTrama(),
					PedidoChequeraOutEntity.class);
			pedidoChequeraEntity.setNumeroComprobante(iatxResponse.getNroComprobante());
			pedidoChequeraEntity.setFechaTransaccion(iatxResponse.getFechaHoraReq());
			pedidoChequeraEntity.setTipoChequera(requestIn.getTipoChequera());
			pedidoChequeraEntity.setCantidadChequera(requestIn.getCantidadChequera());
			pedidoChequeraEntity.setCantidadCheques(requestIn.getCantidadCheque());
			itemMensajeRespuesta.setMensaje(iatxResponse.getErrorMessage());

			respuestaPedidoChequeraEntity.add(itemMensajeRespuesta);
			respuestaPedidoChequeraEntity.setRespuesta(pedidoChequeraEntity);
			respuestaPedidoChequeraEntity.setEstadoRespuesta(iatxResponse.getEstadoRespuesta());
		} catch (IatxException e) {
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuestaPedidoChequeraEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaPedidoChequeraEntity.add(itemMensajeRespuesta);
			LOGGER.error(e.getMessage(), e);
		} catch (Exception ex) {
			itemMensajeRespuesta.setMensaje(ex.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuestaPedidoChequeraEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaPedidoChequeraEntity.add(itemMensajeRespuesta);
			LOGGER.error(ex.getMessage(), ex);
		}

		return respuestaPedidoChequeraEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.chequera.dao.PedidoChequeraDAO#
	 * generarComprobanteChequera(ar.com.santanderrio.obp.servicios.chequera.
	 * view.ChequeraConfirmacionViewResponse)
	 */
	@Override
	public Reporte generarComprobanteChequera(ChequeraConfirmacionViewResponse chequeraConfirmacionView) {
		Reporte reporte = new Reporte();

		try {
			List<PedidoChequeraInReport> listaPedidoChequera = new ArrayList<PedidoChequeraInReport>();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(CUENTA_ORIGEN, chequeraConfirmacionView.getChequeras().get(0).getNumeroCuenta());
			parameters.put(TIPO_CUENTA_ORIGEN, chequeraConfirmacionView.getChequeras().get(0).getTipoCuenta());
			parameters.put(MONEDA, chequeraConfirmacionView.getMoneda());
			parameters.put(SUCURSAL, chequeraConfirmacionView.getSucursalEntrega());
			parameters.put(DIRECCION_SUCURSAL, chequeraConfirmacionView.getDomicilioSucursal());
			parameters.put(LOCALIDAD_SUCURSAL, chequeraConfirmacionView.getLocalidadSucursal());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(FECHA_OPERACION, chequeraConfirmacionView.getFechaHora());

			for (int i = 0; i < chequeraConfirmacionView.getChequeras().size(); i++) {
				if (chequeraConfirmacionView.getChequeras().get(i).isPedidoChequeraOk()) {
					PedidoChequeraInReport pedidoChequeraInReport = new PedidoChequeraInReport();
					pedidoChequeraInReport
							.setCantidadChequera(chequeraConfirmacionView.getChequeras().get(i).getCantidadChequera());
					pedidoChequeraInReport
							.setCantidadCheques(chequeraConfirmacionView.getChequeras().get(i).getCantidadCheque());
					pedidoChequeraInReport.setNumeroComprobante(
							chequeraConfirmacionView.getChequeras().get(i).getNumeroComprobante());
					pedidoChequeraInReport
							.setTipoCuenta(chequeraConfirmacionView.getChequeras().get(i).getTipoChequera());
					listaPedidoChequera.add(pedidoChequeraInReport);
				}
			}

			descargarComprobanteChequera(reporte, listaPedidoChequera, jasperReport, parameters);
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
	}

	/**
	 * Descargar comprobante chequera.
	 *
	 * @param reporte
	 *            the reporte
	 * @param listaPedidoChequera
	 *            the lista pedido chequera
	 * @param jasperReport
	 *            the jasper report
	 * @param parameters
	 *            the parameters
	 * @throws JRException
	 *             the JR exception
	 */
	private void descargarComprobanteChequera(Reporte reporte, List<PedidoChequeraInReport> listaPedidoChequera,
			JasperReport jasperReport, HashMap<String, Object> parameters) throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(listaPedidoChequera));
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

		byte[] byteArray = outStream.toByteArray();
		reporte.setBytes(byteArray);
		reporte.setNombre("Comprobante_Pedido_Chequera" + PDF_EXTENSION);
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
	}
}
