package ar.com.santanderrio.obp.servicios.todopago.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.todopago.dao.impl.SolicitudAdhesionTodoPagoDAOImpl;
import ar.com.santanderrio.obp.servicios.todopago.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.todopago.entity.AdhesionTodoPagoInEntity;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;

/**
 * The Class SolicitudAdhesionTodoPagoDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SolicitudAdhesionTodoPagoDAOTest {

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The Solicitud adhesion TodoPago DAO impl. */
    @InjectMocks
    @Spy
    private SolicitudAdhesionTodoPagoDAOImpl solicitudAdhesionTodoPagoDAOImpl = new SolicitudAdhesionTodoPagoDAOImpl();

    /** The base dato DAO. */
    @Mock
    private BaseDatoDAO baseDatoDAO;

    /**
     * Init.
     */
    @Before
    public void init() {

    }

    /**
     * Grabar solicitud error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public void grabarSolicitudErrorTest() throws DAOException, IllegalAccessException, SQLException {

        AdhesionTodoPagoInEntity inEntity = obtenerTodoPagoInEntity();

        Map<String, Object> respuestaBD = new HashMap<String, Object>();
        respuestaBD.put("p_resultado", "1");
        respuestaBD.put("p_err_amigable", "Error en la consulta DB");

        Mockito.doReturn(respuestaBD).when(solicitudAdhesionTodoPagoDAOImpl).consultar(Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString(), Matchers.any(SqlParameterSource.class),
                Matchers.<SqlParameter>anyVararg());
        Respuesta<ResultadoTransaccion> respuesta = solicitudAdhesionTodoPagoDAOImpl.grabarSolicitud(inEntity);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuestaBD.get("p_err_amigable"), respuesta.getRespuesta().getMensajeError());
    }

    /**
     * Grabar solicitud exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws SQLException
     *             the SQL exception
     */
    @Test(expected = DAOException.class)
    public void grabarSolicitudExceptionTest() throws DAOException, IllegalAccessException, SQLException {

        AdhesionTodoPagoInEntity inEntity = obtenerTodoPagoInEntity();

        Mockito.doThrow(SQLException.class).when(solicitudAdhesionTodoPagoDAOImpl).consultar(Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString(), Matchers.any(SqlParameterSource.class),
                Matchers.<SqlParameter>anyVararg());
        solicitudAdhesionTodoPagoDAOImpl.grabarSolicitud(inEntity);
    }

    /**
     * Grabar solicitud OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public void grabarSolicitudOKTest() throws DAOException, IllegalAccessException, SQLException {

        AdhesionTodoPagoInEntity inEntity = obtenerTodoPagoInEntity();

        Map<String, Object> respuestaBD = new HashMap<String, Object>();
        respuestaBD.put("p_resultado", "0");

        Mockito.doReturn(respuestaBD).when(solicitudAdhesionTodoPagoDAOImpl).consultar(Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString(), Matchers.any(SqlParameterSource.class),
                Matchers.<SqlParameter>anyVararg());
        Respuesta<ResultadoTransaccion> respuesta = solicitudAdhesionTodoPagoDAOImpl.grabarSolicitud(inEntity);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener TodoPago in entity.
     *
     * @return the adhesion TodoPago in entity
     */
    private AdhesionTodoPagoInEntity obtenerTodoPagoInEntity() {
        AdhesionTodoPagoInEntity inEntity = new AdhesionTodoPagoInEntity();
        inEntity.setActividad("Actividad");
        inEntity.setApellido("Apellido");
        inEntity.setApellidoContacto("ApellidoC");
        inEntity.setCbu("1234567890123456789012");
        inEntity.setCelular("1234567890");
        inEntity.setCondicionIIBB("Exento");
        inEntity.setCondicionIVA("Exento");
        inEntity.setCuitDni("20123456789");
        inEntity.setEmail("isban@isbanexternos.com.ar");
        inEntity.setEmpresaCelular("Movistar");
        inEntity.setEnvioMailSatisfactorio("S");
        inEntity.setFechaNacimiento(new Date());
        inEntity.setFechaSolicitud(new Date());
        inEntity.setNombre("Nombre");
        inEntity.setNombreContacto("NombreC");
        inEntity.setNumeroCuenta("0");
        inEntity.setNumeroDocumento("12345678");
        inEntity.setNup("12345678");
        inEntity.setRazonSocial("Razon Social");
        inEntity.setSexo("M");
        inEntity.setTelefonoFijo("99999999");
        inEntity.setTipoDocumento("N");
        inEntity.setDomicilioFacturacion(new DomicilioDTO());
        inEntity.setDomicilioLegal(new DomicilioDTO());
        return inEntity;
    }

    /**
     * Generar comprobante OK test.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    @Ignore
    public void generarComprobanteOKTest() throws IllegalAccessException {

        ComprobanteAdhesionTodoPagoView view = obtenerComprobanteTodoPagoView();

        FieldUtils.writeField(solicitudAdhesionTodoPagoDAOImpl, "logoCabecera",
                appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(solicitudAdhesionTodoPagoDAOImpl, "logoCierre",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(solicitudAdhesionTodoPagoDAOImpl, "logoDefault",
                appContext.getResource("classpath:/report/comprobantes/logo_default.png"), true);
        FieldUtils.writeField(solicitudAdhesionTodoPagoDAOImpl, "reportFileAdhesion",
                appContext.getResource("classpath:/report/todoPago/ComprobanteSolicitudAdhesionTodoPago.jasper"), true);
        Reporte reporte = solicitudAdhesionTodoPagoDAOImpl.generarComprobante(view);
        Assert.assertNotNull(reporte);
        Assert.assertNotNull(reporte.getBytes());
    }

    /**
     * Obtener comprobante TodoPago view.
     *
     * @return the comprobante adhesion TodoPago view
     */
    private ComprobanteAdhesionTodoPagoView obtenerComprobanteTodoPagoView() {
        ComprobanteAdhesionTodoPagoView view = new ComprobanteAdhesionTodoPagoView();
        view.setActividad("Actividad");
        view.setApellidoContacto("ApellidoC");
        view.setCbu("1234567890123456789012");
        view.setCelular("1234567890");
        view.setCondicionIIBB("Exento");
        view.setCondicionIVA("Exento");
        view.setCuitDni("20123456789");
        view.setEmail("isban@isbanexternos.com.ar");
        view.setEmpresaCelular("Movistar");
        view.setEnvioMailSatisfactorio("S");
        view.setFechaNacimiento(new Date());
        view.setFechaSolicitud(new Date());
        view.setNombreContacto("NombreC");
        view.setNumeroCuenta("0");
        view.setNumeroDocumento("12345678");
        view.setNup("12345678");
        view.setRazonSocial("Razon Social");
        view.setSexo("M");
        view.setTelefonoFijo("99999999");
        view.setTipoDocumento("N");
        view.setDomicilioLegal(new DomicilioDTO());
        return view;
    }

}
