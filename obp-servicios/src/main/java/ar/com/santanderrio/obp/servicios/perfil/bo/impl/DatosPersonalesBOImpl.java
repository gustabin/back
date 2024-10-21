package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.DatosPersonalesBO;
import ar.com.santanderrio.obp.servicios.perfil.dao.DatosPersonalesDAO;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosPersonalesPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl.LOGGER;

@Component
public class DatosPersonalesBOImpl implements DatosPersonalesBO {

    @Autowired
    private DatosPersonalesDAO datosPersonalesDAO;

    @Autowired
    private RespuestaFactory respuestaFactory;

    @Override
    public Respuesta<Reporte> descargarDatosPersonalesPDF(DatosPersonalesPDF datosPersonalesPDF) {
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        try {
            Reporte reporte = datosPersonalesDAO.descargarDatosPersonalesPDF(datosPersonalesPDF);
            respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaReporte.setRespuesta(reporte);
        } catch (Exception e) {
            LOGGER.error("Error al generar PDF", e);
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGAR_COMPROBANTE,
                    CodigoMensajeConstantes.DATOS_PERSONALES_DESCARGA_PDF_ERROR);
        }
        return respuestaReporte;
    }
}
