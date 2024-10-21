package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.TarjetasHomeBO;
import ar.com.santanderrio.obp.servicios.home.entitites.ListaTarjetasDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.TarjetaHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class TarjetaHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TarjetaHomeManagerTest {

    /** The tarjeta home manager. */
    @InjectMocks
    private TarjetaHomeManagerImpl tarjetaHomeManager = new TarjetaHomeManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The tarjetas home BO. */
    @Mock
    private TarjetasHomeBO tarjetasHomeBO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO = new MensajeDAOImpl();

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();
    
    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Obtener cajas cuentas test.
     */
    @Test
    public void obtenerCajasCuentasTest() {

        Cliente cliente = new Cliente();
        Respuesta<ListaTarjetasDTO> respuestaTarjetaHomeDTO = new Respuesta<ListaTarjetasDTO>();

        ListaTarjetasDTO listaTarjetasDTO = new ListaTarjetasDTO();
        List<TarjetaHomeDTO> tarjetas = new ArrayList<TarjetaHomeDTO>();
        tarjetas.add(getMockTarjeta("VISA"));
        tarjetas.add(getMockTarjeta("AMERICAN EXPRESS"));
        listaTarjetasDTO.setTarjetas(tarjetas);

        respuestaTarjetaHomeDTO.setRespuesta(listaTarjetasDTO);
        respuestaTarjetaHomeDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(tarjetasHomeBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(respuestaTarjetaHomeDTO);

        when(sesionCliente.getCliente()).thenReturn(cliente);

        GrupoCajaHomeView grupoCajaHomeView = tarjetaHomeManager.obtenerGrupoElementos();

        assertNotNull(grupoCajaHomeView);

        List<Caja> cajas = grupoCajaHomeView.getCajas();

        assertTrue(cajas.size() > 0);

        CajaHomeTarjetaView caja = (CajaHomeTarjetaView) cajas.get(0);
        assertEquals(caja.getEncabezado(), "Visa");
        assertEquals(caja.getIsTarjeta(), true);
        assertEquals(caja.getTitulo(), "Alias Tarjeta");
        CajaHomeTarjetaView caja2 = (CajaHomeTarjetaView) cajas.get(1);
        assertEquals(caja2.getEncabezado(), "American Express");

    }

    /**
     * Aplica grupo test.
     */
    @Test
    public void aplicaGrupoTest() {

        List<ItemMensajeRespuesta> listItemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_ACTUALIZAR_ALIAS.getDescripcion());
        listItemsRespuesta.add(item);

        when(sesionCliente.getItemsRespuesta()).thenReturn(listItemsRespuesta);

        Respuesta<Boolean> respuesta = tarjetaHomeManager.aplicaGrupo();
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta(), true);
    }

    /**
     * Aplica grupo error test.
     */
    @Test
    public void aplicaGrupoErrorTest() {

        List<ItemMensajeRespuesta> listItemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_TARJETA_MASTERCARD_VISA_BANELCO.getDescripcion());
        listItemsRespuesta.add(item);

        when(sesionCliente.getItemsRespuesta()).thenReturn(listItemsRespuesta);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<Boolean> respuesta = tarjetaHomeManager.aplicaGrupo();
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_TABLERO_HOME.getDescripcion());

    }

    /**
     * genera el mock de tarjeta.
     *
     * @param marca
     *            the marca
     * @return the mock tarjeta
     */
    private TarjetaHomeDTO getMockTarjeta(String marca) {
        TarjetaHomeDTO tarjetaHomeDTO = new TarjetaHomeDTO();
        tarjetaHomeDTO.setAlias("Alias Tarjeta");
        tarjetaHomeDTO.setHasAlias(true);
        tarjetaHomeDTO.setMarca(marca);
        tarjetaHomeDTO.setNumero("1111222233334444");
        tarjetaHomeDTO.setSaldoPesos(new BigDecimal("1100"));
        return tarjetaHomeDTO;
    }

    @Test
    public void obtenerSaldoTarjetaTest() {
        CajaHomeTarjetaView cajaTarjetasHomeView = new CajaHomeTarjetaView();
        cajaTarjetasHomeView.setId("1");
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setIndex(1);
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        cuentaList.add(cuenta);
        cliente.setCuentas(cuentaList);
        Respuesta<TarjetaHomeDTO> respuestaTarjetaHomeDTO = new Respuesta<TarjetaHomeDTO>();
        TarjetaHomeDTO tarjetaHomeDTO = new TarjetaHomeDTO();

        respuestaTarjetaHomeDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaTarjetaHomeDTO.setRespuesta(tarjetaHomeDTO);

        when(tarjetasHomeBO.obtenerSaldoTarjeta(Matchers.any(Cuenta.class))).thenReturn(respuestaTarjetaHomeDTO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
  
        Respuesta<CajaHomeTarjetaView> respuesta = tarjetaHomeManager.obtenerSaldoTarjeta(cajaTarjetasHomeView);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

}
