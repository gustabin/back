package ar.com.santanderrio.obp.servicios.perfil.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosPersonalesPDF;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface DatosPersonalesDAO {

    Reporte descargarDatosPersonalesPDF(DatosPersonalesPDF datosPersonalesPDF) throws IOException, JRException;

}
