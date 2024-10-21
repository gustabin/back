/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimoResumenBo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTOMock;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.UltimoResumenManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;

/**
 * The Class UltimoResumenManagerTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class UltimoResumenManagerTest {

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The ultimo resumen BO. */
    @Mock
    private UltimoResumenBo ultimoResumenBO;

    /** The casuistica. */
    @Mock
    private CasuisticaErrorTarjetasBO casuistica;

    /** The ultimo resumen manager. */
    @InjectMocks
    private UltimoResumenManager ultimoResumenManager = new UltimoResumenManagerImpl();

    /** The sesion tarjetas. */
    @Mock
    private SesionTarjetas sesionTarjetas;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    @Mock
    private CuentaBO cuentaBO;

    /**
     * Dado un nro de cuenta, cuando se invoca al metodo "obtenerUltimoResumen",
     * obtengo la respuesta del ultimo resumen OK.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoNroCuentaCuandoInvocaObtenerUltimoResumenObtengoRespuestaUltimoResumenOK()
            throws BusinessException {
        Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoCliente());
        Mockito.when(ultimoResumenBO.obtenerUltimoResumen(Matchers.any(Cliente.class),
                Matchers.any(IdentificacionCuenta.class))).thenReturn(obtenerRespuestaOKUltimoResumenDTO());
        Mockito.when(casuistica.crearRespuestaOk(Matchers.eq(UltimoResumenView.class),
                Matchers.any(UltimoResumenView.class))).thenReturn(obtenerRespuestaUltimoResumenView());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class),Matchers.any(IdentificacionCuenta.class)))
        .thenReturn(new Cuenta());
        Respuesta<UltimoResumenView> respuesta = ultimoResumenManager.obtenerUltimoResumen("221-2579806/6");

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals("AMEX", respuesta.getRespuesta().getMarca());
        Assert.assertEquals("XXXX - 7280", respuesta.getRespuesta().getNumeroTarjeta());
        Assert.assertEquals(Boolean.FALSE, respuesta.getRespuesta().getTieneAlias());
        Assert.assertEquals("3.560,54", respuesta.getRespuesta().getSaldoPesos());
        Assert.assertEquals("13,98", respuesta.getRespuesta().getSaldoDolares());
        Assert.assertEquals("190,00", respuesta.getRespuesta().getPagoMinimo());
        Assert.assertEquals("18.900,00", respuesta.getRespuesta().getLimiteFinanciacion());
        Assert.assertEquals("21.000,00", respuesta.getRespuesta().getLimiteCompra());
        Assert.assertEquals("31.500,00", respuesta.getRespuesta().getLimiteCompraCuotas());
        Assert.assertEquals(Boolean.FALSE, respuesta.getRespuesta().getSonLimitesDolar());
        Assert.assertEquals("38,00", respuesta.getRespuesta().getTasaNominalAnualPesos());
        Assert.assertEquals("0,00", respuesta.getRespuesta().getTasaNominalAnualDolares());
        Assert.assertEquals("3,12", respuesta.getRespuesta().getTasaEfectivaMensualPesos());
        Assert.assertEquals("0,00", respuesta.getRespuesta().getTasaEfectivaMensualDolares());
        Assert.assertEquals(Boolean.FALSE, respuesta.getRespuesta().getSonLimitesDolar());
        Assert.assertEquals("Información obtenida de VISA@home S.E.U.O.", respuesta.getRespuesta().getMensajeSEUO());
        Assert.assertEquals(Boolean.FALSE, respuesta.getRespuesta().getMuestraTarjetasConCabecera());
        Assert.assertEquals("XXXX - 7280", respuesta.getRespuesta().getTarjetas().get(0).getNumeroTarjeta());
        Assert.assertEquals("241,50", respuesta.getRespuesta().getTarjetas().get(0).getTotalPesos());
        Assert.assertEquals("0,00", respuesta.getRespuesta().getTarjetas().get(0).getTotalDolares());
        Assert.assertEquals("792507",
                respuesta.getRespuesta().getTarjetas().get(0).getLineas().get(0).getComprobante());
        Assert.assertEquals("Arredo",
                respuesta.getRespuesta().getTarjetas().get(0).getLineas().get(0).getDescripcion());
        Assert.assertEquals("241,50",
                respuesta.getRespuesta().getTarjetas().get(0).getLineas().get(0).getImportePesos());
        Assert.assertEquals(Boolean.FALSE, respuesta.isRespuestaVacia());
    }

    /**
     * Dado un nro de cuenta, cuando se invoca al metodo "obtenerUltimoResumen",
     * obtengo la respuesta sin ultimo resumen.
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void dadoNroCuentaCuandoInvocaObtenerUltimoResumenObtengoRespuestaSinUltimoResumen()
            throws BusinessException {
        Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoCliente());
        Mockito.when(ultimoResumenBO.obtenerUltimoResumen(Matchers.any(Cliente.class),
                Matchers.any(IdentificacionCuenta.class))).thenReturn(obtenerRespuestaSinUltimoResumenDTO());
        Mockito.when(casuistica.crearRespuestaError(Matchers.eq(UltimoResumenView.class), Matchers.any(List.class)))
                .thenReturn(obtenerRespuestaSinUltimoResumenView());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class),Matchers.any(IdentificacionCuenta.class)))
        .thenReturn(new Cuenta());

        Respuesta<UltimoResumenView> respuesta = ultimoResumenManager.obtenerUltimoResumen("221-2579806/6");

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertNull(respuesta.getRespuesta());
        Assert.assertEquals("Mensaje de error sin ultimo resumen.",
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag(),
                respuesta.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(TipoError.ERROR_SIN_ULTIMO_RESUMEN.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener respuesta sin ultimo resumen view.
     *
     * @return the respuesta
     */
    private Respuesta<UltimoResumenView> obtenerRespuestaSinUltimoResumenView() {
        Respuesta<UltimoResumenView> respView = new Respuesta<UltimoResumenView>();
        respView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respView.setItemMensajeRespuesta(obtenerItemsSinUltimoResumen());
        respView.setRespuestaVacia(Boolean.TRUE);
        return respView;
    }

    /**
     * Obtener respuesta sin ultimo resumen DTO.
     *
     * @return the respuesta
     */
    private Respuesta<UltimoResumenDTO> obtenerRespuestaSinUltimoResumenDTO() {
        Respuesta<UltimoResumenDTO> respDTO = new Respuesta<UltimoResumenDTO>();
        respDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respDTO.setItemMensajeRespuesta(obtenerItemsSinUltimoResumen());
        respDTO.setRespuestaVacia(Boolean.TRUE);
        return respDTO;
    }

    /**
     * Obtener items sin ultimo resumen.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsSinUltimoResumen() {
        List<ItemMensajeRespuesta> itemList = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje("Mensaje de error sin ultimo resumen.");
        item.setTag(TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag());
        item.setTipoError(TipoError.ERROR_SIN_ULTIMO_RESUMEN.getDescripcion());
        itemList.add(item);
        return itemList;
    }

    /**
     * Obtener respuesta ultimo resumen view.
     *
     * @return the respuesta
     */
    private Respuesta<UltimoResumenView> obtenerRespuestaUltimoResumenView() {
        Respuesta<UltimoResumenView> respView = new Respuesta<UltimoResumenView>();
        respView.setEstadoRespuesta(EstadoRespuesta.OK);
        respView.setRespuesta(new UltimoResumenView(UltimoResumenDTOMock.completarInfoUltimoResumenDTO()));
        respView.setRespuestaVacia(Boolean.FALSE);
        return respView;
    }

    /**
     * Obtener respuesta ultimo resumen DTO.
     *
     * @return the respuesta
     */
    private Respuesta<UltimoResumenDTO> obtenerRespuestaOKUltimoResumenDTO() {
        Respuesta<UltimoResumenDTO> resp = new Respuesta<UltimoResumenDTO>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        resp.setRespuesta(UltimoResumenDTOMock.completarInfoUltimoResumenDTO());
        resp.setRespuestaVacia(Boolean.FALSE);
        return resp;
    }
}