package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.bo.DescargaPdfBO;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

@Component
public class DescargaPdfBOImpl implements DescargaPdfBO {

    /** The ondeman dao. */
    @Autowired
    private OndemandDAO ondemanDao;
    
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
    @Value("${RESUMENONDEMAND.PRIVADA.MESES}")
    private String cantidadMeses;
    
    private static final String FORMATO_FECHA = "dd/MM/yy";
    
	
	
	@Override
	public Respuesta<List<ResumenMensualCuenta>> obtenerListaComprobantes(AbstractCuenta cuenta, TipoPDFEnum tipoPDF)
			throws BusinessException, WSODException {
		ResumenParams params = configurarParamentrosConsultaLista(cuenta, tipoPDF);

        List<ResumenMensualCuenta> listaResumenes = ondemanDao.obtenerListaComprobantesBancaPrivada(params);
    	
	    // Recorre cada resumen y le agrega un id que sera utilizado para
	    // identicar que resumen se quiere descargar
	    if (listaResumenes != null) {
	        Long id = Long.valueOf(0);
	        for (ResumenMensualCuenta resumenMensualCuenta : listaResumenes) {
	            resumenMensualCuenta.setId(id);
	            id++;
	        }
	    }
	    	    
    	return respuestaFactory.crearRespuestaOk(listaResumenes);
	}
	
    private ResumenParams configurarParamentrosConsultaLista(AbstractCuenta cuenta, TipoPDFEnum tipoPDF) {
        ResumenParams params = new ResumenParams();
        params.setUsuarioConsulta(cuenta.getCliente().getNup());
        params.setCuenta(cuenta);
        params.setMotivoConsulta("request");
        params.setTipoPDF(tipoPDF);
        Date fechaHasta = new Date();
        Date fechaDesde = restarMeses(fechaHasta, Integer.parseInt(cantidadMeses));
        if (validarFechas(fechaDesde, fechaHasta)) {
            params.setFechaDesde(ISBANStringUtils.formatearFecha(fechaDesde, FORMATO_FECHA));
            params.setFechaHasta(ISBANStringUtils.formatearFecha(fechaHasta, FORMATO_FECHA));
        } else {
            return null;
        }
        return params;
    }
    
    private boolean validarFechas(Date fechaDesde, Date fechaHasta) {
        boolean result = false;
        if (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) <= 0) {
            result = true;
        }
        return result;
    }
    
    private Date restarMeses(Date fecha, int cantMeses) {
        Calendar fechaCalendar = new GregorianCalendar();
        fechaCalendar.setTime(fecha);
        fechaCalendar.add(Calendar.MONTH, -cantMeses);
        return fechaCalendar.getTime();
    }
	
    @Override
    public Respuesta<ReporteResumenMensualCuenta> obtenerPDF(ResumenMensualCuenta resumenSeleccionado,
            AbstractCuenta cuenta, TipoPDFEnum tipoPDF) {

    	Respuesta<ReporteResumenMensualCuenta> respuesta; 
    	ReporteResumenMensualCuenta reporte = new ReporteResumenMensualCuenta();
    	
        try {
        	if (TipoPDFEnum.CUENTAS_BANCA_PRIVADA.equals(tipoPDF)) {
        		reporte = ondemanDao.obtenerReporteMensualBP(resumenSeleccionado, cuenta);
        	} else {
        		reporte = ondemanDao.obtenerReporteComprobantesBP(resumenSeleccionado, cuenta, tipoPDF);
        	}
        	reporte.setTipoArchivo(TipoArchivoEnum.PDF);
            respuesta = respuestaFactory.crearRespuestaOk(reporte);
        } catch (WSODException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, null, TipoError.ERROR_GENERICO, "");
        } catch (RuntimeException e) {
            respuesta = respuestaFactory.crearRespuestaError(null, null, TipoError.ERROR_GENERICO, "");
        }
        return respuesta;
    }
}
