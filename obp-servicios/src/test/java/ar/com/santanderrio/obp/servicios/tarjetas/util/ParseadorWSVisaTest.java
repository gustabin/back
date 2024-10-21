/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Email;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldoEnCuentaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSVisaImpl;

/**
 * The Class ParseadorWSVisaTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class ParseadorWSVisaTest {

    /** The cuotas pendientes BO. */
    @InjectMocks
    private ParseadorWSVisaImpl parseador = new ParseadorWSVisaImpl();

    /**
     * Obtener tarjetas test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerTarjetasTest() throws ParseadorVisaException {

        RetornoTarjetasEntity entity = obtenerRetornoTarjetasEntitySinError();
        List<TarjetaEntity> retorno = parseador.obtenerTarjetas(entity);
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener codigo error sin error test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerCodigoErrorSinErrorTest() throws ParseadorVisaException {
        TarjetaEntity tarjeta = obtenerTarjetaEntity();
        String retorno = parseador.obtenerCodigoError(tarjeta);
        Assert.assertNull(retorno);
    }

    /**
     * Obtener codigo error con error test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerCodigoErrorConErrorTest() throws ParseadorVisaException {
        TarjetaEntity tarjeta = obtenerTarjetaEntityError();
        String retorno = parseador.obtenerCodigoError(tarjeta);
        Assert.assertEquals("110001", retorno);
    }

    /**
     * Obtener tarjeta activa test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerTarjetaActivaTest() throws ParseadorVisaException {
        TarjetaDocumentEntity tarjetaDocument = obtenerTarjetaDocument();
        String retorno = parseador.obtenerTarjetaActiva(tarjetaDocument);
        Assert.assertEquals("4050710080543809", retorno);
    }

    /**
     * Es categoria titular test.
     *
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void esCategoriaTitularTest() throws ParseadorVisaException {
        TarjetaEntity tarjeta = obtenerTarjetaEntity();
        Boolean retorno = parseador.esCategoriaTitular(tarjeta);
        Assert.assertEquals(true, retorno);
    }

    /**
     * Obtener retorno tarjetas entity sin error.
     *
     * @return the retorno tarjetas entity
     */
    private RetornoTarjetasEntity obtenerRetornoTarjetasEntitySinError() {
        RetornoTarjetasEntity entity = new RetornoTarjetasEntity();
        entity.setError(false);
        entity.setErrorTarjetas(null);

        entity.setTarjetas(obtenerTarjetasEntity());
        return entity;
    }

    /**
     * Obtener tarjetas entity.
     *
     * @return the tarjetas entity
     */
    private TarjetasEntity obtenerTarjetasEntity() {
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(obtenerTarjetaList());
        return tarjetas;
    }

    /**
     * Obtener tarjeta list.
     *
     * @return the list
     */
    private List<TarjetaEntity> obtenerTarjetaList() {
        List<TarjetaEntity> tarjetaList = new ArrayList<TarjetaEntity>();
        tarjetaList.add(obtenerTarjetaEntity());
        return tarjetaList;
    }

    /**
     * Obtener tarjeta entity.
     *
     * @return the tarjeta entity
     */
    private TarjetaEntity obtenerTarjetaEntity() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setError(null);
        tarjetaEntity.setPosicionConsolidada(obtenerPosicionConsolidada());
        tarjetaEntity.setTarjetaDocument(obtenerTarjetaDocument());
        return tarjetaEntity;
    }

    /**
     * Obtener tarjeta entity error.
     *
     * @return the tarjeta entity
     */
    private TarjetaEntity obtenerTarjetaEntityError() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setError(obtenerError());
        tarjetaEntity.setPosicionConsolidada(obtenerPosicionConsolidada());
        tarjetaEntity.setTarjetaDocument(obtenerTarjetaDocument());
        return tarjetaEntity;
    }

    /**
     * Obtener tarjeta document.
     *
     * @return the tarjeta document entity
     */
    private TarjetaDocumentEntity obtenerTarjetaDocument() {
        TarjetaDocumentEntity tarjetaDocumentEntity = new TarjetaDocumentEntity();
        tarjetaDocumentEntity.setAutorizaciones(obtenerAutorizaciones());
        tarjetaDocumentEntity.setCuotasPendientes(obtenerCuotasPendientes());
        tarjetaDocumentEntity.setDatos(obtenerDatos());
        tarjetaDocumentEntity.setSaldoEnCuenta(obtenerSaldoEnCuenta());
        tarjetaDocumentEntity.setSessionID(null);
        tarjetaDocumentEntity.setUltimosMovimientos(obtenerUltimosMovimientos());
        tarjetaDocumentEntity.getAutorizaciones();
        return tarjetaDocumentEntity;
    }

    /**
     * Obtener datos.
     *
     * @return the datos entity
     */
    private DatosEntity obtenerDatos() {
        DatosEntity datos = new DatosEntity();
        datos.setAffinityGroup("5348");
        datos.setApellido("Garay");
        datos.setCategoria("0");
        datos.setCodigoProducto("");
        datos.setCodigoSucursal("0");
        datos.setCodTipoTarjeta("CHIP EMV C/CONTAC");
        datos.setCuenta("17450870");
        datos.setDocumento("14029380");
        datos.setEmail(new Email());
        datos.setEstado("");
        datos.setFechaDesde("02/03/2015");
        datos.setFechaNacimiento("30/05/1960");
        datos.setHabiente("JORGE DANIEL/GARAY");
        datos.setId("");
        datos.setLogo("");
        datos.setNombre("Jorge");
        datos.setProducto("Platinum");
        datos.setSucursal("");
        datos.setTarjetaActiva("4050710080543809");
        datos.setTarjetaProdu("CR0702");
        datos.setTelefono("");
        datos.setTipoTarjetaDetalle("CHIP EMV C/CONTAC");
        datos.setTitular("JORGE DANIEL/GARAY");
        datos.setVencimiento("1801");
        return datos;
    }

    /**
     * Obtener ultimos movimientos.
     *
     * @return the ultimos consumos entity
     */
    private UltimosConsumosEntity obtenerUltimosMovimientos() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Obtener saldo en cuenta.
     *
     * @return the saldo en cuenta entity
     */
    private SaldoEnCuentaEntity obtenerSaldoEnCuenta() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Obtener cuotas pendientes.
     *
     * @return the cuotas pendientes entity
     */
    private CuotasPendientesEntity obtenerCuotasPendientes() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Obtener autorizaciones.
     *
     * @return the autorizaciones entity
     */
    private AutorizacionesEntity obtenerAutorizaciones() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Obtener posicion consolidada.
     *
     * @return the posicion consolidada entity
     */
    private PosicionConsolidadaEntity obtenerPosicionConsolidada() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Obtener error.
     *
     * @return the error tarjetas entity
     */
    private ErrorTarjetasEntity obtenerError() {
        ErrorTarjetasEntity error = new ErrorTarjetasEntity();
        error.setCodigo("110001");
        error.setErrorID("132266551");
        error.setSessionID("H0exjwhmdksc7hgkNjaRQgkZ(wKgBKBDN)");
        return error;
    }
}
