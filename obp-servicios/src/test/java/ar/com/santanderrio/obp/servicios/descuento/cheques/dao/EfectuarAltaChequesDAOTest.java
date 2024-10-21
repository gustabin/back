package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
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
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.EfectuarAltaChequesDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EfectivizacionAltaChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		EfectuarAltaChequesDAOTest.EfectuarAltaChequesDAOTestConfiguration.class })
public class EfectuarAltaChequesDAOTest extends IatxBaseDAOTest {

	@Autowired
	EfectuarAltaChequesDAO efectuarAltaChequesDAO;
	
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
    public static class EfectuarAltaChequesDAOTestConfiguration {
        @Bean
        EfectuarAltaChequesDAO efectuarAltaChequesDAO() {
            return new EfectuarAltaChequesDAOImpl();
        }
        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }
    }
    
    @Test
    public void simulacionAltaChequeOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00475562000000112017122712333300000000IBF00LZ9000000000000ACTCONFPRE1000000            03682408    00        00 012328034201712271233190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0191300000000õ00õ000õ3604292õ03õPRECARGA                                          õNõNõSõNõNõNõ2017-12-27õARSõ0000000000õ0000000000õ0000000000õ10õ000000000050000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000050000õ000000000005000õ000000000045000õ010õ072õ000õ1036õ1õ00001613õ9õ00000230670õ0õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ000õ1036õ1õ96385825õ2õ00000171670õ0õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ000õ1036õ1õ96385828õ3õ00000171670õ0õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ000õ1036õ1õ96385849õ8õ00000171670õ0õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ000õ1036õ1õ96385865õ8õ00000171670õ0õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ280õ1642õ8õ81061298õ7õ00003205631õ4õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ280õ1642õ8õ81061303õ4õ00003205631õ4õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ280õ1642õ8õ81061316õ4õ00003205631õ4õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ280õ1642õ8õ81061317õ1õ00003205631õ4õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ072õ280õ1642õ8õ81061321õ8õ00003205631õ4õ2018-01-03õ007õ000000000005000õ000000000000000õ000000000000000õ000000000000000õ000000000000000õ000000000005000õ0000000000õ1õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);

		EfectivizacionAltaChequesEntity  datosCesion = efectuarAltaChequesDAO.efectuarAltaChequesDAO("", cliente);
		Gson gson = new Gson();
		String json = gson.toJson(datosCesion);
        Assert.assertTrue(datosCesion!=null);
    }
    
}
