package ar.com.santanderrio.obp.servicios.tenencias.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.dao.TenenciasDAO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CuentaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.CustodiaDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmanteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantePrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FondoPendienteDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ImpuestoMonedaExtranjeraDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.RendimientoFondoDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TarjetasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.entity.CuentasEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.CustodiaEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmanteEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantePrestamoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosRimpEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FondosPendientesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ImpuestoMonedaExtranjeraEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ImpuestosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.PrestamosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.RendimientoFondosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasOutEntity;

/**
 * The class TenenciasBOTest
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TenenciasBOTest {
    
    /**
     * The agenda tenencias BO.
     */
    @InjectMocks
    private TenenciasBOImpl tenenciasBOImpl = new TenenciasBOImpl();
    
    /**
     * The Tenencia DAO.
     */
    @Mock
    private TenenciasDAO tenenciasDAO;
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Test
    public void testConsultarTenencias() throws DAOException{ 
        TenenciasInDTO inDTO = new TenenciasInDTO();
        inDTO.setAnioDesde("2015");
        inDTO.setAnioHasta("2015");
        inDTO.setNup("123456");
        
        TenenciasOutEntity theEntity = new TenenciasOutEntity();
        theEntity.setCotiDolar(new BigDecimal("10.00"));
        theEntity.setCuentasEntity(new ArrayList<CuentasEntity>());
        theEntity.setCustodiaEntity(new ArrayList<CustodiaEntity>());
        theEntity.setFondosEntity(new ArrayList<FondosRimpEntity>());
        theEntity.setFondosPendientesEntity(new ArrayList<FondosPendientesEntity>());
        theEntity.setImpuestoMonedaExtranjeraEntity(new ArrayList<ImpuestoMonedaExtranjeraEntity>());
        theEntity.setImpuestosEntity(new ArrayList<ImpuestosEntity>());
        theEntity.setPlazoFijoEntity(new ArrayList<PlazoFijoEntity>());
        theEntity.setPrestamosEntity(new ArrayList<PrestamosEntity>());
        theEntity.setRendimientoFondosEntity(new ArrayList<RendimientoFondosEntity>());
        theEntity.setTarjetasEntity(new ArrayList<TarjetasEntity>());
        
        TenenciasDTO theDTO = new TenenciasDTO();
        theDTO.setCotiDolar(new BigDecimal("10.00"));
        theDTO.setCuentasDTO(new ArrayList<CuentaDTO>());
        theDTO.setCustodiaDTO(new ArrayList<CustodiaDTO>());
        theDTO.setFondosDTO(new ArrayList<FondoDTO>());
        theDTO.setFondosPendientesDTO(new ArrayList<FondoPendienteDTO>());
        theDTO.setImpuestoMonedaExtranjeraDTO(new ArrayList<ImpuestoMonedaExtranjeraDTO>());
        theDTO.setImpuestosDTO(new ArrayList<ImpuestoDTO>());
        theDTO.setPlazoFijoDTO(new ArrayList<PlazoFijoDTO>());
        theDTO.setPrestamosDTO(new ArrayList<PrestamoDTO>());
        theDTO.setRendimientoFondosDTO(new ArrayList<RendimientoFondoDTO>());
        theDTO.setTarjetasDTO(new ArrayList<TarjetasDTO>());
        
        Respuesta<TenenciasDTO> expected = new Respuesta<TenenciasDTO>();
        expected.setEstadoRespuesta(EstadoRespuesta.OK);
        expected.setRespuesta(theDTO);
        
        
        when(tenenciasDAO.obtenerTenecias(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(theEntity);

        Respuesta<TenenciasDTO> respuesta = tenenciasBOImpl.consultarTenencias(inDTO);
        
        assertEquals(respuesta, expected);
    }
    
    @Test
    public void testConsultarFirmantes() throws DAOException{ 
        TenenciasInDTO inDTO = new TenenciasInDTO();
        inDTO.setAnioDesde("2015");
        inDTO.setAnioHasta("2015");
        inDTO.setNup("123456");
        
        FirmantesOutEntity theEntity = new FirmantesOutEntity();
        theEntity.setCuentas(new ArrayList<FirmanteEntity>());
        theEntity.setCustodias(new ArrayList<FirmanteEntity>());
        theEntity.setFondos(new ArrayList<FirmanteEntity>());
        theEntity.setPlazoFijo(new ArrayList<FirmanteEntity>());
        theEntity.setPrestamos(new ArrayList<FirmantePrestamoEntity>());
        
        FirmantesDTO theDTO = new FirmantesDTO();
        theDTO.setCuentas(new ArrayList<FirmanteDTO>());
        theDTO.setCustodias(new ArrayList<FirmanteDTO>());
        theDTO.setFondos(new ArrayList<FirmanteDTO>());
        theDTO.setPlazoFijo(new ArrayList<FirmanteDTO>());
        theDTO.setPrestamos(new ArrayList<FirmantePrestamoDTO>());
        
        Respuesta<FirmantesDTO> expected = new Respuesta<FirmantesDTO>();
        expected.setEstadoRespuesta(EstadoRespuesta.OK);
        expected.setRespuesta(theDTO);
        
        when(tenenciasDAO.obtenerFirmantes(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(theEntity);

        Respuesta<FirmantesDTO> respuesta = tenenciasBOImpl.consultarFirmantes(inDTO);
        
        assertEquals(respuesta, expected);
    }
}
