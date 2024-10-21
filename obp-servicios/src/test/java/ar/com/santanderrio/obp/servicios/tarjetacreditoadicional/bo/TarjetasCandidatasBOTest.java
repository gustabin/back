/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaInhabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.TarjetaCreditoAdicionalBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaInhabilitadosInEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class TarjetasCandidatasBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TarjetasCandidatasBOTest {

    /** The Constant ESTADO_TARJETA_ACTIVA. */
    private static final String ESTADO_TARJETA_ACTIVA = "10";

    /** The tarjeta credito adicional BO. */
    @InjectMocks
    private TarjetaCreditoAdicionalBO tarjetaCreditoAdicionalBO = new TarjetaCreditoAdicionalBOImpl();

    /** The estado Y limites tarjeta credito BO. */
    @Mock
    private EstadoYLimitesTarjetaCreditoBO estadoYLimitesTarjetaCreditoBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The datos solicitante DAO. */
    @Mock
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The datos selectores BO. */
    @Mock
    private DatosSelectoresBO datosSelectoresBO;

    /** The consulta inhabilitados DAO. */
    @Mock
    private ConsultaInhabilitadosDAO consultaInhabilitadosDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener tarjetas candidatas OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerTarjetasCandidatasOK() throws DAOException {
        Cliente cliente = ClienteMock.completarInfoCliente();
        ConsultaDetalleDatosTarjetaInDTO consultaDetalleDatosTarjetaInDTO = new ConsultaDetalleDatosTarjetaInDTO();
        consultaDetalleDatosTarjetaInDTO.setNroCuentaTarjeta("0412095313");
        consultaDetalleDatosTarjetaInDTO.setTipoTarjeta("1");

        ConsultaDetalleDatosTarjetaOutDTO consultaDetalleDatosTarjetaOutDTO = new ConsultaDetalleDatosTarjetaOutDTO();
        consultaDetalleDatosTarjetaOutDTO.setLimiteDeCompra("5000");
        consultaDetalleDatosTarjetaOutDTO.setEstado(ESTADO_TARJETA_ACTIVA);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(estadoYLimitesTarjetaCreditoBO.obtenerDetalleDatosTarjeta(
                Matchers.any(ConsultaDetalleDatosTarjetaInDTO.class), Matchers.any(Cliente.class)))
                        .thenReturn(consultaDetalleDatosTarjetaOutDTO);

        Respuesta<List<TarjetaCandidataDTO>> respuesta = tarjetaCreditoAdicionalBO.getTarjetasCandidatas();

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Es persona habilitada OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void esPersonaHabilitadaOK() throws DAOException {
        ConsultaInhabilitadosInEntity entity = ConsultaInhabilitadosInEntityMock.infoEntity();
        ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = new ConsultaInhabilitadosOutEntity();
        consultaInhabilitadosOutEntity.setCodigoRetornoExtendido("0");
        when(consultaInhabilitadosDAO.consultaInhabilitados(Matchers.any(ConsultaInhabilitadosInEntity.class)))
                .thenReturn(consultaInhabilitadosOutEntity);

        String codigoRetornoExtendido = tarjetaCreditoAdicionalBO.esPersonaHabilitada(entity)
                .getCodigoRetornoExtendido();

        Assert.assertEquals("0", codigoRetornoExtendido);
        Assert.assertNotNull(codigoRetornoExtendido);

    }

}