package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TipoDocumento;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSCuotasPendientesImpl;

/**
 * Test de clase ParseadorWSCuotasPendientesTest.
 *
 * @author federico.n.flores
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseadorWSCuotasPendientesTest {

    /** The parseador. */
    @InjectMocks
    private ParseadorWSCuotasPendientesImpl parseador = new ParseadorWSCuotasPendientesImpl();

    /**
     * Obtener total cuotas pendientes test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerTotalCuotasPendientesTest() throws ParseadorVisaException {
        TarjetaEntity tarjetaEntity = obtenerTarjetaEntity();
        ErrorTarjetasEntity error = new ErrorTarjetasEntity();
        error.setCodigo("10007");
        tarjetaEntity.setError(error);
        String respuesta = parseador.obtenerCodigoError(tarjetaEntity);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener numero tarjeta test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerNumeroTarjetaTest() throws ParseadorVisaException {
        List<String> respuesta = parseador.obtenerCodigosDeTarjetas(obtenerTarjetaEntity());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener total tarjeta test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerTotalTarjetaTest() throws ParseadorVisaException {
        String respuesta = parseador.obtenerEstablecimiento(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener establecimiento test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerEstablecimientoTest() throws ParseadorVisaException {
        String respuesta = parseador.obtenerEstablecimiento(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener fecha test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaTest() throws ParseadorVisaException, TarjetaBOUtilsException {
        Date respuesta = parseador.obtenerFecha(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener numero comprobante test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerNumeroComprobanteTest() throws ParseadorVisaException {
        String respuesta = parseador.obtenerNumeroComprobante(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener cuotas test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasTest() throws ParseadorVisaException, TarjetaBOUtilsException {
        Integer respuesta = parseador.obtenerCuotas(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener cuotas pendientes test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerCuotasPendientesTest() throws ParseadorVisaException, TarjetaBOUtilsException {
        Integer respuesta = parseador.obtenerCuotasPendientes(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener moneda test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerMonedaTest() throws ParseadorVisaException {
        String respuesta = parseador.obtenerMoneda(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener importe test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerImporteTest() throws ParseadorVisaException, TarjetaBOUtilsException {
        BigDecimal respuesta = parseador.obtenerImporte(obtenerDetalleCuotaPendiente());
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    private RetornoTarjetasEntity obtenerRetornoWS() {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        retornoWS.setTarjetas(obtenerTarjetasEntity());
        return retornoWS;
    }

    /**
     * Obtener tarjetas.
     *
     * @return the tarjetas
     */
    private TarjetasEntity obtenerTarjetasEntity() {
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(obtenerListaTarjetaEntity());
        return tarjetas;
    }

    /**
     * Obtener lista tarjetas.
     *
     * @return the list
     */
    private List<TarjetaEntity> obtenerListaTarjetaEntity() {
        List<TarjetaEntity> tarjetaList = new ArrayList<TarjetaEntity>();
        tarjetaList.add(obtenerTarjetaEntity());
        return tarjetaList;
    }

    /**
     * Obtener tarjeta desde tarjeta document.
     *
     * @return the tarjeta
     */
    private TarjetaEntity obtenerTarjetaEntity() {
        TarjetaDocumentEntity tarjetaDocument = obtenerTarjetaDocumentEntity();
        TarjetaEntity tarjeta = new TarjetaEntity();
        tarjeta.setTarjetaDocument(tarjetaDocument);
        return tarjeta;
    }

    /**
     * Obtener tarjeta document.
     *
     * @return the tarjeta document
     */
    private TarjetaDocumentEntity obtenerTarjetaDocumentEntity() {
        TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
        tarjetaDocument.setDatos(obtenerDatosTarjetaUno());
        tarjetaDocument.setCuotasPendientes(obtenerCuotasPendientesEntity());
        return tarjetaDocument;
    }

    /**
     * Obtener cuotas pendientes.
     *
     * @return the cuotas pendientes
     */
    private CuotasPendientesEntity obtenerCuotasPendientesEntity() {
        CuotasPendientesEntity cuotasPendientes = new CuotasPendientesEntity();
        cuotasPendientes.setTotales(obtenerTotales("0,00", "316,32"));
        cuotasPendientes.setTarjetaList(obtenerTarjetaCuotasPendientesEntity());
        return cuotasPendientes;
    }

    /**
     * Obtener la lista de tarjetas con cuotas pendientes.
     *
     * @return the list
     */
    private List<TarjetaCuotasPendientesEntity> obtenerTarjetaCuotasPendientesEntity() {
        List<TarjetaCuotasPendientesEntity> tarjetaCuotasPendientesDTOList = new ArrayList<TarjetaCuotasPendientesEntity>();
        tarjetaCuotasPendientesDTOList.add(obtenerTarjetaCuotasPendientesEntity("XXXX XXXX XXXX2392", "0,00", "316,32",
                obtenerListaDetalleCuotaPendienteTarjeta()));
        return tarjetaCuotasPendientesDTOList;
    }

    /**
     * Obtener tarjeta con cuotas pendientes.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @param dolares
     *            the dolares
     * @param pesos
     *            the pesos
     * @param detallesCuotas
     *            the detalles cuotas
     * @return the tarjeta cuotas pendientes DTO
     */
    private TarjetaCuotasPendientesEntity obtenerTarjetaCuotasPendientesEntity(String codigoTarjeta, String dolares,
            String pesos, List<DetalleCuotaPendienteEntity> detallesCuotas) {
        TarjetaCuotasPendientesEntity tarjetaDTO = new TarjetaCuotasPendientesEntity();
        tarjetaDTO.setCodigoTarjeta(codigoTarjeta);
        tarjetaDTO.setPesos(pesos);
        tarjetaDTO.setDolares(dolares);
        tarjetaDTO.setDetalleCuotaPendienteList(detallesCuotas);
        return tarjetaDTO;
    }

    /**
     * Obtener la lista de detalles de cuota pendiente por tarjeta.
     *
     * @return the list
     */
    private List<DetalleCuotaPendienteEntity> obtenerListaDetalleCuotaPendienteTarjeta() {
        List<DetalleCuotaPendienteEntity> detalleList = new ArrayList<DetalleCuotaPendienteEntity>();
        detalleList.add(obtenerDetalleCuotaPendiente());
        return detalleList;
    }

    /**
     * Obtener cada detalle de cuota pendiente.
     *
     * @return the detalle cuota pendiente
     */
    private DetalleCuotaPendienteEntity obtenerDetalleCuotaPendiente() {
        String comprobante = "23431780";
        String cuotas = "12";
        String cuotasPendientes = "02";
        String establecimiento = "WWW.GARBARINO.COM.AR               ";
        String fecha = "07/07/2015";
        String importe = "316,32";
        String moneda = "pesos";
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setEstablecimiento(establecimiento);
        detalleCuotaPendiente.setFecha(fecha);
        detalleCuotaPendiente.setComprobante(comprobante);
        detalleCuotaPendiente.setCuotas(cuotas);
        detalleCuotaPendiente.setCuotasPendientes(cuotasPendientes);
        detalleCuotaPendiente.setMoneda(moneda);
        detalleCuotaPendiente.setImporte(importe);
        return detalleCuotaPendiente;
    }

    /**
     * Obtener totales de las cuotas pendientes.
     *
     * @param dolares
     *            the dolares
     * @param pesos
     *            the pesos
     * @return the totales
     */
    private Totales obtenerTotales(String dolares, String pesos) {
        Totales totales = new Totales();
        totales.setDolares(dolares);
        totales.setPesos(pesos);
        return totales;
    }

    /**
     * Obtener el objeto TipoDocumento.
     *
     * @param codigo
     *            the codigo
     * @param valor
     *            the valor
     * @return the tipo documento
     */
    private TipoDocumento obtenerTipoDocumento(String codigo, String valor) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setCodigo(codigo);
        tipoDocumento.setValor(valor);
        return tipoDocumento;
    }

    /**
     * Obtener datos de la tarjeta.
     *
     * @return the datos
     */
    private DatosEntity obtenerDatosTarjetaUno() {
        DatosEntity datos = new DatosEntity();
        datos.setAffinityGroup("Grupo_Afinidad");
        datos.setApellido("SPORLEDER");
        datos.setCategoria("0");
        datos.setCodTipoTarjeta("CHIP EMV C/CONTAC");
        datos.setCodigoSucursal("201");
        datos.setCuenta("413034030");
        datos.setFechaDesde("04/05/2015");
        datos.setFechaNacimiento("18/11/1984");
        datos.setHabiente("SPORLEDER/BELEN");
        datos.setNombre("BELEN");
        datos.setDocumento("31303514");
        datos.setProducto("Gold");
        datos.setTarjetaActiva("4509950140722392");
        datos.setTarjetaProdu("CR0202");
        datos.setTelefono("8060529");
        datos.setTipoDocumento(obtenerTipoDocumento("1", "DNI"));
        datos.setTipoTarjetaDetalle("CHIP EMV C/CONTAC");
        datos.setTitular("SPORLEDER/BELEN");
        datos.setVencimiento("1608");
        return datos;
    }

    /**
     * Obtener lista de tarjetas activas test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerListaDeTarjetasActivasTest() throws ParseadorVisaException {
        TarjetasEntity tarjetasDTO = obtenerTarjetasEntity();
        String tarjetaActiva = "4509950140722392";

        Cuenta cuenta = new Cuenta();
        cuenta.setNroTarjetaCredito("00004509950140722392");
        cuenta.setTipoCuenta("7");
        cuenta.setClaseCuenta("R");

        // List<String> retorno =
        // obtencionDatosCuotasPendientesImpl.obtenerListaDeTarjetasActivas(tarjetasDTO,
        // cuenta);
        // Assert.assertNotNull(retorno);
    }

}
