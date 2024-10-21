package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsumoPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.ConsumoPendienteManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoPendienteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;

/**
 * The Class ConsumoPendienteManagerTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsumoPendienteManagerTest {

    /** The consumo pendiente manager. */
    @InjectMocks
    private ConsumoPendienteManager consumoPendienteManager = new ConsumoPendienteManagerImpl();

    /** The sesion tarjetas. */
    @Mock
    private SesionTarjetas sesionTarjetas;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /**
     * Obtener consumo pendiente ok test.
     */
    @Test
    public void obtenerConsumoPendienteOkTest() {
        ConsumosPendientesView consumosPendientesView = new ConsumosPendientesView();
        consumosPendientesView.setConsumosPendientes(new ArrayList<ConsumoPendienteView>());
        consumosPendientesView.getConsumosPendientes().add(new ConsumoPendienteView());
        consumosPendientesView.getConsumosPendientes().get(0).setMarca("Duracell");
        Respuesta<ConsumosPendientesView> toRet = new Respuesta<ConsumosPendientesView>();

        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(sesionTarjetas.getConsumoPendiente()).thenReturn(consumosPendientesView);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.eq(ConsumosPendientesView.class),
                Matchers.any(ConsumosPendientesView.class))).thenReturn(toRet);

        Respuesta<ConsumosPendientesView> res = consumoPendienteManager.obtenerConsumoPendiente();
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Obtener consumo pendiente error test.
     */
    @Test
    public void obtenerConsumoPendienteErrorTest() {
        ConsumosPendientesView consumosPendientesView = new ConsumosPendientesView();
        consumosPendientesView.setConsumosPendientes(new ArrayList<ConsumoPendienteView>());
        Respuesta<Object> toRet = new Respuesta<Object>();

        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(sesionTarjetas.getConsumoPendiente()).thenReturn(consumosPendientesView);
        Mockito.when(respuestaFactory.crearRespuestaError(
                TagItemMensajeTarjetaEnum.CONSUMOS_PENDIENTES.getDescripcionTag(), TipoError.ERROR_CONSUMOS_PENDIENTES,
                CodigoMensajeConstantes.CODIGO_ERROR_CONSUMOS_PENDIENTES_CONFIRMACION)).thenReturn(toRet);

        Respuesta<ConsumosPendientesView> res = consumoPendienteManager.obtenerConsumoPendiente();
        Assert.assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

}
