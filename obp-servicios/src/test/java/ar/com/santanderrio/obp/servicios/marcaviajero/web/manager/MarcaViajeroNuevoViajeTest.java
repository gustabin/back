package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse.Paises;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Pais;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Destino;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.NuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.RangoInhabilitacion;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaAsociada;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaBlackNacional;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaNuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeView;

/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaViajeroNuevoViajeTest {

    /** The cuenta manager. */
    @InjectMocks
    private MarcaViajeroManagerImpl marcaViajeroManager = new MarcaViajeroManagerImpl();

    /** The marca viajero BO. */
    @Mock
    private MarcaViajeroBO marcaViajeroBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The paises. */
    @Mock
    private Paises paises;

    /**
     * Nuevo viaje OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void pruebaNuevoViajeOKTest() throws BusinessException, DAOException {//TODO renombrar este método
        mockCliente();
        mockearRespuestaNuevoViaje(EstadoRespuesta.OK);
        mockearCredencialesMya();
        mockearDestinos();

        Respuesta<NuevoViajeView> respuestaNuevoViajeView = marcaViajeroManager
                .nuevoViaje();
        NuevoViajeView nuevoViajeView = respuestaNuevoViajeView.getRespuesta();
        assertNotNull(respuestaNuevoViajeView);
        assertEquals(EstadoRespuesta.OK,
                respuestaNuevoViajeView.getEstadoRespuesta());
        // assertEquals("04/07/2018", nuevoViajeView.getFechaMaxInicioViaje());
        assertEquals(new Integer(180), nuevoViajeView.getDuracionMaximaViaje());
        assertEquals(4, nuevoViajeView.getDestinos().size());
        assertEquals(3, nuevoViajeView.getTarjetas().get(0).getTarjetasAsociadas().size());
    }
    
    
    /**
     * Mockear destinos.
     */
    private void mockearDestinos() {
        Pais pais = new Pais();
        List<Pais> listaPaises = new ArrayList<Pais>();

        pais.setCodPais("ALE");
        pais.setDescripcionPais("ALEMANIA");
        listaPaises.add(pais);

        pais.setCodPais("BOL");
        pais.setDescripcionPais("BOLIVIA");
        listaPaises.add(pais);

        pais.setCodPais("ECU");
        pais.setDescripcionPais("ECUADOR");
        listaPaises.add(pais);

        pais.setCodPais("JAP");
        pais.setDescripcionPais("JAPON");
        listaPaises.add(pais);

        when(paises.getPais()).thenReturn(listaPaises);
    }

    /**
     * Nuevo viaje error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void nuevoViajeErrorTest() throws BusinessException, DAOException {
        mockearRespuestaNuevoViaje(EstadoRespuesta.ERROR);

        Respuesta<NuevoViajeView> respuestaNuevoViajeView = marcaViajeroManager
                .nuevoViaje();
        assertNotNull(respuestaNuevoViajeView);
        assertEquals(EstadoRespuesta.ERROR,
                respuestaNuevoViajeView.getEstadoRespuesta());
    }

    /**
     * Mockear respuesta nuevo viaje.
     *
     * @param estadoRespuesta
     *            the estado respuesta
     */
    private void mockearRespuestaNuevoViaje(EstadoRespuesta estadoRespuesta) {
        Respuesta<NuevoViaje> respuestaNuevoViaje = new Respuesta<NuevoViaje>();
        if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
            NuevoViaje nuevoViaje = new NuevoViaje();

            Date fechaMaxInicioViaje = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaMaxInicioViaje);
            calendar.add(Calendar.DATE, 30);
            fechaMaxInicioViaje = calendar.getTime();
            Integer duracionMaximaViaje = 180;

            nuevoViaje.setFechaMaxInicioViaje(fechaMaxInicioViaje);
            nuevoViaje.setDuracionMaximaViaje(duracionMaximaViaje);
            nuevoViaje.setTarjetaBlackNacional(new TarjetaBlackNacional());

            Destino destino;
            List<Destino> destinosList = new ArrayList<Destino>();

            destino = new Destino();
            destino.setCodigo("ALE");
            destino.setDescripcion("ALEMANIA");
            destinosList.add(destino);

            destino = new Destino();
            destino.setCodigo("BOL");
            destino.setDescripcion("BOLIVIA");
            destinosList.add(destino);

            destino = new Destino();
            destino.setCodigo("ECU");
            destino.setDescripcion("ECUADOR");
            destinosList.add(destino);

            destino = new Destino();
            destino.setCodigo("JAP");
            destino.setDescripcion("JAPON");
            destinosList.add(destino);

            nuevoViaje.setDestinos(destinosList);
            List<TarjetaNuevoViaje> tarjetaNuevoViajeList = new ArrayList<TarjetaNuevoViaje>();
            TarjetaNuevoViaje tarjetaNuevoViaje = new TarjetaNuevoViaje();
            List<TarjetaAsociada> tarjetaAsociadaList = new ArrayList<TarjetaAsociada>();

            //tarjetaAsociada 1
            TarjetaAsociada tarjetaAsociada = new TarjetaAsociada();
            RangoInhabilitacion rangoInhabilitacion = new RangoInhabilitacion(
                    new Date(), new Date());
            List<RangoInhabilitacion> fechaInhabilitadaList = new ArrayList<RangoInhabilitacion>();
            fechaInhabilitadaList.add(rangoInhabilitacion);
            tarjetaAsociada.setFechaInhabilitada(fechaInhabilitadaList);
            tarjetaAsociada.setMarca("AMEX");
            tarjetaAsociada.setNumero("111111111");
            tarjetaAsociadaList.add(tarjetaAsociada);

            //tarjetaAsociada 2
            tarjetaAsociada = new TarjetaAsociada();
            rangoInhabilitacion = new RangoInhabilitacion(
                    new Date(), new Date());
            fechaInhabilitadaList = new ArrayList<RangoInhabilitacion>();
            fechaInhabilitadaList.add(rangoInhabilitacion);
            tarjetaAsociada.setFechaInhabilitada(fechaInhabilitadaList);
            tarjetaAsociada.setMarca("VISA");
            tarjetaAsociada.setNumero("222222222");
            tarjetaAsociadaList.add(tarjetaAsociada);

            //tarjetaAsociada 3
            tarjetaAsociada = new TarjetaAsociada();
            rangoInhabilitacion = new RangoInhabilitacion(
                    new Date(), new Date());
            fechaInhabilitadaList = new ArrayList<RangoInhabilitacion>();
            fechaInhabilitadaList.add(rangoInhabilitacion);
            tarjetaAsociada.setFechaInhabilitada(fechaInhabilitadaList);
            tarjetaAsociada.setMarca("Visa Débito");
            tarjetaAsociada.setNumero("333333333");
            tarjetaAsociadaList.add(tarjetaAsociada);
            
            tarjetaNuevoViaje.setTarjetasAsociadas(tarjetaAsociadaList);
            tarjetaNuevoViajeList.add(tarjetaNuevoViaje);
            nuevoViaje.setTarjetas(tarjetaNuevoViajeList);
            respuestaNuevoViaje.setRespuesta(nuevoViaje);
        }
        respuestaNuevoViaje.setEstadoRespuesta(estadoRespuesta);
        when(marcaViajeroBO.nuevoViaje(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(respuestaNuevoViaje);

    }

    /**
     * Mockear credenciales mya.
     */
    private void mockearCredencialesMya() {
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("juan@prueba.org");
        when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
    }

    /**
     * Mock sesion cliente.
     */
    private void mockCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido1("Caseres");
        cliente.setApellido2("Guiterrez");
        cliente.setDni("1234");
        cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);
    }

}
