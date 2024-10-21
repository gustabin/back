package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.math.BigDecimal;
import java.util.ArrayList;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.mappers.CreditCardsLimitsObpMapper;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.CreditCardLimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.SelectorYCabeceraBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ManejoDeMensajesTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSPosicionConsolidadaImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSResumenCuentaImpl;

/**
 * The Class SelectorYCabeceraBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectorYCabeceraBOTest {

    /** The selector Y cabecera BO. */
    @InjectMocks
    private SelectorYCabeceraBO selectorYCabeceraBO = new SelectorYCabeceraBOImpl();

    /** The tarjeta DAO. */
    @Mock
    private TarjetaDAO tarjetaDAO;

    /** The cliente DAO. */
    @Mock
    private ClienteDAO clienteDAO;

    /** The mensajes tarjeta. */
    @Mock
    private ManejoDeMensajesTarjeta mensajesTarjeta;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The parseador. */
    @Mock
    private ParseadorWSResumenCuentaImpl parseador;

    /** The parseador consolidada. */
    @Mock
    private ParseadorWSPosicionConsolidadaImpl parseadorConsolidada;

    /** The limite y disponibles BO. */
    @Mock
    private LimitesYDisponiblesBO limitesYDisponiblesBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    @Mock
    private CreditCardsServiceConnector creditCardsServiceConnector;
    @Mock
    private CreditCardsLimitsObpMapper creditCardsLimitsObpMapper;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Obtener tool tip favorito test.
     */
    @Test
    public void obtenerToolTipFavoritoTest() {
        Respuesta<Cliente> toReturn = new Respuesta<Cliente>();
        toReturn.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(mensajesTarjeta.obtenerTooltipFavorito()).thenReturn("resultado esperado");
        String res = selectorYCabeceraBO.obtenerTooltipFavorito();
        Assert.assertEquals(res, "resultado esperado");
    }

    /**
     * Obtener tool tip no favorito test.
     */
    @Test
    public void obtenerToolTipNoFavoritoTest() {
        Respuesta<Cliente> toReturn = new Respuesta<Cliente>();
        toReturn.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(mensajesTarjeta.obtenerTooltipNoFavorito()).thenReturn("resultado esperado");
        String res = selectorYCabeceraBO.obtenerTooltipNoFavorito();
        Assert.assertEquals(res, "resultado esperado");
    }

    /**
     * Obtener tarjetas con productos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @Test
    public void obtenerTarjetasConProductosTest() throws BusinessException, ParseadorVisaException, ConnectorException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Respuesta<DisponiblesYConsumoDTO> toRet = new Respuesta<DisponiblesYConsumoDTO>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        Long number = 7L;
        cuenta.setTipoCuenta(String.valueOf(number));
        cuenta.setClaseCuenta("T");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setCodigoTitularidad("TI");
        cuenta.setNroTarjetaCredito("123456789101112");
        cliente.getCuentas().add(cuenta);

        LimitesYDisponiblesDTO disponiblesDTO = new LimitesYDisponiblesDTO();
        Mockito.when(creditCardsLimitsObpMapper.mapToRetornoTarjetasEntity(Matchers.any(CreditCardLimitsAndTotalsDto.class)))
                .thenReturn(new RetornoTarjetasEntity());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(limitesYDisponiblesBO.obtenerLimiteYDisponibleDTO(Matchers.any(Cuenta.class),
                Matchers.any(RetornoTarjetasEntity.class))).thenReturn(disponiblesDTO);
        Mockito.when(parseador.obtenerPorNumeroDeTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenReturn(new TarjetaEntity());
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(DisponiblesYConsumoDTO.class),
                Matchers.any(DisponiblesYConsumoDTO.class))).thenReturn(toRet);
        Mockito.when(parseadorConsolidada.obtenerConsumosPesos(Matchers.any(PosicionConsolidadaEntity.class))).thenReturn(new BigDecimal(1000));
        Mockito.when(parseadorConsolidada.obtenerConsumosDolares(Matchers.any(PosicionConsolidadaEntity.class))).thenReturn(new BigDecimal(1000));

        Respuesta<DisponiblesYConsumoDTO> res = selectorYCabeceraBO.obtenerTarjetas(cliente);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener tarjetas sin productos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerTarjetasSinProductosTest() throws BusinessException, ParseadorVisaException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Respuesta<Object> toRet = new Respuesta<Object>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        Long number = 42L;
        cuenta.setTipoCuenta(String.valueOf(number));
        cuenta.setClaseCuenta("TI");
        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setNroTarjetaCredito("123456789101112");
        cliente.getCuentas().add(cuenta);

        Mockito.when(parseador.obtenerPorNumeroDeTarjetaActiva(Matchers.any(RetornoTarjetasEntity.class),
                Matchers.anyString())).thenReturn(new TarjetaEntity());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(toRet);

        Respuesta<DisponiblesYConsumoDTO> res = selectorYCabeceraBO.obtenerTarjetas(cliente);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

}