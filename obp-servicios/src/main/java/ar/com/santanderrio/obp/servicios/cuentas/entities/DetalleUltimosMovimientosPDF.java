package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.bo.ItemReporteDetalle;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DetalleUltimosMovimientosPDF extends DetalleComprobanteView {

    private static final long serialVersionUID = 1L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DetalleUltimosMovimientosPDF.class);

    private HashMap<String, Object> parametros;

    protected String pathImagenes = "classpath:/report/comprobantes/";

    protected final String pathJasper = "classpath:/report/cuentas/";

    protected final String ultimosMovimientosJasper = "UltimosMovimientos.jasper";

    protected final String logoTransparente = "logo_transparente.png";

    protected final String logoKey = "LOGO";
    
    protected final String localeKey = "REPORT_LOCALE";

    protected final String cabeceraKey = "CABECERA";

    protected final String movimientosKey = "MOVIMIENTOS";

    protected final String tipoCuentaKey = "TIPO_CTA";

    protected final String tipoCuenta2Key = "TIPO_CTA2";

    protected final String multipleCuentaKey = "TIENE_MULTIPLE_CTA";

    protected final String monedaKey = "MONEDA";

    protected final String legalKey = "LEGAL_PIE";

    public DetalleUltimosMovimientosPDF(MovimientosExcel movimientosExcel) {
        setTituloComprobante("Ultimos Movimientos de Cuenta");
        parametros = new HashMap<String, Object>();
        parametros.put(tituloKey, "Ultimos Movimientos de Cuenta");
        parametros.put(fechaActualKey, fechaHoy());
        try {
            parametros.put(logoKey, ResourceUtils.getFile(pathImagenes + logoTransparente).getPath());
        } catch (FileNotFoundException e) {
            LOGGER.error("No se encontro el archivo jasper", e);
        }
        parametros.put(legalKey,
                "La presente búsqueda no refleja los movimientos y/o transacciones que se encuentran pendientes de confirmación.");
        parametros.put(localeKey, new Locale("es", "AR"));

        MovimientosExcelCabecera cabecera = movimientosExcel.getCabecera();
        List<ItemReporteDetalle> listCabecera = new ArrayList<ItemReporteDetalle>();
        listCabecera.add(new ItemReporteDetalle("Cuenta", cabecera.getTipoCuenta() + " " + cabecera.getNumeroCuenta()));
        listCabecera.add(new ItemReporteDetalle("Moneda", cabecera.getMoneda()));
        listCabecera.add(new ItemReporteDetalle("Fecha", cabecera.getFechaDesde() + " - " + cabecera.getFechaHasta()));
        if (cabecera.getMostrarFiltroPalabraClave()) {
            listCabecera.add(new ItemReporteDetalle("Palabra clave", cabecera.getPalabraClave()));
        }
        if (cabecera.getMostrarFiltroImportes()) {
            listCabecera.add(new ItemReporteDetalle("Importes",
                    cabecera.getImporteDesde() + " - " + cabecera.getImporteHasta()));
        }
        parametros.put(cabeceraKey, new JRBeanCollectionDataSource(listCabecera));
        parametros.put(movimientosKey, new JRBeanCollectionDataSource(movimientosExcel.getMovimientos()));
        parametros.put(multipleCuentaKey, false);

        if ("Pesos".equals(cabecera.getMoneda())) {
            parametros.put(monedaKey, "pesos");
            if (!cabecera.getMostrarSaldoAperturado()) {
                parametros.put(tipoCuentaKey, "Importe pesos");
            } else if (cabecera.getIsConvenioPAS()) {
                parametros.put(tipoCuentaKey, "Cuenta sueldo");
            } else {
                parametros.put(tipoCuentaKey, "Importe caja de ahorro pesos");
            }
            if (cabecera.getHasCtaCte() && cabecera.getMostrarSaldoAperturado()) {
                parametros.put(multipleCuentaKey, true);
                parametros.put(tipoCuenta2Key, "Importe cuenta corriente pesos");
            }
        } else if ("Dólares".equals(cabecera.getMoneda())) {
            parametros.put(monedaKey, "dólares");
            if (!cabecera.getMostrarSaldoAperturado()) {
                parametros.put(tipoCuentaKey, "Importe dólares");
            } else {
                parametros.put(tipoCuentaKey, "Importe caja de ahorro dólares");
            }
        }
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
        return ResourceUtils.getFile(pathJasper + ultimosMovimientosJasper).getPath();
    }
}
