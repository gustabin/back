package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DetalleUltimosConsumosPDF extends DetalleComprobanteView {

    private static final long serialVersionUID = 1L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DetalleUltimosConsumosPDF.class);

    private HashMap<String, Object> parametros;

    protected final String pathTarjetas = "classpath:/report/tarjetas/";
    
    protected String pathImagenes = "classpath:/report/comprobantes/";
    
    protected final String ultimosConsumosJasper = "UltimosConsumosYPendientes.jasper";

    protected final String logoTransparente = "logo_transparente.png";

    protected final String logoKey = "LOGO";
    
    protected final String localeKey = "REPORT_LOCALE";

    protected final String consumosKey = "CONSUMOS";

    protected final String pendientesKey = "PENDIENTES";

    protected final String tieneConsumosKey = "TIENE_ULTIMOS_CONSUMOS";

    protected final String tienePendientesKey = "TIENE_CONSUMOS_PENDIENTES";

    protected final String totalConsumosPesosKey = "TOTAL_ULTIMOS_CONSUMOS_PESOS";

    protected final String totalConsumosDolaresKey = "TOTAL_ULTIMOS_CONSUMOS_DOLARES";

    protected final String totalPendientesPesosKey = "TOTAL_CONSUMOS_PENDIENTES_PESOS";
    
    protected final String totalPendientesDolaresKey = "TOTAL_CONSUMOS_PENDIENTES_DOLARES";

    public DetalleUltimosConsumosPDF(MovimientosTarjetaExcel2 movimientos) {
        setTituloComprobante("Ultimos Consumos");
        parametros = new HashMap<String, Object>();
        parametros.put(tituloKey, "Ultimos Consumos");
        parametros.put(fechaActualKey, fechaHoy());
        try {
            parametros.put(logoKey, ResourceUtils.getFile(pathImagenes + logoTransparente).getPath());
        } catch (FileNotFoundException e) {
            LOGGER.error("No se encontro el archivo jasper", e);
        }
        parametros.put(localeKey, new Locale("es", "AR"));
         
        parametros.put(consumosKey, new JRBeanCollectionDataSource(movimientos.getUltimosConsumos()));
        parametros.put(pendientesKey, new JRBeanCollectionDataSource(movimientos.getConsumosPendientes()));
        parametros.put(tieneConsumosKey, movimientos.getTieneUltimosConsumos());
        parametros.put(tienePendientesKey, movimientos.getTieneConsumosPendientes());
        
        parametros.put(totalConsumosPesosKey, movimientos.getTotalUltimosConsumosPesos());
        parametros.put(totalConsumosDolaresKey, movimientos.getTotalUltimosConsumosDolares());
        parametros.put(totalPendientesPesosKey, movimientos.getTotalConsumosPendientesPesos());
        parametros.put(totalPendientesDolaresKey, movimientos.getTotalConsumosPendientesDolares());
    }

    private String fechaHoy() {
        SimpleDateFormat formateador = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy '-' HH:mm",
                new Locale("es", "AR"));
        return formateador.format(new Date());
    }

    @Override
    public HashMap<String, Object> obtenerParametrosPDF() {
        return this.parametros;
    }

    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(pathTarjetas + ultimosConsumosJasper).getPath();
    }
}
