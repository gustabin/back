package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CasuisticaErrorTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.UltimosConsumosTarjetaBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.MovimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaUltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TipoConsumoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSUltimoConsumoImpl;

/**
 * The Class UltimosConsumosTarjetaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class UltimosConsumosTarjetaBOTest {

    /** The reporte DAO *. */
    @Mock
    private ReporteDAO reporteDAO;

    /** The cuenta BO *. */
    @Mock
    private CuentaBO cuentaBO;

    /** The tarjeta DAO *. */
    @Mock
    private TarjetaDAO tarjetaDao;

    /** The mensaje DAO *. */
    @Mock
    private MensajeBO mensajeBO;

    /** The legal BO *. */
    @Mock
    private LegalBO legalBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

    /** The ultimos consumos tarjeta BO. */
    @InjectMocks
    private UltimosConsumosTarjetaBO ultimosConsumosTarjetaBO = new UltimosConsumosTarjetaBOImpl();

    /** The parseador. */
    @InjectMocks
    @Spy
    private ParseadorWSUltimoConsumoImpl parseador = new ParseadorWSUltimoConsumoImpl();

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    }

    /**
     * Obtener ultimos consumos ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void generarUltimosConsumosDTOTest() throws BusinessException, DAOException, ParseadorVisaException {
        List<ConsumoTarjetaDTO> listConsumoTarjetaDTO = completarListConsumoTarjetaDTO();
        UltimosConsumosDTO res = ultimosConsumosTarjetaBO.generarUltimosConsumosDTO(listConsumoTarjetaDTO,
                obtenerCuenta("00004660570052763245"));
        Assert.assertTrue(res.getMuestraTarjetasConCabecera());
    }

    /**
     * Obtener ultimos consumos ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    @Ignore
    public void obtenerUltimosConsumosOkTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSUltimosConsumos());

        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener ultimos consumos business exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerUltimosConsumosBusinessExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(new RetornoTarjetasEntity());

        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener ultimos consumos null pointer exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerUltimosConsumosNullPointerExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenThrow(new NullPointerException());

        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO.obtenerUltimosConsumos(null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Parsear respuesta WS business exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void parsearRespuestaWSBusinessExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSUltimosConsumos());
        Cuenta cuenta = obtenerCuenta("4660570052755555");
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO.obtenerUltimosConsumos(cuenta);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Procesar ultimos movimientos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    @Ignore
    public void procesarUltimosMovimientosTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSUltimosConsumos());
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Procesar ultimos movimientos business exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void procesarUltimosMovimientosBusinessExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        
        RetornoTarjetasEntity retorno = completarInfoRetornoWSUltimosConsumosVacio();
        retorno.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getUltimosMovimientos().getTarjetaList().clear();
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(retorno);
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }
    
    @Test
    @Ignore
    public void procesarUltimosMovimientosSinMovimientosConPago()
            throws BusinessException, DAOException, ParseadorVisaException {

        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSUltimosConsumosVacio());
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Procesar movimiento test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    @Ignore
    public void procesarMovimientoTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSUltimosConsumos());

        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void crearRespuestaErrorDAOExceptionTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenThrow(DAOException.class);
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO.obtenerUltimosConsumos(new Cuenta());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta de WS tarjeta entity null test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    @Ignore
    public void obtenerRespuestaSinConsumosTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(completarInfoRetornoWSSinConsumos());
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosTarjetaBO.obtenerUltimosConsumos(new Cuenta());
        Assert.assertEquals("ERROR_SIN_CONSUMOS", res.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener cuenta.
     *
     * @param numeroTarjetaCredito
     *            the numero tarjeta credito
     * @return the cuenta
     */
    private Cuenta obtenerCuenta(String numeroTarjetaCredito) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroTarjetaCredito(numeroTarjetaCredito);
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setTipoCuenta("7");
        return cuenta;
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    public RetornoTarjetasEntity completarInfoRetornoWSUltimosConsumos() {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        TarjetasEntity tarjetas = new TarjetasEntity();
        List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
        tarjetaEntityList.add(completarTarjetaEntity("0", "4660570052763245", completarInfoMovimientos()));
        tarjetaEntityList.add(completarTarjetaEntity("1", "4050710082523460", completarInfoMovimientos()));
        tarjetaEntityList.add(completarTarjetaEntity("1", "4660570052763237", completarInfoMovimientos()));
        tarjetaEntityList.add(completarInfoTarjetaEntityError());
        tarjetas.setTarjetaList(tarjetaEntityList);
        retornoWS.setTarjetas(tarjetas);
        return retornoWS;
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    public RetornoTarjetasEntity completarInfoRetornoWSUltimosConsumosVacio() {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        TarjetasEntity tarjetas = new TarjetasEntity();
        List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
        tarjetaEntityList.add(completarTarjetaEntity("0", "4660570052763245", completarInfoMovimientosVacio()));
        tarjetaEntityList.add(completarInfoTarjetaEntityError());
        tarjetas.setTarjetaList(tarjetaEntityList);
        retornoWS.setTarjetas(tarjetas);
        return retornoWS;
    }

    /**
     * Completar tarjeta entity.
     *
     * @param categoria
     *            the categoria
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @param ultimosConsumosEntity
     *            the ultimos consumos entity
     * @return the tarjeta entity
     */
    public TarjetaEntity completarTarjetaEntity(String categoria, String codigoTarjeta,
            UltimosConsumosEntity ultimosConsumosEntity) {
        TarjetaEntity tarjetaEntity = completarTarjetaEntity(ultimosConsumosEntity);
        tarjetaEntity.getTarjetaDocument().getDatos().setCategoria(categoria);
        tarjetaEntity.getTarjetaDocument().getDatos().setTarjetaActiva(codigoTarjeta);
        return tarjetaEntity;
    }

    /**
     * Completa la informacion de cada tarjeta entity.
     *
     * @param ultimosMovimientos
     *            the ultimos movimientos
     * @return the tarjeta entity
     */
    public TarjetaEntity completarTarjetaEntity(UltimosConsumosEntity ultimosMovimientos) {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        TarjetaDocumentEntity tarjetaDocumentEntity = new TarjetaDocumentEntity();
        tarjetaDocumentEntity.setDatos(DatosMock.completarInfoDatos());
        tarjetaDocumentEntity.setUltimosMovimientos(ultimosMovimientos);
        tarjetaEntity.setTarjetaDocument(tarjetaDocumentEntity);
        return tarjetaEntity;
    }

    /**
     * Completar info retorno WS sin consumos.
     *
     * @return the retorno tarjetas entity
     */
    public RetornoTarjetasEntity completarInfoRetornoWSSinConsumos() {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        TarjetasEntity tarjetas = new TarjetasEntity();
        List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
        TarjetaEntity tarjetaEntity = completarInfoTarjetaEntityError();
        tarjetaEntityList.add(tarjetaEntity);

        tarjetaEntity = completarInfoTarjetaEntityError();
        tarjetaEntityList.add(tarjetaEntity);

        tarjetaEntity = completarInfoTarjetaEntityError();
        tarjetaEntityList.add(tarjetaEntity);

        tarjetas.setTarjetaList(tarjetaEntityList);

        retornoWS.setTarjetas(tarjetas);
        return retornoWS;
    }

    /**
     * Completa la informacion de cada tarjeta entity.
     *
     * @return the tarjeta entity
     */
    public TarjetaEntity completarInfoTarjetaEntityError() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        ErrorTarjetasEntity error = new ErrorTarjetasEntity();
        error.setCodigo("112107");
        tarjetaEntity.setError(error);
        return tarjetaEntity;
    }

    /**
     * Completar info movimientos.
     *
     * @return the ultimos consumos entity
     */
    private UltimosConsumosEntity completarInfoMovimientos() {
        UltimosConsumosEntity ultimosMovimientos = new UltimosConsumosEntity();
        List<TarjetaUltimosConsumosEntity> tarjetaList = new ArrayList<TarjetaUltimosConsumosEntity>();
        tarjetaList.add(completarTarjetaUltimosConsumosEntity(""));
        tarjetaList.add(completarTarjetaUltimosConsumosEntity("4050710082523452"));
        tarjetaList.add(completarTarjetaUltimosConsumosEntity("4050710082523460"));
        tarjetaList.add(completarTarjetaUltimosConsumosEntity("4660570052763237"));
        tarjetaList.add(completarTarjetaUltimosConsumosEntity("4660570052763245"));
        ultimosMovimientos.setTarjetaList(tarjetaList);
        return ultimosMovimientos;
    }

    /**
     * Completar info movimientos.
     *
     * @return the ultimos consumos entity
     */
    private UltimosConsumosEntity completarInfoMovimientosVacio() {
        UltimosConsumosEntity ultimosMovimientos = new UltimosConsumosEntity();
        List<TarjetaUltimosConsumosEntity> tarjetaList = new ArrayList<TarjetaUltimosConsumosEntity>();
        tarjetaList.add(completarTarjetaUltimosConsumosEntity(""));
        ultimosMovimientos.setTarjetaList(tarjetaList);
        return ultimosMovimientos;
    }

    /**
     * Completar info tarjeta ultimos consumos entity.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return the tarjeta ultimos consumos entity
     */
    private TarjetaUltimosConsumosEntity completarTarjetaUltimosConsumosEntity(String codigoTarjeta) {
        TarjetaUltimosConsumosEntity entidad = new TarjetaUltimosConsumosEntity();
        entidad.setCodigoTarjeta(codigoTarjeta);
        List<MovimientoEntity> movimientosList = new ArrayList<MovimientoEntity>();
        movimientosList.add(completarMovimientoEntity(""));
        movimientosList.add(completarMovimientoEntity("4050710082523452"));
        movimientosList.add(completarMovimientoEntity("4050710082523460"));
        movimientosList.add(completarMovimientoEntity("4660570052763237"));
        movimientosList.add(completarMovimientoEntity("4660570052763245"));
        movimientosList.add(completarMovimientoEntityDolares("4660570052763245"));
        entidad.setDolares("9.99");
        entidad.setPesos("119.85");
        entidad.setMovimientosList(movimientosList);
        return entidad;
    }

    /**
     * Completar movimiento entity.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return the movimiento entity
     */
    private MovimientoEntity completarMovimientoEntity(String codigoTarjeta) {

        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodigo(codigoTarjeta);
        establecimiento.setDescripcion("WANAMA/COOK                      01/06");
        ImporteEntity importe = new ImporteEntity();
        importe.setCodigoTarjeta("");
        importe.setValor("119,85");

        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setEstablecimiento(establecimiento);
        movimientoEntity.setFecha("13/04/2017");
        movimientoEntity.setTicket("0001722   ");
        movimientoEntity.setImporte(importe);
        movimientoEntity.setTipoMoneda("pesos");
        return movimientoEntity;
    }

    /**
     * Completar movimiento entity dolares.
     *
     * @param codigoTarjeta
     *            the codigo tarjeta
     * @return the movimiento entity
     */
    private MovimientoEntity completarMovimientoEntityDolares(String codigoTarjeta) {

        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodigo(codigoTarjeta);
        establecimiento.setDescripcion("NETFLIX.COM");
        ImporteEntity importe = new ImporteEntity();
        importe.setCodigoTarjeta("");
        importe.setValor("9,99");

        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setEstablecimiento(establecimiento);
        movimientoEntity.setFecha("13/04/2017");
        movimientoEntity.setTicket("0001722   ");
        movimientoEntity.setImporte(importe);
        movimientoEntity.setTipoMoneda("dolares");
        return movimientoEntity;
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    public List<ConsumoTarjetaDTO> completarListConsumoTarjetaDTO() {
        List<ConsumoTarjetaDTO> ultimosConsumos = new ArrayList<ConsumoTarjetaDTO>();
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("VISA", "XXXX-3243", null, "19851.12", "9.99", true, false, false,
                false, false, 1, "4660570052763243"));
        return ultimosConsumos;
    }

    /**
     * Obtener consumo tarjeta DTO.
     *
     * @param marca
     *            the marca
     * @param numero
     *            the numero
     * @param nombreAdicional
     *            the nombre adicional
     * @param consumoPesos
     *            the consumo pesos
     * @param consumoDolares
     *            the consumo dolares
     * @param isTitular
     *            the is titular
     * @param isAdicional
     *            the is adicional
     * @param hasConsumoPesosCero
     *            the has consumo pesos cero
     * @param hasConsumoDolaresCero
     *            the has consumo dolares cero
     * @param hasError
     *            the has error
     * @param prioridad
     *            the prioridad
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the consumo tarjeta DTO
     */
    private ConsumoTarjetaDTO obtenerConsumoTarjetaDTO(String marca, String numero, String nombreAdicional,
            String consumoPesos, String consumoDolares, boolean isTitular, boolean isAdicional,
            boolean hasConsumoPesosCero, boolean hasConsumoDolaresCero, boolean hasError, Integer prioridad,
            String nroTarjeta) {
        ConsumoTarjetaDTO consumoTarjetaDTO = new ConsumoTarjetaDTO();
        consumoTarjetaDTO.setConsumoDolares(new BigDecimal(consumoDolares));
        consumoTarjetaDTO.setConsumoPesos(new BigDecimal(consumoPesos));
        consumoTarjetaDTO.setFechaDesde(null);
        consumoTarjetaDTO.setHasConsumoDolaresCero(hasConsumoDolaresCero);
        consumoTarjetaDTO.setHasConsumoPesosCero(hasConsumoDolaresCero);
        consumoTarjetaDTO.setHasError(hasError);
        consumoTarjetaDTO.setIsAdicional(isAdicional);
        consumoTarjetaDTO.setIsTitular(isTitular);
        consumoTarjetaDTO.setLineas(obtenerLineas(nroTarjeta));
        consumoTarjetaDTO.setMarca(marca);
        consumoTarjetaDTO.setNombreAdicional(nombreAdicional);
        consumoTarjetaDTO.setNumero(numero);
        consumoTarjetaDTO.setPrioridad(prioridad);
        return consumoTarjetaDTO;
    }

    /**
     * Obtener lineas.
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineas(String nroTarjeta) {
        LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
        linea.setCodigoEstablecimiento("0025705856");
        linea.setConsumoPesos(true);
        linea.setConsumoDolares(false);
        linea.setCuota("01/06");
        linea.setCuotasCanceladas(1);
        linea.setCuotasTotales(6);
        linea.setDescripcion("Wanama/cook");
        linea.setFecha(new Date());
        linea.setImporteDolares(new BigDecimal(0));
        linea.setImportePesos(new BigDecimal(119.85));
        linea.setNroComprobante("4660570052763245");
        linea.setNroReferencia("5878767   ");
        linea.setNroTarjeta(nroTarjeta);
        linea.setTieneCuota(true);
        linea.setTipoConsumo(TipoConsumoTarjeta.ULTIMO_CONSUMO);
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        lineas.add(linea);
        return lineas;
    }

    /**
     * Obtener reporte test.
     */
    @Test
    public void obtenerReporteTest() {
        Respuesta<Reporte> toRet = new Respuesta<Reporte>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(reporteDAO.obtenerReporte(Matchers.any(Object.class), Matchers.anyString(),
                Matchers.any(Cliente.class), Matchers.any(Boolean.class))).thenReturn(toRet);
        Respuesta<Reporte> res = ultimosConsumosTarjetaBO.obtenerReporte(null, null, null);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

}
