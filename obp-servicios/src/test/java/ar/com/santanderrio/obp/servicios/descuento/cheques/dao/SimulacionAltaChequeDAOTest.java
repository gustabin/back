package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.SimulacionAltaChequeDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		SimulacionAltaChequeDAOTest.SimulacionAltaChequeDAOTestConfiguration.class })
public class SimulacionAltaChequeDAOTest extends IatxBaseDAOTest{
	
	@Autowired
	SimulacionAltaChequeDAO simulacionAltaChequeDAO;
	
    /** The cliente. */
    private Cliente cliente = new Cliente();

    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
    }

    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.descuento.cheques.dao",
            "ar.com.santanderrio.obp.base.comun" })
    public static class SimulacionAltaChequeDAOTestConfiguration {
        @Bean
        SimulacionAltaChequeDAO simulacionAltaChequeDAO() {
            return new SimulacionAltaChequeDAOImpl();
        }
    }
    
    @Test
    public void simulacionAltaChequeOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00475562000000102017122712331400000000IBF00LYX000000000000ACTPRECAR_1000000            03682408    00        00 012328003201712271233100000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0451200000000õ09õ0000õ3604292õ0000130217õ000000000543277õ0000000000õ0000000000õ0000000000õNõNõSõNõNõNõ10õ00õ000000000050000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000050000õ000000000005000õ000000000045000õ000000000000000õ02õACEPTADA                                          õ          õ                                                                                                    õ03õACEPTADO                                          õ                                                                                                    õ010õ072õ000õ1036õ1õ00001613õ9õ00000230670õ0õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ000õ1036õ1õ96385825õ2õ00000171670õ0õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ000õ1036õ1õ96385828õ3õ00000171670õ0õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ000õ1036õ1õ96385849õ8õ00000171670õ0õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ000õ1036õ1õ96385865õ8õ00000171670õ0õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061298õ7õ00003205631õ4õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061303õ4õ00003205631õ4õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061316õ4õ00003205631õ4õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061317õ1õ00003205631õ4õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061321õ8õ00003205631õ4õ007õ000000000005000õ2018-01-03õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);

		ChequesASimularDTO dto = new ChequesASimularDTO();
		dto.getListaCheques().add(new ChequeASimularDTO());
		SimulacionAltaChequesEntity  datosCesion = simulacionAltaChequeDAO.simularAltaChequesDAO(dto , cliente);
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test
    public void simulacionAltaChequeRechazadaTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00460878000000082018032311041400000000IBF006RJ000000000000ACTPRECAR_1000000            03682408    00        00 011018590201803231104070000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0214800000000õ09õ0000õ3604292õ0000130241õ000000035991490õ0000295000õ0000348350õ0000437214õNõNõSõNõNõNõ01õ03õ000000000010000õ000000000000000õ000000000000259õ000000000000000õ000000000000065õ000000000009676õ000000000005000õ000000000004676õ000000000030000õ19õRECHAZADA SIMULACION                              õ          õLA OPERACION ES MENOR AL MONTO MINIMO ADMITIDO                                                      õ36õRECHAZADO OPER. < MONTO MINIMO ADMITIDO SIMULACIONõPOR FAVOR CONTACTESE CON SU EJECUTIVO DE CUENTA, DISCULPE LAS MOLESTIAS OCASIONADAS.                õ004õ072õ000õ1036õ1õ07093859õ9õ90000244017õ5õ000õ000000000010000õ2018-04-05õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000010000õNõ00021767058õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õEL CHQ EXISTE EN CARTERA DE CESION Y NO ES REDEPO   SITABLE                                         õ0000000000õ072õ000õ9999õ1õ00000324õ5õ00000232645õ0õ000õ000000000010000õ2018-04-16õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000010000õNõ00013238861õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õ90 - ZQ9CCCO0             - (ZY8COPE0/60        O   SITABLE                                         õ0000000000õ072õ280õ1642õ8õ81061301õ0õ00003205631õ4õ000õ000000000010000õ2018-04-24õ000000000000000õ000000000000259õ000000000000000õ000000000000065õ000000000009676õNõ00005134021õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000025900õ072õ280õ1642õ8õ81061303õ4õ00003205631õ4õ000õ000000000010000õ2018-04-18õ000000000000000õ000000000000210õ000000000000000õ000000000000053õ000000000009737õNõ00022792232õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õEL CLIENTE ES EL LIBRADOR DEL CHEQUEõ1202457877";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);

		SimulacionAltaChequesEntity  datosCesion = simulacionAltaChequeDAO.simularAltaChequesDAO(new ChequesASimularDTO(), cliente);
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test
    public void simulacionAltaChequeErrorFueraHorarioTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010304ALNJ54  ********00543440000000102017121810391100000000IBF001WB000000000000ACTPRECAR_1000000            04013954    00        00 010317319201712181038580000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0032510000083õAZYõ03õLa operacion solicitada no puede realizarse debido a que se encuentra fuera del horario habilitado.                                                                                                                                                           õFUERA HORARIO  õZYE0083 - APLICACION FUERA DE HORARIOõ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);

		SimulacionAltaChequesEntity  datosCesion = simulacionAltaChequeDAO.simularAltaChequesDAO(new ChequesASimularDTO(), cliente);
        Gson gson = new Gson();
        String json = gson.toJson(datosCesion);
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test
    public void simulacionAltaChequeErrorConAlgunosChequesRechazadosTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00543400000000082017121417110500000000IBF00SOQ000000000000ACTPRECAR_1000000            03682408    00        00 017198035201712141711020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0214800000000õ09õ0000õ3604292õ0000130211õ000000000591277õ0000000000õ0000000000õ0000000000õNõNõSõNõNõNõ02õ02õ000000000002000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000002000õ000000000000000õ000000000002000õ000000000400600õ02õACEPTADA                                          õ          õ                                                                                                    õ03õACEPTADO                                          õ                                                                                                    õ004õ015õ112õ1059õ7õ49069253õ4õ24300148244õ8õ012õ000000000001000õ2017-12-22õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000001000õNõ00021767058õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ000õ1000õ2õ51100044õ1õ00003616534õ6õ008õ000000000001000õ2017-12-22õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000001000õNõ00013238861õ õ           õ1õ                                                                                                    õ                                                                                                    õ0000000000õ072õ280õ1642õ8õ81061298õ7õ00003205631õ4õ008õ000000000300300õ2017-12-22õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000300300õNõ00023333444õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õN00023333444 SUPERA EL 30% DEL MONTO DE CESION                                                      õ0000000000õ072õ280õ1642õ8õ81061303õ4õ00003205631õ4õ008õ000000000100300õ2017-12-22õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000100300õNõ00023333444õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õN00023333444 SUPERA EL 30% DEL MONTO DE CESION                                                      õ0000000000õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);

		SimulacionAltaChequesEntity  datosCesion = simulacionAltaChequeDAO.simularAltaChequesDAO(new ChequesASimularDTO(), cliente);
        Gson gson = new Gson();
        String json = gson.toJson(datosCesion);
        Assert.assertTrue(datosCesion!=null);
    }
    
}
