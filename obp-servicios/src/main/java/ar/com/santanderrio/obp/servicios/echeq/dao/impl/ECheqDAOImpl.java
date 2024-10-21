package ar.com.santanderrio.obp.servicios.echeq.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.echeq.common.TiposGrilla;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.IOperacionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ECheqDAOImpl.
 */
@Component
public class ECheqDAOImpl extends IatxBaseDAO implements ECheqDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ECheqDAOImpl.class);

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The fileJasperComprobanteAltaECheq. */
    @Value("classpath:/report/echeq/ComprobanteAltaECheq.jasper")
    private Resource fileJasperComprobanteAltaECheq;

    /** The fileJasperComprobanteEndosarECheq. */
    @Value("classpath:/report/echeq/ComprobanteEndosarECheq.jasper")
    private Resource fileJasperComprobanteEndosarECheq;

    /** The fileJasperComprobanteDetalleECheq. */
    @Value("classpath:/report/echeq/ComprobanteDetalleECheq.jasper")
    private Resource fileJasperComprobanteDetalleECheq;
    
    /** The fileJasperComprobanteCEDECheq. */
    @Value("classpath:/report/echeq/ComprobanteCEDECheq.jasper")
    private Resource fileJasperComprobanteCEDECheq;

    /** The logoCabecera. */
    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    private Resource logoCabecera;

    /** The logoCierre. */
    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    private Resource logoCierre;

    /** The Constant LOGO_CABECERA. */
    private static final String LOGO_CABECERA = "LOGO_CABECERA";

    /** The Constant NOMBRE_BENEFICIARIO. */
	private static final String NOMBRE_BENEFICIARIO = "NOMBRE_BENEFICIARIO";

	/** The Constant CUIT_BENEFICIARIO. */
	private static final String CUIT_BENEFICIARIO = "CUIT_BENEFICIARIO";

	/** The Constant IMPORTE. */
	private static final String IMPORTE = "IMPORTE";

	/** The Constant NUMERO_CUENTA. */
	private static final String NUMERO_CUENTA = "NUMERO_CUENTA";

	/** The Constant TIPO_CUENTA. */
	private static final String TIPO_CUENTA = "TIPO_CUENTA";

	/** The Constant FECHA_PAGO. */
	private static final String FECHA_PAGO = "FECHA_PAGO";

	/** The Constant MODALIDAD. */
	private static final String MODALIDAD = "MODALIDAD";

	/** The Constant COMPROBANTE. */
	private static final String COMPROBANTE = "COMPROBANTE";

	/** The Constant FECHA. */
	private static final String FECHA = "FECHA";

	/** The Constant EMISOR_CUIT. */
	private static final String EMISOR_CUIT = "EMISOR_CUIT";

	/** The Constant EMISOR_RAZON_SOCIAL. */
	private static final String EMISOR_RAZON_SOCIAL = "EMISOR_RAZON_SOCIAL";

	/** The Constant FECHA_EMISION. */
	private static final String FECHA_EMISION = "FECHA_EMISION";

	/** The Constant NUMERO_CHEQUE. */
	private static final String NUMERO_CHEQUE = "NUMERO_CHEQUE";

    /** The Constant LOGO_CIERRE. */
    private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant LEGALES. */
	private static final String LEGALES = "LEGALES";

	/** The Constant TIPO_ENDOSO. */
	private static final String TIPO_ENDOSO = "TIPO_ENDOSO";

	/** The Constant GRILLA_ORIGEN. */
	private static final String GRILLA_ORIGEN = "GRILLA_ORIGEN";

	/** The Constant ALIAS_CUENTA. */
	private static final String ALIAS_CUENTA = "ALIAS_CUENTA";

	/** The Constant ESTADO. */
	private static final String ESTADO = "ESTADO";

	/** The Constant MOTIVO. */
	private static final String MOTIVO = "MOTIVO";

	/** The Constant ENDOSOS. */
	private static final String ENDOSOS = "ENDOSOS";
	
	/** The Constant CESIONES. */
	private static final String CESIONES = "CESIONES";

	/** The Constant CUIT. */
	private static final String CUIT = "CUIT";

	/** The Constant RAZON_SOCIAL. */
	private static final String RAZON_SOCIAL = "RAZON_SOCIAL";

	/** The Constant SUBREPORT_DIR. */
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	/** The Constant ENTIDAD_GIRADA. */
	private static final String ENTIDAD_GIRADA = "ENTIDAD_GIRADA";
	
	/** The Constant CARACTER. */
	private static final String CARACTER = "CARACTER";

	/** The Constant DOMICILIO_PAGO. */
	private static final String DOMICILIO_PAGO = "DOMICILIO_PAGO";
	
	/** The Constant DOMICILIO_BENEFICIARIO. */
	private static final String DOMICILIO_BENEFICIARIO = "DOMICILIO_BENEFICIARIO";
	
	/** The Constant DOMICILIO_EMISOR. */
	private static final String DOMICILIO_EMISOR = "DOMICILIO_EMISOR";
	
	/** The Constant CMC7. */
	private static final String CMC7 = "CMC7";

	/** The Constant ID. */
	private static final String ID = "ID";	

	private static final String TIPO_CHEQUE = "TIPO_CHEQUE";
	

    @Override
    public OperationEcheqResponse ejecutar(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) throws DAOException {
        LOGGER.debug("ECheqDAOImpl - Iniciando metodo ejecutar()");
        IatxRequest iatxRequest = new IatxRequest(operacionesECheqInEntity.getNombreServicio(), operacionesECheqInEntity.getVersionServicio());
        try {
            LOGGER.info("ECheqDAOImpl - Iniciando llamada iatx " + operacionesECheqInEntity.getNombreServicio());
            IatxRequestData iatxRequestData = (IatxRequestData) operacionesECheqInEntity.generateRequestData(cliente, operacionesECheqInEntity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            LOGGER.info("ECheqDAOImpl _ Finalizando llamada iatx " + operacionesECheqInEntity.getNombreServicio());
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                return OperationEcheqResponse.builder()
                        .numeroComprobante(iatxResponse.getNroComprobante())
                        .status(Boolean.TRUE)
                        .build();
            } else {
                LOGGER.error(iatxResponse.getErrorMessage());
                return OperationEcheqResponse.builder()
                        .iatxErrorCode(Integer.toString(errorCode))
                        .status(Boolean.FALSE)
                        .build();
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * @see ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO#descargarComprobante(ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO)
     */
    @Override
    public Reporte descargarComprobante(ComprobanteECheqDTO datos) {
        LOGGER.debug("ECheqDAOImpl iniciando descargarComprobante()");
        String nombreArchivo;
        JasperPrint jasperPrint = null;
        JasperReport jasperReport = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        try {
        	if (OperacionEcheqEnum.ALTA.equals(datos.getOperacion())) {
        		LOGGER.debug("Completando parametros de reporte Alta Echeq");
        		nombreArchivo = "Comprobante_Emision_ECheq.pdf";
        		jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteAltaECheq.getInputStream());
                parameters.put(IMPORTE, datos.getImporte());
                parameters.put(NUMERO_CUENTA, ISBANStringUtils.formatearNroCuentaProductoConSucursal(datos.getCuenta()));
                parameters.put(TIPO_CUENTA, datos.getCuenta().getTipoCuentaEnum().getDescripcion());
                parameters.put(MODALIDAD, datos.getModalidad());
        	} else if (OperacionEcheqEnum.ENDOSAR.equals(datos.getOperacion())) {
        		LOGGER.debug("Completando parametros de reporte Endoso Echeq");
        		nombreArchivo = "Comprobante_Endoso_ECheq.pdf";
        		jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteEndosarECheq.getInputStream());
                parameters.put(IMPORTE, datos.getImporte());
                parameters.put(EMISOR_CUIT, datos.getEmisorCuit());
                parameters.put(EMISOR_RAZON_SOCIAL, datos.getEmisorRazonSocial());
                parameters.put(FECHA_EMISION, datos.getFechaEmision());
                parameters.put(NUMERO_CHEQUE, datos.getNumeroCheque());
                parameters.put(TIPO_ENDOSO, datos.getTipoEndoso());
        	} else if(OperacionEcheqEnum.EMITIR_CED.equals(datos.getOperacion())) {
        		LOGGER.debug("Completando parametros de reporte Alta CED");
        		nombreArchivo = "Comprobante_Emision_CED.pdf";
        		jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteCEDECheq.getInputStream());
        		parameters.put(IMPORTE, datos.getImporte());
                parameters.put(EMISOR_CUIT, datos.getEmisorCuit());
                parameters.put(EMISOR_RAZON_SOCIAL, datos.getEmisorRazonSocial());
                parameters.put(FECHA_EMISION, datos.getFechaEmision());
                parameters.put(NUMERO_CHEQUE, datos.getNumeroCheque());
                parameters.put(CARACTER, datos.getCaracter());
                parameters.put(DOMICILIO_BENEFICIARIO, datos.getDomicilioBeneficiario());
                parameters.put(ESTADO, datos.getEstado());
        	} else if (OperacionEcheqEnum.VER_DETALLE.equals(datos.getOperacion())) {
        		LOGGER.debug("Completando parametros de reporte Detalle Echeq");
        		nombreArchivo = "Detalle_ECheq.pdf";
        		jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteDetalleECheq.getInputStream());
        		parameters.put(GRILLA_ORIGEN, datos.getGrillaOrigen());
        		if (TiposGrilla.RECIBIDOS.getId().equals(datos.getGrillaOrigen()) 
        				|| TiposGrilla.EMITIDOS.getId().equals(datos.getGrillaOrigen())) {
	        		parameters.put(NUMERO_CUENTA, datos.getCuentaNumero());
	        		parameters.put(TIPO_CUENTA, datos.getCuentaTipo());
	        		parameters.put(ALIAS_CUENTA, datos.getCuentaAlias());
        		}
                parameters.put(IMPORTE, datos.getImporte());
                parameters.put(ESTADO, datos.getEstado());
                if (TiposGrilla.RECIBIDOS.getId().equals(datos.getGrillaOrigen())) {
                	parameters.put(CUIT, datos.getEmisorCuit());
                	parameters.put(RAZON_SOCIAL, datos.getEmisorRazonSocial());
                } else {
                	parameters.put(CUIT, datos.getCuitBeneficiario());
                	parameters.put(RAZON_SOCIAL, datos.getNombreBeneficiario());
                }
                parameters.put(MODALIDAD, datos.getModalidad());
                parameters.put(TIPO_CHEQUE, datos.getChequeTipo());
                parameters.put(CARACTER, datos.getCaracter());
                parameters.put(DOMICILIO_EMISOR, datos.getDomicilioEmisor());
                parameters.put(DOMICILIO_PAGO, datos.getDomicilioPago());
                parameters.put(CMC7, datos.getCmc7());
                parameters.put(ID, datos.getId());
                parameters.put(ENTIDAD_GIRADA, datos.getEntidadGirada());
                parameters.put(FECHA_EMISION, datos.getFechaEmision());
                parameters.put(MOTIVO, datos.getMotivo());
                parameters.put(NUMERO_CHEQUE, datos.getNumeroCheque());
                parameters.put(ENDOSOS, datos.getEndosos());
                parameters.put(CESIONES, datos.getCedidos());
                parameters.put(SUBREPORT_DIR, fileJasperComprobanteDetalleECheq.getFile().getParent() + File.separator);
        	} else {
        		LOGGER.error("descargarComprobante() - Operacion invalida: {}", datos.getOperacion());
                throw new ISBANRuntimeException();
        	}
        	parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
        	parameters.put(NOMBRE_BENEFICIARIO, datos.getNombreBeneficiario());
        	parameters.put(CUIT_BENEFICIARIO, datos.getCuitBeneficiario());
        	parameters.put(FECHA_PAGO, datos.getFechaPago());
        	parameters.put(COMPROBANTE, datos.getNumeroComprobante());
        	parameters.put(FECHA, datos.getFechaComprobante());
        	parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
        	parameters.put(LEGALES, datos.getLegales());

            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            // se procesa el archivo jasper
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            byte[] byteArray = outStream.toByteArray();
            Reporte reporte = new Reporte();
            reporte.setTipoArchivo(TipoArchivoEnum.PDF);
            reporte.setBytes(byteArray);
			reporte.setNombre(nombreArchivo);
            LOGGER.debug("ECheqDAOImpl finalizando descargarComprobante()");
            return reporte;
        } catch (JRException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ISBANRuntimeException(ex);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ISBANRuntimeException(e);
        }
    }

}
