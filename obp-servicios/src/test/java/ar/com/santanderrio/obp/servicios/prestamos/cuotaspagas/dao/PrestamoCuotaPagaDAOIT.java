package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao;

import static org.mockito.Mockito.when;

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
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao.impl.PrestamoCuotaPagaDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PrestamoCuotaPagaDAOTest.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PrestamoCuotaPagaDAOIT.PrestamoCuotaPagaDAOTestConfiguration.class })
public class PrestamoCuotaPagaDAOIT extends IatxBaseDAOTest {

    /** The Constant SERVICIO_CNSPMOHIST. */
    private static final String SERVICIO_CNSPMOHIST = "CNSPMOHIST";

    /** The Constant VERSION_120. */
    private static final String VERSION_120 = "120";

    /** The Constant MENSAJE_ERROR. */
    private static final String MENSAJE_ERROR = "Error de Iatx.";

    /** The Constant SERVICIO_CNINTERVI. */
    private static final String SERVICIO_CNINTERVI = "CNINTERVI";

    /** The prestamo cuota paga DAO. */
    @Autowired
    private PrestamoCuotaPagaDAOImpl prestamoCuotaPagaDAO;

    /**
     * The Class PrestamoCuotaPagaDAOTestConfiguration.
     */
    @Configuration
    public static class PrestamoCuotaPagaDAOTestConfiguration {

        /**
         * Prestamo cuota paga DAO.
         *
         * @return the prestamo cuota paga DAO
         */
        @Bean
        public PrestamoCuotaPagaDAO prestamoCuotaPagaDAO() {
            return new PrestamoCuotaPagaDAOImpl();
        }
    }

