package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
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
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.DetalleOperacionesPrecargadasDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		DetalleOperacionesPrecargadasDAOTest.DetalleOperacionesPrecargadasDAOTestConfiguration.class })
public class DetalleOperacionesPrecargadasDAOTest extends IatxBaseDAOTest{

	@Autowired
	DetalleOperacionesPrecargadasDAO detalleOperacionesPrecargadasDAO;
	
	@InjectMocks
    ArchivoDeRecursosDAO recursos = new ArchivoDeRecursosDAOImpl();
    
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
    public static class DetalleOperacionesPrecargadasDAOTestConfiguration {
        @Bean
        DetalleOperacionesPrecargadasDAO detalleOperacionesPrecargadasDAO() {
            return new DetalleOperacionesPrecargadasDAOImpl();
        }
        
        @Bean
        ArchivoDeRecursosDAO archivoDeRecursosDAO() {
            return new ArchivoDeRecursosDAOImpl();
        }

        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }
    }
    
    
    @Test
    public void obtenerDetalleOperacionesPrecargadasOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00548595000000092017120711505300000000IBF005H3000000000000CNSDETTRA_1000000            03682408    00        00 011526016201712071150400000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0160600000000õNõ00õ000õ3604292õ03õPRECARGA                                          õARSõINNAMORATO                    õBAUDILIO MANSSUR              õ          õ0000000000õ                                                                                                    õSõ000000003800000õ000000000591877õ0000000000õ0000000000õ0000264200õ0000264200õ0000304779õ0000378459õ     õNõNõSõNõNõNõ01õ01õ000000000003500õ000000000000000õ000000000000048õ000000000000000õ000000000000012õ000000000003440õ000000000000000õ000000000003440õ000000000250000õ04õ08õCONFIRMADO                                        õNõNõNõ2017-12-07õ                                                                                                    õ002õ015õ112õ1059õ7õ49069253õ4õ24300148244õ8õ019õ000000000003500õ2017-12-22õ000000000000000õ000000000000048õ000000000000000õ000000000000012õ000000000003440õNõ00023568856õ õ           õ1õ                                                                                                    õ                                                                                                    õ                                                  õ0000013700õ072õ000õ1000õ2õ51100044õ1õ00003616534õ6õ013õ000000000250000õ2017-12-20õ000000000000000õ000000000002352õ000000000000000õ000000000000601õ000000000247047õNõ00014253647õ õ           õ2õPOR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            õN00014253647 SUPERA EL 30% DEL MONTO DE CESION                                                      õ                                                  õ0000009400õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        DetalleOperacionesPrecargadasEntity  datosCesion = detalleOperacionesPrecargadasDAO.obtenerDetalleOperacionesPrecargadas(cliente, "");
        Gson gson = new Gson();
        String json = gson.toJson(datosCesion);
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test
    public void obtenerDetalleHistorialOperacionesOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00339021000000122017120109164200000000IBF000AF000000000000CNSDETTRA_1000000            03682408    00        00 009108934201712010916290000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0160600000000õNõ00õ000õ3604292õ03õPRECARGA                                          õARSõINNAMORATO                    õBAUDILIO MANSSUR              õ          õ0000000000õ                                                                                                    õSõ000000003800000õ000000001552477õ0000000000õ0000000000õ0000264200õ0000264200õ0000304652õ0000341629õ     õNõNõSõNõNõNõ02õ00õ000000000002500õ000000000000000õ000000000000029õ000000000000000õ000000000000008õ000000000002463õ000000000000000õ000000000002463õ000000000000000õ04õ08õCONFIRMADO                                        õNõNõNõ2017-11-30õ                                                                                                    õ002õ015õ112õ1059õ7õ49069253õ4õ24300148244õ8õ018õ000000000001000õ2017-12-15õ000000000000000õ000000000000013õ000000000000000õ000000000000004õ000000000000983õNõ00028536389õ õ           õ1õ                                                                                                    õ                                                                                                    õ                                                  õ0000013000õ072õ000õ1000õ2õ51100044õ1õ00003616534õ6õ015õ000000000001500õ2017-12-15õ000000000000000õ000000000000016õ000000000000000õ000000000000004õ000000000001480õNõ00023568856õ õ           õ1õ                                                                                                    õ                                                                                                    õ                                                  õ0000010600õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        DetalleOperacionesPrecargadasEntity  datosCesion = detalleOperacionesPrecargadasDAO.obtenerDetalleOperacionesPrecargadas(cliente, "");
        Gson gson = new Gson();
        String json = gson.toJson(datosCesion);
        Assert.assertTrue(datosCesion!=null);
    }
}
