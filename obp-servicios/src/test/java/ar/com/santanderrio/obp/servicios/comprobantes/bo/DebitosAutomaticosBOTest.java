package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Before;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.DebitosAutomaticosBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ParseadorWSInformeDebitosAutomaticosImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesVisaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.DebitosTarjetaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.InformeDebitosAutomaticosEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class CoordinadorComprobantesBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DebitosAutomaticosBOTest {
    /** The alta destinatario manager. */
    @InjectMocks
    private DebitosAutomaticosBO debitosAutomaticosBO = new DebitosAutomaticosBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The parseador. */
    @InjectMocks
    @Spy
    private ParseadorWSInformeDebitosAutomaticosImpl parseador = new ParseadorWSInformeDebitosAutomaticosImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The transferencia inmediata BO. */
    @Mock
    private ScompBO transferenciaInmediataBO;

    /** The recarga tarjeta BO. */
    @Mock
    private RecargaTarjetaBO recargaTarjetaBO;

    /** The comprobantes 7 x 24 BO. */
    @Mock
    private ComprobantesSietePorVenticuatroBO comprobantes7x24BO;

    /** The future comprobantes 1. */
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobantes1;

    /** The future comprobantes 2. */
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobantes2;

    /** The future comprobantes 3. */
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobantes3;

    /** The comprobantes visa DAO. */
    @Mock
    private ComprobantesVisaDAO comprobantesVisaDAO;

    /** The medios pago BO. */
    @Mock
    private MediosPagoBO mediosPagoBO ;

    /**
     * Iniciar mocks.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Before
    public void iniciarMocks() throws IllegalAccessException, BusinessException, DAOException {

        Respuesta<ComprobantesDTO> respuestaDTOScomp = crearRespuesta(ComprobantesDTO.class, obtenerMocks(),
                EstadoRespuesta.OK, false, new ArrayList<ItemMensajeRespuesta>());

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(obtenerMensaje("¡Utiliza tu agenda!"));

        when(comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class), Matchers.any(Cliente.class))).thenReturn(obtenerRetornoTarjetasEntity());

        Mockito.when(mediosPagoBO.obtenerPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());

    }

    /**
     * Obtener medio pago.
     *
     * @return the medio pago
     */
    private MedioPago obtenerMedioPago() {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombreFantasia("DIREC TV ");
        return medioPago;
    }

    /**
     * Obtener respuesta comprobantes.
     *
     * @throws InterruptedException
     *             the interrupted exception
     * @throws ExecutionException
     *             the execution exception
     * @throws DAOException 
     * @throws ParseadorVisaException 
     */
    @Test
    public void obtenerRespuestaComprobantes() throws InterruptedException, ExecutionException, DAOException, ParseadorVisaException {
        TransaccionDTO transferenciaDTO = new TransaccionDTO();
        transferenciaDTO.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        transferenciaDTO.setFechaHasta(new Date());
        transferenciaDTO.setFechaDesde(new Date());
        
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        
        tarjetaEntity.setTarjetaDocument(new TarjetaDocumentEntity());
        
        Mockito.when(comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(Matchers.any(Cuenta.class),
                    Matchers.any(OperacionTarjetaWSEnum.class), Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(obtenerRetornoTarjetasEntity() );
        Respuesta<ComprobantesDTO> respuestaComprobantes = debitosAutomaticosBO
                .obtenerComprobantes(obtenerClienteConProductos(),transferenciaDTO);
        Assert.assertNotNull(respuestaComprobantes.getRespuesta());

    }
    
    @Test
    public void obtenerRespuestaComprobantesTest() throws InterruptedException, ExecutionException, DAOException, ParseadorVisaException {
        TransaccionDTO transferenciaDTO = new TransaccionDTO();
        transferenciaDTO.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        transferenciaDTO.setFechaHasta(new Date());
        transferenciaDTO.setFechaDesde(new Date());
        
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        
        tarjetaEntity.setTarjetaDocument(new TarjetaDocumentEntity());
        
        RetornoTarjetasEntity  retorno = obtenerRetornoTarjetasEntity();
        retorno.setError(true);
        retorno.setErrorTarjetas(new ErrorTarjetasEntity());
        retorno.getErrorTarjetas().setCodigo("112501");
        
        Mockito.when(comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(Matchers.any(Cuenta.class),
                    Matchers.any(OperacionTarjetaWSEnum.class), Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(retorno);
        Respuesta<ComprobantesDTO> respuestaComprobantes = debitosAutomaticosBO
                .obtenerComprobantes(obtenerClienteConProductos(),transferenciaDTO);
        Assert.assertNotNull(respuestaComprobantes.getRespuesta());

    }

    /**
     * Obtener mocks.
     *
     * @return the comprobantes DTO
     */
    private ComprobantesDTO obtenerMocks() {
        LinkedList<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
        for (int i = 1; i < 29; i++) {
            comprobantes.add(obtenerComprobanteDTO(i, TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA));
        }
        return new ComprobantesDTO(comprobantes);
    }

    /**
     * Obtener comprobante DTO.
     *
     * @param dia
     *            the dia
     * @param op
     *            the op
     * @return the comprobante DTO
     */
    private ComprobanteDTO obtenerComprobanteDTO(int dia, TipoOperacionComprobanteEnum op) {
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 4, dia).getTime());
        comprobanteDTO.setTipoOperacion(op);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Credito ciudad Mock");
        comprobanteDTO.setImporteDolares(null);
        comprobanteDTO.setImportePesos(new BigDecimal(1200));
        return comprobanteDTO;
    }

    /**
     * Crear respuesta.
     *
     * @param <T>
     *            the generic type
     * @param respuestaClass
     *            the respuesta class
     * @param data
     *            the data
     * @param estado
     *            the estado
     * @param vacia
     *            the vacia
     * @param itemMensajes
     *            the item mensajes
     * @return the respuesta
     */
    public <T> Respuesta<T> crearRespuesta(Class<T> respuestaClass, T data, EstadoRespuesta estado, Boolean vacia,
            List<ItemMensajeRespuesta> itemMensajes) {
        Respuesta<T> rta = new Respuesta<T>();
        rta.setRespuesta(data);
        rta.setEstadoRespuesta(estado);
        rta.setRespuestaVacia(vacia);
        rta.setItemMensajeRespuesta(itemMensajes);
        return rta;
    };

    /**
     * Obtener mensaje cabecera.
     *
     * @param mensaje
     *            the mensaje
     * @return the mensaje
     */
    private Mensaje obtenerMensaje(String mensaje) {
        Mensaje msj = new Mensaje();
        msj.setCodigo("1390");
        msj.setMensaje(mensaje);
        return msj;
    }

    /**
     * Obtener cliente con productos.
     *
     * @return the cliente
     */
    private Cliente obtenerClienteConProductos() {
        Cliente cliente = new Cliente();

        cliente.setNup("00276937");
        cliente.setCuentas(new ArrayList<Cuenta>());
        Respuesta<DisponiblesYConsumoDTO> toRet = new Respuesta<DisponiblesYConsumoDTO>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        cliente.getCuentas().add(obtenerCuenta());
        return cliente;
    }

    /**
     * Obtener cuenta.
     *
     * @return the cuenta
     */
    private Cuenta obtenerCuenta() {
        return CuentaMock.completarInfoCuenta();
    }

    /**
     * Obtener retorno tarjetas entity.
     *
     * @return the retorno tarjetas entity
     */
    private RetornoTarjetasEntity obtenerRetornoTarjetasEntity() {
        RetornoTarjetasEntity retornoTarjetasEntity = new RetornoTarjetasEntity();
        TarjetasEntity tarjetas = new TarjetasEntity();
        List<TarjetaEntity> tarjetaList = new ArrayList<TarjetaEntity>();
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        TarjetaDocumentEntity tarjetaDocument = new TarjetaDocumentEntity();
        DatosEntity datos = new DatosEntity();
        datos.setTarjetaActiva("3777910057203120");
        tarjetaDocument.setDatos(datos);
        InformeDebitosAutomaticosEntity informeDebitosAutomaticos = new InformeDebitosAutomaticosEntity();
        DebitosTarjetaEntity debitoTarjetas = new DebitosTarjetaEntity();
        debitoTarjetas.setNombreTarjeta(" tarjeta TEST");
        List<DebitoEntity> listDebitos = new ArrayList<DebitoEntity>();
        DebitoEntity debitoEntity = new DebitoEntity();
        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodEstablecimiento("0009734005");
        debitoEntity.setEstablecimiento(establecimiento);
        debitoEntity.setFecha("31-03-2017");
        debitoEntity.setDescripcion("descripcion TEST");
        ImporteEntity importe = new ImporteEntity();
        importe.setPesos("1.375,00");
        importe.setDolares("0,00");
        debitoEntity.setImporte(importe);

        listDebitos.add(debitoEntity);
        debitoTarjetas.setListDebitos(listDebitos);
        informeDebitosAutomaticos.setDebitosTarjeta(Arrays.asList(debitoTarjetas));
        tarjetaDocument.setInformeDebitosAutomaticos(informeDebitosAutomaticos);
        tarjetaEntity.setTarjetaDocument(tarjetaDocument);
        tarjetaList.add(tarjetaEntity);
        tarjetas.setTarjetaList(tarjetaList);
        retornoTarjetasEntity.setTarjetas(tarjetas);
        return retornoTarjetasEntity;
    }

}
