package ar.com.santanderrio.obp.servicios.banelco.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfirmacionAltaDestinatarioInViewMock;
import ar.com.santanderrio.obp.servicios.banelco.bo.impl.BanelcoBOImpl;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;

/**
 * The Class BanelcoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class BanelcoBOTest {

    /** The banelco BO. */
    @InjectMocks
    private BanelcoBOImpl banelcoBO;

    /** The banelco DAO. */
    @Mock
    BanelcoDAO banelcoDAO;

    /** The respuesta factory. */
    @Mock
    RespuestaFactory respuestaFactory;

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The Constant CODIGO_ERROR_DEBITO_BLOQUEADA. */
    private static final int CODIGO_ERROR_DEBITO_BLOQUEADA = 10005020;

    /** The Constant CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS. */
    private static final int CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS = 10005030;

    /** The Constant MENSAJE_BANELCO_BLOQUEADA. */
    private static final String MENSAJE_BANELCO_BLOQUEADA = "1291";

    /**
     * Validar tarjeta debito.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarTarjetaDebito() throws BusinessException, DAOException {
        Cliente cliente = new Cliente();
        String nroTarjeta = "12345678";
        Respuesta<Boolean> respuestaOK = new Respuesta<Boolean>();
        respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOK.setRespuesta(new Boolean(true));
        Mockito.when(banelcoDAO.validarTarjetaDebito(Matchers.any(Cliente.class), Matchers.any(String.class)))
                .thenReturn(respuestaOK);
        Respuesta<Boolean> rta = banelcoBO.validarTarjetaDebito(nroTarjeta);

        Assert.assertNotNull(rta);
    }

    /**
     * Validar tarjeta debito bloqueada.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void validarTarjetaDebitoBloqueada() throws BusinessException {
        Cliente cliente = new Cliente();
        String nroTarjeta = "12345678";
        Respuesta<Boolean> rtaError = new Respuesta<Boolean>();
        rtaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rtaError.setRespuestaVacia(true);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        rtaError.add(item);
        DAOException exception = new DAOException("Mensaje Debito bloqueada");
        exception.setErrorCode(String.valueOf(CODIGO_ERROR_DEBITO_BLOQUEADA));
        Mockito.when(respuestaFactory.crearRespuestaError(Boolean.class, "Mensaje Balenco Bloqueado", null))
                .thenReturn(rtaError);
        Mockito.when(mensajeDAO.obtenerMensajeDescripcion(MENSAJE_BANELCO_BLOQUEADA))
                .thenReturn("Mensaje Balenco Bloqueado");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<Boolean> rta = null;
        try {
            Mockito.when(banelcoDAO.validarTarjetaDebito(cliente, nroTarjeta)).thenThrow(exception);
            rta = banelcoBO.validarTarjetaDebito(nroTarjeta);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (BusinessException e2) {
            e2.printStackTrace();
        }

        Assert.assertNull(rta);
    }

    /**
     * Validar tarjeta debito error.
     */
    @Test
    public void validarTarjetaDebitoError() {
        Cliente cliente = new Cliente();
        String nroTarjeta = "12345678";

        Respuesta<Boolean> rtaError = new Respuesta<Boolean>();
        rtaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rtaError.setRespuestaVacia(true);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        rtaError.add(item);
        DAOException exception = new DAOException("Error validacion");
        exception.setErrorCode(String.valueOf(CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS));
        Mockito.when(respuestaFactory.crearRespuestaError(Boolean.class, "", "")).thenReturn(rtaError);
        Respuesta<Boolean> rta = null;
        try {
            Mockito.when(banelcoDAO.validarTarjetaDebito(cliente, nroTarjeta)).thenThrow(exception);
            rta = banelcoBO.validarTarjetaDebito(nroTarjeta);
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            Assert.assertEquals(CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS, e.getErrorCode());
        }
    }
}
