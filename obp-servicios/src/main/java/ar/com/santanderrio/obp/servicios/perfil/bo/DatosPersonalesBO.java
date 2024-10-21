package ar.com.santanderrio.obp.servicios.perfil.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosPersonalesPDF;
import org.springframework.stereotype.Component;

public interface DatosPersonalesBO {

    Respuesta<Reporte> descargarDatosPersonalesPDF(DatosPersonalesPDF datosPersonalesPDF);

}
