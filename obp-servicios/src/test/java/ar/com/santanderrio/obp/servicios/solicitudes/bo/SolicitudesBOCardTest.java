package ar.com.santanderrio.obp.servicios.solicitudes.bo;

import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.adhesionwomen.manager.AdhesionWomenManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.solicitudes.bo.impl.SolicitudesBOImpl;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.impl.CreditCardsServiceConnectorImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetasWomenMock;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SolicitudesBOCardTest {

    @InjectMocks
    private SolicitudesBOImpl solicitudesBO;

    @Mock
    private Sign sign;


    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private InversionWSHelper inversionWSHelper;

    @Mock
    private AdhesionWomenManager adhesionWomenManager;

    @Mock
    private AdministradorPermisos administradorPermisos;

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO;

    @Mock
    private CreditCardsServiceConnectorImpl creditCardApiService;

    @Before
    public void init() {
        solicitudesBO.setAppEncoding("UTF-8");
        ReflectionTestUtils.setField(solicitudesBO, "habilitacionWomen", "07|42");
    }

    @Test
    public void obtenerTarjetas_visaPlatinumConAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisa();
        mockAdhesionWomen(cliente, new ArrayList<DatosTarjetaWomen>());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_visaPlatinumSinAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisa();
        mockAdhesionWomen(cliente, DatosTarjetasWomenMock.completarInfoDatosTarjetasWomenAmex());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_visaCorporateRecargableSinAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaCorporateRecargable();
        mockAdhesionWomen(cliente, new ArrayList<DatosTarjetaWomen>());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_amexPlatinumSinAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteAmex();
        mockAdhesionWomen(cliente, new ArrayList<DatosTarjetaWomen>());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_amexPlatinumConAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteAmex();
        mockAdhesionWomen(cliente, DatosTarjetasWomenMock.completarInfoDatosTarjetasWomenAmex());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_visaCorporateRecargableConAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaCorporateRecargable();
        mockAdhesionWomen(cliente, DatosTarjetasWomenMock.completarInfoDatosTarjetasWomenAmex());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_visaOroConAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaOro();
        mockAdhesionWomen(cliente, DatosTarjetasWomenMock.completarInfoDatosTarjetasWomenAmex());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }

    @Test
    public void obtenerTarjetas_visaOroSinAdhesionTarjetas() throws IllegalAccessException {

        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaOro();
        mockAdhesionWomen(cliente, new ArrayList<DatosTarjetaWomen>());

        //Then
        SolicitudesDTO respuesta = solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas());

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.getGestiones().contains("adhesionWomen"));
    }


    private void mockAdhesionWomen(Cliente cliente, List<DatosTarjetaWomen> datosTarjetaWomenList) throws IllegalAccessException {
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);

        AdhesionWomenView adhesionWomenView = new AdhesionWomenView();
        adhesionWomenView.setErrorServicio(null);
        adhesionWomenView.setListaTarjetasAdheridas(datosTarjetaWomenList);

        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(inversionWSHelper.enriFlag()).thenReturn(true);
        when(adhesionWomenManager.recuperarTarjetasAdhesionWomen()).thenReturn(adhesionWomenView);

        FieldUtils.writeField(solicitudesBO, "valorTrjCoord", "3",true);
    }
    
}
