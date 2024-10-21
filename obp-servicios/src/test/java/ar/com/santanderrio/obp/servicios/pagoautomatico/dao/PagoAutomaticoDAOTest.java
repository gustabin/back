/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.dao;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagoautomatico.dao.impl.PagoAutomaticoDAOImpl;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * @author julian.ariel.karp
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PagoAutomaticoDAOTest.PagoAutomaticoDAOTestConfiguration.class })
public class PagoAutomaticoDAOTest extends IatxBaseDAOTest {

    @Autowired
    PagoAutomaticoDAO pagoAutomaticoDAO;

    @Configuration
    public static class PagoAutomaticoDAOTestConfiguration {
        @Bean
        public PagoAutomaticoDAO PagoAutomaticoDAO() {
            return new PagoAutomaticoDAOImpl();
        }

    }

    @Test
    public void adhierePagoMisCuentasOK() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00407468000000192017080716020200000000IBF00A87000000000000ALTPESADHE1100000            03007878    00        00 016010599201708071601520000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0008400000000õLA ADHESION NO IMPLICA PAGO             õSPARKLING    õ07200262           õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        String respuesta = pagoAutomaticoDAO.adhierePagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("00407468", respuesta);
    }

    @Test(expected = DAOException.class)
    public void adhierePagoMisCuentasDAOException() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00579788000000252017080914253100000000IBF007IL000000000000ALTPESADHE1100000            03007878    00        00 014208131201708091425230000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000004õBHHõ03õLa operacion solicitada no puede ser realizada. Por favor, reintente mas tarde.                                                                                                                                                                               õSin resp. BAN  õTIME-OUT - SIN RESPUESTA DE BANELCO                                             õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        pagoAutomaticoDAO.adhierePagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
    }

    @Test(expected = DAOException.class)
    public void adhierePagoMisCuentasDAOException2() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException());
        // Then
        pagoAutomaticoDAO.adhierePagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
    }

    @Test
    public void bajaPagoMisCuentasOK() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00407468000000212017080716020400000000IBF00A8A000000000000ALTPESADHE1100000            03007878    00        00 016010604201708071601540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0008400000000õ                                        õ             õ                   õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        IatxResponse respuesta = pagoAutomaticoDAO.bajaPagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @Test(expected = DAOException.class)
    public void bajaPagoMisCuentasDAOException() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00407469000000242017080809252500000000IBF00092000000000000ALTPESADHE1100000            03007878    00        00 009200602201708080925160000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000009õBHHõ03õLa transaccion solicitada no se encuentra disponible en este momento, por favor intente mas tarde.                                                                                                                                                            õBanelco off-linõNO DISPONIBLE - SIN CONECTIVIDAD                                                õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        pagoAutomaticoDAO.bajaPagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
    }

    @Test(expected = DAOException.class)
    public void bajaPagoMisCuentasDAOException2() throws IatxException, DAOException {
        // When
        String servicio = "ALTPESADHE";
        String version = "110";
        Cliente cliente = ClienteMock.completarInfoCliente();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException());
        // Then
        pagoAutomaticoDAO.bajaPagoMisCuentas(cliente, adhesionPagoAutomatico);
        // Expected
    }

    @Test
    public void adhierePagoAutomaticoOK() throws IatxException, DAOException {
        // When
        String servicio = "ALTPAUADHE";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00216303000000242017080910144600000000IBF0019H000000000000ALTPAUADHE1000000            03007878    00        00 010102909201708091014360000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0010400000000õ                                        õSPARKLING    õ00000654672        õ                   õ";
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        ResultadoTransaccion respuesta = pagoAutomaticoDAO.adhierePagoAutomatico(cliente, adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @Test(expected = DAOException.class)
    public void adhierePagoAutomaticoDAOException() throws IatxException, DAOException {
        // When
        String servicio = "ALTPAUADHE";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010303AKRI78  ********00407468000000202017080716020200000000IBF00A88000000000000ALTPAUADHE1000000            03007878    00        00 016010602201708071601540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000004õBHHõ03õLa operacion solicitada no puede ser realizada. Por favor, reintente mas tarde.                                                                                                                                                                               õSin resp. BAN  õTIME-OUT - SIN RESPUESTA DE BANELCO                                             õ";
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        pagoAutomaticoDAO.adhierePagoAutomatico(cliente, adhesionPagoAutomatico);
        // Expected
    }

    @Test(expected = DAOException.class)
    public void adhierePagoAutomaticoDAOException2() throws IatxException, DAOException {
        // When
        String servicio = "ALTPAUADHE";
        String version = "100";
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();

        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCodigoPagoElectronico("07200262");
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException());
        // Then
        pagoAutomaticoDAO.adhierePagoAutomatico(cliente, adhesionPagoAutomatico);
        // Expected
    }
    
}
