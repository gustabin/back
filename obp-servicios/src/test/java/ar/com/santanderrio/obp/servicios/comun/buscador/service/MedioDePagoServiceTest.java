package ar.com.santanderrio.obp.servicios.comun.buscador.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.impl.BuscadorEmpresaBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;

/**
 * The Class MedioDePagoServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MedioDePagoServiceTest {

    /** The medio de pago service. */
    @InjectMocks
    private BuscadorEmpresaBOImpl medioDePagoService;

    /** The Moc K medios pago DAO. */
    @Mock
    private BuscadorEmpresaDAO MocKMediosPagoDAO;

    /** The Moc K medios pago BO. */
    @Mock
    private MediosPagoBO MocKMediosPagoBO;

    /** The Moc K mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * Search.
     */
    @Test
    public void search() {

        // When
        String teminoBusqueda = "telefonica";
        Set<MedioPago> pagos = new HashSet<MedioPago>();
        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        MedioPago medioPago2 = new MedioPago();
        medioPago2.setRubroFantasia("Batman");
        medioPago2.setNombreFantasia("Robin");
        // pagos.add(medioPago1);
        // pagos.add(medioPago2);
        Mockito.when(MocKMediosPagoDAO.search(Matchers.anyString())).thenReturn(pagos);
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        respuestaMensaje.setRespuesta(new Mensaje());
        Mensaje mensaje = new Mensaje();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<BuscadorEmpresaRta> rtaPagos = medioDePagoService.search(teminoBusqueda);

        // Expected
        Assert.assertNotNull(rtaPagos);
    }

    /**
     * Search pago automatico.
     */
    @Test
    public void searchPagoAutomatico() {

        // When
        String teminoBusqueda = "termino";
        Set<MedioPago> pagos = new HashSet<MedioPago>();
        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setAddiLeyendaIdentificacion("leyenda");
        medioPago1.setRubroFantasia("Acuaman");
        MedioPago medioPago2 = new MedioPago();
        medioPago2.setAddiLeyendaIdentificacion("leyenda");
        medioPago2.setRubroFantasia("Batman");
        medioPago2.setNombreFantasia("Robin");
        pagos.add(medioPago1);
        pagos.add(medioPago2);
        Mensaje mensaje = new Mensaje();
        Mockito.when(MocKMediosPagoDAO.searchPagoAutomatico(Matchers.anyString())).thenReturn(pagos);
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        respuestaMensaje.setRespuesta(new Mensaje());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<BuscadorEmpresaRta> rtaPagos = medioDePagoService.searchPagoAutomatico(teminoBusqueda);

        // Expected
        Assert.assertNotNull(rtaPagos);

    }

    /**
     * Gets the by codigo OK.
     *
     * @return the by codigo OK
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getByCodigoOK() throws BusinessException {
        String code = "codigoB   usqueda";

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Respuesta<MedioPagoView> rtaPagos = medioDePagoService.getByCodigo(code);

        Assert.assertNotNull(rtaPagos);
        Assert.assertEquals(EstadoRespuesta.OK, rtaPagos.getEstadoRespuesta());
    }

    /**
     * Gets the by codigo NOK.
     *
     * @return the by codigo NOK
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getByCodigoNOK() throws BusinessException {
        String code = "codigoBusqueda";

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuesta(medioPago1);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Respuesta<MedioPagoView> rtaPagos = medioDePagoService.getByCodigo(code);
        Assert.assertNotNull(rtaPagos);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaPagos.getEstadoRespuesta());

    }

}