    /**
     * Dados un cliente y una ConsultaCuotaPagaInEntity, cuando se invoca al
     * metodo "obtenerCuotasPagasPrestamo", obtengo DAOException por un error de
     * servicio con codigo de retorno extendido distinto de cero.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYConsultaCuotaPagaInEntityCuandoInvocaObtenerCuotasPagasPrestamoObtengoDAOExceptionPorErrorServicioConCodigoRetornoDistintoCero()
            throws IatxException, DAOException, SinCuotasPagasException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010302QLQQ47  ********00640757000000452017041217275100000000IBF21422000000000000CNSPMOHIST1200000            02616647N   00        00 000000000201704121727440000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHÃµ01826-1ÃµNÃµ                          Ãµ   Ãµ000Ãµ004ÃµUGX8HN2017-01-0917223200ÃµCOBRÃµCOBRO DE RECIBOS    Ãµ2017-01-09Ãµ2017-01-09Ãµ2017-01-09Ãµ2017-01-04ÃµNÃµ0001-01-01Ãµ8550ÃµARSÃµ00000001242435300Ãµ00000001232583800ÃµUGDTMVRÃµARSÃµ00000000000000000Ãµ Ãµ                    Ãµ00000000029211700Ãµ1ÃµCUENTA              ÃµTRANÃµ00000000000000000+Ãµ000000000Ãµ00109Ãµ00000000009851500Ãµ00000000018170600Ãµ00000000001149400Ãµ00000000000000000Ãµ00000000000000000Ãµ00000000000000000Ãµ017550000Ãµ019034869Ãµ015880000Ãµ015880000ÃµHOMEBANKÃµUGX8TP2017-02-0816350800ÃµCOBRÃµCOBRO DE RECIBOS    Ãµ2017-02-08Ãµ2017-02-08Ãµ2017-02-08Ãµ2017-02-04ÃµNÃµ0001-01-01Ãµ8550ÃµARSÃµ00000001232583800Ãµ00000001222588200ÃµUGDTMVRÃµARSÃµ00000000000000000Ãµ Ãµ                    Ãµ00000000029200000Ãµ1ÃµCUENTA              ÃµTRANÃµ00000000000000000+Ãµ000000000Ãµ00110Ãµ00000000009995600Ãµ00000000018026500Ãµ00000000001145300Ãµ00000000000000000Ãµ00000000000000000Ãµ00000000000000000Ãµ017550000Ãµ019034869Ãµ015880000Ãµ015880000ÃµHOMEBANKÃµUGX81D2017-02-2315570100ÃµCOBRÃµCOBRO DE RECIBOS    Ãµ2017-02-23Ãµ2017-02-23Ãµ2017-02-23Ãµ2017-03-04ÃµNÃµ0001-01-01Ãµ8550ÃµARSÃµ00000001222588200Ãµ00000001212446500ÃµUGDTMVRÃµARSÃµ00000000000000000Ãµ Ãµ                    Ãµ00000000029113100Ãµ1ÃµCUENTA              ÃµTRANÃµ00000000000000000+Ãµ000000000Ãµ00111Ãµ00000000010141700Ãµ00000000017880400Ãµ00000000001091000Ãµ00000000000000000Ãµ00000000000000000Ãµ00000000000000000Ãµ017550000Ãµ019034869Ãµ015880000Ãµ015880000ÃµHOMEBANKÃµUGX8452017-03-3010282100ÃµCOBRÃµCOBRO DE RECIBOS    Ãµ2017-03-30Ãµ2017-03-30Ãµ2017-03-30Ãµ2017-04-04ÃµNÃµ0001-01-01Ãµ8550ÃµARSÃµ00000001212446500Ãµ00000001202156400ÃµUGDTMVRÃµARSÃµ00000000000000000Ãµ Ãµ                    Ãµ00000000029158900Ãµ1ÃµCUENTA              ÃµTRANÃµ00000000000000000+Ãµ000000000Ãµ00112Ãµ00000000010290100Ãµ00000000017732000Ãµ00000000001136800Ãµ00000000000000000Ãµ00000000000000000Ãµ00000000000000000Ãµ017550000Ãµ019034869Ãµ015880000Ãµ015880000ÃµHOMEBANKÃµ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNSPMOHIST, VERSION_120))))
                .thenReturn(tramaResponsePrimera);

        ConsultaCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(),
                new ConsultaCuotaPagaInEntity("0000", "000001234567", "2015-09-23", "2017-09-23"));
    }

    /**
     * Dados un cliente y una ConsultaCuotaPagaInEntity con error, cuando se
     * invoca al metodo "obtenerCuotasPagasPrestamo", obtengo DAOException por
     * falla en el "validate".
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYConsultaCuotaPagaInEntityErrorCuandoInvocaObtenerCuotasPagasPrestamoObtengoDAOExceptionPorErrorValidate()
            throws IatxException, DAOException, SinCuotasPagasException {
        ConsultaCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(),
                new ConsultaCuotaPagaInEntity("0000", "000001234567", "2015-09-23", "19170-09-23"));
    }

    /**
     * Dados un cliente y una ConsultaCuotaPagaInEntity, cuando se invoca al
     * metodo "obtenerCuotasPagasPrestamo", obtengo DAO exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYConsultaCuotaPagaInEntityCuandoInvocaObtenerCuotasPagasPrestamoObtengoDAOException()
            throws IatxException, DAOException, SinCuotasPagasException {
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNSPMOHIST, VERSION_120))))
                .thenThrow(new IatxException(MENSAJE_ERROR));

        ConsultaCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(),
                new ConsultaCuotaPagaInEntity("0000", "000001234567", "2015-09-23", "2017-09-23"));
    }

    /**
     * Dados un cliente y DatoClienteCuotaPagaInEntity con error en validacion,
     * cuando se invoca al metodo "obtenerDatosClienteCuotasPagasPrestamo",
     * obtengo DAOException.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYDatoClienteCuotaPagaInEntityErrorValidacionCuandoInvocaObtenerDatosClienteCuotasPagasPrestamoObtengoDAOException()
            throws IatxException, DAOException {
        DatoClienteCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerDatosClienteCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(), new DatoClienteCuotaPagaInEntity("0000", "00000000012345"));
    }

    /**
     * Dados un cliente y DatoClienteCuotaPagaInEntity, cuando se invoca al
     * metodo "obtenerDatosClienteCuotasPagasPrestamo", obtengo DAOException por
     * codigo de retorno extendido distinto a cero.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYDatoClienteCuotaPagaInEntityCuandoInvocaObtenerDatosClienteCuotasPagasPrestamoObtengoDAOExceptionPorCodErrorDistintoCero()
            throws IatxException, DAOException {
        String tramaResponse = "200000000000P04HTML0009900010390229769  ********00042484000000102016072715050600000000IBF31357000000000000CNSINTERVI1200000            90229769    00        00 015080867201607271505020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHÃµ00289-1ÃµAV FEDERICO LACROZE Ãµ03164ÃµC  Ãµ6 ÃµCAPITAL      Ãµ1426ÃµCÃµCQRÃµ01Ãµ080Ãµ20533994Ãµ117Ãµ001Ãµ90229769ÃµTIÃµ001ÃµAÃµ31129999Ãµ  ÃµAGUILAR                       ÃµANALIA JIMENA                 ÃµNÃµ00033908038ÃµSÃµLÃµ27339080386ÃµFÃµ09031989Ãµ00000ÃµSÃµ2013-09-02-14.28.30.871260Ãµ  Ãµ  Ãµ   Ãµ   Ãµ  Ãµ  Ãµ02092013Ãµ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNINTERVI, VERSION_120))))
                .thenReturn(tramaResponse);

        DatoClienteCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerDatosClienteCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(), new DatoClienteCuotaPagaInEntity("0000", "000000012345"));
    }

    /**
     * Dados un cliente y DatoClienteCuotaPagaInEntity, cuando se invoca al
     * metodo "obtenerDatosClienteCuotasPagasPrestamo", obtengo DAOException por
     * codigo de retorno extendido distinto a cero.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void dadosClienteYDatoClienteCuotaPagaInEntityCuandoInvocaObtenerDatosClienteCuotasPagasPrestamoObtengoDAOExceptionPorIatxException()
            throws IatxException, DAOException {
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNINTERVI, VERSION_120))))
                .thenThrow(new IatxException("Error de Iatx."));

        DatoClienteCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerDatosClienteCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(), new DatoClienteCuotaPagaInEntity("0000", "000000012345"));
    }

    /**
     * Obtener datos cliente cuotas pagas prestamo OK test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosClienteCuotasPagasPrestamoOKTest() throws IatxException, DAOException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00107935000000142017120609585600000000IBF0016Q000000000000CNSINTERVI1200000            02615492N   00        00 009507505201712060958430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0028900000000õUSPALLATA           õ02953õA  õ1 õCAPITAL      õ1437õCõJCGõ01õ080õ42961388õ002õ001õ02615492õTIõ001õAõ31129999õ  õCOMIGNAGHI                    õVALERIANO PAUL TADEO          õNõ00013238861õSõLõ20132388610õFõ05101959õ00000õSõ2016-12-01-17.35.59.221332õ  õ  õ   õ   õ  õ  õ01122016õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNINTERVI, VERSION_120))))
                .thenReturn(tramaResponse);

        // Then
        DatoClienteCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerDatosClienteCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(), new DatoClienteCuotaPagaInEntity("0000", "000000012345"));

        // Expected
        Assert.assertNotNull(outEntity);
    }

    /**
     * Obtener cuotas pagas prestamo OK test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuotasPagasPrestamoOKTest() throws IatxException, DAOException, SinCuotasPagasException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00107935000000152017120609585600000000IBF0016R000000000000CNSPMOHIST1200000            02615492    00        00 000000000201712060958430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0271400000000õNõ                          õ   õ000õ006õUGX8E82017-07-1212145800õCOBRõCOBRO DE RECIBOS    õ2017-07-12õ2017-07-12õ2017-07-12õ2017-01-03õNõ0001-01-01õ8550õARSõ00000000050000000õ00000000049598200õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000002112300õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00001õ00000000000401800õ00000000001220500õ00000000000000000õ00000000000351300õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõUGX99Q2017-07-1313051000õCOBRõCOBRO DE RECIBOS    õ2017-07-13õ2017-07-13õ2017-07-13õ2017-02-03õNõ0001-01-01õ8550õARSõ00000000049598200õ00000000049187400õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000001959700õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00002õ00000000000410800õ00000000001116000õ00000000000000000õ00000000000318300õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõUGX9ZM2017-08-2815510200õCOBRõCOBRO DE RECIBOS    õ2017-08-28õ2017-08-28õ2017-08-28õ2017-03-03õNõ0001-01-01õ8550õARSõ00000000049187400õ00000000048767300õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000001970800õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00003õ00000000000420100õ00000000001106700õ00000000000000000õ00000000000315100õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõUGX8TQ2017-09-0711014100õCOBRõCOBRO DE RECIBOS    õ2017-09-07õ2017-09-07õ2017-09-07õ2017-04-03õNõ0001-01-01õ8550õARSõ00000000048767300õ00000000048337800õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000001955000õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00004õ00000000000429500õ00000000001097300õ00000000000000000õ00000000000313300õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõUGX8NC2017-09-2610324700õCOBRõCOBRO DE RECIBOS    õ2017-09-26õ2017-09-26õ2017-09-26õ2017-05-03õNõ0001-01-01õ8550õARSõ00000000048337800õ00000000047898600õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000001942200õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00005õ00000000000439200õ00000000001087600õ00000000000000000õ00000000000307200õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõUGX8U32017-10-0409482100õCOBRõCOBRO DE RECIBOS    õ2017-10-04õ2017-10-04õ2017-10-04õ2017-06-03õNõ0001-01-01õ8550õARSõ00000000047898600õ00000000047449500õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000001920600õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00006õ00000000000449100õ00000000001077700õ00000000000000000õ00000000000301600õ00000000000000000õ00000000000000000õ027000000õ030604999õ000000000õ000000000õHOMEBANKõ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(SERVICIO_CNSPMOHIST, VERSION_120))))
                .thenReturn(tramaResponse);

        // Then
        ConsultaCuotaPagaOutEntity outEntity = prestamoCuotaPagaDAO.obtenerCuotasPagasPrestamo(
                ClienteMock.completarInfoCliente(),
                new ConsultaCuotaPagaInEntity("0000", "000000012345", "2015-02-02", "2017-09-09"));

        // Expected
        Assert.assertNotNull(outEntity);
    }
}
