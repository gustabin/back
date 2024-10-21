package ar.com.santanderrio.obp.servicios.comun.contrato.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.impl.ContratoBOImpl;
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;

/**
 * The Class ContratoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContratoBOTest {

    /** The contrato BO. */
    @InjectMocks
    private ContratoBO contratoBO = new ContratoBOImpl();

    /** The contrato DAO. */
    @Mock
    private ContratoDAO contratoDAO;

    /**
     * Buscar contrato aceptado ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void buscarContratoAceptado_Ok_Test() throws DAOException {

        Mockito.when(contratoDAO.verContrato(Matchers.any(ContratoInEntity.class), Matchers.any(CampoEnum.class),
                Matchers.any(SinonimoEnum.class))).thenReturn("1");

        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("19520527");
        cliente.setDni("00010271006");
        String resultado = contratoBO.buscarContratoAceptadoOld(cliente.getFechaNacimiento(), cliente.getDni(),
                CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado, new String("1"));
    }

    /**
     * Buscar contrato N O aceptado test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void buscarContrato_NO_Aceptado_Test() throws DAOException {

        Mockito.when(contratoDAO.verContrato(Matchers.any(ContratoInEntity.class), Matchers.any(CampoEnum.class),
                Matchers.any(SinonimoEnum.class))).thenReturn("0");

        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("19520527");
        cliente.setDni("00010271006");
        String resultado = contratoBO.buscarContratoAceptadoOld(cliente.getFechaNacimiento(), cliente.getDni(),
                CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado, new String("0"));
    }

    /**
     * Aceptar contrato ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptarContrato_Ok_Test() throws DAOException {

        Mockito.when(contratoDAO.aceptarContrato(Matchers.any(ContratoInEntity.class), Matchers.any(CampoEnum.class),
                Matchers.any(SinonimoEnum.class))).thenReturn("1");

        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("19520527");
        cliente.setDni("00010271006");
        String resultado = contratoBO.confirmarAceptacionContratoOld(cliente.getFechaNacimiento(), cliente.getDni(),
                CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado, new String("1"));
    }

    /**
     * Aceptar contrato N O ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptarContrato_NO_Ok_Test() throws DAOException {

        Mockito.when(contratoDAO.aceptarContrato(Matchers.any(ContratoInEntity.class), Matchers.any(CampoEnum.class),
                Matchers.any(SinonimoEnum.class))).thenReturn("0");

        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("19520527");
        cliente.setDni("00010271006");
        String resultado = contratoBO.confirmarAceptacionContratoOld(cliente.getFechaNacimiento(), cliente.getDni(),
                CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado, new String("0"));
    }
}
