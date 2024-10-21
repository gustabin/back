package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosExtra;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechasUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimiteUltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimitesUltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LineaUltimaLiquidacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PagoUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PagosUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldoTasaUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldosUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TasasUltimaLiquidacion;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Total;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimaLiquidacionEntity;

/**
 * The Class UltimaLiquidacionEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class UltimaLiquidacionEntityMock {

    private UltimaLiquidacionEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completa la informacion de la ultima liquidacion entity.
     *
     * @return the ultima liquidacion entity
     */
    public static UltimaLiquidacionEntity completarInfoUltimaLiquidacion() {
        UltimaLiquidacionEntity ultimaLiquidacionEntity = new UltimaLiquidacionEntity();
        ultimaLiquidacionEntity.setNumero("78147");
        ultimaLiquidacionEntity.setResumen("800");
        ultimaLiquidacionEntity.setTotal(completarInfoTotal());
        ultimaLiquidacionEntity.setDatos(DatosMock.completarInfoDatos());
        ultimaLiquidacionEntity.setDatosExtra(completarInfoDatosExtra());
        ultimaLiquidacionEntity.setFechas(completarInfoFechas());
        ultimaLiquidacionEntity.setPagos(completarInfoPagos());
        ultimaLiquidacionEntity.setLimites(completarInfoLimites());
        ultimaLiquidacionEntity.setSaldos(completarInfoSaldos());
        ultimaLiquidacionEntity.setTasas(completarInfoTasas());
        ultimaLiquidacionEntity.setDetalleLiquidacion(completarInfoDetalleLiquidacion());
        return ultimaLiquidacionEntity;
    }

    /**
     * Completar info detalle liquidacion.
     *
     * @return the detalle liquidacion
     */
    private static DetalleLiquidacion completarInfoDetalleLiquidacion() {
        DetalleLiquidacion detalle = new DetalleLiquidacion();
        detalle.setLineas(completarListaLineas());
        return detalle;
    }

    /**
     * Completar lista lineas.
     *
     * @return the list
     */
    private static List<LineaUltimaLiquidacionEntity> completarListaLineas() {
        List<LineaUltimaLiquidacionEntity> lineas = new ArrayList<LineaUltimaLiquidacionEntity>();
        lineas.add(
                completarInfoLinea("05/09/16 SU PAGO EN PESOS                                 3065,49-             "));
        lineas.add(
                completarInfoLinea("23/01/16 ARREDO                      C.08/12  792507*      241,50              "));
        lineas.add(
                completarInfoLinea(" TOTAL TARJETA  XXXX XXXX XXXX  7280  ......         3.557,14 *          13,98 *"));
        return lineas;
    }

    /**
     * Completar info linea.
     *
     * @param descripcion
     *            the descripcion
     * @return the linea ultima liquidacion
     */
    private static LineaUltimaLiquidacionEntity completarInfoLinea(String descripcion) {
        LineaUltimaLiquidacionEntity linea = new LineaUltimaLiquidacionEntity();
        linea.setDescripcion(descripcion);
        return linea;
    }

    /**
     * Completar info tasas.
     *
     * @return the tasas ultima liquidacion
     */
    private static TasasUltimaLiquidacion completarInfoTasas() {
        TasasUltimaLiquidacion tasasUltimaLiquidacion = new TasasUltimaLiquidacion();
        tasasUltimaLiquidacion.setTasas(completarInfoTasasLista());
        return tasasUltimaLiquidacion;
    }

    /**
     * Completar info tasas lista.
     *
     * @return the list
     */
    private static List<SaldoTasaUltimaLiquidacion> completarInfoTasasLista() {
        List<SaldoTasaUltimaLiquidacion> tasaList = new ArrayList<SaldoTasaUltimaLiquidacion>();
        tasaList.add(completarInfoSaldoTasa("TNA", "38,00", "0,00"));
        tasaList.add(completarInfoSaldoTasa("TNA", "38,00", "0,00"));
        tasaList.add(completarInfoSaldoTasa("TEM", "3,12", "0,00"));
        return tasaList;
    }

    /**
     * Completar info saldos.
     *
     * @return the saldos ultima liquidacion
     */
    private static SaldosUltimaLiquidacion completarInfoSaldos() {
        SaldosUltimaLiquidacion saldosUltimaLiquidacion = new SaldosUltimaLiquidacion();
        saldosUltimaLiquidacion.setSaldos(completarInfoSaldosLista());
        return saldosUltimaLiquidacion;
    }

    /**
     * Completar info saldos lista.
     *
     * @return the list
     */
    private static List<SaldoTasaUltimaLiquidacion> completarInfoSaldosLista() {
        List<SaldoTasaUltimaLiquidacion> saldoList = new ArrayList<SaldoTasaUltimaLiquidacion>();
        saldoList.add(completarInfoSaldoTasa("actual", "3.560,54", "13,98"));
        saldoList.add(completarInfoSaldoTasa("anterior", "3.065,49", "0,00"));
        return saldoList;
    }

    /**
     * Completar info saldo tasa.
     *
     * @param descripcion
     *            the descripcion
     * @param total
     *            the total
     * @param usdTotal
     *            the usd total
     * @return the saldo tasa ultima liquidacion
     */
    private static SaldoTasaUltimaLiquidacion completarInfoSaldoTasa(String descripcion, String total,
            String usdTotal) {
        SaldoTasaUltimaLiquidacion saldoTasaUltimaLiquidacion = new SaldoTasaUltimaLiquidacion();
        saldoTasaUltimaLiquidacion.setDescripcion(descripcion);
        saldoTasaUltimaLiquidacion.setTotal(total);
        saldoTasaUltimaLiquidacion.setUsdTotal(usdTotal);
        return saldoTasaUltimaLiquidacion;
    }

    /**
     * Completar info limites.
     *
     * @return the limites ultima liquidacion
     */
    private static LimitesUltimaLiquidacionEntity completarInfoLimites() {
        LimitesUltimaLiquidacionEntity limitesUltimaLiquidacion = new LimitesUltimaLiquidacionEntity();
        limitesUltimaLiquidacion.setLimites(completarInfoLimitesLista());
        return limitesUltimaLiquidacion;
    }

    /**
     * Completar info limites lista.
     *
     * @return the list
     */
    private static List<LimiteUltimaLiquidacionEntity> completarInfoLimitesLista() {
        List<LimiteUltimaLiquidacionEntity> limiteUltimaLiquidacionList = new ArrayList<LimiteUltimaLiquidacionEntity>();
        limiteUltimaLiquidacionList.add(completarInfoLimite("compra", "21.000,00"));
        limiteUltimaLiquidacionList.add(completarInfoLimite("compracuotas", "31.500,00"));
        limiteUltimaLiquidacionList.add(completarInfoLimite("financiero", "18.900,00"));
        limiteUltimaLiquidacionList.add(completarInfoLimite("adelanto", "4.200,00"));
        return limiteUltimaLiquidacionList;
    }

    /**
     * Completar info limite.
     *
     * @param descripcion
     *            the descripcion
     * @param total
     *            the total
     * @return the limite ultima liquidacion
     */
    private static LimiteUltimaLiquidacionEntity completarInfoLimite(String descripcion, String total) {
        LimiteUltimaLiquidacionEntity limiteUltimaLiquidacion = new LimiteUltimaLiquidacionEntity();
        limiteUltimaLiquidacion.setDescripcion(descripcion);
        limiteUltimaLiquidacion.setTotal(total);
        return limiteUltimaLiquidacion;
    }

    /**
     * Completar info pagos.
     *
     * @return the pagos ultima liquidacion
     */
    private static PagosUltimaLiquidacion completarInfoPagos() {
        PagosUltimaLiquidacion pagosUltimaLiquidacion = new PagosUltimaLiquidacion();
        pagosUltimaLiquidacion.setPagos(completarInfoPagosLista());
        return pagosUltimaLiquidacion;
    }

    /**
     * Completar info pagos lista.
     *
     * @return the list
     */
    private static List<PagoUltimaLiquidacion> completarInfoPagosLista() {
        List<PagoUltimaLiquidacion> pagoUltimaLiquidacionList = new ArrayList<PagoUltimaLiquidacion>();
        pagoUltimaLiquidacionList.add(completarInfoPagoUltimaLiquidacion("minimo", "pesos", "190,00"));
        pagoUltimaLiquidacionList.add(completarInfoPagoUltimaLiquidacion("minimo", "dolares", "0,00"));
        return pagoUltimaLiquidacionList;
    }

    /**
     * Completar info pago ultima liquidacion.
     *
     * @param descripcion
     *            the descripcion
     * @param tipoMoneda
     *            the tipo moneda
     * @param total
     *            the total
     * @return the pago ultima liquidacion
     */
    private static PagoUltimaLiquidacion completarInfoPagoUltimaLiquidacion(String descripcion, String tipoMoneda,
            String total) {
        PagoUltimaLiquidacion pago = new PagoUltimaLiquidacion();
        pago.setDescripcion(descripcion);
        pago.setTipoMoneda(tipoMoneda);
        pago.setTotal(total);
        pago.setUsdTotal(null);
        return pago;
    }

    /**
     * Completar info fechas.
     *
     * @return the fechas ultima liquidacion
     */
    private static FechasUltimaLiquidacion completarInfoFechas() {
        FechasUltimaLiquidacion fechasUltimaLiquidacion = new FechasUltimaLiquidacion();
        fechasUltimaLiquidacion.setFechas(completarInfoFechasLista());
        return fechasUltimaLiquidacion;
    }

    /**
     * Completar info fechas lista.
     *
     * @return the list
     */
    private static List<FechaUltimaLiquidacion> completarInfoFechasLista() {
        List<FechaUltimaLiquidacion> fechaUltimaLiquidacionList = new ArrayList<FechaUltimaLiquidacion>();
        fechaUltimaLiquidacionList.add(completarInfoFechaUltimaLiquidacion("25/08/2016", "anterior", "07/09/2016"));
        fechaUltimaLiquidacionList.add(completarInfoFechaUltimaLiquidacion("20/10/2016", "proximo", "02/11/2016"));
        fechaUltimaLiquidacionList.add(completarInfoFechaUltimaLiquidacion("22/09/2016", "actual", "05/10/2016"));
        return fechaUltimaLiquidacionList;
    }

    /**
     * Completar info fecha ultima liquidacion.
     *
     * @param cierre
     *            the cierre
     * @param descripcion
     *            the descripcion
     * @param vencimiento
     *            the vencimiento
     * @return the fecha ultima liquidacion
     */
    private static FechaUltimaLiquidacion completarInfoFechaUltimaLiquidacion(String cierre, String descripcion,
            String vencimiento) {
        FechaUltimaLiquidacion fecha = new FechaUltimaLiquidacion();
        fecha.setCierre(cierre);
        fecha.setDescripcion(descripcion);
        fecha.setVencimiento(vencimiento);
        return fecha;
    }

    /**
     * Completar info datos extra.
     *
     * @return the datos extra
     */
    private static DatosExtra completarInfoDatosExtra() {
        DatosExtra datosExtra = new DatosExtra();
        datosExtra.setDni("33467953");
        datosExtra.setCalle("AV FEDERICO LACROZE");
        datosExtra.setCart("02");
        datosExtra.setCp("1426");
        datosExtra.setCuit("27-33467953-6");
        datosExtra.setDptoNumero("C");
        datosExtra.setIva("F");
        datosExtra.setLeyendaIva("CONSUMIDOR FINAL");
        datosExtra.setLocalidad("CAPITAL");
        datosExtra.setDocumento("33467953");
        datosExtra.setPiso("6");
        datosExtra.setPuerta("03164");
        datosExtra.setSucursal("404");
        return datosExtra;
    }

    /**
     * Completar info total.
     *
     * @return the total
     */
    private static Total completarInfoTotal() {
        Total total = new Total();
        total.setSessionID("ksBgRjkOQ59E3GTSmA3ARogF");
        total.setValor("1");
        return total;
    }
}
